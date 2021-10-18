package seedu.address.model.participant;

import java.util.function.Predicate;

/**
 * Tests that a {@code Participant}'s {@code ParticipantId} contains the given ID
 */
public class ParticipantIdMatchesGivenIdPredicate implements Predicate<Participant> {
    private final String givenId;

    public ParticipantIdMatchesGivenIdPredicate(String givenId) {
        this.givenId = givenId;
    }

    @Override
    public boolean test(Participant participant) {
        return participant.getParticipantIdValue().toLowerCase()
                .contains(givenId.toLowerCase());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ParticipantIdMatchesGivenIdPredicate // instanceof handles nulls
                && givenId.equals(((ParticipantIdMatchesGivenIdPredicate) other).givenId)); // state check
    }

}
