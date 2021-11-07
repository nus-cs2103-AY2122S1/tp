package seedu.address.testutil;

import seedu.address.logic.commands.RenameEventCommand;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.person.UniquePersonList;

public class RenameEventDescriptorBuilder {

    private RenameEventCommand.RenameEventDescriptor descriptor;

    public RenameEventDescriptorBuilder() {
        descriptor = new RenameEventCommand.RenameEventDescriptor();
    }

    public RenameEventDescriptorBuilder(RenameEventCommand.RenameEventDescriptor descriptor) {
        this.descriptor = new RenameEventCommand.RenameEventDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code event}'s details
     */
    public RenameEventDescriptorBuilder(Event event) {
        descriptor = new RenameEventCommand.RenameEventDescriptor();
        descriptor.setEventName(event.getEventName());
        descriptor.setAttendees(event.getAttendees());
    }

    /**
     * Sets the {@code EventName} of the {@code RenameEventDescriptor} that we are building.
     */
    public RenameEventDescriptorBuilder withName(String eventName) {
        descriptor.setEventName(new EventName(eventName));
        return this;
    }

    /**
     * Sets the {@code UniquePersonList} of the {@code RenameEventDescriptor} that we are building.
     */
    public RenameEventDescriptorBuilder withName(UniquePersonList attendees) {
        UniquePersonList builderAttendees = new UniquePersonList();
        builderAttendees.setPersons(attendees);
        descriptor.setAttendees(builderAttendees);
        return this;
    }

    public RenameEventCommand.RenameEventDescriptor build() {
        return descriptor;
    }
}
