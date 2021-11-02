package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SORT_ORDER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.SORT_BY_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.SORT_BY_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.SORT_BY_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.SORT_BY_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.SORT_DESC_ASCENDING;
import static seedu.address.logic.commands.CommandTestUtil.SORT_DESC_DESCENDING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORT_BY_ADDRESS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORT_BY_NAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORT_ORDER_ASCENDING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORT_ORDER_DESCENDING;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.INVALID_SORT_BY_SHIFT;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.INVALID_SORT_BY_TAG;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.SORT_BY_DESC_JOB_TITLE;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.SORT_BY_DESC_LEAVES;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.SORT_BY_DESC_SALARY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortEmployeeCommand;
import seedu.address.model.person.SortOrder;
import seedu.address.model.person.employee.EmployeeComparator;
import seedu.address.model.person.employee.SortByEmployee;


public class SortEmployeeCommandParserTest {

    private SortEmployeeCommandParser parser = new SortEmployeeCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + SORT_BY_NAME_DESC + SORT_DESC_ASCENDING,
                new SortEmployeeCommand(EmployeeComparator.getNameComparator(true),
                        "name", "ascending"));

        // multiple sort by's - last sort by accepted
        assertParseSuccess(parser, SORT_BY_NAME_DESC + SORT_DESC_DESCENDING + SORT_BY_DESC_SALARY,
                new SortEmployeeCommand(EmployeeComparator.getSalaryComparator(false),
                        "salary", "descending"));

        // multiple sorting orders - last sorting order accepted
        assertParseSuccess(parser, SORT_BY_DESC_LEAVES + SORT_DESC_DESCENDING + SORT_DESC_ASCENDING,
                new SortEmployeeCommand(EmployeeComparator.getLeavesComparator(true),
                        "leaves", "ascending"));

        // Test for address comparator
        assertParseSuccess(parser, SORT_BY_ADDRESS_DESC + SORT_DESC_DESCENDING,
                new SortEmployeeCommand(EmployeeComparator.getAddressComparator(false),
                        "address", "descending"));

        // Test for email comparator
        assertParseSuccess(parser, SORT_BY_EMAIL_DESC + SORT_DESC_ASCENDING,
                new SortEmployeeCommand(EmployeeComparator.getEmailComparator(true),
                        "email", "ascending"));

        // Test for phone comparator
        assertParseSuccess(parser, SORT_BY_PHONE_DESC + SORT_DESC_DESCENDING,
                new SortEmployeeCommand(EmployeeComparator.getPhoneComparator(false),
                        "phone", "descending"));

        // Test for job title comparator
        assertParseSuccess(parser, SORT_BY_DESC_JOB_TITLE + SORT_DESC_ASCENDING,
                new SortEmployeeCommand(EmployeeComparator.getJobTitleComparator(true),
                        "job title", "ascending"));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortEmployeeCommand.MESSAGE_USAGE);

        // missing sort by prefix
        assertParseFailure(parser, VALID_SORT_BY_NAME + SORT_DESC_DESCENDING, expectedMessage);

        // missing sorting order prefix
        assertParseFailure(parser, SORT_BY_PHONE_DESC + VALID_SORT_ORDER_DESCENDING, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_SORT_BY_ADDRESS + VALID_SORT_ORDER_ASCENDING, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid sort by
        assertParseFailure(parser, INVALID_SORT_BY_SHIFT + SORT_DESC_ASCENDING,
                SortByEmployee.MESSAGE_CONSTRAINTS);

        // invalid sorting order
        assertParseFailure(parser, SORT_BY_EMAIL_DESC + INVALID_SORT_ORDER_DESC,
                SortOrder.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value will be reported
        assertParseFailure(parser, INVALID_SORT_BY_TAG + INVALID_SORT_ORDER_DESC,
                SortByEmployee.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + SORT_BY_ADDRESS_DESC + SORT_DESC_DESCENDING,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortEmployeeCommand.MESSAGE_USAGE));
    }
}
