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

public class AddModCommand extends Command {
    public static final String COMMAND_WORD = "addMod";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds one or more modules to your module list. "
            + "You may optionally add your grouping status."
            + "Existing modules will not be overwritten.\n"
            + "Duplicate modules cannot be added.\n"
            + "Parameters: "
            + "[" + PREFIX_MOD + "MOD]...\n"
            + "Example: \n- " + COMMAND_WORD
            + " " + PREFIX_MOD + "CS2103T\n"
            + COMMAND_WORD + " " + PREFIX_MOD + "CS2100 need group"
            + " " + PREFIX_MOD + "CS1101S need member\n";

    public static final String MESSAGE_ADD_MODULE_SUCCESS = "Added module: %1$s";
    public static final String MESSAGE_NO_CHANGE = "At least one module must be provided.";
    public static final String MESSAGE_DUPLICATE_MODULE = "You have already added this module to your list.\n"
            + "Please use the \"editGroupStatus\" keyword to change your grouping status.";

    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * Constructor.
     */
    public AddModCommand(EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(editPersonDescriptor);

        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person personToEdit = model.getProfile();

        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (personToEdit.equals(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_ADD_MODULE_SUCCESS, editPersonDescriptor.getTags().get()));
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
        updatedMods.addAll(personToEdit.getMods());
        updatedMods.addAll(editPersonDescriptor.getTags().get());

        return new Person(updatedName, updatedId, updatedPhone, updatedEmail, false, updatedMods, true);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddModCommand)) {
            return false;
        }

        // state check
        AddModCommand amc = (AddModCommand) other;
        return editPersonDescriptor.equals(amc.editPersonDescriptor);
    }
}
