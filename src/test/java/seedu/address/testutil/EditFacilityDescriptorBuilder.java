package seedu.address.testutil;

import seedu.address.logic.commands.EditFacilityCommand.EditFacilityDescriptor;
import seedu.address.model.facility.Capacity;
import seedu.address.model.facility.Facility;
import seedu.address.model.facility.FacilityName;
import seedu.address.model.facility.Location;
import seedu.address.model.facility.Time;

/**
 * A utility class to help with building EditFacilityDescriptorBuilder objects.
 */
public class EditFacilityDescriptorBuilder {

    private EditFacilityDescriptor descriptor;

    public EditFacilityDescriptorBuilder() {
        this.descriptor = new EditFacilityDescriptor();
    }

    public EditFacilityDescriptorBuilder(EditFacilityDescriptor descriptor) {
        this.descriptor = new EditFacilityDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditFacilityDescriptor} with fields containing {@code facility}'s parameters.
     */
    public EditFacilityDescriptorBuilder(Facility facility) {
        descriptor = new EditFacilityDescriptor();
        descriptor.setFacilityName(facility.getName());
        descriptor.setLocation(facility.getLocation());
        descriptor.setTime(facility.getTime());
        descriptor.setCapacity(facility.getCapacity());
    }

    /**
     * Sets the {@code FacilityName} of the {@code EditFacilityDescriptor} that we are building.
     */
    public EditFacilityDescriptorBuilder withFacilityName(String name) {
        descriptor.setFacilityName(new FacilityName(name));
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code EditFacilityDescriptor} that we are building.
     */
    public EditFacilityDescriptorBuilder withLocation(String location) {
        descriptor.setLocation(new Location(location));
        return this;
    }

    /**
     * Sets the {@code Time} of the {@code EditFacilityDescriptor} that we are building.
     */
    public EditFacilityDescriptorBuilder withTime(String time) {
        descriptor.setTime(new Time(time));
        return this;
    }

    /**
     * Sets the {@code Capacity} of the {@code EditFacilityDescriptor} that we are building.
     */
    public EditFacilityDescriptorBuilder withCapacity(String capacity) {
        descriptor.setCapacity(new Capacity(capacity));
        return this;
    }

    public EditFacilityDescriptor build() {
        return descriptor;
    }
}
