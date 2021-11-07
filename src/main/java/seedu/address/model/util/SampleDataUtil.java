package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.BookKeeping;
import seedu.address.model.Inventory;
import seedu.address.model.ReadOnlyBookKeeping;
import seedu.address.model.ReadOnlyInventory;
import seedu.address.model.item.Item;
import seedu.address.model.item.Name;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Item[] getSampleItems() {
        return new Item[] {
            new Item(new Name("Oatmeal Cookie"), 140121, 3,
                    getTagSet("baked"), 1.0, 3.0),
            new Item(new Name("Banana Muffin"), 201928, 5,
                    getTagSet("baked"), 2.0, 4.0),
            new Item(new Name("Pecan Pie"), 178522, 7,
                    getTagSet("baked"), 3.0, 5.0),
            new Item(new Name("Oreo Cheesecake"), 109128, 1,
                    getTagSet("desert"), 4.0, 5.0),
            new Item(new Name("Strawberry Shortcake"), 199127, 2,
                    getTagSet("desert"), 2.1, 3.2),
            new Item(new Name("Cold Brew Coffee"), 121858, 5,
                    getTagSet("beverage"), 3.2, 4.4),
        };
    }

    public static ReadOnlyInventory getSampleInventory() {
        Inventory sampleInventory = new Inventory();
        for (Item sampleItem : getSampleItems()) {
            sampleInventory.addItem(sampleItem);
        }
        return sampleInventory;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    public static ReadOnlyBookKeeping getSampleBookKeeping() {
        double cost = Arrays.stream(getSampleItems())
                .map(item -> item.getCostPrice() * item.getCount()).reduce((a, b) -> a + b).get();
        return new BookKeeping(0.0, cost);
    }

}
