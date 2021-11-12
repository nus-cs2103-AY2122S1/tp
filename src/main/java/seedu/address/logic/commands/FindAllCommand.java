package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.ui.FullPersonCard;
import seedu.address.ui.UiManager;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindAllCommand extends Command {

    public static final String COMMAND_WORD = "findAll";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose fields matches "
            + "ALL the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [g/GENDER] [age/AGE] [i/INTEREST]...\n"
            + "Example: " + COMMAND_WORD + " n/alice  g/F age/15";

    private final Predicate<Person> predicate;

    public FindAllCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        resetDisplay();
        UiManager.displayFunction();
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    /**
     * Resets the displayed index to 1
     */
    private void resetDisplay() {
        FullPersonCard.setDisplayedIndex(1);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindAllCommand // instanceof handles nulls
                && predicate.equals(((FindAllCommand) other).predicate)); // state check
    }
}
