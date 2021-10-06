package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.Inventory;
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
            new Item(new Name("Oatmeal Cookie"), "#140121", 3,
                getTagSet("baked")),
            new Item(new Name("Banana Muffin"), "#201928", 5,
                    getTagSet("baked")),
            new Item(new Name("Pecan Pie"), "#178522",7,
                    getTagSet("baked")),
            new Item(new Name("Oreo Cheesecake"), "#109128", 1,
                    getTagSet("desert")),
            new Item(new Name("Strawberry Shortcake"), "#091287", 2,
                    getTagSet("desert")),
            new Item(new Name("Cold Brew Coffee"), "#001858", 5,
                    getTagSet("beverage")),
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

}
