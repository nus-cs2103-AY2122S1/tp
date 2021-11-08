package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INCOMING_MONTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INCOMING_WEEK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAST_VISIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NORMAL_LIST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VISIT;

import org.junit.jupiter.api.Test;

public class ArgumentMultimapTest {

    @Test
    public void isAllPresent() {
        ArgumentMultimap allPresent = ArgumentTokenizer.tokenize(" all/ w/ m/",
                PREFIX_INCOMING_MONTH, PREFIX_INCOMING_WEEK, PREFIX_NORMAL_LIST);

        ArgumentMultimap somePresent = ArgumentTokenizer.tokenize(" v/123",
                PREFIX_VISIT, PREFIX_LAST_VISIT);

        // in this case lv/ is considered the value of key v/
        ArgumentMultimap somePresentNoSpace = ArgumentTokenizer.tokenize(" v/lv/",
                PREFIX_VISIT, PREFIX_LAST_VISIT);


        assertTrue(allPresent.isAllPresent(PREFIX_INCOMING_MONTH, PREFIX_INCOMING_WEEK, PREFIX_NORMAL_LIST));
        assertTrue(somePresent.isAllPresent(PREFIX_VISIT));

        assertFalse(somePresent.isAllPresent(PREFIX_VISIT, PREFIX_LAST_VISIT));
        assertFalse(somePresentNoSpace.isAllPresent(PREFIX_VISIT, PREFIX_LAST_VISIT));
    }

    @Test
    public void isMultiplePresent() {
        ArgumentMultimap allPresent = ArgumentTokenizer.tokenize(" all/ w/ m/",
                PREFIX_INCOMING_MONTH, PREFIX_INCOMING_WEEK, PREFIX_NORMAL_LIST);

        ArgumentMultimap somePresent = ArgumentTokenizer.tokenize(" v/123",
                PREFIX_VISIT, PREFIX_LAST_VISIT);

        // in this case lv/ is considered the value of key v/
        ArgumentMultimap somePresentNoSpace = ArgumentTokenizer.tokenize(" v/lv/",
                PREFIX_VISIT, PREFIX_LAST_VISIT);

        assertTrue(allPresent.isMultiplePresent(PREFIX_INCOMING_MONTH, PREFIX_INCOMING_WEEK, PREFIX_NORMAL_LIST));
        assertTrue(allPresent.isMultiplePresent(PREFIX_INCOMING_MONTH, PREFIX_INCOMING_WEEK));

        // less than 2 params -> false
        assertFalse(somePresent.isMultiplePresent(PREFIX_VISIT));
        assertFalse(somePresent.isMultiplePresent());

        // no space so not detected -> false
        assertFalse(somePresentNoSpace.isMultiplePresent(PREFIX_VISIT, PREFIX_LAST_VISIT));
    }

    @Test
    public void equalsMethod() {
        ArgumentMultimap argMap = ArgumentTokenizer.tokenize("");
        ArgumentMultimap otherArgMap = ArgumentTokenizer.tokenize("");
        ArgumentMultimap differentArgMap = ArgumentTokenizer.tokenize("v/");

        assertEquals(argMap, argMap);
        assertEquals(argMap, otherArgMap);
        assertNotEquals(argMap, differentArgMap);
    }
}
