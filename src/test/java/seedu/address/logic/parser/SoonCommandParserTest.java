package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPLETION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPLICATION;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SoonCommand;
import seedu.address.model.application.InterviewDateAndTimePredicate;
import seedu.address.model.application.SoonDeadlinePredicate;

public class SoonCommandParserTest {
    private SoonCommandParser parser = new SoonCommandParser();

    @Test
    public void parse_validArgs_returnsSoonCommand() {
        assertParseSuccess(parser, " d/1",
                new SoonCommand(Index.fromZeroBased(1),
                        new SoonDeadlinePredicate(Index.fromZeroBased(1))));

        assertParseSuccess(parser, " i/1",
                new SoonCommand(Index.fromZeroBased(1),
                        new InterviewDateAndTimePredicate(Index.fromZeroBased(1))));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "d",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        SoonCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "i",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        SoonCommand.MESSAGE_USAGE));
        assertParseFailure(parser, ";",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        SoonCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        SoonCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        SoonCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "9999999999",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        SoonCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "d/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        SoonCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "i/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        SoonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidDays_throwsParseException() {
        assertParseFailure(parser, " d/-1",
                String.format(SoonCommand.MESSAGE_INVALID_DAYS + "\n%1$s",
                        SoonCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " d/999999999999",
                String.format(SoonCommand.MESSAGE_INVALID_DAYS + "\n%1$s",
                        SoonCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " i/-1",
                String.format(SoonCommand.MESSAGE_INVALID_DAYS + "\n%1$s",
                        SoonCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " i/999999999999",
                String.format(SoonCommand.MESSAGE_INVALID_DAYS + "\n%1$s",
                        SoonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multipleParametersPresent_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SoonCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "" + INDEX_FIRST_APPLICATION + " " + INDEX_SECOND_APPLICATION, expectedMessage);
        assertParseFailure(parser, "d/1" + " i/1", expectedMessage);
    }

    @Test
    public void parse_noParameterPresent_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SoonCommand.MESSAGE_USAGE);

        // command word only
        assertParseFailure(parser, "", expectedMessage);

        // white space only
        assertParseFailure(parser, "      ", expectedMessage);

        // no parameter
        assertParseFailure(parser, PREAMBLE_NON_EMPTY, expectedMessage);

        // unsupported parameter
        assertParseFailure(parser, "" + PREFIX_COMPLETION, expectedMessage);
    }


}
