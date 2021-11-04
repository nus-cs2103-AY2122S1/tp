package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_IN_UNION = "This person does not exist in UNIon";
    public static final String MESSAGE_INVALID_FOLDER_IN_UNION = "This folder does not exist in UNIon";
    private static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d person%2$s listed!";
    private static final String MESSAGE_FOLDERS_LISTED_OVERVIEW = "%1$d folder%2$s listed!";

    private static String getPluralModifier(int numberOfObject) {
        return numberOfObject != 1 ? "s" : "";
    }

    public static String getMessagePersonsListedOverview(int numberOfPersons) {
        return String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, numberOfPersons, getPluralModifier((numberOfPersons)));
    }
    public static String getMessageFoldersListedOverview(int numberOfFolders) {
        return String.format(MESSAGE_FOLDERS_LISTED_OVERVIEW, numberOfFolders, getPluralModifier((numberOfFolders)));
    }
}
