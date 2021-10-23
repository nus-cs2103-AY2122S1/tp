package tutoraid.model.student;

import java.util.List;
import java.util.function.Predicate;

import tutoraid.commons.util.StringUtil;

/**
 * Tests that a {@code Student}'s {@code Name} contains any of the substrings given.
 */
public class NameContainsSubstringsPredicate implements Predicate<Student> {
    private final List<String> substrings;

    public NameContainsSubstringsPredicate(List<String> keywords) {
        this.substrings = keywords;
    }


    @Override
    public boolean test(Student student) {
        return substrings.stream()
                .anyMatch(substring ->
                        StringUtil.containsSubstringIgnoreCase(student.getStudentName().fullName, substring));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsSubstringsPredicate // instanceof handles nulls
                && substrings.equals(((NameContainsSubstringsPredicate) other).substrings)); // state check
    }

}
