package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SwapShiftCommand;
import seedu.address.model.person.Name;

public class SwapShiftCommandParserTest {
    private final SwapShiftCommandParser parser = new SwapShiftCommandParser();

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, "  ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SwapShiftCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_onlyOneName_throwsParseException() {
        String userInput = " -n Alex Yeoh d/monday-0 d/tuesday-1";
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SwapShiftCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_threeNames_throwsParseException() {
        String userInput = " -n Alex Yeoh -n David Li -n John Wick d/monday-0 d/tuesday-1";
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SwapShiftCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nonUniqueNames_throwsParseException() {
        String userInput = " -n Alex Yeoh -n Alex Yeoh d/monday-0 d/tuesday-1";
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SwapShiftCommand.NON_UNIQUE_NAMES));
    }

    @Test
    public void parse_onlyOneShift_throwsParseException() {
        String userInput = " -n Alex Yeoh -n David Li d/monday-0";
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SwapShiftCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_threeShifts_throwsParseException() {
        String userInput = " -n Alex Yeoh -n David Li d/monday-0 d/tuesday-1 d/wednesday-0";
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SwapShiftCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nonUniqueShifts_throwsParseException() {
        String userInput = " -n Alex Yeoh -n David Li d/monday-0 d/monday-0";
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SwapShiftCommand.NON_UNIQUE_SHIFTS));
    }

    @Test
    public void parse_validArgs_returnsSwapShiftCommand() {
        String firstUserInput = " -n Alex Yeoh -n David Li d/monday-0 d/tuesday-1";
        String secondUserInput = " -n Alex Yeoh d/monday-0 -n David Li d/tuesday-1";
        String thirdUserInput = " -n Alex Yeoh d/monday-0 d/tuesday-1 -n David Li";
        String fourthUserInput = " d/monday-0 d/tuesday-1 -n Alex Yeoh -n David Li";

        List<Name> nameList = Arrays.asList(new Name("Alex Yeoh"), new Name("David Li"));
        List<String> shiftList = Arrays.asList("monday-0", "tuesday-1");
        SwapShiftCommand expectedCommand = new SwapShiftCommand(nameList, shiftList,
                LocalDate.now(), LocalDate.now().plusDays(7));

        assertParseSuccess(parser, firstUserInput, expectedCommand);
        assertParseSuccess(parser, secondUserInput, expectedCommand);
        assertParseSuccess(parser, thirdUserInput, expectedCommand);
        assertParseSuccess(parser, fourthUserInput, expectedCommand);
    }
}
