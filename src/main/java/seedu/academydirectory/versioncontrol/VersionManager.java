package seedu.academydirectory.versioncontrol;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
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
import seedu.academydirectory.versioncontrol.utils.HashGenerator;
import seedu.academydirectory.versioncontrol.utils.HashMethod;

public class VersionManager implements Version {
    private static final Path vcPath = Paths.get("vc");
    private static final SimpleDateFormat DF = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");

    private Commit head;

    private final CommitController commitController;
    private final TreeController treeController;
    private final LabelController labelController;

    private final Path storagePath;

    /**
     * Creates a VersionManager that tracks and make commits
     * @param storagePath path to where file storage is saved
     */
    public VersionManager(Path storagePath) {
        HashGenerator generator = new HashGenerator(HashMethod.SHA1);
        this.commitController = new CommitController(generator, vcPath);
        this.treeController = new TreeController(generator, vcPath);
        this.labelController = new LabelController(generator, vcPath);

        this.storagePath = storagePath;

        // Make versionControlled path if not exist
        try {
            Files.createDirectories(vcPath);
            Path headPath = vcPath.resolve(Paths.get("HEAD"));
            File file = new File(String.valueOf(headPath));
            if (file.exists()) {
                Label head = labelController.fetchLabelByName("HEAD");
                Commit mostRecent = head.getCommitSupplier().get();
                moveHead(mostRecent);
            } else {
                // Create initial commit
                this.head = Commit.NULL;
                boolean isSuccessful = this.commit("Initial Commit");
                assert isSuccessful;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Commits a particular command
     * @param message Message attached to the Commit for a readable explanation
     * @return whether commit is successful
     */
    @Override
    public boolean commit(String message) {
        Commit parentCommit = head;
        try {
            // Make VcObjects
            Tree tree = treeController.createNewTree(storagePath);
            Commit newCommit = commitController.createNewCommit(message, () -> tree, () -> parentCommit);

            // Write VcObjects to disk
            treeController.write(tree);
            commitController.write(newCommit);

            // Move HEAD pointer
            moveHead(newCommit);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String getPresentableHistory(Commit commit, int num, String label) {
        assert num == 0 || num == 1;
        if (num == 0) {
            return commit.getHash().substring(0, 5) + " - " + DF.format(commit.getDate()) + " " + label;
        } else {
            return "\t\t" + commit.getMessage();
        }
    }

    @Override
    public List<String> retrieveHistory() {
        return retrieveHistoryAdvanced();
    }

    private List<String> retrieveHistoryAdvanced() {
        Label latestLabel = labelController.fetchLabelByName("temp_LATEST");

        Commit headCommit = head;
        Commit latestCommit = latestLabel.getCommitSupplier().get();

        Commit lca = commitController.findLca(headCommit, latestCommit);

        Commit latestAncestor = commitController.getHighestAncestor(latestCommit, lca);
        Commit headAncestor = commitController.getHighestAncestor(headCommit, lca);
        assert !headAncestor.equals(latestAncestor); // Violates LCA definition

        List<Commit> earlyHistory = commitController.retrieveCommitHistory(lca);
        List<Commit> latestToEarly = commitController.retrieveCommitHistory(latestCommit, lca);
        List<Commit> headToEarly = commitController.retrieveCommitHistory(headCommit, lca);

        List<Commit> sortedBranch = Stream.concat(latestToEarly.stream(), headToEarly.stream())
                .sorted(Comparator.comparing(Commit::getDate)).collect(Collectors.toList());
        List<Commit> sortedEarlyHistory = earlyHistory.stream().sorted(Comparator.comparing(Commit::getDate))
                .collect(Collectors.toList());

        List<String> result = new ArrayList<>();
        for (Commit commit : sortedEarlyHistory) {
            result.add("| " + getPresentableHistory(commit, 1, ""));
            result.add("* " + getPresentableHistory(commit, 0, commit.equals(headCommit) ? "(HEAD)" : ""));
        }

        if (sortedBranch.size() == 0) {
            Collections.reverse(result);
            return result;
        }

        result.add("|/"); // Separates early history from branch

        // Latest on left lane
        for (Commit commit : sortedBranch) {
            if (latestToEarly.contains(commit)) {
                result.add("| | " + getPresentableHistory(commit, 1, ""));
                result.add("* | " + getPresentableHistory(commit, 0, commit.equals(latestCommit) ? "(prior)" : ""));
            } else {
                result.add("| | " + getPresentableHistory(commit, 1, ""));
                result.add("| * " + getPresentableHistory(commit, 0, commit.equals(headCommit) ? "(HEAD)" : ""));
            }
        }
        Collections.reverse(result);
        return result;
    }

    @Override
    public Commit revert(String fiveCharHash) throws IOException, ParseException {
        Label latest = labelController.createNewLabel("temp_LATEST", head);
        labelController.write(latest.getName(), latest);
        Commit mainCommit = latest.getCommitSupplier().get();
        assert mainCommit.equals(head);

        Commit relevantCommit = commitController.fetchCommitByHash(fiveCharHash);
        if (relevantCommit.equals(Commit.NULL)) {
            return Commit.NULL;
        }

        Tree relevantTree = relevantCommit.getTreeSupplier().get();
        treeController.regenerateBlobs(relevantTree);
        moveHead(relevantCommit);
        return relevantCommit;
    }

    private void moveHead(Commit commit) throws IOException {
        this.head = commit;
        String headFilename = "HEAD";

        FileWriter writer = new FileWriter(headFilename);
        writer.append("ref: ").append(commit.getHash());
        writer.flush();

        writer.close();

        Path headPath = Path.of(headFilename);
        Path newHeadPath = vcPath.resolve(headPath);

        Files.move(headPath, newHeadPath, REPLACE_EXISTING);
    }
}
