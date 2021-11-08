package ay2122s1_cs2103t_w16_2.btbb.logic.commands.general;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.LogsCenter;
import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.commons.util.JsonUtil;
import ay2122s1_cs2103t_w16_2.btbb.exception.CommandException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.Command;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandResult;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

/**
 * Switches to tab identified by its index.
 */
public class TabCommand extends Command {
    public static final String COMMAND_WORD = "tab";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Switches to the tab identified by the index number.\n"
            + "Parameters: INDEX (must be either 1 or 2)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_CHANGE_TAB_SUCCESS = "Changed tab to: %1$s tab";

    public static final String MESSAGE_INVALID_TAB_INDEX = "The tab index provided is invalid. "
            + "The index can only be 1 or 2.";

    private static final Logger logger = LogsCenter.getLogger(JsonUtil.class);

    private final Index targetIndex;

    /**
     * Constructs a {@code TabCommand}.
     *
     * @param targetIndex Target index identifying the tab to switch to.
     */
    public TabCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        logger.info("Executing " + TabCommand.class.getSimpleName());

        requireNonNull(model);
        UiTab[] tabList = UiTab.values();

        if (targetIndex.getZeroBased() >= tabList.length) {
            throw new CommandException(MESSAGE_INVALID_TAB_INDEX);
        }

        UiTab tabToSwitchTo = tabList[targetIndex.getZeroBased()];

        return new CommandResult(String.format(MESSAGE_CHANGE_TAB_SUCCESS, tabToSwitchTo.toString()), tabToSwitchTo);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TabCommand // instanceof handles nulls
                && targetIndex.equals(((TabCommand) other).targetIndex)); // state check
    }
}
