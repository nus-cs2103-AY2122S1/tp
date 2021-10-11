package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Encapsulates the Command Word for Parser to
 * know which command the user wants to execute.
 *
 * Adapted from stackoverflow
 * @see <a href=https://stackoverflow.com/questions/41494056/add-alias-to-an-enum-in-java>
 *     Add alias to an enum in java</a>
 * @see <a href=https://stackoverflow.com/questions/443980/why-cant-enums-constructor-access-static-fields>
 *     Why can't enum's constructor access static fields</a>
 */
public enum CommandWord {
    ADD("add"),
    CLEAR("clear", "clr"),
    DELETE("delete", "del", "rm"),
    EDIT("edit", "update"),
    EXIT("exit", "quit"),
    FIND("find"),
    HELP("help", "man"),
    LIST("list", "ls"),
    REMARK("remark");

    private static final Map<CommandWord, ArrayList<String>> ALIAS_MAP;

    static {
        Map<CommandWord, ArrayList<String>> aliasMap = new HashMap<>();
        for (CommandWord cw : values()) {
            aliasMap.put(cw, cw.aliasList);
        }
        ALIAS_MAP = Collections.unmodifiableMap(aliasMap);
    }

    private ArrayList<String> aliasList;

    /**
     * @param aliases The array of alias that will match to this CommandWord
     */
    CommandWord(String... aliases) {
        aliasList = new ArrayList<>(Arrays.asList(aliases));
    }

    /**
     * Parse the user input, if valid, into a Command Word used
     * internally in the program for the creation of Command objects.
     *
     * @param userInput The user input to parse into a command word
     * @return The matching command word if exists, else null.
     */
    public static CommandWord getCommandType(String userInput) throws ParseException {
        String aliasToLowerCase = userInput.toLowerCase();
        for (CommandWord cw : ALIAS_MAP.keySet()) {
            if (cw.aliasList.contains(aliasToLowerCase)) {
                return cw;
            }
        }
        throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
    }

    public static List<String> getAliasList(CommandWord cw) {
        return Collections.unmodifiableList(cw.aliasList);
    }
}
