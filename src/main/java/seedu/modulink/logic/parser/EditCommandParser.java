package seedu.modulink.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.modulink.commons.core.Messages.MESSAGE_DUPLICATE_PREFIX_FORMAT;
import static seedu.modulink.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_GITHUB_USERNAME;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_MOD;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_TELEGRAM_HANDLE;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.modulink.logic.commands.EditCommand;
import seedu.modulink.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.modulink.logic.parser.exceptions.ParseException;
import seedu.modulink.model.tag.Mod;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_GITHUB_USERNAME, PREFIX_TELEGRAM_HANDLE,
                        PREFIX_MOD);

        String trimmedArgs = args.trim();
        String preamble = argMultimap.getPreamble();
        if (trimmedArgs.isEmpty()
            || !preamble.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();

        // either duplicate parameters, or unsupported parameters (dealt with in next try-catch block)
        try {
            ParserUtil.checkDuplicate(args, argMultimap, ParserUtil.isDuplicatePrefix(args, PREFIX_NAME, PREFIX_ID,
                    PREFIX_PHONE, PREFIX_EMAIL, PREFIX_GITHUB_USERNAME, PREFIX_TELEGRAM_HANDLE));
        } catch (ParseException e) {
            StringBuilder duplicatePrefixes = ParserUtil.findDuplicatePrefixes(args,
                    PREFIX_NAME, PREFIX_ID, PREFIX_PHONE,
                    PREFIX_EMAIL, PREFIX_GITHUB_USERNAME, PREFIX_TELEGRAM_HANDLE);
            throw new ParseException(String.format(MESSAGE_DUPLICATE_PREFIX_FORMAT,
                    duplicatePrefixes, EditCommand.MESSAGE_USAGE));
        }

        try {
            if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
                editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
            }
            if (argMultimap.getValue(PREFIX_ID).isPresent()) {
                editPersonDescriptor.setStudentId(ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_ID).get()));
            }
            if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
                editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
            }
            if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
                editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
            }
            if (argMultimap.getValue(PREFIX_GITHUB_USERNAME).isPresent()) {
                editPersonDescriptor.setGitHubUsername(ParserUtil.parseGithubUsername
                        (argMultimap.getValue(PREFIX_GITHUB_USERNAME).get()));
            }
            if (argMultimap.getValue(PREFIX_TELEGRAM_HANDLE).isPresent()) {
                editPersonDescriptor.setTelegramHandle(ParserUtil.parseTelegramHandle(
                        argMultimap.getValue(PREFIX_TELEGRAM_HANDLE).get()));
            }
            if (parseModsToEdit(argMultimap.getAllValues(PREFIX_MOD)).isPresent()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
            }

            if (!editPersonDescriptor.isAnyFieldEdited()) {
                throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
            }

            return new EditCommand(editPersonDescriptor);
        } catch (ParseException e) {
            throw new ParseException(String.format(e.getMessage() + "%s",
                    e.getMessage().startsWith("Unknown prefix(es)") ? EditCommand.MESSAGE_USAGE : ""));
        }
    }


    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     *
     * Only used for error checking.
     */
    private Optional<Set<Mod>> parseModsToEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
