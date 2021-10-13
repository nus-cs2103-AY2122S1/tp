package seedu.academydirectory.logic.parser;

import static seedu.academydirectory.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.academydirectory.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.academydirectory.commons.core.Messages;
import seedu.academydirectory.logic.commands.AddCommand;
import seedu.academydirectory.logic.commands.GradeCommand;
import seedu.academydirectory.logic.commands.HelpCommand;

public class HelpCommandParserTest {
    private HelpCommandParser parser = new HelpCommandParser();

    private final String validInput1 = "";
    private final HelpCommand expectedValidOutcome1 = new HelpCommand();
    private final String validInput2 = "add";
    private final HelpCommand expectedValidOutcome2 = new HelpCommand("add", AddCommand.HELP_MESSAGE);
    private final String validInput3 = "grade";
    private final HelpCommand expectedValidOutcome3 = new HelpCommand("grade", GradeCommand.HELP_MESSAGE);
    private final String invalidInput1 = "CS2103T";
    private final String expectedInvalidOutcome1 = String.format(Messages.MESSAGE_HELP_NOT_EXIST, invalidInput1);
    private final String invalidInput2 = "please send help I'm suffering with CS right now";
    private final String expectedInvalidOutcome2 = String.format(Messages.MESSAGE_HELP_NOT_EXIST, invalidInput2);

    @Test
    public void parse_validArgument_success() {
        assertParseSuccess(parser, validInput1, expectedValidOutcome1);
        assertParseSuccess(parser, validInput2, expectedValidOutcome2);
        assertParseSuccess(parser, validInput3, expectedValidOutcome3);
    }

    @Test
    public void parse_invalidArgument_failure() {
        assertParseFailure(parser, invalidInput1, expectedInvalidOutcome1);
        assertParseFailure(parser, invalidInput2, expectedInvalidOutcome2);
    }
}
