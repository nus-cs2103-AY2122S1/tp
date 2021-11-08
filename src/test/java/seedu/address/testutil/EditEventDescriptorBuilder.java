package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditEventDescriptor;
import seedu.address.model.event.Date;
import seedu.address.model.event.Event;
import seedu.address.model.event.Location;
import seedu.address.model.event.Name;
import seedu.address.model.event.Remark;
import seedu.address.model.event.TimeSlot;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditEventDescriptor objects.
 */
public class EditEventDescriptorBuilder {

    private EditEventDescriptor descriptor;

    public EditEventDescriptorBuilder() {
        descriptor = new EditEventDescriptor();
    }

    public EditEventDescriptorBuilder(EditCommand.EditEventDescriptor descriptor) {
        this.descriptor = new EditCommand.EditEventDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditEventDescriptor} with fields containing {@code event}'s details
     */
    public EditEventDescriptorBuilder(Event event) {
        descriptor = new EditEventDescriptor();
        descriptor.setName(event.getName());
        descriptor.setDate(event.getDate());
        descriptor.setTimeSlot(event.getTimeSlot());
        descriptor.setLocation(event.getLocation());
        descriptor.setTags(event.getTags());
        descriptor.setRemark(event.getRemark());
    }

    /**
     * Sets the {@code Name} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withDate(String date) {
        descriptor.setDate(new Date(date));
        return this;
    }

    /**
     * Sets the {@code TimeSlot} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withTimeSlot(String startTime, String endTime) {
        descriptor.setTimeSlot(new TimeSlot(startTime, endTime));
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withLocation(String address) {
        descriptor.setLocation(new Location(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditEventDescriptor}
     * that we are building.
     */
    public EditEventDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withRemark(String remark) {
        descriptor.setRemark(new Remark(remark));
        return this;
    }

    public EditEventDescriptor build() {
        return descriptor;
    }
}
