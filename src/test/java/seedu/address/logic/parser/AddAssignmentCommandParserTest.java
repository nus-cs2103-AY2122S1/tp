package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.AddAssignmentCommand;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Description;
import seedu.address.model.assignment.DueDate;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalAssignments;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.*;

public class AddAssignmentCommandParserTest {
    private AddAssignmentCommandParser parser = new AddAssignmentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).build();
        Assignment expectedAssignment = TypicalAssignments.ASSIGNMENT_CS1231S_TUTORIAL;

        // whitespace only preamble
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + POSITIVE_INDEX + ASSIGNMENT_DESC_BOB + DATE_DESC_BOB,
                new AddAssignmentCommand(INDEX_FIRST_PERSON, expectedAssignment));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAssignmentCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, ASSIGNMENT_DESC_BOB + DATE_DESC_BOB,
                expectedMessage);

        // missing assignment description prefix
        assertParseFailure(parser, POSITIVE_INDEX + VALID_DESCRIPTION_CS1231S_TUTORIAL + DATE_DESC_BOB,
                expectedMessage);

        // missing due date prefix
        assertParseFailure(parser, POSITIVE_INDEX + ASSIGNMENT_DESC_BOB + VALID_DATE_TIME_CS1231S_TUTORIAL,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser,
                POSITIVE_INDEX + VALID_DESCRIPTION_CS1231S_TUTORIAL + VALID_DATE_TIME_CS1231S_TUTORIAL,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid index : negative index
        assertParseFailure(parser, NEGATIVE_INDEX + ASSIGNMENT_DESC_BOB + DATE_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAssignmentCommand.MESSAGE_USAGE));

        // invalid index : zero index
        assertParseFailure(parser, ZERO_INDEX + ASSIGNMENT_DESC_BOB + DATE_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAssignmentCommand.MESSAGE_USAGE));

        // invalid assignment description
        assertParseFailure(parser, POSITIVE_INDEX + INVALID_DESCRIPTION_DESC + DATE_DESC_BOB,
                Description.MESSAGE_CONSTRAINTS);

        // invalid due date
        assertParseFailure(parser, POSITIVE_INDEX + ASSIGNMENT_DESC_BOB + INVALID_DATE_DESCRIPTION,
                DueDate.MESSAGE_CONSTRAINTS_DUE_DATE);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, ZERO_INDEX + ASSIGNMENT_DESC_BOB + INVALID_DATE_DESCRIPTION,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAssignmentCommand.MESSAGE_USAGE));

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + POSITIVE_INDEX + ASSIGNMENT_DESC_BOB
                        + DATE_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAssignmentCommand.MESSAGE_USAGE));
    }
}
