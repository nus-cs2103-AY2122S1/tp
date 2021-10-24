package seedu.academydirectory.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import seedu.academydirectory.versioncontrol.objects.Commit;
import seedu.academydirectory.versioncontrol.objects.Label;
import seedu.academydirectory.versioncontrol.objects.Tree;
import seedu.academydirectory.versioncontrol.objects.VcObject;
import seedu.academydirectory.versioncontrol.storage.CommitStorageManager;
import seedu.academydirectory.versioncontrol.storage.LabelStorageManager;
import seedu.academydirectory.versioncontrol.storage.TreeStorageManager;
import seedu.academydirectory.versioncontrol.utils.HashGenerator;
import seedu.academydirectory.versioncontrol.utils.HashMethod;

public class StageAreaStorage {
    TreeStorageManager treeStorageManager;
    CommitStorageManager commitStorageManager;
    LabelStorageManager labelStorageManager;

    public StageAreaStorage(HashMethod hashMethod, Path vcPath) {
        HashGenerator generator = new HashGenerator(hashMethod);

        // Create storage managers
        this.treeStorageManager = new TreeStorageManager(vcPath);
        this.commitStorageManager = new CommitStorageManager(vcPath, treeStorageManager);
        this.labelStorageManager = new LabelStorageManager(vcPath, commitStorageManager);
    }

    public void saveStageArea(List<VcObject> vcObjectList) throws IOException {
        for (VcObject vcObject : vcObjectList) {
            write(vcObject);
        }
    }

    private void write(VcObject vcObject) throws IOException {
        if (vcObject instanceof Commit && !((Commit) vcObject).equals(Commit.NULL)) {
            Commit commit = (Commit) vcObject;
            commitStorageManager.write(commit.getHash(), commit);
        } else if (vcObject instanceof Tree && !((Tree) vcObject).equals(Tree.NULL)) {
            Tree tree = (Tree) vcObject;
            treeStorageManager.write(tree.getHash(), tree);
        } else if (vcObject instanceof Label && !((Label) vcObject).equals(Label.NULL)) {
            Label label = (Label) vcObject;
            labelStorageManager.write(label.getName(), label);
        }
    }
}
