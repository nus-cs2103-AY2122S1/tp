package nustracker.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import nustracker.logic.parser.exceptions.ExtraSlashException;

public class ArgumentTokenizerTest {

    private final Prefix unknownPrefix = new Prefix("--u");
    private final Prefix pSlash = new Prefix("p/");
    private final Prefix dashT = new Prefix("-t");
    private final Prefix hatQ = new Prefix("^Q");

    @Test
    public void tokenize_emptyArgsString_noValues() {
        String argsString = "  ";

        ArgumentMultimap argMultimap = null;
        try {
            argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash);
        } catch (ExtraSlashException e) {
            fail("Unexpected Extra Slash Exception thrown.");
        }

        assertPreambleEmpty(argMultimap);
        assertArgumentAbsent(argMultimap, pSlash);
    }

    private void assertPreamblePresent(ArgumentMultimap argMultimap, String expectedPreamble) {
        assertEquals(expectedPreamble, argMultimap.getPreamble());
    }

    private void assertPreambleEmpty(ArgumentMultimap argMultimap) {
        assertTrue(argMultimap.getPreamble().isEmpty());
    }

    /**
     * Asserts all the arguments in {@code argMultimap} with {@code prefix} match the {@code expectedValues}
     * and only the last value is returned upon calling {@code ArgumentMultimap#getValue(Prefix)}.
     */
    private void assertArgumentPresent(ArgumentMultimap argMultimap, Prefix prefix, String... expectedValues) {

        // Verify the last value is returned
        assertEquals(expectedValues[expectedValues.length - 1], argMultimap.getValue(prefix).get());

        // Verify the number of values returned is as expected
        assertEquals(expectedValues.length, argMultimap.getAllValues(prefix).size());

        // Verify all values returned are as expected and in order
        for (int i = 0; i < expectedValues.length; i++) {
            assertEquals(expectedValues[i], argMultimap.getAllValues(prefix).get(i));
        }
    }

    private void assertArgumentAbsent(ArgumentMultimap argMultimap, Prefix prefix) {
        assertFalse(argMultimap.getValue(prefix).isPresent());
    }

    @Test
    public void tokenize_noPrefixesNoBackslash_allTakenAsPreamble() {
        String argsString = "  some random string no slash with leading and trailing spaces ";

        ArgumentMultimap argMultimap = null;

        try {
            argMultimap = ArgumentTokenizer.tokenize(argsString);
        } catch (ExtraSlashException e) {
            fail("Unexpected Extra Slash Exception thrown.");;
        }

        // Same string expected as preamble, but leading/trailing spaces should be trimmed
        assertPreamblePresent(argMultimap, argsString.trim());

    }

    @Test
    public void tokenize_noPrefixesHasBackslash_throwsExtraBackslashException() {
        String argsString = "  some random string /n prefix with leading and trailing spaces ";

        ArgumentMultimap argMultimap = null;

        try {
            argMultimap = ArgumentTokenizer.tokenize(argsString);
        } catch (ExtraSlashException e) {
            assertTrue(true);
            return;
        }

        fail("Expected Extra Slash Exception but it was not thrown.");

    }

    @Test
    public void tokenize_oneArgument() {
        // Preamble present
        String argsString = "  Some preamble string p/ Argument value ";

        ArgumentMultimap argMultimap = null;

        try {
            argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash);
        } catch (ExtraSlashException e) {
            fail("Unexpected Extra Slash Exception thrown.");;
        }
        assertPreamblePresent(argMultimap, "Some preamble string");
        assertArgumentPresent(argMultimap, pSlash, "Argument value");

        // No preamble
        argsString = " p/   Argument value ";

        try {
            argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash);
        } catch (ExtraSlashException e) {
            fail("Unexpected Extra Slash Exception thrown.");
        }

        assertPreambleEmpty(argMultimap);
        assertArgumentPresent(argMultimap, pSlash, "Argument value");

    }

    @Test
    public void tokenize_multipleArguments() {
        // Only two arguments are present
        String argsString = "SomePreambleString -t dashT-Value p/pSlash value";

        ArgumentMultimap argMultimap = null;

        try {
            argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash, dashT, hatQ);
        } catch (ExtraSlashException e) {
            fail("Unexpected Extra Slash Exception thrown.");
        }
        assertPreamblePresent(argMultimap, "SomePreambleString");
        assertArgumentPresent(argMultimap, pSlash, "pSlash value");
        assertArgumentPresent(argMultimap, dashT, "dashT-Value");
        assertArgumentAbsent(argMultimap, hatQ);

        // All three arguments are present
        argsString = "Different Preamble String ^Q111 -t dashT-Value p/pSlash value";

        try {
            argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash, dashT, hatQ);
        } catch (ExtraSlashException e) {
            fail("Unexpected Extra Slash Exception thrown.");
        }

        assertPreamblePresent(argMultimap, "Different Preamble String");
        assertArgumentPresent(argMultimap, pSlash, "pSlash value");
        assertArgumentPresent(argMultimap, dashT, "dashT-Value");
        assertArgumentPresent(argMultimap, hatQ, "111");

        /* Also covers: Reusing of the tokenizer multiple times */

        // Reuse tokenizer on an empty string to ensure ArgumentMultimap is correctly reset
        // (i.e. no stale values from the previous tokenizing remain)
        argsString = "";

        try {
            argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash, dashT, hatQ);
        } catch (ExtraSlashException e) {
            fail("Unexpected Extra Slash Exception thrown.");
        }

        assertPreambleEmpty(argMultimap);
        assertArgumentAbsent(argMultimap, pSlash);

        /* Also covers: testing for prefixes not specified as a prefix */

        // Prefixes not previously given to the tokenizer should not return any values
        argsString = unknownPrefix + "some value";

        try {
            argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash, dashT, hatQ);
        } catch (ExtraSlashException e) {
            fail("Unexpected Extra Slash Exception thrown.");
        }

        assertArgumentAbsent(argMultimap, unknownPrefix);
        assertPreamblePresent(argMultimap, argsString); // Unknown prefix is taken as part of preamble
    }

    @Test
    public void tokenize_multipleArgumentsWithRepeats() {
        // Two arguments repeated, some have empty values
        String argsString = "SomePreambleString -t dashT-Value ^Q ^Q -t another dashT value p/ pSlash value -t";
        ArgumentMultimap argMultimap = null;

        try {
            argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash, dashT, hatQ);
        } catch (ExtraSlashException e) {
            fail("Unexpected Extra Slash Exception thrown.");
        }
        assertPreamblePresent(argMultimap, "SomePreambleString");
        assertArgumentPresent(argMultimap, pSlash, "pSlash value");
        assertArgumentPresent(argMultimap, dashT, "dashT-Value", "another dashT value", "");
        assertArgumentPresent(argMultimap, hatQ, "", "");
    }

    @Test
    public void tokenize_multipleArgumentsJoined() {
        String argsString = "SomePreambleString-t t-dash joined-tjoined p/ not joined^Qjoined";
        ArgumentMultimap argMultimap = null;

        try {
            argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash, dashT, hatQ);
        } catch (ExtraSlashException e) {
            fail("Unexpected Extra Slash Exception thrown.");
        }

        assertPreamblePresent(argMultimap, "SomePreambleString-t t-dash joined-tjoined");
        assertArgumentPresent(argMultimap, pSlash, "not joined^Qjoined");
        assertArgumentAbsent(argMultimap, dashT);
        assertArgumentAbsent(argMultimap, hatQ);
    }

    @Test
    public void equalsMethod() {
        Prefix aaa = new Prefix("aaa");

        assertEquals(aaa, aaa);
        assertEquals(aaa, new Prefix("aaa"));

        assertNotEquals(aaa, "aaa");
        assertNotEquals(aaa, new Prefix("aab"));
    }

}
