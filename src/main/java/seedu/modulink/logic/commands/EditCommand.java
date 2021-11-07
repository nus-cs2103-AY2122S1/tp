package seedu.modulink.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_GITHUB_USERNAME;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_TELEGRAM_HANDLE;
import static seedu.modulink.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.modulink.commons.util.CollectionUtil;
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


/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of your profile.\n"
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_ID + "STUDENT ID] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_GITHUB_USERNAME + "GITHUB] "
            + "[" + PREFIX_TELEGRAM_HANDLE + "TELEGRAM]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com\n"
            + "Note: Module tags cannot be edited using this command.\n "
            + "To edit your module list, "
            + "please use the \"addMod\", \"remMod\", or \"editGroupStatus\" commands.";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited your profile: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_STUDENT_ID = "There is already a profile with this Student ID.";

    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(editPersonDescriptor);

        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person personToEdit = model.getProfile();
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (model.hasStudentIdNotProfile(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT_ID);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
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
        Set<Mod> updatedMods = personToEdit.getMods();

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
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private StudentId id;
        private Phone phone;
        private Email email;
        private GitHubUsername gitHubUsername;
        private TelegramHandle telegramHandle;
        private Set<Mod> mods;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code mods} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setStudentId(toCopy.id);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setGitHubUsername(toCopy.gitHubUsername);
            setTelegramHandle(toCopy.telegramHandle);
            setTags(toCopy.mods);
        }


        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, id, phone, email, gitHubUsername, telegramHandle, mods);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setStudentId(StudentId id) {
            this.id = id;
        }

        public Optional<StudentId> getStudentId() {
            return Optional.ofNullable(id);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setGitHubUsername(GitHubUsername gitHubUsername) {
            this.gitHubUsername = gitHubUsername;
        }

        public Optional<GitHubUsername> getGitHubUsername() {
            return Optional.ofNullable(gitHubUsername);
        }

        public void setTelegramHandle(TelegramHandle telegramHandle) {
            this.telegramHandle = telegramHandle;
        }

        public Optional<TelegramHandle> getTelegramHandle() {
            return Optional.ofNullable(telegramHandle);
        }

        /**
         * Sets {@code mods} to this object's {@code mods}.
         * A defensive copy of {@code mods} is used internally.
         */
        public void setTags(Set<Mod> mods) {
            this.mods = (mods != null) ? new HashSet<>(mods) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code mods} is null.
         */
        public Optional<Set<Mod>> getTags() {
            return (mods != null) ? Optional.of(Collections.unmodifiableSet(mods)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getName().equals(e.getName())
                    && getStudentId().equals(e.getStudentId())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getGitHubUsername().equals(e.getGitHubUsername())
                    && getTelegramHandle().equals(e.getTelegramHandle())
                    && getTags().equals(e.getTags());
        }
    }
}
