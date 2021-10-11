package seedu.anilist.logic.commands;

/**
 * List of all commands currently supported.
 */
public class CommandsList {
    private static final String[] LIST_OF_COMMANDS = {"add", "delete", "help", "list", "update"};

    /**
     * @return a copy of the LIST_OF_COMMANDS array
     */
    public static String[] getListOfCommands() {
        String[] copyArray = new String[LIST_OF_COMMANDS.length];

        for (int i = 0; i < LIST_OF_COMMANDS.length; i++) {
            copyArray[i] = LIST_OF_COMMANDS[i];
        }

        return copyArray;
    }

    /**
     * @return string of the list of all supported commands, each command separated by a comma
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
