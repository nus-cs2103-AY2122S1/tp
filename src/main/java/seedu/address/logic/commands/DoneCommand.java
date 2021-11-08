package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_NO_EXISTING_VISIT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Frequency;
import seedu.address.model.person.LastVisit;
import seedu.address.model.person.Occurrence;
import seedu.address.model.person.Person;
import seedu.address.model.person.Visit;

/**
 * Marks as done the visit for person identified using it's displayed index from the address book.
 * There must be a visit scheduled for the person.
 * When a person's visit is marked as done, the visit is deleted and the field last visited is updated.
 */
public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks as done the visit for the elderly "
            + "identified by the index number used in the displayed elderly list. "
            + "There must be a next visit for the elderly\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DONE_PERSON_SUCCESS = "Mark visit as done for Elderly: %1$s";

    private final Index targetIndex;

    public DoneCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDone = lastShownList.get(targetIndex.getZeroBased());

        if (!personToDone.hasVisit()) {
            throw new CommandException(String.format(MESSAGE_NO_EXISTING_VISIT, personToDone));
        }

        String newLastVisitedDate = personToDone.getVisit().orElse(new Visit("")).toString();
        Visit newLastVisit = personToDone.getVisit().get();
        Optional<LastVisit> newLastVisited = Optional.of(new LastVisit(newLastVisitedDate));

        Occurrence currentOccurrence = personToDone.getOccurrence().orElse(Occurrence.EMPTY_OCCURRENCE);
        Frequency currentFrequency = personToDone.getFrequency().orElse(Frequency.EMPTY);

        Optional<Occurrence> newOccurrence;
        Optional<Visit> newVisit;


        if (currentOccurrence.value == 1 || currentFrequency.isEmpty()) {
            newOccurrence = Optional.of(new Occurrence(1));
            newVisit = Optional.ofNullable(new Visit(""));
        } else {
            newOccurrence = Optional.of(currentOccurrence.getNext());
            newVisit = Optional.ofNullable(currentFrequency.nextVisit(newLastVisit));
        }

        Person donePerson = new Person(personToDone.getName(), personToDone.getPhone(), personToDone.getLanguage(),
                personToDone.getAddress(), newLastVisited, newVisit,
                personToDone.getFrequency(), newOccurrence, personToDone.getHealthConditions());

        model.setPerson(personToDone, donePerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(
                MESSAGE_DONE_PERSON_SUCCESS, personToDone));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DoneCommand // instanceof handles nulls
                && targetIndex.equals(((DoneCommand) other).targetIndex)); // state check
    }
}
