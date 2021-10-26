package seedu.address.model.schedule;

import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.person.Address;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

public class Appointment {

    private final UniquePersonList clients;
    private final Address location;
    private TimePeriod timePeriod;
    private String description;


    /**
     * Creates an Appointment class with a specified time.
     */
    public Appointment(UniquePersonList clients, Address location, TimePeriod timePeriod, String description) {
        this.clients = clients;
        this.location = location;
        this.timePeriod = timePeriod;
        this.description = description;
    }

    public UniquePersonList getClients() {
        return clients;
    }

    public Address getLocation() {
        return location;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public String getDescription() {
        return description;
    }

    public ObservableList<Person> getClientList() {
        return clients.asUnmodifiableObservableList();
    }

    /**
     * Checks if this appointment is related to the client.
     * @param person the client to check with.
     * @return true if the client is related and false otherwise.
     */
    public boolean hasClient(Person person) {
        return this.clients.contains(person);
    }

    /**
     * Returns true if both Appointments have the same fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherApp = (Appointment) other;
        return otherApp.getClients().equals(getClients())
                && otherApp.getLocation().equals(getLocation())
                && otherApp.getTimePeriod().equals(getTimePeriod())
                && otherApp.getDescription().equals(getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(clients, location, timePeriod, description);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescription())
                .append("; Clients: ")
                .append(getClients())
                .append("; Location: ")
                .append(getLocation())
                .append("; Time Period: ")
                .append(getTimePeriod());

        return builder.toString();
    }
}
