package seedu.address.testutil;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.FindCommand;
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
     * Sets the tag keywords of the {@code PersonMatchesKeywordsPredicate} that we are building.
     */
    public PersonMatchesKeywordsPredicateBuilder withTags(String... keywords) {
        predicate.setTagKeywords(Arrays.asList(keywords));
        return this;
    }

    /**
     * Sets the tag keywords of the {@code PersonMatchesKeywordsPredicate} that we are building.
     */
    public PersonMatchesKeywordsPredicateBuilder withTags(List<String> keywords) {
        predicate.setTagKeywords(keywords);
        return this;
    }

    public PersonMatchesKeywordsPredicate build() {
        return predicate;
    }
}
