package seedu.address.logic.update;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;

/**
 * Updates the lessons of students if the date has passed.
 */
public class LessonUpdate {

    /**
     * Updates the date for recurring lessons whose start date has passed.
     *
     * @param model The model of the addressbook.
     */
    public static void updateLessonsForEachStudent(Model model) {
        ObservableList<Person> studentsList = model.getFilteredPersonList();
        for (Person student : studentsList) {
            // Get updated lesson set
            Set<Lesson> updatedLessons = compareLessons(student.getLessons());
            Person newStudent = createEditedPerson(student, updatedLessons);
            execute(model, student, newStudent);
        }
    }

    private static Set<Lesson> compareLessons(Set<Lesson> lessons) {
        Set<Lesson> updatedLessons = lessons.stream()
            .map(lesson -> {
                // Only updates if lesson is a recurring lesson
                if (lesson.isRecurring()) {
                    return lesson.updateDate(lesson.updateDateWithWeek().toString());
                }
                return lesson;
            })
            .collect(Collectors.toSet());
        return updatedLessons;
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * and the updated lessons.
     */
    private static Person createEditedPerson(Person personToEdit, Set<Lesson> updatedLessons) {
        assert personToEdit != null;

        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        Address updatedAddress = personToEdit.getAddress();
        Remark updatedRemark = personToEdit.getRemark();
        Set<Tag> updatedTags = personToEdit.getTags();

        Set<Lesson> lessons = new TreeSet<>(updatedLessons);

        return new Person(updatedName, updatedPhone, updatedEmail,
            updatedAddress, updatedRemark, updatedTags, lessons);
    }

    private static void execute(Model model, Person personToEdit, Person editedPerson) {
        requireNonNull(model);
        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }
}
