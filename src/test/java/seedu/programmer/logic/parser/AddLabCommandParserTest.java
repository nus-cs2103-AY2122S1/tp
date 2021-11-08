package seedu.programmer.logic.parser;

import static seedu.programmer.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.programmer.commons.core.Messages.MESSAGE_MISSING_ARGUMENT;
import static seedu.programmer.logic.commands.CommandTestUtil.LAB_NUM;
import static seedu.programmer.logic.commands.CommandTestUtil.LAB_TOTAL;
import static seedu.programmer.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_LAB_NO;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_TOTAL_SCORE;
import static seedu.programmer.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.programmer.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.programmer.logic.commands.AddLabCommand;
import seedu.programmer.model.student.Lab;
import seedu.programmer.testutil.LabBuilder;

public class AddLabCommandParserTest {
    private AddLabCommandParser parser = new AddLabCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        //System.out.println(NAME_DESC_BOB);
        Lab expectedLab = new LabBuilder().withLabNum(VALID_LAB_NO).withTotal(VALID_TOTAL_SCORE).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + LAB_NUM + LAB_TOTAL, new AddLabCommand(expectedLab));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedInvalidMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLabCommand.MESSAGE_USAGE);
        String expectedMissingArgMessage = String.format(MESSAGE_MISSING_ARGUMENT, AddLabCommand.MESSAGE_USAGE);

        // missing labNum prefix
        assertParseFailure(parser, VALID_LAB_NO + LAB_TOTAL, expectedInvalidMessage);

        // missing totalScore prefix
        assertParseFailure(parser, LAB_NUM + VALID_TOTAL_SCORE, expectedMissingArgMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_LAB_NO + VALID_TOTAL_SCORE.toString(), expectedInvalidMessage);
    }
}
