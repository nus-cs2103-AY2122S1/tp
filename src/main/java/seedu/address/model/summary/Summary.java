package seedu.address.model.summary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.contact.Contact;


/**
 *  This class contains the logic of retrieving data from different components
 *  to display as a summary on application start up.
 */
public class Summary {
    private int numberOfContacts;
    private final HashMap<String, Integer> percentageRatings;
    private HashMap<String, Integer> percentageCategory;

    private List<Contact> contactList;


    /**
     * Constructor to create a summary of the addressBook.
     * @param addressBook addressBook to summarise.
     */
    public Summary(ReadOnlyAddressBook addressBook) {
        this.contactList = addressBook.getContactList();
        numberOfContacts = setNumberOfContacts();
        percentageCategory = setNumberCategory();
        percentageRatings = setPercentageRatings();
    }

    /**
     * Sets the total number of contacts in the addressBook.
     * @return size of AddressBook.
     */
    private int setNumberOfContacts() {
        return contactList.size();
    }

    /**
     * Sets the total number of Ratings in each category (0-5 stars).
     * @return HashMap of total number of contacts in each category.
     */
    private HashMap<String, Integer> setPercentageRatings() {
        HashMap<String, Integer> count = new HashMap<>();
        for (Contact contact : contactList) {
            String rating = contact.getRating().toString();
            if (count.containsKey(rating)) {
                int value = count.get(rating);
                int newValue = value + 1;
                count.replace(rating, newValue);
            } else {
                count.put(rating, 1);
            }
        }

        return count;
    }


    /**
     * Sets the total number of contacts in each category.
     * @return HashMap of total number of contacts in each category.
     */
    private HashMap<String, Integer> setNumberCategory() {
        HashMap<String, Integer> count = new HashMap<>();

        for (Contact contact : contactList) {
            String categoryString = contact.getCategoryCode().toString().toLowerCase();
            if (count.containsKey(categoryString)) {
                int value = count.get(categoryString);
                int newValue = value + 1;
                count.replace(categoryString, newValue);
            } else {
                count.put(categoryString, 1);
            }

        }

        return count;
    }

    public int getNumberOfContacts() {
        return numberOfContacts;
    }

    public HashMap<String, Integer> getPercentageCategory() {
        return percentageCategory;
    }

    public HashMap<String, Integer> getPercentageRatings() {
        return percentageRatings;
    }

    //=========== GUI ==================================================================================

    public String getNumberOfContactsGui() {
        String totalContacts = "Total Number of Contacts: ";
        return totalContacts + numberOfContacts;
    }

    public ObservableList<PieChart.Data> getPercentageCategoryGui() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for (Map.Entry<String, Integer> entry : percentageCategory.entrySet()) {
            String key = entry.getKey().toUpperCase();
            Integer value = entry.getValue();
            pieChartData.add(new PieChart.Data(key, value));
        }
        return pieChartData;
    }

    public ObservableList<PieChart.Data> getPercentageRatingsGui() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for (Map.Entry<String, Integer> entry : percentageRatings.entrySet()) {
            String key = entry.getKey() + "\u2B50";
            Integer value = entry.getValue();
            pieChartData.add(new PieChart.Data(key, value));
        }
        return pieChartData;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Summary
                && getNumberOfContacts() == ((Summary) other).getNumberOfContacts()
                && getPercentageCategory().equals(((Summary) other).getPercentageCategory())
                && getPercentageRatings().equals(((Summary) other).getPercentageRatings()));
    }

}
