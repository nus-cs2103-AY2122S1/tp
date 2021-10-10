package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CLASSID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.CLASSID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GRADE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GRADE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.STUDENTID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.STUDENTID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASSID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENTID_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalStudents.AMY;
import static seedu.address.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        //System.out.println(NAME_DESC_BOB);
        Student expectedStudent = new StudentBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + STUDENTID_DESC_BOB + CLASSID_DESC_BOB
                + GRADE_DESC_BOB, new AddCommand(expectedStudent));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + STUDENTID_DESC_BOB + CLASSID_DESC_BOB
                + GRADE_DESC_BOB , new AddCommand(expectedStudent));
    }

    //TODO
    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Student expectedStudent = new StudentBuilder(AMY).build();
        assertParseSuccess(parser, NAME_DESC_AMY + STUDENTID_DESC_AMY + CLASSID_DESC_AMY + GRADE_DESC_AMY,
                new AddCommand(expectedStudent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + STUDENTID_DESC_BOB + CLASSID_DESC_BOB + GRADE_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_STUDENTID_BOB + CLASSID_DESC_BOB + GRADE_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + STUDENTID_DESC_BOB + VALID_CLASSID_BOB + GRADE_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + STUDENTID_DESC_BOB + CLASSID_DESC_BOB + VALID_GRADE_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_STUDENTID_BOB + VALID_CLASSID_BOB + VALID_GRADE_BOB,
                expectedMessage);
    }
}
