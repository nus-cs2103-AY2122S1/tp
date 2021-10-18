package seedu.academydirectory.versioncontrol;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import seedu.academydirectory.versioncontrol.controllers.CommitController;
import seedu.academydirectory.versioncontrol.controllers.TreeController;
import seedu.academydirectory.versioncontrol.objects.Commit;
import seedu.academydirectory.versioncontrol.objects.Tree;
import seedu.academydirectory.versioncontrol.parsers.CommitParser;
import seedu.academydirectory.versioncontrol.parsers.HeadParser;
import seedu.academydirectory.versioncontrol.parsers.TreeParser;
import seedu.academydirectory.versioncontrol.utils.HashGenerator;
import seedu.academydirectory.versioncontrol.utils.HashMethod;

public class VersionManager implements Version {
    private static final Path vcPath = Paths.get("vc");

    private Commit head;

    private final CommitController commitController;
    private final TreeController treeController;

    private final Path storagePath;

    /**
     * Creates a VersionManager that tracks and make commits
     * @param storagePath path to where file storage is saved
     */
    public VersionManager(Path storagePath) {
        HashGenerator generator = new HashGenerator(HashMethod.SHA1);
        this.commitController = new CommitController(generator, vcPath);
        this.treeController = new TreeController(generator, vcPath);

        this.storagePath = storagePath;

        // Make versionControlled path if not exist
        try {
            Files.createDirectories(vcPath);
            Path headPath = vcPath.resolve(Paths.get("HEAD"));
            File file = new File(String.valueOf(headPath));
            if (file.exists()) {
                Commit mostRecent = commitController.generate("HEAD",
                        new HeadParser(), new CommitParser(), new TreeParser(), treeController);
                moveHead(mostRecent);
            } else {
                moveHead(Commit.NULL);
            }
        } catch (Exception e) {
            this.head = Commit.NULL;
            e.printStackTrace();
        }
    }

    /**
     * Commits a particular command
     * @param message Message attached to the Commit for a readable explanation
     * @return whether commit is successful
     */
    public boolean commit(String message) {
        Commit parentCommit = head;
        try {
            // Make VcObjects
            Tree tree = treeController.generate(storagePath);
            Commit newCommit = commitController.generate(message, () -> tree, () -> parentCommit);

            // Write VcObjects to disk
            treeController.write(tree);
            commitController.write(newCommit);

            // Move HEAD pointer
            moveHead(newCommit);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String getPresentableHistory(Commit commit) {
        return commit.getHash().substring(0, 6) + ": " + commit.getMessage();
    }
    @Override
    public List<String> retrieveHistory() {
        Commit currentCommit = head;
        List<String> history = new ArrayList<>();
        history.add(getPresentableHistory(currentCommit));

        Supplier<Commit> parentCommitSupplier = head.getParentSupplier();
        while (parentCommitSupplier.get() != Commit.NULL) {
            currentCommit = parentCommitSupplier.get();
            history.add(getPresentableHistory(currentCommit));
            parentCommitSupplier = currentCommit.getParentSupplier();
        }

        return history;
    }

    private void moveHead(Commit commit) throws IOException {
        this.head = commit;
        String headFilename = "HEAD";

        FileWriter writer = new FileWriter(headFilename);
        writer.append("ref: ").append(commit.getHash());
        writer.flush();

        writer.close();

        Path headPath = Path.of(headFilename);
        Path newHeadPath = vcPath.resolve(headPath);

        Files.move(headPath, newHeadPath, REPLACE_EXISTING);
    }
}
