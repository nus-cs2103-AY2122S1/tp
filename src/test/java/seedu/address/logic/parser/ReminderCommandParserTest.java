package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ReminderCommand;

public class ReminderCommandParserTest {

    private ReminderCommandParser parser = new ReminderCommandParser();

    @Test
    public void parse_emptyArg_success() {
        assertParseSuccess(parser, "", new ReminderCommand());
    }

    @Test
    public void parse_validArgs_success() {
        assertParseSuccess(parser, "-s 3", new ReminderCommand(3));
    }

    @Test
    public void parse_invalidArgWithFlag_failure() {
        assertParseFailure(parser, "-s 99999999999",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReminderCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgWithoutFlag_failure() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReminderCommand.MESSAGE_USAGE));
    }
}
