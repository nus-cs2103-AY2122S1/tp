package seedu.address.commons.core;

public class RolesRequirement {
    public static int bartender_req = 0;
    public static int kitchen_req = 0;
    public static int floor_req = 0;

    public void updateBartender_req(int newReq) {
        bartender_req = newReq;
    }

    public void updateKitchen_req(int newReq) {
        kitchen_req = newReq;
    }

    public void updateFloor_req(int newReq) {
        floor_req = newReq;
    }
}
