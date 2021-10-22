package seedu.academydirectory.versioncontrol.controllers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.academydirectory.testutil.TypicalTrees.TREE1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import seedu.academydirectory.versioncontrol.objects.Tree;
import seedu.academydirectory.versioncontrol.storage.TreeStorageManager;
import seedu.academydirectory.versioncontrol.utils.HashGenerator;
import seedu.academydirectory.versioncontrol.utils.HashMethod;

public class TreeControllerTest {
    private static final Path BLOB_DIR = Paths.get("src", "test", "data",
            "VersionControlTest", "BlobStorage");
    private static final Path DATA_DIR = Paths.get("src", "test", "data",
            "VersionControlTest", "TreeControllerTest");
    private static final Path TESTING_DIR = Paths.get("src", "test", "temp");
    private static final HashGenerator hashGenerator = new HashGenerator(HashMethod.SHA1);

    @Test
    public void createNewTree_blobsPresent_correctlyDuplicated() throws IOException {
        if (TESTING_DIR.toFile().exists()) {
            assertTrue(TESTING_DIR.toFile().delete());
        }
        assertTrue(TESTING_DIR.toFile().mkdir());

        TreeStorageManager treeStorageManager = new TreeStorageManager(TESTING_DIR);
        TreeController treeController = new TreeController(hashGenerator, treeStorageManager);

        // Blobs present -> Correctly Duplicated
        String blobName = "HEAD";
        Path blobPath = BLOB_DIR.resolve(Paths.get(blobName));
        assertTrue(blobPath.toFile().exists()); // Check if blob exists first

        Tree actualTree = treeController.createNewTree(blobPath);
        HashMap<String, String> hashMap = actualTree.getHashMap();
        String[] keySet = hashMap.keySet().toArray(new String[0]);
        for (String vcName : keySet) {
            String realName = hashMap.get(vcName);

            Path actualPath = Paths.get(realName);
            assertTrue(actualPath.toFile().exists());

            Path vcPath = Paths.get(vcName);
            assertTrue(vcPath.toFile().exists());

            String actualHash = assertDoesNotThrow(() ->
                    hashGenerator.generateHashFromFile(actualPath));
            String vcHash = assertDoesNotThrow(() ->
                    hashGenerator.generateHashFromFile(actualPath));
            assertEquals(actualHash, vcHash);
        }

        Files.walk(TESTING_DIR).sorted(Comparator.reverseOrder()).map(Path::toFile)
                .forEach(File::delete);

        assertFalse(Files.exists(TESTING_DIR), "Directory still exists");
    }

    @Test
    public void createNewTree_blobsAbsent_nullTree() throws IOException {
        if (TESTING_DIR.toFile().exists()) {
            assertTrue(TESTING_DIR.toFile().delete());
        }
        assertTrue(TESTING_DIR.toFile().mkdir());

        TreeStorageManager treeStorageManager = new TreeStorageManager(TESTING_DIR);
        TreeController treeController = new TreeController(hashGenerator, treeStorageManager);

        String blobName = "TAIL";
        Path blobPath = BLOB_DIR.resolve(Paths.get(blobName));
        assertFalse(blobPath.toFile().exists()); // Check if blob does not exist

        Tree actualTree = treeController.createNewTree(blobPath);
        assertEquals(Tree.NULL, actualTree);

        Files.walk(TESTING_DIR).sorted(Comparator.reverseOrder()).map(Path::toFile)
                .forEach(File::delete);

        assertFalse(Files.exists(TESTING_DIR), "Directory still exists");
    }

    @Test
    public void fetchTreeByHash() throws IOException {
        if (TESTING_DIR.toFile().exists()) {
            assertTrue(TESTING_DIR.toFile().delete());
        }
        assertTrue(TESTING_DIR.toFile().mkdir());

        TreeStorageManager treeStorageManager = new TreeStorageManager(DATA_DIR);
        TreeController treeController = new TreeController(hashGenerator, treeStorageManager);

        // Exact Hash used -> Commit fetched successfully
        String treeHash = "9d34f3e9ada5ae7cc5c063b905a5d7893f792497";
        Path filepath = DATA_DIR.resolve(Paths.get(treeHash));
        assertTrue(filepath.toFile().exists()); // Check if file exists first

        String hash = hashGenerator.generateHashFromFile(filepath);
        System.out.println(hash);

        Tree actualTree = treeController.fetchTreeByHash(treeHash);
        assertEquals(TREE1.getHash(), actualTree.getHash());

        // fiveCharHash used -> Commit fetched successfully
        treeHash = treeHash.substring(0, 5);
        actualTree = treeController.fetchTreeByHash(treeHash);
        assertEquals(TREE1.getHash(), actualTree.getHash());

        // given hash not present in disk -> Commit.NULL returned
        treeHash = "Testing123";
        actualTree = treeController.fetchTreeByHash(treeHash);
        assertEquals(Tree.NULL, actualTree);

        // given corrupt commit file -> Commit.NULL returned
        treeHash = "corrupted";
        actualTree = treeController.fetchTreeByHash(treeHash);
        assertEquals(Tree.NULL, actualTree);

        assertTrue(TESTING_DIR.toFile().delete());
    }
}
