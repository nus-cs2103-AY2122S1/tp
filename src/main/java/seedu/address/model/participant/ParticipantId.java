package seedu.address.model.participant;

import java.util.HashMap;
import java.util.Objects;

public class ParticipantId {

    public static final String MESSAGE_CONSTRAINTS = "Participant ID should be of length 1 - 6";

    private static HashMap<String, Integer> idMap = new HashMap<>();

    private String id;

    /**
     * Creates a ParticipantId with the provided String.
     * Called when converting existing Participants in memory (JSONAdaptedParticipant) to Participant.
     * Sets up the idMap with latest ID values for each ID name.
     *
     * @param id of the Participant.
     */
    public ParticipantId(String id) {
        this.id = id;

        String idName = id.replaceAll("[0-9]", "");
        int idNumber = Integer.parseInt(id.replaceAll("[^\\d.]", ""));
        if (idMap.containsKey(idName)) {
            int currentIdValue = idMap.get(idName);
            if (idNumber > currentIdValue) {
                idMap.replace(idName, idNumber);
            }
        } else {
            idMap.put(idName, idNumber);
        }
    }

    private ParticipantId(Participant p) {
        this.id = encode(p);
    }

    /**
     * Factory method for id.
     * Called upon creation of new Participant.
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
        StringBuilder idString = new StringBuilder();
        idString.append(generateIdString(p.getFullName()));

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

    /**
     * Generates the name part of the Participant ID.
     *
     * @param fullName of the Participant.
     * @return the name part of the Participant ID.
     */
    public static String generateIdString(String fullName) {
        String[] sections = fullName.trim().split(" ");
        if (sections.length == 1) {
            String name = sections[0];
            return name.length() < 6 ? name.toLowerCase() : name.substring(0, 6).toLowerCase();
        } else {
            String first = sections[0];
            String last = sections[sections.length - 1];
            String firstPart = first.length() < 3 ? first : first.substring(0, 3);
            String lastPart = last.length() < 3 ? last : last.substring(0, 3);
            return (firstPart + lastPart).toLowerCase();
        }
    }

    /**
     * Checks whether the provided ID is valid.
     *
     * @param id to be checked for validity.
     * @return true if the ID is valid.
     */
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

