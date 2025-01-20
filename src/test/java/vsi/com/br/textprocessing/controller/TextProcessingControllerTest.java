package vsi.com.br.textprocessing.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static vsi.com.br.textprocessing.teste.TextScenarioCreator.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import vsi.com.br.textprocessing.service.TextProcessingService;
import vsi.com.br.textprocessing.shared.exceptions.Logger.VSILoggerAdapter;
import vsi.com.br.textprocessing.shared.exceptions.VSIException;
import vsi.com.br.textprocessing.shared.request.AnagramRequest;

import java.io.UnsupportedEncodingException;

@SpringBootTest
@AutoConfigureJsonTesters
@AutoConfigureMockMvc
class TextProcessingControllerTest {

	@Mock
	private VSILoggerAdapter vsiLoggerAdapter;

	@Autowired
	private MockMvc mockMvc;

	private TextProcessingService textProcessingService;

    @Autowired
	private JacksonTester<AnagramRequest> json;

	@BeforeEach
	public void setup() {
		this.textProcessingService = new TextProcessingService(vsiLoggerAdapter);
	}

	private static void assertSuccessResponseStatus(MockHttpServletResponse response) throws UnsupportedEncodingException {
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(aValidAppResponse()
				.withData(aValidAnagramRequest()
						.withLetters(VALID_TEXT_PROCESS_RETURN_LIST)
						.build()
				).build()
				.toStringSucess(), response.getContentAsString());
	}

	private static void assertBadRequestResponseStatus(MockHttpServletResponse response, HttpStatus status,
	   String errorCode, String errorMessage) throws UnsupportedEncodingException {
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
		assertEquals(anInValidAppResponse(status, errorCode, errorMessage).build().toStringError(),
				response.getContentAsString());
	}

	private MockHttpServletResponse getMockHttpServletResponse(AnagramRequest value) throws Exception {
		return mockMvc.perform(
						post("/api/generator")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON)
								.content(
										json.write(value).getJson()
								)
				)
				.andReturn()
				.getResponse();
	}

	@Test
	void shouldReturnList() throws Exception {
		var validRequest = aValidAnagramRequest().build();
		this.textProcessingService.generateAnagram(validRequest);
		MockHttpServletResponse response = getMockHttpServletResponse(validRequest);
		assertSuccessResponseStatus(response);
	}

	@Test
	void shouldThrowVSIExceptionWhenLetterListIsNull() throws Exception {
		var invalidRequest = aValidAnagramRequest().withLetters(null).build();
		assertThrows(VSIException.class, () -> this.textProcessingService.generateAnagram(invalidRequest));
		MockHttpServletResponse response = getMockHttpServletResponse(invalidRequest);
		assertBadRequestResponseStatus(response, HttpStatus.BAD_REQUEST,
				INVALID_LETTERS_ERROR_CODE, LETTER_LIST_IS_NULL_OR_EMPTY_ERROR_MESSAGE);
	}

	@Test
	void shouldThrowVSIExceptionWhenLetterListContainsNumber() throws Exception {
		var invalidRequest = aValidAnagramRequest().withLetters(INVALID_LETTER_LIST_WITH_NUMBERS).build();
		assertThrows(VSIException.class, () -> this.textProcessingService.generateAnagram(invalidRequest));
		MockHttpServletResponse response = getMockHttpServletResponse(invalidRequest);
		assertBadRequestResponseStatus(response, HttpStatus.BAD_REQUEST,
				INVALID_LETTERS_ERROR_CODE,
				INVALID_LETTER_LIST_ERROR_MESSAGE);
	}

	@Test
	void shouldThrowVSIExceptionWhenLetterListContainsSymbols() throws Exception {
		var invalidRequest = aValidAnagramRequest().withLetters(INVALID_LETTER_LIST_WITH_SYMBOLS).build();
		assertThrows(VSIException.class, () -> this.textProcessingService.generateAnagram(invalidRequest));
		MockHttpServletResponse response = getMockHttpServletResponse(invalidRequest);
		assertBadRequestResponseStatus(response, HttpStatus.BAD_REQUEST,
				INVALID_LETTERS_ERROR_CODE, INVALID_LETTER_LIST_ERROR_MESSAGE);
	}

	@Test
	void shouldThrowVSIExceptionWhenLetterListContainsDuplicatedLetters() throws Exception {
		var invalidRequest = aValidAnagramRequest().withLetters(INVALID_LETTER_LIST_WITH_DUPLICATES).build();
		assertThrows(VSIException.class, () -> this.textProcessingService.generateAnagram(invalidRequest));
		MockHttpServletResponse response = getMockHttpServletResponse(invalidRequest);
		assertBadRequestResponseStatus(response, HttpStatus.BAD_REQUEST,
				DUPLICATED_LETTERS_ERROR_CODE, DUPLICATED_LETTERS_ERROR_MESSAGE);
	}

}
