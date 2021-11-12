package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
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
    HELP("help", "man", "h"),
    EXIT("exit", "quit", "q"),
    ADD_PERSON("add", "a"),
    CLEAR_PERSON("clear", "clr", "c"),
    DELETE_PERSON("delete", "del", "rm", "d"),
    EDIT_PERSON("edit", "update", "e"),
    FIND_PERSON("find", "f"),
    LIST_PERSON("list", "ls"),
    EDIT_MODULE_LESSON("editc", "ec", "updatec"),
    FIND_MODULE_LESSON("findc", "fc"),
    LIST_MODULE_LESSON("listc", "lsc"),
    CLEAR_MODULE_LESSON("clearc", "clrc", "cc"),
    DELETE_MODULE_LESSON("deletec", "delc", "rmc", "dc"),
    ADD_MODULE_LESSON("addc", "ac");

    private static final Map<CommandWord, ArrayList<String>> ALIAS_MAP;
    private static final Logger logger = LogsCenter.getLogger(CommandWord.class);

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
                logger.info("----------------[Alias given is supported][" + userInput + "]");
                return cw;
            }
        }
        logger.info("----------------[Not a supported alias][" + userInput + "]");
        throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
    }

    public static List<String> getAliasList(CommandWord cw) {
        return Collections.unmodifiableList(cw.aliasList);
    }
}
