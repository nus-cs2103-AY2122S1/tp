package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewProductCommand;
import seedu.address.model.product.ProductContainsIdPredicate;

public class ViewProductCommandParserTest {
    private ViewProductCommandParser parser = new ViewProductCommandParser();

    @Test
    public void parse_emptyArgs_throwParseException() {
        assertParseFailure(parser, "          ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewProductCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_tooManyArgs_throwParseException() {
        assertParseFailure(parser, "too many arguments",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewProductCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_oneValidArgument_returnViewProductCommand() {
        ViewProductCommand expectedCommand = new ViewProductCommand(new ProductContainsIdPredicate(List.of("1")));
        assertParseSuccess(parser, "1", expectedCommand);
    }

    @Test
    public void parse_oneInvalidArgument_throwParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewProductCommand.MESSAGE_USAGE));
    }
}
