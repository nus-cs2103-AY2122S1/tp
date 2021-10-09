package seedu.address.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.item.Item;
import seedu.address.model.item.Name;

class OrderTest {

    private Order order = new Order();
    private Item milk = new Item(new Name("Milk"), "AS0123", 15, new HashSet<>());
    private Item milkWithNameOnly = new Item(new Name("Milk"), "Dummy ID", 15, new HashSet<>());
    private Item milkWithIdOnly = new Item(new Name("Dummy name"), "AS0123", 15, new HashSet<>());


    @Test
    public void addItem_normalItem_itemAdded() {
        order.addItem(milk);
        assertEquals(order.getOrderItems(), List.of(milk));
    }

    @Test
    public void removeItem_normalItem_itemRemoved() {
        order.removeItem(milk);
        assertEquals(order.getOrderItems(), new ArrayList<Item>());
    }

    @Test
    public void removeItem_onlyNameMatches_itemRemoved() {
        order.addItem(milk);
        order.removeItem(milkWithNameOnly);
        assertEquals(order.getOrderItems(), new ArrayList<Item>());
    }

    @Test
    public void removeItem_onlyIdMatches_itemRemoved() {
        order.addItem(milk);
        order.removeItem(milkWithIdOnly);
        assertEquals(order.getOrderItems(), new ArrayList<Item>());
    }
}
