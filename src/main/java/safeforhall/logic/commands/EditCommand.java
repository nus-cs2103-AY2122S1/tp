package safeforhall.logic.commands;

import static java.util.Objects.requireNonNull;
import static safeforhall.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import safeforhall.commons.core.Messages;
import safeforhall.commons.core.index.Index;
import safeforhall.commons.util.CollectionUtil;
import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.logic.parser.CliSyntax;
import safeforhall.model.Model;
import safeforhall.model.person.Email;
import safeforhall.model.person.Faculty;
import safeforhall.model.person.LastDate;
import safeforhall.model.person.Name;
import safeforhall.model.person.Person;
import safeforhall.model.person.Phone;
import safeforhall.model.person.Room;
import safeforhall.model.person.VaccStatus;


/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the residents identified "
            + "by the index numbers used in the displayed resident list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEXES (positive integers, separated by a space) "
            + "[" + CliSyntax.PREFIX_NAME + "NAME] "
            + "[" + CliSyntax.PREFIX_PHONE + "PHONE] "
            + "[" + CliSyntax.PREFIX_EMAIL + "EMAIL] "
            + "[" + CliSyntax.PREFIX_ROOM + "ROOM] "
            + "[" + CliSyntax.PREFIX_VACCSTATUS + "VACCINATION STATUS] "
            + "[" + CliSyntax.PREFIX_FACULTY + "FACULTY]"
            + "[" + CliSyntax.PREFIX_FETDATE + "LAST FET DATE] "
            + "[" + CliSyntax.PREFIX_COLLECTIONDATE + "LAST COLLECTION DATE] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + CliSyntax.PREFIX_PHONE + "91234567 "
            + CliSyntax.PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Residents: \n%1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This resident already exists in the address book.";

    private final ArrayList<Index> indexArray;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param indexArray Array of people in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(ArrayList<Index> indexArray, EditPersonDescriptor editPersonDescriptor) {
        for (Index index : indexArray) {
            requireNonNull(index);
        }
        requireNonNull(editPersonDescriptor);

        this.indexArray = indexArray;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        String editedResidents = "";
        int count = 0;

        for (Index targetIndex : indexArray) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Person personToEdit = lastShownList.get(targetIndex.getZeroBased());
            Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);
            if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
                throw new CommandException(MESSAGE_DUPLICATE_PERSON);
            }
            model.setPerson(personToEdit, editedPerson);
            editedResidents += ((count + 1) + ".\t" + personToEdit.getName() + "\n");
            count++;
        }
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedResidents));
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
        Room updatedRoom = editPersonDescriptor.getRoom().orElse(personToEdit.getRoom());
        VaccStatus updatedVaccStatus = editPersonDescriptor.getVaccStatus().orElse(personToEdit.getVaccStatus());
        Faculty updatedFaculty = editPersonDescriptor.getFaculty().orElse(personToEdit.getFaculty());
        LastDate updatedLastFetDate = editPersonDescriptor.getLastFetDate().orElse(personToEdit.getLastFetDate());
        LastDate updatedLastCollectionDate = editPersonDescriptor.getLastCollectionDate()
                .orElse(personToEdit.getLastCollectionDate());
        // Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());
        return new Person(updatedName, updatedRoom, updatedPhone, updatedEmail, updatedVaccStatus, updatedFaculty,
                updatedLastFetDate, updatedLastCollectionDate);
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
        return indexArray.equals(e.indexArray)
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
        private Room room;
        private VaccStatus vaccStatus;
        private Faculty faculty;
        private LastDate lastFetDate;
        private LastDate lastCollectionDate;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setRoom(toCopy.room);
            setVaccStatus(toCopy.vaccStatus);
            setFaculty(toCopy.faculty);
            setLastFetDate(toCopy.lastFetDate);
            setLastCollectionDate(toCopy.lastCollectionDate);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, room, vaccStatus, faculty,
                    lastFetDate, lastCollectionDate);
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

        public void setRoom(Room room) {
            this.room = room;
        }

        public Optional<Room> getRoom() {
            return Optional.ofNullable(room);
        }

        public void setVaccStatus(VaccStatus vaccStatus) {
            this.vaccStatus = vaccStatus;
        }

        public Optional<VaccStatus> getVaccStatus() {
            return Optional.ofNullable(vaccStatus);
        }

        public void setFaculty(Faculty faculty) {
            this.faculty = faculty;
        }

        public Optional<Faculty> getFaculty() {
            return Optional.ofNullable(faculty);
        }

        public void setLastFetDate(LastDate lastFetDate) {
            this.lastFetDate = lastFetDate;
        }

        public Optional<LastDate> getLastFetDate() {
            return Optional.ofNullable(lastFetDate);
        }

        public void setLastCollectionDate(LastDate lastCollectionDate) {
            this.lastCollectionDate = lastCollectionDate;
        }

        public Optional<LastDate> getLastCollectionDate() {
            return Optional.ofNullable(lastCollectionDate);
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
                    && getRoom().equals(e.getRoom())
                    && getVaccStatus().equals(e.getVaccStatus())
                    && getFaculty().equals(e.getFaculty())
                    && getLastFetDate().equals(e.getLastFetDate())
                    && getLastCollectionDate().equals(e.getLastCollectionDate());
        }
    }
}
