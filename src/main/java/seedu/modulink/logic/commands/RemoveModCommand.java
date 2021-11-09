package seedu.modulink.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_MOD;
import static seedu.modulink.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.modulink.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.modulink.logic.commands.exceptions.CommandException;
import seedu.modulink.model.Model;
import seedu.modulink.model.person.Email;
import seedu.modulink.model.person.GitHubUsername;
import seedu.modulink.model.person.Name;
import seedu.modulink.model.person.Person;
import seedu.modulink.model.person.Phone;
import seedu.modulink.model.person.StudentId;
import seedu.modulink.model.person.TelegramHandle;
import seedu.modulink.model.tag.Mod;

public class RemoveModCommand extends Command {
    public static final String COMMAND_WORD = "remMod";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes a module from your module list. "
            + "Remaining modules will not be affected.\n"
            + "Parameters: "
            + "[" + PREFIX_MOD + "MOD]\n"
            + "Example: " + COMMAND_WORD
            + " " + PREFIX_MOD + "CS2103T";

    public static final String MESSAGE_ADD_MODULE_SUCCESS = "Removed module: %1$s";
    public static final String MESSAGE_NO_CHANGE =
            "Please provide a module to remove with the \"mod/\" prefix.";
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

        Person myProfile = model.getProfile();

        if (!myProfile.getMods().containsAll(editPersonDescriptor.getTags().get())) {
            throw new CommandException(MESSAGE_MODULE_DOES_NOT_EXIST);
        }

        Person editedProfile = createEditedPerson(myProfile, editPersonDescriptor);

        model.setPerson(myProfile, editedProfile);
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
        GitHubUsername updatedGitHubUsername = editPersonDescriptor.getGitHubUsername()
                .orElse(personToEdit.getGithubUsername());
        TelegramHandle updatedTelegramHandle = editPersonDescriptor.getTelegramHandle()
                .orElse(personToEdit.getTelegramHandle());
        Set<Mod> updatedMods = new HashSet<>(Collections.emptySet());

        for (Mod mod : personToEdit.getMods()) {
            if (!editPersonDescriptor.getTags().get().contains(mod)) {
                updatedMods.add(mod);
            }
        }

        return new Person(updatedName, updatedId, updatedPhone, updatedEmail,
                updatedGitHubUsername, updatedTelegramHandle, false, updatedMods, true);
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
