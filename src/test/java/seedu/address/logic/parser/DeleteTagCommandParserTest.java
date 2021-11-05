package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.DeleteTagCommand.MESSAGE_INVALID_COMMAND_FORMAT_INVALID_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.model.tag.Tag;

public class DeleteTagCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagCommand.MESSAGE_USAGE);

    private DeleteTagCommandParser parser = new DeleteTagCommandParser();

    @Test
    public void parse_invalidValue_failure() {

        assertParseFailure(parser, "0" + TAG_DESC_FRIEND, String.format(
                MESSAGE_INVALID_COMMAND_FORMAT_INVALID_INDEX, DeleteTagCommand.MESSAGE_USAGE)); // invalid index
        assertParseFailure(parser, "-1" + TAG_DESC_FRIEND, String.format(
                MESSAGE_INVALID_COMMAND_FORMAT_INVALID_INDEX, DeleteTagCommand.MESSAGE_USAGE)); // negative index
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(
                parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(
                parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(
                parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "0" + TAG_EMPTY, String.format(
                MESSAGE_INVALID_COMMAND_FORMAT_INVALID_INDEX, DeleteTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String validTagString = TAG_DESC_FRIEND;
        Set<Tag> validTag = new HashSet<>(Arrays.asList(new Tag(VALID_TAG_FRIEND)));

        DeleteTagCommand expectedCommand = new DeleteTagCommand(targetIndex, validTag);

        assertParseSuccess(parser, targetIndex.getOneBased() + " " + validTagString, expectedCommand);
    }

    @Test
    public void parse_indexOnlySpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        Set<Tag> emptyTag = null;

        DeleteTagCommand expectedCommand = new DeleteTagCommand(targetIndex, emptyTag);

        assertParseSuccess(parser, String.valueOf(targetIndex.getOneBased()), expectedCommand);
    }

    @Test
    public void parse_tagOnlySpecified_success() {
        Index targetIndex = null;
        String validTagString = TAG_DESC_FRIEND;
        Set<Tag> validTag = new HashSet<>(Arrays.asList(new Tag(VALID_TAG_FRIEND)));

        DeleteTagCommand expectedCommand = new DeleteTagCommand(targetIndex, validTag);

        assertParseSuccess(parser, "all" + validTagString, expectedCommand);
    }

    @Test
    public void parse_noneSpecified_success() {
        Index targetIndex = null;
        Set<Tag> validTag = null;

        DeleteTagCommand expectedCommand = new DeleteTagCommand(targetIndex, validTag);

        assertParseSuccess(parser, "all", expectedCommand);
    }

    @Test
    public void parse_multipleTags_success() {
        //Error should be raised only at DeleteTagCommand.execute
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND + TAG_DESC_HUSBAND;

        DeleteTagCommand expectedCommand = new DeleteTagCommand(targetIndex,
                new HashSet<>(Arrays.asList(new Tag("friend"), new Tag("husband"))));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleIndices_failure() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " "
                + INDEX_SECOND_PERSON.getOneBased() + TAG_DESC_FRIEND;
        String expectedMessage = MESSAGE_INVALID_FORMAT + DeleteTagCommand.MESSAGE_USAGE;
        assertParseFailure(parser, userInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT_INVALID_INDEX, DeleteTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        //Error should be raised only at DeleteTagCommand.execute
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_DESC_HUSBAND + TAG_DESC_FRIEND;

        DeleteTagCommand expectedCommand = new DeleteTagCommand(targetIndex,
                new HashSet<>(Arrays.asList(new Tag("husband"), new Tag("friend"))));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        DeleteTagCommand expectedCommand = new DeleteTagCommand(targetIndex, new HashSet<>());

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
