package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CLASSCODE_DESC_G01;
import static seedu.address.logic.commands.CommandTestUtil.CLASSCODE_DESC_G02;
import static seedu.address.logic.commands.CommandTestUtil.GROUPNUMBER_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.GROUPNUMBER_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.GROUPTYPE_DESC_OP1;
import static seedu.address.logic.commands.CommandTestUtil.GROUPTYPE_DESC_OP2;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CLASSCODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GROUPNUMBER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GROUPTYPE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASSCODE_G01;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUPNUMBER_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUPTYPE_OP1;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTutorialGroups.TUT_01;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddGroupCommand;
import seedu.address.model.student.ClassCode;
import seedu.address.model.tutorialgroup.GroupNumber;
import seedu.address.model.tutorialgroup.GroupType;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.testutil.TutorialGroupBuilder;

public class AddGroupCommandParserTest {

    private AddGroupCommandParser parser = new AddGroupCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        TutorialGroup expectedTutorialGroup = new TutorialGroupBuilder(TUT_01).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + GROUPNUMBER_DESC_1 + CLASSCODE_DESC_G01
                + GROUPTYPE_DESC_OP1, new AddGroupCommand(expectedTutorialGroup));

        // multiple classCodes - last classCode accepted
        assertParseSuccess(parser, GROUPNUMBER_DESC_1 + CLASSCODE_DESC_G02 + CLASSCODE_DESC_G01
                + GROUPTYPE_DESC_OP1, new AddGroupCommand(expectedTutorialGroup));

        // multiple group numbers - last group number accepted
        assertParseSuccess(parser, GROUPNUMBER_DESC_2 + GROUPNUMBER_DESC_1 + CLASSCODE_DESC_G01
                + GROUPTYPE_DESC_OP1, new AddGroupCommand(expectedTutorialGroup));

        // multiple group types - last group type accepted
        assertParseSuccess(parser, GROUPNUMBER_DESC_1 + CLASSCODE_DESC_G01 + GROUPTYPE_DESC_OP2
                + GROUPTYPE_DESC_OP1, new AddGroupCommand(expectedTutorialGroup));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddGroupCommand.MESSAGE_USAGE);

        // missing ClassCode Prefix
        assertParseFailure(parser, VALID_CLASSCODE_G01 + GROUPNUMBER_DESC_1 + GROUPTYPE_DESC_OP1, expectedMessage);

        // missing group number prefix
        assertParseFailure(parser, CLASSCODE_DESC_G01 + VALID_GROUPNUMBER_1 + GROUPNUMBER_DESC_1, expectedMessage);

        // missing group type prefix
        assertParseFailure(parser, CLASSCODE_DESC_G01 + GROUPNUMBER_DESC_1 + VALID_GROUPTYPE_OP1, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_CLASSCODE_G01 + VALID_GROUPNUMBER_1 + VALID_GROUPTYPE_OP1, expectedMessage);

    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid class code
        assertParseFailure(parser, GROUPNUMBER_DESC_1 + INVALID_CLASSCODE_DESC + GROUPTYPE_DESC_OP1,
                ClassCode.MESSAGE_CONSTRAINTS);

        // invalid group number
        assertParseFailure(parser, INVALID_GROUPNUMBER_DESC + CLASSCODE_DESC_G01 + GROUPTYPE_DESC_OP1,
                GroupNumber.MESSAGE_CONSTRAINTS);

        // invalid group type
        assertParseFailure(parser, GROUPNUMBER_DESC_1 + CLASSCODE_DESC_G01 + INVALID_GROUPTYPE_DESC,
                GroupType.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_GROUPNUMBER_DESC + CLASSCODE_DESC_G01 + INVALID_GROUPTYPE_DESC,
                GroupNumber.MESSAGE_CONSTRAINTS);

        // preamble not empty
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + CLASSCODE_DESC_G01 + GROUPNUMBER_DESC_1 + GROUPTYPE_DESC_OP1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddGroupCommand.MESSAGE_USAGE));
    }
}
