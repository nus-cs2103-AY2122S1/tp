package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class FindCandidateCommandPredicate implements Predicate<Person> {

    private List<String> nameKeywords = new ArrayList<String>();
    private List<String> phoneKeywords = new ArrayList<String>();
    private List<String> emailKeywords = new ArrayList<String>();
    private List<String> addressKeywords = new ArrayList<String>();
    private List<String> tagKeywords = new ArrayList<String>();
    private List<String> statusKeywords = new ArrayList<String>();
    private List<String> positionKeywords = new ArrayList<String>();

    public FindCandidateCommandPredicate() {
    }

    /**
     * Alternative Constructor with all fields.
     * @param nameKeywords
     * @param phoneKeywords
     * @param emailKeywords
     * @param addressKeywords
     * @param tagKeywords
     * @param statusKeywords
     * @param positionKeywords
     */
    public FindCandidateCommandPredicate(List<String> nameKeywords, List<String> phoneKeywords,
                                         List<String> emailKeywords, List<String> addressKeywords,
                                         List<String> tagKeywords, List<String> statusKeywords,
                                         List<String> positionKeywords) {
        this.nameKeywords = nameKeywords;
        this.phoneKeywords = phoneKeywords;
        this.emailKeywords = emailKeywords;
        this.addressKeywords = addressKeywords;
        this.tagKeywords = tagKeywords;
        this.statusKeywords = statusKeywords;
        this.positionKeywords = positionKeywords;
    }

    /**
     * Alternative Constructor with only keyword field.
     * @param nameKeywords
     */
    public FindCandidateCommandPredicate(List<String> nameKeywords) {
        this.nameKeywords = nameKeywords;
    }

    @Override
    public boolean test(Person person) {
        boolean nameCheck = nameKeywords.isEmpty() || nameKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));

        boolean phoneCheck = phoneKeywords.isEmpty() || phoneKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getPhone().value, keyword));

        boolean emailCheck = emailKeywords.isEmpty() || emailKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getEmail().value, keyword));

        boolean addressCheck = addressKeywords.isEmpty() || addressKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getAddress().value, keyword));

        boolean tagCheck = tagKeywords.isEmpty() || tagKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getTagsString(), keyword));

        boolean statusCheck = statusKeywords.isEmpty() || statusKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getStatus().toString(), keyword));

        boolean positionCheck = positionKeywords.isEmpty() || positionKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getPositionsString(), keyword));

        return nameCheck && phoneCheck && emailCheck && addressCheck && tagCheck && statusCheck && positionCheck;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCandidateCommandPredicate // instanceof handles nulls
                && nameKeywords.equals(((FindCandidateCommandPredicate) other).nameKeywords) // state check
                && phoneKeywords.equals(((FindCandidateCommandPredicate) other).phoneKeywords)
                && addressKeywords.equals(((FindCandidateCommandPredicate) other).addressKeywords)
                && tagKeywords.equals(((FindCandidateCommandPredicate) other).tagKeywords)
                && statusKeywords.equals(((FindCandidateCommandPredicate) other).statusKeywords)
                && positionKeywords.equals(((FindCandidateCommandPredicate) other).positionKeywords));
    }

    public void setNameKeywords(List<String> name) {
        this.nameKeywords = name;
    }

    public void setPhoneKeywords(List<String> phone) {
        this.phoneKeywords = phone;
    }

    public void setEmailKeywords(List<String> email) {
        this.emailKeywords = email;
    }

    public void setAddressKeywords(List<String> address) {
        this.addressKeywords = address;
    }

    public void setStatusKeywords(List<String> status) {
        this.statusKeywords = status;
    }

    public void setTagKeywords(List<String> tags) {
        this.tagKeywords = tags;
    }

    public void setPositionKeywords(List<String> positionKeywords) {
        this.positionKeywords = positionKeywords;
    }

    /**
     * If any field is provided.
     * @return if any field is provided.
     */
    public boolean isAnyField() {
        return !Stream.of(nameKeywords, phoneKeywords, emailKeywords, addressKeywords, statusKeywords,
                tagKeywords, positionKeywords)
                .allMatch(List::isEmpty);
    }

}
