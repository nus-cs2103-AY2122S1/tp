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
        List<Lesson> lessonList = new ArrayList<>();
        List<Student> studentList = new ArrayList<>();
        prepareAssociations(lessonList, studentList);

        AddressBook addressBook = new AddressBook();
        for (Lesson lesson : lessonList) {
            if (addressBook.hasLesson(lesson)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_LESSON);
            }
            addressBook.addLesson(lesson);
        }
        for (Student student : studentList) {
            if (addressBook.hasPerson(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(student);
        }
        return addressBook;
    }

    /**
     * Prepares relevant Lessons and Students with their association.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    private void prepareAssociations(List<Lesson> lessonList, List<Student> studentList) throws IllegalValueException {
        for (JsonAdaptedLesson jsonAdaptedLesson : lessons) {
            Lesson lesson = jsonAdaptedLesson.toModelType();
            lessonList.add(lesson);
        }

        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Student student = jsonAdaptedPerson.toModelType();
            List<String> jsonLessonCodes = jsonAdaptedPerson.getLessonCodes();
            for (String jsonLessonCode : jsonLessonCodes) {
                boolean hasFoundLesson = false;
                for (Lesson lesson : lessonList) {
                    if (lesson.getLessonCode().equals(jsonLessonCode)) {
                        lesson.addStudent(student);
                        hasFoundLesson = true;
                        break;
                    }
                }
                if (!hasFoundLesson) {
                    throw new IllegalValueException(MESSAGE_INVALID_LESSON_CODE);
                }
            }
            studentList.add(student);
        }
    }
}
