package seedu.address.model.person.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SrList {

    public static final String MESSAGE_CONSTRAINTS = "All special requests in the special requests list has"
            + " to be alphanumeric";

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
     * Constructs an SrList from a list of strings of special requests.
     *
     * @param specialRequests The given list of strings.
     */
    public SrList(List<String> specialRequests) {
        this.specialRequests = new ArrayList<>(specialRequests.stream()
                .map(s -> new SpecialRequest(s))
                .collect(Collectors.toList()));
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
        int length = this.specialRequests.size();
        if (length == 0) {
            return "";
        } else if (length == 1) {
            return this.specialRequests.get(0).toString();
        }
        String temp = "";
        for (int i = 0; i < length - 1; i++) {
            temp += String.format("%s, ", this.specialRequests.get(i).toString());
        }
        temp += String.format("%s", this.specialRequests.get(this.specialRequests.size() - 1).toString());
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

    /**
     * Returns true if a given list contains valid special requests.
     */
    public static boolean isValidSrList(List<String> specialRequests) {
        for (int i = 0; i < specialRequests.size(); i++) {
            if (!SpecialRequest.isValidSpecialRequest(specialRequests.get(i))) {
                return false;
            }
        }
        return true;
    }
}
