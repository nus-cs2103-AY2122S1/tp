package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_RECITATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddAllocCommand.AllocDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Group;
import seedu.address.model.student.Student;
import seedu.address.testutil.AllocDescriptorBuilder;
import seedu.address.testutil.GroupBuilder;
import seedu.address.testutil.PersonBuilder;

public class AddGroupCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final String NEW_GROUP = "X01X";

    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddGroupCommand(null, new ArrayList<>()));
    }

    @Test
    public void constructor_nullDescriptorList_throwsNullPointerException() {
        Group validGroup = new GroupBuilder().build();
        assertThrows(NullPointerException.class, () -> new AddGroupCommand(validGroup, null));
    }

    @Test
    public void execute_validGroupWithoutStudents_addSuccessful() throws Exception {
        Group groupToAdd = new GroupBuilder().withName(NEW_GROUP).build();
        AddGroupCommand command = new AddGroupCommand(groupToAdd, new ArrayList<>());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.addGroup(groupToAdd);

        assertCommandSuccess(command, model,
                String.format(AddGroupCommand.MESSAGE_SUCCESS, NEW_GROUP), expectedModel);
    }

    @Test
    public void execute_validGroupWithStudent_addSuccessful() throws Exception {
        // Student should already exist in the application
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        model.addStudent(AMY);
        expectedModel.addStudent(AMY);

        Group groupToAdd = new GroupBuilder().withName(NEW_GROUP).build();
        AllocDescriptor amyNameDescriptor = new AllocDescriptorBuilder()
                .withGroup(NEW_GROUP)
                .withName(VALID_NAME_AMY).build();
        AddGroupCommand command = new AddGroupCommand(groupToAdd, Arrays.asList(amyNameDescriptor));

        // Expected model should have the new group with the student allocated to it
        Group completeGroup =  new GroupBuilder().withName(NEW_GROUP).withStudents(AMY).build();
        expectedModel.addGroup(completeGroup);

        // Expected model's student should have the new group
        List<Group> newGroups = new ArrayList<>(AMY.getGroups());
        newGroups.add(completeGroup);
        expectedModel.setStudent(AMY,
                new Student(AMY.getName(), AMY.getId(), newGroups, AMY.getScores(), AMY.getTags()));

        assertCommandSuccess(command, model,
                String.format(AddGroupCommand.MESSAGE_SUCCESS, NEW_GROUP), expectedModel);
    }

    @Test
    public void execute_duplicateGroup_throwsCommandException() {
        Group existingGroup;
        if (model.getAddressBook().getGroupList().size() > 0) {
            existingGroup = model.getAddressBook().getGroupList().get(0);
        } else {
            existingGroup = new GroupBuilder().build();
            model.addGroup(existingGroup);
        }

        AddGroupCommand command = new AddGroupCommand(existingGroup, new ArrayList<>());
        assertThrows(CommandException.class, AddGroupCommand.MESSAGE_DUPLICATE_GROUP, () -> command.execute(model));
    }

    @Test
    public void execute_nonExistentStudentName_throwsCommandException() {
        Group groupToAdd = new GroupBuilder().withName(NEW_GROUP).build();
        String randomName = "ZXC987";
        AllocDescriptor descriptorForNonExistentStudent = new AllocDescriptorBuilder()
                .withGroup(NEW_GROUP)
                .withName(randomName).build();
        AddGroupCommand command = new AddGroupCommand(groupToAdd, Arrays.asList(descriptorForNonExistentStudent));

        assertThrows(CommandException.class, String.format(AddGroupCommand.MESSAGE_NONEXISTENT_STUDENT, randomName),
                () -> command.execute(model));
    }

    @Test
    public void execute_nonExistentId_throwsCommandException() {
        Group groupToAdd = new GroupBuilder().withName(NEW_GROUP).build();
        String randomId = "E9090909";
        AllocDescriptor descriptorForNonExistentStudent = new AllocDescriptorBuilder()
                .withGroup(NEW_GROUP)
                .withName(randomId).build();

        AddGroupCommand command = new AddGroupCommand(groupToAdd, Arrays.asList(descriptorForNonExistentStudent));

        assertThrows(CommandException.class, String.format(AddGroupCommand.MESSAGE_NONEXISTENT_STUDENT, randomId),
                () -> command.execute(model));
    }

    @Test
    public void execute_allocatingSameStudentTwice_throwsCommandException() {
        // Student should already exist in the application
        model.addStudent(AMY);

        Group groupToAdd = new GroupBuilder().withName(NEW_GROUP).build();
        AllocDescriptor amyNameDescriptor = new AllocDescriptorBuilder()
                .withGroup(NEW_GROUP)
                .withName(VALID_NAME_AMY).build();
        AllocDescriptor amyIdDescriptor = new AllocDescriptorBuilder()
                .withGroup(NEW_GROUP)
                .withId(VALID_ID_AMY).build();

        AddGroupCommand command = new AddGroupCommand(groupToAdd, Arrays.asList(amyNameDescriptor, amyIdDescriptor));

        assertThrows(CommandException.class, String.format(AddGroupCommand.MESSAGE_DUPLICATE_STUDENT_IN_GROUP, AMY.getName()), () -> command.execute(model));
    }

    @Test void execute_ambiguousStudentName_throwsCommandException() {
        // Two students named John Doe (with different IDs) exist in the application
        final String duplicatedName = "John Doe";
        Student firstJohn = new PersonBuilder().withName(duplicatedName).withId("E9090909").build();
        Student secondJohn = new PersonBuilder(firstJohn).withId("E9191919").build();
        model.addStudent(firstJohn);
        model.addStudent(secondJohn);

        Group groupToAdd = new GroupBuilder().withName(NEW_GROUP).build();
        AllocDescriptor johnDoeNameDescriptor = new AllocDescriptorBuilder()
                .withGroup(NEW_GROUP)
                .withName(duplicatedName).build();

        AddGroupCommand command = new AddGroupCommand(groupToAdd, Arrays.asList(johnDoeNameDescriptor));

        assertThrows(CommandException.class, String.format(AddGroupCommand.MESSAGE_DUPLICATE_STUDENT, duplicatedName), () -> command.execute(model));
    }

    @Test
    public void equals() {
        Group standardGroup = new GroupBuilder().withName(VALID_GROUP_TUTORIAL).build();
        AllocDescriptor standardDescriptor = new AllocDescriptorBuilder()
                .withGroup(VALID_GROUP_TUTORIAL)
                .withName(VALID_NAME_AMY).build();

        final AddGroupCommand standardCommand = new AddGroupCommand(standardGroup, Arrays.asList(standardDescriptor));

        // same values -> returns true
        AllocDescriptor copyDescriptor = new AllocDescriptorBuilder(standardDescriptor).build();
        Group copyGroup = new GroupBuilder(standardGroup).build();
        AddGroupCommand commandWithSameValues = new AddGroupCommand(copyGroup, Arrays.asList(copyDescriptor));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different group -> returns false
        Group differentGroup = new GroupBuilder().withName(VALID_GROUP_RECITATION).build();
        assertFalse(standardCommand.equals(new AddGroupCommand(differentGroup, Arrays.asList(standardDescriptor))));

        // different descriptor -> returns false
        AllocDescriptor differentDescriptor = new AllocDescriptorBuilder()
                .withGroup(VALID_GROUP_RECITATION)
                .withName(VALID_NAME_BOB).build();
        assertFalse(standardCommand.equals(new AddGroupCommand(standardGroup, Arrays.asList(differentDescriptor))));
    }
}
