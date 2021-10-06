package seedu.plannermd.logic.parser;

import static seedu.plannermd.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.plannermd.logic.parser.CliSyntax.FLAG_DELETE;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.plannermd.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.plannermd.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.plannermd.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.logic.commands.tagcommand.AddPatientTagCommand;
import seedu.plannermd.logic.commands.tagcommand.DeletePatientTagCommand;
import seedu.plannermd.model.tag.Tag;

class TagCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            AddPatientTagCommand.MESSAGE_USAGE + "\n" + DeletePatientTagCommand.MESSAGE_USAGE);

    private static final String VALID_TAG = "tag1";
    private static final String INVALID_TAG = "!@#";

    private final TagCommandParser parser = new TagCommandParser();

    @Test
    void parse_addIndexAndTagSpecified_success() {
        Index index = INDEX_FIRST_PERSON;
        String userInput = " " + PREFIX_ID + index.getOneBased() + " " + PREFIX_TAG + VALID_TAG;
        AddPatientTagCommand addPatientTagCommand = new AddPatientTagCommand(index, new Tag(VALID_TAG));

        assertParseSuccess(parser, userInput, addPatientTagCommand);
    }

    @Test
    void parse_addMissingFields_failure() {
        // missing index
        assertParseFailure(parser, " " + PREFIX_TAG + VALID_TAG, MESSAGE_INVALID_FORMAT);

        // missing tag
        assertParseFailure(parser, " " + PREFIX_ID + INDEX_FIRST_PERSON.getOneBased(),
                AddPatientTagCommand.MESSAGE_NOT_ADDED);

        // missing both index and tag
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    void parse_addInvalidValues_failure() {
        // invalid index
        assertParseFailure(parser, " " + PREFIX_ID + "-1 " + PREFIX_TAG + VALID_TAG, MESSAGE_INVALID_FORMAT);

        // invalid tag
        assertParseFailure(parser, " " + PREFIX_ID + INDEX_FIRST_PERSON.getOneBased()
                        + " " + PREFIX_TAG + INVALID_TAG,
                Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    void parse_deleteIndexAndTagSpecified_success() {
        Index index = INDEX_FIRST_PERSON;
        String userInput = FLAG_DELETE + " " + PREFIX_ID + index.getOneBased() + " " + PREFIX_TAG + VALID_TAG;
        DeletePatientTagCommand deletePatientTagCommand = new DeletePatientTagCommand(index, new Tag(VALID_TAG));

        assertParseSuccess(parser, userInput, deletePatientTagCommand);
    }

    @Test
    void parse_deleteMissingFields_failure() {
        // missing index
        assertParseFailure(parser, FLAG_DELETE + " " + PREFIX_TAG + VALID_TAG,
                MESSAGE_INVALID_FORMAT);

        // missing tag
        assertParseFailure(parser, FLAG_DELETE + " " + PREFIX_ID + INDEX_FIRST_PERSON.getOneBased(),
                AddPatientTagCommand.MESSAGE_NOT_ADDED);

        // missing both index and tag
        assertParseFailure(parser, FLAG_DELETE.toString(), MESSAGE_INVALID_FORMAT);
    }

    @Test
    void parse_deleteInvalidValues_failure() {
        // invalid index
        assertParseFailure(parser, FLAG_DELETE + " " + PREFIX_ID + "-1 " + PREFIX_TAG + VALID_TAG,
                MESSAGE_INVALID_FORMAT);

        // invalid tag
        assertParseFailure(parser, FLAG_DELETE + " " + PREFIX_ID + INDEX_FIRST_PERSON.getOneBased()
                        + " " + PREFIX_TAG + INVALID_TAG,
                Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    void parse_invalidPreamble_failure() {
        assertParseFailure(parser, "1 " + PREFIX_ID + INDEX_FIRST_PERSON + " " + PREFIX_TAG + VALID_TAG,
                MESSAGE_INVALID_FORMAT);

        assertParseFailure(parser, "d " + PREFIX_ID + INDEX_FIRST_PERSON + " " + PREFIX_TAG + VALID_TAG,
                MESSAGE_INVALID_FORMAT);

        assertParseFailure(parser, "-z " + PREFIX_ID + INDEX_FIRST_PERSON + " " + PREFIX_TAG + VALID_TAG,
                MESSAGE_INVALID_FORMAT);

        assertParseFailure(parser, "random string " + PREFIX_ID + INDEX_FIRST_PERSON
                        + " " + PREFIX_TAG + VALID_TAG,
                MESSAGE_INVALID_FORMAT);
    }
}
