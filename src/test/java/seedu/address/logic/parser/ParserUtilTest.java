package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_GROUP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commons.RepoName;
import seedu.address.model.group.GroupName;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.StudentNumber;
import seedu.address.model.student.UserName;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_STUDENTNUMBER = "a91023b";
    private static final String INVALID_USERNAME = "@a@";
    private static final String INVALID_REPONAME = "}sad{P";
    private static final String INVALID_GROUPNAME = "W1-2";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_EMAIL = "rachel@u.nus.edu";
    private static final String VALID_STUDENTNUMBER = "A0123456A";
    private static final String VALID_USERNAME = "racch";
    private static final String VALID_REPONAME = "ab3";
    private static final String VALID_GROUPNAME = "W01-4";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_STUDENT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_STUDENT, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseMultipleIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMultipleIndex("10 a"));
    }

    @Test
    public void parseMultipleIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, () -> ParserUtil.parseMultipleIndex(
                Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseMultipleIndex_validInput_success() throws Exception {
        ArrayList<Index> expectedIndexArray = new ArrayList<>();
        expectedIndexArray.add(INDEX_FIRST_STUDENT);
        expectedIndexArray.add(INDEX_THIRD_GROUP);
        // 1 whitespace
        assertEquals(expectedIndexArray, ParserUtil.parseMultipleIndex("1 3"));

        // Leading and trailing whitespaces
        assertEquals(expectedIndexArray, ParserUtil.parseMultipleIndex("  1 3  "));

        // Multiple whitespaces
        assertEquals(expectedIndexArray, ParserUtil.parseMultipleIndex("1    3"));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseStudentNumber_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStudentNumber((String) null));
    }

    @Test
    public void parseStudentNumber_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStudentNumber(INVALID_STUDENTNUMBER));
    }

    @Test
    public void parseStudentNumber_validValueWithoutWhitespace_returnsStudentNumber() throws Exception {
        StudentNumber expectedStudentNumber = new StudentNumber(VALID_STUDENTNUMBER);
        assertEquals(expectedStudentNumber, ParserUtil.parseStudentNumber(VALID_STUDENTNUMBER));
    }

    @Test
    public void parseStudentNumber_validValueWithWhitespace_returnsTrimmedStudentNumber() throws Exception {
        String studentNumberWithWhitespace = WHITESPACE + VALID_STUDENTNUMBER + WHITESPACE;
        StudentNumber expectedStudentNumber = new StudentNumber(VALID_STUDENTNUMBER);
        assertEquals(expectedStudentNumber, ParserUtil.parseStudentNumber(studentNumberWithWhitespace));
    }

    @Test
    public void parseUserName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseUserName((String) null));
    }

    @Test
    public void parseUserName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseUserName(INVALID_USERNAME));
    }

    @Test
    public void parseUserName_validValueWithoutWhitespace_returnsUserName() throws Exception {
        UserName expectedUserName = new UserName(VALID_USERNAME);
        assertEquals(expectedUserName, ParserUtil.parseUserName(VALID_USERNAME));
    }

    @Test
    public void parseUserName_validValueWithWhitespace_returnsTrimmedUserName() throws Exception {
        String userNameWithWhitespace = WHITESPACE + VALID_USERNAME + WHITESPACE;
        UserName expectedUserName = new UserName(VALID_USERNAME);
        assertEquals(expectedUserName, ParserUtil.parseUserName(userNameWithWhitespace));
    }

    @Test
    public void parseRepoName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRepo((String) null));
    }

    @Test
    public void parseRepoName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRepo(INVALID_REPONAME));
    }

    @Test
    public void parseRepoName_validValueWithoutWhitespace_returnsRepoName() throws Exception {
        RepoName expectedRepoName = new RepoName(VALID_REPONAME);
        assertEquals(expectedRepoName, ParserUtil.parseRepo(VALID_REPONAME));
    }

    @Test
    public void parseRepoName_validValueWithWhitespace_returnsTrimmedRepoName() throws Exception {
        String repoNameWithWhitespace = WHITESPACE + VALID_REPONAME + WHITESPACE;
        RepoName expectedRepoName = new RepoName(VALID_REPONAME);
        assertEquals(expectedRepoName, ParserUtil.parseRepo(repoNameWithWhitespace));
    }

    @Test
    public void parseGroupName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGroupName((String) null));
    }

    @Test
    public void parseGroupName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGroupName(INVALID_GROUPNAME));
    }

    @Test
    public void parseGroupName_validValueWithoutWhitespace_returnsGroupName() throws Exception {
        GroupName expectedGroupName = new GroupName(VALID_GROUPNAME);
        assertEquals(expectedGroupName, ParserUtil.parseGroupName(VALID_GROUPNAME));
    }

    @Test
    public void parseGroupName_validValueWithWhitespace_returnsTrimmedGroupName() throws Exception {
        String groupNameWithWhitespace = WHITESPACE + VALID_GROUPNAME + WHITESPACE;
        GroupName expectedGroupName = new GroupName(VALID_GROUPNAME);
        assertEquals(expectedGroupName, ParserUtil.parseGroupName(groupNameWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }
}
