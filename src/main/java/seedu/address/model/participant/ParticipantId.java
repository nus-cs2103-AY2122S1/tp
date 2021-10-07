package seedu.address.model.participant;

import java.util.Objects;

public class ParticipantId {

    public static final String MESSAGE_CONSTRAINTS = "Participant ID should be of length 1 - 6";

    private String id;

    public ParticipantId(String id) {
        this.id = id;
    }

    private ParticipantId(Participant p) {
        this.id = encode(p);
    }

    /**
     * Factory method for id.
     *
     * @param p participant to encode the id.
     * @return the ParticipantId for given participant.
     */
    public static ParticipantId of(Participant p) {
        return new ParticipantId(p);
    }

    /**
     * Encode the given participant to give id.
     *
     * @param p participant to encode the id.
     * @return the id for given participant.
     */
    public static String encode(Participant p) {
        // may change in the future
        String[] sections = p.getFullName().trim().split(" ");
        if (sections.length == 1) {
            String name = sections[0];
            return name.length() < 6 ? name.toLowerCase() : name.substring(0, 6).toLowerCase();
        } else {
            String first = sections[0];
            String last = sections[sections.length - 1];
            String firstPart = first.length() < 3 ? first : first.substring(0, 3);
            String lastPart = last.length() < 3 ? last : last.substring(0, 3);
            return firstPart.toLowerCase() + lastPart.toLowerCase();
        }
    }

    public static boolean isValidId(String id) {
        return id.length() > 0 && id.length() <= 6;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ParticipantId)) {
            return false;
        }
        ParticipantId otherParticipantId = (ParticipantId) other;
        return otherParticipantId.id.equals(id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return this.id;
    }
}

