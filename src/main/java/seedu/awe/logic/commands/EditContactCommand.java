package seedu.awe.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.awe.commons.core.Messages.MESSAGE_EDITCONTACTCOMMAND_CANNOT_BE_EDITED;
import static seedu.awe.commons.core.Messages.MESSAGE_EDITCONTACTCOMMAND_DUPLICATE_PERSON;
import static seedu.awe.commons.core.Messages.MESSAGE_EDITCONTACTCOMMAND_EDIT_PERSON_SUCCESS;
import static seedu.awe.commons.core.Messages.MESSAGE_EDITCONTACTCOMMAND_SAME_NAME;
import static seedu.awe.commons.core.Messages.MESSAGE_EDITCONTACTCOMMAND_SAME_NUMBER;
import static seedu.awe.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.awe.commons.core.Messages;
import seedu.awe.commons.core.index.Index;
import seedu.awe.commons.util.CollectionUtil;
import seedu.awe.logic.commands.exceptions.CommandException;
import seedu.awe.model.Model;
import seedu.awe.model.person.Name;
import seedu.awe.model.person.Person;
import seedu.awe.model.person.Phone;
import seedu.awe.model.tag.Tag;
import seedu.awe.ui.MainWindow;
import seedu.awe.ui.UiView;

/**
 * Edits the details of an existing person in the awe book.
 */
public class EditContactCommand extends Command {

    public static final String COMMAND_WORD = "editcontact";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditContactCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    /**
     * Returns CommandResult object with message representing successful edit of person.
     * If not a valid command, returns CommandResult object with error message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult object to depict status of edit operation.
     * @throws CommandException If model is null.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (MainWindow.getViewEnum() != UiView.CONTACT_PAGE) {
            throw new CommandException(MESSAGE_EDITCONTACTCOMMAND_CANNOT_BE_EDITED);
        }

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        if (editPersonDescriptor.getName().isPresent()
                && editPersonDescriptor.getName().get().equals(personToEdit.getName())) {
            throw new CommandException(MESSAGE_EDITCONTACTCOMMAND_SAME_NAME);
        }

        if (editPersonDescriptor.getPhone().isPresent()
                && editPersonDescriptor.getPhone().get().equals(personToEdit.getPhone())) {
            throw new CommandException(MESSAGE_EDITCONTACTCOMMAND_SAME_NUMBER);
        }

        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);


        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_EDITCONTACTCOMMAND_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDITCONTACTCOMMAND_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());

        return new Person(updatedName, updatedPhone, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditContactCommand)) {
            return false;
        }

        // state check
        EditContactCommand e = (EditContactCommand) other;
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
        private Set<Tag> tags;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, tags);
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
                    && getTags().equals(e.getTags());
        }
    }
}
