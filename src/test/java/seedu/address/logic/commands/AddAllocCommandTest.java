package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.ALLOC_DESCRIPTOR_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ALLOC_DESCRIPTOR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddAllocCommand.AllocDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Group;
import seedu.address.model.student.Student;
import seedu.address.testutil.AllocDescriptorBuilder;
import seedu.address.testutil.GroupBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with Model) and unit tests for AddAllocCommand.
 */
public class AddAllocCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private final Student simpleAmy = new PersonBuilder()
            .withName(VALID_NAME_AMY)
            .withId(VALID_ID_AMY).build();

    private final Group simpleGroup = new GroupBuilder()
            .withName(VALID_GROUP_TUTORIAL).build();

    @Test
    public void execute_addAllocByName_success() {
        AllocDescriptor allocDescriptor = new AllocDescriptorBuilder()
                .withName(VALID_NAME_AMY)
                .withGroup(VALID_GROUP_TUTORIAL).build();
        AddAllocCommand addAllocCommand = new AddAllocCommand(allocDescriptor);

        model.addStudent(simpleAmy);
        model.addGroup(simpleGroup);

        Student expectedAmy = new PersonBuilder(simpleAmy)
                .withGroups(VALID_GROUP_TUTORIAL).build();

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(simpleAmy, expectedAmy);

        String expectedMessage = String.format(AddAllocCommand.MESSAGE_SUCCESS, expectedAmy);

        assertCommandSuccess(addAllocCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addAllocById_success() {
        AllocDescriptor allocDescriptor = new AllocDescriptorBuilder()
                .withId(VALID_ID_AMY)
                .withGroup(VALID_GROUP_TUTORIAL).build();
        AddAllocCommand addAllocCommand = new AddAllocCommand(allocDescriptor);

        model.addStudent(simpleAmy);
        model.addGroup(simpleGroup);

        Student expectedAmy = new PersonBuilder(simpleAmy)
                .withGroups(VALID_GROUP_TUTORIAL).build();

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(simpleAmy, expectedAmy);

        String expectedMessage = String.format(AddAllocCommand.MESSAGE_SUCCESS, expectedAmy);

        assertCommandSuccess(addAllocCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonexistentGroup_failure() {
        AllocDescriptor allocDescriptor = new AllocDescriptorBuilder()
                .withName(VALID_NAME_AMY)
                .withGroup(VALID_GROUP_TUTORIAL).build();
        AddAllocCommand addAllocCommand = new AddAllocCommand(allocDescriptor);

        model.addStudent(simpleAmy);

        String expectedMessage = AddAllocCommand.MESSAGE_NONEXISTENT_GROUP;

        assertCommandFailure(addAllocCommand, model, expectedMessage);
    }

    @Test
    public void execute_nonexistentStudent_failure() {
        AllocDescriptor allocDescriptor = new AllocDescriptorBuilder()
                .withName(VALID_NAME_AMY)
                .withGroup(VALID_GROUP_TUTORIAL).build();
        AddAllocCommand addAllocCommand = new AddAllocCommand(allocDescriptor);

        model.addGroup(simpleGroup);

        String expectedMessage = AddAllocCommand.MESSAGE_NONEXISTENT_STUDENT;

        assertCommandFailure(addAllocCommand, model, expectedMessage);
    }

    @Test
    public void execute_duplicateStudentName_failure() {
        AllocDescriptor allocDescriptor = new AllocDescriptorBuilder()
                .withName(VALID_NAME_AMY)
                .withGroup(VALID_GROUP_TUTORIAL).build();
        AddAllocCommand addAllocCommand = new AddAllocCommand(allocDescriptor);

        Student duplicateAmy = new PersonBuilder()
                .withName(VALID_NAME_AMY)
                .withId(VALID_ID_BOB).build();

        model.addStudent(simpleAmy);
        model.addStudent(duplicateAmy);
        model.addGroup(simpleGroup);

        String expectedMessage = AddAllocCommand.MESSAGE_DUPLICATE_STUDENT_NAME;

        assertCommandFailure(addAllocCommand, model, expectedMessage);
    }

    @Test
    public void execute_duplicateStudent_failure() {
        AllocDescriptor allocDescriptor = new AllocDescriptorBuilder()
                .withName(VALID_NAME_AMY)
                .withGroup(VALID_GROUP_TUTORIAL).build();
        AddAllocCommand addAllocCommand = new AddAllocCommand(allocDescriptor);

        Group groupWithAmy = new GroupBuilder()
                .withName(VALID_GROUP_TUTORIAL)
                .withStudents(simpleAmy).build();

        model.addStudent(simpleAmy);
        model.addGroup(groupWithAmy);

        String expectedMessage = AddAllocCommand.MESSAGE_DUPLICATE_STUDENT;

        assertCommandFailure(addAllocCommand, model, expectedMessage);
    }

    @Test
    public void equal() {
        final AddAllocCommand standardCommand = new AddAllocCommand(ALLOC_DESCRIPTOR_AMY);

        // same values -> returns true
        AllocDescriptor copyDescriptor = new AllocDescriptor(ALLOC_DESCRIPTOR_AMY);
        AddAllocCommand commandWithSameValues = new AddAllocCommand(copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(standardCommand, null);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new AddAllocCommand(ALLOC_DESCRIPTOR_BOB));
    }
}
