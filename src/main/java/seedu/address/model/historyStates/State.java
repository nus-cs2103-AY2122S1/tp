package seedu.address.model.historyStates;

import seedu.address.model.AddressBook;
import seedu.address.model.schedule.Schedule;

public class State {
    private AddressBook addressBook;
    private Schedule schedule;

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
