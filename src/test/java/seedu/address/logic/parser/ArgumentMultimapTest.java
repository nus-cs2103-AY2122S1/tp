package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class ArgumentMultimapTest {

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
