package vsi.com.br.textprocessing.teste;

import org.springframework.http.HttpStatus;
import vsi.com.br.textprocessing.shared.builders.AnagramRequestBuilder;
import vsi.com.br.textprocessing.shared.builders.AppResponseBuilder;
import vsi.com.br.textprocessing.shared.request.AnagramRequest;

import java.util.Arrays;
import java.util.List;

public class TextScenarioCreator {
    public static String INVALID_LETTERS_ERROR_CODE = "INVALID_LETTERS";
    public static String DUPLICATED_LETTERS_ERROR_CODE = "DUPLICATED_LETTERS";

    public static String LETTER_LIST_IS_NULL_OR_EMPTY_ERROR_MESSAGE = "The Provided Anagram Letters List is Null or Empty.";
    public static String INVALID_LETTER_LIST_ERROR_MESSAGE = "The Provided Anagram Letters Must Be Valid Single Letter, Without Numbers or Symbols.";
    public static String DUPLICATED_LETTERS_ERROR_MESSAGE = "The Provided Anagram Letters Must Be Unique, Without Duplications.";


    static List<String> VALID_LETTER_LIST = Arrays.asList("a", "b", "c");

    public static List<String> INVALID_LETTER_LIST_WITH_NUMBERS = Arrays.asList(
            "a", "b", "3", "d", "e", "f", "g", "h", "1", "j", "k",
            "l", "m", "n", "o", "p", "q", "r", "s", "2", "u", "v",
            "w", "x", "y", "z");

    public static List<String> INVALID_LETTER_LIST_WITH_SYMBOLS = Arrays.asList(
            "a", "b", "#", "d", "e", "f", "g", "h", "@", "j", "k",
            "l", "m", "n", "o", "$", "q", "r", "s", "!", "u", "v",
            "w", "x", "y", "z");

    public static List<String> INVALID_LETTER_LIST_WITH_DUPLICATES = Arrays.asList(
            "a", "b", "c", "d", "e", "f", "g", "h", "c", "j", "k",
            "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
            "w", "x", "y", "z", "a", "b", "c", "d", "e", "f", "g",
            "h", "i", "j", "k");

    public static List<String> VALID_TEXT_PROCESS_RETURN_LIST = Arrays.asList(
            "acb",
            "bca",
            "abc",
            "cba",
            "bac",
            "cab"
    );

    public static AnagramRequestBuilder aValidAnagramRequest() {
        return new AnagramRequestBuilder().withLetters(VALID_LETTER_LIST);
    }

    public static AppResponseBuilder<AnagramRequest> aValidAppResponse() {
        return new AppResponseBuilder<AnagramRequest>()
                .withSuccess(true)
                .withStatus(HttpStatus.OK)
                .withData(aValidAnagramRequest().build());
    }

    public static AppResponseBuilder<AnagramRequest> anInValidAppResponse(HttpStatus status, String errorCode, String errorMessage) {
        return new AppResponseBuilder<AnagramRequest>()
                .withSuccess(false)
                .withStatus(status)
                .withData(null)
                .withErrorCode(errorCode)
                .withErrorMessage(errorMessage);
    }
}
