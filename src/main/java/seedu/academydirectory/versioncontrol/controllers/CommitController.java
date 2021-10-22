package seedu.academydirectory.versioncontrol.controllers;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Supplier;

import seedu.academydirectory.versioncontrol.objects.Commit;
import seedu.academydirectory.versioncontrol.objects.Tree;
import seedu.academydirectory.versioncontrol.storage.CommitStorageManager;
import seedu.academydirectory.versioncontrol.storage.TreeStorageManager;
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

    /**
     * Retrieve all the ancestors of a given Commit object
     * @param commit Commit object
     * @return ancestors of given commit object, including the given Commit object
     */
    public List<Commit> retrieveCommitHistory(Commit commit) {
        return retrieveCommitHistory(commit, Commit.NULL);
    }

    /**
     * Retrieve all the ancestors of a given Commit object, up to an end Commit object. If end Commit object is an
     * ancestor, then all Commit objects up to but excluding the end Commit object will be included. Otherwise,
     * all ancestor Commit objects are returned.
     * @param commit Commit object
     * @param endExclusive end Commit object
     * @return ancestors of given commit object, including the given Commit object but excluding the end Commit
     */
    public List<Commit> retrieveCommitHistory(Commit commit, Commit endExclusive) {
        List<Commit> history = new ArrayList<>();
        if (commit.equals(endExclusive) || commit.equals(Commit.NULL)) {
            return new ArrayList<>();
        }

        history.add(commit);
        Supplier<Commit> parentCommitSupplier = commit.getParentSupplier();
        while (!parentCommitSupplier.get().equals(endExclusive)) {
            commit = parentCommitSupplier.get();
            history.add(commit);
            parentCommitSupplier = commit.getParentSupplier();
        }
        return history;
    }

    /**
     * Return the lowest common ancestor of two given commit objects.
     * @param commitA First Commit object
     * @param commitB Second Commit object
     * @return Lowest common ancestor of both commit objects.
     */
    public Commit findLca(Commit commitA, Commit commitB) {
        requireNonNull(commitA);
        requireNonNull(commitB);

        if (commitA.equals(Commit.NULL) || commitB.equals(Commit.NULL)) {
            return Commit.NULL;
        }

        List<Commit> fromA = retrieveCommitHistory(commitA);
        List<Commit> fromB = retrieveCommitHistory(commitB);

        int shiftDepth = Math.abs(fromA.size() - fromB.size());
        Commit toMove = fromA.size() > fromB.size() ? commitA : commitB;
        Commit notMoved = fromA.size() > fromB.size() ? commitB : commitA;
        Commit afterMove = move(toMove, shiftDepth);

        while (!notMoved.equals(afterMove)) {
            notMoved = move(notMoved, 1);
            afterMove = move(afterMove, 1);
        }
        assert notMoved.equals(afterMove);
        return notMoved;
    }

    /**
     * Return the ancestor that is the furthest away from the queried Commit object, limited by the given end Commit.
     * @param queriedCommit Commit object whose highest ancestor is sought out
     * @param endExclusive Commit object which limits the search
     * @return furthest ancestor of queriedCommit but is child of endExclusive
     */
    public Commit getHighestAncestor(Commit queriedCommit, Commit endExclusive) {
        if (queriedCommit.equals(Commit.NULL) || queriedCommit.getParentSupplier().get().equals(endExclusive)) {
            return queriedCommit;
        }
        return getHighestAncestor(queriedCommit.getParentSupplier().get(), endExclusive);
    }

    private Commit move(Commit start, int numStep) {
        if (start.equals(Commit.NULL) || numStep == 0) {
            return start;
        }
        return move(start.getParentSupplier().get(), numStep - 1);
    }

    public void write(Commit commit) throws IOException {
        commitStorageManager.write(commit.getHash(), commit);
    }
}
