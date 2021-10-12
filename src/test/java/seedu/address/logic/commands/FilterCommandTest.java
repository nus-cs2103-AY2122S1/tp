package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.CategoryCode;

class FilterCommandTest {
    @Test
    public void equals() {
        Set<CategoryCode> firstCategoryCodeSet = Collections.singleton(new CategoryCode("att"));
        Set<CategoryCode> secondCategoryCodeSet = new HashSet<>(
                Arrays.asList(new CategoryCode("fnb"), new CategoryCode("att")));

        FilterCommand firstFilterCommand = new FilterCommand(firstCategoryCodeSet);
        FilterCommand secondFilterCommand = new FilterCommand(secondCategoryCodeSet);

        // same object -> returns true
        assertTrue(firstFilterCommand.equals(firstFilterCommand));

        // same values -> returns true
        FilterCommand firstFilterCommandCopy = new FilterCommand(firstCategoryCodeSet);
        assertTrue(firstFilterCommand.equals(firstFilterCommandCopy));

        // different types -> returns false
        assertFalse(firstFilterCommand.equals(1));

        // null -> returns false
        assertFalse(firstFilterCommand.equals(null));

        // different category codes -> returns false
        assertFalse(firstFilterCommand.equals(secondFilterCommand));
    }

    /*
    TODO [LETHICIA]
    @Test
    public void execute_oneCategoryCode_noPersonFound() {
    }

    @Test
    public void execute_oneCategoryCode_singlePersonFound() {
    }

    @Test
    public void execute_oneCategoryCode_multiplePersonsFound() {
    }

    @Test
    public void execute_multipleCategoryCode_singlePersonsFound() {
    }

    @Test
    public void execute_multipleCategoryCode_multiplePersonsFound() {
    }
    */



}
