package nustracker.logic.commands;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static nustracker.commons.core.Messages.MESSAGE_INVALID_STUDENTID;
import static nustracker.logic.parser.CliSyntax.PREFIX_EMAIL;
import static nustracker.logic.parser.CliSyntax.PREFIX_MAJOR;
import static nustracker.logic.parser.CliSyntax.PREFIX_NAME;
import static nustracker.logic.parser.CliSyntax.PREFIX_PHONE;
import static nustracker.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static nustracker.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import nustracker.commons.util.CollectionUtil;
import nustracker.logic.commands.exceptions.CommandException;
import nustracker.model.Model;
import nustracker.model.event.Event;
import nustracker.model.event.Participant;
import nustracker.model.student.Email;
import nustracker.model.student.EnrolledEvents;
import nustracker.model.student.Major;
import nustracker.model.student.Name;
import nustracker.model.student.Phone;
import nustracker.model.student.Student;
import nustracker.model.student.StudentId;
import nustracker.model.student.Year;
import nustracker.ui.MainWindow.CurrentlyShownList;

/**
 * Edits the details of an existing student in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the student identified "
            + "by the student's student ID. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: "
            + PREFIX_STUDENTID + "ID_OF_STUDENT_TO_EDIT "
            + "[" + PREFIX_NAME + "NEW_NAME] "
            + "[" + PREFIX_PHONE + "NEW_PHONE] "
            + "[" + PREFIX_EMAIL + "NEW_EMAIL] "
            + "[" + PREFIX_YEAR + "NEW_YEAR] "
            + "[" + PREFIX_MAJOR + "NEW_MAJOR] "
            + "[" + PREFIX_STUDENTID + "NEW_STUDENT_ID] "
            + "\n"
            + "Example: " + COMMAND_WORD
            + " " + PREFIX_STUDENTID + "e0322322 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com "
            + PREFIX_STUDENTID + "e0322323";

    public static final String MESSAGE_EDIT_STUDENT_SUCCESS = "Edited Student: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_STUDENT = "A student with %1$s already exists in the address book. \n"
            + "Please ensure that the Student ID, Phone and Email to edit to are all unique.";
    public static final String MESSAGE_EDIT_SHOULD_UPDATE_VALUES = "All fields being edited should be"
            + " updated to contain different values.";

    private final StudentId studentIdToEdit;
    private final EditStudentDescriptor editStudentDescriptor;

    /**
     * @param studentId the student ID of the student to be edited.
     * @param editStudentDescriptor details to edit the student with.
     */
    public EditCommand(StudentId studentId, EditStudentDescriptor editStudentDescriptor) {
        requireNonNull(studentId);
        requireNonNull(editStudentDescriptor);

        this.studentIdToEdit = studentId;
        this.editStudentDescriptor = new EditStudentDescriptor(editStudentDescriptor);
    }

    @Override
    public CommandResult execute(Model model,
                                 CurrentlyShownList currentlyShownList) throws CommandException {
        requireNonNull(model);

        Student studentToEdit = model.getStudent(studentIdToEdit);

        if (studentToEdit == null) {
            throw new CommandException(String.format(MESSAGE_INVALID_STUDENTID,
                    studentIdToEdit.getStudentIdString()));
        }

        Student editedStudent = createEditedStudent(studentToEdit, editStudentDescriptor);

        if (!editStudentDescriptor.isStudentDifferent(studentToEdit)) {
            throw new CommandException(MESSAGE_EDIT_SHOULD_UPDATE_VALUES);
        }

        Student studentWithSameId = model.getStudent(editedStudent.getStudentId());
        Student studentWithSamePhone = model.getStudentByPhone(editedStudent.getPhone());
        Student studentWithSameEmail = model.getStudentByEmail(editedStudent.getEmail());

        if (studentWithSameId != null && editStudentDescriptor.getStudentId().isPresent()) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_STUDENT, "the STUDENT ID "
                    + editedStudent.getStudentId().getStudentIdString()));
        }

        if (studentWithSamePhone != null && editStudentDescriptor.getPhone().isPresent()) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_STUDENT, "the PHONE "
                    + editedStudent.getPhone().toString()));
        }

        if (studentWithSameEmail != null && editStudentDescriptor.getEmail().isPresent()) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_STUDENT, "the EMAIL "
                    + editedStudent.getEmail().toString()));
        }


        editStudentIdInEventList(studentToEdit, editedStudent, model);

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent));
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code studentToEdit}
     * edited with {@code editStudentDescriptor}.
     */
    private static Student createEditedStudent(Student studentToEdit, EditStudentDescriptor editStudentDescriptor) {
        assert studentToEdit != null;

        Name updatedName = editStudentDescriptor.getName().orElse(studentToEdit.getName());
        Phone updatedPhone = editStudentDescriptor.getPhone().orElse(studentToEdit.getPhone());
        Email updatedEmail = editStudentDescriptor.getEmail().orElse(studentToEdit.getEmail());
        Year updatedYear = editStudentDescriptor.getYear().orElse(studentToEdit.getYear());
        Major updatedMajor = editStudentDescriptor.getMajor().orElse(studentToEdit.getMajor());
        StudentId updatedStudentId = editStudentDescriptor.getStudentId().orElse(studentToEdit.getStudentId());


        // Enrolled Events should not be updated using Edit Command
        EnrolledEvents notUpdatedEvents = studentToEdit.getEvents();

        return new Student(updatedName, updatedPhone, updatedEmail,
                updatedYear, updatedMajor, updatedStudentId, notUpdatedEvents);
    }

    /**
     * Externally looks like it edits the Student Ids of the Participant object in the Events that this Student
     * is enrolled in, if this Student's Student Id is being edited. Internally it deletes the old Event
     * and creates a new Event with the updated Student so that the ObservableList of Events would trigger
     * an update to GUI.
     *
     * @param studentToEdit the Student whose Student Id is being edited.
     * @param newStudent the new Student which has an edited Student Id.
     * @param model the Model that the Student is being updated in.
     */
    private void editStudentIdInEventList(Student studentToEdit, Student newStudent, Model model) {

        Optional<StudentId> studentIdFromDesc = editStudentDescriptor.getStudentId();
        StudentId editedStudentId = studentIdFromDesc.orElse(null);

        if (editedStudentId == null) {
            // This Edit Command does not edit the Student Id.
            return;
        }

        EnrolledEvents currEnrolledEvents = studentToEdit.getEvents();
        Set<Event> allEventSet = currEnrolledEvents.getAllEventsEnrolledIn(model);

        for (Event currEvent : allEventSet) {
            Set<Participant> currParticipants = currEvent.getParticipants();
            Set<Participant> newParticipants = new HashSet<>();

            for (Participant currParticipant : currParticipants) {
                if (currParticipant.getStudentId().equals(studentIdToEdit)) {
                    // Update Student Id by creating new Participant with the updated Id and adding to the Set
                    Participant updParticipant = new Participant(newStudent.getStudentId().getStudentIdString());

                    newParticipants.add(updParticipant);
                } else {
                    // Otherwise use the existing Participant object since they are not being modified.
                    newParticipants.add(currParticipant);
                }

            }

            Event newUpdatedEvent = currEvent.getNewEventWithUpdatedParticipants(newParticipants);

            model.setEvent(currEvent, newUpdatedEvent);

        }

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
        return studentIdToEdit.equals(e.studentIdToEdit)
                && editStudentDescriptor.equals(e.editStudentDescriptor);
    }

    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class EditStudentDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Year year;
        private Major major;
        private StudentId studentId;

        public EditStudentDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditStudentDescriptor(EditStudentDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setYear(toCopy.year);
            setMajor(toCopy.major);
            setStudentId(toCopy.studentId);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, major, year, studentId);
        }

        /**
         * Checks if a student already contains the same values that the fields being edited by this
         * descriptor contains.
         *
         * @param currStudent the student to check.
         * @return true if all fields being edited are different from what is in the student, false otherwise.
         */
        public boolean isStudentDifferent(Student currStudent) {
            return (isNull(name) || !currStudent.getName().equals(name))
                    && (isNull(phone) || !currStudent.getPhone().equals(phone))
                    && (isNull(email) || !currStudent.getEmail().equals(email))
                    && (isNull(year) || !currStudent.getYear().equals(year))
                    && (isNull(major) || !currStudent.getMajor().equals(major))
                    && (isNull(studentId) || !currStudent.getStudentId().equals(studentId));
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

        public void setYear(Year year) {
            this.year = year;
        }

        public Optional<Year> getYear() {
            return Optional.ofNullable(year);
        }

        public void setMajor(Major major) {
            this.major = major;
        }

        public Optional<Major> getMajor() {
            return Optional.ofNullable(major);
        }

        public void setStudentId(StudentId studentId) {
            this.studentId = studentId;
        }

        public Optional<StudentId> getStudentId() {
            return Optional.ofNullable(studentId);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditStudentDescriptor)) {
                return false;
            }

            // state check
            EditStudentDescriptor e = (EditStudentDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getYear().equals(e.getYear())
                    && getStudentId().equals(e.getStudentId())
                    && getMajor().equals(e.getMajor());
        }
    }
}
