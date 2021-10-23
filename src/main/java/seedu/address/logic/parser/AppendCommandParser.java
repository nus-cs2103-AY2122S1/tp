package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FRAMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LANGUAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARKS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AppendCommand;
import seedu.address.logic.commands.AppendCommand.AppendPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.remark.Remark;
import seedu.address.model.skill.Framework;
import seedu.address.model.skill.Language;
import seedu.address.model.skill.Skill;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AppendCommand object
 */
public class AppendCommandParser implements Parser<AppendCommand> {

    /**
     * Parses the give {@code String} of arguments in the context of the AppendCommand
     * and returns an AppendCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AppendCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SKILL, PREFIX_LANGUAGE,
                        PREFIX_FRAMEWORK, PREFIX_TAG, PREFIX_REMARKS);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AppendCommand.MESSAGE_USAGE), pe);
        }
        AppendPersonDescriptor appendPersonDescriptor = new AppendPersonDescriptor();
        parseSkillsForAppend(argMultimap.getAllValues(PREFIX_SKILL))
                .ifPresent(appendPersonDescriptor::setSkills);
        parseLanguagesForAppend(argMultimap.getAllValues(PREFIX_LANGUAGE))
                .ifPresent(appendPersonDescriptor::setLanguages);
        parseFrameworksForAppend(argMultimap.getAllValues(PREFIX_FRAMEWORK))
                .ifPresent(appendPersonDescriptor::setFrameworks);
        parseTagsForAppend(argMultimap.getAllValues(PREFIX_TAG))
                .ifPresent(appendPersonDescriptor::setTags);
        parseRemarksForAppend(argMultimap.getAllValues(PREFIX_REMARKS))
                .ifPresent(appendPersonDescriptor::setRemarks);

        if (!appendPersonDescriptor.isAnyFieldAppended()) {
            throw new ParseException(AppendCommand.MESSAGE_NOT_APPENDED);
        }

        return new AppendCommand(index, appendPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> skills} into a {@code Set<Skill>} if {@code skills} is non-empty.
     * If {@code skills} contains only one element which is an empty string, it will be parsed into a
     * {@code Set<Skill>} containing zero skills.
     */
    private Optional<Set<Skill>> parseSkillsForAppend(Collection<String> skills) throws ParseException {
        assert skills != null;

        if (skills.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> skillSet = skills.size() == 1 && skills.contains("")
                ? Collections.emptySet()
                : skills;
        return Optional.of(ParserUtil.parseSkills(skillSet));
    }

    /**
     * Parses {@code Collection<String> languages} into a {@code Set<Language>} if {@code languages} is non-empty.
     * If {@code languages} contains only one element which is an empty string, it will be parsed into a
     * {@code Set<Skill>} containing zero languages.
     */
    private Optional<Set<Language>> parseLanguagesForAppend(Collection<String> languages) throws ParseException {
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
     * If {@code frameworks} contains only one element which is an empty string, it will be parsed into a
     * {@code Set<Skill>} containing zero frameworks.
     */
    private Optional<Set<Framework>> parseFrameworksForAppend(Collection<String> frameworks) throws ParseException {
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
     * If {@code tags} contains only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForAppend(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("")
                ? Collections.emptySet()
                : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

    /**
     * Parses {@code Collection<String> remarks} into a {@code Set<Remark>} if {@code remarks} is non-empty.
     * If {@code remarks} contains only one element which is an empty string, it will be parsed into a
     * {@code Set<Remark>} containing zero remarks.
     */
    private Optional<Set<Remark>> parseRemarksForAppend(Collection<String> remarks) throws ParseException {
        assert remarks != null;

        if (remarks.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> remarkSet = remarks.size() == 1 && remarks.contains("")
                ? Collections.emptySet()
                : remarks;
        return Optional.of(ParserUtil.parseRemarks(remarkSet));
    }
}
