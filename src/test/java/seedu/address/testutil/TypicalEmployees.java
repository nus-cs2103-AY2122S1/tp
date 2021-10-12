package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOBTITLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOBTITLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEAVES_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEAVES_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.employee.Employee;

/**
 * A utility class containing a list of {@code Employee} objects to be used in tests.
 */
public class TypicalEmployees {

    public static final Employee ALICE_EMPLOYEE = new EmployeeBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withLeaves("14").withSalary("3000").withJobTitle("Team lead")
            .withTags("friends").build();
    public static final Employee BENSON_EMPLOYEE = new EmployeeBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withLeaves("13").withSalary("4000").withJobTitle("Second lead")
            .withTags("owesMoney", "friends").build();
    public static final Employee CARL_EMPLOYEE = new EmployeeBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street")
            .withLeaves("12").withSalary("5000").withJobTitle("Third lead")
            .build();
    public static final Employee DANIEL_EMPLOYEE = new EmployeeBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends")
            .withLeaves("11").withSalary("6000").withJobTitle("Fourth lead")
            .build();
    public static final Employee ELLE_EMPLOYEE = new EmployeeBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave")
            .withLeaves("10").withSalary("9000").withJobTitle("Product lead")
            .build();
    public static final Employee FIONA_EMPLOYEE = new EmployeeBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo")
            .withLeaves("9").withSalary("5000").withJobTitle("Project Manager")
            .build();
    public static final Employee GEORGE_EMPLOYEE = new EmployeeBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street")
            .withLeaves("8").withSalary("10000").withJobTitle("CEO")
            .build();

    // Manually added
    public static final Employee HOON_EMPLOYEE = new EmployeeBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india")
            .withLeaves("7").withSalary("7000").withJobTitle("Senior Developer")
            .build();
    public static final Employee IDA_EMPLOYEE = new EmployeeBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave")
            .withLeaves("6").withSalary("6000").withJobTitle("Senior Developer")
            .build();

    // Manually added - Employee's details found in {@code CommandTestUtil}
    public static final Employee AMY_EMPLOYEE = new EmployeeBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withTags(VALID_TAG_FRIEND)
            .withLeaves(VALID_LEAVES_AMY).withSalary(VALID_SALARY_AMY).withJobTitle(VALID_JOBTITLE_AMY)
            .build();
    public static final Employee BOB_EMPLOYEE = new EmployeeBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withLeaves(VALID_LEAVES_BOB).withSalary(VALID_SALARY_BOB).withJobTitle(VALID_JOBTITLE_BOB)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalEmployees() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical employees.
     */
    public static AddressBook getTypicalAddressBookEmployees() {
        AddressBook ab = new AddressBook();
        for (Employee employee : getTypicalEmployees()) {
            ab.addEmployee(employee);
        }
        return ab;
    }

    public static List<Employee> getTypicalEmployees() {
        return new ArrayList<>(Arrays.asList(ALICE_EMPLOYEE, BENSON_EMPLOYEE, CARL_EMPLOYEE,
                DANIEL_EMPLOYEE, ELLE_EMPLOYEE, FIONA_EMPLOYEE, GEORGE_EMPLOYEE));
    }
}
