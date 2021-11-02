package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELE_HANDLE;

import java.util.Set;

import seedu.address.logic.commands.person.EditPersonCommand.EditPersonDescriptor;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Person;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_TELE_HANDLE + person.getTeleHandle().value + " ");
        sb.append(PREFIX_REMARK + person.getRemark().value + " ");
        person.getModuleCodes().stream().forEach(
            s -> sb.append(PREFIX_MODULE_CODE + s.value + " ")
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
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getTeleHandle()
                .ifPresent(teleHandle -> sb.append(PREFIX_TELE_HANDLE).append(teleHandle.value).append(" "));
        descriptor.getRemark().ifPresent(remark -> sb.append(PREFIX_REMARK).append(remark.value).append(" "));
        if (descriptor.getModuleCodes().isPresent()) {
            Set<ModuleCode> moduleCodes = descriptor.getModuleCodes().get();
            if (moduleCodes.isEmpty()) {
                sb.append(PREFIX_MODULE_CODE);
            } else {
                moduleCodes.forEach(s -> sb.append(PREFIX_MODULE_CODE).append(s.value).append(" "));
            }
        }
        return sb.toString();
    }
}
