package seedu.address.model.history_states;

import seedu.address.model.AddressBook;
import seedu.address.model.schedule.Schedule;

public class State {
    private AddressBook addressBook;
    private Schedule schedule;

    /**
     * A constructor to initialize the {@Code State} with the given {@Code AddressBook} and {@Code Schedule}.
     * The given {@Code AddressBook} and {@Code Schedule} will be copied and store in this {@Code State}.
     * @param addressBook The given {@Code AddressBook}.
     * @param schedule The given {@Code Schedule}.
     */
    public State(AddressBook addressBook, Schedule schedule) {
        this.addressBook = new AddressBook(addressBook);
        this.schedule = new Schedule(schedule);
    }

    public AddressBook getAddressBook() {
        return new AddressBook(this.addressBook);
    }

    public Schedule getSchedule() {
        return new Schedule(this.schedule);
    }
}
