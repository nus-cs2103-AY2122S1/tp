package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDIT_SPOT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditStudySpotDescriptor;
import seedu.address.model.studyspot.Address;
import seedu.address.model.studyspot.Name;
import seedu.address.model.studyspot.Rating;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditStudySpotDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_FRONTIER, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, " " + PREFIX_EDIT_SPOT + "Test", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_FRONTIER, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_FRONTIER, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, " " + PREFIX_EDIT_SPOT + "Test" + INVALID_NAME_DESC,
                Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, " " + PREFIX_EDIT_SPOT + "Test" + INVALID_RATING_DESC,
                Rating.MESSAGE_CONSTRAINTS); // invalid rating
        assertParseFailure(parser, " " + PREFIX_EDIT_SPOT + "Test" + INVALID_RATING_OUTOFRANGE_DESC,
                Rating.MESSAGE_CONSTRAINTS); // invalid rating
        assertParseFailure(parser, " " + PREFIX_EDIT_SPOT + "Test" + INVALID_ADDRESS_DESC,
                Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, " " + PREFIX_EDIT_SPOT + "Test" + INVALID_TAG_DESC,
                Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid rating followed by valid operating hours
        assertParseFailure(parser, " " + PREFIX_EDIT_SPOT + "Test" + INVALID_RATING_DESC
                        + OPERATING_HOURS_DESC_FRONTIER,
                Rating.MESSAGE_CONSTRAINTS);

        // valid rating followed by invalid rating. The test case for invalid rating followed by valid rating
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, " " + PREFIX_EDIT_SPOT + "Test" + RATING_DESC_DECK + INVALID_RATING_DESC,
                Rating.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code StudySpot} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, " " + PREFIX_EDIT_SPOT + "Test" + TAG_DESC_CROWDED
                + TAG_DESC_QUIET + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " " + PREFIX_EDIT_SPOT + "Test" + TAG_DESC_CROWDED + TAG_EMPTY
                + TAG_DESC_QUIET, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " " + PREFIX_EDIT_SPOT + "Test" + TAG_EMPTY + TAG_DESC_CROWDED
                + TAG_DESC_QUIET, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, " " + PREFIX_EDIT_SPOT + "Test"
                        + INVALID_NAME_DESC + INVALID_OPERATING_HOURS_DESC + VALID_ADDRESS_FRONTIER
                        + VALID_RATING_FRONTIER,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Name targetName = new Name(VALID_NAME_FRONTIER);
        String userInput = " " + PREFIX_EDIT_SPOT + targetName.fullName + RATING_DESC_DECK
                + OPERATING_HOURS_DESC_FRONTIER + ADDRESS_DESC_FRONTIER + NAME_DESC_FRONTIER
                + TAG_DESC_CROWDED + TAG_DESC_REMOVE_QUIET
                + AMENITY_DESC_WIFI + AMENITY_RM_DESC_FOOD;

        EditStudySpotDescriptor descriptor = new EditStudySpotDescriptorBuilder().withName(VALID_NAME_FRONTIER)
                .withRating(VALID_RATING_DECK).withOperatingHours(VALID_OPERATING_HOURS_FRONTIER)
                .withAddress(VALID_ADDRESS_FRONTIER)
                .withAddedTags(VALID_TAG_CROWDED).withRemovedTags(VALID_TAG_QUIET)
                .withAddedAmenities(VALID_AMENITY_WIFI).withRemovedAmenities(VALID_AMENITY_FOOD).build();
        EditCommand expectedCommand = new EditCommand(targetName, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Name targetName = new Name(VALID_NAME_FRONTIER);
        String userInput = " " + PREFIX_EDIT_SPOT + targetName.fullName + RATING_DESC_DECK
                + OPERATING_HOURS_DESC_FRONTIER;

        EditStudySpotDescriptor descriptor = new EditStudySpotDescriptorBuilder().withRating(VALID_RATING_DECK)
                .withOperatingHours(VALID_OPERATING_HOURS_FRONTIER).build();
        EditCommand expectedCommand = new EditCommand(targetName, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Name targetName = new Name(VALID_NAME_FRONTIER);
        String userInput = " " + PREFIX_EDIT_SPOT + targetName.fullName + NAME_DESC_FRONTIER;
        EditStudySpotDescriptor descriptor = new EditStudySpotDescriptorBuilder().withName(VALID_NAME_FRONTIER).build();
        EditCommand expectedCommand = new EditCommand(targetName, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // rating
        userInput = " " + PREFIX_EDIT_SPOT + targetName.fullName + RATING_DESC_FRONTIER;
        descriptor = new EditStudySpotDescriptorBuilder().withRating(VALID_RATING_FRONTIER).build();
        expectedCommand = new EditCommand(targetName, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // operating hours
        userInput = " " + PREFIX_EDIT_SPOT + targetName.fullName + OPERATING_HOURS_DESC_FRONTIER;
        descriptor = new EditStudySpotDescriptorBuilder().withOperatingHours(VALID_OPERATING_HOURS_FRONTIER).build();
        expectedCommand = new EditCommand(targetName, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = " " + PREFIX_EDIT_SPOT + targetName.fullName + ADDRESS_DESC_FRONTIER;
        descriptor = new EditStudySpotDescriptorBuilder().withAddress(VALID_ADDRESS_FRONTIER).build();
        expectedCommand = new EditCommand(targetName, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = " " + PREFIX_EDIT_SPOT + targetName.fullName + TAG_DESC_CROWDED;
        descriptor = new EditStudySpotDescriptorBuilder().withAddedTags(VALID_TAG_CROWDED).build();
        expectedCommand = new EditCommand(targetName, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Name targetName = new Name(VALID_NAME_FRONTIER);
        Name extraInputName = new Name(VALID_NAME_DECK);
        String userInput = " " + PREFIX_EDIT_SPOT + extraInputName.fullName + RATING_DESC_FRONTIER
                + ADDRESS_DESC_FRONTIER + OPERATING_HOURS_DESC_FRONTIER + TAG_DESC_CROWDED + RATING_DESC_FRONTIER
                + ADDRESS_DESC_FRONTIER + OPERATING_HOURS_DESC_FRONTIER + TAG_DESC_CROWDED + RATING_DESC_DECK
                + ADDRESS_DESC_DECK
                + OPERATING_HOURS_DESC_DECK + TAG_DESC_QUIET + " " + PREFIX_EDIT_SPOT + targetName.fullName;

        EditStudySpotDescriptor descriptor = new EditStudySpotDescriptorBuilder()
                .withRating(VALID_RATING_DECK)
                .withOperatingHours(VALID_OPERATING_HOURS_DECK)
                .withAddress(VALID_ADDRESS_DECK)
                .withAddedTags(VALID_TAG_CROWDED, VALID_TAG_QUIET)
                .build();
        EditCommand expectedCommand = new EditCommand(targetName, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Name targetName = new Name(VALID_NAME_FRONTIER);
        String userInput = " " + PREFIX_EDIT_SPOT + targetName.fullName + INVALID_RATING_DESC + RATING_DESC_DECK;
        EditStudySpotDescriptor descriptor = new EditStudySpotDescriptorBuilder().withRating(VALID_RATING_DECK).build();
        EditCommand expectedCommand = new EditCommand(targetName, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = " " + PREFIX_EDIT_SPOT + targetName.fullName + OPERATING_HOURS_DESC_DECK + INVALID_RATING_DESC
                + ADDRESS_DESC_DECK + RATING_DESC_DECK;
        descriptor = new EditStudySpotDescriptorBuilder().withRating(VALID_RATING_DECK)
                .withOperatingHours(VALID_OPERATING_HOURS_DECK)
                .withAddress(VALID_ADDRESS_DECK).build();
        expectedCommand = new EditCommand(targetName, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Name targetName = new Name(VALID_NAME_FRONTIER);
        String userInput = " " + PREFIX_EDIT_SPOT + targetName.fullName + TAG_EMPTY;

        EditStudySpotDescriptor descriptor = new EditStudySpotDescriptorBuilder().withAddedTags().build();
        EditCommand expectedCommand = new EditCommand(targetName, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    //todo reset amenities
}
