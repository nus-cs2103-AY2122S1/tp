package seedu.academydirectory.versioncontrol.parsers;

import java.io.IOException;
import java.nio.file.Path;

import seedu.academydirectory.versioncontrol.objects.Tree;

public class TreeParser extends VcParser<Tree> {
    @Override
    public String[] parse(Path filepath) throws IOException {
        String response = loadFile(filepath);
        return response.split(" ");
    }
}
