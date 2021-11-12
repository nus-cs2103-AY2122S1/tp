package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CAPACITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.logic.commands.EditFacilityCommand;

/**
 * A utility class for Facility.
 */
public class FacilityUtil {

    /**
     * Returns the part of command string for the given {@code EditFacilityDescriptor}'s details.
     */
    public static String getEditFacilityDescriptorDetails(EditFacilityCommand.EditFacilityDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getFacilityName()
                .ifPresent(facilName -> sb.append(PREFIX_NAME).append(facilName.facilityName).append(" "));
        descriptor.getLocation()
                .ifPresent(location -> sb.append(PREFIX_LOCATION).append(location.location).append(" "));
        descriptor.getTime()
                .ifPresent(time -> sb.append(PREFIX_TIME).append(time.time).append(" "));
        descriptor.getCapacity()
                .ifPresent(capacity -> sb.append(PREFIX_CAPACITY).append(capacity.capacity).append(" "));
        return sb.toString();
    }
}
