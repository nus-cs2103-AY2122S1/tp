package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AVAILABILITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TODAY_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TOTAL_ATTENDANCE;
import static seedu.address.model.person.MemberMatchesKeywordsPredicate.Builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindMemberCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Availability;
import seedu.address.model.person.AvailabilityContainsKeywordsPredicate;
import seedu.address.model.person.Member;
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

/**
 * Parses input arguments and creates a new FindMemberCommand object
 */
public class FindMemberCommandParser implements Parser<FindMemberCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindMemberCommand
     * and returns a FindMemberCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindMemberCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindMemberCommand.MESSAGE_USAGE));
        }
        return new FindMemberCommand(generatePredicate(args));
    }

    /**
     * Generates the final predicate to be used for FindMemberCommand
     * @throws ParseException if the user input does not conform the expected format.
     */
    private Predicate<Member> generatePredicate(String args) throws ParseException {
        Builder builder = new Builder();
        Predicate<Member> predicate = x -> true;
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_AVAILABILITY, PREFIX_TAG, PREFIX_TODAY_ATTENDANCE, PREFIX_TOTAL_ATTENDANCE);
        if (!argMultimap.getPreamble().trim().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindMemberCommand.MESSAGE_USAGE));
        }
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            predicate = generateNamePredicate(ParserUtil.parseName(argMultimap
                    .getValue(PREFIX_NAME).get()), builder, predicate);
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            predicate = generatePhonePredicate(ParserUtil.parsePhone(argMultimap
                    .getValue(PREFIX_PHONE).get()), builder, predicate);
        }
        if (argMultimap.getValue(PREFIX_AVAILABILITY).isPresent()) {
            predicate = generateAvailabilityPredicate(ParserUtil.parseAvailability(argMultimap
                    .getValue(PREFIX_AVAILABILITY).get()), builder, predicate);
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            predicate = generateTagPredicate(parseTags(argMultimap.getAllValues(PREFIX_TAG)).get(), builder, predicate);
        }
        if (argMultimap.getValue(PREFIX_TODAY_ATTENDANCE).isPresent()) {
            predicate = generateTodayAttendancePredicate(ParserUtil.parseTodayAttendance(argMultimap
            .getValue(PREFIX_TODAY_ATTENDANCE).get()), builder, predicate);
        }
        if (argMultimap.getValue(PREFIX_TOTAL_ATTENDANCE).isPresent()) {
            predicate = generateTotalAttendancePredicate(ParserUtil.parseTotalAttendance(argMultimap
            .getValue(PREFIX_TOTAL_ATTENDANCE).get()), builder, predicate);
        }
        return builder.setPredicate(predicate).build();
    }

    private Predicate<Member> generateNamePredicate(Name name, Builder builder, Predicate<Member> predicate) {
        String nameWithNoSpaces = name.toString().toLowerCase().replace("\\s+", "");
        List<String> nameList = new ArrayList<>(Arrays.asList(nameWithNoSpaces));
        predicate = predicate.and(new NameContainsKeywordsPredicate(nameList));
        builder.setName(name);
        return predicate;
    }

    private Predicate<Member> generatePhonePredicate(Phone phone, Builder builder, Predicate<Member> predicate) {
        List<Phone> phoneList = new ArrayList<>(Arrays.asList(phone));
        predicate = predicate.and(new PhoneContainsKeywordsPredicate(phoneList));
        builder.setPhone(phone);
        return predicate;
    }

    private Predicate<Member> generateTagPredicate(List<Tag> tags, Builder builder, Predicate<Member> predicate) {
        predicate = predicate.and(new TagsContainKeywordsPredicate(tags));
        builder.setTags(tags);
        return predicate;
    }

    private Predicate<Member> generateAvailabilityPredicate(Availability availability,
                                                            Builder builder, Predicate<Member> predicate) {
        List<Availability> availabilityList = new ArrayList<>(Arrays.asList(availability));
        predicate = predicate.and(new AvailabilityContainsKeywordsPredicate(availabilityList));
        builder.setAvailability(availability);
        return predicate;
    }

    private Predicate<Member> generateTodayAttendancePredicate(TodayAttendance todayAttendance,
                                                               Builder builder, Predicate<Member> predicate) {
        List<TodayAttendance> todayAttendanceList = new ArrayList<>(Arrays.asList(todayAttendance));
        predicate = predicate.and(new TodayAttendanceContainsKeywordsPredicate(todayAttendanceList));
        builder.setTodayAttendance(todayAttendance);
        return predicate;
    }

    private Predicate<Member> generateTotalAttendancePredicate(TotalAttendance totalAttendance,
                                                               Builder builder, Predicate<Member> predicate) {
        List<TotalAttendance> totalAttendanceList = new ArrayList<>(Arrays.asList(totalAttendance));
        predicate = predicate.and(new TotalAttendanceContainsKeywordsPredicate(totalAttendanceList));
        builder.setTotalAttendance(totalAttendance);
        return predicate;
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Optional<List<Tag>>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Optional<List<Tag>>} containing zero tags.
     */
    private Optional<List<Tag>> parseTags(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("\\s+") ? Collections.emptySet() : tags;
        return Optional.of(List.copyOf(ParserUtil.parseTags(tagSet)));
    }
}
