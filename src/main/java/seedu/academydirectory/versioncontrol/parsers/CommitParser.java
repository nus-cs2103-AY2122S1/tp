package seedu.academydirectory.versioncontrol.parsers;

import java.io.IOException;
import java.nio.file.Path;

import seedu.academydirectory.versioncontrol.objects.Commit;

public class CommitParser extends VcParser<Commit> {

    @Override
    public String[] parse(Path file) throws IOException {
        String response = loadFile(file);
        String[] responseArr = response.split(System.lineSeparator());

        String author = responseArr[0].split(": ")[1];
        String date = responseArr[1].split(": ")[1];
        String[] messageArr = responseArr[2].split(": ");
        String message = messageArr.length == 1 ? "" : messageArr[1];

        String parentHash = responseArr[4].split(": ")[1];
        String treeHash = responseArr[5].split(": ")[1];

        return new String[]{String.valueOf(file.getFileName()), author, date, message, parentHash, treeHash};
    }
}
