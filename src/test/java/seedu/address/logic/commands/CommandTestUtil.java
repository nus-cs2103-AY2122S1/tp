package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALLERGIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALREQUESTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOBTITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEAVES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.customer.Customer;
import seedu.address.model.person.customer.CustomerNameContainsKeywordsPredicate;
import seedu.address.testutil.EditCustomerDescriptorBuilder;
import seedu.address.model.person.employee.Employee;
import seedu.address.model.person.employee.EmployeeNameContainsKeywordsPredicate;
import seedu.address.testutil.EditEmployeeDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_LP_AMY = "5000";
    public static final String VALID_LP_BOB = "9000";
    public static final String VALID_ALLERGY_NONSENSE = "Nonsense";
    public static final String VALID_ALLERGY_ALMONDS = "Almonds";
    public static final String VALID_ALLERGY_GRAPES = "Grapes";
    public static final String VALID_ALLERGY_GRAPEFRUITS = "Grapefruit";
    public static final String VALID_SPECIALREQUEST_OFFLIGHTS = "off lights";
    public static final String VALID_SPECIALREQUEST_SILENCE = "Silence";
    public static final String VALID_SPECIALREQUEST_ROCK = "Rock music";
    public static final String VALID_SPECIALREQUEST_LIVEBAND = "Live band";
    public static final String VALID_LEAVES_AMY = "2";
    public static final String VALID_LEAVES_BOB = "1";
    public static final String VALID_SALARY_AMY = "14000";
    public static final String VALID_SALARY_BOB = "5000";
    public static final String VALID_JOBTITLE_AMY = "Account Manager";
    public static final String VALID_JOBTITLE_BOB = "UIUX Designer";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
  
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String LP_DESC_AMY = " " + PREFIX_LP + VALID_LP_AMY;
    public static final String LP_DESC_BOB = " " + PREFIX_LP + VALID_LP_BOB;
    public static final String ALLERGY_DESC_NONSENSE = " " + PREFIX_ALLERGIES + VALID_ALLERGY_NONSENSE;
    public static final String ALLERGY_DESC_ALMONDS = " " + PREFIX_ALLERGIES + VALID_ALLERGY_ALMONDS;
    public static final String ALLERGY_DESC_GRAPES = " " + PREFIX_ALLERGIES + VALID_ALLERGY_GRAPES;
    public static final String ALLERGY_DESC_GRAPEFRUITS = " " + PREFIX_ALLERGIES + VALID_ALLERGY_GRAPEFRUITS;
    public static final String SPECIALREQUEST_DESC_OFFLIGHTS = " " + PREFIX_SPECIALREQUESTS
            + VALID_SPECIALREQUEST_OFFLIGHTS;
    public static final String SPECIALREQUEST_DESC_SILENCE = " " + PREFIX_SPECIALREQUESTS
            + VALID_SPECIALREQUEST_SILENCE;
    public static final String SPECIALREQUEST_DESC_ROCK = " " + PREFIX_SPECIALREQUESTS + VALID_SPECIALREQUEST_ROCK;
    public static final String SPECIALREQUEST_DESC_LIVEBAND = " " + PREFIX_SPECIALREQUESTS
            + VALID_SPECIALREQUEST_LIVEBAND;
    public static final String LEAVES_DESC_AMY = " " + PREFIX_LEAVES + VALID_LEAVES_AMY;
    public static final String LEAVES_DESC_BOB = " " + PREFIX_LEAVES + VALID_LEAVES_BOB;
    public static final String SALARY_DESC_AMY = " " + PREFIX_SALARY + VALID_SALARY_AMY;
    public static final String SALARY_DESC_BOB = " " + PREFIX_SALARY + VALID_SALARY_BOB;
    public static final String JOBTITLE_DESC_AMY = " " + PREFIX_JOBTITLE + VALID_JOBTITLE_AMY;
    public static final String JOBTITLE_DESC_BOB = " " + PREFIX_JOBTITLE + VALID_JOBTITLE_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_LP_DESC = " " + PREFIX_LP + "911a"; // 'a' not allowed in loyalty
    // points
    public static final String INVALID_ALLERGIES_DESC = " " + PREFIX_ALLERGIES + "!vvdv"; // '!' not allowed
    // symbol
    public static final String INVALID_SPECIALREQUESTS_DESC = " " + PREFIX_SPECIALREQUESTS + "!vdfv"; // '!'
    // not
    // allowed
    public static final String INVALID_LEAVES_DESC = " " + PREFIX_LEAVES + "abc"; // alphabets not allowed
    public static final String INVALID_SALARY_DESC = " " + PREFIX_SALARY + "abc"; //alphabets not allowed
    public static final String INVALID_JOBTITLE_DESC = " " + PREFIX_JOBTITLE + "Account Manager*"; // '*' not allowed
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditEmployeeCommand.EditEmployeeDescriptor DESC_EMPLOYEE_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;
    public static final EditCustomerCommand.EditCustomerDescriptor DESC_CUSTOMER_AMY;
    public static final EditCustomerCommand.EditCustomerDescriptor DESC_CUSTOMER_BOB;
    public static final EditEmployeeCommand.EditEmployeeDescriptor DESC_EMPLOYEE_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_CUSTOMER_AMY = new EditCustomerDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withLoyaltyPoints(VALID_LP_AMY)
                .withAllergies(VALID_ALLERGY_GRAPEFRUITS, VALID_ALLERGY_NONSENSE)
                .withSpecialRequests(VALID_SPECIALREQUEST_LIVEBAND, VALID_SPECIALREQUEST_ROCK)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_CUSTOMER_BOB = new EditCustomerDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withLoyaltyPoints(VALID_LP_BOB)
                .withAllergies(VALID_ALLERGY_ALMONDS).withSpecialRequests(VALID_SPECIALREQUEST_OFFLIGHTS)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_EMPLOYEE_AMY = new EditEmployeeDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).withLeaves(VALID_LEAVES_AMY).withSalary(VALID_SALARY_AMY)
                .withJobTitle(VALID_JOBTITLE_AMY).build();
        DESC_EMPLOYEE_BOB = new EditEmployeeDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withLeaves(VALID_LEAVES_BOB).withSalary(VALID_SALARY_BOB)
                .withJobTitle(VALID_JOBTITLE_BOB).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());
        List<Customer> expectedFilteredCustomerList = new ArrayList<>(actualModel.getFilteredCustomerList());
        List<Employee> expectedFilteredEmployees = new ArrayList<>(actualModel.getFilteredEmployeeList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());

        assertEquals(expectedFilteredCustomerList, actualModel.getFilteredCustomerList());
        assertEquals(expectedFilteredEmployees, actualModel.getFilteredEmployeeList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showCustomerAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredCustomerList().size());

        Customer customer = model.getFilteredCustomerList().get(targetIndex.getZeroBased());
        final String[] splitName = customer.getName().fullName.split("\\s+");
        model.updateFilteredCustomerList(new CustomerNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredCustomerList().size());
    }
  
    /**
     * Updates {@code model}'s filtered list to show only the employee at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showEmployeeAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredEmployeeList().size());

        Employee employee = model.getFilteredEmployeeList().get(targetIndex.getZeroBased());
        final String[] splitName = employee.getName().fullName.split("\\s+");
        model.updateFilteredEmployeeList(new EmployeeNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredEmployeeList().size());
    }
}
