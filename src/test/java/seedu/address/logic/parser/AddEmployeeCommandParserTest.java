package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.INVALID_JOB_TITLE_DESC;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.INVALID_LEAVES_DESC;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.INVALID_SALARY_DESC;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.INVALID_SHIFT_DESC;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.JOB_TITLE_DESC_AMY;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.JOB_TITLE_DESC_BOB;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.LEAVES_DESC_AMY;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.LEAVES_DESC_BOB;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.SALARY_DESC_AMY;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.SALARY_DESC_BOB;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.SHIFTS_DESC_AMY;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.SHIFTS_DESC_BOB;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.VALID_JOB_TITLE_BOB;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.VALID_LEAVES_BOB;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.VALID_SALARY_BOB;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.VALID_SHIFTS_AMY;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.VALID_SHIFTS_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalEmployees.AMY_EMPLOYEE;
import static seedu.address.testutil.TypicalEmployees.BOB_EMPLOYEE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddEmployeeCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.employee.Employee;
import seedu.address.model.person.employee.JobTitle;
import seedu.address.model.person.employee.Leaves;
import seedu.address.model.person.employee.Salary;
import seedu.address.model.person.employee.Shift;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EmployeeBuilder;

public class AddEmployeeCommandParserTest {
    private AddEmployeeCommandParser parser = new AddEmployeeCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Employee expectedEmployee = new EmployeeBuilder(BOB_EMPLOYEE).withTags(VALID_TAG_FRIEND).build();
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + LEAVES_DESC_BOB + SALARY_DESC_BOB
                 + SHIFTS_DESC_BOB + SHIFTS_DESC_AMY
                + JOB_TITLE_DESC_BOB + TAG_DESC_FRIEND, new AddEmployeeCommand(expectedEmployee));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + LEAVES_DESC_BOB + SALARY_DESC_BOB
                 + SHIFTS_DESC_BOB + SHIFTS_DESC_AMY
                + JOB_TITLE_DESC_BOB + TAG_DESC_FRIEND, new AddEmployeeCommand(expectedEmployee));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + LEAVES_DESC_BOB + SALARY_DESC_BOB
                + SHIFTS_DESC_BOB + SHIFTS_DESC_AMY
                + JOB_TITLE_DESC_BOB + TAG_DESC_FRIEND, new AddEmployeeCommand(expectedEmployee));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + LEAVES_DESC_BOB + SALARY_DESC_BOB
                + SHIFTS_DESC_BOB + SHIFTS_DESC_AMY
                + JOB_TITLE_DESC_BOB + TAG_DESC_FRIEND, new AddEmployeeCommand(expectedEmployee));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + LEAVES_DESC_BOB + SALARY_DESC_BOB
                + SHIFTS_DESC_BOB + SHIFTS_DESC_AMY
                + JOB_TITLE_DESC_BOB + TAG_DESC_FRIEND, new AddEmployeeCommand(expectedEmployee));

        // multiple leaves - last leaves accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + LEAVES_DESC_AMY + LEAVES_DESC_BOB + SALARY_DESC_BOB
                + SHIFTS_DESC_BOB + SHIFTS_DESC_AMY
                + JOB_TITLE_DESC_BOB + TAG_DESC_FRIEND, new AddEmployeeCommand(expectedEmployee));

        // multiple salaries - last salary accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + LEAVES_DESC_BOB + SALARY_DESC_AMY + SALARY_DESC_BOB
                + SHIFTS_DESC_BOB + SHIFTS_DESC_AMY
                + JOB_TITLE_DESC_BOB + TAG_DESC_FRIEND, new AddEmployeeCommand(expectedEmployee));

        // multiple job titles - last job title accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + LEAVES_DESC_BOB + SALARY_DESC_BOB + JOB_TITLE_DESC_AMY
                + SHIFTS_DESC_BOB + SHIFTS_DESC_AMY
                + JOB_TITLE_DESC_BOB + TAG_DESC_FRIEND, new AddEmployeeCommand(expectedEmployee));

        // multiple tags - all accepted
        Employee expectedEmployeeMultipleTags = new EmployeeBuilder(BOB_EMPLOYEE)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + LEAVES_DESC_BOB + SALARY_DESC_BOB + JOB_TITLE_DESC_BOB + SHIFTS_DESC_BOB + SHIFTS_DESC_AMY
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddEmployeeCommand(expectedEmployeeMultipleTags));

        // multiple shifts - all accepted
        Employee expectedEmployeeMultipleShifts = new EmployeeBuilder(BOB_EMPLOYEE)
                .withShifts(VALID_SHIFTS_BOB, VALID_SHIFTS_AMY)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + LEAVES_DESC_BOB + SALARY_DESC_BOB + JOB_TITLE_DESC_BOB + SHIFTS_DESC_AMY + SHIFTS_DESC_BOB
                + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, new AddEmployeeCommand(expectedEmployeeMultipleShifts));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Employee expectedEmployee = new EmployeeBuilder(AMY_EMPLOYEE).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                + LEAVES_DESC_AMY + SALARY_DESC_AMY + JOB_TITLE_DESC_AMY + SHIFTS_DESC_AMY,
                new AddEmployeeCommand(expectedEmployee));

        // zero shifts
        expectedEmployee = new EmployeeBuilder(AMY_EMPLOYEE).withShifts().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                + LEAVES_DESC_AMY + SALARY_DESC_AMY + JOB_TITLE_DESC_AMY + TAG_DESC_FRIEND,
                new AddEmployeeCommand(expectedEmployee));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEmployeeCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + LEAVES_DESC_BOB + SALARY_DESC_BOB + JOB_TITLE_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + LEAVES_DESC_BOB + SALARY_DESC_BOB + JOB_TITLE_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB
                + LEAVES_DESC_BOB + SALARY_DESC_BOB + JOB_TITLE_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB
                + LEAVES_DESC_BOB + SALARY_DESC_BOB + JOB_TITLE_DESC_BOB,
                expectedMessage);

        // missing leaves prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + VALID_LEAVES_BOB + SALARY_DESC_BOB + JOB_TITLE_DESC_BOB,
                expectedMessage);

        // missing salary prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + LEAVES_DESC_BOB + VALID_SALARY_BOB + JOB_TITLE_DESC_BOB,
                expectedMessage);

        // missing jobTitle prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + LEAVES_DESC_BOB + SALARY_DESC_BOB + VALID_JOB_TITLE_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB
                + VALID_LEAVES_BOB + VALID_SALARY_BOB + VALID_JOB_TITLE_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + LEAVES_DESC_BOB + SALARY_DESC_BOB + JOB_TITLE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + LEAVES_DESC_BOB + SALARY_DESC_BOB + JOB_TITLE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + LEAVES_DESC_BOB + SALARY_DESC_BOB + JOB_TITLE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + LEAVES_DESC_BOB + SALARY_DESC_BOB + JOB_TITLE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        //invalid leaves
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_LEAVES_DESC + SALARY_DESC_BOB + JOB_TITLE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Leaves.MESSAGE_CONSTRAINTS);

        //invalid salary
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + LEAVES_DESC_BOB + INVALID_SALARY_DESC + JOB_TITLE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Salary.MESSAGE_CONSTRAINTS);

        //invalid job title
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + LEAVES_DESC_BOB + SALARY_DESC_BOB + INVALID_JOB_TITLE_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, JobTitle.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + LEAVES_DESC_BOB + SALARY_DESC_BOB + JOB_TITLE_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // invalid shift
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + LEAVES_DESC_BOB + SALARY_DESC_BOB + JOB_TITLE_DESC_BOB
                + INVALID_SHIFT_DESC, Shift.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + LEAVES_DESC_BOB + SALARY_DESC_BOB + JOB_TITLE_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND
                        + LEAVES_DESC_BOB + SALARY_DESC_BOB + JOB_TITLE_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEmployeeCommand.MESSAGE_USAGE));
    }
}
