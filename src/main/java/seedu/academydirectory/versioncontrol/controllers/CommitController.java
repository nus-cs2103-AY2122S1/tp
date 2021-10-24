package seedu.academydirectory.versioncontrol.controllers;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.function.Supplier;

import seedu.academydirectory.versioncontrol.objects.Commit;
import seedu.academydirectory.versioncontrol.objects.Tree;
import seedu.academydirectory.versioncontrol.storage.CommitStorageManager;
import seedu.academydirectory.versioncontrol.utils.HashGenerator;

public class CommitController extends Controller<Commit> {
    private final CommitStorageManager commitStorageManager;
    private final Path vcPath;

    /**
     * Creates a Controller for Commit object. Use this object to create Commit objects and not by calling Commit
     * constructor.
     * @param generator produce hash of commit. Hashing algorithm defined in generator
     */
    public CommitController(HashGenerator generator, CommitStorageManager commitStorageManager) {
        super(generator);
        this.commitStorageManager = commitStorageManager;
        this.vcPath = commitStorageManager.getVcPath();
    }

    /**
     * Constructs a new Commit object to be saved to disk.
     * @param message Commit message for a human-readable format of the commit
     * @param treeSupplier Supplier to the tree object that this Commit points to
     * @param parentCommitSupplier Supplier to the parent Commit that this Commit points to
     * @return a Commit object which represents the change. Returns a null commit if unable to write
     */
    public Commit createNewCommit(String message, Supplier<Tree> treeSupplier, Supplier<Commit> parentCommitSupplier) {
        requireNonNull(message);
        requireNonNull(treeSupplier);
        requireNonNull(parentCommitSupplier);

        String commitFileName = "temp";
        String author = System.getProperty("user.name");
        Date date = new Date();
        Commit temp = new Commit(commitFileName, author, date, message, parentCommitSupplier, treeSupplier);
        Path commitPath = vcPath.resolve(Paths.get(commitFileName));

        try {
            // Write a temporary commit to disk
            commitStorageManager.write(commitFileName, temp);

            // Delete temporarily created Commit
            String commitHash = generator.generateHashFromFile(commitPath);
            boolean deletedSuccessfully = commitPath.toFile().delete();

            // Return final commit
            return new Commit(commitHash, author, date, message, parentCommitSupplier, treeSupplier);
        } catch (IOException e) {
            return Commit.NULL;
        }
    }

    /**
     * Constructs a commit based on hash. Will find the commit object with the same hash in disk
     * @param hash Hash of the commit saved in disk
     * @return Commit object of the given hash
     */
    public Commit fetchCommitByHash(String hash) {
        // Allow for 5 digit hash to be used
        File f = new File(String.valueOf(vcPath));
        String finalHash = hash.trim();
        File[] matchingFiles = requireNonNull(f.listFiles((x, name) -> name.startsWith(finalHash)));
        if (matchingFiles.length == 0) {
            return Commit.NULL;
        }

        // Pick first match
        Path filePath = matchingFiles[0].toPath();
        return commitStorageManager.read(String.valueOf(filePath.getFileName()));
    }

    public void write(Commit commit) throws IOException {
        commitStorageManager.write(commit.getHash(), commit);
    }
}
