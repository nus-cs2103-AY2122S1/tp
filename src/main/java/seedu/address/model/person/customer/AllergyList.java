package seedu.address.model.person.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AllergyList {

    public static final String MESSAGE_CONSTRAINTS = "All allergies in the allergy list has to be "
        + "alphabetical";

    public final ArrayList<Allergy> value;

    /**
     * Constructs an empty AllergyList.
     */
    public AllergyList() {
        this.value = new ArrayList<>();
    }

    /**
     * Constructs an AllergyList from a list of strings of allergies.
     *
     * @param allergies The given list of strings.
     */
    public AllergyList(List<String> allergies) {
        this.value = new ArrayList<>(allergies.stream()
                .map(s -> new Allergy(s))
                .collect(Collectors.toList()));
    }

    /**
     * Constructs an AllergyList from an existing arraylist of allergies.
     *
     * @param allergies The given arraylist.
     */
    public AllergyList(ArrayList<Allergy> allergies) {
        this.value = allergies;
    }

    /**
     * Performs an adding of an allergy to an AllergyList.
     *
     * @param allergy The given allergy.
     */
    public void add(Allergy allergy) {
        this.value.add(allergy);
    }

    /**
     * Returns an allergy at a given index.
     *
     * @param index The given index.
     * @return the allergy at the given index.
     */
    public Allergy get(int index) {
        return this.value.get(index);
    }

    /**
     * Returns the size of the Allergylist.
     *
     * @return The size of an Allergylist.
     */
    public int size() {
        return this.value.size();
    }

    @Override
    public String toString() {
        int length = this.value.size();
        if (length == 0) {
            return "";
        } else if (length == 1) {
            return this.value.get(0).toString();
        }
        String temp = "";
        for (int i = 0; i < length - 1; i++) {
            temp += String.format("%s, ", this.value.get(i).toString());
        }
        temp += String.format("%s", this.value.get(this.value.size() - 1).toString());
        return temp;
    }

    /**
     * Performs a deletion of an allergy at a given index.
     *
     * @param index The given index.
     */
    public void delete(int index) {
        this.value.remove(index);
    }

    /**
     * Returns true if a given list contains valid allergies.
     */
    public static boolean isValidAllergyList(List<String> allergies) {
        for (int i = 0; i < allergies.size(); i++) {
            if (!Allergy.isValidAllergy(allergies.get(i))) {
                return false;
            }
        }
        return true;
    }

}
