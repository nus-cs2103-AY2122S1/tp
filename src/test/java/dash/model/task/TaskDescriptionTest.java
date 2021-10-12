package dash.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dash.testutil.Assert;

public class TaskDescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new TaskDescription(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new TaskDescription(invalidDescription));
    }

    @Test
    public void isValidDescription() {
        // null name
        Assert.assertThrows(NullPointerException.class, () -> TaskDescription.isValidDescription(null));

        // invalid name
        assertFalse(TaskDescription.isValidDescription("")); // empty string
        assertFalse(TaskDescription.isValidDescription(" ")); // spaces only

        // valid name
        assertTrue(TaskDescription.isValidDescription("do coding assignment")); // letters only
        assertTrue(TaskDescription.isValidDescription("12345")); // numbers only
        assertTrue(TaskDescription.isValidDescription("?!?!?!?!")); // only non-alphanumeric characters
        assertTrue(TaskDescription.isValidDescription("watch st2334 lectures")); // alphanumeric characters
        assertTrue(TaskDescription.isValidDescription("get some sleep!")); // contains non-alphanumeric characters
        assertTrue(TaskDescription.isValidDescription("Meet up with Jack for movie")); // with capital letters
        assertTrue(TaskDescription.isValidDescription("Memorise Joker (2019) quote - Murray Franklin: 'Let me get "
                + "this straight. You think that killing those guys is funny?' Arthur Fleck: 'I do. And I'm tired of "
                + "pretending it's not.'")); // long description
    }
}

