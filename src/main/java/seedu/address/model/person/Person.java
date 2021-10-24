package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.Model;
import seedu.address.model.TaskAssignable;
import seedu.address.model.id.HasUniqueId;
import seedu.address.model.id.UniqueId;
import seedu.address.model.id.exceptions.DuplicateIdException;
import seedu.address.model.id.exceptions.IdNotFoundException;
import seedu.address.model.lesson.Attendee;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.LessonAssignable;
import seedu.address.model.lesson.LessonWithAttendees;
import seedu.address.model.lesson.NoOverlapLessonList;
import seedu.address.model.lesson.exceptions.CannotAssignException;
import seedu.address.model.lesson.exceptions.OverlappingLessonsException;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */

public class Person implements HasUniqueId, Attendee,
        TaskAssignable, LessonAssignable {

    // Identity fields
    private final Name name;
    private final UniqueId id;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<UniqueId> assignedTaskIds = new HashSet<>();
    private final NoOverlapLessonList lessonsList;
    private final List<Exam> exams = new ArrayList<>();
    private final Set<UniqueId> assignedGroupIds = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                  Set<UniqueId> assignedTaskIds, NoOverlapLessonList lessonsList,
                  List<Exam> exams, Set<UniqueId> assignedGroupIds) {
        this.id = UniqueId.generateId(this);
        requireAllNonNull(name, phone, email, address, tags, assignedTaskIds, id, lessonsList, exams, assignedGroupIds);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.assignedTaskIds.addAll(assignedTaskIds);
        this.lessonsList = lessonsList;
        this.exams.addAll(exams);
        this.assignedGroupIds.addAll(assignedGroupIds);
    }

    /**
     * Every field must be present and not null.
     */
    public Person(UniqueId uniqueId, Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                  Set<UniqueId> assignedTaskIds, NoOverlapLessonList lessonsList, List<Exam> exams,
                  Set<UniqueId> assignedGroupIds) {
        requireAllNonNull(name, phone, email, address, tags, assignedTaskIds, uniqueId, lessonsList, exams,
                assignedGroupIds);
        this.id = uniqueId;
        uniqueId.setOwner(this);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.assignedTaskIds.addAll(assignedTaskIds);
        this.lessonsList = lessonsList;
        this.exams.addAll(exams);
        this.assignedGroupIds.addAll(assignedGroupIds);
    }

    /**
     * Constructs a person based on anther person immutably
     * @param toCopy person to copy
     */
    public Person (Person toCopy) {
        this.id = toCopy.id;
        this.name = toCopy.name;
        this.phone = toCopy.phone;
        this.email = toCopy.email;
        this.address = toCopy.address;
        this.tags.addAll(toCopy.tags);
        this.assignedTaskIds.addAll(toCopy.assignedTaskIds);
        this.lessonsList = toCopy.lessonsList;
        this.exams.addAll(toCopy.exams);
        this.assignedGroupIds.addAll(toCopy.assignedGroupIds);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public UniqueId getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<UniqueId> getAssignedTaskIds() {
        return Collections.unmodifiableSet(assignedTaskIds);
    }

    public NoOverlapLessonList getLessonsList() {
        return lessonsList;
    }

    public List<Exam> getExams() {
        return Collections.unmodifiableList(exams);
    }

    public Set<UniqueId> getAssignedGroupIds() {
        return Collections.unmodifiableSet(assignedGroupIds);
    }

    /**
     * Check if person can attend lesson
     *
     * @param lesson lesson to check
     * @return true if person can attend the lesson
     */
    public boolean canAttendLesson(Lesson lesson) {
        return !lessonsList.doesLessonOverlap(lesson);
    }

    @Override
    public String getAttendeeDetails() {
        return name.toString();
    }

    @Override
    public Person assignLesson(Lesson lesson) throws CannotAssignException {
        NoOverlapLessonList newList;
        try {
            newList = lessonsList.addLesson(lesson);
        } catch (OverlappingLessonsException e) {
            throw new CannotAssignException(e.getMessage());
        }

        return new Person(id, name, phone, email, address, tags, assignedTaskIds, newList, exams, assignedGroupIds);
    }

    @Override
    public Person unassignLesson(int index) throws IndexOutOfBoundsException {
        NoOverlapLessonList newList = lessonsList.removeLesson(index);
        return new Person(id, name, phone, email, address, tags, assignedTaskIds, newList, exams, assignedGroupIds);
    }

    @Override
    public List<LessonWithAttendees> getLessonsWithAttendees() {
        List<LessonWithAttendees> toReturn = new ArrayList<>();
        List<Attendee> attendees = new ArrayList<>(Arrays.asList(this));
        for (Lesson lesson : lessonsList.getLessons()) {
            toReturn.add(new LessonWithAttendees(lesson, attendees));
        }
        return toReturn;
    }

    /**
     * Immutable way of adding an exam
     * @param e exam to add
     * @return Person with exam added
     */
    public Person addExam(Exam e) {
        Person newPerson = new Person(this);
        newPerson.exams.add(e);
        return newPerson;
    }

    /**
     * Immutable way of removing an exam
     * @param index of exam to remove
     * @return Person with examed removed
     * @throws IndexOutOfBoundsException if specified index is out of bounds
     */
    public Person removeExam(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= exams.size()) {
            throw new IndexOutOfBoundsException();
        }
        Person newPerson = new Person(this);
        newPerson.exams.remove(index);
        return newPerson;
    }

    /**
     * Checks if this person has the specified group id
     * @param id to check
     * @return true if present
     */
    public boolean hasGroupId(UniqueId id) {
        return assignedGroupIds.contains(id);
    }

    /**
     * Adds the group id to the person. Group id is presumed to belong to a group.
     * @param id to add.
     * @return new Person containing the added id.
     */
    public Person addGroupId(UniqueId id) {
        if (assignedGroupIds.contains(id)) {
            throw new DuplicateIdException();
        }
        Person newPerson = new Person(this);
        newPerson.assignedGroupIds.add(id);
        return newPerson;
    }

    /**
     * Remove the group id from the person.
     * @param id to remove.
     * @return new Person with id removed.
     */
    public Person removeGroupId(UniqueId id) {
        if (!assignedGroupIds.contains(id)) {
            throw new IdNotFoundException(id);
        }
        Person newPerson = new Person(this);
        newPerson.assignedGroupIds.remove(id);
        return newPerson;
    }

    /**
     * Immutable way of updating the lessons list. Note that the id will be re-generated.
     *
     * @param newLessonsList to change to
     * @return new Person instance with the updated lessons list
     */
    public Person updateLessonsList(NoOverlapLessonList newLessonsList) {
        return new Person(id, name, phone, email, address, tags, assignedTaskIds,
                newLessonsList, exams, assignedGroupIds);
    }

    /**
     * Immutable way of updating the assigned task id list
     *
     * @param newAssignedTaskIds the new assigned task id list
     * @return new Person instance with the updated assigned task id list
     */
    public Person updateAssignedTaskIds(Set<UniqueId> newAssignedTaskIds) {
        requireNonNull(newAssignedTaskIds);
        return new Person(id, name, phone, email, address, tags, newAssignedTaskIds,
                lessonsList, exams, assignedGroupIds);
    }

    /**
     * Gets the filter list from the given model.
     *
     * @param model The model that stores the filter list.
     * @return The filter list from the given model.
     */
    @Override
    public List<Person> getFilteredListFromModel(Model model) {
        return model.getFilteredPersonList();
    };

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same id.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        // TODO: make the following code only compare the object id. You will have to face some tedious test fail.
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getLessonsList().equals(lessonsList)
                && otherPerson.getExams().equals(exams)
                && otherPerson.getAssignedGroupIds().equals(assignedGroupIds);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, id, phone, email, address, tags, lessonsList, exams);
    }

    @Override
    public String toString() {
        // TODO: Add individual exams in exams list to string representation!
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Lessons: ")
                .append(getLessonsList())
                .append("; Exams: ")
                .append(getExams());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
