package seedu.academydirectory.versioncontrol.parsers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class HeadParserTest {
    private final HeadParser parser = new HeadParser();

    @Test
    public void parse_noRef() {
        String filename = "HeadParserTest_NoRef";
        Path filepath = Paths.get("src", "test", "data", "VersionControlTest", "ParserTest", filename);
        assertTrue(() -> filepath.toFile().exists());

        assertThrows(FileNotFoundException.class, () -> parser.parse(filepath));
    }

    @Test
    public void parse_allFieldsPresent_success() {
        String filename = "HeadParserTest_WithRef";
        Path filepath = Paths.get("src", "test", "data", "VersionControlTest", "ParserTest", filename);
        assertTrue(() -> filepath.toFile().exists());

        String[] actual = assertDoesNotThrow(() -> parser.parse(filepath));
        String[] expected = {"CommitParserTest", "amadeus", "17/10/2021 14:13:39", "Hello, World!",
                "8e5abe51e5e68cc7e41e4d783529ec90506fdfa6", "18f30e1a6e30583a2a8afd86c08b06c449658db1"};
        assertArrayEquals(actual, expected);
    }
}
