package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALIAS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMAND;

import java.util.Optional;

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
                ArgumentTokenizer.tokenize(args, PREFIX_ALIAS, PREFIX_COMMAND);

        if (argMultimap.getValue(PREFIX_ALIAS).isEmpty()
            || argMultimap.getValue(PREFIX_COMMAND).isEmpty()) {
            throw new ParseException(AliasCommand.MESSAGE_USAGE);
        }

        String aliasWord = argMultimap.getValue(PREFIX_ALIAS).get().strip();
        String commandWord = argMultimap.getValue(PREFIX_COMMAND).get().strip();

        if (!Alias.isValidAlias(aliasWord)) {
            throw new ParseException(Alias.MESSAGE_CONSTRAINTS);
        }

        // prevents trying to alias a default command
        if (isValidCommandWord(aliasWord) && parser.getAlias(aliasWord).isEmpty()) {
            throw new ParseException(AliasCommand.MESSAGE_OVERWRITE_DEFAULT);
        }

        // short circuit if removing existing alias
        if (aliasWord.equals(commandWord)) {
            Alias newAlias = new Alias(aliasWord, commandWord);
            return new AliasCommand(newAlias, parser);
        }

        Optional<Alias> existingAlias = parser.getAlias(commandWord);
        // prevents chaining of aliases
        // e.g. if there exists an existing alias "bye" for "exit", and "bye" is specified as the commandWord
        commandWord = existingAlias.map(Alias::getCommandWord).orElse(commandWord);

        if (!isValidCommandWord(commandWord)) {
            throw new ParseException(AliasCommand.MESSAGE_UNKNOWN_OLD_COMMAND);
        }

        Alias newAlias = new Alias(aliasWord, commandWord);
        return new AliasCommand(newAlias, parser);
    }

    /**
     * Checks if the provided commandWord is a valid commandWord.
     */
    private boolean isValidCommandWord(String commandWord) {
        if (commandWord.equals("") || commandWord.contains(" ")) {
            return false;
        }

        try {
            parser.parseCommand(commandWord);
        } catch (ParseException e) {
            if (e.getMessage().equals(MESSAGE_UNKNOWN_COMMAND)) {
                return false;
            }
        }

        return true;
    }
}
