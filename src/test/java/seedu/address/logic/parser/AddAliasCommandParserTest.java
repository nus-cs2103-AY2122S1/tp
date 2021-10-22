package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMAND_WORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SHORTCUT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddAliasCommand;
import seedu.address.model.alias.Alias;
import seedu.address.model.alias.CommandWord;
import seedu.address.model.alias.Shortcut;

public class AddAliasCommandParserTest {
    private AddAliasCommandParser parser = new AddAliasCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        String shortcut = "lf";
        String command = "listf";
        assertParseSuccess(parser, PREAMBLE_WHITESPACE
                + " " + PREFIX_SHORTCUT + shortcut
                + " " + PREFIX_COMMAND_WORD + command,
                new AddAliasCommand(new Alias(new Shortcut(shortcut), new CommandWord(command))));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAliasCommand.MESSAGE_USAGE);

        // missing alias prefix
        assertParseFailure(parser, "lf cw/listf", expectedMessage);

        // missing command word prefix
        assertParseFailure(parser, "a/lf listf", expectedMessage);
    }


}
