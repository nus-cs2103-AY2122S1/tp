package seedu.intern.logic.parser;

import static seedu.intern.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intern.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.intern.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.intern.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.intern.logic.commands.CommandTestUtil.INVALID_SKILL_DESC;
import static seedu.intern.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.SKILL_DESC_JAVA;
import static seedu.intern.logic.commands.CommandTestUtil.SKILL_DESC_PYTHON;
import static seedu.intern.logic.commands.CommandTestUtil.STATUS_DESC_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.STATUS_DESC_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_SKILL_JAVA;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_SKILL_PYTHON;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_STATUS_AMY;
import static seedu.intern.logic.parser.CliSyntax.FLAG_ALL;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.intern.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.intern.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.intern.testutil.TypicalIndexes.INDEX_FIRST_APPLICANT;
import static seedu.intern.testutil.TypicalIndexes.INDEX_SECOND_APPLICANT;
import static seedu.intern.testutil.TypicalIndexes.INDEX_THIRD_APPLICANT;
import static seedu.intern.testutil.TypicalSelections.SELECTION_ALL;
import static seedu.intern.testutil.TypicalSelections.SELECTION_FIRST_APPLICANT;

import org.junit.jupiter.api.Test;

import seedu.intern.commons.core.selection.Index;
import seedu.intern.commons.core.selection.Selection;
import seedu.intern.logic.commands.EditCommand;
import seedu.intern.logic.commands.EditCommand.EditApplicantDescriptor;
import seedu.intern.model.applicant.Email;
import seedu.intern.model.applicant.Name;
import seedu.intern.model.applicant.Phone;
import seedu.intern.model.skills.Skill;
import seedu.intern.testutil.EditApplicantDescriptorBuilder;

public class EditCommandParserTest {

    private static final String SKILL_EMPTY = " " + PREFIX_SKILL;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_allSelectMultipleRepeatedFields_acceptsLast() {
        String userInput = FLAG_ALL + STATUS_DESC_BOB + STATUS_DESC_AMY;

        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder()
                .withApplicationStatus(VALID_STATUS_AMY)
                .build();
        EditCommand expectedCommand = new EditCommand(Selection.fromAllFlag(), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_validArgsInt_returnsEditCommand() {
        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder()
                .withApplicationStatus(VALID_STATUS_AMY)
                .build();
        assertParseSuccess(parser,
                "1 " + STATUS_DESC_AMY,
                new EditCommand(SELECTION_FIRST_APPLICANT, descriptor));
    }

    @Test
    public void parse_validArgsAll_returnsEditCommand() {
        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder()
                .withApplicationStatus(VALID_STATUS_AMY)
                .build();
        assertParseSuccess(parser,
                FLAG_ALL + STATUS_DESC_AMY,
                new EditCommand(SELECTION_ALL, descriptor));
    }

    @Test
    public void parse_invalidArgsAll_returnsEditCommand() {
        // lowercase should not be accepted
        assertParseFailure(parser,
                "all" + STATUS_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser,
                "a " + STATUS_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 z/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_SKILL_DESC, Skill.MESSAGE_CONSTRAINTS); // invalid SKILL

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_SKILL} alone will reset the SKILLs of the {@code Applicant} being edited,
        // parsing it together with a valid SKILL results in error
        assertParseFailure(parser, "1" + SKILL_DESC_PYTHON + SKILL_DESC_JAVA + SKILL_EMPTY, Skill.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + SKILL_DESC_PYTHON + SKILL_EMPTY + SKILL_DESC_JAVA, Skill.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + SKILL_EMPTY + SKILL_DESC_PYTHON + SKILL_DESC_JAVA, Skill.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_APPLICANT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + SKILL_DESC_JAVA
                + EMAIL_DESC_AMY + NAME_DESC_AMY + SKILL_DESC_PYTHON;

        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY)
                .withSkills(VALID_SKILL_JAVA, VALID_SKILL_PYTHON).build();
        EditCommand expectedCommand = new EditCommand(Selection.fromIndex(targetIndex), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_APPLICANT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(Selection.fromIndex(targetIndex), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_APPLICANT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(Selection.fromIndex(targetIndex), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditApplicantDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(Selection.fromIndex(targetIndex), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditApplicantDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(Selection.fromIndex(targetIndex), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // SKILLs
        userInput = targetIndex.getOneBased() + SKILL_DESC_PYTHON;
        descriptor = new EditApplicantDescriptorBuilder().withSkills(VALID_SKILL_PYTHON).build();
        expectedCommand = new EditCommand(Selection.fromIndex(targetIndex), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_APPLICANT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + SKILL_DESC_PYTHON + PHONE_DESC_AMY + EMAIL_DESC_AMY + SKILL_DESC_PYTHON
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + SKILL_DESC_JAVA;

        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withSkills(VALID_SKILL_PYTHON, VALID_SKILL_JAVA)
                .build();
        EditCommand expectedCommand = new EditCommand(Selection.fromIndex(targetIndex), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_APPLICANT;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(Selection.fromIndex(targetIndex), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC
                + PHONE_DESC_BOB;
        descriptor = new EditApplicantDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .build();
        expectedCommand = new EditCommand(Selection.fromIndex(targetIndex), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetSkills_success() {
        Index targetIndex = INDEX_THIRD_APPLICANT;
        String userInput = targetIndex.getOneBased() + SKILL_EMPTY;

        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder().withSkills().build();
        EditCommand expectedCommand = new EditCommand(Selection.fromIndex(targetIndex), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
