package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedGroup.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.GROUP1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.commons.RepoName;
import seedu.address.model.group.GroupName;
import seedu.address.model.group.LinkYear;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;

public class JsonAdaptedGroupTest {
    private static final String INVALID_NAME = "w1-4";
    private static final String INVALID_YEAR = "20!21";
    private static final String INVALID_REPONAME = "Re:po";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = GROUP1.getName().toString();
    private static final String VALID_YEAR = GROUP1.getYear().toString();
    private static final String VALID_REPONAME = GROUP1.getRepoName().toString();
    private static final Set<Tag> VALID_TAGS_SET = GROUP1.getTags();
    private static final List<JsonAdaptedTag> VALID_TAGS = VALID_TAGS_SET.stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final ArrayList<Student> VALID_MEMBER_ARRAY = new ArrayList<>(GROUP1.getMembers().studentList);
    private static final ArrayList<JsonAdaptedStudent> VALID_MEMBERS = new ArrayList<>(VALID_MEMBER_ARRAY.stream()
            .map(JsonAdaptedStudent::new)
            .collect(Collectors.toList()));

    @Test
    public void toModelType_validGroupDetails_returnsGroup() throws Exception {
        JsonAdaptedGroup group = new JsonAdaptedGroup(GROUP1);
        assertEquals(GROUP1, group.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedGroup group =
                new JsonAdaptedGroup(INVALID_NAME, VALID_TAGS, VALID_MEMBERS, VALID_YEAR, VALID_REPONAME);
        String expectedMessage = GroupName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, group::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedGroup group =
                new JsonAdaptedGroup(null, VALID_TAGS, VALID_MEMBERS, VALID_YEAR, VALID_REPONAME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, GroupName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, group::toModelType);
    }

    @Test
    public void toModelType_invalidYear_throwsIllegalValueException() {
        JsonAdaptedGroup group =
                new JsonAdaptedGroup(VALID_NAME, VALID_TAGS, VALID_MEMBERS, INVALID_YEAR, VALID_REPONAME);
        String expectedMessage = LinkYear.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, group::toModelType);
    }

    @Test
    public void toModelType_invalidRepoName_throwsIllegalValueException() {
        JsonAdaptedGroup group =
                new JsonAdaptedGroup(VALID_NAME, VALID_TAGS, VALID_MEMBERS, VALID_YEAR, INVALID_REPONAME);
        String expectedMessage = RepoName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, group::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedGroup group =
                new JsonAdaptedGroup(VALID_NAME, invalidTags, VALID_MEMBERS, VALID_YEAR, VALID_REPONAME);
        assertThrows(IllegalValueException.class, group::toModelType);
    }

}
