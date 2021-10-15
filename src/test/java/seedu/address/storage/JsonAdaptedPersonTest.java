package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Faculty;
import seedu.address.model.person.Major;
import seedu.address.model.person.Name;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_FACULTY = "wrong_faculty";
    private static final String INVALID_MAJOR = "##computer_2science";
    private static final String INVALID_TAG = "#friend";


    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_FACULTY = BENSON.getFaculty().toString();
    private static final String VALID_MAJOR = BENSON.getMajor().toString();
    private static final List<JsonAdaptedSkill> VALID_SKILLS = BENSON.getSkills().stream()
            .map(JsonAdaptedSkill::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedLanguage> VALID_LANGUAGES = BENSON.getLanguages().stream()
            .map(JsonAdaptedLanguage::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedFramework> VALID_FRAMEWORK = BENSON.getFrameworks().stream()
            .map(JsonAdaptedFramework::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_EMAIL, VALID_FACULTY, VALID_MAJOR,
                        VALID_SKILLS, VALID_LANGUAGES, VALID_FRAMEWORK, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_EMAIL, VALID_FACULTY, VALID_MAJOR,
                VALID_SKILLS, VALID_LANGUAGES, VALID_FRAMEWORK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, INVALID_EMAIL, VALID_FACULTY, VALID_MAJOR,
                VALID_SKILLS, VALID_LANGUAGES, VALID_FRAMEWORK, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_FACULTY, VALID_MAJOR,
                VALID_SKILLS, VALID_LANGUAGES, VALID_FRAMEWORK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidFaculty_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_EMAIL, INVALID_FACULTY, VALID_MAJOR,
                        VALID_SKILLS, VALID_LANGUAGES, VALID_FRAMEWORK, VALID_TAGS);
        String expectedMessage = Faculty.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullFaculty_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_EMAIL, null, VALID_MAJOR,
                        VALID_SKILLS, VALID_LANGUAGES, VALID_FRAMEWORK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Faculty.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidMajor_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_EMAIL, VALID_FACULTY, INVALID_MAJOR,
                        VALID_SKILLS, VALID_LANGUAGES, VALID_FRAMEWORK, VALID_TAGS);
        String expectedMessage = Major.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullMajor_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_EMAIL, VALID_FACULTY, null,
                        VALID_SKILLS, VALID_LANGUAGES, VALID_FRAMEWORK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Major.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }


    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_EMAIL, VALID_FACULTY, VALID_MAJOR,
                VALID_SKILLS, VALID_LANGUAGES, VALID_FRAMEWORK, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
