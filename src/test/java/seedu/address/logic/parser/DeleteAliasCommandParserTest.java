package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteAliasCommand;
import seedu.address.model.alias.Shortcut;

public class DeleteAliasCommandParserTest {

    private DeleteAliasCommandParser parser = new DeleteAliasCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteAliasCommand() {
        // No leading and trailing whitespaces
        assertParseSuccess(parser, "lf", new DeleteAliasCommand(new Shortcut("lf")));
        // Multiple whitespaces
        assertParseSuccess(parser, "    \n \t  lf  \t", new DeleteAliasCommand(new Shortcut("lf")));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "      ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAliasCommand.MESSAGE_USAGE));
    }
}
