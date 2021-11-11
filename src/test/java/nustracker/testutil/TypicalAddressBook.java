package nustracker.testutil;

import static nustracker.testutil.TypicalEvents.getTypicalEvents;
import static nustracker.testutil.TypicalStudents.getTypicalStudents;

import java.util.Set;

import nustracker.model.AddressBook;
import nustracker.model.event.Event;
import nustracker.model.student.EnrolledEvents;
import nustracker.model.student.Student;

public class TypicalAddressBook {

    private TypicalAddressBook() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical students.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Student student : getTypicalStudents()) {
            ab.addStudent(student);
        }
        for (Event event : getTypicalEvents()) {
            ab.addEvent(event);
            // Update student enrolled events
            Set<Student> studentParticipants = event.getParticipantsAsStudents(ab);
            for (Student currStudent : studentParticipants) {
                if (currStudent == null) {
                    continue;
                }

                EnrolledEvents currentlyEnrolledEvents = currStudent.getEvents();
                EnrolledEvents updatedEnrolledEvents = currentlyEnrolledEvents.enrollIntoEvent(event);

                Student enrolledStudent = new Student(
                        currStudent.getName(), currStudent.getPhone(), currStudent.getEmail(),
                        currStudent.getYear(), currStudent.getMajor(), currStudent.getStudentId(),
                        updatedEnrolledEvents);

                ab.setStudent(currStudent, enrolledStudent);
            }

        }
        return ab;
    }

}
