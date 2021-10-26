package seedu.address.testutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.model.person.Address;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.schedule.Appointment;
import seedu.address.model.schedule.TimePeriod;

/**
 * A utility class to help with building Appointment Objects.
 */
public class AppointmentBuilder {
    private static final String DEFAULT_CLIENT = "ALICE";
    private static final String DEFAULT_LOCATION = "vivocity";
    private static final String DEFAULT_DESCRIPTION = "Halloween Sales";

    private UniquePersonList clients;
    private Address location;
    private TimePeriod timePeriod;
    private String description;

    /**
     * Creates a {@code} AppointmentBuilder with the default details.
     */
    public AppointmentBuilder() {
        UniquePersonList clients = new UniquePersonList();
        clients.add(new PersonBuilder().withName(DEFAULT_CLIENT).build());
        this.clients = clients;
        this.location = new Address(DEFAULT_LOCATION);
        this.timePeriod = new TimePeriod(LocalDateTime.of(2021, 12, 25, 21, 30),
                LocalDateTime.of(2021, 12, 25, 22, 30));
        this.description = DEFAULT_DESCRIPTION;
    }

    /**
     * Initializes the AppointmentBuilder with the data of {@code appointmentToCopy}.
     * @param appointmentToCopy The given appointment to copy.
     */
    public AppointmentBuilder(Appointment appointmentToCopy) {
        this.clients = appointmentToCopy.getClients();
        this.location = appointmentToCopy.getLocation();
        this.timePeriod = appointmentToCopy.getTimePeriod();
        this.description = appointmentToCopy.getDescription();
    }

    /**
     * Sets the {@code clients} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withClient(Person client) {
        UniquePersonList clients = new UniquePersonList();
        clients.add(client);
        this.clients = clients;
        return this;
    }

    /**
     * Sets the {@code clients} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withClient(String clientName) {
        UniquePersonList clients = new UniquePersonList();
        clients.add(new PersonBuilder().withName(clientName).build());
        this.clients = clients;
        return this;
    }

    /**
     * Adds a {@code Person} to the {@code clients} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder addClient(Person client) {
        this.clients.add(client);
        return this;
    }

    /**
     * Adds a {@code Person} to the {@code clients} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder addClient(String clientName) {
        this.clients.add(new PersonBuilder().withName(clientName).build());
        return this;
    }

    /**
     * Sets the {@code location} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withLocation(String location) {
        this.location = new Address(location);
        return this;
    }

    /**
     * Sets the {@code TimePeriod} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withTimePeriod(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
        return this;
    }

    /**
     * Sets the {@code TimePeriod} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withTimePeriod(String startTime, String endTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
        this.timePeriod = new TimePeriod(LocalDateTime.parse(startTime, formatter),
                LocalDateTime.parse(endTime, formatter));
        return this;
    }


    /**
     * Sets the {@code description} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public Appointment build() {
        return new Appointment(clients, location, timePeriod, description);
    }
}
