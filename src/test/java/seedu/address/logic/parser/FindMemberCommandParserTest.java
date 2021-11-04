package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.AVAILABILITY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_AVAILABILITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PREAMBLE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_EXCO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_EXCO;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindMemberCommand;
import seedu.address.model.person.Availability;
import seedu.address.model.person.AvailabilityContainsKeywordsPredicate;
import seedu.address.model.person.Member;
import seedu.address.model.person.MemberMatchesKeywordsPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Phone;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.TodayAttendance;
import seedu.address.model.person.TodayAttendanceContainsKeywordsPredicate;
import seedu.address.model.person.TotalAttendance;
import seedu.address.model.person.TotalAttendanceContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagsContainKeywordsPredicate;

public class FindMemberCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindMemberCommand.MESSAGE_USAGE);

    private FindMemberCommandParser parser = new FindMemberCommandParser();

    // valid name keywords
    private final Name amyName = new Name(VALID_NAME_AMY);
    private final Name bobName = new Name(VALID_NAME_BOB);

    // valid phone
    private final Phone phone = new Phone(VALID_PHONE_AMY);

    // valid tags
    private final Tag excoTag = new Tag(VALID_TAG_EXCO);

    // valid availability
    private final Availability availability =
            new Availability(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY));

    // valid today attendance
    private final TodayAttendance todayAttendance = new TodayAttendance(true);

    // valid total attendance
    private final TotalAttendance totalAttendance = new TotalAttendance(3);

    // predicates
    private final Predicate<Member> namePredicate =
            new NameContainsKeywordsPredicate(Collections.singletonList(VALID_NAME_AMY));
    private final Predicate<Member> phonePredicate =
            new PhoneContainsKeywordsPredicate(Collections.singletonList(phone));
    private final Predicate<Member> singleTagPredicate =
            new TagsContainKeywordsPredicate(Collections.singletonList(excoTag));
    private final Predicate<Member> availabilityPredicate =
            new AvailabilityContainsKeywordsPredicate(Collections.singletonList(availability));
    private final Predicate<Member> todayAttendancePredicate =
            new TodayAttendanceContainsKeywordsPredicate(Collections.singletonList(todayAttendance));
    private final Predicate<Member> totalAttendancePredicate =
            new TotalAttendanceContainsKeywordsPredicate(Collections.singletonList(totalAttendance));

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindMemberCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // valid name
        Predicate<Member> predicate = x -> true;
        Predicate<Member> personPredicate = predicate.and(namePredicate);
        MemberMatchesKeywordsPredicate testPredicate =
                new MemberMatchesKeywordsPredicate.Builder().setName(amyName).setPredicate(personPredicate).build();
        FindMemberCommand expectedCommand = new FindMemberCommand(testPredicate);
        assertParseSuccess(parser, NAME_DESC_AMY, expectedCommand);

        // valid phone
        personPredicate = predicate.and(phonePredicate);
        testPredicate = new MemberMatchesKeywordsPredicate.Builder()
                .setPhone(phone).setPredicate(personPredicate).build();
        expectedCommand = new FindMemberCommand(testPredicate);
        assertParseSuccess(parser, PHONE_DESC_AMY, expectedCommand);

        // valid tag
        personPredicate = predicate.and(singleTagPredicate);
        testPredicate = new MemberMatchesKeywordsPredicate.Builder()
                .setTags(Collections.singletonList(excoTag)).setPredicate(personPredicate).build();
        expectedCommand = new FindMemberCommand(testPredicate);
        assertParseSuccess(parser, TAG_DESC_EXCO, expectedCommand);

        // valid availability
        personPredicate = predicate.and(availabilityPredicate);
        testPredicate = new MemberMatchesKeywordsPredicate.Builder()
                .setAvailability(availability).setPredicate(personPredicate).build();
        expectedCommand = new FindMemberCommand(testPredicate);
        assertParseSuccess(parser, AVAILABILITY_DESC_AMY, expectedCommand);

        // valid today attendance
        personPredicate = predicate.and(todayAttendancePredicate);
        testPredicate = new MemberMatchesKeywordsPredicate.Builder()
                .setTodayAttendance(todayAttendance).setPredicate(personPredicate).build();
        expectedCommand = new FindMemberCommand(testPredicate);
        assertParseSuccess(parser, " tda/true", expectedCommand);

        // valid total attendance
        personPredicate = predicate.and(totalAttendancePredicate);
        testPredicate = new MemberMatchesKeywordsPredicate.Builder()
                .setTotalAttendance(totalAttendance).setPredicate(personPredicate).build();
        expectedCommand = new FindMemberCommand(testPredicate);
        assertParseSuccess(parser, " tta/3", expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Predicate<Member> predicate = x -> true;
        predicate = predicate.and(namePredicate);
        predicate = predicate.and(availabilityPredicate);
        MemberMatchesKeywordsPredicate testPredicate = new MemberMatchesKeywordsPredicate.Builder().setName(amyName)
                        .setAvailability(availability).setPredicate(predicate).build();
        FindMemberCommand expectedCommand = new FindMemberCommand(testPredicate);

        assertParseSuccess(parser, NAME_DESC_AMY + AVAILABILITY_DESC_AMY, expectedCommand);
        // switch order
        assertParseSuccess(parser, AVAILABILITY_DESC_AMY + NAME_DESC_AMY, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        Predicate<Member> predicate = x -> true;
        predicate = predicate.and(namePredicate);
        MemberMatchesKeywordsPredicate testPredicate =
                new MemberMatchesKeywordsPredicate.Builder().setName(amyName).setPredicate(predicate).build();
        FindMemberCommand expectedCommand = new FindMemberCommand(testPredicate);

        assertParseSuccess(parser, INVALID_NAME_DESC + NAME_DESC_AMY, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Predicate<Member> predicate = x -> true;
        predicate = predicate.and(namePredicate);
        MemberMatchesKeywordsPredicate testPredicate =
                new MemberMatchesKeywordsPredicate.Builder().setName(amyName).setPredicate(predicate).build();
        FindMemberCommand expectedCommand = new FindMemberCommand(testPredicate);

        assertParseSuccess(parser, NAME_DESC_BOB + NAME_DESC_AMY, expectedCommand);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // invalid tag argument ahead of valid tag
        assertParseFailure(parser, INVALID_TAG_DESC + TAG_DESC_EXCO, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, NAME_DESC_AMY + INVALID_AVAILABILITY_DESC + INVALID_TAG_DESC,
                Availability.MESSAGE_CONSTRAINTS);

        // empty field
        assertParseFailure(parser, " n/  ", Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid arguments being parsed as preamble
        assertParseFailure(parser, INVALID_PREAMBLE, MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "i/ string", MESSAGE_INVALID_FORMAT);
    }
}
