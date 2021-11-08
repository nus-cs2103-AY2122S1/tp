package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_REPONAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STUDENTNUMBER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_USERNAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.REPONAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.STUDENTNUMBER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.USERNAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REPONAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENTNUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USERNAME_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditStudentCommand;
import seedu.address.logic.commands.EditStudentCommand.EditStudentDescriptor;
import seedu.address.model.commons.RepoName;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.StudentNumber;
import seedu.address.model.student.UserName;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditStudentDescriptorBuilder;

public class EditStudentCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditStudentCommand.MESSAGE_USAGE);

    private final EditStudentCommandParser parser = new EditStudentCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditStudentCommand.MESSAGE_NOT_EDITED);

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
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag
        assertParseFailure(parser, "1" + INVALID_STUDENTNUMBER_DESC, StudentNumber.MESSAGE_CONSTRAINTS);
        // invalid studentNumber
        assertParseFailure(parser, "1" + INVALID_USERNAME_DESC, UserName.MESSAGE_CONSTRAINTS);
        // invalid username
        assertParseFailure(parser, "1" + INVALID_REPONAME_DESC, RepoName.MESSAGE_CONSTRAINTS);
        // invalid repoName


        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Student} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND,
                Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_STUDENT;
        String userInput = targetIndex.getOneBased() + TAG_DESC_HUSBAND + EMAIL_DESC_AMY + USERNAME_DESC_AMY
                + NAME_DESC_AMY + STUDENTNUMBER_DESC_AMY + REPONAME_DESC_AMY + TAG_DESC_FRIEND;

        EditStudentCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder()
                .withName(VALID_NAME_AMY)
                .withEmail(VALID_EMAIL_AMY).withStudentNumber(VALID_STUDENTNUMBER_AMY).withRepoName(VALID_REPONAME_AMY)
                .withUserName(VALID_USERNAME_AMY).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditStudentCommand expectedCommand = new EditStudentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder()
                .withEmail(VALID_EMAIL_AMY).build();
        EditStudentCommand expectedCommand = new EditStudentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_STUDENT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditStudentCommand expectedCommand = new EditStudentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditStudentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // studentNumber
        userInput = targetIndex.getOneBased() + STUDENTNUMBER_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withStudentNumber(VALID_STUDENTNUMBER_AMY).build();
        expectedCommand = new EditStudentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // userName
        userInput = targetIndex.getOneBased() + USERNAME_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withUserName(VALID_USERNAME_AMY).build();
        expectedCommand = new EditStudentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // repoName
        userInput = targetIndex.getOneBased() + REPONAME_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withRepoName(VALID_REPONAME_AMY).build();
        expectedCommand = new EditStudentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditStudentDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditStudentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + EMAIL_DESC_AMY + TAG_DESC_FRIEND + EMAIL_DESC_BOB + TAG_DESC_HUSBAND;

        EditStudentCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder()
                .withEmail(VALID_EMAIL_BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        EditStudentCommand expectedCommand = new EditStudentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + INVALID_EMAIL_DESC + EMAIL_DESC_BOB;
        EditStudentCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder()
                .withEmail(VALID_EMAIL_BOB)
                .build();
        EditStudentCommand expectedCommand = new EditStudentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB;
        descriptor = new EditStudentDescriptorBuilder().withEmail(VALID_EMAIL_BOB).build();
        expectedCommand = new EditStudentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_STUDENT;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditStudentCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withTags().build();
        EditStudentCommand expectedCommand = new EditStudentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
