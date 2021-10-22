package seedu.academydirectory.versioncontrol.parsers;

import java.nio.file.Path;
import java.util.Optional;

import seedu.academydirectory.versioncontrol.objects.Label;

public class LabelParser extends VcParser<Label> {
    @Override
    public String[] parse(Path file) {
        Optional<String> response = loadFile(file);
        if (response.isEmpty()) {
            return NULL_PARSE;
        }

        try {
            String refHash = response.get().split("ref: ")[1].split(System.lineSeparator())[0];
            return new String[]{refHash};
        } catch (ArrayIndexOutOfBoundsException e) {
            return NULL_PARSE;
        }
    }
}
