package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.EditCommand.MESSAGE_EDIT_PROFILE_GITHUB_CANNOT_BE_EMPTY;
import static seedu.address.logic.commands.EditCommand.MESSAGE_EDIT_PROFILE_NAME_CANNOT_BE_EMPTY;
import static seedu.address.logic.commands.EditCommand.MESSAGE_EDIT_PROFILE_PARAMETERS_CANNOT_BE_EMPTY;
import static seedu.address.logic.commands.EditCommand.MESSAGE_EDIT_PROFILE_TELEGRAM_CANNOT_BE_EMPTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    private void checkEditProfileInputFormat(String args, ArgumentMultimap argMultimap) throws ParseException {
        assert(!args.isEmpty());
        boolean isNameEdited = args.contains(PREFIX_NAME.getPrefix());
        boolean isTelegramEdited = args.contains(PREFIX_TELEGRAM.getPrefix());
        boolean isGithubEdited = args.contains(PREFIX_GITHUB.getPrefix());
        boolean isNameArgEmpty = argMultimap.getAllValues(PREFIX_NAME).size() == 1
                && argMultimap.getAllValues(PREFIX_NAME).indexOf("") == 0;
        boolean isTelegramArgEmpty = argMultimap.getAllValues(PREFIX_TELEGRAM).size() == 1
                && argMultimap.getAllValues(PREFIX_TELEGRAM).indexOf("") == 0;
        boolean isGithubArgEmpty = argMultimap.getAllValues(PREFIX_GITHUB).size() == 1
                && argMultimap.getAllValues(PREFIX_GITHUB).indexOf("") == 0;
        if (isNameEdited && isNameArgEmpty && isGithubEdited && isGithubArgEmpty
                && isTelegramEdited && isTelegramArgEmpty) {
            throw new ParseException(MESSAGE_EDIT_PROFILE_PARAMETERS_CANNOT_BE_EMPTY);
        } else if (args.contains(PREFIX_NAME.getPrefix()) && isNameArgEmpty) {
            throw new ParseException(MESSAGE_EDIT_PROFILE_NAME_CANNOT_BE_EMPTY);
        } else if (args.contains(PREFIX_TELEGRAM.getPrefix()) && isTelegramArgEmpty) {
            throw new ParseException(MESSAGE_EDIT_PROFILE_TELEGRAM_CANNOT_BE_EMPTY);
        } else if (args.contains(PREFIX_GITHUB.getPrefix()) && isGithubArgEmpty) {
            throw new ParseException(MESSAGE_EDIT_PROFILE_GITHUB_CANNOT_BE_EMPTY);
        } else {
            return;
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TELEGRAM, PREFIX_GITHUB, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        Index index = ParserUtil.parseIndex("1");
        boolean isProfile = false;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            if (argMultimap.getPreamble().equals("profile")) {
                isProfile = true;
                checkEditProfileInputFormat(args, argMultimap);
            } else {
                throw new ParseException(pe.getMessage() + " \n" + EditCommand.MESSAGE_USAGE);
            }
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_TELEGRAM).isPresent()) {
            editPersonDescriptor.setTelegram(ParserUtil.parseTelegram(argMultimap.getValue(PREFIX_TELEGRAM).get()));
        }
        if (argMultimap.getValue(PREFIX_GITHUB).isPresent()) {
            editPersonDescriptor.setGithub(ParserUtil.parseGithub(argMultimap.getValue(PREFIX_GITHUB).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);
        editPersonDescriptor.setIsProfile(isProfile);
        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }
        return new EditCommand(index, editPersonDescriptor);
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
