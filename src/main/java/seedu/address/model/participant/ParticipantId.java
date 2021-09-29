package seedu.address.model.participant;

public class ParticipantId {

    private String id;

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
        String[] sections = p.getName().fullName.trim().split(" ");
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

    @Override
    public String toString() {
        return this.id;
    }
}

