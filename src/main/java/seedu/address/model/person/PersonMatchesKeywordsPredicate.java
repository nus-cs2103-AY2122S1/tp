package seedu.address.model.person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class PersonMatchesKeywordsPredicate implements Predicate<Person> {
    private List<String> nameKeywords;
    
    private List<String> addressKeywords;

    public PersonMatchesKeywordsPredicate() {
        nameKeywords = Collections.emptyList() ;
        addressKeywords = Collections.emptyList() ;
    }
    
    public void setNameKeywords(List<String> keywords ){
        nameKeywords = keywords;
    }

    public void setAddressKeywords(List<String> keywords ){
        addressKeywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        List<Predicate<Person>> predicates = new ArrayList<>();
        if (!nameKeywords.isEmpty()) {
            predicates.add(namePredicate());
        }
        if (!addressKeywords.isEmpty()) {
            predicates.add(addressPredicate());
        }
        
        return predicates.stream().reduce(x -> true, Predicate::and).test(person);
    }
    
    private Predicate<Person> namePredicate() {
        return person -> nameKeywords.stream()
            .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
    }

    private Predicate<Person> addressPredicate() {
        return person -> addressKeywords.stream()
            .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getAddress().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonMatchesKeywordsPredicate)) {
            return false;
        }

        PersonMatchesKeywordsPredicate p = (PersonMatchesKeywordsPredicate) other;
        
        return nameKeywords.equals(p.nameKeywords)
        && addressKeywords.equals(p.addressKeywords);
    }

}
