package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

public class PersonModuleCodesContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PersonModuleCodesContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .allMatch(keyword -> person.getModuleCodes()
                        .stream().anyMatch(moduleCode ->
                                keyword.equalsIgnoreCase(moduleCode.toString())
                        )
                );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonModuleCodesContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonModuleCodesContainsKeywordsPredicate) other).keywords)); // state check
    }
}
