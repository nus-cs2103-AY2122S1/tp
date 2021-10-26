package seedu.academydirectory.model.student;

import java.util.List;
import java.util.function.Predicate;

public class InformationContainsKeywordsPredicate implements Predicate<Student> {
    private final List<String> keywords;

    public InformationContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    @Override
    public boolean test(Student student) {
        return new NameContainsKeywordsPredicate(keywords).test(student)
                || new TagContainsKeywordsPredicate(keywords).test(student);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InformationContainsKeywordsPredicate // instanceof handles nulls
                && this.getKeywords().equals(((InformationContainsKeywordsPredicate) other)
                .getKeywords())); // state check
    }
}
