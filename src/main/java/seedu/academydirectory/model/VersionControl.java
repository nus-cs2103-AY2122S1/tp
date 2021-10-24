package seedu.academydirectory.model;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private static final SimpleDateFormat DF = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");

    private Commit headCommit = Commit.NULL;
    private Label headLabel = Label.NULL;
    private Label oldLabel = Label.NULL;
    private Tree blobTree = Tree.NULL;

    private static final String headLabelString = "HEAD";
    private static final String oldLabelString = "temp_LATEST";

    private HashGenerator hashGenerator;

    private TreeController treeController;
    private CommitController commitController;
    private LabelController labelController;

    private Path vcPath;
    private Path storagePath;

    private List<VcObject> stageArea = List.of(headCommit, headLabel, oldLabel, blobTree);

    public VersionControl(HashMethod hashMethod, Path vcPath, Path storagePath) {
        this.vcPath = vcPath;

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
            Files.createDirectories(vcPath);
            Path headPath = vcPath.resolve(Paths.get(headLabelString));
            File file = new File(String.valueOf(headPath));
            if (file.exists()) {
                Label head = labelController.fetchLabelByName(headLabelString);
                Commit mostRecent = head.getCommitSupplier().get();
                moveHead(mostRecent);
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

    private void moveHead(Commit commit) throws IOException {
        this.headCommit = commit;
        String headFilename = headLabelString;

        FileWriter writer = new FileWriter(headFilename);
        writer.append("ref: ").append(commit.getHash());
        writer.flush();

        writer.close();

        Path headPath = Path.of(headFilename);
        Path newHeadPath = vcPath.resolve(headPath);

        Files.move(headPath, newHeadPath, REPLACE_EXISTING);
    }

    public boolean commit(String message) {
        Commit parentCommit = this.headCommit;
        // Make VcObjects
        this.blobTree = treeController.createNewTree(storagePath);
        this.headCommit = commitController.createNewCommit(message, () -> this.blobTree, () -> parentCommit);
        this.headLabel = labelController.createNewLabel(headLabelString, headCommit);

        this.resetStage();
        stage(this.headLabel);
        stage(this.headCommit);
        stage(this.blobTree);
        return true;
    }

    public Commit revert(String fiveCharHash) throws IOException {
        Label latest = labelController.createNewLabel(oldLabelString, this.headCommit);
        Commit mainCommit = latest.getCommitSupplier().get();
        assert mainCommit.equals(this.headCommit);

        Commit relevantCommit = commitController.fetchCommitByHash(fiveCharHash);
        if (relevantCommit.equals(Commit.NULL)) {
            return Commit.NULL;
        }

        Tree relevantTree = relevantCommit.getTreeSupplier().get();
        treeController.regenerateBlobs(relevantTree);
        this.headCommit = relevantCommit;

        resetStage();
        stage(latest);
        this.headLabel = labelController.createNewLabel(headLabelString, headCommit);
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

//    private String getPresentableHistory(Commit commit, int num, String label) {
//        assert num == 0 || num == 1;
//        if (num == 0) {
//            return commit.getHash().substring(0, 5) + " - " + DF.format(commit.getDate()) + " " + label;
//        } else {
//            return "\t\t" + commit.getMessage();
//        }
//    }
}
