package seedu.address.model.tuition;

import java.util.ArrayList;
import java.util.Objects;

import seedu.address.model.Nameable;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;

/**
 * Represents a tuition class in the book
 */
public class TuitionClass implements Nameable {
    /** Most recently viewed tuition class */
    private static TuitionClass mostRecent;

    private final ClassName name;
    private final ClassLimit limit;
    private final Timeslot timeslot;
    private StudentList studentList;
    private final Remark remark;
    private int id;

    /**
     * Constructor for Tuition Class.
     *
     * @param name The name of the tuition class.
     * @param limit The maximum number of students allowed.
     * @param timeslot The date and time of the tuition.
     * @param studentList The list of students attending the tuition.
     * @param remark Any remarks noted for the tuition class.
     */
    public TuitionClass(ClassName name, ClassLimit limit, Timeslot timeslot, StudentList studentList,
                        Remark remark) {
        this.name = name;
        this.limit = limit;
        this.timeslot = timeslot;
        this.studentList = studentList;
        this.remark = remark;
        this.id = this.hashCode();
        mostRecent = this;
    }

    /**
     * Constructor for Tuition Class used in reading data.
     *
     * @param name The name of the tuition class.
     * @param limit The maximum number of students allowed.
     * @param timeslot The date and time of the tuition.
     * @param studentList The list of students attending the tuition.
     * @param id the auto generated unique id for each tuition class
     */
    public TuitionClass(ClassName name, ClassLimit limit, Timeslot timeslot,
                        StudentList studentList, Remark remark, int id) {
        this.name = name;
        this.limit = limit;
        this.timeslot = timeslot;
        this.studentList = studentList;
        this.remark = remark;
        this.id = id;
        mostRecent = this;
    }

    public ClassName getName() {
        return name;
    }

    public ClassLimit getLimit() {
        return limit;
    }


    public Timeslot getTimeslot() {
        return timeslot;
    }

    public StudentList getStudentList() {
        return studentList;
    }

    public Remark getRemark() {
        return remark;
    }

    public int getStudentCount() {
        return studentList.getStudents().size();
    }

    public boolean isFull() {
        return this.getStudentCount() == this.limit.getLimit();
    }

    public int getId() {
        return id;
    }

    @Override
    public String getNameString() {
        return name.getName();
    }

    /**
     * Returns true if both Tuition have the same time slot.
     * This defines a weaker notion of equality between two tuition classes.
     */
    public boolean isSameTuition(TuitionClass otherTuition) {
        if (otherTuition == null) {
            return false;
        }
        if (otherTuition == this) {
            return true;
        }
        return otherTuition.getTimeslot().equals(getTimeslot());
    }

    //addn/John Doe p/98765432 e/johnd@example.com a/John street, block 123
    //addclass n/cs2100 l/10 c/4 ts/Mon 13:00-14:00 s/John Doe

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        TuitionClass otherClass = (TuitionClass) other;
        return otherClass.getTimeslot().equals(getTimeslot());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, limit, timeslot, studentList, remark);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Class: ")
                .append(getName())
                .append("; Limit: ")
                .append(getLimit())
                .append(" Timeslot: ")
                .append(getTimeslot())
                .append("; Students: ")
                .append(getStudentList())
                .append("; Remark: ")
                .append(getRemark());
        return builder.toString();
    }

    /**
     * Changes the students in this class to the students in the input.
     * @param students a list of students names to be added.
     * @return this TuitionClass
     */
    public TuitionClass changeStudents(ArrayList<String> students) {
        this.studentList = new StudentList(students);
        return this;
    }

    /**
     * Return updated Tuition class after removing student.
     *
     * @param person the student to be removed.
     * @return Updated tuition class.
     */
    public TuitionClass removeStudent(Person person) {
        this.studentList.getStudents().remove(person.getName().fullName);
        return this;
    }

    public boolean containsStudent(Person person) {
        return this.studentList.getStudents().contains(person.getName().fullName);
    }

    /**
     * Adds a new student to an existing class if the student is not already in the class.
     *
     * @param person student to be added
     * @return the tuition class after modification
     */
    public TuitionClass addStudent(Person person) {
        ArrayList<String> nowStudents = this.studentList.getStudents();
        String name = person.getName().fullName;
        for (String s: nowStudents) {
            if (s.equals(name)) {
                return null;
            }
        }
        nowStudents.add(person.getName().fullName);
        return this;
    }

    /**
     * Convert students from an arraylist to a string to be displayed in UI.
     * @return a string of all the student names combined into a list.
     */
    public String listStudents() {
        String studentString = "";
        if (this.studentList.isEmpty()) {
            studentString = "No student yet.";
            return studentString;
        }
        String lastStudent = studentList.getStudents().get(studentList.getStudents().size() - 1);
        for (String name: studentList.getStudents()) {
            studentString += name;
            if (!name.equals(lastStudent)) {
                studentString += ", ";
            }
        }
        return studentString;
    }

    /**
     * Sets most recently viewed tuition class to a given TuitionClass.
     * @param tuitionClass Tuition Class to set as most recently looked at.
     */
    public static void setMostRecentTo(TuitionClass tuitionClass) {
        mostRecent = tuitionClass;
    }

    /**
     * Returns the most recently viewed tuition class
     * @return most recently viewed tuition class.
     */
    public static TuitionClass getMostRecent() {
        return mostRecent;
    }
}
