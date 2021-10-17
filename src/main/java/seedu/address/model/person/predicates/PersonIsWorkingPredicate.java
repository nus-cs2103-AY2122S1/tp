package seedu.address.model.person.predicates;

import seedu.address.logic.commands.FindScheduleCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Person;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.function.Predicate;

public class PersonIsWorkingPredicate implements Predicate<Person> {

    private final DayOfWeek dayOfWeek;
    private final int slotNum;
    private final LocalTime time;

    public PersonIsWorkingPredicate(DayOfWeek dayOfWeek, int slotNum, LocalTime time) {
        this.dayOfWeek = dayOfWeek;
        this.slotNum = slotNum;
        this.time = time;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonIsWorkingPredicate // instanceof handles nulls
                && (this.dayOfWeek.equals(((PersonIsWorkingPredicate) other).dayOfWeek))
                    && (this.slotNum == ((PersonIsWorkingPredicate) other).slotNum)
                    && (this.time.equals(((PersonIsWorkingPredicate) other).time)));
    }

    @Override
    public boolean test(Person person) {

        if (time != null && dayOfWeek != null) {
            return person.isWorking(dayOfWeek, time);
        } else if (slotNum != FindScheduleCommand.INVALID_SLOT_NUMBER && dayOfWeek != null) {
            return person.isWorking(dayOfWeek, slotNum);
        } else {
            return false; // can consider throwing an exception?
        }
    }
}
