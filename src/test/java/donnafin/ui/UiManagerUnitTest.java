package donnafin.ui;

import donnafin.logic.Logic;
import donnafin.ui.testutil.LogicManagerStub;
import org.junit.jupiter.api.BeforeEach;

public class UiManagerUnitTest {

    private Logic logic;
    private Ui ui;

    @BeforeEach
    public void reset() {
        this.logic = new LogicManagerStub();
        this.ui = new UiManager(logic);
    }

    

}
