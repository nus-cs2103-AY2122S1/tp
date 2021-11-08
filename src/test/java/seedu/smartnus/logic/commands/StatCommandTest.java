package seedu.smartnus.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_TAG_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_TAG_2;
import static seedu.smartnus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.smartnus.logic.commands.StatCommand.MESSAGE_FOUND_STATS_FORMAT;
import static seedu.smartnus.testutil.TypicalSmartNus.getTypicalSmartNus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.smartnus.model.Model;
import seedu.smartnus.model.ModelManager;
import seedu.smartnus.model.UserPrefs;
import seedu.smartnus.model.statistic.TagStatistic;
import seedu.smartnus.model.statistic.predicates.StatContainsKeywordPredicate;
import seedu.smartnus.ui.panel.StatisticListPanel;


/**
 * Contains integration tests (interaction with the Model) for {@code StatCommand}.
 */
class StatCommandTest {
    private Model model = new ModelManager(getTypicalSmartNus(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalSmartNus(), new UserPrefs());

    @Test
    void equals() {
        StatContainsKeywordPredicate firstPredicate = createPredicateFromUserInput(VALID_TAG_1);
        StatContainsKeywordPredicate secondPredicate = createPredicateFromUserInput(VALID_TAG_2);

        ArrayList<Predicate<TagStatistic>> firstPredicateArr = createPredicateArr(firstPredicate);
        ArrayList<Predicate<TagStatistic>> secondPredicateArr = createPredicateArr(secondPredicate);
        ArrayList<Predicate<TagStatistic>> thirdPredicateArr = createPredicateArr(firstPredicate, secondPredicate);

        StatCommand getFirstStat = new StatCommand(firstPredicateArr);
        StatCommand getSecondStat = new StatCommand(secondPredicateArr);
        StatCommand getBothStats = new StatCommand(thirdPredicateArr);


        // same object -> returns true
        assertTrue(getFirstStat.equals(getFirstStat));

        // same values -> returns true
        StatCommand getFirstStatCopy = new StatCommand(firstPredicateArr);
        assertTrue(getFirstStat.equals(getFirstStatCopy));

        // different types -> returns false
        assertFalse(getSecondStat.equals(1));

        // null -> returns false
        assertFalse(getFirstStat.equals(null));

        // different tags -> returns false
        assertFalse(getFirstStat.equals(getSecondStat));
        assertFalse(getFirstStat.equals(getBothStats));
    }

    @Test
    void execute_noTags_allTagsFound() {
        StatCommand command = new StatCommand(new ArrayList<>());
        model.setPanel(StatisticListPanel.STATISTIC_PANEL);
        expectedModel.setPanel(StatisticListPanel.STATISTIC_PANEL);
        String expectedMessage = String.format(MESSAGE_FOUND_STATS_FORMAT, expectedModel.getTagStatistic().size());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_oneTag_oneTagFound() {
        StatContainsKeywordPredicate firstTagPredicate = createPredicateFromUserInput(VALID_TAG_1);
        StatCommand command = new StatCommand(createPredicateArr(firstTagPredicate));
        expectedModel.updateFilteredTagStatistic(firstTagPredicate);
        model.setPanel(StatisticListPanel.STATISTIC_PANEL);
        expectedModel.setPanel(StatisticListPanel.STATISTIC_PANEL);
        String expectedMessage = String.format(MESSAGE_FOUND_STATS_FORMAT, expectedModel.getTagStatistic().size());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    private StatContainsKeywordPredicate createPredicateFromUserInput(String userInput) {
        return new StatContainsKeywordPredicate(Collections.singletonList(userInput));
    }

    private ArrayList<Predicate<TagStatistic>> createPredicateArr(StatContainsKeywordPredicate ...predicates) {
        return new ArrayList<>(Arrays.asList(predicates));
    }

}
