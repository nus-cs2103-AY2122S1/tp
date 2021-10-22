package seedu.academydirectory.versioncontrol.parsers;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import seedu.academydirectory.versioncontrol.objects.Commit;

public class CommitParser extends VcParser<Commit> {

    @Override
    public String[] parse(Path file) {
        Optional<String[]> responseArr = loadFile(file).map(s -> s.split(System.lineSeparator()));
        if (responseArr.isEmpty()) {
            return NULL_PARSE;
        }

        // Field checks
        int numField = Arrays.stream(responseArr.get()).map(x -> x.split(": ").length == 2
                        ? x.split(": ")[0]
                        : "")
                .filter(x -> !x.equals("")).toArray().length;
        if (numField != 5) {
            return NULL_PARSE;
        }

        return Stream.concat(Stream.of(String.valueOf(file.getFileName())),
                Arrays.stream(responseArr.get()).map(x -> x.split(": ").length == 2
                        ? x.split(": ")[1]
                        : ""))
                .toArray(String[]::new);
    }
}
