package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.SortComparator;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonAttributesContainsKeywordsPredicate;

public class SortCommandTest {
    private static final PersonAttributesContainsKeywordsPredicate PREDICATE_STUB = preparePredicate("meier");
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_sortUnfilteredListByNextVisit_showEverythingSorted() {
        SortCommand sortByNextVisitCommand = new SortCommand(SortComparator.SORT_BY_NEXT_VISIT, true);
        expectedModel.sortFilteredPersonList(SortComparator.SORT_BY_NEXT_VISIT, true);
        assertCommandSuccess(sortByNextVisitCommand, model, SortCommand.MESSAGE_SUCCESS, expectedModel);

        SortCommand sortByLastVisitCommand = new SortCommand(SortComparator.SORT_BY_LAST_VISIT, false);
        expectedModel.sortFilteredPersonList(SortComparator.SORT_BY_LAST_VISIT, false);
        assertCommandSuccess(sortByLastVisitCommand, model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortFilteredListByNextVisit_showFilteredSorted() {
        model.updateFilteredPersonList(PREDICATE_STUB);
        expectedModel.updateFilteredPersonList(PREDICATE_STUB);
        SortCommand sortCommand = new SortCommand(SortComparator.SORT_BY_LAST_VISIT, false);
        expectedModel.sortFilteredPersonList(SortComparator.SORT_BY_LAST_VISIT, false);
        assertCommandSuccess(sortCommand, model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortFilteredListByLastVisit_showFilteredSorted() {
        model.updateFilteredPersonList(PREDICATE_STUB);
        expectedModel.updateFilteredPersonList(PREDICATE_STUB);
        SortCommand sortCommand = new SortCommand(SortComparator.SORT_BY_NEXT_VISIT, true);
        expectedModel.sortFilteredPersonList(SortComparator.SORT_BY_NEXT_VISIT, true);
        assertCommandSuccess(sortCommand, model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private static PersonAttributesContainsKeywordsPredicate preparePredicate(String userInput) {
        return new PersonAttributesContainsKeywordsPredicate(ArgumentTokenizer.tokenize(userInput));
    }

}
