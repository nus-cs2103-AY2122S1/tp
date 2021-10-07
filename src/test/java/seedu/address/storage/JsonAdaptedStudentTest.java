package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Assessment;
import seedu.address.model.student.Group;
import seedu.address.model.student.ID;
import seedu.address.model.student.Name;
import seedu.address.model.student.Score;

public class JsonAdaptedStudentTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_ID = "E123";
    private static final String INVALID_GROUP = "5";
    private static final String INVALID_ASSESSMENT = "8";
    private static final String INVALID_SCORE = "101";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_ID = BENSON.getId().toString();
    // TODO: Replace with Jackson-friendly versions
    private static final List<Group> VALID_GROUPS = BENSON.getGroups();
    private static final Map<Assessment, Score> VALID_SCORES = BENSON.getScores();
    private static final String VALID_ASSESSMENT = "P01";
    private static final String VALID_SCORE = "100";
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedStudent student = new JsonAdaptedStudent(BENSON);
        assertEquals(BENSON, student.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(INVALID_NAME, VALID_ID, VALID_GROUPS, VALID_SCORES, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStudent student = 
                new JsonAdaptedStudent(null, VALID_ID, VALID_GROUPS, VALID_SCORES, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidId_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, INVALID_ID, VALID_GROUPS, VALID_SCORES, VALID_TAGS);
        String expectedMessage = ID.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullId_throwsIllegalValueException() {
        JsonAdaptedStudent student 
                = new JsonAdaptedStudent(VALID_NAME, null, VALID_GROUPS, VALID_SCORES, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ID.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidGroups_throwsIllegalValueException() {
        // TODO: Replace with JsonAdaptedX
        List<Group> invalidGroups = new ArrayList<>(VALID_GROUPS);
        invalidGroups.add(new Group(INVALID_GROUP));
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_ID, invalidGroups, VALID_SCORES, VALID_TAGS);
        assertThrows(IllegalValueException.class, student::toModelType);
    }

    @Test
    public void toModelType_invalidAssessment_throwsIllegalValueException() {
        // TODO: Replace with JsonAdaptedX
        Map<Assessment, Score> invalidScores = new HashMap<>(VALID_SCORES);
        invalidScores.put(new Assessment(INVALID_ASSESSMENT), new Score(VALID_SCORE));
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_ID, VALID_GROUPS, invalidScores, VALID_TAGS);
        assertThrows(IllegalValueException.class, student::toModelType);
    }

    @Test
    public void toModelType_invalidScore_throwsIllegalValueException() {
        // TODO: Replace with JsonAdaptedX
        Map<Assessment, Score> invalidScores = new HashMap<>(VALID_SCORES);
        invalidScores.put(new Assessment(VALID_ASSESSMENT), new Score(INVALID_SCORE));
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_ID, VALID_GROUPS, invalidScores, VALID_TAGS);
        assertThrows(IllegalValueException.class, student::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_ID, VALID_GROUPS, VALID_SCORES, invalidTags);
        assertThrows(IllegalValueException.class, student::toModelType);
    }

}
