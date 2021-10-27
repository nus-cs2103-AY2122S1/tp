package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FACULTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FRAMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LANGUAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.model.Model;
import seedu.address.model.person.ContactHasFacultyPredicate;
import seedu.address.model.person.ContactHasFrameworkPredicate;
import seedu.address.model.person.ContactHasLanguagePredicate;
import seedu.address.model.person.ContactHasMajorPredicate;
import seedu.address.model.person.ContactHasSkillPredicate;
import seedu.address.model.person.ContactHasTagPredicate;
import seedu.address.model.person.Faculty;
import seedu.address.model.person.Major;
import seedu.address.model.skill.Framework;
import seedu.address.model.skill.Language;
import seedu.address.model.skill.Skill;
import seedu.address.model.tag.Tag;

/**
 * Finds and lists all persons in ComputingConnection which have the specified tags attached to them.
 * tag matching is case insensitive.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all contacts which have "
            + "the specified tag (case-insensitive) attached to them and displays them as a list with index numbers.\n"
            + "Parameters: TAGNAME [MORE_TAGNAMES]...\n"
            + "Example: " + COMMAND_WORD + " r/staff f/computing";

    private ContactHasTagPredicate tagPredicate;
    private ContactHasFacultyPredicate facultyPredicate;
    private ContactHasMajorPredicate majorPredicate;
    private ContactHasFrameworkPredicate frameworkPredicate;
    private ContactHasLanguagePredicate languagePredicate;
    private ContactHasSkillPredicate skillPredicate;
    private final ArgumentMultimap argMultimap;

    public FilterCommand(ArgumentMultimap argMultimap) {
        this.argMultimap = argMultimap;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (argMultimap.getValue(PREFIX_FACULTY).isPresent()) {
            List<String> list = argMultimap.getAllValues(PREFIX_FACULTY);
            for (String s : list) {
                if (!Faculty.isValidFaculty(s)) {
                    return new CommandResult("Faculty not found: " + s);
                }
            }
            facultyPredicate = new ContactHasFacultyPredicate(list);
            model.updateFilteredPersonList(facultyPredicate);
        }

        if (argMultimap.getValue(PREFIX_MAJOR).isPresent()) {
            List<String> list = argMultimap.getAllValues(PREFIX_MAJOR);
            for (String s : list) {
                if (!Major.isValidMajor(s)) {
                    return new CommandResult("Major not found: " + s);
                }
            }
            majorPredicate = new ContactHasMajorPredicate(list);
            model.updateFilteredPersonList(majorPredicate);
        }

        if (argMultimap.getValue(PREFIX_SKILL).isPresent()) {
            List<String> list = argMultimap.getAllValues(PREFIX_SKILL);
            for (String s : list) {
                if (!Skill.isValidSkillName(s)) {
                    return new CommandResult("Skill name is invalid: " + s);
                }
            }
            skillPredicate = new ContactHasSkillPredicate(list);
            model.updateFilteredPersonList(skillPredicate);
        }

        if (argMultimap.getValue(PREFIX_FRAMEWORK).isPresent()) {
            List<String> list = argMultimap.getAllValues(PREFIX_FRAMEWORK);
            for (String s : list) {
                if (!Framework.isValidFrameworkName(s)) {
                    return new CommandResult("Framework name is invalid: " + s);
                }
            }
            frameworkPredicate = new ContactHasFrameworkPredicate(list);
            model.updateFilteredPersonList(frameworkPredicate);
        }

        if (argMultimap.getValue(PREFIX_LANGUAGE).isPresent()) {
            List<String> list = argMultimap.getAllValues(PREFIX_LANGUAGE);
            for (String s : list) {
                if (!Language.isValidLanguageName(s)) {
                    return new CommandResult("Language name is invalid: " + s);
                }
            }
            languagePredicate = new ContactHasLanguagePredicate(list);
            model.updateFilteredPersonList(languagePredicate);
        }

        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            List<String> list = argMultimap.getAllValues(PREFIX_TAG);
            for (String s : list) {
                if (!Tag.isValidTagName(s)) {
                    return new CommandResult("Tag name is invalid: " + s);
                }
            }
            tagPredicate = new ContactHasTagPredicate(list);
            model.updateFilteredPersonList(tagPredicate);
        }

        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                && tagPredicate.equals(((FilterCommand) other).tagPredicate)
                && facultyPredicate.equals(((FilterCommand) other).facultyPredicate)
                && majorPredicate.equals(((FilterCommand) other).majorPredicate));
    }

}
