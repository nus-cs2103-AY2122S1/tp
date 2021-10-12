package seedu.address.model.item;

import java.util.Comparator;

/**
 * Comparators that compares items by their count.
 */
public class ItemCountComparator implements Comparator<Item> {
    @Override
    public int compare(Item item1, Item item2) {
        return Integer.compare(item1.getCount(), item2.getCount());
    }
}
