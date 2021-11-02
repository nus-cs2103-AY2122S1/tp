package seedu.unify.logic.parser;

import static seedu.unify.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.unify.logic.commands.CommandTestUtil.DATE_DESC_QUIZ;
import static seedu.unify.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.unify.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.unify.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.unify.logic.commands.CommandTestUtil.INVALID_TIME_DESC;
import static seedu.unify.logic.commands.CommandTestUtil.NAME_DESC_QUIZ;
import static seedu.unify.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.unify.logic.commands.CommandTestUtil.TAG_DESC_CCA;
import static seedu.unify.logic.commands.CommandTestUtil.TAG_DESC_MODULE;
import static seedu.unify.logic.commands.CommandTestUtil.TIME_DESC_QUIZ;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_DATE_QUIZ;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_NAME_QUIZ;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_TAG_CCA;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_TIME_QUIZ;
import static seedu.unify.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.unify.logic.commands.AddCommand;
import seedu.unify.model.tag.Tag;
import seedu.unify.model.task.Date;
import seedu.unify.model.task.Name;
import seedu.unify.model.task.Time;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_QUIZ + TIME_DESC_QUIZ + DATE_DESC_QUIZ,
                expectedMessage);

        // missing date prefix
        assertParseFailure(parser, NAME_DESC_QUIZ + TIME_DESC_QUIZ + VALID_DATE_QUIZ,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_QUIZ + VALID_TIME_QUIZ + VALID_DATE_QUIZ,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + TIME_DESC_QUIZ + DATE_DESC_QUIZ
                + TAG_DESC_MODULE + TAG_DESC_CCA, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_QUIZ + INVALID_TIME_DESC + DATE_DESC_QUIZ
                + TAG_DESC_MODULE + TAG_DESC_CCA, Time.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, NAME_DESC_QUIZ + TIME_DESC_QUIZ + INVALID_DATE_DESC
                + TAG_DESC_MODULE + TAG_DESC_CCA, Date.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_QUIZ + TIME_DESC_QUIZ + DATE_DESC_QUIZ
                + INVALID_TAG_DESC + VALID_TAG_CCA, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + TIME_DESC_QUIZ + INVALID_DATE_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_QUIZ + TIME_DESC_QUIZ
                        + DATE_DESC_QUIZ + TAG_DESC_MODULE + TAG_DESC_CCA,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
