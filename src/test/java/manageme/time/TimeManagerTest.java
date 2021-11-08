package manageme.time;

import org.junit.jupiter.api.Test;

import manageme.model.ModelManager;
import manageme.testutil.Assert;

public class TimeManagerTest {

    private ModelManager modelManager = new ModelManager();
    private TimeManager timeManager = new TimeManager(modelManager.getManageMe());

    @Test
    public void updateTasks_nullManageMe_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> timeManager.updateTasks(null));
    }

}
