package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Student;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_LESSON = "Lessons list contains duplicate lesson(s).";
    public static final String MESSAGE_INVALID_LESSON_CODE = "Lessons list does not match with "
            + "person-associated lesson codes(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedLesson> lessons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
            @JsonProperty("lessons") List<JsonAdaptedLesson> lessons) {
        this.persons.addAll(persons);
        this.lessons.addAll(lessons);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        lessons.addAll(source.getLessonList().stream().map(JsonAdaptedLesson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        List<Lesson> lessonList = prepareLessonList();
        List<Student> studentList = prepareStudentList(lessonList);
        return prepareAddressBook(lessonList, studentList);
    }

    /**
     * Prepares the model's {@code AddressBook} object with the lesson and student lists.
     */
    public AddressBook prepareAddressBook(List<Lesson> lessons, List<Student> students) throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (Lesson lesson : lessons) {
            if (addressBook.hasLesson(lesson)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_LESSON);
            }
            addressBook.addLesson(lesson);
        }
        for (Student student : students) {
            if (addressBook.hasPerson(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(student);
        }
        return addressBook;
    }

    /**
     * Prepares the list of Lessons for model.
     */
    public List<Lesson> prepareLessonList() throws IllegalValueException {
        List<Lesson> lessonList = new ArrayList<>();
        for (JsonAdaptedLesson jsonAdaptedLesson : lessons) {
            Lesson lesson = jsonAdaptedLesson.toModelType();
            lessonList.add(lesson);
        }
        return lessonList;
    }

    /**
     * Prepares the list of Students for model.
     */
    public List<Student> prepareStudentList(List<Lesson> lessonList) throws IllegalValueException {
        List<Student> studentList = new ArrayList<>();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Student student = jsonAdaptedPerson.toModelType();
            List<String> jsonLessonCodes = jsonAdaptedPerson.getLessonCodes();
            establishStudentLessonLinkages(student, jsonLessonCodes, lessonList);
            studentList.add(student);
        }
        return studentList;
    }

    /**
     * Prepare linkages between a student and lessons.
     */
    public void establishStudentLessonLinkages(Student student, List<String> jsonLessonCodes,
            List<Lesson> lessonList) throws IllegalValueException {

        for (String jsonLessonCode : jsonLessonCodes) {
            boolean hasFoundLesson = false;
            for (Lesson lesson : lessonList) {
                if (jsonLessonCode.equals(lesson.getLessonCode().value)) {
                    lesson.addStudent(student);
                    hasFoundLesson = true;
                    break;
                }
            }
            if (!hasFoundLesson) {
                throw new IllegalValueException(MESSAGE_INVALID_LESSON_CODE);
            }
        }
    }
}
