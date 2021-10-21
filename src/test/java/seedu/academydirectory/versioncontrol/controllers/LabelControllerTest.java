package seedu.academydirectory.versioncontrol.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.academydirectory.testutil.TypicalCommits.COMMIT1;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.academydirectory.versioncontrol.objects.Commit;
import seedu.academydirectory.versioncontrol.objects.Label;
import seedu.academydirectory.versioncontrol.utils.HashGenerator;
import seedu.academydirectory.versioncontrol.utils.HashMethod;

public class LabelControllerTest {
    private static final Path DATA_DIR = Paths.get("src", "test",
            "data", "VersionControlTest", "LabelControllerTest");
    private static final Path TESTING_DIR = Paths.get("src", "test", "temp");
    private static final HashGenerator hashGenerator = new HashGenerator(HashMethod.SHA1);

    @Test
    public void createLabel() {
        if (TESTING_DIR.toFile().exists()) {
            assertTrue(TESTING_DIR.toFile().delete());
        }
        assertTrue(TESTING_DIR.toFile().mkdir());
        LabelController labelController = new LabelController(hashGenerator, TESTING_DIR);

        // null passed in -> exception thrown
        assertThrows(NullPointerException.class, () -> labelController.createNewLabel(null, null));
        assertThrows(NullPointerException.class, () -> labelController.createNewLabel("NULL", null));

        // Commit1 passed in -> Commit1 labelled
        Label labelledCommit = labelController.createNewLabel("temp", COMMIT1);
        assertEquals(labelledCommit.getCommitSupplier().get(), COMMIT1);

        // Commit.NULL passed in -> Commit.NULL labelled
        Label labelledNullCommit = labelController.createNewLabel("temp", Commit.NULL);
        assertEquals(labelledNullCommit.getCommitSupplier().get(), Commit.NULL);

        assertTrue(TESTING_DIR.toFile().delete());
    }

    @Test
    public void fetchLabelByName() {
        LabelController labelController = new LabelController(hashGenerator, DATA_DIR);

        String labelName = "HEAD";
        Path filepath = DATA_DIR.resolve(Paths.get(labelName));
        assertTrue(filepath.toFile().exists()); // Check if file exists first

        // Correct Name given -> Label fetched successfully
        Label actualLabel = labelController.fetchLabelByName(labelName);
        assertEquals(COMMIT1, actualLabel.getCommitSupplier().get());

        // given name not present in disk -> Label.NULL returned
        actualLabel = labelController.fetchLabelByName("MISSING");
        assertEquals(Label.NULL, actualLabel);
    }
}
