package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.FindCommand.FindCondition;

/**
 * Tests that a {@code Person}'s fields matches the keywords given.
 */
public class PersonMatchesKeywordsPredicate implements Predicate<Person> {
    private List<String> nameKeywords;
    private List<String> addressKeywords;

    private FindCondition condition = FindCondition.ALL; // default find condition is match all

    /**
     * Returns true if at least one field is searched.
     *
     * @return True if at least one field is searched.
     */
    public boolean isAnyFieldSearched() {
        return CollectionUtil.isAnyNonNull(nameKeywords, addressKeywords);
    }

    /**
     * Sets keywords to match with a Person's name.
     *
     * @param keywords Name keywords to find.
     */
    public void setNameKeywords(List<String> keywords) {
        this.nameKeywords = keywords;
    }

    /**
     * Sets keywords to match with a Person's address.
     *
     * @param keywords Address keywords to find.
     */
    public void setAddressKeywords(List<String> keywords) {
        this.addressKeywords = keywords;
    }

    /**
     * Sets find condition.
     *
     * @param condition Find condition
     */
    public void setCondition(FindCondition condition) {
        this.condition = condition;
    }

    /**
     * Returns true if field matches any of the keywords given.
     *
     * @param keywords Keywords to find.
     * @param field    Person's field to match with keywords.
     * @return True if field matches keywords.
     */
    private boolean isMatch(List<String> keywords, String field) {
        requireAllNonNull(keywords, field);
        return keywords.stream().anyMatch(keyword -> StringUtil.containsWordIgnoreCase(field, keyword));
    }

    /**
     * Returns a {@code Predicate} that tests that a {@code Person}'s {@code Name} matches the keywords given.
     *
     * @return A predicate that tests a person's name.
     */
    private Predicate<Person> getNamePredicate() {
        return person -> isMatch(nameKeywords, person.getName().fullName);
    }

    /**
     * Returns a {@code Predicate} that tests that a {@code Person}'s {@code Address} matches the keywords given.
     *
     * @return A predicate that tests a person's address.
     */
    private Predicate<Person> getAddressPredicate() {
        return person -> isMatch(addressKeywords, person.getAddress().value);
    }

    /**
     * Returns a list of predicates that test a person's fields against non-null keywords.
     *
     * @return A list of person predicates.
     */
    private List<Predicate<Person>> getPredicates() {
        List<Predicate<Person>> predicates = new ArrayList<>();
        if (nameKeywords != null) {
            predicates.add(getNamePredicate());
        }
        if (addressKeywords != null) {
            predicates.add(getAddressPredicate());
        }

        return predicates;
    }

    /**
     * Returns a composed predicate that represents matching all of the given predicates.
     *
     * @param predicates A list of predicates to match.
     * @return A predicate that returns true only if all predicates test true on a person.
     */
    private Predicate<Person> matchAll(List<Predicate<Person>> predicates) {
        return predicates.stream().reduce(x -> true, Predicate::and);
    }

    /**
     * Returns a composed predicate that represents matching any one of the given predicates.
     *
     * @param predicates A list of predicates to match.
     * @return A predicate that returns true if any one of the predicates test true on a person.
     */
    private Predicate<Person> matchAny(List<Predicate<Person>> predicates) {
        return predicates.stream().reduce(x -> false, Predicate::or);
    }

    /**
     * Returns a composed predicate that represents matching none of the given predicates.
     *
     * @param predicates A list of predicates to match.
     * @return A predicate that returns true only if none of the predicates test true on a person.
     */
    private Predicate<Person> matchNone(List<Predicate<Person>> predicates) {
        return matchAny(predicates).negate();
    }

    /**
     * Evaluates this predicate on the given person.
     *
     * @param person The person to test on.
     * @return True if the person matches the predicate, otherwise false
     */
    @Override
    public boolean test(Person person) {
        List<Predicate<Person>> predicates = getPredicates();
        switch (condition) {
        case ALL:
            return matchAll(predicates).test(person);
        case ANY:
            return matchAny(predicates).test(person);
        case NONE:
            return matchNone(predicates).test(person);
        default:
            // should not reach here
            return false;
        }
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
