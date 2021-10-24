package seedu.academydirectory.model;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import seedu.academydirectory.versioncontrol.objects.Commit;
import seedu.academydirectory.versioncontrol.objects.VcObject;

public interface VersionedModel extends Model {
    /**
     * Commits a change and tie the change with a given message
     * @param message Message attached to the Commit for a readable explanation
     */
    boolean commit(String message);

    Commit revert(String fiveCharHash) throws IOException, ParseException;

    List<VcObject> getStageArea();

    Commit getHeadCommit();

    Commit fetchCommitByLabel(String temp_latest);
}
