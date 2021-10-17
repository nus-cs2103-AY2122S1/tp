package seedu.academydirectory.versioncontrol.controllers;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
     * @param blobPaths Path to blobs to be duplicated
     * @return a Tree object which represents mapping between actual filename and version-controlled filename
     * @throws IOException if vcPath given is not writeable
     */
    public Tree makeTree(List<Path> blobPaths) throws IOException {
        // Make a blob snapshot
        ArrayList<Path> blobTargetPaths = new ArrayList<>();
        for (Path blobPath: blobPaths) {
            String blobHash = generator.generateHashFromFile(blobPath);
            Path blobTargetPath = this.vcPath.resolve(Path.of(blobHash));

            Files.copy(blobPath, blobTargetPath, REPLACE_EXISTING);
            blobTargetPaths.add(blobTargetPath);
        }

        // Write tree to disk
        Tree temp = new Tree("temp",
                blobPaths.stream().map(String::valueOf).collect(Collectors.toList()),
                blobTargetPaths.stream().map(String::valueOf).collect(Collectors.toList()));
        String treeFileName = "temp";

        FileWriter writer = new FileWriter(treeFileName);
        List<List<String>> toBeWritten = temp.getWriteableFormat();
        writeTree(toBeWritten, writer);

        // Move tree file in disk
        Path treePath = Path.of(treeFileName);
        String treeHash = generator.generateHashFromFile(treePath);
        Path treeTargetPath = this.vcPath.resolve(Path.of(treeHash));

        Files.move(treePath, treeTargetPath, REPLACE_EXISTING);

        // Return final tree
        return new Tree(treeHash,
                blobPaths.stream().map(String::valueOf).collect(Collectors.toList()),
                blobTargetPaths.stream().map(String::valueOf).collect(Collectors.toList()));
    }

    /**
     * Constructs a Tree object that is saved to disk.
     * @param blobPath Path to blob to be duplicated
     * @return a Tree object which represents mapping between actual filename and version-controlled filename
     * @throws IOException if vcPath given is not writeable
     */
    public Tree makeTree(Path blobPath) throws IOException {
        return makeTree(List.of(blobPath));
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

    private void writeTree(List<List<String>> writeableTree, FileWriter writer) throws IOException {
        writeableTree.stream()
                .map(xs -> xs.get(0) + " " + xs.get(1))
                .forEach(x -> {
                    try {
                        writer.append(x).append(System.lineSeparator());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        writer.close();
    }
}
