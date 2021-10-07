package seedu.address.model.participant;

import java.util.HashMap;
import java.util.Objects;

public class ParticipantId {

    public static final String MESSAGE_CONSTRAINTS = "Participant ID should be of length 1 - 6";

    private static HashMap<String, Integer> idMap = new HashMap<>();

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
        StringBuilder idString = new StringBuilder();
        String[] sections = p.getFullName().trim().split(" ");
        if (sections.length == 1) {
            String name = sections[0];
            idString.append(name.length() < 6 ? name.toLowerCase() : name.substring(0, 6).toLowerCase());
        } else {
            String first = sections[0];
            String last = sections[sections.length - 1];
            String firstPart = first.length() < 3 ? first : first.substring(0, 3);
            String lastPart = last.length() < 3 ? last : last.substring(0, 3);
            idString.append((firstPart + lastPart).toLowerCase());
        }

        if (ParticipantId.idMap.containsKey(idString.toString())) {
            int count = ParticipantId.idMap.get(idString.toString());
            ParticipantId.idMap.replace(idString.toString(), count + 1);
            idString.append(count + 1);
        } else {
            ParticipantId.idMap.put(idString.toString(), 1);
            idString.append(1);
        }

        return idString.toString();
    }

    public static boolean isValidId(String id) {
        String idName = id.replaceAll("[0-9]", "");
        String idNumber = id.replaceAll("[^\\d.]", "");
        return idName.length() > 0 && idName.length() <= 6 && idNumber.length() > 0 && Integer.parseInt(idNumber) > 0;
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

