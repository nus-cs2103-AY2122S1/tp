package seedu.address.logic.init;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Fee;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;

public class LessonManager {
    /**
     * Initializes TAB lesson's to update all recurring lessons of students to
     * the next date that has yet to pass.
     *
     * @param model Model to work on.
     */
    public static void init(Model model) {
        ObservableList<Person> students = model.getFilteredPersonList();
        students.forEach(student -> updateLessons(model, student));
    }

    /**
     * Update lessons for the specified student in TAB.
     *
     * @param model The model to work on.
     * @param student The student to update.
     */
    private static void updateLessons(Model model, Person student) {
        Set<Lesson> lessonSet = new TreeSet<>(student.getLessons())
            .stream()
            .map(lesson -> lesson.isRecurring() ? lesson.updateDate() : lesson)
            .collect(Collectors.toSet());

        Person editedStudent = createEditedPerson(student, lessonSet);
        model.setPerson(student, editedStudent);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     */
    private static Person createEditedPerson(Person personToEdit, Set<Lesson> lessons) {
        assert personToEdit != null;

        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        Phone updatedParentPhone = personToEdit.getParentPhone();
        Email updatedParentEmail = personToEdit.getParentEmail();
        Address updatedAddress = personToEdit.getAddress();
        Fee updatedOutstandingFee = personToEdit.getFee();
        Remark updatedRemark = personToEdit.getRemark();
        Set<Tag> updatedTags = personToEdit.getTags();

        return new Person(updatedName, updatedPhone, updatedEmail, updatedParentPhone,
            updatedParentEmail, updatedAddress, updatedOutstandingFee, updatedRemark,
            updatedTags, lessons);
    }

}
