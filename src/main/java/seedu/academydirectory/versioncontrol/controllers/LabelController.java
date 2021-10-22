package seedu.academydirectory.versioncontrol.controllers;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import seedu.academydirectory.versioncontrol.objects.Commit;
import seedu.academydirectory.versioncontrol.objects.Label;
import seedu.academydirectory.versioncontrol.storage.LabelStorageManager;
import seedu.academydirectory.versioncontrol.utils.HashGenerator;

public class LabelController extends Controller<Label> {
    private final LabelStorageManager labelStorageManager;
    private final Path vcPath;

    /**
     * Creates a Controller for Label object.
     * @param generator produce hash of commit. Hashing algorithm defined in generator
     * @param labelStorageManager labelStorageManager to interact with disk
     */
    public LabelController(HashGenerator generator, LabelStorageManager labelStorageManager) {
        super(generator);
        this.labelStorageManager = labelStorageManager;
        this.vcPath = labelStorageManager.getVcPath();
    }

    /**
     * Constructs a Label object to be saved to disk.
     * @param name Label name
     * @param commit Commit to be labelled
     * @return a Label object which represents a label of the given commit. Returns a null label if unable to write
     */
    public Label createNewLabel(String name, Commit commit) {
        requireNonNull(name);
        requireNonNull(commit);

        String labelFileName = "temp";
        Label temp = new Label(labelFileName, name, () -> commit);

        Path labelPath = this.vcPath.resolve(Path.of(labelFileName));
        try {
            labelStorageManager.write(labelFileName, temp);

            String labelHash = generator.generateHashFromFile(labelPath);
            boolean deletedSuccessfully = labelPath.toFile().delete();

            return new Label(labelHash, name, () -> commit);
        } catch (IOException e) {
            return Label.NULL;
        }
    }

    /**
     * Find a label on disk based on name.
     * @param labelName Hash of the commit saved in disk
     * @return Label object of the given hash
     */
    public Label fetchLabelByName(String labelName) {
        File f = new File(String.valueOf(vcPath));
        File[] matchingFiles = requireNonNull(f.listFiles((x, name) -> name.startsWith(labelName)));
        if (matchingFiles.length == 0) {
            return Label.NULL;
        }

        // Pick first match
        Path labelPath = matchingFiles[0].toPath();
        return labelStorageManager.read(String.valueOf(labelPath.getFileName()));
    }

    public void write(Label label) throws IOException {
        labelStorageManager.write(label.getName(), label);
    }
}
