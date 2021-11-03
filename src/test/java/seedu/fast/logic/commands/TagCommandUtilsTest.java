package seedu.fast.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.fast.commons.util.TagCommandUtils;
import seedu.fast.model.tag.InvestmentPlanTag;
import seedu.fast.model.tag.PriorityTag;
import seedu.fast.model.tag.Tag;

public class TagCommandUtilsTest {

    @Test
    public void hasMultiplePriorityTags() {
        Tag normalTag = Tag.createTag("test");
        Tag lowPriorityTag = Tag.createTag(PriorityTag.LowPriority.COMMAND);
        Tag highPriorityTag = Tag.createTag(PriorityTag.HighPriority.COMMAND);
        Tag savingInvestmentPlanTag = Tag.createTag(InvestmentPlanTag.Savings.COMMAND);

        Set<Tag> emptySet = new HashSet<>();

        Set<Tag> setWithNoPriorityTag = new HashSet<>();
        setWithNoPriorityTag.add(normalTag);
        setWithNoPriorityTag.add(savingInvestmentPlanTag);

        Set<Tag> setWithOnePriorityTag = new HashSet<>();
        setWithOnePriorityTag.add(normalTag);
        setWithOnePriorityTag.add(lowPriorityTag);
        setWithOnePriorityTag.add(savingInvestmentPlanTag);

        Set<Tag> setWithTwoPriorityTag = new HashSet<>();
        setWithTwoPriorityTag.add(normalTag);
        setWithTwoPriorityTag.add(lowPriorityTag);
        setWithTwoPriorityTag.add(savingInvestmentPlanTag);
        setWithTwoPriorityTag.add(highPriorityTag);

        //cases with different amounts of priority tags
        assertTrue(TagCommandUtils.hasMultiplePriorityTags(setWithTwoPriorityTag));
        assertFalse(TagCommandUtils.hasMultiplePriorityTags(setWithNoPriorityTag));
        assertFalse(TagCommandUtils.hasMultiplePriorityTags(setWithOnePriorityTag));
        assertFalse(TagCommandUtils.hasMultiplePriorityTags(emptySet));
        //case if null
        assertThrows(NullPointerException.class, () -> TagCommandUtils.hasMultiplePriorityTags(null));
    }

}
