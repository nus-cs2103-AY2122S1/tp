package seedu.address.testutil;

import seedu.address.logic.commands.EditEventCommand.EditEventDescriptor;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;

/**
 * This is an EditEventDescriptorBuilder that facilitates Junit testing of EditEventCommand.
 */
public class EditEventDescriptorBuilder {
    private EditEventDescriptor descriptor;

    /**
     * This is the constructor for EditEventDescriptorBuilder.
     */
    public EditEventDescriptorBuilder() {
        this.descriptor = new EditEventDescriptor();
    }

    /**
     * Returns an {@code EditEventDescriptor} with fields containing {@code event}'s details.
     */
    public EditEventDescriptorBuilder(Event event) {
        descriptor = new EditEventDescriptor();
        descriptor.setEventName(event.getName());
        descriptor.setEventDate(event.getDate());
        descriptor.setEventTime(event.getTime());
    }

    /**
     * Sets the {@code eventName} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withEventName(String eventName) {
        descriptor.setEventName(new EventName(eventName));
        return this;
    }

    /**
     * Sets the {@code eventDate} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withEventDate(String eventDate) {
        descriptor.setEventDate(new EventDate(eventDate));
        return this;
    }

    /**
     * Sets the {@code eventTime} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withEventTime(String eventTime) {
        descriptor.setEventTime(new EventTime(eventTime));
        return this;
    }

    /**
     * Returns a EditEventDescriptor created using EditEventDescriptorBuilder.
     *
     * @return an instance of EditEventDescriptor.
     */
    public EditEventDescriptor build() {
        return descriptor;
    }
}
