package seedu.academydirectory.model;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import seedu.academydirectory.commons.util.FileUtil;
import seedu.academydirectory.versioncontrol.controllers.CommitController;
import seedu.academydirectory.versioncontrol.controllers.LabelController;
import seedu.academydirectory.versioncontrol.controllers.TreeController;
import seedu.academydirectory.versioncontrol.objects.Commit;
import seedu.academydirectory.versioncontrol.objects.Label;
import seedu.academydirectory.versioncontrol.objects.Tree;
import seedu.academydirectory.versioncontrol.objects.VcObject;
import seedu.academydirectory.versioncontrol.storage.CommitStorageManager;
import seedu.academydirectory.versioncontrol.storage.LabelStorageManager;
import seedu.academydirectory.versioncontrol.storage.TreeStorageManager;
import seedu.academydirectory.versioncontrol.utils.HashGenerator;
import seedu.academydirectory.versioncontrol.utils.HashMethod;

public class VersionControl {
    private Commit headCommit = Commit.NULL;

    private static final String headLabelString = "HEAD";
    private static final String oldLabelString = "temp_LATEST";

    private final TreeController treeController;
    private final CommitController commitController;
    private final LabelController labelController;

    private final Path storagePath;
    private List<VcObject> stageArea = List.of(headCommit);

    public VersionControl(HashMethod hashMethod, Path vcPath, Path storagePath) {
        HashGenerator generator = new HashGenerator(HashMethod.SHA1);
        // Create storage managers
        TreeStorageManager treeStorageManager = new TreeStorageManager(vcPath);
        this.treeController = new TreeController(generator, treeStorageManager);

        CommitStorageManager commitStorageManager = new CommitStorageManager(vcPath, treeStorageManager);
        this.commitController = new CommitController(generator, commitStorageManager);

        LabelStorageManager labelStorageManager = new LabelStorageManager(vcPath, commitStorageManager);
        this.labelController = new LabelController(generator, labelStorageManager);

        this.storagePath = storagePath;
        // Make versionControlled path if not exist
        try {
            Path headPath = vcPath.resolve(Paths.get(headLabelString));
            FileUtil.createIfMissing(headPath);
            if (FileUtil.isFileExists(headPath)) {
                this.headCommit = fetchCommitByLabel(headLabelString);
            } else {
                // Create initial commit
                this.headCommit = Commit.NULL;
                boolean isSuccessful = this.commit("Initial Commit");
                assert isSuccessful;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean commit(String message) {
        Commit parentCommit = this.headCommit;
        // Make VcObjects
        Tree blobTree = treeController.createNewTree(storagePath);
        this.headCommit = commitController.createNewCommit(message, () -> blobTree, () -> parentCommit);
        Label headLabel = labelController.createNewLabel(headLabelString, headCommit);

        this.resetStage();
        stage(headLabel);
        stage(this.headCommit);
        stage(blobTree);
        return true;
    }

    public Commit revert(String fiveCharHash) throws IOException {
        Commit mainCommit = fetchCommitByLabel(oldLabelString);
        assert mainCommit.equals(this.headCommit);

        Commit relevantCommit = commitController.fetchCommitByHash(fiveCharHash);
        if (relevantCommit.equals(Commit.NULL)) {
            return Commit.NULL;
        }

        Tree relevantTree = relevantCommit.getTreeSupplier().get();
        treeController.regenerateBlobs(relevantTree);
        this.headCommit = relevantCommit;

        Label oldLabel = labelController.createNewLabel(oldLabelString, mainCommit);
        Label headLabel = labelController.createNewLabel(headLabelString, headCommit);

        resetStage();
        stage(oldLabel);
        stage(headLabel);
        return relevantCommit;
    }

    public List<VcObject> getStageArea() {
        return stageArea;
    }

    private void stage(VcObject vcObject) {
        stageArea.add(vcObject);
    }

    private void resetStage() {
        stageArea = new ArrayList<>();
    }

    public Commit getHeadCommit() {
        return headCommit;
    }

    public Commit fetchCommitByLabel(String labelName) {
        return labelController.fetchLabelByName(labelName).getCommitSupplier().get();
    }
}
