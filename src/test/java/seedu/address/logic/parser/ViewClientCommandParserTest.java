package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewClientCommand;
import seedu.address.model.client.ClientContainsIdPredicate;

public class ViewClientCommandParserTest {
    private ViewClientCommandParser parser = new ViewClientCommandParser();

    @Test
    public void parse_emptyArgs_throwParseException() {
        assertParseFailure(parser, "          ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewClientCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_tooManyArgs_throwParseException() {
        assertParseFailure(parser, "too many arguments",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewClientCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_oneValidArgument_returnViewClientCommand() {
        ViewClientCommand expectedCommand = new ViewClientCommand(new ClientContainsIdPredicate(List.of("1")));
        assertParseSuccess(parser, "1", expectedCommand);
    }

    @Test
    public void parse_oneInvalidArgument_throwParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewClientCommand.MESSAGE_USAGE));
    }
}
