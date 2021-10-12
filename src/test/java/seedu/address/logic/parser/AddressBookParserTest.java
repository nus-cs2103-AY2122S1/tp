package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddCustomerCommand;
import seedu.address.logic.commands.AddEmployeeCommand;
import seedu.address.logic.commands.CheckCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteEmployeeCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.EditEmployeeCommand;
import seedu.address.logic.commands.EditEmployeeCommand.EditEmployeeDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ReserveCommand;
import seedu.address.logic.parser.enums.EnumTypeOfCheck;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.customer.Customer;
import seedu.address.model.person.employee.Employee;
import seedu.address.model.reservation.ListContainsReservationPredicate;
import seedu.address.testutil.CustomerBuilder;
import seedu.address.testutil.CustomerUtil;
import seedu.address.testutil.EditEmployeeDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.EmployeeBuilder;
import seedu.address.testutil.EmployeeUtil;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_addcustomer() throws Exception {
        Customer customer = new CustomerBuilder().build();
        String temp = CustomerUtil.getAddCustomerCommand(customer);
        AddCustomerCommand command =
                (AddCustomerCommand) parser.parseCommand(CustomerUtil.getAddCustomerCommand(customer));
        assertEquals(new AddCustomerCommand(customer), command);
    }
    @Test
    public void parseCommand_addemployee() throws Exception {
        Employee employee = new EmployeeBuilder().build();
        AddEmployeeCommand command = (AddEmployeeCommand) parser.parseCommand(EmployeeUtil
                .getAddEmployeeCommand(employee));
        assertEquals(new AddEmployeeCommand(employee), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_deleteemployee() throws Exception {
        DeleteEmployeeCommand command = (DeleteEmployeeCommand) parser.parseCommand(
                DeleteEmployeeCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteEmployeeCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        String temp = EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor);
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    /**@Test
    public void parseCommand_editCustomer() throws Exception {
        Customer customer = new CustomerBuilder().build();
        EditCustomerDescriptor descriptor = new EditCustomerDescriptorBuilder(customer).build();
        EditCustomerCommand command =
                (EditCustomerCommand) parser.parseCommand(EditCustomerCommand.COMMAND_WORD + " "
                + INDEX_FIRST_CUSTOMER.getOneBased() + " " + CustomerUtil.getEditCustomerDescriptorDetails(descriptor));
        assertEquals(new EditCustomerCommand(INDEX_FIRST_CUSTOMER, descriptor), command);
    }*/

    @Test
    public void parseCommand_editemployee() throws Exception {
        Employee employee = new EmployeeBuilder().build();
        EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder(employee).build();
        EditEmployeeCommand command = (EditEmployeeCommand) parser.parseCommand(EditEmployeeCommand.COMMAND_WORD
            + " " + INDEX_FIRST_PERSON.getOneBased() + " " + EmployeeUtil.getEditEmployeeDescriptorDetails(descriptor));
        assertEquals(new EditEmployeeCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_check() throws Exception {
        String dateString = "2021-10-10";
        String timeString = "1900";
        LocalDate date = LocalDate.parse(dateString);
        LocalTime time = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HHmm"));
        EnumTypeOfCheck typeOfCheck = EnumTypeOfCheck.DateTime;

        CheckCommand command = (CheckCommand) parser.parseCommand(
                CheckCommand.COMMAND_WORD + " " + dateString + " " + timeString);
        assertEquals(new CheckCommand(new ListContainsReservationPredicate(date, time, typeOfCheck)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_reserve() throws Exception {
        assertTrue(parser.parseCommand("reserve 2 p/98765432 at/2021-11-11 2030") instanceof ReserveCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () ->
                parser.parseCommand("unknownCommand"));
    }
}
