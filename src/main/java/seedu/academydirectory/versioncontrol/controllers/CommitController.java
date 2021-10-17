package seedu.academydirectory.versioncontrol.controllers;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import seedu.academydirectory.versioncontrol.objects.Commit;
import seedu.academydirectory.versioncontrol.objects.Tree;
import seedu.academydirectory.versioncontrol.parsers.CommitParser;
import seedu.academydirectory.versioncontrol.parsers.TreeParser;
import seedu.academydirectory.versioncontrol.utils.HashGenerator;

public class CommitController extends Controller<Commit> {
    private static final DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    /**
     * Creates a Controller for Commit object. Use this object to create Commit objects and not by calling Commit
     * constructor.
     * @param generator produce hash of commit. Hashing algorithm defined in generator
     * @param vcPath general path to place version control related files
     */
    public CommitController(HashGenerator generator, Path vcPath) {
        super(generator, vcPath);
    }

    @Override
    public List<String> getWriteableFormat(Commit commit) {
        String author = "Author: " + System.getProperty("user.name");
        String date = "Date: " + df.format(commit.getDate());
        String message = "Message: " + commit.getMessage();
        String noop = "";
        String parent = "Parent: " + commit.getParentSupplier().get().getHash();
        String treeRef = "TreeRef: " + commit.getTreeSupplier().get().getHash();

        return List.of(author, date, message, noop, parent, treeRef);
    }

    /**
     * Constructs a Commit object to be saved to disk.
     * @param message Commit message for a human-readable format of the commit
     * @param treeSupplier Supplier to the tree object that this Commit points to
     * @param parentCommitSupplier Supplier to the parent Commit that this Commit points to
     * @return a Commit object which represents the change
     * @throws IOException if vcPath given is not writeable
     */
    public Commit generate(String message, Supplier<Tree> treeSupplier, Supplier<Commit> parentCommitSupplier)
            throws IOException {
        // Write a temporary commit to disk
        String commitFileName = "temp";
        String author = System.getProperty("user.name");
        Date date = new Date();
        Commit temp = new Commit(commitFileName, author, date, message, parentCommitSupplier, treeSupplier);

        Path commitPath = this.vcPath.resolve(Path.of(commitFileName));
        write(temp);

        // Delete temporarily created Commit
        String commitHash = generator.generateHashFromFile(commitPath);
        boolean deletedSuccessfully = commitPath.toFile().delete();

        // Return final commit
        return new Commit(commitHash, author, date, message, parentCommitSupplier, treeSupplier);
    }

    /**
     * Constructs a commit based on hash. Will find the commit object with the same hash in disk
     * @param hash Hash of the commit saved in disk
     * @param currentCommitParser Parser that constructs current commit
     * @param nextCommitParser Parser that constructs parent of current commit
     * @param treeController Controller that constructs tree pointed at by current commit
     * @return Commit object of the given hash
     * @throws IOException if no commit of the same hash can be found in disk
     * @throws ParseException if date in the commit in disk cannot be understood
     */
    public Commit generate(String hash, CommitParser currentCommitParser, CommitParser nextCommitParser,
                           TreeParser treeParser,
                           TreeController treeController)
            throws IOException, ParseException {
        Path headPath = vcPath.resolve(Paths.get(hash));
        String[] args = currentCommitParser.parse(vcPath.resolve(Paths.get(hash)));

        hash = args[0];
        String author = args[1];
        Date date = df.parse(args[2]);
        String message = args[3];
        String parentHash = args[4];
        String treeHash = args[5];

        Supplier<Commit> parentCommitSupplier = () -> {
            if (Objects.equals(parentHash, Commit.NULL.getHash())) {
                return Commit.NULL;
            } else {
                try {
                    return generate(parentHash, nextCommitParser, nextCommitParser, treeParser, treeController);
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                    return Commit.NULL;
                }
            }
        };

        Supplier<Tree> treeSupplier = () -> {
            try {
                return treeController.generate(treeHash, treeParser);
            } catch (IOException e) {
                e.printStackTrace();
                return Tree.NULL;
            }
        };

        return new Commit(hash, author, date, message, parentCommitSupplier, treeSupplier);
    }

    public static DateFormat getDf() {
        return df;
    }
}
