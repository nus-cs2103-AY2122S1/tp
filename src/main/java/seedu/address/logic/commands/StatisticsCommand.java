package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

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

    public static final String COMMAND_WORD = "stats";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays statistics of the current data.\n"
            + "Parameters: TUTORIAL_GROUP\n"
            + "Example: " + COMMAND_WORD + " T09";

    public static final String MESSAGE_STATISTICS = "Statistics: \n%s";

    private final TutorialGroup tutorialGroup;

    /**
     * Initializes a StatisticCommand
     *
     * @param tutorialGroup The tutorialGroup that the user wants to find the statistics for.
     */
    public StatisticsCommand(String tutorialGroup) {
        requireNonNull(tutorialGroup);

        this.tutorialGroup = new TutorialGroup(tutorialGroup);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateFilteredPersonList(p -> p.getTutorialGroup().equals(tutorialGroup));
        List<Person> filteredPersonList = model.getFilteredPersonList();
        model.updateFilteredPersonList(p -> true);

        Statistic statistic = new Statistic(filteredPersonList);

        return new CommandResult(String.format(MESSAGE_STATISTICS, statistic.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StatisticsCommand // instanceof handles nulls
                && tutorialGroup.equals(((StatisticsCommand) other).tutorialGroup)); // state check
    }
}

