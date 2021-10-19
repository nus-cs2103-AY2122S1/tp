package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.tag.Mod;

public class RemoveModCommand extends Command {
    public static final String COMMAND_WORD = "remMod";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes one or more modules from your module list. "
            + "Remaining modules will not be overwritten.\n"
            + "Parameters: "
            + "[" + PREFIX_MOD + "MOD]\n"
            + "Example: " + COMMAND_WORD
            + " " + PREFIX_MOD + "CS2103T";

    public static final String MESSAGE_ADD_MODULE_SUCCESS = "Removed module: %1$s";
    public static final String MESSAGE_NO_CHANGE = "At least one module must be provided.";
    public static final String MESSAGE_MODULE_DOES_NOT_EXIST =
            "You can only remove existing modules in your module list.";


    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * Constructor for RemoveModCommand.
     */
    public RemoveModCommand(EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(editPersonDescriptor);

        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person personToEdit = model.getProfile();

        if (!personToEdit.getMods().containsAll(editPersonDescriptor.getTags().get())) {
            throw new CommandException(MESSAGE_MODULE_DOES_NOT_EXIST);
        }

        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_ADD_MODULE_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        StudentId updatedId = editPersonDescriptor.getStudentId().orElse(personToEdit.getStudentId());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Set<Mod> updatedMods = new HashSet<>(Collections.emptySet());

        for (Mod mod : personToEdit.getMods()) {
            if (!editPersonDescriptor.getTags().get().contains(mod)) {
                updatedMods.add(mod);
            }
        }

        return new Person(updatedName, updatedId, updatedPhone, updatedEmail, false, updatedMods, true);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemoveModCommand)) {
            return false;
        }

        // state check
        RemoveModCommand rmc = (RemoveModCommand) other;
        return editPersonDescriptor.equals(rmc.editPersonDescriptor);
    }
}
