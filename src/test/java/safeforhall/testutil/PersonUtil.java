package safeforhall.testutil;

import static safeforhall.logic.parser.CliSyntax.PREFIX_COLLECTIONDATE;
import static safeforhall.logic.parser.CliSyntax.PREFIX_EMAIL;
import static safeforhall.logic.parser.CliSyntax.PREFIX_FACULTY;
import static safeforhall.logic.parser.CliSyntax.PREFIX_FETDATE;
import static safeforhall.logic.parser.CliSyntax.PREFIX_NAME;
import static safeforhall.logic.parser.CliSyntax.PREFIX_PHONE;
import static safeforhall.logic.parser.CliSyntax.PREFIX_ROOM;
import static safeforhall.logic.parser.CliSyntax.PREFIX_VACCSTATUS;

import safeforhall.logic.commands.add.AddPersonCommand;
import safeforhall.logic.commands.edit.EditPersonCommand.EditPersonDescriptor;
import safeforhall.model.person.Person;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddPersonCommand.COMMAND_WORD + " " + getPersonDetails(person);
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
        descriptor.getRoom().ifPresent(room -> sb.append(PREFIX_ROOM).append(room.room).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getVaccStatus().ifPresent(vaccStatus -> sb.append(PREFIX_VACCSTATUS)
                .append(vaccStatus.vaccStatus).append(" "));
        descriptor.getFaculty().ifPresent(faculty -> sb.append(PREFIX_FACULTY).append(faculty.faculty).append(" "));
        descriptor.getLastFetDate().ifPresent(lastFetDate -> sb.append(PREFIX_FETDATE)
                .append(lastFetDate.date).append(" "));
        descriptor.getLastCollectionDate().ifPresent(lastCollectionDate -> sb.append(PREFIX_COLLECTIONDATE)
                .append(lastCollectionDate.date).append(" "));
        return sb.toString();
    }
}
