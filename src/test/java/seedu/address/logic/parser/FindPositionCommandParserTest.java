package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.position.FindPositionCommand;
import seedu.address.model.position.FindPositionCommandPredicate;
import seedu.address.model.position.Position;

public class FindPositionCommandParserTest {

    private FindPositionCommandParser parser = new FindPositionCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindPositionCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindPositionCommand expectedFindPositionCommand =
                new FindPositionCommand(new FindPositionCommandPredicate(Arrays.asList("Assistant", "HR")));
        assertParseSuccess(parser, " " + PREFIX_TITLE + "Assistant HR",
                expectedFindPositionCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_TITLE + " \n Assistant \n \t HR  \t",
                expectedFindPositionCommand);

        //find by status closed
        expectedFindPositionCommand =
                new FindPositionCommand(new FindPositionCommandPredicate(new ArrayList<>(),
                        Arrays.asList(Position.PositionStatus.CLOSED.toString())));
        assertParseSuccess(parser, " " + PREFIX_POSITION_STATUS + Position.PositionStatus.CLOSED,
                expectedFindPositionCommand);

        //find by status open
        expectedFindPositionCommand =
                new FindPositionCommand(new FindPositionCommandPredicate(new ArrayList<>(),
                        Arrays.asList(Position.PositionStatus.OPEN.toString())));
        assertParseSuccess(parser, " " + PREFIX_POSITION_STATUS + Position.PositionStatus.OPEN,
                expectedFindPositionCommand);
    }

}
