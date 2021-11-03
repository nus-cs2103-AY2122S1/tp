package seedu.modulink.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_MOD;
import static seedu.modulink.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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

public class EditGroupStatusCommand extends Command {

    public static final String COMMAND_WORD = "editGroupStatus";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the group status of the specified module. "
            + "To change the grouping status to 'don't need group/not looking for group', "
            + "please leave the description blank\n"
            + "Parameters: "
            + PREFIX_MOD + "MOD \n"
            + "Example: \n- " + COMMAND_WORD + " " + PREFIX_MOD + "CS2100 need group\n"
            + "- " + COMMAND_WORD + " " + PREFIX_MOD + "CS2101 need member\n";

    public static final String MESSAGE_EDIT_GROUP_STATUS_SUCCESS = "Group status changed to: %1$s";
    public static final String MESSAGE_MODULE_DOES_NOT_EXIST = "The specified module does not exist on your profile. "
            + "To add the module to your profile, use the addMod command.";
    public static final String MESSAGE_NO_MODULE_SPECIFIED = "One module must be provided.";
    public static final String MESSAGE_MULTIPLE_MODULES_SPECIFIED = "Only one module must be provided.";
    public static final String MESSAGE_NO_STATUS_CHANGED = "The status specified is the same "
            + "as what currently exists for the specified module.";


    private final EditCommand.EditPersonDescriptor editPersonDescriptor;

    /**
     * Constructor.
     */
    public EditGroupStatusCommand(EditCommand.EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(editPersonDescriptor);

        this.editPersonDescriptor = new EditCommand.EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person myProfile = model.getProfile();

        for (Mod mod : myProfile.getMods()) {
            for (Mod toEditModule : editPersonDescriptor.getTags().get()) {
                if (toEditModule.equals(mod)) {
                    if (toEditModule.equalsStatus(mod)) {
                        throw new CommandException(MESSAGE_NO_STATUS_CHANGED);
                    }
                }
            }
        }

        Person editedProfile = createEditedPerson(myProfile, editPersonDescriptor);

        if (!myProfile.getMods().containsAll(editPersonDescriptor.getTags().get())) {
            throw new CommandException(MESSAGE_MODULE_DOES_NOT_EXIST);
        }

        model.setPerson(myProfile, editedProfile);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_GROUP_STATUS_SUCCESS,
                editPersonDescriptor.getTags().get()));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code myProfile}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit,
                                             EditCommand.EditPersonDescriptor editPersonDescriptor) {
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
        if (!(other instanceof EditGroupStatusCommand)) {
            return false;
        }

        // state check
        EditGroupStatusCommand egsc = (EditGroupStatusCommand) other;
        return editPersonDescriptor.equals(egsc.editPersonDescriptor);
    }
}
