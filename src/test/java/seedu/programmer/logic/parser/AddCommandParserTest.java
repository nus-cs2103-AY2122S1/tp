package seedu.programmer.logic.parser;

import static seedu.programmer.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.programmer.logic.commands.CommandTestUtil.CLASSID_DESC_AMY;
import static seedu.programmer.logic.commands.CommandTestUtil.CLASSID_DESC_BOB;
import static seedu.programmer.logic.commands.CommandTestUtil.GRADE_DESC_AMY;
import static seedu.programmer.logic.commands.CommandTestUtil.GRADE_DESC_BOB;
import static seedu.programmer.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.programmer.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.programmer.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.programmer.logic.commands.CommandTestUtil.STUDENTID_DESC_AMY;
import static seedu.programmer.logic.commands.CommandTestUtil.STUDENTID_DESC_BOB;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_CLASSID_BOB;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_GRADE_BOB;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_STUDENTID_BOB;
import static seedu.programmer.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.programmer.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.programmer.testutil.TypicalPersons.AMY;
import static seedu.programmer.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.programmer.logic.commands.AddCommand;
import seedu.programmer.model.person.Person;
import seedu.programmer.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        //System.out.println(NAME_DESC_BOB);
        Person expectedPerson = new PersonBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + STUDENTID_DESC_BOB + CLASSID_DESC_BOB
                + GRADE_DESC_BOB, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + STUDENTID_DESC_BOB + CLASSID_DESC_BOB
                + GRADE_DESC_BOB , new AddCommand(expectedPerson));
    }

    //TODO
    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).build();
        System.out.println(expectedPerson);
        assertParseSuccess(parser, NAME_DESC_AMY + STUDENTID_DESC_AMY + CLASSID_DESC_AMY + GRADE_DESC_AMY,
                new AddCommand(expectedPerson));
        System.out.println(expectedPerson);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + STUDENTID_DESC_BOB + CLASSID_DESC_BOB + GRADE_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_STUDENTID_BOB + CLASSID_DESC_BOB + GRADE_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + STUDENTID_DESC_BOB + VALID_CLASSID_BOB + GRADE_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + STUDENTID_DESC_BOB + CLASSID_DESC_BOB + VALID_GRADE_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_STUDENTID_BOB + VALID_CLASSID_BOB + VALID_GRADE_BOB,
                expectedMessage);
    }
}
