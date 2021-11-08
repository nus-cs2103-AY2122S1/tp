package seedu.plannermd.logic.parser.tagcommandparser;

import static java.util.Objects.requireNonNull;
import static seedu.plannermd.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.plannermd.logic.commands.tagcommand.AddTagCommand.MESSAGE_NOT_ADDED;
import static seedu.plannermd.logic.commands.tagcommand.TagCommand.TOO_MANY_TAGS_MESSAGE;
import static seedu.plannermd.logic.parser.CliSyntax.FLAG_ADD;
import static seedu.plannermd.logic.parser.CliSyntax.FLAG_DELETE;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.commons.exceptions.IllegalValueException;
import seedu.plannermd.logic.commands.tagcommand.TagCommand;
import seedu.plannermd.logic.parser.ArgumentMultimap;
import seedu.plannermd.logic.parser.ArgumentTokenizer;
import seedu.plannermd.logic.parser.Parser;
import seedu.plannermd.logic.parser.ParserUtil;
import seedu.plannermd.logic.parser.exceptions.ParseException;
import seedu.plannermd.model.tag.Tag;

public abstract class TagCommandParser implements Parser<TagCommand> {

    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<flag>\\S+)(?<arguments>.*)");

    protected abstract String getUsageMessage();
    protected abstract TagCommand getAddTagCommand(Index index, Tag tag);
    protected abstract TagCommand getDeleteTagCommand(Index index, Tag tag);

    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param userInput user's input string
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public TagCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE));
        }

        final String flag = matcher.group("flag");
        final String arguments = matcher.group("arguments");

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(arguments, PREFIX_TAG);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, getUsageMessage()), ive);
        }

        if (argMultimap.getAllValues(PREFIX_TAG).size() > 1) {
            throw new ParseException(TOO_MANY_TAGS_MESSAGE);
        }

        String tagString = argMultimap.getValue(PREFIX_TAG).orElse("");

        if (tagString.equals("")) {
            throw new ParseException(MESSAGE_NOT_ADDED);
        }

        Tag tag = ParserUtil.parseTag(tagString);

        if (flag.equals(FLAG_ADD)) {
            return getAddTagCommand(index, tag);
        } else if (flag.equals(FLAG_DELETE)) {
            return getDeleteTagCommand(index, tag);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, getUsageMessage()));
        }
    }
}
