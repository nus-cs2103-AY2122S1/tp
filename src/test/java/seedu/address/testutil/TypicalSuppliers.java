package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.SupplierCommandTestUtil.VALID_DELIVERY_DETAIL_AMY;
import static seedu.address.logic.commands.SupplierCommandTestUtil.VALID_DELIVERY_DETAIL_BOB;
import static seedu.address.logic.commands.SupplierCommandTestUtil.VALID_SUPPLY_TYPE_BEEF;
import static seedu.address.logic.commands.SupplierCommandTestUtil.VALID_SUPPLY_TYPE_CHICKEN;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Rhrh;
import seedu.address.model.person.supplier.Supplier;

/**
 * A utility class containing a list of {@code Supplier} objects to be used in tests.
 */
public class TypicalSuppliers {

    public static final Supplier JAVIER = new SupplierBuilder().withName("Javier Phon")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("javier@example.com")
            .withPhone("94351253").withTags("friends").withSupplyType("Chicken Thighs")
            .withDeliveryDetails("2021-09-19 08:00").build();
    public static final Supplier CHETWIN = new SupplierBuilder().withName("Chetwin Low")
            .withAddress("5 Faber Drive").withEmail("chetty@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").withSupplyType("Chicken Breast")
            .withDeliveryDetails("05-05-2021 1630").build();
    public static final Supplier THANG = new SupplierBuilder().withName("Pham Ba Thang").withPhone("95352563")
            .withEmail("thang@example.com").withAddress("wall street").withSupplyType("Pineapples")
            .withDeliveryDetails("2021-10-17 18:15").build();
    public static final Supplier CLEMENT = new SupplierBuilder().withName("Kong Fanji").withPhone("87652533")
            .withEmail("Fanji@example.com").withAddress("10th street").withTags("friends")
            .withSupplyType("Duel Masters").withDeliveryDetails("21-10-2021 1100").build();
    public static final Supplier HERNPING = new SupplierBuilder().withName("Hern Ping").withPhone("9482224")
            .withEmail("hp@example.com").withAddress("michegan ave").withSupplyType("Hernpiblo")
            .withDeliveryDetails("11:30 22-10-2021").build();
    public static final Supplier FIORA = new SupplierBuilder().withName("Fiora Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").withSupplyType("Beef")
            .withDeliveryDetails("1030 23-10-2021").build();
    public static final Supplier GREG = new SupplierBuilder().withName("Greg Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").withSupplyType("Fish")
            .withDeliveryDetails("09:30 2021-10-24").build();

    // Manually added
    public static final Supplier HOON_SUPPLIER = new SupplierBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withSupplyType("Mee Hoon Kueh")
            .withDeliveryDetails("0730 2021-10-25").build();
    public static final Supplier IDA_SUPPLIER = new SupplierBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withSupplyType("Pork")
            .withDeliveryDetails("15:00 2021-12-25").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Supplier AMY = new SupplierBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND)
            .withSupplyType(VALID_SUPPLY_TYPE_CHICKEN).withDeliveryDetails(VALID_DELIVERY_DETAIL_AMY).build();
    public static final Supplier BOB = new SupplierBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withSupplyType(VALID_SUPPLY_TYPE_BEEF).withDeliveryDetails(VALID_DELIVERY_DETAIL_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Chetwin"; // A keyword that matches CHETWIN

    private TypicalSuppliers() {} // prevents instantiation

    /**
     * Returns an {@code Rhrh} with all the typical suppliers.
     */
    public static Rhrh getTypicalRhrhSuppliers() {
        Rhrh ab = new Rhrh();
        for (Supplier supplier : getTypicalSuppliers()) {
            ab.addSupplier(supplier);
        }
        return ab;
    }

    public static List<Supplier> getTypicalSuppliers() {
        return new ArrayList<>(Arrays.asList(JAVIER, CHETWIN, THANG, CLEMENT, HERNPING, FIORA, GREG));
    }
}
