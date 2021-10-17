package seedu.address.model.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_CANNON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UNIT_PRICE_CANNON;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProducts.IPAD;
import static seedu.address.testutil.TypicalProducts.IPHONE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.product.exceptions.DuplicateProductException;
import seedu.address.model.product.exceptions.ProductNotFoundException;
import seedu.address.testutil.ProductBuilder;

public class UniqueProductListTest {

    private final UniqueProductList uniqueProductList = new UniqueProductList();

    @Test
    public void contains_nullProduct_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProductList.contains(null));
    }

    @Test
    public void contains_productNotInList_returnsFalse() {
        assertFalse(uniqueProductList.contains(IPHONE));
    }

    @Test
    public void contains_productInList_returnsTrue() {
        uniqueProductList.add(IPHONE);
        assertTrue(uniqueProductList.contains(IPHONE));
    }

    @Test
    public void contains_productWithSameIdentityFieldsInList_returnsTrue() {
        uniqueProductList.add(IPHONE);
        Product editedAlice = new ProductBuilder(IPHONE).withUnitPrice(VALID_UNIT_PRICE_CANNON).build();
        assertTrue(uniqueProductList.contains(editedAlice));
    }

    @Test
    public void add_nullProduct_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProductList.add(null));
    }

    @Test
    public void add_duplicateProduct_throwsDuplicateProductException() {
        uniqueProductList.add(IPHONE);
        assertThrows(DuplicateProductException.class, () -> uniqueProductList.add(IPHONE));
    }

    @Test
    public void setProduct_nullTargetProduct_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProductList.setProduct(null, IPHONE));
    }

    @Test
    public void setProduct_nullEditedProduct_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProductList.setProduct(IPHONE, null));
    }

    @Test
    public void setProduct_targetProductNotInList_throwsProductNotFoundException() {
        assertThrows(ProductNotFoundException.class, () -> uniqueProductList.setProduct(IPHONE, IPHONE));
    }

    @Test
    public void setProduct_editedProductIsSameProduct_success() {
        uniqueProductList.add(IPHONE);
        uniqueProductList.setProduct(IPHONE, IPHONE);
        UniqueProductList expectedUniqueProductList = new UniqueProductList();
        expectedUniqueProductList.add(IPHONE);
        assertEquals(expectedUniqueProductList, uniqueProductList);
    }

    @Test
    public void setProduct_editedProductHasSameIdentity_success() {
        uniqueProductList.add(IPHONE);
        Product editedAlice = new ProductBuilder(IPHONE).withQuantity(VALID_QUANTITY_CANNON).build();
        uniqueProductList.setProduct(IPHONE, editedAlice);
        UniqueProductList expectedUniqueProductList = new UniqueProductList();
        expectedUniqueProductList.add(editedAlice);
        assertEquals(expectedUniqueProductList, uniqueProductList);
    }

    @Test
    public void setProduct_editedProductHasDifferentIdentity_success() {
        uniqueProductList.add(IPHONE);
        uniqueProductList.setProduct(IPHONE, IPAD);
        UniqueProductList expectedUniqueProductList = new UniqueProductList();
        expectedUniqueProductList.add(IPAD);
        assertEquals(expectedUniqueProductList, uniqueProductList);
    }

    @Test
    public void setProduct_editedProductHasNonUniqueIdentity_throwsDuplicateProductException() {
        uniqueProductList.add(IPHONE);
        uniqueProductList.add(IPAD);
        assertThrows(DuplicateProductException.class, () -> uniqueProductList.setProduct(IPHONE, IPAD));
    }

    @Test
    public void remove_nullProduct_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProductList.remove(null));
    }

    @Test
    public void remove_productDoesNotExist_throwsProductNotFoundException() {
        assertThrows(ProductNotFoundException.class, () -> uniqueProductList.remove(IPHONE));
    }

    @Test
    public void remove_existingProduct_removesProduct() {
        uniqueProductList.add(IPHONE);
        uniqueProductList.remove(IPHONE);
        UniqueProductList expectedUniqueProductList = new UniqueProductList();
        assertEquals(expectedUniqueProductList, uniqueProductList);
    }

    @Test
    public void setProducts_nullUniqueProductList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProductList.setProducts((UniqueProductList) null));
    }

    @Test
    public void setProducts_uniqueProductList_replacesOwnListWithProvidedUniqueProductList() {
        uniqueProductList.add(IPHONE);
        UniqueProductList expectedUniqueProductList = new UniqueProductList();
        expectedUniqueProductList.add(IPAD);
        uniqueProductList.setProducts(expectedUniqueProductList);
        assertEquals(expectedUniqueProductList, uniqueProductList);
    }

    @Test
    public void setProducts_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProductList.setProducts((List<Product>) null));
    }

    @Test
    public void setProducts_list_replacesOwnListWithProvidedList() {
        uniqueProductList.add(IPHONE);
        List<Product> productList = Collections.singletonList(IPAD);
        uniqueProductList.setProducts(productList);
        UniqueProductList expectedUniqueProductList = new UniqueProductList();
        expectedUniqueProductList.add(IPAD);
        assertEquals(expectedUniqueProductList, uniqueProductList);
    }

    @Test
    public void setProducts_listWithDuplicateProducts_throwsDuplicateProductException() {
        List<Product> listWithDuplicateProducts = Arrays.asList(IPHONE, IPHONE);
        assertThrows(DuplicateProductException.class, () -> uniqueProductList.setProducts(listWithDuplicateProducts));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueProductList.asUnmodifiableObservableList().remove(0));
    }
}
