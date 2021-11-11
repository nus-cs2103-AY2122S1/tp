package nustracker.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import nustracker.commons.exceptions.IllegalValueException;
import nustracker.model.AddressBook;
import nustracker.model.ReadOnlyAddressBook;
import nustracker.model.event.Event;
import nustracker.model.event.Participant;
import nustracker.model.student.EnrolledEvents;
import nustracker.model.student.Student;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Students list contains duplicate student(s).";
    public static final String MESSAGE_DUPLICATE_EVENT = "Events list contains duplicate event(s).";
    public static final String MESSAGE_PARTICIPANTS_BLACKLIST_CONFLICT = "There were conflicts found "
            + "between certain Participants and Blacklists when trying to load events.";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();
    private final List<JsonAdaptedEvent> events = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given students and events.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("students") List<JsonAdaptedStudent> students,
                @JsonProperty("events") List<JsonAdaptedEvent> events) {
        this.students.addAll(students);
        this.events.addAll(events);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
        events.addAll(source.getEventList().stream().map(JsonAdaptedEvent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (addressBook.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            addressBook.addStudent(student);
        }
        for (JsonAdaptedEvent jsonAdaptedEvent : events) {
            Event event = jsonAdaptedEvent.toModelType();
            if (addressBook.hasEvent(event)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_EVENT);
            }
            addressBook.addEvent(event);

            Set<Participant> currBlacklist = event.getBlacklist();

            Set<Student> studentParticipants = event.getParticipantsAsStudents(addressBook);
            for (Student currStudent : studentParticipants) {
                if (currStudent == null) {
                    continue;
                }

                Participant currParticipant = new Participant(currStudent.getStudentId().getStudentIdString());

                if (currBlacklist.contains(currParticipant)) {
                    throw new IllegalValueException(MESSAGE_PARTICIPANTS_BLACKLIST_CONFLICT);
                }

                EnrolledEvents currentlyEnrolledEvents = currStudent.getEvents();
                EnrolledEvents updatedEnrolledEvents = currentlyEnrolledEvents.enrollIntoEvent(event);

                Student enrolledStudent = new Student(
                        currStudent.getName(), currStudent.getPhone(), currStudent.getEmail(),
                        currStudent.getYear(), currStudent.getMajor(), currStudent.getStudentId(),
                        updatedEnrolledEvents);

                // Have to set student or GUI will not update
                // From documentation of ObservableList:
                // "Note that mutation operations made directly to the underlying list are not reported to
                // observers of any ObservableList that wraps it."
                addressBook.setStudent(currStudent, enrolledStudent);
            }

        }
        return addressBook;
    }

}
