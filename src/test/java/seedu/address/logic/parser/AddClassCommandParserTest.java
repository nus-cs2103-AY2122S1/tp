package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.AddClassCommand;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorialclass.Schedule;
import seedu.address.model.tutorialclass.TutorialClass;
import seedu.address.testutil.TutorialClassBuilder;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTutorialClasses.G101;

public class AddClassCommandParserTest {
    private AddClassCommandParser parser = new AddClassCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        TutorialClass expectedTutorialClass = new TutorialClassBuilder(G101).withTags(VALID_TAG_BESTCLASS).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + CLASSCODE_DESC_G101 + SCHEDULE_DESC_G1O1
                + TAG_DESC_BESTCLASS, new AddClassCommand(expectedTutorialClass));

        // multiple classCodes - last classCode accepted
        assertParseSuccess(parser, CLASSCODE_DESC_G102 + CLASSCODE_DESC_G101 + SCHEDULE_DESC_G1O1
                + TAG_DESC_BESTCLASS, new AddClassCommand(expectedTutorialClass));

        // multiple schedules - last schedule accepted
        assertParseSuccess(parser,  CLASSCODE_DESC_G101 + SCHEDULE_DESC_G1O2 + SCHEDULE_DESC_G1O1
                + TAG_DESC_BESTCLASS, new AddClassCommand(expectedTutorialClass));

        // multiple tags - all accepted
        TutorialClass expectedTutorialClassMultipleTags = new TutorialClassBuilder(G101).withTags(VALID_TAG_BESTCLASS, VALID_TAG_MORNING).build();
        assertParseSuccess(parser, CLASSCODE_DESC_G101 + SCHEDULE_DESC_G1O1 + TAG_DESC_MORNING
                + TAG_DESC_BESTCLASS, new AddClassCommand(expectedTutorialClassMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        TutorialClass expectedTutorialClass = new TutorialClassBuilder(G101).withTags().build();
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + CLASSCODE_DESC_G101 + SCHEDULE_DESC_G1O1
                , new AddClassCommand(expectedTutorialClass));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClassCommand.MESSAGE_USAGE);

        // missing ClassCode Prefix
        assertParseFailure(parser, VALID_CLASSCODE_G101 + SCHEDULE_DESC_G1O1, expectedMessage);

        // missing schedule prefix
        assertParseFailure(parser, CLASSCODE_DESC_G101 + VALID_SCHEDULE_G101, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_CLASSCODE_G101 + VALID_SCHEDULE_G101, expectedMessage);

    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid schedule
        assertParseFailure(parser, CLASSCODE_DESC_G101 + INVALID_SCHEDULE + TAG_DESC_MORNING, Schedule.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, CLASSCODE_DESC_G101 + SCHEDULE_DESC_G1O1 + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, CLASSCODE_DESC_G101 + INVALID_SCHEDULE + INVALID_TAG_DESC, Schedule.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, PREAMBLE_NON_EMPTY + CLASSCODE_DESC_G101 + SCHEDULE_DESC_G1O1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClassCommand.MESSAGE_USAGE));
    }
}
