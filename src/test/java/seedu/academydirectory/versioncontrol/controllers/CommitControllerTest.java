package seedu.academydirectory.versioncontrol.controllers;

import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

import seedu.academydirectory.versioncontrol.objects.Commit;
import seedu.academydirectory.versioncontrol.objects.Tree;

public class CommitControllerTest {
    @Test
    public void makeCommit() {
        String message = "Testing";
        Supplier<Tree> nullTreeSupplier = () -> Tree.NULL;
        Supplier<Commit> nullCommitSupplier = () -> Commit.NULL;
    }
}
