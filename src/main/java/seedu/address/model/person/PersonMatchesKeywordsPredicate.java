package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.FindCommand.FindCondition;

/**
 * Tests that a {@code Person}'s fields matches the keywords given.
 */
public class PersonMatchesKeywordsPredicate implements Predicate<Person> {
    private List<String> nameKeywords;
    private List<String> phoneKeywords;
    private List<String> emailKeywords;
    private List<String> parentPhoneKeywords;
    private List<String> parentEmailKeywords;
    private List<String> addressKeywords;
    private List<String> tagKeywords;

    private FindCondition condition = FindCondition.ALL; // default find condition is match all

    /**
     * Returns true if at least one field is searched.
     *
     * @return True if at least one field is searched.
     */
    public boolean isAnyFieldSearched() {
        return CollectionUtil.isAnyNonNull(nameKeywords, phoneKeywords, emailKeywords, parentPhoneKeywords,
            parentEmailKeywords, addressKeywords, tagKeywords);
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
     * Sets keywords to match with a Person's phone number.
     *
     * @param keywords Phone keywords to find.
     */
    public void setPhoneKeywords(List<String> keywords) {
        this.phoneKeywords = keywords;
    }

    /**
     * Sets keywords to match with a Person's email.
     *
     * @param keywords Email keywords to find.
     */
    public void setEmailKeywords(List<String> keywords) {
        this.emailKeywords = keywords;
    }

    /**
     * Sets keywords to match with a Person's parent phone number.
     *
     * @param keywords Parent phone keywords to find.
     */
    public void setParentPhoneKeywords(List<String> keywords) {
        this.parentPhoneKeywords = keywords;
    }

    /**
     * Sets keywords to match with a Person's parent email.
     *
     * @param keywords Parent email keywords to find.
     */
    public void setParentEmailKeywords(List<String> keywords) {
        this.parentEmailKeywords = keywords;
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
     * Sets tag keywords to match with a Person's tags.
     *
     * @param keywords Phone keywords to find.
     */
    public void setTagKeywords(List<String> keywords) {
        this.tagKeywords = keywords;
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
     * There is a match if a keyword is a substring of the field.
     *
     * @param keywords Keywords to find.
     * @param field    Person's field to match with keywords.
     * @return True if field matches keywords.
     */
    private boolean isMatch(List<String> keywords, String field) {
        requireAllNonNull(keywords, field);
        return keywords.stream().anyMatch(keyword -> StringUtil.containsSubstringIgnoreCase(field, keyword));
    }

    /**
     * Returns a {@code Predicate} that tests that a {@code Person}'s {@code Name} matches the keywords given.
     *
     * @return A predicate that tests a person's name.
     */
    private Predicate<Person> nameMatchPredicate() {
        return person -> isMatch(nameKeywords, person.getName().fullName);
    }

    /**
     * Returns a {@code Predicate} that tests that a {@code Person}'s {@code Phone} matches the keywords given.
     *
     * @return A predicate that tests a person's phone number.
     */
    private Predicate<Person> phoneMatchPredicate() {
        return person -> isMatch(phoneKeywords, person.getPhone().value);
    }

    /**
     * Returns a {@code Predicate} that tests that a {@code Person}'s {@code Email} matches the keywords given.
     *
     * @return A predicate that tests a person's email.
     */
    private Predicate<Person> emailMatchPredicate() {
        return person -> isMatch(emailKeywords, person.getEmail().value);
    }

    /**
     * Returns a {@code Predicate} that tests that a {@code Person}'s parent {@code Phone} matches the keywords given.
     *
     * @return A predicate that tests a person's phone number.
     */
    private Predicate<Person> parentPhoneMatchPredicate() {
        return person -> isMatch(parentPhoneKeywords, person.getParentPhone().value);
    }

    /**
     * Returns a {@code Predicate} that tests that a {@code Person}'s parent {@code Email} matches the keywords given.
     *
     * @return A predicate that tests a person's email.
     */
    private Predicate<Person> parentEmailMatchPredicate() {
        return person -> isMatch(parentEmailKeywords, person.getParentEmail().value);
    }

    /**
     * Returns a {@code Predicate} that tests that a {@code Person}'s {@code Address} matches the keywords given.
     *
     * @return A predicate that tests a person's address.
     */
    private Predicate<Person> addressMatchPredicate() {
        return person -> isMatch(addressKeywords, person.getAddress().value);
    }

    /**
     * Returns a {@code List<Predicate<Person>>} that tests if each keyword matches a {@code Person}'s {@code Tag}s.
     *
     * @return A predicate that tests a person's tags.
     */
    private List<Predicate<Person>> tagsMatchPredicates() {
        return tagKeywords.stream().map(keyword -> tagMatchPredicate(keyword)).collect(Collectors.toList());
    }

    /**
     * Returns a {@code Predicate} that tests that any of a {@code Person}'s {@code tag}s matches the keyword given.
     *
     * @return A predicate that tests a person's phone number.
     */
    private Predicate<Person> tagMatchPredicate(String keyword) {
        return person -> person.getTags().stream().anyMatch(tag -> isMatch(List.of(keyword), tag.tagName));
    }

    /**
     * Returns a list of predicates that test a person's fields against non-null keywords.
     *
     * @return A list of person predicates.
     */
    private List<Predicate<Person>> getPredicates() {
        List<Predicate<Person>> predicates = new ArrayList<>();
        if (nameKeywords != null) {
            predicates.add(nameMatchPredicate());
        }
        if (phoneKeywords != null) {
            predicates.add(phoneMatchPredicate());
        }
        if (emailKeywords != null) {
            predicates.add(emailMatchPredicate());
        }
        if (parentPhoneKeywords != null) {
            predicates.add(parentPhoneMatchPredicate());
        }
        if (parentEmailKeywords != null) {
            predicates.add(parentEmailMatchPredicate());
        }
        if (addressKeywords != null) {
            predicates.add(addressMatchPredicate());
        }
        if (tagKeywords != null) {
            predicates.addAll(tagsMatchPredicates());
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
            && phoneKeywords.equals(p.phoneKeywords)
            && emailKeywords.equals(p.emailKeywords)
            && parentPhoneKeywords.equals(p.parentPhoneKeywords)
            && parentEmailKeywords.equals(p.parentEmailKeywords)
            && addressKeywords.equals(p.addressKeywords)
            && tagKeywords.equals(p.tagKeywords);
    }

}
