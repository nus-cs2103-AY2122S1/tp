package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NATIONALITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SOCIAL_HANDLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nationality;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.SocialHandle;
import seedu.address.model.person.TutorialGroup;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = "\n" + COMMAND_WORD
            + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list.\n\n"
            + "Parameters: INDEX PREFIX/VALUE [PREFIX/VALUE]...\n"
            + "PREFIX/VALUE can be any of the following:\n"
            + PREFIX_NAME + "NAME\n"
            + PREFIX_GENDER + "GENDER\n"
            + PREFIX_PHONE + "PHONE_NUMBER\n"
            + PREFIX_EMAIL + "EMAIL\n"
            + PREFIX_NATIONALITY + "NATIONALITY\n"
            + PREFIX_TUTORIAL_GROUP + "TUTORIAL_GROUP\n"
            + PREFIX_REMARK + "REMARK\n"
            + PREFIX_SOCIAL_HANDLE + "SOCIAL_HANDLE\n"
            + PREFIX_TAG + "TAG\n\n"
            + "Note:\n"
            + " - INDEX must be a positive integer.\n"
            + " - At least one field to edit must be provided.\n"
            + " - Existing values will be overwritten by the input values.\n\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT_INVALID_INDEX = String.format(
            MESSAGE_INVALID_COMMAND_FORMAT, "A valid index was not entered. \n%1$s");

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

    @Override
    public CommandResult execute(Model model) throws CommandException {
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
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Nationality updatedNationality = editPersonDescriptor.getNationality().orElse(personToEdit.getNationality());
        TutorialGroup updatedTutorialGroup = editPersonDescriptor.getTutorialGroup()
                .orElse(personToEdit.getTutorialGroup());
        Gender updatedGender = editPersonDescriptor.getGender()
                .orElse(personToEdit.getGender());
        Remark updatedRemark = editPersonDescriptor.getRemark().orElse(personToEdit.getRemark());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());
        Set<SocialHandle> updatedSocialHandles = updateSocialHandles(personToEdit, editPersonDescriptor);

        return new Person(updatedName, updatedPhone, updatedEmail, updatedNationality,
                updatedTutorialGroup, updatedGender, updatedRemark, updatedTags, updatedSocialHandles);
    }

    private static Set<SocialHandle> updateSocialHandles(
            Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        if (editPersonDescriptor.getSocialHandles().isPresent()) {
            if (editPersonDescriptor.getSocialHandles().get().isEmpty()) {
                return new HashSet<>(); // Clear all social handles entries
            }
        } else {
            return new HashSet<>(personToEdit.getSocialHandles());
        }
        Hashtable<String, SocialHandle> tmp = new Hashtable<>();
        // Add all original social handles to hashtable
        for (SocialHandle socialHandle : personToEdit.getSocialHandles()) {
            if (!socialHandle.platform.isEmpty() && !socialHandle.value.isEmpty()) {
                tmp.put(socialHandle.platform, socialHandle);
            }
        }
        // Add all new social handles to hashtable; Replace with new social handles if platform already exists
        for (SocialHandle socialHandle : editPersonDescriptor.getSocialHandles().get()) {
            if (socialHandle.platform.isEmpty()) {
                tmp.clear();
            }
            String value = socialHandle.value;
            if (value.isEmpty()) {
                tmp.remove(socialHandle.platform);
            } else {
                tmp.put(socialHandle.platform, socialHandle);
            }
        }
        return new HashSet<>(tmp.values());
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
        return index.equals(e.index)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Nationality nationality;
        private TutorialGroup tutorialGroup;
        private Gender gender;
        private Remark remark;
        private Set<Tag> tags;
        private Set<SocialHandle> socialHandles;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setNationality(toCopy.nationality);
            setTutorialGroup(toCopy.tutorialGroup);
            setGender(toCopy.gender);
            setRemark(toCopy.remark);
            setTags(toCopy.tags);
            setSocialHandles(toCopy.socialHandles);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, nationality, tutorialGroup,
                    gender, remark, tags, socialHandles);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
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

        public void setNationality(Nationality nationality) {
            this.nationality = nationality;
        }

        public Optional<Nationality> getNationality() {
            return Optional.ofNullable(nationality);
        }

        public void setTutorialGroup(TutorialGroup tutorialGroup) {
            this.tutorialGroup = tutorialGroup;
        }

        public Optional<TutorialGroup> getTutorialGroup() {
            return Optional.ofNullable(tutorialGroup);
        }

        public void setGender(Gender gender) {
            this.gender = gender;
        }

        public Optional<Gender> getGender() {
            return Optional.ofNullable(gender);
        }

        public void setRemark(Remark remark) {
            this.remark = remark;
        }

        public Optional<Remark> getRemark() {
            return Optional.ofNullable(remark);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
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
         * Sets {@code socialHandles} to this object's {@code socialHandles}.
         * A defensive copy of {@code socialHandles} is used internally.
         */
        public void setSocialHandles(Set<SocialHandle> socialHandles) {
            this.socialHandles = (socialHandles != null) ? new HashSet<>(socialHandles) : null;
        }

        /**
         * Returns an unmodifiable social handle set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code socialHandles} is null.
         */
        public Optional<Set<SocialHandle>> getSocialHandles() {
            return (socialHandles != null)
                    ? Optional.of(Collections.unmodifiableSet(socialHandles)) : Optional.empty();
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
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getNationality().equals(e.getNationality())
                    && getTutorialGroup().equals(e.getTutorialGroup())
                    && getGender().equals(e.getGender())
                    && getRemark().equals(e.getRemark())
                    && getTags().equals(e.getTags())
                    && getSocialHandles().equals(e.getSocialHandles());
        }
    }
}
