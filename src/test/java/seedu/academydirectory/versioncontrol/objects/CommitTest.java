package seedu.academydirectory.versioncontrol.objects;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.academydirectory.testutil.TypicalCommits.COMMIT1;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.junit.jupiter.api.Test;

import seedu.academydirectory.testutil.CommitBuilder;
import seedu.academydirectory.versioncontrol.utils.HashGenerator;
import seedu.academydirectory.versioncontrol.utils.HashMethod;

public class CommitTest {

    @Test
    public void equals() {
        Commit commit1Copy = new CommitBuilder(COMMIT1).build();
        // same values -> returns true
        assertEquals(COMMIT1, commit1Copy);

        // same object -> returns true
        assertEquals(COMMIT1, COMMIT1);

        // null -> returns false
        assertNotEquals(COMMIT1, null);

        // same hash, all other attributes different -> returns true
        Commit editedCommitSameHash = new CommitBuilder(COMMIT1)
                .withAuthor(COMMIT1.getAuthor() + "Hi")
                .withDate(new Date())
                .withMessage(COMMIT1.getMessage() + "Hi")
                .withParentSupplier(() -> COMMIT1)
                .withTreeSupplier(() -> new Tree("testing", "1", "2"))
                .build();
        assertEquals(COMMIT1, editedCommitSameHash);

        // different hash, all other attributes same -> returns false
        String filename = "CommitParserTest";
        Path filepath = Paths.get("src", "test", "data", "VersionControlTest", "ParserTest", filename);
        String newHash = assertDoesNotThrow(() -> new HashGenerator(HashMethod.SHA1).generateHashFromFile(filepath));

        Commit editedCommitDiffHash = new CommitBuilder(COMMIT1).withHash(newHash).build();
        assertNotEquals(COMMIT1, editedCommitDiffHash);

        // null commits -> returns false
        assertNotEquals(COMMIT1, Commit.NULL);

        // null commits and null commits -> return true
        assertEquals(Commit.NULL, Commit.NULL);
    }
}
