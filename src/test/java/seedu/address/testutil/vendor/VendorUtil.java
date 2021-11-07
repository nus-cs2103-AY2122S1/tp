package seedu.address.testutil.vendor;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPERATING_HOURS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENDOR_ID;

import java.util.Set;

import seedu.address.logic.commands.vendor.AddVendorCommand;
import seedu.address.logic.commands.vendor.EditVendorCommand.EditVendorDescriptor;
import seedu.address.model.tag.Tag;
import seedu.address.model.vendor.Vendor;

/**
 * A utility class for Vendor.
 */
public class VendorUtil {

    /**
     * Returns an add command string for adding the {@code vendor}.
     */
    public static String getAddCommand(Vendor vendor) {
        return AddVendorCommand.COMMAND_WORD + " " + getVendorDetails(vendor);
    }

    /**
     * Returns the part of command string for the given {@code vendor}'s details.
     */
    public static String getVendorDetails(Vendor vendor) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + vendor.getName().fullName + " ");
        sb.append(PREFIX_EMAIL + vendor.getEmail().value + " ");
        sb.append(PREFIX_VENDOR_ID + vendor.getVendorId().value + " ");
        sb.append(PREFIX_PHONE + vendor.getPhone().value + " ");
        sb.append(PREFIX_ADDRESS + vendor.getAddress().value + " ");
        sb.append(PREFIX_COST + vendor.getCost().value.toString() + " ");
        sb.append(PREFIX_SERVICE_NAME + vendor.getServiceName().serviceName + " ");
        sb.append(PREFIX_OPERATING_HOURS + vendor.getOperatingHours().operatingHoursStringRep + " ");
        vendor.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditVendorDescriptor}'s details.
     */
    public static String getEditVendorDescriptorDetails(EditVendorDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getVendorId().ifPresent(vendorId -> sb.append(PREFIX_VENDOR_ID).append(vendorId.value).append(" "));
        descriptor.getServiceName().ifPresent(
            serviceName -> sb.append(PREFIX_SERVICE_NAME).append(serviceName.serviceName).append(" "));
        descriptor.getCost().ifPresent(cost -> sb.append(PREFIX_COST).append(cost.value.toString()).append(" "));
        descriptor.getOperatingHours().ifPresent(operatingHours -> sb.append(PREFIX_OPERATING_HOURS).append(
            operatingHours.operatingHoursStringRep).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}

