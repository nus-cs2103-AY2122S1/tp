package seedu.academydirectory.versioncontrol.parsers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Optional;

import seedu.academydirectory.versioncontrol.objects.VcObject;

/**
 * Represents a Parser that is able to parse a storage file into a {@code VcObject} of type {@code T}.
 */
public abstract class VcParser<T extends VcObject> {
    public static final String[] NULL_PARSE = null;

    protected Optional<String> loadFile(Path filepath) {
        File file = new File(String.valueOf(filepath));
        String result;
        try {
            result = new String(new FileInputStream(file).readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            return Optional.empty();
        }
        return Optional.of(result);
    }

    abstract String[] parse(Path filepath);
}
