package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.mapper.PrefixMapper.PREFIX_EDIT_SET_MAP;
import static seedu.address.commons.mapper.PrefixMapper.PREFIX_PARSE_MAP;
import static seedu.address.logic.parser.CliSyntax.ALL_PREFIXES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENTID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.allPrefixLess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ClientId;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, ALL_PREFIXES);

        List<ClientId> clientIds = new ArrayList<>();
        try {
            String[] clientIdInput = argMultimap.getPreamble().split(" ");
            for (String s : clientIdInput) {
                clientIds.add(ParserUtil.parseClientId(s));
            }
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        if (argMultimap.getValue(PREFIX_CLIENTID).isPresent()) {
            throw new ParseException(EditCommand.MESSAGE_CHANGE_CLIENTID);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        Prefix[] prefixes = allPrefixLess(PREFIX_CLIENTID, PREFIX_TAG);
        for (Prefix prefix : prefixes) {
            if (argMultimap.getValue(prefix).isPresent()) {
                // The output type of parser function will match with the setter function based on PrefixMapper
                @SuppressWarnings("unchecked")
                BiConsumer<EditPersonDescriptor, Object> setFunction =
                        (BiConsumer<EditPersonDescriptor, Object>) PREFIX_EDIT_SET_MAP.get(prefix);
                Function<String, ?> parseFunction = PREFIX_PARSE_MAP.get(prefix);
                String toParse = argMultimap.getValue(prefix).get();
                Object parsed = parseFunction.apply(toParse);
                setFunction.accept(editPersonDescriptor, parsed);
            }
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(clientIds, editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
