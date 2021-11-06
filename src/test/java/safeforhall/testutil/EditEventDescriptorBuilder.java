package safeforhall.testutil;

import safeforhall.logic.commands.edit.EditEventCommand;
import safeforhall.model.event.Capacity;
import safeforhall.model.event.Event;
import safeforhall.model.event.EventDate;
import safeforhall.model.event.EventName;
import safeforhall.model.event.EventTime;
import safeforhall.model.event.Venue;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditEventDescriptorBuilder {

    private EditEventCommand.EditEventDescriptor descriptor;

    public EditEventDescriptorBuilder() {
        descriptor = new EditEventCommand.EditEventDescriptor();
    }

    public EditEventDescriptorBuilder(EditEventCommand.EditEventDescriptor descriptor) {
        this.descriptor = new EditEventCommand.EditEventDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditEventDescriptor} with fields containing {@code event}'s details
     */
    public EditEventDescriptorBuilder(Event event) {
        descriptor = new EditEventCommand.EditEventDescriptor();
        descriptor.setName(event.getEventName());
        descriptor.setDate(event.getEventDate());
        descriptor.setTime(event.getEventTime());
        descriptor.setVenue(event.getVenue());
        descriptor.setCapacity(event.getCapacity());
        descriptor.setTime(event.getEventTime());
    }

    /**
     * Sets the {@code EventName} of the {@code EditEventDescriptor} that we are building.
     */
    public safeforhall.testutil.EditEventDescriptorBuilder withName(String name) {
        descriptor.setName(new EventName(name));
        return this;
    }

    /**
     * Sets the {@code EventDate} of the {@code EditEventDescriptor} that we are building.
     */
    public safeforhall.testutil.EditEventDescriptorBuilder withDate(String date) {
        descriptor.setDate(new EventDate(date));
        return this;
    }

    /**
     * Sets the {@code EventTime} of the {@code EditEventDescriptor} that we are building.
     */
    public safeforhall.testutil.EditEventDescriptorBuilder withTime(String time) {
        descriptor.setTime(new EventTime(time));
        return this;
    }

    /**
     * Sets the {@code Venue} of the {@code EditEventDescriptor} that we are building.
     */
    public safeforhall.testutil.EditEventDescriptorBuilder withVenue(String venue) {
        descriptor.setVenue(new Venue(venue));
        return this;
    }

    /**
     * Sets the {@code Capacity} of the {@code EditEventDescriptor} that we are building.
     */
    public safeforhall.testutil.EditEventDescriptorBuilder withCapacity(String capacity) {
        descriptor.setCapacity(new Capacity(capacity));
        return this;
    }

    public EditEventCommand.EditEventDescriptor build() {
        return descriptor;
    }
}

