package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.LastVisit;
import seedu.address.model.person.PersonAttributesContainsKeywordsPredicate;
import seedu.address.model.person.Visit;
import seedu.address.model.util.SortUtil;

public class SortCommandTest {
    private static final PersonAttributesContainsKeywordsPredicate PREDICATE_STUB = preparePredicate("meier");
    private static final String SUCCESS_MESSAGE_VISIT =
            String.format(SortCommand.MESSAGE_SUCCESS, Visit.class.getSimpleName());
    private static final String SUCCESS_MESSAGE_LAST_VISIT =
            String.format(SortCommand.MESSAGE_SUCCESS, LastVisit.class.getSimpleName());
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_sortUnfilteredListByNextVisit_showEverythingSorted() {
        // sort by visit
        SortCommand sortByNextVisitCommand = new SortCommand(SortUtil.SORT_BY_NEXT_VISIT, true);
        expectedModel.sortFilteredPersonList(SortUtil.SORT_BY_NEXT_VISIT, true);
        assertCommandSuccess(sortByNextVisitCommand, model, SUCCESS_MESSAGE_VISIT, expectedModel);

        // sort by last visit
        SortCommand sortByLastVisitCommand = new SortCommand(SortUtil.SORT_BY_LAST_VISIT, false);
        expectedModel.sortFilteredPersonList(SortUtil.SORT_BY_LAST_VISIT, false);
        assertCommandSuccess(sortByLastVisitCommand, model, SUCCESS_MESSAGE_LAST_VISIT, expectedModel);
    }

    @Test
    public void execute_sortFilteredListByNextVisit_showFilteredSorted() {
        model.updateFilteredPersonList(PREDICATE_STUB);
        expectedModel.updateFilteredPersonList(PREDICATE_STUB);

        SortCommand sortCommand = new SortCommand(SortUtil.SORT_BY_LAST_VISIT, false);
        expectedModel.sortFilteredPersonList(SortUtil.SORT_BY_LAST_VISIT, false);

        assertCommandSuccess(sortCommand, model, SUCCESS_MESSAGE_LAST_VISIT, expectedModel);
    }

    @Test
    public void execute_sortFilteredListByLastVisit_showFilteredSorted() {
        model.updateFilteredPersonList(PREDICATE_STUB);
        expectedModel.updateFilteredPersonList(PREDICATE_STUB);

        SortCommand sortCommand = new SortCommand(SortUtil.SORT_BY_NEXT_VISIT, true);
        expectedModel.sortFilteredPersonList(SortUtil.SORT_BY_NEXT_VISIT, true);

        assertCommandSuccess(sortCommand, model, SUCCESS_MESSAGE_VISIT, expectedModel);
    }

    @Test
    public void execute_invalidComparator_throwCommandException() {
        SortCommand sortCommand = new SortCommand(SortUtil.SORT_BY_NAME, true);
        String errorMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);
        assertCommandFailure(sortCommand, model, errorMessage);
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private static PersonAttributesContainsKeywordsPredicate preparePredicate(String userInput) {
        return new PersonAttributesContainsKeywordsPredicate(ArgumentTokenizer.tokenize(userInput));
    }

}
