package nustracker.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import nustracker.model.AddressBook;
import nustracker.model.ReadOnlyAddressBook;
import nustracker.model.event.Event;
import nustracker.model.event.EventDate;
import nustracker.model.event.EventName;
import nustracker.model.event.EventTime;
import nustracker.model.event.Participant;
import nustracker.model.student.Email;
import nustracker.model.student.EnrolledEvents;
import nustracker.model.student.Major;
import nustracker.model.student.Name;
import nustracker.model.student.Phone;
import nustracker.model.student.Student;
import nustracker.model.student.StudentId;
import nustracker.model.student.Year;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Rajesh Chopra"), new Phone("98765432"), new Email("rajesh@example.com"),
                new Year("1"),
                new Major("CS"),
                new StudentId("e1234567"),
                new EnrolledEvents()),
            new Student(new Name("Sonia Gupta"), new Phone("91013810"), new Email("soniagupta@example.com"),
                new Year("2"),
                new Major("CS"),
                new StudentId("e1123911"),
                new EnrolledEvents()),
            new Student(new Name("Vanessa Lee"), new Phone("91231313"), new Email("vanessalee@example.com"),
                new Year("3"),
                new Major("CS"),
                new StudentId("e1237781"),
                new EnrolledEvents()),
            new Student(new Name("Samantha Richardson"), new Phone("91231231"),
                new Email("samantharichardson@example.com"),
                new Year("4"),
                new Major("CS"),
                new StudentId("e1238811"),
                new EnrolledEvents()),
            new Student(new Name("Emily Roberts"), new Phone("91238181"), new Email("emilyroberts@example.com"),
                new Year("1"),
                new Major("CS"),
                new StudentId("e1231418"),
                new EnrolledEvents()),
            new Student(new Name("Kim Hyun"), new Phone("93191109"), new Email("kimhyun@example.com"),
                new Year("1"),
                new Major("CS"),
                new StudentId("e9102931"),
                new EnrolledEvents()),
            new Student(new Name("Amy Madison"), new Phone("90123901"), new Email("amymadison@example.com"),
                new Year("1"),
                new Major("CS"),
                new StudentId("e1913019"),
                new EnrolledEvents()),
            new Student(new Name("Timothy Goh"), new Phone("91231291"), new Email("timothygoh@example.com"),
                new Year("1"),
                new Major("CS"),
                new StudentId("e1231910"),
                new EnrolledEvents()),
            new Student(new Name("Richard Watt"), new Phone("92138921"), new Email("richardwatt@example.com"),
                new Year("1"),
                new Major("CS"),
                new StudentId("e1239191"),
                new EnrolledEvents()),
        };
    }

    public static Event[] getSampleEvents() {
        return new Event[] {
            new Event(new EventName("Orientation Camp"),
                    new EventDate("01-08-2022"),
                    new EventTime("0900"),
                    new HashSet<>(List.of(
                            new Participant("e1234567"),
                            new Participant("e1123911"),
                            new Participant("e1231418"),
                            new Participant("e9102931"),
                            new Participant("e1237781"),
                            new Participant("e1238811"))
                    ),
                    new HashSet<Participant>()
            ),
            new Event(new EventName("Sports Camp"),
                    new EventDate("10-09-2021"),
                    new EventTime("1200"),
                    new HashSet<>(List.of(
                            new Participant("e1239191"),
                            new Participant("e1231910"))
                    ),
                    new HashSet<Participant>(List.of(
                            new Participant("e1913019"),
                            new Participant("e1238811"))
                    )
            ),
            new Event(new EventName("Math Olympiad"),
                    new EventDate("05-03-2017"),
                    new EventTime("1615"),
                    new HashSet<>(List.of(
                            new Participant("e1123911"),
                            new Participant("e1238811"),
                            new Participant("e1231910"))
                    ),
                    new HashSet<Participant>(List.of(
                            new Participant("e1239191"))
                    )
            ),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
        }
        for (Event sampleEvent : getSampleEvents()) {
            sampleAb.addEvent(sampleEvent);

            Set<Student> sampleStudentParticipants = sampleEvent.getParticipantsAsStudents(sampleAb);
            for (Student sampleStudent : sampleStudentParticipants) {


                EnrolledEvents currentlyEnrolledEvents = sampleStudent.getEvents();
                EnrolledEvents updatedEnrolledEvents = currentlyEnrolledEvents.enrollIntoEvent(sampleEvent);

                Student sampleEnrolledStudent = new Student(
                        sampleStudent.getName(), sampleStudent.getPhone(), sampleStudent.getEmail(),
                        sampleStudent.getYear(), sampleStudent.getMajor(), sampleStudent.getStudentId(),
                        updatedEnrolledEvents);

                sampleAb.setStudent(sampleStudent, sampleEnrolledStudent);
            }

        }
        return sampleAb;

    }

    /**
     * Returns a student ID set containing the list of strings given.
     */
    public static Set<Participant> getParticipantSet(String... strings) {
        return Arrays.stream(strings)
                .map(Participant::new)
                .collect(Collectors.toSet());
    }
}
