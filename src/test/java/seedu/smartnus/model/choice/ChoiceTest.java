package seedu.smartnus.model.choice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartnus.testutil.Assert.assertThrows;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ChoiceTest {
    private static final Choice DEFAULT_CHOICE = new Choice("alpha", true);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Choice(null, true));
    }

    @Test
    public void constructor_invalidChoiceTitle_throwsIllegalArgumentException() {
        String invalidChoiceName = "";
        assertThrows(IllegalArgumentException.class, () -> new Choice(invalidChoiceName, true));
    }

    @Test
    public void isValidChoiceTitle_nullTitle_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Choice.isValidChoiceTitle(null));
    }

    @Test
    public void isValidChoiceTitle_emptyStringTitle_false() {
        // Choice title containing only spaces
        assertFalse(Choice.isValidChoiceTitle(""));
        assertFalse(Choice.isValidChoiceTitle("  "));
    }

    @Test
    public void isValidChoiceTitle_validTitle_true() {
        assertTrue(Choice.isValidChoiceTitle("option 1"));
        assertTrue(Choice.isValidChoiceTitle("10"));
        assertTrue(Choice.isValidChoiceTitle("901p#__"));
    }

    @Test
    public void hasSameTitle_nullChoice_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> DEFAULT_CHOICE.hasSameTitle(null));
    }

    @Test
    public void hasSameTitle_sameTitleChoice_true() {
        // same object
        assertTrue(DEFAULT_CHOICE.hasSameTitle(DEFAULT_CHOICE));
        // same title, different isCorrect value, should still return true
        assertTrue(DEFAULT_CHOICE.hasSameTitle(new Choice("alpha", false)));
        // same title, same isCorrect value
        assertTrue(DEFAULT_CHOICE.hasSameTitle(new Choice("alpha", true)));
    }

    @Test
    public void hasSameTitle_differentTitleChoice_false() {
        assertFalse(DEFAULT_CHOICE.hasSameTitle(new Choice("abc", true)));
        // has additional whitespace
        assertFalse(DEFAULT_CHOICE.hasSameTitle(new Choice("alpha ", true)));
        // different case
        assertFalse(DEFAULT_CHOICE.hasSameTitle(new Choice("aLPha", true)));
    }

    @Test
    public void getKeywordsString_noKeywords_emptyString() {
        assertEquals("", DEFAULT_CHOICE.getKeywordsString());
    }

    @Test
    public void getKeywordsString_keywordsPresent_formattedString() {
        Choice choice = new Choice("a b c", true, new HashSet<>(List.of("a", "b", "c")));
        assertEquals("a, b, c", choice.getKeywordsString());
    }

    @Test
    public void equals() {
        Choice choiceACorrect = new Choice("choice A", true);
        Choice choiceAWrong = new Choice("choice A", false);
        Choice choiceBCorrect = new Choice("choice B", true);

        // different isCorrect values
        assertFalse(choiceACorrect.equals(choiceAWrong));

        // different titles
        assertFalse(choiceBCorrect.equals(choiceACorrect));

        assertTrue(choiceACorrect.equals(new Choice("choice A", true)));
    }

    @Test
    public void toString_test() {
        Choice wrongChoice = new Choice("wrong", false);
        Choice correctChoice = new Choice("correct", true);

        assertEquals(wrongChoice.toString(), "wrong");
        assertEquals(correctChoice.toString(), "correct (answer)");
    }
}
