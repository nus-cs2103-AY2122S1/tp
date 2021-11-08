package seedu.tuitione.storage;

import static seedu.tuitione.model.student.Student.MAX_REMARK_SIZE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.tuitione.commons.exceptions.IllegalValueException;
import seedu.tuitione.model.lesson.LessonCode;
import seedu.tuitione.model.remark.Remark;
import seedu.tuitione.model.student.Address;
import seedu.tuitione.model.student.Email;
import seedu.tuitione.model.student.Grade;
import seedu.tuitione.model.student.Name;
import seedu.tuitione.model.student.ParentContact;
import seedu.tuitione.model.student.Student;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String grade;
    private final List<JsonAdaptedRemark> remarks = new ArrayList<>();
    private final List<String> lessonCodes = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("tuitione") String address,
            @JsonProperty("grade") String grade, @JsonProperty("remarks") List<JsonAdaptedRemark> remarks,
            @JsonProperty("lessons") List<String> lessonCodes) {

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.grade = grade;
        if (remarks != null) {
            this.remarks.addAll(remarks);
        }
        if (lessonCodes != null) {
            this.lessonCodes.addAll(lessonCodes);
        }
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        name = source.getName().fullName;
        phone = source.getParentContact().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        grade = source.getGrade().value;
        remarks.addAll(source.getRemarks().stream()
                .map(JsonAdaptedRemark::new)
                .collect(Collectors.toList()));
        lessonCodes.addAll(source.getLessonCodes().stream()
                .map(LessonCode::toString)
                .collect(Collectors.toList()));
    }

    /**
     * Returns the list of lesson codes the encoded Student is enrolled to.
     */
    public List<String> getLessonCodes() {
        return Collections.unmodifiableList(lessonCodes);
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Student} object.
     * Lesson codes will only be attached in {@link JsonTuitioneStorage} when it can interact
     * with both lesson and students.
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

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ParentContact.class.getSimpleName()));
        }
        if (!ParentContact.isValidPhone(phone)) {
            throw new IllegalValueException(ParentContact.MESSAGE_CONSTRAINTS);
        }
        final ParentContact modelParentContact = new ParentContact(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (grade == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Grade.class.getSimpleName()));
        }
        if (!Grade.isValidGrade(grade)) {
            throw new IllegalValueException(Grade.GRADE_MESSAGE_CONSTRAINTS);
        }
        final Grade modelGrade = new Grade(grade);

        final Set<Remark> modelRemarks = new HashSet<>();
        for (JsonAdaptedRemark remark : remarks) {
            if (modelRemarks.size() == MAX_REMARK_SIZE) {
                break;
            }
            modelRemarks.add(remark.toModelType());
        }
        return new Student(modelName, modelParentContact, modelEmail, modelAddress, modelGrade, modelRemarks);
    }
}
