package seedu.programmer.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.programmer.commons.exceptions.IllegalValueException;
import seedu.programmer.model.student.ClassId;
import seedu.programmer.model.student.Grade;
import seedu.programmer.model.student.LabResult;
import seedu.programmer.model.student.Name;
import seedu.programmer.model.student.Student;
import seedu.programmer.model.student.StudentId;

import java.util.ArrayList;
import java.util.List;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "student's %s field is missing!";

    private final String name;
    private final String studentId;
    private final String classId;
    private final String grade;
    private List<LabResult> labResultList;

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("studentId") String studentId,
            @JsonProperty("classId") String classId, @JsonProperty("grade") String grade) {
        this.name = name;
        this.studentId = studentId;
        this.classId = classId;
        this.grade = grade;
        //this.labResultList = labResultList;
    }

    /**
     * Converts a given {@code student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        name = source.getName().fullName;
        studentId = source.getStudentId().studentId;
        classId = source.getClassId().classId;
        grade = source.getGrade().grade;
        if(source.getLabResultList() != null) {
            labResultList = new ArrayList<>();
            labResultList.addAll(source.getLabResultList());
        }
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Student toModelType() throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (studentId == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, StudentId.class.getSimpleName()));
        }
        if (!StudentId.isValidStudentId(studentId)) {
            throw new IllegalValueException(StudentId.MESSAGE_CONSTRAINTS);
        }
        final StudentId modelStudentId = new StudentId(studentId);

        if (classId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, ClassId.class.getSimpleName()));
        }
        if (!ClassId.isValidClassId(classId)) {
            throw new IllegalValueException(ClassId.MESSAGE_CONSTRAINTS);
        }
        final ClassId modelClassId = new ClassId(classId);

        if (grade == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Grade.class.getSimpleName()));
        }
        if (!Grade.isValidGrade(grade)) {
            throw new IllegalValueException(Grade.MESSAGE_CONSTRAINTS);
        }
        final Grade modelGrade = new Grade(grade);
        Student student = new Student(modelName, modelStudentId, modelClassId, modelGrade);
        student.setLabResultRecord(labResultList);

        return student;
    }

}
