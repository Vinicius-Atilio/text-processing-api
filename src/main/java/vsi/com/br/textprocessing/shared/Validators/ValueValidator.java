package vsi.com.br.textprocessing.shared.Validators;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ValueValidator {

    public static <T> boolean isNullOrEmpty(Collection<T> list) {
        return list == null || list.isEmpty();
    }

    public static boolean isValidArrayOfLetters(Collection<String> list) {
        return !isNullOrEmpty(list) && list.stream().anyMatch(letter -> !letter.matches("^[a-zA-Z]+$"));
    }

    public static boolean hasAnyDuplicates(Collection<String> list) {
        Set<String> set = new HashSet<>(list);
        return set.size() < list.size();
    }
}
