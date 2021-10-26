package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ALIAS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AliasCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ExitCommand;

public class AliasCommandParserTest {
    private final AddressBookParser abParser = new AddressBookParser();
    private final AliasCommandParser parser = new AliasCommandParser(abParser);
    private final String validAliasWord = "aliasWord";
    private final String validCommandWord = ClearCommand.COMMAND_WORD;
    private final String validCommandWord2 = ExitCommand.COMMAND_WORD;
    private final String invalidAliasWord = validCommandWord + " hello";
    private final String invalidCommandWord = "invalidCommandWord";

    @Test
    public void parse_missingPrefix_failure() {
        assertParseFailure(parser, validAliasWord + " " + validCommandWord, AliasCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_missingAliasWord_failure() {
        assertParseFailure(parser, PREFIX_ALIAS + validCommandWord, AliasCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_missingCommandWord_failure() {
        assertParseFailure(parser, validAliasWord + " " + PREFIX_ALIAS, AliasCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_unknownCommandWord_failure() {
        assertParseFailure(parser, validAliasWord + " " + PREFIX_ALIAS + invalidCommandWord,
                AliasCommand.MESSAGE_UNKNOWN_OLD_COMMAND);

        // extra word on command word
        assertParseFailure(parser, validAliasWord + " " + PREFIX_ALIAS + validCommandWord + " hello",
                AliasCommand.MESSAGE_UNKNOWN_OLD_COMMAND);

        // extra characters on command word
        assertParseFailure(parser, validAliasWord + " " + PREFIX_ALIAS + invalidCommandWord + "hello",
                AliasCommand.MESSAGE_UNKNOWN_OLD_COMMAND);
    }

    @Test
    public void parse_multipleWordAliasWord_failure() {
        assertParseFailure(parser, invalidAliasWord + " " + PREFIX_ALIAS + validCommandWord,
                Alias.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_overwriteDefault_failure() {
        assertParseFailure(parser, validCommandWord + " " + PREFIX_ALIAS + validCommandWord2,
                AliasCommand.MESSAGE_OVERWRITE_DEFAULT);
    }

    @Test
    public void parse_validInputs_success() {
        assertParseSuccess(parser, validAliasWord + " " + PREFIX_ALIAS + validCommandWord,
                new AliasCommand(new Alias(validAliasWord, validCommandWord), abParser));
    }
}
