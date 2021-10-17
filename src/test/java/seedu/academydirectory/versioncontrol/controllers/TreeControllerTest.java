package seedu.academydirectory.versioncontrol.controllers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.academydirectory.testutil.TypicalTrees.TREE1;
import static seedu.academydirectory.testutil.TypicalTrees.TREE2;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.academydirectory.versioncontrol.objects.Tree;
import seedu.academydirectory.versioncontrol.parsers.TreeParser;
import seedu.academydirectory.versioncontrol.utils.HashGenerator;
import seedu.academydirectory.versioncontrol.utils.HashMethod;

public class TreeControllerTest {
    private static final Path SRC_DIR = Paths.get("src", "test", "data",
            "VersionControlTest", "ControllerTest");
    private static final Path TESTING_DIR = Paths.get("src", "test", "temp");
    private static final HashGenerator hashGenerator = new HashGenerator(HashMethod.SHA1);

    @Test
    public void generateTree() {
        if (TESTING_DIR.toFile().exists()) {
            assertTrue(TESTING_DIR.toFile().delete());
        }
        assertTrue(TESTING_DIR.toFile().mkdir());

        TreeController treeController = new TreeController(hashGenerator, SRC_DIR);

        // Nulls
        assertThrows(NullPointerException.class, () -> treeController.generate((Path) null));

        // Build from disk
        String hash = "34cb4b0c49f5ba8f1d052884440edd1045a4c558";
        Tree builtTree = assertDoesNotThrow(() -> treeController.generate(hash,
                new TreeParser()));
        assertEquals(hash, builtTree.getHash());
        assertEquals(builtTree.getHashMap().get("vc/54594835555fa465ebc6aad1c2b778da64d5f616"),
                "data/academydirectory.json");
    }

    @Test
    public void getWriteableFormat() {
        if (TESTING_DIR.toFile().exists()) {
            assertTrue(TESTING_DIR.toFile().delete());
        }
        assertTrue(TESTING_DIR.toFile().mkdir());

        TreeController treeController = new TreeController(hashGenerator, TESTING_DIR);

        // Null tree
        assertEquals(List.of("null null"), treeController.getWriteableFormat(Tree.NULL));

        assertEquals(List.of("world.hello hello.world"), treeController.getWriteableFormat(TREE1));
        assertEquals(List.of("world_hello Hello.png", "world_hello? Hello World.java"),
                treeController.getWriteableFormat(TREE2));
    }
}
