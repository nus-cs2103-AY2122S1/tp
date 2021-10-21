package seedu.academydirectory.versioncontrol.parsers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.academydirectory.versioncontrol.parsers.VcParser.NULL_PARSE;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class CommitParserTest {
    private final CommitParser parser = new CommitParser();

    @Test
    public void parse_allFieldsPresent_success() {
        String filename = "CommitParserTest";
        Path filepath = Paths.get("src", "test", "data", "VersionControlTest", "ParserTest", filename);
        assertTrue(() -> filepath.toFile().exists());

        String[] actual = assertDoesNotThrow(() -> parser.parse(filepath));
        String[] expected = {filename, "amadeus", "17/10/2021 14:13:39", "Hello, World!",
            "8e5abe51e5e68cc7e41e4d783529ec90506fdfa6", "18f30e1a6e30583a2a8afd86c08b06c449658db1"};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void parse_corruptedFile_nullParse() {
        String filename = "TreeParserTest";
        Path filepath = Paths.get("src", "test", "data", "VersionControlTest", "ParserTest", filename);
        assertTrue(() -> filepath.toFile().exists());

        String[] actual = assertDoesNotThrow(() -> parser.parse(filepath));
        System.out.println(Arrays.toString(actual));
        assertArrayEquals(NULL_PARSE, actual);
    }
}
