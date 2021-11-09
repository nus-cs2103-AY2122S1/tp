package seedu.modulink.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
import seedu.modulink.testutil.EditPersonDescriptorBuilder;
import seedu.modulink.testutil.PersonBuilder;

class RemoveModCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    void execute_removeMod_success() throws CommandException, ParseException {
        Person editedPerson = new PersonBuilder().withTags("CS2103T").buildProfile();
        model.addPerson(editedPerson);
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withTags("CS2103T").build();
        RemoveModCommand rmc = new RemoveModCommand(descriptor);
        rmc.execute(model);
        assertFalse(model.getProfile().getMods().contains(new Mod("CS2103T")));
    }

    @Test
    void execute_removeNonExistingMod_failure() {
        Person editedPerson = new PersonBuilder().buildProfile();
        model.addPerson(editedPerson);
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withTags("CS2103T").build();
        RemoveModCommand rmc = new RemoveModCommand(descriptor);
        assertCommandFailure(rmc, model, RemoveModCommand.MESSAGE_MODULE_DOES_NOT_EXIST);
    }
}
