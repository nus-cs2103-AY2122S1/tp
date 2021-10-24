package seedu.academydirectory.model;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import seedu.academydirectory.commons.util.FileUtil;
import seedu.academydirectory.storage.VersionControlReader;
import seedu.academydirectory.versioncontrol.objects.Commit;
import seedu.academydirectory.versioncontrol.objects.Label;
import seedu.academydirectory.versioncontrol.objects.Tree;
import seedu.academydirectory.versioncontrol.objects.VcObject;
import seedu.academydirectory.versioncontrol.utils.HashGenerator;
import seedu.academydirectory.versioncontrol.utils.HashMethod;

public class VersionControl {
    private Commit headCommit = Commit.NULL;

    private static final String headLabelString = "HEAD";
    private static final String oldLabelString = "temp_LATEST";

    private final VersionControlReader versionControlReader;

    private final Path storagePath;
    private List<VcObject> stageArea = List.of(headCommit);

    public VersionControl(HashMethod hashMethod, Path vcPath, Path storagePath) {
        this.versionControlReader = new VersionControlReader(hashMethod, vcPath);
        HashGenerator generator = new HashGenerator(hashMethod);

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
        Tree blobTree = versionControlReader.createNewTree(storagePath);
        this.headCommit = versionControlReader.createNewCommit(message, () -> blobTree, () -> parentCommit);
        Label headLabel = versionControlReader.createNewLabel(headLabelString, headCommit);

        this.resetStage();
        stage(headLabel);
        stage(this.headCommit);
        stage(blobTree);
        return true;
    }

    public Commit revert(String fiveCharHash) throws IOException {
        Commit mainCommit = fetchCommitByLabel(oldLabelString);
        assert mainCommit.equals(this.headCommit);

        Commit relevantCommit = versionControlReader.fetchCommitByHash(fiveCharHash);
        if (relevantCommit.equals(Commit.NULL)) {
            return Commit.NULL;
        }

        Tree relevantTree = relevantCommit.getTreeSupplier().get();
        this.headCommit = relevantCommit;

        Label oldLabel = versionControlReader.createNewLabel(oldLabelString, mainCommit);
        Label headLabel = versionControlReader.createNewLabel(headLabelString, headCommit);

        regenerateBlobs(relevantTree);

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
        return versionControlReader.fetchLabelByName(labelName).getCommitSupplier().get();
    }

    private void regenerateBlobs(Tree tree) throws IOException {
        HashMap<String, String> hashMap = tree.getHashMap();
        for (String vcFilename : hashMap.keySet()) {
            String actualFilename = hashMap.get(vcFilename);
            Path actualFilepath = Paths.get(actualFilename);
            Path vcFilepath = Paths.get(vcFilename);
            Files.copy(vcFilepath, actualFilepath, REPLACE_EXISTING);
        }
    }
}
