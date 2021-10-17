package seedu.academydirectory.versioncontrol.controllers;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import seedu.academydirectory.versioncontrol.objects.Tree;
import seedu.academydirectory.versioncontrol.parsers.TreeParser;
import seedu.academydirectory.versioncontrol.utils.HashGenerator;


public class TreeFactory {
    private final HashGenerator generator;
    private final Path vcPath;

    /**
     * Creates a Factory for Tree object. Use this object to create Tree objects and not by calling Tree's
     * constructor.
     * @param generator produce hash of commit. Hashing algorithm defined in generator
     * @param vcPath general path to place version control related files
     */
    public TreeFactory(HashGenerator generator, Path vcPath) {
        this.generator = generator;
        this.vcPath = vcPath;
    }

    /**
     * Constructs a Tree object that is saved to disk.
     * @param blobPath Path to blob to be duplicated
     * @return a Tree object which represents mapping between actual filename and version-controlled filename
     * @throws IOException if vcPath given is not writeable
     */
    public Tree makeTree(Path blobPath) throws IOException {
        // Make a blob snapshot
        String blobHash = generator.generateHashFromFile(blobPath);
        Path blobTargetPath = this.vcPath.resolve(Path.of(blobHash));
        Files.copy(blobPath, blobTargetPath, REPLACE_EXISTING);

        // Make the tree
        String treeFileName = "temp";

        FileWriter writer = new FileWriter(treeFileName);
        writer.append(blobHash).append(" ").append(String.valueOf(blobPath.getFileName()));
        writer.flush();

        writer.close();
        Path treePath = Path.of(treeFileName);
        String treeHash = generator.generateHashFromFile(treePath);
        Path treeTargetPath = this.vcPath.resolve(Path.of(treeHash));

        Files.move(treePath, treeTargetPath, REPLACE_EXISTING);

        System.out.println(blobPath);
        System.out.println(blobTargetPath);
        Tree test = new Tree(treeHash, "Null", "Null");

        return new Tree(treeHash, String.valueOf(blobPath), String.valueOf(blobTargetPath));
    }

    /**
     * Constructs a tree based on hash. Will find the tree object with the same hash in disk
     * @param hash Hash of the tree saved in disk
     * @param treeParser Parser that constructs current tree
     * @return Commit object of the given hash
     * @throws IOException if no tree of the same hash can be found in disk
     */
    public Tree makeTree(String hash, TreeParser treeParser) throws IOException {
        String[] args = treeParser.parse(vcPath.resolve(Paths.get(hash)));
        System.out.println(Arrays.toString(args));
        assert hash.equals(args[0]);
        return new Tree(args[0], args[1], args[2]);
    }
}
