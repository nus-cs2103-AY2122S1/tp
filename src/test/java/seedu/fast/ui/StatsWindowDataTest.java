package seedu.fast.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.fast.ui.StatsWindowData.InvestmentPlanData;
import static seedu.fast.ui.StatsWindowData.PriorityData;

import org.junit.jupiter.api.Test;

import seedu.fast.model.tag.InvestmentPlanTag;
import seedu.fast.model.tag.PriorityTag.HighPriority;
import seedu.fast.model.tag.PriorityTag.LowPriority;

public class StatsWindowDataTest {

    private final PriorityData pDataEqualCounts = new PriorityData(5, 5, 5);
    private final PriorityData pDataDifferentCounts = new PriorityData(10, 20, 30);

    private final InvestmentPlanData iPDataEqualCounts = new InvestmentPlanData(5, 5, 5, 5, 5, 5, 5);
    private final InvestmentPlanData iPDataDifferentCounts = new InvestmentPlanData(1, 2, 3, 4, 5, 6, 7);


    @Test
    public void getMaxName_priorityDataEqualCounts_success() {
        assertEquals(pDataEqualCounts.getMaxName(), HighPriority.NAME);
    }

    @Test
    public void getMaxName_priorityDataDifferentCounts_success() {
        assertEquals(pDataDifferentCounts.getMaxName(), LowPriority.NAME);
    }

    @Test
    public void getMaxValue_priorityDataEqualCounts_success() {
        assertEquals(pDataEqualCounts.getMaxValue(), 5);
    }

    @Test
    public void getMaxValue_priorityDataDifferentCounts_success() {
        assertEquals(pDataDifferentCounts.getMaxValue(), 30);
    }

    @Test
    public void getTotalCount_priorityData_success() {
        assertEquals(pDataDifferentCounts.getTotalCount(), 60);
        assertEquals(pDataEqualCounts.getTotalCount(), 15);
    }


    @Test
    public void getMaxName_investmentPlanDataDifferentCounts_success() {
        assertEquals(iPDataEqualCounts.getMaxName(), InvestmentPlanTag.Savings.NAME);
    }

    @Test
    public void getMaxValue_investmentPlanDataEqualCounts_success() {
        assertEquals(iPDataEqualCounts.getMaxValue(), 5);
    }

    @Test
    public void getMaxValue_investmentPlanDataDifferentCounts_success() {
        assertEquals(iPDataDifferentCounts.getMaxValue(), 7);
    }

    @Test
    public void getTotalCount_investmentPlanData_success() {
        assertEquals(iPDataEqualCounts.getTotalCount(), 35);
        assertEquals(iPDataDifferentCounts.getTotalCount(), 28);
    }
}
