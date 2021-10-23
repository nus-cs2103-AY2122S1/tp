package seedu.address.model.summary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.CategoryCode;


public class SummaryTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_getNumberOfContacts_success() {
        ReadOnlyAddressBook addressBook = model.getAddressBook();
        int size = addressBook.getPersonList().size();

        Summary summary = new Summary(getTypicalAddressBook());
        int summarySize = summary.getNumberOfContacts();

        assertEquals(size, summarySize);
    }

    @Test
    public void execute_getPercentageCategory_success() {
        Summary summary = new Summary(getTypicalAddressBook());
        HashMap<String, Integer> summarySize = summary.getPercentageCategory();
        List<String> categoryValues = CategoryCode.CATEGORY_VALUES;
        assertEquals(categoryValues.size(), summarySize.size());

        for (String key : categoryValues) {
            assertTrue(summarySize.containsKey(key));
        }
    }

//    @Test
//    public void execute_getPercentageRatings_success() {
//        Summary summary = new Summary(getTypicalAddressBook());
//        HashMap<String, Integer> summarySize = summary.getPercentageRatings();
//        List<String> categoryValues = CategoryCode.CATEGORY_VALUES;
//        assertEquals(categoryValues.size(), summarySize.size());
//
//        for (String key : categoryValues) {
//            assertTrue(summarySize.containsKey(key));
//        }
//    }

}
