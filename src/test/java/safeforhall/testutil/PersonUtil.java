package safeforhall.testutil;

import static safeforhall.logic.parser.CliSyntax.PREFIX_COLLECTIONDATE;
import static safeforhall.logic.parser.CliSyntax.PREFIX_EMAIL;
import static safeforhall.logic.parser.CliSyntax.PREFIX_FACULTY;
import static safeforhall.logic.parser.CliSyntax.PREFIX_FETDATE;
import static safeforhall.logic.parser.CliSyntax.PREFIX_NAME;
import static safeforhall.logic.parser.CliSyntax.PREFIX_PHONE;
import static safeforhall.logic.parser.CliSyntax.PREFIX_ROOM;
import static safeforhall.logic.parser.CliSyntax.PREFIX_VACCSTATUS;

import safeforhall.logic.commands.AddCommand;
import safeforhall.logic.commands.EditCommand.EditPersonDescriptor;
import safeforhall.model.person.Person;

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
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ROOM + person.getRoom().room + " ");
        sb.append(PREFIX_FACULTY + person.getFaculty().faculty + " ");
        sb.append(PREFIX_VACCSTATUS + person.getVaccStatus().vaccStatus + " ");
        sb.append(PREFIX_FETDATE + person.getLastFetDate().date + " ");
        sb.append(PREFIX_COLLECTIONDATE + person.getLastCollectionDate().date + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        //descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        /*if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();*/
        //TODO
        return "";
    }
}
