package seedu.academydirectory.versioncontrol.reader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.academydirectory.commons.util.FileUtil;
import seedu.academydirectory.versioncontrol.objects.VcObject;

public abstract class VersionControlObjectReader<T extends VcObject> {
    protected final Path vcPath;

    /**
     * Creates a VersionControlObjectReader to build
     * @param vcPath path to load and save files to
     */
    protected VersionControlObjectReader(Path vcPath) {
        this.vcPath = vcPath;
    }

    /**
     * Reads a given VcObject from file, with filename being the given name
     */
    public T read(String name) {
        Path filePath = this.vcPath.resolve(Path.of(name));
        Optional<String[]> responseArr = loadFile(filePath).map(s -> s.split(System.lineSeparator()));
        return getProgrammableFormat(Stream.concat(Stream.of(name), Stream.of(responseArr.orElse(new String[]{})))
                .collect(Collectors.toList()));
    }

    private Optional<String> loadFile(Path filepath) {
        String result;
        try {
            result = FileUtil.readFromFile(filepath);
        } catch (IOException e) {
            return Optional.empty();
        }
        return Optional.of(result);
    }

    protected abstract T getProgrammableFormat(List<String> vcObject);
}
