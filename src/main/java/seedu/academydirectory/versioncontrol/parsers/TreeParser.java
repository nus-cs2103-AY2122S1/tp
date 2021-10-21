package seedu.academydirectory.versioncontrol.parsers;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import seedu.academydirectory.versioncontrol.objects.Tree;

public class TreeParser extends VcParser<Tree> {
    @Override
    public String[] parse(Path file) {
        Optional<String> response = loadFile(file);
        if (response.isEmpty()) {
            return NULL_PARSE;
        }

        return Stream.concat(Stream.of(file.getFileName().toString()),
                Arrays.stream(response.get().split(System.lineSeparator()))).toArray(String[]::new);
    }
}
