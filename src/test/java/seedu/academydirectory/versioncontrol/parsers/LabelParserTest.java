package seedu.academydirectory.versioncontrol.parsers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.academydirectory.versioncontrol.parsers.VcParser.NULL_PARSE;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class LabelParserTest {
    private final LabelParser parser = new LabelParser();

    @Test
    public void parse_fileExist_success() {
        String filename = "HeadParserTest_NoRef";
        Path filepath = Paths.get("src", "test", "data", "VersionControlTest", "ParserTest", filename);
        Path finalFilepath = filepath;
        assertTrue(() -> finalFilepath.toFile().exists());

        assertArrayEquals(new String[]{"1f2bd072c41e9f9a4fcb2206c47b5ce09c4ea27a"}, parser.parse(filepath));

        filename = "HeadParserTest_WithRef";
        filepath = Paths.get("src", "test", "data", "VersionControlTest", "ParserTest", filename);
        Path finalFilepath1 = filepath;
        assertTrue(() -> finalFilepath1.toFile().exists());

        Path finalFilepath2 = filepath;
        assertArrayEquals(new String[]{"CommitParserTest"}, parser.parse(finalFilepath2));
    }

    @Test
    public void parse_fileAbsent_nullParse() {
        String filename = "NOSUCHFILE";
        Path filepath = Paths.get("src", "test", "data", "VersionControlTest", "ParserTest", filename);
        assertFalse(() -> filepath.toFile().exists());
        assertEquals(NULL_PARSE, parser.parse(filepath));
    }

    @Test
    public void parse_fileCorrupt_nullParse() {
        String filename = "CommitParserTest";
        Path filepath = Paths.get("src", "test", "data", "VersionControlTest", "ParserTest", filename);
        assertTrue(() -> filepath.toFile().exists());
        assertEquals(NULL_PARSE, parser.parse(filepath));
    }
}
