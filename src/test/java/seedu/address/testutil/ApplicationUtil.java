package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE_OF_APPLICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REQUIREMENT;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditApplicationDescriptor;
import seedu.address.model.application.Application;
import seedu.address.model.application.Requirement;

/**
 * A utility class for Application.
 */
public class ApplicationUtil {

    /**
     * Returns an add command string for adding the {@code application}.
     */
    public static String getAddCommand(Application application) {
        return AddCommand.COMMAND_WORD + " " + getApplicationDetails(application);
    }

    /**
     * Returns the part of command string for the given {@code application}'s details.
     */
    public static String getApplicationDetails(Application application) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_COMPANY_NAME + application.getCompany().fullCompanyName + " ");
        sb.append(PREFIX_INTERNSHIP_POSITION + application.getPosition().value + " ");
        sb.append(PREFIX_DEADLINE_OF_APPLICATION + application.getDeadline().value + " ");

        application.getRequirements().stream().forEach(
            s -> sb.append(PREFIX_REQUIREMENT + s.value + " ")
        );

        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditApplicationDescriptor}'s details.
     */
    public static String getEditApplicationDescriptorDetails(EditApplicationDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getCompany().ifPresent(name -> sb.append(PREFIX_COMPANY_NAME)
                .append(name.fullCompanyName).append(" "));
        descriptor.getPosition().ifPresent(position -> sb.append(PREFIX_INTERNSHIP_POSITION)
                .append(position.value).append(" "));
        descriptor.getDeadline().ifPresent(deadline -> sb.append(PREFIX_DEADLINE_OF_APPLICATION)
                .append(deadline.value).append(" "));

        if (descriptor.getRequirements().isPresent()) {
            Set<Requirement> requirements = descriptor.getRequirements().get();
            if (requirements.isEmpty()) {
                sb.append(PREFIX_REQUIREMENT);
            } else {
                requirements.forEach(s -> sb.append(PREFIX_REQUIREMENT).append(s.value).append(" "));
            }
        }

        return sb.toString();
    }
}
