package seedu.academydirectory.versioncontrol.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Supplier;

import seedu.academydirectory.versioncontrol.objects.Commit;
import seedu.academydirectory.versioncontrol.objects.Tree;
import seedu.academydirectory.versioncontrol.parsers.CommitParser;
import seedu.academydirectory.versioncontrol.utils.HashGenerator;

public class CommitFactory {
    private static final DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private final HashGenerator generator;
    private final Path vcPath;

    /**
     * Creates a Factory for Commit object. Use this object to create Commit objects and not by calling Commit's
     * constructor.
     * @param generator produce hash of commit. Hashing algorithm defined in generator
     * @param vcPath general path to place version control related files
     */
    public CommitFactory(HashGenerator generator, Path vcPath) {
        this.generator = generator;
        this.vcPath = vcPath;
    }

    /**
     * Constructs a Commit object that is saved to disk.
     * @param message Commit message for a human-readable format of the commit
     * @param treeSupplier Supplier to the tree object that this Commit points to
     * @param parentCommitSupplier Supplier to the parent Commit that this Commit points to
     * @return a Commit object which represents the change
     * @throws IOException if vcPath given is not writeable
     */
    public Commit makeCommit(String message, Supplier<Tree> treeSupplier, Supplier<Commit> parentCommitSupplier)
            throws IOException {
        String fileName = "temp";
        Date date = new Date();

        FileWriter writer = new FileWriter(fileName);
        String author = System.getProperty("user.name");
        writer.append("Author: ").append(author).append(System.lineSeparator());
        writer.flush();

        writer.append("Date: ").append(df.format(date)).append(System.lineSeparator());
        writer.flush();

        writer.append("Message: ").append(message).append(System.lineSeparator());
        writer.flush();

        writer.append(System.lineSeparator());
        writer.flush();

        writer.append("Parent: ").append(parentCommitSupplier.get().getHash()).append(System.lineSeparator());
        writer.flush();

        writer.append("BlobRef: ").append(treeSupplier.get().getHash()).append(System.lineSeparator());
        writer.flush();

        writer.close();
        Path path = Path.of(fileName);
        String hash = generator.generateHashFromFile(path);
        Path targetPath = this.vcPath.resolve(Path.of(hash));

        Files.move(path, targetPath);
        return new Commit(hash, author, date, message, parentCommitSupplier, treeSupplier);
    }

    /**
     * Constructs a commit based on hash. Will find the commit object with the same hash in disk
     * @param hash Hash of the commit saved in disk
     * @param currentCommitParser Parser that constructs current commit
     * @param nextCommitParser Parser that constructs parent of current commit
     * @param treeFactory Factory that constructs tree pointed at by current commit
     * @return Commit object of the given hash
     * @throws IOException if no commit of the same hash can be found in disk
     * @throws ParseException if date in the commit in disk cannot be understood
     */
    public Commit makeCommit(String hash, CommitParser currentCommitParser, CommitParser nextCommitParser,
                             TreeFactory treeFactory)
            throws IOException, ParseException {
        String[] args = currentCommitParser.parse(vcPath.resolve(Paths.get(hash)));
        assert hash.equals(args[0]);

        String author = args[1];
        Date date = df.parse(args[2]);
        String message = args[3];
        String parentHash = args[4];
        String treeHash = args[5];

        Supplier<Commit> parentCommitSupplier = () -> {
            try {
                return makeCommit(parentHash, nextCommitParser, nextCommitParser, treeFactory);
            } catch (IOException | ParseException e) {
                e.printStackTrace();
                return null;
            }
        };

        Supplier<Tree> treeSupplier = () -> {
            try {
                return treeFactory.makeTree(vcPath.resolve(Paths.get(treeHash)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        };

        return new Commit(hash, author, date, message, parentCommitSupplier, treeSupplier);
    }
}
