package vsi.com.br.textprocessing.shared.request;

import org.springframework.http.HttpStatus;
import vsi.com.br.textprocessing.shared.Validators.ValueValidator;
import vsi.com.br.textprocessing.shared.enums.ErrorType;
import vsi.com.br.textprocessing.shared.exceptions.VSIException;

import java.util.List;
import java.util.stream.Collectors;

public class AnagramRequest {
    private List<String> letters;

    public List<String> getLetters() {
        return letters;
    }

    public AnagramRequest() {}

    public AnagramRequest(List<String> letters) {
        this.letters = letters;
    }

    @Override
    public String toString() {
        return letters.stream()
                .map(letter -> "\"" + letter + "\"")
                .collect(Collectors.joining(",", "[", "]"));
    }

    public void validate() {
        if (ValueValidator.isNullOrEmpty(this.letters)) {
            throw VSIException.of(ErrorType.Validation, HttpStatus.BAD_REQUEST, "INVALID_LETTERS",
                    "The Provided Anagram Letters List is Null or Empty.");
        }

        if (ValueValidator.isValidArrayOfLetters(this.letters)) {
            throw VSIException.of(ErrorType.Validation, HttpStatus.BAD_REQUEST, "INVALID_LETTERS",
                    "The Provided Anagram Letters Must Be Valid Single Letter, Without Numbers or Symbols.");
        }

        if (ValueValidator.hasAnyDuplicates(this.letters)) {
            throw VSIException.of(ErrorType.Validation, HttpStatus.BAD_REQUEST, "DUPLICATED_LETTERS",
                    "The Provided Anagram Letters Must Be Unique, Without Duplications.");
        }
    }
}
