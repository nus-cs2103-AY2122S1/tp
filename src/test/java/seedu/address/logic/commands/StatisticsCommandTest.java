package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.TutorialGroup;
import seedu.address.model.statistic.Statistic;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code StatisticsCommand}.
 */
public class StatisticsCommandTest {

    private final TutorialGroup tutorialGroup = new TutorialGroup("T09");
    private final TutorialGroup nonexistentTutorialGroup = new TutorialGroup("T99");
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validTutorialGroup_success() throws CommandException {
        StatisticsCommand statisticsCommand = new StatisticsCommand(tutorialGroup);

        model.updateFilteredPersonList(p -> p.getTutorialGroup().equals(tutorialGroup));
        List<Person> filteredPersonList = new ArrayList<>(model.getFilteredPersonList());
        model.updateFilteredPersonList(p -> true);

        Statistic statistic = new Statistic(filteredPersonList);

        String expectedMessage = String.format(StatisticsCommand.MESSAGE_STATISTICS, statistic.toString());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(statisticsCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_tutorialGroupNotFound_throwsCommandException() {
        StatisticsCommand statisticsCommand = new StatisticsCommand(nonexistentTutorialGroup);

        assertThrows(CommandException.class,
            StatisticsCommand.MESSAGE_TUTORIAL_GROUP_NOT_FOUND, () -> statisticsCommand.execute(model));
    }
}

