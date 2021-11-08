package seedu.address.model.position;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.commons.util.StringUtil;


/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class FindPositionCommandPredicate implements Predicate<Position> {

    private List<String> titleKeywords = new ArrayList<String>();
    private List<String> positionStatusKeywords = new ArrayList<String>();

    public FindPositionCommandPredicate() {
    }


    /**
     * Alternative Constructor with only title field.
     * @param titleKeyword
     */
    public FindPositionCommandPredicate(List<String> titleKeyword) {
        this.titleKeywords = titleKeyword;
    }

    /**
     * Alternative Constructor with keyword field.
     * @param titleKeywords
     * @param positionStatusKeywords
     */
    public FindPositionCommandPredicate(List<String> titleKeywords, List<String> positionStatusKeywords) {
        this.titleKeywords = titleKeywords;
        this.positionStatusKeywords = positionStatusKeywords;
    }

    @Override
    public boolean test(Position position) {
        boolean titleCheck = titleKeywords.isEmpty() || titleKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(position.getTitle().fullTitle, keyword));

        boolean positionCheck = positionStatusKeywords.isEmpty() || positionStatusKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(position.getStatus().name(), keyword));

        return titleCheck && positionCheck;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindPositionCommandPredicate // instanceof handles nulls
                && titleKeywords.equals(((FindPositionCommandPredicate) other).titleKeywords) // state check
                && positionStatusKeywords.equals(((FindPositionCommandPredicate) other).positionStatusKeywords));
    }

    public void setTitleKeywords(List<String> titleKeywords) {
        this.titleKeywords = titleKeywords;
    }

    public void setPositionStatusKeywords(List<String> positionStatusKeywords) {
        this.positionStatusKeywords = positionStatusKeywords;
    }


    /**
     * If any field is provided.
     * @return if any field is provided.
     */
    public boolean isAnyField() {
        return !Stream.of(titleKeywords, positionStatusKeywords)
                .allMatch(List::isEmpty);
    }

}
