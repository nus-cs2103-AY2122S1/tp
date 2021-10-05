package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.HashMap;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Encapsulates the Command Word for Parser to
 * know which command the user wants to execute.
 *
 * Adapted from stackoverflow
 * @see <a href=https://stackoverflow.com/questions/41494056/add-alias-to-an-enum-in-java>
 *     Add alias to an enum in java</a>
 */
public enum CommandWord {
    ADD("add"),
    CLEAR("clear"),
    DELETE("delete"),
    EDIT("edit", "update"),
    EXIT("exit", "quit"),
    FIND("find"),
    HELP("help", "man"),
    LIST("list", "ls");

    private final HashMap<String, CommandWord> aliasMap = new HashMap<>();

    /**
     * @param aliases The array of alias that will match to this CommandWord
     */
    CommandWord(String... aliases) {
        for (String alias : aliases) {
            aliasMap.put(alias, this);
        }
    }

    /**
     * Parse the user input, if valid, into a Command Word used
     * internally in the program for the creation of Command objects.
     *
     * @param userInput The user input to parse into a command word
     * @return The matching command word if exists, else null.
     */
    public static CommandWord getCommandWord(String userInput) throws ParseException {
        String aliasToLowerCase = userInput.toLowerCase();
        for (CommandWord cw : CommandWord.values()) {
            CommandWord cwToReturn = cw.aliasMap.get(aliasToLowerCase);
            if (cwToReturn != null) {
                return cw.aliasMap.get(aliasToLowerCase);
            }
        }
        throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
    }
}
