package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class EmploymentTypeContainsKeywordsPredicate implements Predicate<Person> {

    private final String keyword;

    public EmploymentTypeContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

//    @Override
//    public boolean test(Person person) {
//        System.out.println(person.getEmploymentType().employmentType);
//        return keywords.stream()
//                .anyMatch(keyword -> StringUtil
//                        .containsWordIgnoreCase(person.getEmploymentType().employmentType, keyword));
//
//    }

    @Override
    public boolean test(Person person) {
        return keyword.equalsIgnoreCase(person.getEmploymentType().employmentType);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmploymentTypeContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((EmploymentTypeContainsKeywordsPredicate) other).keyword)); // state check
    }
}
