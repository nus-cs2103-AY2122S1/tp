package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class ArgumentTokenizerTest {

    private final Prefix pSlash = new Prefix("p/");
    private final Prefix dSlash = new Prefix("d/");
    private final Prefix wordSlash = new Prefix("word/");

    private final String wordParameter1 = "Argument";
    private final String wordParameter2 = "Value";
    private final String sentenceParameter1 = "Argument value with spaces";
    private final String sentenceParameter2 = "Another argument value";

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

    /* Equivalence Partitions:
            Expected Prefixes:
                - No prefixes
                - Single character prefixes
                - Multiple character prefixes
                - Prefixes not in arg string

            Preamble:
                - Empty string
                - Single word
                - Multiple word

            Parameters:
                - Empty parameter
                - Single word
                - Multiple word
                - Single word with trailing / leading spaces
                - Multiple word with trailing / leading spaces

        The following test cases attempts to cover all partitions in an efficient manner.
     */

    @Test
    public void tokenize_emptyArgsString_noValues() throws ParseException {
        String argsString = "  ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash);

        assertPreambleEmpty(argMultimap);
        assertArgumentAbsent(argMultimap, pSlash);
    }

    @Test
    public void tokenize_noPrefixes_allTakenAsPreamble() throws ParseException {
        String argsString = "  some random string with trailing space ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString);

        // Same string expected as preamble, but leading/trailing spaces should be trimmed
        assertPreamblePresent(argMultimap, argsString.trim());
    }

    @Test
    public void tokenize_oneArgument() throws ParseException {
        // Preamble absent, empty values.
        String argsString = pSlash.getPrefix();
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash);
        assertPreambleEmpty(argMultimap);
        assertArgumentPresent(argMultimap, pSlash, "");

        // Preamble present, word values.
        argsString = wordParameter1 + " " + pSlash + wordParameter2;
        argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash);
        assertPreamblePresent(argMultimap, wordParameter1);
        assertArgumentPresent(argMultimap, pSlash, wordParameter2);

        // Preamble absent, word values.
        argsString = pSlash + wordParameter2;
        argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash);
        assertPreambleEmpty(argMultimap);
        assertArgumentPresent(argMultimap, pSlash, wordParameter2);

        // Preamble present, sentence values.
        argsString = sentenceParameter1 + " " + pSlash + sentenceParameter2;
        argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash);
        assertPreamblePresent(argMultimap, sentenceParameter1);
        assertArgumentPresent(argMultimap, pSlash, sentenceParameter2);

        // Preamble absent, sentence values.
        argsString = pSlash + sentenceParameter2;
        argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash);
        assertPreambleEmpty(argMultimap);
        assertArgumentPresent(argMultimap, pSlash, sentenceParameter2);
    }

    @Test
    public void tokenize_multipleArguments() throws ParseException {
        // Not all expected prefixes are present
        String argsString = wordParameter1 + " " + pSlash + wordParameter2;
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash, dSlash);
        assertPreamblePresent(argMultimap, wordParameter1);
        assertArgumentPresent(argMultimap, pSlash, wordParameter2);

        // All expected prefixes present
        argsString = pSlash + wordParameter2 + " " + wordSlash + sentenceParameter1;
        argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash, dSlash, wordSlash);
        assertPreambleEmpty(argMultimap);
        assertArgumentPresent(argMultimap, pSlash, wordParameter2);
        assertArgumentPresent(argMultimap, wordSlash, sentenceParameter1);

        /* Also covers: Reusing of the tokenizer multiple times */

        // Reuse tokenizer on an empty string to ensure ArgumentMultimap is correctly reset
        // (i.e. no stale values from the previous tokenizing remain)
        argsString = "";
        argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash);
        assertPreambleEmpty(argMultimap);
        assertArgumentAbsent(argMultimap, pSlash);
    }

    @Test
    public void tokenize_multipleArgumentsWithRepeats() throws ParseException {
        // Two arguments repeated, some have empty values
        String argsString = wordParameter1 + " " + pSlash + " " + pSlash
                + wordParameter2 + " " + dSlash + sentenceParameter1 + " " + dSlash;
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash, dSlash);
        assertPreamblePresent(argMultimap, wordParameter1);
        assertArgumentPresent(argMultimap, pSlash, "", wordParameter2);
        assertArgumentPresent(argMultimap, dSlash, sentenceParameter1, "");
    }

    @Test
    public void tokenize_paddedSpaces() throws ParseException {
        // Parameters with leading or trailing whitespace should be trimmed
        String argsString = wordParameter1 + "  " + pSlash + " " + sentenceParameter2 + " ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash);
        assertPreamblePresent(argMultimap, wordParameter1);
        assertArgumentPresent(argMultimap, pSlash, sentenceParameter2);
    }

    @Test
    public void tokenize_unexpectedPrefixes_throwsParseException() throws ParseException {
        // Unexpected single character prefix
        String argsString1 = wordParameter1 + "  " + pSlash + " " + sentenceParameter2 + " ";
        assertThrows(ParseException.class, () -> ArgumentTokenizer.tokenize(argsString1, dSlash));

        // Unexpected multiple character prefix
        String argsString2 = wordParameter1 + "  " + wordSlash + " " + sentenceParameter2 + " ";
        assertThrows(ParseException.class, () -> ArgumentTokenizer.tokenize(argsString2, dSlash));
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
