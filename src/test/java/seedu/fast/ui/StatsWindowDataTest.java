package seedu.fast.ui;

import org.junit.jupiter.api.Test;
import seedu.fast.model.tag.InvestmentPlanTag;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.fast.ui.StatsWindowData.PriorityData;
import static seedu.fast.ui.StatsWindowData.InvestmentPlanData;

import seedu.fast.model.tag.PriorityTag.HighPriority;
import seedu.fast.model.tag.PriorityTag.LowPriority;

public class StatsWindowDataTest {

    private final PriorityData pDataEqualCounts = new PriorityData(5, 5, 5);
    private final PriorityData pDataDifferentCounts = new PriorityData(10, 20, 30);

    private final InvestmentPlanData iPDataEqualCounts = new InvestmentPlanData(5, 5, 5, 5, 5, 5, 5);
    private final InvestmentPlanData iPDataDifferentCounts = new InvestmentPlanData(1, 2, 3, 4, 5, 6, 7);


    @Test
    public void getMaxName_PriorityDataEqualCounts_success() {
        assertEquals(pDataEqualCounts.getMaxName(), HighPriority.NAME);
    }

    @Test
    public void getMaxName_PriorityDataDifferentCounts_success() {
        assertEquals(pDataDifferentCounts.getMaxName(), LowPriority.NAME);
    }

    @Test
    public void getMaxValue_PriorityDataEqualCounts_success() {
        assertEquals(pDataEqualCounts.getMaxValue(), 5);
    }

    @Test
    public void getMaxValue_PriorityDataDifferentCounts_success() {
        assertEquals(pDataDifferentCounts.getMaxValue(), 30);
    }

    @Test
    public void getTotalCount_PriorityData_success() {
        assertEquals(pDataDifferentCounts.getTotalCount(), 60);
        assertEquals(pDataEqualCounts.getTotalCount(), 15);
    }


    @Test
    public void getMaxName_InvestmentPlanDataDifferentCounts_success() {
        assertEquals(iPDataEqualCounts.getMaxName(), InvestmentPlanTag.Savings.NAME);
    }

    @Test
    public void getMaxValue_InvestmentPlanDataEqualCounts_success() {
        assertEquals(iPDataEqualCounts.getMaxValue(), 5);
    }

    @Test
    public void getMaxValue_InvestmentPlanDataDifferentCounts_success() {
        assertEquals(iPDataDifferentCounts.getMaxValue(), 7);
    }

    @Test
    public void getTotalCount_InvestmentPlanData_success() {
        assertEquals(iPDataEqualCounts.getTotalCount(), 35);
        assertEquals(iPDataDifferentCounts.getTotalCount(), 28);
    }
}