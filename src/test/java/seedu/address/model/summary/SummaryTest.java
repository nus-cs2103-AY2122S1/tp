package seedu.address.model.summary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.PersonBuilder;

public class SummaryTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    String totalContacts = "Total Number of Contacts: ";

    @Test
    public void execute_getNumberOfContacts_success() {
        ReadOnlyAddressBook addressBook = model.getAddressBook();
        int size = addressBook.getPersonList().size();

        Summary summary = new Summary(getTypicalAddressBook());
        int summarySize = summary.getNumberOfContacts();

        assertEquals(size, summarySize);
    }

    @Test
    public void execute_getNumberOfContactsGui_success() {
        ReadOnlyAddressBook addressBook = model.getAddressBook();
        int size = addressBook.getPersonList().size();

        Summary summary = new Summary(getTypicalAddressBook());
        String summarySize = summary.getNumberOfContactsGui();

        assertEquals(totalContacts + String.valueOf(size), summarySize);
    }

    @Test
    public void execute_getPercentageCategory_successAfterUpdate() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        ReadOnlyAddressBook addressBook = model.getAddressBook();
        Summary summary = new Summary(addressBook);
        HashMap<String, Integer> summaryCategory = summary.getPercentageCategory();

        String category = PersonBuilder.DEFAULT_CATEGORY_CODE;
        model.addPerson(new PersonBuilder().withName("Alice").build());


        ReadOnlyAddressBook addressBookAfterAdd = model.getAddressBook();
        Summary summaryAfterAdd = new Summary(addressBookAfterAdd);
        HashMap<String, Integer> summaryCategoryAfterAdd = summaryAfterAdd.getPercentageCategory();

        assertEquals(summaryCategory.size(), summaryCategoryAfterAdd.size());

        for (String i : summaryCategoryAfterAdd.keySet()) {
            Integer afterAdd = summaryCategoryAfterAdd.get(i);
            Integer beforeAdd = summaryCategory.get(i);
            Integer afterAddAtt = afterAdd - 1;

            if (i.equals(category)) {
                assertEquals(afterAddAtt, beforeAdd);
            } else {
                assertEquals(afterAdd, beforeAdd);
            }
        }

    }

    @Test
    public void execute_getPercentageRating_successAfterUpdate() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        ReadOnlyAddressBook addressBook = model.getAddressBook();
        Summary summary = new Summary(addressBook);
        HashMap<String, Integer> summaryRating = summary.getPercentageRatings();

        String category = PersonBuilder.DEFAULT_RATING;
        model.addPerson(new PersonBuilder().withName("Alice").build());


        ReadOnlyAddressBook addressBookAfterAdd = model.getAddressBook();
        Summary summaryAfterAdd = new Summary(addressBookAfterAdd);
        HashMap<String, Integer> summaryRatingAfterAdd = summaryAfterAdd.getPercentageRatings();

        assertEquals(summaryRating.size(), summaryRatingAfterAdd.size());

        for (String i : summaryRatingAfterAdd.keySet()) {
            Integer afterAdd = summaryRatingAfterAdd.get(i);
            Integer beforeAdd = summaryRating.get(i);
            Integer afterAddAtt = afterAdd - 1;

            if (i.equals(category)) {
                assertEquals(afterAddAtt, beforeAdd);
            } else {
                assertEquals(afterAdd, beforeAdd);
            }
        }
    }


}
