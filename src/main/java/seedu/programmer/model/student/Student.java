package seedu.programmer.model.student;

import static seedu.programmer.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.programmer.logic.commands.exceptions.CommandException;
//import seedu.programmer.model.student.comparator.SortByLabName;


/**
 * Represents a student in the ProgrammerError.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    public static final String REQUIRE_POSITIVE_SCORE = "The student score should be a positive value.";

    public static final String EXCEEDED_TOTAL_SCORE = "The student score should be less than or equal to total score";

    // Identity fields
    private final Name name;
    private final StudentId studentId;
    private final ClassId classId;
    private final Email email;
    private ObservableList<Lab> labResultList;

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, StudentId studentId, ClassId classId, Email email) {
        requireAllNonNull(name, studentId, classId, email);
        this.name = name;
        this.studentId = studentId;
        this.classId = classId;
        this.email = email;
        this.labResultList = FXCollections.observableArrayList();
    }

    /**
     * Constructor for all fields including list of all the labs.
     */
    public Student(Name name, StudentId studentId, ClassId classId, Email email, ObservableList<Lab> list) {
        requireAllNonNull(name, studentId, classId, email);
        this.name = name;
        this.studentId = studentId;
        this.classId = classId;
        this.email = email;
        this.labResultList = list;
    }

    public Name getName() {
        return name;
    }

    public String getNameValue() {
        return name.toString();
    }

    public StudentId getStudentId() {
        return studentId;
    }

    public String getStudentIdValue() {
        return studentId.toString();
    }

    public ClassId getClassId() {
        return classId;
    }

    public String getClassIdValue() {
        return classId.toString();
    }

    public Email getEmail() {
        return email;
    }

    public String getEmailValue() {
        return email.toString();
    }

    public ObservableList<Lab> getLabResultList() {
        return labResultList;
    }

    public Lab getLab(int index) {
        return labResultList.get(index);
    }

    //todo check comment out
    /**
     * Adds a lab result to all the student records
     * */
    public Boolean addLabResult(Lab lab) {
        int index = this.labResultList.indexOf(lab);
        if (index == -1) {
            this.labResultList.add(lab);
            //labResultList.sort(new SortByLabName());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Deletes a lab result from all the student records
     * */
    public boolean delLabResult(Lab lab) {
        return this.labResultList.remove(lab);
    }

    /**
     * Updates a lab result for a student
     * */
    public void editLabResult(Lab lab , Double score) throws CommandException {
        if (score < 0.0) {
            throw new CommandException(REQUIRE_POSITIVE_SCORE);
        }
//        System.out.println(lab.getTotalScore());
//        System.out.println(lab);
//        if (score > lab.getTotalScore()) {
//            throw new CommandException(EXCEEDED_TOTAL_SCORE);
//        }
        int index = this.labResultList.indexOf(lab);
        Lab current = this.labResultList.get(index);

        current.updateActualScore(score);
    }

    /**
     * Updates a lab result for a student
     * */
    public void editLabInfo(Lab lab, String newTitle, Double total) throws CommandException {
        if (total < 0.0) {
            throw new CommandException(REQUIRE_POSITIVE_SCORE);
        }
        int index = this.labResultList.indexOf(lab);
        Lab current = this.labResultList.get(index);
        current.updateTitle(newTitle);
        current.updateTotal(total);
    }

    public void setLabResultRecord(List<Lab> labResultRecord) {
        if (labResultRecord == null) {
            labResultRecord = new ArrayList<>();
        }
        this.labResultList.addAll(labResultRecord);
    }

    public void setLabResultList(ObservableList<Lab> labResultList) {
        this.labResultList = labResultList;
    }

    /**
     * Returns true if both students have the same name.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getStudentId().equals(getStudentId());
    }

    /**
     * Returns true if both students have the same identity and data fields.
     * This defines a stronger notion of equality between two students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return otherStudent.getName().equals(getName())
                && otherStudent.getStudentId().equals(getStudentId())
                && otherStudent.getClassId().equals(getClassId())
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getLabResultList().equals(getLabResultList());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, studentId, classId, email);
    }

    @Override
    public String toString() {
        return getName()
                + "; Student ID: "
                + getStudentId()
                + "; Class ID: "
                + getClassId()
                + "; Email: "
                + getEmail();
    }

    /**
     * Create a new Student Object with the same fields.
     * @return a copy of the Student
     * */
    public Student copy() {
        Student studentCopy = new Student(name, studentId, classId, email);
        studentCopy.setLabResultRecord(labResultList);
        return studentCopy;
    }
}
