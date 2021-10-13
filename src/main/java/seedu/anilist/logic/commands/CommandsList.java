package seedu.anilist.logic.commands;

/**
 * List of all commands currently supported.
 */
public class CommandsList {
    private static final String[] LIST_OF_COMMANDS = {"add", "delete", "help", "list", "update"};

    /**
     * Creates a copy of the LIST_OF_COMMANDS array and return it.
     *
     * @return the copied array
     */
    public static String[] getListOfCommands() {
        String[] copyArray = new String[LIST_OF_COMMANDS.length];

        for (int i = 0; i < LIST_OF_COMMANDS.length; i++) {
            copyArray[i] = LIST_OF_COMMANDS[i];
        }

        return copyArray;
    }

    /**
     * Converts the list of commands into a String, with each command separated by a comma and space.
     *
     * @return string of the list of all supported commands
     */
    public static String getListOfCommandsAsString() {
        StringBuilder listOfCommandsAsString = new StringBuilder();
        String separator = "";
        for (int i = 0; i < LIST_OF_COMMANDS.length; i++) {
            listOfCommandsAsString.append(separator);
            listOfCommandsAsString.append(LIST_OF_COMMANDS[i]);
            separator = ", ";
        }

        return listOfCommandsAsString.toString();
    }
}
