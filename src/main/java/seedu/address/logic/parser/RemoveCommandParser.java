package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FRAMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERACTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LANGUAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARKS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemoveCommand;
import seedu.address.logic.commands.RemoveCommand.RemovePersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

public class RemoveCommandParser implements Parser<RemoveCommand> {

    /**
     * Parses the give {@code String} of arguments in the context of the RemoveCommand
     * and returns an RemoveCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public RemoveCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SKILL, PREFIX_LANGUAGE, PREFIX_FRAMEWORK, PREFIX_TAG,
                        PREFIX_REMARKS, PREFIX_INTERACTION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCommand.MESSAGE_USAGE));
        }

        RemovePersonDescriptor removePersonDescriptor = new RemovePersonDescriptor();

        parseSkillIndexesForRemove(argMultimap.getAllValues(PREFIX_SKILL))
                .ifPresent(removePersonDescriptor::setIndexesOfSkillsToRemove);
        parseLanguageIndexesForRemove(argMultimap.getAllValues(PREFIX_LANGUAGE))
                .ifPresent(removePersonDescriptor::setIndexesOfLanguagesToRemove);
        parseFrameworkIndexesForRemove(argMultimap.getAllValues(PREFIX_FRAMEWORK))
                .ifPresent(removePersonDescriptor::setIndexesOfFrameworksToRemove);
        parseTagIndexesForRemove(argMultimap.getAllValues(PREFIX_TAG))
                .ifPresent(removePersonDescriptor::setIndexesOfTagsToRemove);
        parseRemarkIndexesForRemove(argMultimap.getAllValues(PREFIX_REMARKS))
                .ifPresent(removePersonDescriptor::setIndexesOfRemarksToRemove);
        parseInteractionIndexesForRemove(argMultimap.getAllValues(PREFIX_INTERACTION))
                .ifPresent(removePersonDescriptor::setIndexesOfInteractionsToRemove);

        if (!removePersonDescriptor.isAnyFieldRemoved()) {
            throw new ParseException(RemoveCommand.MESSAGE_NOT_REMOVED);
        }

        return new RemoveCommand(index, removePersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> skills} into a {@code Set<Index>} if {@code skills} is non-empty.
     * If {@code indexes} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Index>} containing zero indexes.
     */
    private Optional<Set<Index>> parseSkillIndexesForRemove(Collection<String> indexes) throws ParseException {
        assert indexes != null;

        if (indexes.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> indexesOfSkills = indexes.size() == 1 && indexes.contains("")
                ? Collections.emptySet()
                : indexes;
        return Optional.of(ParserUtil.parseIndexes(indexesOfSkills));
    }

    /**
     * Parses {@code Collection<String> languages} into a {@code Set<Index>} if {@code languages} is non-empty.
     * If {@code indexes} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Index>} containing zero indexes.
     */
    private Optional<Set<Index>> parseLanguageIndexesForRemove(Collection<String> indexes) throws ParseException {
        assert indexes != null;

        if (indexes.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> indexesOfLanguages = indexes.size() == 1 && indexes.contains("")
                ? Collections.emptySet() : indexes;
        return Optional.of(ParserUtil.parseIndexes(indexesOfLanguages));
    }

    /**
     * Parses {@code Collection<String> frameworks} into a {@code Set<Index>} if {@code frameworks} is non-empty.
     * If {@code indexes} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Index>} containing zero indexes.
     */
    private Optional<Set<Index>> parseFrameworkIndexesForRemove(Collection<String> indexes) throws ParseException {
        assert indexes != null;

        if (indexes.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> indexesOfFrameworks = indexes.size() == 1 && indexes.contains("")
                ? Collections.emptySet() : indexes;
        return Optional.of(ParserUtil.parseIndexes(indexesOfFrameworks));
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Index>} if {@code tags} is non-empty.
     * If {@code indexes} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Index>} containing zero indexes.
     */
    private Optional<Set<Index>> parseTagIndexesForRemove(Collection<String> indexes) throws ParseException {
        assert indexes != null;

        if (indexes.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> indexesOfTags = indexes.size() == 1 && indexes.contains("")
                ? Collections.emptySet() : indexes;
        return Optional.of(ParserUtil.parseIndexes(indexesOfTags));
    }

    /**
     * Parses {@code Collection<String> remarks} into a {@code Set<Index>} if {@code remarks} is non-empty.
     * If {@code indexes} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Index>} containing zero indexes.
     */
    private Optional<Set<Index>> parseRemarkIndexesForRemove(Collection<String> indexes) throws ParseException {
        assert indexes != null;

        if (indexes.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> indexesOfRemarks = indexes.size() == 1 && indexes.contains("")
                ? Collections.emptySet() : indexes;
        return Optional.of(ParserUtil.parseIndexes(indexesOfRemarks));
    }

    /**
     * Parses {@code Collection<String> interactions} into a {@code Set<Index>} if {@code interactions} is non-empty.
     * If {@code indexes} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Index>} containing zero indexes.
     */
    private Optional<Set<Index>> parseInteractionIndexesForRemove(Collection<String> indexes) throws ParseException {
        assert indexes != null;

        if (indexes.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> indexesOfInteractions = indexes.size() == 1 && indexes.contains("")
                ? Collections.emptySet() : indexes;
        return Optional.of(ParserUtil.parseIndexes(indexesOfInteractions));
    }
}
