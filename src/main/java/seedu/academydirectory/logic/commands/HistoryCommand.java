package seedu.academydirectory.logic.commands;

import static java.util.Objects.requireNonNull;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TimeZone;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.academydirectory.logic.AdditionalViewType;
import seedu.academydirectory.logic.commands.exceptions.CommandException;
import seedu.academydirectory.model.AdditionalInfo;
import seedu.academydirectory.model.VersionControlController;
import seedu.academydirectory.model.VersionedModel;
import seedu.academydirectory.versioncontrol.objects.Commit;

/**
 * Lists all commands used in the Academy Directory to the user.
 */
public class HistoryCommand extends Command {
    public static final String COMMAND_WORD = "history";
    public static final String MESSAGE_SUCCESS = "Commit history shown";
    public static final String HELP_MESSAGE = "#### [For Advanced Users] Viewing Commit History: `history`\n"
            + "Shows local commit history.\n"
            + "\n"
            + "Format: `history`\n"
            + "\n"
            + "- Only commands that result in a state change in Academy "
            + "Directory will be committed. Such commands include\n"
            + "  `add`, `edit`, `delete`, `clear`, and more. For a full list, "
            + "refer to the [appendix](#appendix-a-version-controlled-commands).\n"
            + "- Commits are sorted based on time, with the most recent commit "
            + "shown at the top and the initial commit shown\n"
            + "  at the bottom\n"
            + "- The commit corresponding to current Academy Directory state is labelled with \"(HEAD)\"\n"
            + "- The commit corresponding to current branch is labelled with \"(MAIN)\"\n"
            + "- The commit corresponding to old branch is labelled with \"(OLD)\"\n"
            + "- Old branch is shown at the left, current branch at the right\n"
            + "- The corresponding commit hash is the five character string displayed\n"
            + "- Note that current implementation of `history` command can only show 2 branches: \"OLD\" and \n"
            + "\"MAIN\". If there are already two branches and a third branch is going to be made, then the \n"
            + "oldest branch will not be visible.";

    private static final Supplier<SimpleDateFormat> dateFormatSupplier = () -> {
        SimpleDateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
        df.setTimeZone(TimeZone.getTimeZone(ZoneId.of("Asia/Singapore")));
        return df;
    };

    @Override
    public CommandResult execute(VersionedModel model) throws CommandException {
        requireNonNull(model);
        String result = String.join("\n", retrieveHistory(model));

        model.setAdditionalViewType(AdditionalViewType.TEXT);
        model.setAdditionalInfo(AdditionalInfo.of(result));
        return new CommandResult(MESSAGE_SUCCESS);
    }

    private List<String> retrieveHistory(VersionedModel model) {
        Commit headCommit = model.getHeadCommit();
        Commit currLatestCommit = model.fetchCommitByLabel(VersionControlController.CURRENT_LABEL_STRING);
        Commit oldLatestCommit = model.fetchCommitByLabel(VersionControlController.OLD_LABEL_STRING);

        Commit lca = currLatestCommit.findLca(oldLatestCommit);

        List<Commit> earlyHistory = lca.getHistory();
        List<Commit> currLatestToEarly = currLatestCommit.getHistory(lca);
        List<Commit> oldLatestToEarly = oldLatestCommit.getHistory(lca);

        List<Commit> sortedBranch = Stream.concat(oldLatestToEarly.stream(), currLatestToEarly.stream())
                .sorted(Comparator.comparing(Commit::getDate)).collect(Collectors.toList());
        List<Commit> sortedEarlyHistory = earlyHistory.stream().sorted(Comparator.comparing(Commit::getDate))
                .collect(Collectors.toList());

        List<String> result = new ArrayList<>();
        for (Commit commit : sortedEarlyHistory) {
            String label = getLabel(commit, currLatestCommit, oldLatestCommit, headCommit);
            result.add("| " + getPresentableHistory(commit, 1, label));
            result.add("* " + getPresentableHistory(commit, 0, label));
        }

        if (sortedBranch.size() == 0) {
            Collections.reverse(result);
            return result;
        }

        result.add("|/"); // Separates early history from branch

        // Latest on left lane
        for (Commit commit : sortedBranch) {
            String label = getLabel(commit, currLatestCommit, oldLatestCommit, headCommit);
            if (oldLatestToEarly.contains(commit)) {
                result.add("| | " + getPresentableHistory(commit, 1, label));
                result.add("* | " + getPresentableHistory(commit, 0, label));
            } else {
                result.add("| | " + getPresentableHistory(commit, 1, label));
                result.add("| * " + getPresentableHistory(commit, 0, label));
            }
        }
        Collections.reverse(result);
        return result;
    }

    private String getPresentableHistory(Commit commit, int idx, String label) {
        assert idx == 0 || idx == 1;
        if (idx == 0) {
            return commit.getHash().substring(0, 5) + " - " + dateFormatSupplier.get()
                    .format(commit.getDate()) + " " + label;
        } else {
            return "\t\t" + commit.getMessage();
        }
    }

    private String getLabel(Commit commit, Commit currLatestCommit, Commit oldLatestCommit, Commit headCommit) {
        List<String> result = new ArrayList<>();
        if (commit.equals(headCommit)) {
            result.add("(HEAD)");
        }
        if (commit.equals(currLatestCommit)) {
            result.add("(MAIN)");
        }
        if (commit.equals(oldLatestCommit)) {
            result.add("(OLD)");
        }
        return String.join(" ", result);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof HistoryCommand);
    }
}
