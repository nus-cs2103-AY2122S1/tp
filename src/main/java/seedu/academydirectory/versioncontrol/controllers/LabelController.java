package seedu.academydirectory.versioncontrol.controllers;

import static java.util.Objects.requireNonNull;
import static seedu.academydirectory.versioncontrol.parsers.VcParser.NULL_PARSE;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import seedu.academydirectory.versioncontrol.objects.Commit;
import seedu.academydirectory.versioncontrol.objects.Label;
import seedu.academydirectory.versioncontrol.parsers.LabelParser;
import seedu.academydirectory.versioncontrol.utils.HashGenerator;

public class LabelController extends Controller<Label> {
    /**
     * Creates a Controller for Label object.
     * @param generator produce hash of commit. Hashing algorithm defined in generator
     * @param vcPath general path to place version control related files
     */
    public LabelController(HashGenerator generator, Path vcPath) {
        super(generator, vcPath);
    }

    @Override
    public List<String> getWriteableFormat(Label vcObject) {
        return List.of("ref: " + vcObject.getHash());
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
            write(temp);

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
        LabelParser labelParser = new LabelParser();

        File f = new File(String.valueOf(vcPath));
        File[] matchingFiles = requireNonNull(f.listFiles((x, name) -> name.startsWith(labelName)));
        if (matchingFiles.length == 0) {
            return Label.NULL;
        }

        // Pick first match
        Path labelPath = matchingFiles[0].toPath();

        String[] args = labelParser.parse(labelPath);
        if (Arrays.equals(args, NULL_PARSE)) {
            return Label.NULL;
        }
        String refHash = args[0];

        String hash;
        try {
            hash = generator.generateHashFromFile(labelPath);
        } catch (IOException e) {
            return Label.NULL;
        }

        return new Label(hash, labelName, () -> new CommitController(generator, vcPath).fetchCommitByHash(refHash));
    }
}
