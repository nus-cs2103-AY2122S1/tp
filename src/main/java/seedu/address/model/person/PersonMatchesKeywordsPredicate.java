package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    private List<String> schoolKeywords;
    private List<String> acadStreamKeywords;
    private List<String> acadLevelKeywords;
    private List<String> tagKeywords;

    private FindCondition condition = FindCondition.ALL; // default find condition is match all

    /**
     * Returns true if at least one field is searched.
     *
     * @return True if at least one field is searched.
     */
    public boolean isAnyFieldSearched() {
        return CollectionUtil.isAnyNonNull(nameKeywords, phoneKeywords, emailKeywords,
                parentPhoneKeywords, parentEmailKeywords, addressKeywords,
                schoolKeywords, acadStreamKeywords, acadLevelKeywords, tagKeywords);
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
     * Sets keywords to match with a Person's school.
     *
     * @param keywords School keywords to find.
     */
    public void setSchoolKeywords(List<String> keywords) {
        this.schoolKeywords = keywords;
    }

    /**
     * Sets keywords to match with a Person's academic stream.
     *
     * @param keywords Academic stream keywords to find.
     */
    public void setAcadStreamKeywords(List<String> keywords) {
        this.acadStreamKeywords = keywords;
    }

    /**
     * Sets keywords to match with a Person's academic level.
     *
     * @param keywords Academic level keywords to find.
     */
    public void setAcadLevelKeywords(List<String> keywords) {
        this.acadLevelKeywords = keywords;
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
     * Returns the find condition.
     */
    public FindCondition getCondition() {
        return condition;
    }

    /**
     * Returns optional name keywords.
     */
    public Optional<List<String>> getNameKeywords() {
        return Optional.ofNullable(nameKeywords);
    }

    /**
     * Returns optional phone keywords.
     */
    public Optional<List<String>> getPhoneKeywords() {
        return Optional.ofNullable(phoneKeywords);
    }

    /**
     * Returns optional email keywords.
     */
    public Optional<List<String>> getEmailKeywords() {
        return Optional.ofNullable(emailKeywords);
    }

    /**
     * Returns optional parent phone keywords.
     */
    public Optional<List<String>> getParentPhoneKeywords() {
        return Optional.ofNullable(parentPhoneKeywords);
    }

    /**
     * Returns optional parent email keywords.
     */
    public Optional<List<String>> getParentEmailKeywords() {
        return Optional.ofNullable(parentEmailKeywords);
    }

    /**
     * Returns optional address keywords.
     */
    public Optional<List<String>> getAddressKeywords() {
        return Optional.ofNullable(addressKeywords);
    }

    /**
     * Returns optional school keywords.
     */
    public Optional<List<String>> getSchoolKeywords() {
        return Optional.ofNullable(schoolKeywords);
    }

    /**
     * Returns optional academic stream keywords.
     */
    public Optional<List<String>> getAcadStreamKeywords() {
        return Optional.ofNullable(acadStreamKeywords);
    }

    /**
     * Returns optional academic level keywords.
     */
    public Optional<List<String>> getAcadLevelKeywords() {
        return Optional.ofNullable(acadLevelKeywords);
    }

    /**
     * Returns optional tag keywords.
     */
    public Optional<List<String>> getTagKeywords() {
        return Optional.ofNullable(tagKeywords);
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
        return keywords.stream().allMatch(keyword -> StringUtil.containsSubstringIgnoreCase(field, keyword));
    }

    /**
     * Returns a {@code Predicate} that tests that a {@code Person}'s {@code Name} matches the keywords given.
     *
     * @return A predicate that tests a person's name.
     */
    private Predicate<Person> getNameMatchPredicate() {
        return person -> isMatch(nameKeywords, person.getName().fullName);
    }

    /**
     * Returns a {@code Predicate} that tests that a {@code Person}'s {@code Phone} matches the keywords given.
     *
     * @return A predicate that tests a person's phone number.
     */
    private Predicate<Person> getPhoneMatchPredicate() {
        return person -> isMatch(phoneKeywords, person.getPhone().value);
    }

    /**
     * Returns a {@code Predicate} that tests that a {@code Person}'s {@code Email} matches the keywords given.
     *
     * @return A predicate that tests a person's email.
     */
    private Predicate<Person> getEmailMatchPredicate() {
        return person -> isMatch(emailKeywords, person.getEmail().value);
    }

    /**
     * Returns a {@code Predicate} that tests that a {@code Person}'s parent {@code Phone} matches the keywords given.
     *
     * @return A predicate that tests a person's parent phone number.
     */
    private Predicate<Person> getParentPhoneMatchPredicate() {
        return person -> isMatch(parentPhoneKeywords, person.getParentPhone().value);
    }

    /**
     * Returns a {@code Predicate} that tests that a {@code Person}'s parent {@code Email} matches the keywords given.
     *
     * @return A predicate that tests a person's parent email.
     */
    private Predicate<Person> getParentEmailMatchPredicate() {
        return person -> isMatch(parentEmailKeywords, person.getParentEmail().value);
    }

    /**
     * Returns a {@code Predicate} that tests that a {@code Person}'s {@code Address} matches the keywords given.
     *
     * @return A predicate that tests a person's address.
     */
    private Predicate<Person> getAddressMatchPredicate() {
        return person -> isMatch(addressKeywords, person.getAddress().value);
    }

    /**
     * Returns a {@code Predicate} that tests that a {@code Person}'s {@code School} matches the keywords given.
     *
     * @return A predicate that tests a person's school.
     */
    private Predicate<Person> getSchoolMatchPredicate() {
        return person -> isMatch(schoolKeywords, person.getSchool().value);
    }

    /**
     * Returns a {@code Predicate} that tests that a {@code Person}'s {@code AcadStream} matches the keywords given.
     *
     * @return A predicate that tests a person's academic stream.
     */
    private Predicate<Person> getAcadStreamMatchPredicate() {
        return person -> isMatch(acadStreamKeywords, person.getAcadStream().value);
    }

    /**
     * Returns a {@code Predicate} that tests that a {@code Person}'s {@code AcadLevel} matches the keywords given.
     *
     * @return A predicate that tests a person's academic level.
     */
    private Predicate<Person> getAcadLevelMatchPredicate() {
        return person -> isMatch(acadLevelKeywords, person.getAcadLevel().value);
    }

    /**
     * Returns a {@code List<Predicate<Person>>} that tests if each keyword matches a {@code Person}'s {@code Tag}s.
     *
     * @return A predicate that tests a person's tags.
     */
    private List<Predicate<Person>> getTagsMatchPredicates() {
        return tagKeywords.stream().map(keyword -> getTagMatchPredicate(keyword)).collect(Collectors.toList());
    }

    /**
     * Returns a {@code Predicate} that tests that one of a {@code Person}'s {@code tag}s matches the keyword given.
     * Ignores case, but the keyword must match the full tag name.
     *
     * @return A predicate that tests a person's phone number.
     */
    private Predicate<Person> getTagMatchPredicate(String keyword) {
        return person -> person.getTags().stream().anyMatch(tag -> keyword.equalsIgnoreCase(tag.tagName));
    }

    /**
     * Returns a list of predicates that test a person's fields against non-null keywords.
     *
     * @return A list of person predicates.
     */
    private List<Predicate<Person>> getPredicates() {
        List<Predicate<Person>> predicates = new ArrayList<>();
        if (getNameKeywords().isPresent()) {
            predicates.add(getNameMatchPredicate());
        }
        if (getPhoneKeywords().isPresent()) {
            predicates.add(getPhoneMatchPredicate());
        }
        if (getEmailKeywords().isPresent()) {
            predicates.add(getEmailMatchPredicate());
        }
        if (getParentPhoneKeywords().isPresent()) {
            predicates.add(getParentPhoneMatchPredicate());
        }
        if (getParentEmailKeywords().isPresent()) {
            predicates.add(getParentEmailMatchPredicate());
        }
        if (getAddressKeywords().isPresent()) {
            predicates.add(getAddressMatchPredicate());
        }
        if (getSchoolKeywords().isPresent()) {
            predicates.add(getSchoolMatchPredicate());
        }
        if (getAcadStreamKeywords().isPresent()) {
            predicates.add(getAcadStreamMatchPredicate());
        }
        if (getAcadLevelKeywords().isPresent()) {
            predicates.add(getAcadLevelMatchPredicate());
        }
        if (getTagKeywords().isPresent()) {
            predicates.addAll(getTagsMatchPredicates());
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
        assert condition != null;
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
        return getNameKeywords().equals(p.getNameKeywords())
                && getPhoneKeywords().equals(p.getPhoneKeywords())
                && getEmailKeywords().equals(p.getEmailKeywords())
                && getParentPhoneKeywords().equals(p.getParentPhoneKeywords())
                && getParentEmailKeywords().equals(p.getParentEmailKeywords())
                && getAddressKeywords().equals(p.getAddressKeywords())
                && getSchoolKeywords().equals(p.getSchoolKeywords())
                && getAcadStreamKeywords().equals(p.getAcadStreamKeywords())
                && getAcadLevelKeywords().equals(p.getAcadLevelKeywords())
                && getCondition().equals(p.getCondition());
    }

}
