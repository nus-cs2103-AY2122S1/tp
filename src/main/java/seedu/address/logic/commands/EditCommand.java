package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Github;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Telegram;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.UserProfileWatcher;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: <INDEX> (must be a positive integer) "
            + "[" + PREFIX_NAME + "<NAME>] "
            + "[" + PREFIX_TELEGRAM + "<TELEGRAM>] "
            + "[" + PREFIX_GITHUB + "<GITHUB>] "
            + "[" + PREFIX_PHONE + "<PHONE>] "
            + "[" + PREFIX_EMAIL + "<EMAIL>] "
            + "[" + PREFIX_ADDRESS + "<ADDRESS>] "
            + "[" + PREFIX_TAG + "<TAG>]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_EDIT_PROFILE_SUCCESS = "Successfully edited profile!";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    public static final String MESSAGE_EDIT_PROFILE_DESCRIPTION_CANNOT_BE_EMPTY = "Edit profile description "
            + "cannot be empty!";
    public static final String MESSAGE_EDIT_PROFILE_PARAMETERS_CANNOT_BE_EMPTY = "Name, Telegram and GitHub "
            + "parameters cannot be empty!";
    public static final String MESSAGE_EDIT_PROFILE_NAME_CANNOT_BE_EMPTY = "Name to be edited cannot be empty!";
    public static final String MESSAGE_EDIT_PROFILE_TELEGRAM_CANNOT_BE_EMPTY = "Telegram handle to be edited "
            + "cannot be empty!";
    public static final String MESSAGE_EDIT_PROFILE_GITHUB_CANNOT_BE_EMPTY = "GitHub username to be edited "
            + "cannot be empty!";

    private static List<UserProfileWatcher> userProfileWatchers = new ArrayList<>();

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    /**
     * Executes the command to edit user's profile.
     *
     * @param model used to retrieve the user profile.
     * @return CommandResult holds the outcome of this method.
     */
    public CommandResult executeEditProfile(Model model) {
        requireNonNull(model);
        Person currentProfile = model.getUserProfile();
        Person editedProfile = createEditedPerson(currentProfile, editPersonDescriptor);
        model.setUserProfile(editedProfile);
        notifyUserProfileWatchers();
        return new CommandResult(String.format(MESSAGE_EDIT_PROFILE_SUCCESS, editedProfile));
    }

    /**
     * Adds a {@code UserProfileWatcher} Component to the
     * User Profile Watcher List.
     *
     * @param userProfileWatcher The Component to be added to the Watcher List.
     */
    public static void addUserProfileWatcher(UserProfileWatcher userProfileWatcher) {
        userProfileWatchers.add(userProfileWatcher);
    }

    /**
     * Notifies all the {@code UserProfileWatcher} Components in the Watcher List
     * about the changes in the User Profile Credentials.
     */
    public void notifyUserProfileWatchers() {
        if (userProfileWatchers.size() == 0) {
            return;
        }

        for (UserProfileWatcher userProfileWatcher : userProfileWatchers) {
            userProfileWatcher.updateUserProfile();
        }
    }

    /**
     * This method attempts to edit the details of an existing contact.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult which holds the outcome of this method.
     * @throws CommandException if there are any errors during execution.
     */
    public CommandResult executeEditContact(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        if (model.getPersonListControl() != null) {
            model.setSelectedIndex(model.getFilteredPersonList().indexOf(editedPerson));
            model.getPersonListControl().refreshPersonListUI();
        }
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * This method determines if the editing command is meant to
     * edit the profile or edit a contact.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult which holds the outcome of this method.
     * @throws CommandException if there are any errors during execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (editPersonDescriptor.getIsProfile()) {
            return executeEditProfile(model);
        }
        return executeEditContact(model);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     *
     * @param editPersonDescriptor details to edit the person with.
     * @param personToEdit is the person to edit.
     * @return Person object of the edited person.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Telegram updatedTelegram = editPersonDescriptor.getTelegram().orElse(personToEdit.getTelegram());
        Github updatedGithub = editPersonDescriptor.getGithub().orElse(personToEdit.getGithub());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());
        boolean updatedIsFavorite = editPersonDescriptor.getIsFavorite().orElse(personToEdit.isFavorite());

        return new Person(updatedName, updatedTelegram, updatedGithub,
                updatedPhone, updatedEmail, updatedAddress, updatedTags, updatedIsFavorite);
    }

    /**
     * Method to compare two EditCommand objects.
     *
     * @param other is the object that is going to be compared
     *              to the EditCommand object that called this method.
     * @return boolean representation of whether the EditCommand
     * object is equal to the other object passed as parameter.
     */
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
        return index.equals(e.index)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Telegram telegram;
        private Github github;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;
        private boolean isFavorite;
        private boolean isProfile;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         *
         * @param toCopy contains details of person to be edited.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setTelegram(toCopy.telegram);
            setGithub(toCopy.github);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
            setIsFavorite(toCopy.isFavorite);
            setIsProfile(toCopy.isProfile);
        }

        /**
         * Returns true if at least one field is edited.
         *
         * @return Boolean representation of whether either of
         * the fields have been edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, telegram, github, phone, email, address, tags);
        }

        public void setIsProfile(boolean isProfile) {
            this.isProfile = isProfile;
        }

        public boolean getIsProfile() {
            return isProfile;
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setTelegram(Telegram telegram) {
            this.telegram = telegram;
        }

        public Optional<Telegram> getTelegram() {
            return Optional.ofNullable(telegram);
        }

        public void setGithub(Github github) {
            this.github = github;
        }

        public Optional<Github> getGithub() {
            return Optional.ofNullable(github);
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

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setIsFavorite(boolean isFavorite) {
            this.isFavorite = isFavorite;
        }

        public Optional<Boolean> getIsFavorite() {
            return Optional.ofNullable(isFavorite);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         *
         * @param tags to be set.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        /**
         * Method to compare two EditPersonDescriptor objects.
         *
         * @param other is the object that is going to be compared
         *              to the EditPersonDescriptor object that called this method.
         * @return boolean representation of whether the EditPersonDescriptor
         * object is equal to the other object passed as parameter.
         */
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
                    && getTelegram().equals(e.getTelegram())
                    && getGithub().equals(e.getGithub())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags())
                    && getIsFavorite().equals(e.getIsFavorite());
        }
    }
}
