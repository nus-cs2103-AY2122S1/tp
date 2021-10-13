package seedu.address.model.participant;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Participant}'s {@code Name} matches any of the keywords given.
 */
public class ParticipantNameContainsKeywordsPredicate implements Predicate<Participant> {
    private final List<String> keywords;

    public ParticipantNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Participant participant) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(participant.getFullName(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ParticipantNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ParticipantNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
