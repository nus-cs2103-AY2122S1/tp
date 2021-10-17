package seedu.academydirectory.versioncontrol.utils;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class HashGeneratorTest {
    private final HashMethod[] supportedMethods = {HashMethod.MD5, HashMethod.SHA1, HashMethod.SHA256};

    @Test
    public void hashGenerator_correctHash() {
        String filename = "CommitParserTest";
        Path filepath = Paths.get("src", "test", "data", "VersionControlTest", "ParserTest", filename);
        assertTrue(() -> filepath.toFile().exists());

        String[] actualHash = new String[3];
        String[] expectedHash = {
                "00f8e0d3e2f06918e4b3f2ef2709e2df",
                "1ba3aa88d488e76d30086af249a8d17072e04185",
                "0c54914698ffaee27e52d66038ced1b69c2cea854c16c906c6764196514217d5"
        };

        int idx = 0;
        for (HashMethod hashMethod: supportedMethods) {
            HashGenerator generator = new HashGenerator(hashMethod);
            actualHash[idx] = assertDoesNotThrow(() -> generator.generateHashFromFile(filepath));
            idx += 1;
        }

        assertArrayEquals(actualHash, expectedHash);
    }
}
