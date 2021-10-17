package seedu.academydirectory.versioncontrol.parsers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class TreeParserTest {
    private final TreeParser parser = new TreeParser();

    @Test
    public void parse_allFieldsPresent_success() {
        String filename = "TreeParserTest";
        Path filepath = Paths.get("src", "test", "data", "VersionControlTest", "ParserTest", filename);
        assertTrue(() -> filepath.toFile().exists());

        String[] actual = assertDoesNotThrow(() -> parser.parse(filepath));
        String[] expected = {"922d4ff703f9b003da1962b1a2228371718e504a", "academydirectory.json"};
        assertArrayEquals(actual, expected);
    }
}
