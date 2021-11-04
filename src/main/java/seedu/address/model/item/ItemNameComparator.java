package seedu.address.model.item;

import java.util.Comparator;

/**
 * Comparators that compares items by their names. Ignores case
 */
public class ItemNameComparator implements Comparator<Item> {
    @Override
    public int compare(Item item1, Item item2) {
        String name1 = item1.getName().fullName.toLowerCase();
        String name2 = item2.getName().fullName.toLowerCase();

        return name1.compareTo(name2);
    }
}
