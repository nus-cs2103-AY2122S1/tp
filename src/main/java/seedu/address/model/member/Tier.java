package seedu.address.model.member;

/**
 * Represents a Tier in the ezFoodie.
 */
public enum Tier {

    BRONZE("Bronze", 0),
    SILVER("Silver", 100),
    GOLD("Gold", 500),
    PLATINUM("Platinum", 1000);

    private final String key;
    private final Integer value;

    /**
     * Constructs a {@code Tier} with input {@code key} and {@code  value}.
     *
     * @param key the different tiers for Platinum, Gold, Silver or Bronze.
     * @param value the value of tier.
     */
    Tier(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Returns key of tier.
     */
    public String getKey() {
        return key;
    }

    /**
     * Returns all possible key of tier.
     */
    public static String[] getAllKeys() {
        return new String[]{BRONZE.key, SILVER.key, GOLD.key, PLATINUM.key};
    }

    /**
     * Returns value of tier.
     */
    public int getValue() {
        return this.value;
    }

    public static String getTierByCredit(int credit) {
        if (credit >= PLATINUM.getValue()) {
            return PLATINUM.key;
        }
        if (credit >= GOLD.getValue()) {
            return GOLD.key;
        }
        if (credit >= SILVER.getValue()) {
            return SILVER.key;
        }
        return BRONZE.key;
    }
}
