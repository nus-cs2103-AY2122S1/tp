package seedu.address.model.person.customer;

import java.util.ArrayList;

public class AllergyList {

    private ArrayList<Allergy> allergies;

    /**
     * Constructs an empty TaskList.
     */
    public AllergyList() {
        this.allergies = new ArrayList<>();
    }

    /**
     * Constructs an AllergyList from an existing arraylist of allergies.
     *
     * @param allergies The given arraylist.
     */
    public AllergyList(ArrayList<Allergy> allergies) {
        this.allergies = allergies;
    }

    /**
     * Performs an adding of an allergy to an AllergyList.
     *
     * @param allergy The given allergy.
     */
    public void add(Allergy allergy) {
        this.allergies.add(allergy);
    }

    /**
     * Returns an allergy at a given index.
     *
     * @param index The given index.
     * @return the allergy at the given index.
     */
    public Allergy get(int index) {
        return this.allergies.get(index);
    }

    /**
     * Returns the size of the Allergylist.
     *
     * @return The size of an Allergylist.
     */
    public int size() {
        return this.allergies.size();
    }

    @Override
    public String toString() {
        int length = this.allergies.size();
        if (length == 0) {
            return "";
        } else if (length == 1) {
            return this.allergies.get(0).toString();
        }
        String temp = "";
        for (int i = 0; i < length - 1; i++) {
            temp += String.format("%s, ", this.allergies.get(i).toString());
        }
        temp += String.format("%s", this.allergies.get(this.allergies.size() - 1).toString());
        return temp;
    }

    /**
     * Performs a deletion of an allergy at a given index.
     *
     * @param index The given index.
     */
    public void delete(int index) {
        this.allergies.remove(index);
    }
}
