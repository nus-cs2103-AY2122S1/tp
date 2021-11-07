package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.TutorialGroup;
import seedu.address.model.statistic.Statistic;

/**
 * Displays the statistics of a given tutorial group.
 */
public class StatisticsCommand extends Command {

    public static final String COMMAND_WORD = "stat";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays statistics of the current data.\n"
            + "Parameters: TUTORIAL_GROUP\n"
            + "Example: " + COMMAND_WORD + " T09";

    public static final String MESSAGE_STATISTICS = "Statistics: \n%s";
    public static final String MESSAGE_TUTORIAL_GROUP_NOT_FOUND = "Tutorial group not found";
    public static final String MESSAGE_TG_FIELD_MISSING = "Unable to compute statistics. "
            + "%s info for the contacts in the specified tutorial group are missing.";

    private final TutorialGroup tutorialGroup;

    /**
     * Initializes a StatisticCommand
     *
     * @param tutorialGroup The tutorialGroup that the user wants to find the statistics for.
     */
    public StatisticsCommand(TutorialGroup tutorialGroup) {
        requireNonNull(tutorialGroup);

        this.tutorialGroup = tutorialGroup;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateFilteredPersonList(p -> p.getTutorialGroup().equals(tutorialGroup));
        List<Person> filteredPersonList = new ArrayList<Person>(model.getFilteredPersonList());
        model.updateFilteredPersonList(p -> true);

        if (filteredPersonList.size() == 0) {
            throw new CommandException(MESSAGE_TUTORIAL_GROUP_NOT_FOUND);
        }

        Statistic statistic = new Statistic(filteredPersonList);

        return new CommandResult(String.format(MESSAGE_STATISTICS, statistic), statistic.getRawData());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StatisticsCommand // instanceof handles nulls
                && tutorialGroup.equals(((StatisticsCommand) other).tutorialGroup)); // state check
    }
}

