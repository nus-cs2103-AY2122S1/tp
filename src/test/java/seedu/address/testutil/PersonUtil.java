package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEALTH_CONDITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LANGUAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAST_VISIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.healthcondition.HealthCondition;
import seedu.address.model.person.LastVisit;
import seedu.address.model.person.Person;

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
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_LANGUAGE + person.getLanguage().value + " ");
        sb.append(PREFIX_LAST_VISIT + person.getLastVisit().orElse(new LastVisit("")).value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        person.getHealthConditions().stream().forEach(
            s -> sb.append(PREFIX_HEALTH_CONDITION + s.healthCondition + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getLanguage().ifPresent(language -> sb.append(PREFIX_LANGUAGE).append(language.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getLastVisit().ifPresent(lastVisit -> sb.append(PREFIX_LAST_VISIT).append(lastVisit.value)
                .append(" "));
        if (descriptor.getHealthConditions().isPresent()) {
            Set<HealthCondition> tags = descriptor.getHealthConditions().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_HEALTH_CONDITION);
            } else {
                tags.forEach(s -> sb.append(PREFIX_HEALTH_CONDITION).append(s.healthCondition).append(" "));
            }
        }
        return sb.toString();
    }
}
