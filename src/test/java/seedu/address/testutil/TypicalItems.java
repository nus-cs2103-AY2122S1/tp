package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BAKED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_POPULAR;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.newmodel.Inventory;
import seedu.address.newmodel.item.Item;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalItems {

    public static final Item APPLE_PIE = new ItemBuilder().withName("Apple Pie")
            .withId("#222222")
            .withTags("baked").build();
    public static final Item BANANA_MUFFIN = new ItemBuilder().withName("Banana Muffin")
            .withId("#333333")
            .withTags("baked", "bestseller").build();
    public static final Item CHOCOCHIP = new ItemBuilder().withName("Chocholate Chip Cookie")
            .withId("#444444")
            .withTags("baked").build();
    public static final Item DALGONA_COFFEE  = new ItemBuilder().withName("Dalgona Coffee")
            .withId("#555555").build();
    public static final Item EGGNOG = new ItemBuilder().withName("Egg Nog")
            .withId("#666666").build();
    public static final Item FOREST_CAKE = new ItemBuilder().withName("Forest Cake")
            .withId("#777777").build();
    public static final Item GRANOLA_BAR = new ItemBuilder().withName("Granola Bar")
            .withId("#888888").build();

    // Manually added
    public static final Item HONEY_CAKE = new ItemBuilder().withName("Honey Cake").withId("#999999").build();
    public static final Item ICE_CREAM = new ItemBuilder().withName("Ice Cream").withId("#000000").build();

    // Manually added - Item's details found in {@code CommandTestUtil}
    public static final Item BAGEL = new ItemBuilder()
            .withName(VALID_NAME_BAGEL).withId("#123456").withTags(VALID_TAG_BAKED).build();
    public static final Item DONUT = new ItemBuilder()
            .withName(VALID_NAME_DONUT).withId("#789012").withTags(VALID_TAG_BAKED, VALID_TAG_POPULAR)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalItems() {} // prevents instantiation

    /**
     * Returns an {@code Inventory} with all the typical items.
     */
    public static Inventory getTypicalInventory() {
        Inventory typicalInventory = new Inventory();
        for (Item item : getTypicalItems()) {
            typicalInventory.addItem(item);
        }
        return typicalInventory;
    }

    public static List<Item> getTypicalItems() {
        return new ArrayList<>(Arrays.asList(APPLE_PIE, BANANA_MUFFIN, CHOCOCHIP,
                DALGONA_COFFEE, EGGNOG, FOREST_CAKE, GRANOLA_BAR));
    }
}
