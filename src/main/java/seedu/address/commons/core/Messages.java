package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_EVENTS_LISTED_OVERVIEW = "%1$d events listed!";
    public static final String MESSAGE_EVENT_NOT_FOUND = "The Event you are looking for does not exist!";
    public static final String MESSAGE_INVALID_PARTICIPANT_DISPLAYED_INDEX =
            "The participant index provided is invalid";
    public static final String MESSAGE_INVALID_EVENT_DISPLAYED_INDEX =
            "The event index provided is invalid";
    public static final String MESSAGE_PARTICIPANTS_LISTED_OVERVIEW = "%1$d participants listed!";
    public static final String MESSAGE_PARTICIPANT_NOT_FOUND = "Participant of id: %1$s not found, consider "
            + "relisting the participants using '%2$s'.";
    public static final String MESSAGE_EVENT_NOT_FOUND_IN_FILTERED_LIST =
            "Event %1$s Not Found, consider relisting events using '%2$s'.";

    /**
     * Forms the message that states that a Participant already exists in an Event in Managera.
     *
     * @param participantName The name of the Participant.
     * @return a message that states that the Participant already exists in an Event.
     */
    public static String showParticipantExists(String participantName) {
        return "Participant " + participantName + " already exists!";
    }

}
