package seedu.academydirectory.versioncontrol.controllers;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import seedu.academydirectory.versioncontrol.objects.Tree;
import seedu.academydirectory.versioncontrol.storage.TreeStorageManager;
import seedu.academydirectory.versioncontrol.utils.HashGenerator;


public class TreeController extends Controller<Tree> {
    private final TreeStorageManager treeStorageManager;
    private final Path vcPath;

    /**
     * Creates a Controller for Tree object. Use this object to create Tree objects and not by calling Tree's
     * constructor.
     * @param generator produce hash of commit. Hashing algorithm defined in generator
     * @param treeStorageManager TreeStorageManager to interface with disk
     */
    public TreeController(HashGenerator generator, TreeStorageManager treeStorageManager) {
        super(generator);
        this.treeStorageManager = treeStorageManager;
        this.vcPath = treeStorageManager.getVcPath();
    }

    /**
     * Constructs a Tree object to be saved to disk
     * @param blobPaths Path to blobs to be duplicated
     * @return a Tree object which represents mapping between actual filename and version-controlled filename
     */
    public Tree createNewTree(List<Path> blobPaths) {
        // Make a blob snapshot
        ArrayList<Path> blobTargetPaths = new ArrayList<>();
        for (Path blobPath: blobPaths) {
            String blobHash;
            try {
                blobHash = generator.generateHashFromFile(blobPath);
            } catch (IOException e) {
                return Tree.NULL;
            }
            Path blobTargetPath = this.vcPath.resolve(Path.of(blobHash));

            try {
                Files.copy(blobPath, blobTargetPath, REPLACE_EXISTING);
            } catch (IOException e) {
                return Tree.NULL;
            }
            blobTargetPaths.add(blobTargetPath);
        }

        // Write a temporary tree to disk
        String treeFileName = "temp";
        Tree temp = new Tree(treeFileName,
                blobPaths.stream().map(String::valueOf).collect(Collectors.toList()),
                blobTargetPaths.stream().map(String::valueOf).collect(Collectors.toList()));
        Path treePath = this.vcPath.resolve(Path.of(treeFileName));
        try {
            treeStorageManager.write(treeFileName, temp);

            // Delete temporarily created Tree
            String treeHash = generator.generateHashFromFile(treePath);
            boolean deletedSuccessfully = treePath.toFile().delete();

            // Return final tree
            return new Tree(treeHash,
                    blobPaths.stream().map(String::valueOf).collect(Collectors.toList()),
                    blobTargetPaths.stream().map(String::valueOf).collect(Collectors.toList()));
        } catch (IOException e) {
            return Tree.NULL;
        }
    }

    /**
     * Constructs a Tree object to be saved to disk.
     * @param blobPath Path to blob to be duplicated
     * @return a Tree object which represents mapping between actual filename and version-controlled filename
     */
    public Tree createNewTree(Path blobPath) {
        return createNewTree(List.of(blobPath));
    }

    /**
     * Constructs a tree based on hash. Will find the tree object with the same hash in disk
     * @param hash Hash of the tree saved in disk
     * @return Commit object of the given hash
     * @throws IOException if no tree of the same hash can be found in disk
     */
    public Tree fetchTreeByHash(String hash) {
        // Allow for 5 digit hash to be used
        File f = new File(String.valueOf(vcPath));
        File[] matchingFiles = requireNonNull(f.listFiles((x, name) -> name.startsWith(hash)));
        if (matchingFiles.length == 0) {
            return Tree.NULL;
        }

        // Pick first match
        Path filePath = matchingFiles[0].toPath();
        return treeStorageManager.read(String.valueOf(filePath.getFileName()));
    }

    /**
     * Regenerate all blobs pointed at by a @code{Tree}
     * @param tree Mapping containing all the blobs to be regenerated
     * @throws IOException thrown when unable to regenerate
     */
    public void regenerateBlobs(Tree tree) throws IOException {
        HashMap<String, String> hashMap = tree.getHashMap();
        for (String vcFilename : hashMap.keySet()) {
            String actualFilename = hashMap.get(vcFilename);
            Path actualFilepath = Paths.get(actualFilename);
            Path vcFilepath = Paths.get(vcFilename);
            Files.copy(vcFilepath, actualFilepath, REPLACE_EXISTING);
        }
    }

    public void write(Tree tree) throws IOException {
        treeStorageManager.write(tree.getHash(), tree);
    }
}
