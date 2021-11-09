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

public class AddModCommand extends Command {
    public static final String COMMAND_WORD = "addMod";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a module to your module list. "
            + "You may optionally add your grouping status. "
            + "Existing modules will not be overwritten.\n"
            + "Duplicate modules cannot be added.\n"
            + "Parameters: "
            + "[" + PREFIX_MOD + "MOD]\n"
            + "Example: \n- " + COMMAND_WORD
            + " " + PREFIX_MOD + "CS2103T\n- "
            + COMMAND_WORD + " " + PREFIX_MOD + "CS2100 need group";

    public static final String MESSAGE_ADD_MODULE_SUCCESS = "Added module: %1$s";
    public static final String MESSAGE_NO_CHANGE =
            "Please provide a module to add with the \"mod/\" prefix.";
    public static final String MESSAGE_DUPLICATE_MODULE = "You have already added this module to your list.\n"
            + "Please use the \"editGroupStatus\" command to change your grouping status.";


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

        Person myProfile = model.getProfile();

        Person editedProfile = createEditedPerson(myProfile, editPersonDescriptor);

        if (myProfile.equals(editedProfile)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }

        model.setPerson(myProfile, editedProfile);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_ADD_MODULE_SUCCESS, editPersonDescriptor.getTags().get()));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code myProfile}
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
        updatedMods.addAll(personToEdit.getMods());
        updatedMods.addAll(editPersonDescriptor.getTags().get());

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
        if (!(other instanceof AddModCommand)) {
            return false;
        }

        // state check
        AddModCommand amc = (AddModCommand) other;
        return editPersonDescriptor.equals(amc.editPersonDescriptor);
    }
}
