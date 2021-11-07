package seedu.address.model.summary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.CategoryCode;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Rating;
import seedu.address.testutil.ContactBuilder;



public class SummaryTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private String totalContacts = "Total Number of Contacts: ";


    @Test
    public void execute_getNumberOfContactsGui_success() {
        ReadOnlyAddressBook addressBook = model.getAddressBook();
        int size = addressBook.getContactList().size();

        Summary summary = new Summary(getTypicalAddressBook());
        String summarySize = summary.getNumberOfContactsGui();

        assertEquals(totalContacts + size, summarySize);
    }


    @Test
    public void execute_getPercentageCategoryGui_successAfterUpdate() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        ReadOnlyAddressBook addressBook = model.getAddressBook();
        Summary summary = new Summary(addressBook);
        ObservableList<PieChart.Data> summaryCategory = summary.getPercentageCategoryGui();

        String category = ContactBuilder.DEFAULT_CATEGORY_CODE;
        model.addContact(new ContactBuilder().withName("Ang Mo Kio Village").build());


        ReadOnlyAddressBook addressBookAfterAdd = model.getAddressBook();
        Summary summaryAfterAdd = new Summary(addressBookAfterAdd);
        ObservableList<PieChart.Data> summaryCategoryAfterAdd = summaryAfterAdd.getPercentageCategoryGui();

        assertEquals(summaryCategory.size(), summaryCategoryAfterAdd.size());

        int count = 0;
        for (PieChart.Data i : summaryCategoryAfterAdd) {
            double afterAdd = summaryCategoryAfterAdd.get(count).getPieValue();
            double beforeAdd = summaryCategory.get(count).getPieValue();
            double afterAddAtt = afterAdd - 1;
            if (summaryCategoryAfterAdd.get(count).getName().equalsIgnoreCase(category)) {
                assertEquals(afterAddAtt, beforeAdd);
            } else {
                assertEquals(afterAdd, beforeAdd);
            }
            count++;
        }
    }

    @Test
    public void execute_getPercentageRatingGui_successAfterUpdate() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        ReadOnlyAddressBook addressBook = model.getAddressBook();
        Summary summary = new Summary(addressBook);
        ObservableList<PieChart.Data> summaryRating = summary.getPercentageRatingsGui();

        String category = ContactBuilder.DEFAULT_RATING;
        model.addContact(new ContactBuilder().withName("Ang Mo Kio Village").build());


        ReadOnlyAddressBook addressBookAfterAdd = model.getAddressBook();
        Summary summaryAfterAdd = new Summary(addressBookAfterAdd);
        ObservableList<PieChart.Data> summaryRatingAfterAdd = summaryAfterAdd.getPercentageRatingsGui();

        assertEquals(summaryRating.size(), summaryRatingAfterAdd.size());

        int count = 0;
        for (PieChart.Data i : summaryRatingAfterAdd) {
            double afterAdd = summaryRatingAfterAdd.get(count).getPieValue();
            double beforeAdd = summaryRating.get(count).getPieValue();
            double afterAddAtt = afterAdd - 1;
            if (summaryRatingAfterAdd.get(count).getName().substring(0, 1).equalsIgnoreCase(category)) {
                assertEquals(afterAddAtt, beforeAdd);
            } else {
                assertEquals(afterAdd, beforeAdd);
            }
            count++;
        }
    }

    @Test
    public void execute_summary_successAfterEdit() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        ReadOnlyAddressBook addressBook = model.getAddressBook();
        Summary summary = new Summary(addressBook);
        ObservableList<PieChart.Data> summaryRating = summary.getPercentageRatingsGui();
        ObservableList<PieChart.Data> summaryCategory = summary.getPercentageCategoryGui();

        String rating = ContactBuilder.DEFAULT_RATING;
        String category = ContactBuilder.DEFAULT_CATEGORY_CODE;
        model.addContact(new ContactBuilder().withName("Ang Mo Kio Village").build());


        ReadOnlyAddressBook addressBookAfterAdd = model.getAddressBook();
        Summary summaryAfterAdd = new Summary(addressBookAfterAdd);
        ObservableList<PieChart.Data> summaryRatingAfterAdd = summaryAfterAdd.getPercentageRatingsGui();
        ObservableList<PieChart.Data> summaryCategoryAfterAdd = summaryAfterAdd.getPercentageCategoryGui();

        assertEquals(summaryRating.size(), summaryRatingAfterAdd.size());

        int count = 0;
        for (PieChart.Data i : summaryRatingAfterAdd) {
            double afterAdd = summaryRatingAfterAdd.get(count).getPieValue();
            double beforeAdd = summaryRating.get(count).getPieValue();
            double afterAddAtt = afterAdd - 1;
            if (summaryRatingAfterAdd.get(count).getName().substring(0, 1).equalsIgnoreCase(rating)) {
                assertEquals(afterAddAtt, beforeAdd);
            } else {
                assertEquals(afterAdd, beforeAdd);
            }
            count++;
        }

        count = 0;
        for (PieChart.Data i : summaryCategoryAfterAdd) {
            double afterAdd = summaryCategoryAfterAdd.get(count).getPieValue();
            double beforeAdd = summaryCategory.get(count).getPieValue();
            double afterAddAtt = afterAdd - 1;
            if (summaryCategoryAfterAdd.get(count).getName().equalsIgnoreCase(category)) {
                assertEquals(afterAddAtt, beforeAdd);
            } else {
                assertEquals(afterAdd, beforeAdd);
            }
            count++;
        }

        int originalSize = addressBook.getContactList().size();
        int updatedSize = addressBookAfterAdd.getContactList().size();

        assertEquals(originalSize, updatedSize);

    }

    @Test
    public void execute_summary_successAfterClear() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        ReadOnlyAddressBook addressBook = model.getAddressBook();
        Summary summary = new Summary(addressBook);
        ObservableList<PieChart.Data> summaryRating = summary.getPercentageRatingsGui();
        ObservableList<PieChart.Data> summaryCategory = summary.getPercentageCategoryGui();

        String rating = ContactBuilder.DEFAULT_RATING;
        String category = ContactBuilder.DEFAULT_CATEGORY_CODE;
        model.addContact(new ContactBuilder().withName("Ang Mo Kio Village").build());

        model.setAddressBook(new AddressBook());
        ReadOnlyAddressBook addressBookAfterClear = model.getAddressBook();
        Summary summaryAfterClear = new Summary(addressBookAfterClear);
        ObservableList<PieChart.Data> summaryRatingAfterClear = summaryAfterClear.getPercentageRatingsGui();
        ObservableList<PieChart.Data> summaryCategoryAfterClear = summaryAfterClear.getPercentageCategoryGui();

        assertEquals(0, summaryRatingAfterClear.size());

        int count = 0;
        for (PieChart.Data i : summaryRatingAfterClear) {
            double afterAdd = summaryRatingAfterClear.get(count).getPieValue();
            double beforeAdd = summaryRating.get(count).getPieValue();
            double afterAddAtt = afterAdd - 1;
            if (summaryRatingAfterClear.get(count).getName().substring(0, 1).equalsIgnoreCase(rating)) {
                assertEquals(afterAddAtt, beforeAdd);
            } else {
                assertEquals(afterAdd, beforeAdd);
            }
            count++;
        }

        count = 0;
        for (PieChart.Data i : summaryCategoryAfterClear) {
            double afterAdd = summaryCategoryAfterClear.get(count).getPieValue();
            double beforeAdd = summaryCategory.get(count).getPieValue();
            double afterAddAtt = afterAdd - 1;
            if (summaryCategoryAfterClear.get(count).getName().equalsIgnoreCase(category)) {
                assertEquals(afterAddAtt, beforeAdd);
            } else {
                assertEquals(afterAdd, beforeAdd);
            }
            count++;
        }

        int originalSize = addressBook.getContactList().size();
        int updatedSize = addressBookAfterClear.getContactList().size();

        assertEquals(originalSize, updatedSize);

    }

    @Test
    public void execute_summary_successAfterUndo() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        ReadOnlyAddressBook addressBook = model.getAddressBook();
        Summary summary = new Summary(addressBook);
        ObservableList<PieChart.Data> summaryRating = summary.getPercentageRatingsGui();
        ObservableList<PieChart.Data> summaryCategory = summary.getPercentageCategoryGui();

        String rating = getTypicalAddressBook().getContactList().get(0).getRating().toString();
        String category = getTypicalAddressBook().getContactList().get(0).getCategoryCode().toString();
        model.deleteContact(model.getFilteredContactList().get(0));
        model.undo();

        ReadOnlyAddressBook addressBookAfterDelete = model.getAddressBook();
        Summary summaryAfterDelete = new Summary(addressBookAfterDelete);
        ObservableList<PieChart.Data> summaryRatingAfterDelete = summaryAfterDelete.getPercentageRatingsGui();
        ObservableList<PieChart.Data> summaryCategoryAfterDelete = summaryAfterDelete.getPercentageCategoryGui();


        int count = 0;
        for (PieChart.Data i : summaryRatingAfterDelete) {
            double afterAdd = summaryRatingAfterDelete.get(count).getPieValue();
            double beforeAdd = summaryRating.get(count).getPieValue();
            double afterAddAtt = afterAdd;
            if (summaryRatingAfterDelete.get(count).getName().substring(0, 1).equalsIgnoreCase(rating)) {
                assertEquals(afterAddAtt, beforeAdd);
            } else {
                assertEquals(afterAdd, beforeAdd);
            }
            count++;
        }

        count = 0;
        for (PieChart.Data i : summaryCategoryAfterDelete) {
            double afterAdd = summaryCategoryAfterDelete.get(count).getPieValue();
            double beforeAdd = summaryCategory.get(count).getPieValue();
            double afterAddAtt = afterAdd;
            if (summaryCategoryAfterDelete.get(count).getName().equalsIgnoreCase(category)) {
                assertEquals(afterAddAtt, beforeAdd);
            } else {
                assertEquals(afterAdd, beforeAdd);
            }
            count++;
        }

        int originalSize = addressBook.getContactList().size();
        int updatedSize = addressBookAfterDelete.getContactList().size();

        assertEquals(originalSize, updatedSize);

    }

    @Test
    public void execute_summary_successAfterRedo() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        ReadOnlyAddressBook addressBook = model.getAddressBook();
        Summary summary = new Summary(addressBook);
        ObservableList<PieChart.Data> summaryRating = summary.getPercentageRatingsGui();
        ObservableList<PieChart.Data> summaryCategory = summary.getPercentageCategoryGui();

        String rating = ContactBuilder.DEFAULT_RATING;
        String category = ContactBuilder.DEFAULT_CATEGORY_CODE;
        model.addContact(new ContactBuilder().withName("Ang Mo Kio Village").build());
        model.undo();
        model.redo();

        ReadOnlyAddressBook addressBookAfterAdd = model.getAddressBook();
        Summary summaryAfterAdd = new Summary(addressBookAfterAdd);
        ObservableList<PieChart.Data> summaryRatingAfterAdd = summaryAfterAdd.getPercentageRatingsGui();
        ObservableList<PieChart.Data> summaryCategoryAfterAdd = summaryAfterAdd.getPercentageCategoryGui();

        assertEquals(summaryRating.size(), summaryRatingAfterAdd.size());

        int count = 0;
        for (PieChart.Data i : summaryRatingAfterAdd) {
            double afterAdd = summaryRatingAfterAdd.get(count).getPieValue();
            double beforeAdd = summaryRating.get(count).getPieValue();
            double afterAddAtt = afterAdd - 1;
            if (summaryRatingAfterAdd.get(count).getName().substring(0, 1).equalsIgnoreCase(rating)) {
                assertEquals(afterAddAtt, beforeAdd);
            } else {
                assertEquals(afterAdd, beforeAdd);
            }
            count++;
        }

        count = 0;
        for (PieChart.Data i : summaryCategoryAfterAdd) {
            double afterAdd = summaryCategoryAfterAdd.get(count).getPieValue();
            double beforeAdd = summaryCategory.get(count).getPieValue();
            double afterAddAtt = afterAdd - 1;
            if (summaryCategoryAfterAdd.get(count).getName().equalsIgnoreCase(category)) {
                assertEquals(afterAddAtt, beforeAdd);
            } else {
                assertEquals(afterAdd, beforeAdd);
            }
            count++;
        }

        int originalSize = addressBook.getContactList().size();
        int updatedSize = addressBookAfterAdd.getContactList().size();

        assertEquals(originalSize, updatedSize);

    }


    @Test
    public void equals() {
        assertEquals(new Summary(model.getAddressBook()), model.getSummary());
    }


}
