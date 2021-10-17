package seedu.academydirectory.versioncontrol.parsers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import seedu.academydirectory.versioncontrol.objects.VcObject;

/**
 * Represents a Parser that is able to parse a storage file into a {@code VcObject} of type {@code T}.
 */
public abstract class VcParser<T extends VcObject> {
    protected String loadFile(Path filepath) throws IOException {
        File file = new File(String.valueOf(filepath));
        FileInputStream inputStream = new FileInputStream(file);
        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    }

    abstract String[] parse(Path filepath) throws IOException;
}
