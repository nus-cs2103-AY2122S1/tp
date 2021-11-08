package seedu.smartnus.ui.panel;

import java.util.ArrayList;
import java.util.List;

import seedu.smartnus.logic.Logic;

/**
 * Manage what panel is to be shown.
 */
public class PanelManager {
    private List<Panel> panelList;
    private Logic logic;

    /**
     * Constructor of a panel manager.
     * @param logic The logic component of the app.
     */
    public PanelManager(Logic logic) {
        panelList = new ArrayList<>();
        this.logic = logic;
    }

    /**
     * Adds a panel to the panel manager.
     * @param panel The panel to be added.
     */
    public void addPanel(Panel panel) {
        panelList.add(panel);
    }

    /**
     * Render the panel and show it to the user.
     * @param panel The panel to be shown.
     */
    public void showPanel(Panel panel) {
        panel.render(logic);
    }
}
