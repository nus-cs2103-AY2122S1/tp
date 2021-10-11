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
    private final HelpCommand expectedOutcome1 = new HelpCommand();
    private final String validInput2 = "add";
    private final HelpCommand expectedOutcome2 = new HelpCommand("add", AddCommand.HELP_MESSAGE);
    private final String validInput3 = "grade";
    private final HelpCommand expectedOutcome3 = new HelpCommand("grade", GradeCommand.HELP_MESSAGE);
    private final String invalidInput1 = "CS2103T";
    private final String expectedOutcome4 = String.format(Messages.MESSAGE_HELP_NOT_EXIST, invalidInput1);
    private final String invalidInput2 = "please send help I'm suffering with CS right now";
    private final String expectedOutcome5 = String.format(Messages.MESSAGE_HELP_NOT_EXIST, invalidInput2);

    @Test
    public void parse_validArgument_success() {
        assertParseSuccess(parser, validInput1, expectedOutcome1);
        assertParseSuccess(parser, validInput2, expectedOutcome2);
        assertParseSuccess(parser, validInput3, expectedOutcome3);
    }

    @Test
    public void parse_invalidArgument_failure() {
        assertParseFailure(parser, invalidInput1, expectedOutcome4);
        assertParseFailure(parser, invalidInput2, expectedOutcome5);
    }
}
