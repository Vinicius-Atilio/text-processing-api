package vsi.com.br.textprocessing.service;

import org.springframework.stereotype.Service;
import vsi.com.br.textprocessing.shared.exceptions.Logger.VSILoggerAdapter;
import vsi.com.br.textprocessing.shared.exceptions.VSIException;
import vsi.com.br.textprocessing.shared.request.AnagramRequest;

import java.util.*;

@Service
public class TextProcessingService {
    private VSILoggerAdapter vsiLoggerAdapter;

    public TextProcessingService(VSILoggerAdapter vsiLoggerAdapter) {
        this.vsiLoggerAdapter = vsiLoggerAdapter;
    }

    public List<String> generateAnagram(AnagramRequest anagramRequest) {
        try {
            anagramRequest.validate();
            Set<String> anagrams = new HashSet<>();
            generateAnagrams(anagramRequest.getLetters(), anagrams, 0);
            return anagrams.stream().toList();
        } catch (VSIException ex) {
            this.vsiLoggerAdapter.logException(ex);
            throw VSIException.of(ex.getErrorType(), ex.getHttpStatus(), ex.getErrorCode(), ex.getErrorMessage());
        }
    }

    private void generateAnagrams(List<String> letters,  Set<String> anagrams , int index) {
        if (anagrams.size() >= 362880) return;

        if (index == letters.size() - 1) {
            anagrams.add(String.join("", letters));
            return;
        }

        Set<String> anagramsAtIndex = new HashSet<>();
        for (int i = 0; i < letters.size(); i++) {
            if (anagramsAtIndex.add(letters.get(i))) {
                Collections.swap(letters, index, i);
                generateAnagrams(letters, anagrams, index + 1);
                Collections.swap(letters, index, i);
            }
        }
    }
}
