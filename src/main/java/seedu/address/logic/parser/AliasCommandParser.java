package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALIAS;

import seedu.address.logic.commands.AliasCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AliasCommandParser implements Parser<AliasCommand> {
    private final AddressBookParser parser;

    public AliasCommandParser(AddressBookParser parser) {
        this.parser = parser;
    }

    @Override
    public AliasCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ALIAS);

        if (argMultimap.getValue(PREFIX_ALIAS).isEmpty()
            || argMultimap.getPreamble().equals("")
            || argMultimap.getValue(PREFIX_ALIAS).get().equals("")) {
            throw new ParseException(AliasCommand.MESSAGE_USAGE);
        }

        String aliasWord = argMultimap.getPreamble().strip();
        String commandWord = argMultimap.getValue(PREFIX_ALIAS).get().strip();

        if (!isValidCommandWord(commandWord)) {
            throw new ParseException(AliasCommand.MESSAGE_UNKNOWN_OLD_COMMAND);
        }

        if (!Alias.isValidAlias(aliasWord)) {
            throw new ParseException(Alias.MESSAGE_CONSTRAINTS);
        }

        if (isValidCommandWord(aliasWord) && !parser.containsAlias(aliasWord)) {
            throw new ParseException(AliasCommand.MESSAGE_OVERWRITE_DEFAULT);
        }

        Alias newAlias = new Alias(aliasWord, commandWord);
        return new AliasCommand(newAlias, parser);
    }

    /**
     * Checks if the provided commandWord is a valid commandWord.
     */
    private boolean isValidCommandWord(String commandWord) {
        if (commandWord.equals("")) {
            return false;
        }

        // canParse would be true if the commandWord gets recognised as a proper command
        boolean canParse = true;
        try {
            parser.parseCommand(commandWord);
        } catch (ParseException e) {
            if (e.getMessage().equals(MESSAGE_UNKNOWN_COMMAND)) {
                canParse = false;
            }
        }

        int lastIndex = commandWord.lastIndexOf(" ");
        if (lastIndex == -1) {
            return canParse;
        } else {
            // to ensure that the command does not overlap with another smaller command
            // e.g. if command word is clear hello, this is not a valid command word, but would still parse properly
            String smallerCommand = commandWord.substring(0, lastIndex);
            return canParse && !isValidCommandWord(smallerCommand);
        }
    }
}
