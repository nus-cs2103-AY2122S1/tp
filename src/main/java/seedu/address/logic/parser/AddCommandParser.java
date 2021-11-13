package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.mapper.PrefixMapper.parseAndEditSetDefaultFunction;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENTID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.allPrefixLess;
import static seedu.address.logic.parser.CliSyntax.allPrefixesPresent;

import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.client.Client.EditClientDescriptor;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    private static final Prefix[] REQUIRED_PREFIXES = {
        PREFIX_NAME, PREFIX_EMAIL
    };

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddCommand parse(String args, Model model) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, allPrefixLess(PREFIX_CLIENTID));
        if (!allPrefixesPresent(argMultimap, REQUIRED_PREFIXES)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        EditClientDescriptor editClientDescriptor = new EditClientDescriptor();

        for (Prefix prefix: allPrefixLess(PREFIX_CLIENTID, PREFIX_TAG)) {
            BiConsumer<EditClientDescriptor, Optional<String>> parseEditSetDefaultFunction =
                    parseAndEditSetDefaultFunction(prefix);
            Optional<String> attributeString = argMultimap.getValue(prefix);
            parseEditSetDefaultFunction.accept(editClientDescriptor, attributeString);
        }

        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG), model);
        editClientDescriptor.setTags(tagList);

        return new AddCommand(editClientDescriptor);
    }
}
