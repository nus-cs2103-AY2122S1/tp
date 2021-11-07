package seedu.programmer.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.programmer.commons.exceptions.IllegalValueException;
import seedu.programmer.model.student.ClassId;
import seedu.programmer.model.student.Email;
import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.LabNum;
import seedu.programmer.model.student.LabResult;
import seedu.programmer.model.student.LabTotal;
import seedu.programmer.model.student.Name;
import seedu.programmer.model.student.Student;
import seedu.programmer.model.student.StudentId;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "student's %s field is missing!";

    private final String name;
    private final String studentId;
    private final String classId;
    private final String email;
    private List<JsonAdaptedLab> labResultList;

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("studentId") String studentId,
            @JsonProperty("classId") String classId, @JsonProperty("email") String email) {
        this.name = name;
        this.studentId = studentId;
        this.classId = classId;
        this.email = email;
    }

    /**
     * Converts a given {@code student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        name = source.getNameValue();
        studentId = source.getStudentIdValue();
        classId = source.getClassIdValue();
        email = source.getEmailValue();
        if (source.getLabList() != null) {
            labResultList = new ArrayList<>();
            List<Lab> labs = source.getLabList();
            for (Lab lab : labs) {
                JsonAdaptedLab adaptedLab = new JsonAdaptedLab(lab);
                labResultList.add(adaptedLab);
            }
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

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);
        Student student = new Student(modelName, modelStudentId, modelClassId, modelEmail);



        if (labResultList != null) {
            List<Lab> studentLabResults = new ArrayList<>();
            for (JsonAdaptedLab adaptedLab : labResultList) {
                if (adaptedLab == null) {
                    throw new IllegalValueException(JsonAdaptedLab.NON_NULL_ADAPTED_LAB);
                }

                int labNum = adaptedLab.getLabNum();
                int labResult = adaptedLab.getLabResult();
                int labTotal = adaptedLab.getLabTotal();


                if (!LabNum.isValidLabNum(labNum)) {
                    throw new IllegalValueException(LabNum.MESSAGE_CONSTRAINT);
                }

                if (!LabResult.isValidLabResult(labResult)) {
                    throw new IllegalValueException(LabResult.MESSAGE_CONSTRAINT);
                }

                if (!LabTotal.isValidLabTotalScore(labTotal)) {
                    throw new IllegalValueException(LabTotal.MESSAGE_CONSTRAINT);
                }

                if (labResult > labTotal) {
                    throw new IllegalValueException("Lab result should be smaller or equal to lab total score.");
                }

                Lab lab = new Lab(new LabNum(labNum), new LabResult(labResult), new LabTotal(labTotal));
                studentLabResults.add(lab);
             }
            student.setLabResultRecord(studentLabResults);
        }

        return student;
    }

}
