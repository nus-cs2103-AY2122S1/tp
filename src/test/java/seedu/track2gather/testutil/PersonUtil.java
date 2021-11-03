package seedu.track2gather.testutil;

import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_CASE_NUMBER;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_HOME_ADDRESS;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_NEXT_OF_KIN_ADDRESS;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_NEXT_OF_KIN_NAME;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_NEXT_OF_KIN_PHONE;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_QUARANTINE_ADDRESS;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_SHN_PERIOD;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_WORK_ADDRESS;

import seedu.track2gather.logic.commands.AddCommand;
import seedu.track2gather.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.track2gather.model.person.Person;

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
        sb.append(PREFIX_NAME + person.getName().value + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_CASE_NUMBER + person.getCaseNumber().value + " ");
        sb.append(PREFIX_HOME_ADDRESS + person.getHomeAddress().value + " ");
        person.getWorkAddress().value.ifPresent(address -> sb.append(PREFIX_WORK_ADDRESS + address.value + " "));
        person.getQuarantineAddress().value.ifPresent(address ->
                sb.append(PREFIX_QUARANTINE_ADDRESS + address.value + " "));
        person.getShnPeriod().value.ifPresent(period ->
                sb.append(PREFIX_SHN_PERIOD + period.getStartDate().toString() + " "
                        + period.getEndDate().toString() + " "));
        person.getNextOfKinName().value.ifPresent(name ->
                sb.append(PREFIX_NEXT_OF_KIN_NAME + name.value + " "));
        person.getNextOfKinPhone().value.ifPresent(phone ->
                sb.append(PREFIX_NEXT_OF_KIN_PHONE + phone.value + " "));
        person.getNextOfKinAddress().value.ifPresent(address ->
                sb.append(PREFIX_NEXT_OF_KIN_ADDRESS + address.value + " "));
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME + name.value + " "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE + phone.value + " "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL + email.value + " "));
        descriptor.getCaseNumber().ifPresent(caseNumber -> sb.append(PREFIX_CASE_NUMBER + caseNumber.value + " "));
        descriptor.getHomeAddress().ifPresent(homeAddress -> sb.append(PREFIX_HOME_ADDRESS + homeAddress.value + " "));
        descriptor.getWorkAddress().flatMap(workAddress -> workAddress.value)
                .ifPresent(address -> sb.append(PREFIX_WORK_ADDRESS + address.value + " "));
        descriptor.getQuarantineAddress().flatMap(quarantineAddress -> quarantineAddress.value)
                .ifPresent(address -> sb.append(PREFIX_QUARANTINE_ADDRESS + address.value + " "));
        descriptor.getShnPeriod().flatMap(shnPeriod -> shnPeriod.value)
                .ifPresent(period -> sb.append(PREFIX_SHN_PERIOD + period.getStartDate().toString() + " "
                        + period.getEndDate().toString() + " "));
        descriptor.getNextOfKinName().flatMap(nextOfKinName -> nextOfKinName.value)
                .ifPresent(name -> sb.append(PREFIX_NEXT_OF_KIN_NAME + name.value + " "));
        descriptor.getNextOfKinPhone().flatMap(nextOfKinPhone -> nextOfKinPhone.value)
                .ifPresent(phone -> sb.append(PREFIX_NEXT_OF_KIN_PHONE + phone.value + " "));
        descriptor.getNextOfKinAddress().flatMap(nextOfKinAddress -> nextOfKinAddress.value)
                .ifPresent(address -> sb.append(PREFIX_NEXT_OF_KIN_ADDRESS + address.value + " "));
        return sb.toString();
    }
}
