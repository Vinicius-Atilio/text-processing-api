package vsi.com.br.textprocessing.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vsi.com.br.textprocessing.service.TextProcessingService;
import vsi.com.br.textprocessing.shared.request.AnagramRequest;
import vsi.com.br.textprocessing.shared.response.AppResponse;

import java.util.List;

@RestController
@RequestMapping("api")
public class TextProcessingController {
    private final TextProcessingService textProcessingService;

    public TextProcessingController(TextProcessingService textProcessingService) {
        this.textProcessingService = textProcessingService;
    }

    @PostMapping("generator")
    public ResponseEntity<AppResponse<List<String>>> anagramGenerator(@RequestBody @Valid AnagramRequest anagramRequest) {
        return new ResponseEntity<>(AppResponse.Execute(() ->
                this.textProcessingService.generateAnagram(anagramRequest)), HttpStatus.OK);
    }
}
