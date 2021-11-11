package nustracker.model;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javafx.collections.ObservableList;
import nustracker.logic.commands.CommandResult;
import nustracker.logic.commands.RemoveCommand;
import nustracker.logic.commands.exceptions.CommandException;
import nustracker.model.event.Event;
import nustracker.model.event.EventName;
import nustracker.model.event.Participant;
import nustracker.model.event.UniqueEventList;
import nustracker.model.student.Email;
import nustracker.model.student.EnrolledEvents;
import nustracker.model.student.Phone;
import nustracker.model.student.Student;
import nustracker.model.student.StudentId;
import nustracker.model.student.UniqueStudentList;
import nustracker.ui.MainWindow.CurrentlyShownList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameStudent comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueStudentList students;
    private final UniqueEventList events;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        students = new UniqueStudentList();
        events = new UniqueEventList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Students and Events in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Replaces the contents of the event list with {@code events}.
     * {@code events} must not contain duplicate persons.
     */
    public void setEvents(List<Event> events) {
        this.events.setEvents(events);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setStudents(newData.getStudentList());
        setEvents(newData.getEventList());
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in the address book.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Returns true if an event with the same identity as {@code event} exists in the address book.
     */
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return events.contains(event);
    }

    /**
     * Adds a student to the address book.
     * The student must not already exist in the address book.
     */
    public void addStudent(Student p) {
        students.add(p);
    }

    /**
     * Adds an event to the address book.
     * The event must not already exist in the address book.
     */
    public void addEvent(Event e) {
        events.add(e);
    }

    /**
     * Gets a student from the address book by his/her student ID.
     *
     * @param studentId the relevant student ID.
     * @return the {@code Student} with this studentId, null if that student does not exist.
     */
    public Student getStudent(StudentId studentId) {
        return students.get(studentId);
    }

    /**
     * Gets a Student by his/her Phone.
     *
     * @param phone The corresponding student's phone.
     * @return The student with this Phone number, null if that student does not exist.
     */
    public Student getStudentByPhone(Phone phone) {
        return students.getByPhone(phone);
    }

    /**
     * Gets a Student by his/her Email.
     *
     * @param email The corresponding student's email.
     * @return The student with this Email, null if that student does not exist.
     */
    public Student getStudentByEmail(Email email) {
        return students.getByEmail(email);
    }

    /**
     * Gets an event from the address book by its name.
     * Returns null if that event does not exist.
     */
    public Event getEvent(EventName name) {
        return events.get(name);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the address book.
     * The student identity of {@code editedStudent} must not be the same as another existing student
     * in the address book.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        students.setStudent(target, editedStudent);
    }

    /**
     * Replaces the given event {@code target} in the list with {@code editedEvent}.
     * {@code target} must exist in the address book.
     * The event identity of {@code editedEvent} must not be the same as another existing event in the address book.
     */
    public void setEvent(Event target, Event editedEvent) {
        requireNonNull(editedEvent);

        events.setEvent(target, editedEvent);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeStudent(Student key, Model currModel) {
        // Before removing the student, we remove the student from all their enrolled events first.
        EnrolledEvents enrolledEvents = key.getEvents();
        Set<Event> upToDateEventSet = enrolledEvents.getAllEventsEnrolledIn(currModel);
        StudentId currStudentId = key.getStudentId();

        for (Event currEvent : upToDateEventSet) {

            RemoveCommand currRemoveCmd = new RemoveCommand(currStudentId, currEvent.getName());

            try {
                CommandResult currCmdResult = currRemoveCmd.execute(currModel, CurrentlyShownList.STUDENTS_LIST);
            } catch (CommandException e) {
                // Means either Invalid Student ID (Not possible)
                // or invalid event name (Not possible)
                // or the student does not have this event in its EnrolledEvents (Not Possible)
            }
        }

        // Key does not exist in model anymore since it was modified when being removed from events
        Student newKey = currModel.getStudent(currStudentId);

        students.remove(newKey);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeEvent(Event key, Model currModel) {
        // Before removing the event, we remove all students from this event first.
        Set<Participant> participantsOfThisEvent = key.getParticipants();
        Set<StudentId> studentIdInThisEvent = new HashSet<>();

        for (Participant currParticipant : participantsOfThisEvent) {
            studentIdInThisEvent.add(currParticipant.getStudentId());
        }

        for (StudentId currStudentId : studentIdInThisEvent) {

            // Use a RemoveCommand to do this because it does exactly what we want which is to
            // remove the Event from the Student's EnrolledEvents (while also removing from Event's Participants)
            RemoveCommand currRemoveCmd = new RemoveCommand(currStudentId, key.getName());

            try {
                CommandResult currCmdResult = currRemoveCmd.execute(currModel,
                        CurrentlyShownList.STUDENTS_LIST);
            } catch (CommandException e) {
                // Means either Invalid Student ID (Not possible)
                // or invalid event name (Not possible)
                // or the student does not have this event in its EnrolledEvents
                // (Then just skip because this is the desired result anyway)
            }
        }

        // key does not exist in model anymore since it was modified when removing all participants
        Event newKey = currModel.getEvent(key.getName());

        events.remove(newKey);

    }

    //// util methods

    @Override
    public String toString() {
        return students.asUnmodifiableObservableList().size() + " students"
                + events.asUnmodifiableObservableList().size() + " events";
        // TODO: refine later
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Event> getEventList() {
        return events.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && students.equals(((AddressBook) other).students)
                && events.equals(((AddressBook) other).events));
    }

    @Override
    public int hashCode() {
        return Objects.hash(students.hashCode(), events.hashCode());
    }

}
