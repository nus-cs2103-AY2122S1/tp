package seedu.academydirectory.versioncontrol.parsers;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HeadParser extends CommitParser {
    private final CommitParser commitParser = new CommitParser();

    @Override
    public String[] parse(Path file) throws IOException {
        String response = loadFile(file);
        String refHash = response.split("ref: ")[1].split(System.lineSeparator())[0];
        Path p = file.getParent().resolve(Paths.get(refHash));
        return commitParser.parse(p);
    }
}
