package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.model.person.PersonMatchesKeywordsPredicate.Builder;

import java.util.*;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindMemberCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagsContainsKeywordsPredicate;

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

    private Predicate<Person> generatePredicate(String args) throws ParseException {
        Builder builder = new Builder();
        Predicate<Person> predicate = x -> true;
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_AVAILABILITY, PREFIX_TAG);
        if (!argMultimap.getPreamble().trim().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindMemberCommand.MESSAGE_USAGE));
        }
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            predicate = predicate.and(generateNamePredicate(ParserUtil.parseName(argMultimap
                    .getValue(PREFIX_NAME).get()), builder, predicate));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            predicate = predicate.and(generatePhonePredicate(ParserUtil.parsePhone(argMultimap
                    .getValue(PREFIX_PHONE).get()), builder, predicate));
        }
        if (argMultimap.getValue(PREFIX_AVAILABILITY).isPresent()) {
            predicate = predicate.and(generateAvailabilityPredicate(ParserUtil.parseAvailability(argMultimap
                    .getValue(PREFIX_AVAILABILITY).get()), builder, predicate));
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            predicate = predicate.and(generateTagPredicate(parseTags(argMultimap
                    .getAllValues(PREFIX_TAG)).get(), builder, predicate));
        }
        return builder.setPredicate(predicate).build();
    }

    private Predicate<Person> generateNamePredicate(Name name, Builder builder, Predicate<Person> predicate) {
        List<String> nameList = new ArrayList<>(Arrays.asList(name.toString()));
        predicate = predicate.and(new NameContainsKeywordsPredicate(nameList));
        builder.setName(name);
        return predicate;
    }

    private Predicate<Person> generatePhonePredicate(Phone phone, Builder builder, Predicate<Person> predicate) {
        List<Phone> phoneList = new ArrayList<>(Arrays.asList(phone));
        predicate = predicate.and(new PhoneContainsKeywordsPredicate(phoneList));
        builder.setPhone(phone);
        return predicate;
    }

    private Predicate<Person> generateTagPredicate(List<Tag> tags, Builder builder, Predicate<Person> predicate) {
        predicate = predicate.and(new TagsContainsKeywordsPredicate(tags));
        builder.setTags(tags);
        return predicate;
    }

    private Predicate<Person> generateAvailabilityPredicate(Availability availability,
                                                            Builder builder, Predicate<Person> predicate) {
        List<Availability> availabilityList = new ArrayList<>(Arrays.asList(availability));
        predicate = predicate.and(new AvailabilityContainsKeywordsPredicate(availabilityList));
        builder.setAvailability(availability);
        return predicate;
    }
    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
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
