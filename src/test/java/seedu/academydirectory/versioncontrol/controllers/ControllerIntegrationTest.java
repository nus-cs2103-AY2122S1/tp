package seedu.academydirectory.versioncontrol.controllers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.academydirectory.versioncontrol.parsers.VcParser.NULL_PARSE;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.academydirectory.versioncontrol.objects.Commit;
import seedu.academydirectory.versioncontrol.objects.Label;
import seedu.academydirectory.versioncontrol.parsers.CommitParser;
import seedu.academydirectory.versioncontrol.parsers.LabelParser;
import seedu.academydirectory.versioncontrol.parsers.TreeParser;
import seedu.academydirectory.versioncontrol.utils.HashGenerator;
import seedu.academydirectory.versioncontrol.utils.HashMethod;

public class ControllerIntegrationTest {
    private static final Path TESTING_DIR = Paths.get("src",
            "test", "data", "VersionControlTest", "ControllerIntegrationTest");
    private static final HashGenerator hashGenerator = new HashGenerator(HashMethod.SHA1);

    @Test
    public void generateCommitFromHead() throws IOException {
        CommitController commitController = new CommitController(hashGenerator, TESTING_DIR);
        CommitParser commitParser = new CommitParser();

        LabelController labelController = new LabelController(hashGenerator, TESTING_DIR);
        LabelParser labelParser = new LabelParser();

        TreeParser treeParser = new TreeParser();
        TreeController treeController = new TreeController(hashGenerator, TESTING_DIR);

        Label actualLabel = labelController.fetchLabelByName("HEAD");
        assertNotEquals(Label.NULL, actualLabel);

        Commit actualCommit = actualLabel.getCommitSupplier().get();
        assertNotEquals(Commit.NULL, actualCommit);

        String[] expectedLabel = labelParser.parse(TESTING_DIR.resolve(Paths.get("HEAD")));
        String[] expectedCommit = commitParser.parse(TESTING_DIR.resolve(Paths.get(expectedLabel[0])));

        assertNotEquals(expectedLabel, NULL_PARSE);
        assertEquals(expectedCommit[0], actualCommit.getHash());
        assertEquals(expectedCommit[1], actualCommit.getAuthor());
        assertEquals(
                assertDoesNotThrow(() -> CommitController.getDf().parse(expectedCommit[2])),
                actualCommit.getDate());
        assertEquals(expectedCommit[3], actualCommit.getMessage());
        assertEquals(expectedCommit[4], actualCommit.getParentSupplier().get().getHash());
        assertEquals(expectedCommit[5], actualCommit.getTreeSupplier().get().getHash());
    }
}
