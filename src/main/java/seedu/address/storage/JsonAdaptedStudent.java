package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Address;
import seedu.address.model.student.ClassCode;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentMark;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorialgroup.GroupNumber;
import seedu.address.model.tutorialgroup.GroupType;
import seedu.address.model.tutorialgroup.TutorialGroup;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String classCode;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedMark> marks = new ArrayList<>();
    private final List<JsonAdaptedTutorialGroup> tutorialGroups = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email, @JsonProperty("address") String address,
                              @JsonProperty("classCode") String classCode,
                              @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                              @JsonProperty("marks") List<JsonAdaptedMark> marks,
                              @JsonProperty("tutorialGroups") List<JsonAdaptedTutorialGroup> tutorialGroups) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.classCode = classCode;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (marks != null) {
            this.marks.addAll(marks);
        }
        if (tutorialGroups != null) {
            this.tutorialGroups.addAll(tutorialGroups);
        }
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        classCode = source.getClassCode().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        marks.addAll(source.getMarks().stream()
                .map(JsonAdaptedMark::new)
                .collect(Collectors.toList()));
        tutorialGroups.addAll(source.getTutorialGroups().stream()
                .map(JsonAdaptedTutorialGroup::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Student toModelType() throws IllegalValueException {
        final List<Tag> studentTags = addTags();
        final List<StudentMark> studentMarks = addMarks();
        final List<TutorialGroup> studentTutorialGroups = addTutorialGroups();
        final Name modelName = addName();
        final Phone modelPhone = addPhone();
        final Email modelEmail = addEmail();
        final Address modelAddress = addAddress();
        final ClassCode modelClassCode = addClassCode();

        final Set<Tag> modelTags = new HashSet<>(studentTags);
        final List<StudentMark> modelMarks = new ArrayList<>(studentMarks);
        final Set<TutorialGroup> modelTutorialGroups = new HashSet<>(studentTutorialGroups);

        return new Student(modelName, modelPhone, modelEmail, modelAddress,
                modelClassCode, modelTags, modelMarks, modelTutorialGroups);
    }

    /**
     * Abstracted method to create List of tags.
     *
     * @return List of tags.
     * @throws IllegalValueException
     */
    private List<Tag> addTags() throws IllegalValueException {
        List<Tag> studentTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            studentTags.add(tag.toModelType());
        }
        return studentTags;
    }

    /**
     * Abstracted method to create List of Student Marks.
     *
     * @return List of student marks.
     * @throws IllegalValueException
     */
    private List<StudentMark> addMarks() throws IllegalValueException {
        List<StudentMark> studentMarks = new ArrayList<>();
        for (JsonAdaptedMark mark : marks) {
            studentMarks.add(mark.toModelType());
        }
        return studentMarks;
    }

    /**
     * Abstracted method to get list of tutorial groups.
     *
     * @return Valid Tutorial Groups.
     * @throws IllegalValueException
     */
    private List<TutorialGroup> addTutorialGroups() throws IllegalValueException {
        List<TutorialGroup> studentTutorialGroups = new ArrayList<>();
        for (JsonAdaptedTutorialGroup tutorialGroup : tutorialGroups) {
            studentTutorialGroups.add(tutorialGroup.toModelType());
        }
        return studentTutorialGroups;
    }

    /**
     * Abstracted method to add Name.
     *
     * @return valid Name.
     * @throws IllegalValueException
     */
    private Name addName() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(name);
    }

    /**
     * Abstracted method to add Phone.
     *
     * @return valid Phone.
     * @throws IllegalValueException
     */
    private Phone addPhone() throws IllegalValueException {
        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(phone);
    }

    /**
     * Abstracted method to add Address.
     *
     * @return valid Address.
     * @throws IllegalValueException
     */
    private Address addAddress() throws IllegalValueException {
        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(address);
    }

    /**
     * Abstracted method to add Email.
     *
     * @return valid Email.
     * @throws IllegalValueException
     */
    private Email addEmail() throws IllegalValueException {
        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(email);
    }

    /**
     * Abstracted method to get ClassCode.
     *
     * @return Valid ClassCode.
     * @throws IllegalValueException
     */
    private ClassCode addClassCode() throws IllegalValueException {
        if (classCode == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, ClassCode.class.getSimpleName()));
        }
        if (!ClassCode.isValidClassCode(classCode)) {
            throw new IllegalValueException(ClassCode.MESSAGE_CONSTRAINTS);
        }
        return new ClassCode(classCode);
    }

}
