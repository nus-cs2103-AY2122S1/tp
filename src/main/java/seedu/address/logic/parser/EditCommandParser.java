package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FACULTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FRAMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LANGUAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.skill.Framework;
import seedu.address.model.skill.Language;
import seedu.address.model.skill.Skill;
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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_EMAIL, PREFIX_FACULTY, PREFIX_MAJOR,
                        PREFIX_SKILL, PREFIX_LANGUAGE, PREFIX_FRAMEWORK, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_FACULTY).isPresent()) {
            editPersonDescriptor.setFaculty(ParserUtil.parseFaculty(argMultimap.getValue(PREFIX_FACULTY).get()));
        }
        if (argMultimap.getValue(PREFIX_MAJOR).isPresent()) {
            editPersonDescriptor.setMajor(ParserUtil.parseMajor(argMultimap.getValue(PREFIX_MAJOR).get()));
        }
        parseSkillsForEdit(argMultimap.getAllValues(PREFIX_SKILL)).ifPresent(editPersonDescriptor::setSkills);
        parseLanguagesForEdit(argMultimap.getAllValues(PREFIX_LANGUAGE)).ifPresent(editPersonDescriptor::setLanguages);
        parseFrameworksForEdit(argMultimap.getAllValues(PREFIX_FRAMEWORK))
                .ifPresent(editPersonDescriptor::setFrameworks);
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> skills} into a {@code Set<Tag>} if {@code skills} is non-empty.
     * If {@code skills} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Skill>} containing zero skills.
     */
    private Optional<Set<Skill>> parseSkillsForEdit(Collection<String> skills) throws ParseException {
        assert skills != null;

        if (skills.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> skillSet = skills.size() == 1 && skills.contains("") ? Collections.emptySet() : skills;
        return Optional.of(ParserUtil.parseSkills(skillSet));
    }

    /**
     * Parses {@code Collection<String> languages} into a {@code Set<Language>} if {@code languages} is non-empty.
     * If {@code languages} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Language>} containing zero languages.
     */
    private Optional<Set<Language>> parseLanguagesForEdit(Collection<String> languages) throws ParseException {
        assert languages != null;

        if (languages.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> languageSet = languages.size() == 1 && languages.contains("")
                ? Collections.emptySet()
                : languages;
        return Optional.of(ParserUtil.parseLanguages(languageSet));
    }

    /**
     * Parses {@code Collection<String> frameworks} into a {@code Set<Framework>} if {@code frameworks} is non-empty.
     * If {@code frameworks} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Framework>} containing zero frameworks.
     */
    private Optional<Set<Framework>> parseFrameworksForEdit(Collection<String> frameworks) throws ParseException {
        assert frameworks != null;

        if (frameworks.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> frameworkSet = frameworks.size() == 1 && frameworks.contains("")
                ? Collections.emptySet()
                : frameworks;
        return Optional.of(ParserUtil.parseFrameworks(frameworkSet));
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
