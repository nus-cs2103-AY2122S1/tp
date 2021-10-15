package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FACULTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FRAMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LANGUAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Person;
import seedu.address.model.skill.Framework;
import seedu.address.model.skill.Language;
import seedu.address.model.skill.Skill;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_FACULTY + person.getFaculty().value + " ");
        sb.append(PREFIX_MAJOR + person.getMajor().value + " ");

        person.getSkills().stream().forEach(
            s -> sb.append(PREFIX_SKILL + s.skillName + " ")
        );

        person.getLanguages().stream().forEach(
            s -> sb.append(PREFIX_LANGUAGE + s.languageName + " ")
        );

        person.getFrameworks().stream().forEach(
            s -> sb.append(PREFIX_FRAMEWORK + s.frameworkName + " ")
        );

        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );

        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getFaculty().ifPresent(faculty -> sb.append(PREFIX_FACULTY).append(faculty.value).append(" "));
        descriptor.getMajor().ifPresent(major -> sb.append(PREFIX_MAJOR).append(major.value).append(" "));

        if (descriptor.getSkills().isPresent()) {
            Set<Skill> skills = descriptor.getSkills().get();
            if (skills.isEmpty()) {
                sb.append(PREFIX_SKILL).append(" "); // TODO: check why this is here
            } else {
                skills.forEach(s -> sb.append(PREFIX_SKILL).append(s.skillName).append(" "));
            }
        }

        if (descriptor.getLanguages().isPresent()) {
            Set<Language> languages = descriptor.getLanguages().get();
            if (languages.isEmpty()) {
                sb.append(PREFIX_LANGUAGE).append(" "); // TODO: check why this is here
            } else {
                languages.forEach(s -> sb.append(PREFIX_LANGUAGE).append(s.languageName).append(" "));
            }
        }

        if (descriptor.getFrameworks().isPresent()) {
            Set<Framework> frameworks = descriptor.getFrameworks().get();
            if (frameworks.isEmpty()) {
                sb.append(PREFIX_FRAMEWORK).append(" "); // TODO: check why this is here
            } else {
                frameworks.forEach(s -> sb.append(PREFIX_FRAMEWORK).append(s.frameworkName).append(" "));
            }
        }

        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG); // TODO: check why this is here
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
