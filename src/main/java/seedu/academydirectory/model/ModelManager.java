package seedu.academydirectory.model;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.util.Objects.requireNonNull;
import static seedu.academydirectory.commons.util.CollectionUtil.requireAllNonNull;

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
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.academydirectory.commons.core.GuiSettings;
import seedu.academydirectory.commons.core.LogsCenter;
import seedu.academydirectory.model.student.Student;
import seedu.academydirectory.versioncontrol.controllers.CommitController;
import seedu.academydirectory.versioncontrol.controllers.LabelController;
import seedu.academydirectory.versioncontrol.controllers.TreeController;
import seedu.academydirectory.versioncontrol.objects.Commit;
import seedu.academydirectory.versioncontrol.objects.Label;
import seedu.academydirectory.versioncontrol.objects.Tree;
import seedu.academydirectory.versioncontrol.storage.CommitStorageManager;
import seedu.academydirectory.versioncontrol.storage.LabelStorageManager;
import seedu.academydirectory.versioncontrol.storage.TreeStorageManager;
import seedu.academydirectory.versioncontrol.utils.HashGenerator;
import seedu.academydirectory.versioncontrol.utils.HashMethod;

/**
 * Represents the in-memory model of the academy directory data.
 */
public class ModelManager implements VersionedModel {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private static final Path vcPath = Paths.get("vc");
    private static final SimpleDateFormat DF = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");

    private final AcademyDirectory academyDirectory;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredStudents;

    private Commit head;
    private final CommitController commitController;
    private final TreeController treeController;
    private final LabelController labelController;
    private final Path storagePath;

    /**
     * Initializes a ModelManager with the given academyDirectory and userPrefs.
     */
    public ModelManager(ReadOnlyAcademyDirectory academyDirectory, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(academyDirectory, userPrefs);

        logger.fine("Initializing with academy directory: " + academyDirectory + " and user prefs " + userPrefs);

        this.academyDirectory = new AcademyDirectory(academyDirectory);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.academyDirectory.getStudentList());

        Path vcPath = Paths.get("vc");
        HashGenerator generator = new HashGenerator(HashMethod.SHA1);

        // Create storage managers
        TreeStorageManager treeStorageManager = new TreeStorageManager(vcPath);
        this.treeController = new TreeController(generator, treeStorageManager);

        CommitStorageManager commitStorageManager = new CommitStorageManager(vcPath, treeStorageManager);
        this.commitController = new CommitController(generator, commitStorageManager);

        LabelStorageManager labelStorageManager = new LabelStorageManager(vcPath, commitStorageManager);
        this.labelController = new LabelController(generator, labelStorageManager);

        this.storagePath = userPrefs.getAcademyDirectoryFilePath();

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

    public ModelManager() {
        this(new AcademyDirectory(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAcademyDirectoryFilePath() {
        return userPrefs.getAcademyDirectoryFilePath();
    }

    @Override
    public void setAcademyDirectoryFilePath(Path academyDirectoryFilePath) {
        requireNonNull(academyDirectoryFilePath);
        userPrefs.setAcademyDirectoryFilePath(academyDirectoryFilePath);
    }

    //=========== AcademyDirectory ================================================================================

    @Override
    public void setAcademyDirectory(ReadOnlyAcademyDirectory academyDirectory) {
        this.academyDirectory.resetData(academyDirectory);
    }

    @Override
    public ReadOnlyAcademyDirectory getAcademyDirectory() {
        return academyDirectory;
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return academyDirectory.hasStudent(student);
    }

    @Override
    public void deleteStudent(Student target) {
        academyDirectory.removeStudent(target);
    }

    @Override
    public void addStudent(Student student) {
        academyDirectory.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override

    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);
        academyDirectory.setStudent(target, editedStudent);
    }

    //=========== Filtered Student List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of
     * {@code versionedAcademyDirectory}
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    @Override
    public <T> ObservableList<T> getFilteredStudentListView(Function<? super Student, ? extends T> function) {
        requireNonNull(function);
        return filteredStudents
                .stream()
                .map(function)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return academyDirectory.equals(other.academyDirectory)
                && userPrefs.equals(other.userPrefs)
                && filteredStudents.equals(other.filteredStudents);
    }

    //=========== Version Methods =========================================================================

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
            Tree newTree = treeController.createNewTree(storagePath);
            Commit newCommit = commitController.createNewCommit(message, () -> newTree, () -> parentCommit);

            // Write VcObjects to disk
            treeController.write(newTree);
            commitController.write(newCommit);

            // Move HEAD pointer
            moveHead(newCommit);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<String> retrieveHistory() {
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
        labelController.write(latest);
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

    private String getPresentableHistory(Commit commit, int num, String label) {
        assert num == 0 || num == 1;
        if (num == 0) {
            return commit.getHash().substring(0, 5) + " - " + DF.format(commit.getDate()) + " " + label;
        } else {
            return "\t\t" + commit.getMessage();
        }
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
