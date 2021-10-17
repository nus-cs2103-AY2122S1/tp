package seedu.academydirectory.versioncontrol;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.academydirectory.versioncontrol.controllers.CommitFactory;
import seedu.academydirectory.versioncontrol.controllers.TreeFactory;
import seedu.academydirectory.versioncontrol.objects.Commit;
import seedu.academydirectory.versioncontrol.objects.Tree;
import seedu.academydirectory.versioncontrol.parsers.CommitParser;
import seedu.academydirectory.versioncontrol.parsers.HeadParser;
import seedu.academydirectory.versioncontrol.utils.HashGenerator;
import seedu.academydirectory.versioncontrol.utils.HashMethod;

public class VersionManager implements Version {
    private static final Path vcPath = Paths.get("vc");

    private Commit head;

    private final CommitFactory commitFactory;
    private final TreeFactory treeFactory;

    private final Path storagePath;

    /**
     * Creates a VersionManager that tracks and make commits
     * @param storagePath path to where file storage is saved
     */
    public VersionManager(Path storagePath) {
        HashGenerator generator = new HashGenerator(HashMethod.SHA1);
        this.commitFactory = new CommitFactory(generator, vcPath);
        this.treeFactory = new TreeFactory(generator, vcPath);

        this.storagePath = storagePath;

        // Make versionControlled path if not exist
        try {
            Files.createDirectories(vcPath);
            Path headPath = vcPath.resolve(Paths.get("HEAD"));
            File file = new File(String.valueOf(headPath));
            if (file.exists()) {
                Commit mostRecent = commitFactory.makeCommit("HEAD", new HeadParser(), new CommitParser(), treeFactory);
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
            Tree tree = treeFactory.makeTree(storagePath);
            Commit newCommit = commitFactory.makeCommit(message, () -> tree, () -> parentCommit);
            moveHead(newCommit);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
