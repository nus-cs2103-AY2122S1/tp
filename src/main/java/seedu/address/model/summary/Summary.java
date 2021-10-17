package seedu.address.model.summary;

import java.util.HashMap;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.CategoryCode;
import seedu.address.model.person.Person;
import seedu.address.model.person.Review;


/**
 *  This class contains the logic of retrieving data from different components
 *  to display as a summary on application start up.
 */
public class Summary {
    private int numberOfContacts;
    //  private final int percentageRatings;
    private int percentageReviews;
    private HashMap<String, Integer> numberCategory;

    private List<Person> personList;

    /**
     * Constructor to create a summary of the addressBook.
     * @param addressBook addressBook to summarise.
     */
    public Summary(AddressBook addressBook) {
        this.personList = addressBook.getPersonList();
        numberOfContacts = setNumberOfContacts();
        percentageReviews = setPercentageReviews();
        numberCategory = setNumberCategory();
    }

    /**
     * Sets the total number of contacts in the addressBook.
     * @return size of AddressBook.
     */
    private int setNumberOfContacts() {
        return personList.size();
    }

    private int setPercentageRatings(Model model) {
        List<Person> personList = model.getAddressBook().getPersonList();

        return 0;
    }

    /**
     * Sets the total percentage of Reviews in the addressBook.
     * @return total percentage of reviews of all contacts.
     */
    private int setPercentageReviews() {
        if (numberOfContacts <= 0) {
            return 100;
        }
        int count = 0;
        for (Person person : personList) {
            Review review = person.getReview();
            if (!review.isEmptyReview()) {
                count++;
            }
        }
        return 100 * count / numberOfContacts;
    }

    /**
     * Sets the total number of contacts in each category.
     * @return HashMap of total number of contacts in each category.
     */
    private HashMap<String, Integer> setNumberCategory() {
        List<String> categoryValues = CategoryCode.CATEGORY_VALUES;
        HashMap<String, Integer> count = new HashMap<>();

        for (String categoryValue : categoryValues) {
            count.put(categoryValue, 0);
        }

        for (Person person : personList) {
            String categoryString = person.getCategoryCode().toString().toLowerCase();
            int value = count.get(categoryString);
            int newValue = value + 1;
            count.replace(categoryString, newValue);
        }

        return count;
    }

    public int getNumberOfContacts() {
        return numberOfContacts;
    }

    public int getPercentageReviews() {
        return percentageReviews;
    }

    public HashMap<String, Integer> getNumberCategory() {
        return numberCategory;
    }

}
