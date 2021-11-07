package seedu.modulink.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modulink.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.modulink.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.modulink.logic.commands.exceptions.CommandException;
import seedu.modulink.logic.parser.exceptions.ParseException;
import seedu.modulink.model.Model;
import seedu.modulink.model.ModelManager;
import seedu.modulink.model.UserPrefs;
import seedu.modulink.model.person.Person;
import seedu.modulink.model.tag.Mod;
import seedu.modulink.model.tag.Status;
import seedu.modulink.testutil.EditPersonDescriptorBuilder;
import seedu.modulink.testutil.PersonBuilder;

public class EditGroupStatusCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    void execute_editGroupStatus_success() throws CommandException, ParseException {
        Person editedPerson = new PersonBuilder().withTags("CS2103T").buildProfile();
        model.addPerson(editedPerson);
        EditCommand.EditPersonDescriptor descriptor =
                new EditPersonDescriptorBuilder().withTags("CS2103T need member").build();
        EditGroupStatusCommand editGroupStatusCommand = new EditGroupStatusCommand(descriptor);
        editGroupStatusCommand.execute(model);
        assertTrue(model.getProfile().getMods().contains(new Mod("CS2103T need member")));
    }

    @Test
    void execute_editGroupStatusDoesNotRetainOriginalStatus_success() throws CommandException {
        Person editedPerson = new PersonBuilder().withTags("CS2103T").buildProfile();
        model.addPerson(editedPerson);
        EditCommand.EditPersonDescriptor descriptor =
                new EditPersonDescriptorBuilder().withTags("CS2103T need member").build();
        EditGroupStatusCommand editGroupStatusCommand = new EditGroupStatusCommand(descriptor);
        editGroupStatusCommand.execute(model);
        Mod moduleToTest = null;
        for (Mod mod : model.getProfile().getMods()) {
            if (mod.modName.equals("CS2103T")) {
                moduleToTest = mod;
            }
        }
        assertNotEquals(Status.NONE, moduleToTest.status);
    }

    @Test
    void execute_editGroupStatusDoesNotCreateCopyOfModule_success() throws CommandException {
        Person editedPerson = new PersonBuilder().withTags("CS2103T").buildProfile();
        model.addPerson(editedPerson);
        EditCommand.EditPersonDescriptor descriptor =
                new EditPersonDescriptorBuilder().withTags("CS2103T need member").build();
        EditGroupStatusCommand editGroupStatusCommand = new EditGroupStatusCommand(descriptor);
        editGroupStatusCommand.execute(model);
        int count = 0;
        for (Mod mod : model.getProfile().getMods()) {
            if (mod.modName.equals("CS2103T")) {
                count++;
            }
        }
        assertEquals(count, 1);
    }

    @Test
    public void execute_editGroupStatusWithNoStatusChange_failure() throws ParseException {
        Person editedPerson = new PersonBuilder().withTags("CS2103T").buildProfile();
        model.addPerson(editedPerson);
        EditCommand.EditPersonDescriptor descriptor =
                new EditPersonDescriptorBuilder().withTags("CS2103T").build();
        EditGroupStatusCommand editGroupStatusCommand = new EditGroupStatusCommand(descriptor);
        assertTrue(model.getProfile().getMods().contains(new Mod("CS2103T")));
        assertEquals(editedPerson, model.getProfile());
        assertCommandFailure(editGroupStatusCommand, model, EditGroupStatusCommand.MESSAGE_NO_STATUS_CHANGED);
    }

    @Test
    public void execute_editGroupStatusWhereModuleDoesNotExist_failure() throws ParseException {
        Person editedPerson = new PersonBuilder().buildProfile();
        model.addPerson(editedPerson);
        EditCommand.EditPersonDescriptor descriptor =
                new EditPersonDescriptorBuilder().withTags("CS2100").build();
        EditGroupStatusCommand editGroupStatusCommand = new EditGroupStatusCommand(descriptor);
        assertFalse(model.getProfile().getMods().contains(new Mod("CS2100")));
        assertCommandFailure(editGroupStatusCommand, model, EditGroupStatusCommand.MESSAGE_MODULE_DOES_NOT_EXIST);
    }


}
