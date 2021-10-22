package seedu.academydirectory.versioncontrol.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.academydirectory.versioncontrol.objects.Commit;
import seedu.academydirectory.versioncontrol.objects.Label;
import seedu.academydirectory.versioncontrol.storage.CommitStorageManager;
import seedu.academydirectory.versioncontrol.storage.LabelStorageManager;
import seedu.academydirectory.versioncontrol.storage.TreeStorageManager;
import seedu.academydirectory.versioncontrol.utils.HashGenerator;
import seedu.academydirectory.versioncontrol.utils.HashMethod;

public class ControllerIntegrationTest {
    private static final Path TESTING_DIR = Paths.get("src",
            "test", "data", "VersionControlTest", "ControllerIntegrationTest");
    private static final HashGenerator hashGenerator = new HashGenerator(HashMethod.SHA1);

    @Test
    public void generateCommitFromHead() throws IOException {
        // Create storage managers
        TreeStorageManager treeStorageManager = new TreeStorageManager(TESTING_DIR);
        TreeController treeController = new TreeController(hashGenerator, treeStorageManager);

        CommitStorageManager commitStorageManager = new CommitStorageManager(TESTING_DIR, treeStorageManager);
        CommitController commitController = new CommitController(hashGenerator, commitStorageManager);

        LabelStorageManager labelStorageManager = new LabelStorageManager(TESTING_DIR, commitStorageManager);
        LabelController labelController = new LabelController(hashGenerator, labelStorageManager);

        Label actualLabel = labelController.fetchLabelByName("HEAD");
        assertNotEquals(Label.NULL, actualLabel);

        Commit actualCommit = actualLabel.getCommitSupplier().get();
        assertNotEquals(Commit.NULL, actualCommit);

        Label expectedLabel = labelStorageManager.read("HEAD");
        Commit expectedCommit = expectedLabel.getCommitSupplier().get();

        assertEquals(expectedLabel, actualLabel);
        assertEquals(expectedCommit, actualCommit);
    }
}
