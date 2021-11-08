package seedu.track2gather.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.track2gather.commons.core.Messages.MESSAGE_PREDICATE_SHOW_ALL_PERSONS;
import static seedu.track2gather.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

import seedu.track2gather.model.Model;
import seedu.track2gather.model.person.Person;
import seedu.track2gather.model.person.attributes.Period;

/**
 * Clears the contacts list.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Contacts with completed SHN periods have been cleared. "
        + "Note that SHN periods with end dates that fall on the current day are not cleared.\n"
        + MESSAGE_PREDICATE_SHOW_ALL_PERSONS;

    private Clock clock;

    public ClearCommand() {
        this.clock = Clock.systemDefaultZone();
    }

    public ClearCommand(Clock clock) {
        this.clock = clock;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        List<Person> lastShownListCopy = List.copyOf(model.getFilteredPersonList());

        // A dummy SHN period for contacts that do not have SHN periods
        // These contacts will be ignored as the endDate is always set to the future
        LocalDate startDate = LocalDate.of(2000, 1, 1);
        LocalDate futureDate = LocalDate.now().plusDays(1);
        assert (futureDate.isAfter(startDate));
        Period dummyIncompleteShnPeriod = new Period(startDate, futureDate);

        for (int i = 0; i < lastShownListCopy.size(); i++) {
            Person person = lastShownListCopy.get(i);
            if (person.getShnPeriod().value.orElse(dummyIncompleteShnPeriod).isCompletedBy(LocalDate.now(clock))) {
                model.deletePerson(person);
            }
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ClearCommand // instanceof handles nulls
            && clock.equals(((ClearCommand) other).clock)); // state check
    }
}
