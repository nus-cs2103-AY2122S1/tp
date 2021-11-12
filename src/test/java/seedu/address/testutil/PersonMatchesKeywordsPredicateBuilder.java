package seedu.address.testutil;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.lesson.Date;
import seedu.address.model.lesson.TimeRange;
import seedu.address.model.person.PersonMatchesKeywordsPredicate;

public class PersonMatchesKeywordsPredicateBuilder {

    private PersonMatchesKeywordsPredicate predicate;

    public PersonMatchesKeywordsPredicateBuilder() {
        predicate = new PersonMatchesKeywordsPredicate();
    }

    /**
     * Sets the find condition of the {@code PersonMatchesKeywordsPredicate} that we are building.
     */
    public PersonMatchesKeywordsPredicateBuilder withCondition(FindCommand.FindCondition condition) {
        predicate.setCondition(condition);
        return this;
    }

    private List<String> parseKeywords(String keywords) {
        return Arrays.asList(keywords.split("\\s+"));
    }

    /**
     * Sets the name keywords of the {@code PersonMatchesKeywordsPredicate} that we are building.
     */
    public PersonMatchesKeywordsPredicateBuilder withName(String keywords) {
        predicate.setNameKeywords(parseKeywords(keywords));
        return this;
    }

    /**
     * Sets the phone keywords of the {@code PersonMatchesKeywordsPredicate} that we are building.
     */
    public PersonMatchesKeywordsPredicateBuilder withPhone(String keywords) {
        predicate.setPhoneKeywords(parseKeywords(keywords));
        return this;
    }

    /**
     * Sets the email keywords of the {@code PersonMatchesKeywordsPredicate} that we are building.
     */
    public PersonMatchesKeywordsPredicateBuilder withEmail(String keywords) {
        predicate.setEmailKeywords(parseKeywords(keywords));
        return this;
    }

    /**
     * Sets the parent phone keywords of the {@code PersonMatchesKeywordsPredicate} that we are building.
     */
    public PersonMatchesKeywordsPredicateBuilder withParentPhone(String keywords) {
        predicate.setParentPhoneKeywords(parseKeywords(keywords));
        return this;
    }

    /**
     * Sets the parent email keywords of the {@code PersonMatchesKeywordsPredicate} that we are building.
     */
    public PersonMatchesKeywordsPredicateBuilder withParentEmail(String keywords) {
        predicate.setParentEmailKeywords(parseKeywords(keywords));
        return this;
    }

    /**
     * Sets the address keywords of the {@code PersonMatchesKeywordsPredicate} that we are building.
     */
    public PersonMatchesKeywordsPredicateBuilder withAddress(String keywords) {
        predicate.setAddressKeywords(parseKeywords(keywords));
        return this;
    }

    /**
     * Sets the school keywords of the {@code PersonMatchesKeywordsPredicate} that we are building.
     */
    public PersonMatchesKeywordsPredicateBuilder withSchool(String keywords) {
        predicate.setSchoolKeywords(parseKeywords(keywords));
        return this;
    }

    /**
     * Sets the academic stream keywords of the {@code PersonMatchesKeywordsPredicate} that we are building.
     */
    public PersonMatchesKeywordsPredicateBuilder withAcadStream(String keywords) {
        predicate.setAcadStreamKeywords(parseKeywords(keywords));
        return this;
    }

    /**
     * Sets the academic level keywords of the {@code PersonMatchesKeywordsPredicate} that we are building.
     */
    public PersonMatchesKeywordsPredicateBuilder withAcadLevel(String keywords) {
        predicate.setAcadLevelKeywords(parseKeywords(keywords));
        return this;
    }

    /**
     * Sets the tag keywords of the {@code PersonMatchesKeywordsPredicate} that we are building.
     */
    public PersonMatchesKeywordsPredicateBuilder withTags(String... keywords) {
        predicate.setTagKeywords(Arrays.asList(keywords));
        return this;
    }

    /**
     * Sets the remark keywords of the {@code PersonMatchesKeywordsPredicate} that we are building.
     */
    public PersonMatchesKeywordsPredicateBuilder withRemark(String keywords) {
        predicate.setRemarkKeywords(parseKeywords(keywords));
        return this;
    }

    /**
     * Sets the time range of the {@code PersonMatchesKeywordsPredicate} that we are building.
     */
    public PersonMatchesKeywordsPredicateBuilder withTimeRange(String timeRange) {
        predicate.setTimeRange(new TimeRange(timeRange));
        return this;
    }

    /**
     * Sets the start date of the {@code PersonMatchesKeywordsPredicate} that we are building.
     */
    public PersonMatchesKeywordsPredicateBuilder withDate(String date) {
        predicate.setDate(new Date(date));
        return this;
    }

    /**
     * Sets the cancelled date of the {@code PersonMatchesKeywordsPredicate} that we are building.
     */
    public PersonMatchesKeywordsPredicateBuilder withCancelledDate(String cancelledDate) {
        predicate.setCancelledDate(new Date(cancelledDate));
        return this;
    }

    /**
     * Sets the subject keywords of the {@code PersonMatchesKeywordsPredicate} that we are building.
     */
    public PersonMatchesKeywordsPredicateBuilder withSubject(String keywords) {
        predicate.setSubjectKeywords(parseKeywords(keywords));
        return this;
    }

    /**
     * Sets the rates keywords of the {@code PersonMatchesKeywordsPredicate} that we are building.
     */
    public PersonMatchesKeywordsPredicateBuilder withRates(String keywords) {
        predicate.setRatesKeywords(parseKeywords(keywords));
        return this;
    }

    /**
     * Sets the homework keywords of the {@code PersonMatchesKeywordsPredicate} that we are building.
     */
    public PersonMatchesKeywordsPredicateBuilder withHomework(String keywords) {
        predicate.setHomeworkKeywords(parseKeywords(keywords));
        return this;
    }

    public PersonMatchesKeywordsPredicate build() {
        return predicate;
    }
}
