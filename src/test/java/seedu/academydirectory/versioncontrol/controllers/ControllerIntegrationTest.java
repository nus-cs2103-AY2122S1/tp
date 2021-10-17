package seedu.academydirectory.versioncontrol.controllers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.academydirectory.versioncontrol.objects.Commit;
import seedu.academydirectory.versioncontrol.parsers.CommitParser;
import seedu.academydirectory.versioncontrol.parsers.HeadParser;
import seedu.academydirectory.versioncontrol.parsers.TreeParser;
import seedu.academydirectory.versioncontrol.utils.HashGenerator;
import seedu.academydirectory.versioncontrol.utils.HashMethod;

public class ControllerIntegrationTest {
    private static final Path TESTING_DIR = Paths.get("src",
            "test", "data", "VersionControlTest", "ControllerTest");
    private static final HashGenerator hashGenerator = new HashGenerator(HashMethod.SHA1);

    @Test
    public void generateCommitFromHead() {
        CommitController commitController = new CommitController(hashGenerator, TESTING_DIR);
        CommitParser commitParser = new CommitParser();
        HeadParser headParser = new HeadParser();
        TreeParser treeParser = new TreeParser();
        TreeController treeController = new TreeController(hashGenerator, TESTING_DIR);

        Commit actualCommit = assertDoesNotThrow(() -> commitController.generate("HEAD",
                headParser,
                commitParser,
                treeParser,
                treeController));

        String[] expectedCommit = assertDoesNotThrow(() -> headParser.parse(TESTING_DIR
                .resolve(Paths.get("HEAD"))));

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
