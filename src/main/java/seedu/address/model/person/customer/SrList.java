package seedu.address.model.person.customer;

import java.util.ArrayList;

public class SrList {

    private ArrayList<SpecialRequest> specialRequests;

    /**
     * Constructs an empty SrList.
     */
    public SrList() {
        this.specialRequests = new ArrayList<>();
    }

    /**
     * Constructs an SrList from an existing arraylist of special requests.
     *
     * @param specialRequests The given arraylist.
     */
    public SrList(ArrayList<SpecialRequest> specialRequests) {
        this.specialRequests = specialRequests;
    }

    /**
     * Performs an adding of a special request to an SrList.
     *
     * @param specialRequest The given specialRequest.
     */
    public void add(SpecialRequest specialRequest) {
        this.specialRequests.add(specialRequest);
    }

    /**
     * Returns a special request at a given index.
     *
     * @param index The given index.
     * @return the special request at the given index.
     */
    public SpecialRequest get(int index) {
        return this.specialRequests.get(index);
    }

    /**
     * Returns the size of the Srlist.
     *
     * @return The size of an Srlist.
     */
    public int size() {
        return this.specialRequests.size();
    }

    @Override
    public String toString() {
        String temp = "";
        for (int i = 0; i < this.specialRequests.size(); i++) {
            temp += String.format("%s. %s\n", i + 1, this.specialRequests.get(i).toString());
        }
        return temp;
    }

    /**
     * Performs a deletion of a special request at a given index.
     *
     * @param index The given index.
     */
    public void delete(int index) {
        this.specialRequests.remove(index);
    }
}
