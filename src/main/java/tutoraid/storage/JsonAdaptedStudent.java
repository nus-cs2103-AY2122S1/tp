package tutoraid.storage;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import tutoraid.commons.exceptions.IllegalValueException;
import tutoraid.model.student.InitialStudent;
import tutoraid.model.student.Lessons;
import tutoraid.model.student.Name;
import tutoraid.model.student.ParentName;
import tutoraid.model.student.Phone;
import tutoraid.model.student.Progress;
import tutoraid.model.student.ProgressList;
import tutoraid.model.student.Student;
import tutoraid.model.student.StudentName;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String studentName;
    private final String studentPhone;
    private final String parentName;
    private final String parentPhone;
    private final ArrayList<String> progressList;
    private final ArrayList<String> lessonNames;

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedStudent(
            @JsonProperty("studentName") String studentName, @JsonProperty("studentPhone") String studentPhone,
            @JsonProperty("parentName") String parentName, @JsonProperty("parentPhone") String parentPhone,
            @JsonProperty("progressList") ArrayList<String> progressList,
            @JsonProperty("lessons") ArrayList<String> lessonNames) {

        this.studentName = studentName;
        this.studentPhone = studentPhone;
        this.parentName = parentName;
        this.parentPhone = parentPhone;
        this.progressList = progressList;
        this.lessonNames = lessonNames;
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        studentName = source.getStudentName().fullName;
        studentPhone = source.getStudentPhone().value;
        parentName = source.getParentName().fullName;
        parentPhone = source.getParentPhone().value;
        progressList = source.getProgressList().getAllProgressAsStringArrayList();
        lessonNames = source.getLessons().getAllLessonNamesAsStringArrayList();
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public InitialStudent toModelType() throws IllegalValueException {
        if (studentName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(studentName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final StudentName modelStudentName = new StudentName(studentName);

        if (!studentPhone.equals("") && !Phone.isValidPhone(studentPhone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelStudentPhone = new Phone(studentPhone);

        if (!parentName.equals("") && !Name.isValidName(parentName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final ParentName modelParentName = new ParentName(parentName);

        if (!parentPhone.equals("") && !Phone.isValidPhone(parentPhone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelParentPhone = new Phone(parentPhone);

        if (progressList == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, ProgressList.class.getSimpleName()));
        }
        if (!ProgressList.isValidProgressList(progressList)) {
            throw new IllegalValueException(Progress.MESSAGE_CONSTRAINTS);
        }
        final ProgressList modelProgress = new ProgressList(progressList);
        if (lessonNames == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Lessons.class.getSimpleName()));
        }

        return new InitialStudent(modelStudentName, modelStudentPhone, modelParentName, modelParentPhone,
                modelProgress, lessonNames);
    }

}
