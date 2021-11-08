package seedu.plannermd.logic.parser.tagcommandparser;

import static seedu.plannermd.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.plannermd.logic.commands.tagcommand.TagCommand.TOO_MANY_TAGS_MESSAGE;
import static seedu.plannermd.logic.parser.CliSyntax.FLAG_ADD;
import static seedu.plannermd.logic.parser.CliSyntax.FLAG_DELETE;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.plannermd.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.plannermd.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.plannermd.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.logic.commands.tagcommand.AddPatientTagCommand;
import seedu.plannermd.logic.commands.tagcommand.DeletePatientTagCommand;
import seedu.plannermd.model.tag.Tag;

class TagPatientCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            AddPatientTagCommand.MESSAGE_USAGE + "\n" + DeletePatientTagCommand.MESSAGE_USAGE);

    private static final String VALID_TAG = "tag1";
    private static final String INVALID_TAG = "!@#";

    private final TagPatientCommandParser parser = new TagPatientCommandParser();

    @Test
    void parse_addIndexAndTagSpecified_success() {
        Index index = INDEX_FIRST_PERSON;
        String userInput = FLAG_ADD + " " + index.getOneBased() + " " + PREFIX_TAG + VALID_TAG;
        AddPatientTagCommand addPatientTagCommand = new AddPatientTagCommand(index, new Tag(VALID_TAG));

        assertParseSuccess(parser, userInput, addPatientTagCommand);
    }

    @Test
    void parse_addMissingFields_failure() {
        // missing index
        assertParseFailure(parser, FLAG_ADD + " " + PREFIX_TAG + VALID_TAG, MESSAGE_INVALID_FORMAT);

        // missing tag
        assertParseFailure(parser, FLAG_ADD + " " + INDEX_FIRST_PERSON.getOneBased(),
                AddPatientTagCommand.MESSAGE_NOT_ADDED);

        // missing both index and tag
        assertParseFailure(parser, FLAG_ADD, MESSAGE_INVALID_FORMAT);
    }

    @Test
    void parse_addMultipleTags_failure() {
        assertParseFailure(parser, FLAG_ADD + " " + INDEX_FIRST_PERSON.getOneBased() + " "
                + PREFIX_TAG + VALID_TAG + " " + PREFIX_TAG + "test", TOO_MANY_TAGS_MESSAGE);
    }

    @Test
    void parse_addInvalidValues_failure() {
        // invalid index
        assertParseFailure(parser, FLAG_ADD + " " + "-1 " + PREFIX_TAG + VALID_TAG, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, FLAG_ADD + " " + "a " + PREFIX_TAG + VALID_TAG, MESSAGE_INVALID_FORMAT);

        // invalid tag
        assertParseFailure(parser, FLAG_ADD + " " + INDEX_FIRST_PERSON.getOneBased()
                        + " " + PREFIX_TAG + INVALID_TAG,
                Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    void parse_deleteIndexAndTagSpecified_success() {
        Index index = INDEX_FIRST_PERSON;
        String userInput = FLAG_DELETE + " " + index.getOneBased() + " " + PREFIX_TAG + VALID_TAG;
        DeletePatientTagCommand deletePatientTagCommand = new DeletePatientTagCommand(index, new Tag(VALID_TAG));

        assertParseSuccess(parser, userInput, deletePatientTagCommand);
    }

    @Test
    void parse_deleteMissingFields_failure() {
        // missing index
        assertParseFailure(parser, FLAG_DELETE + " " + PREFIX_TAG + VALID_TAG,
                MESSAGE_INVALID_FORMAT);

        // missing tag
        assertParseFailure(parser, FLAG_DELETE + " " + INDEX_FIRST_PERSON.getOneBased(),
                AddPatientTagCommand.MESSAGE_NOT_ADDED);

        // missing both index and tag
        assertParseFailure(parser, FLAG_DELETE, MESSAGE_INVALID_FORMAT);
    }

    @Test
    void parse_deleteInvalidValues_failure() {
        // invalid index
        assertParseFailure(parser, FLAG_DELETE + " " + "-1 " + PREFIX_TAG + VALID_TAG,
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, FLAG_DELETE + " " + "a " + PREFIX_TAG + VALID_TAG,
                MESSAGE_INVALID_FORMAT);

        // invalid tag
        assertParseFailure(parser, FLAG_DELETE + " " + INDEX_FIRST_PERSON.getOneBased()
                        + " " + PREFIX_TAG + INVALID_TAG,
                Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    void parse_deleteMultipleTags_failure() {
        assertParseFailure(parser, FLAG_DELETE + " " + INDEX_FIRST_PERSON.getOneBased()
                + " " + PREFIX_TAG + VALID_TAG + " " + PREFIX_TAG + "test", TOO_MANY_TAGS_MESSAGE);
    }

    @Test
    void parse_invalidFlag_failure() {
        assertParseFailure(parser, "1 " + INDEX_FIRST_PERSON + " " + PREFIX_TAG + VALID_TAG,
                MESSAGE_INVALID_FORMAT);

        assertParseFailure(parser, "d " + INDEX_FIRST_PERSON + " " + PREFIX_TAG + VALID_TAG,
                MESSAGE_INVALID_FORMAT);

        assertParseFailure(parser, "-z " + INDEX_FIRST_PERSON + " " + PREFIX_TAG + VALID_TAG,
                MESSAGE_INVALID_FORMAT);

        assertParseFailure(parser, "random string " + INDEX_FIRST_PERSON
                        + " " + PREFIX_TAG + VALID_TAG,
                MESSAGE_INVALID_FORMAT);
    }
}
