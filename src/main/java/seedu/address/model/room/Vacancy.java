package seedu.address.model.room;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public abstract class Vacancy {

    public static final Vacancy VACANT = new Vacant();
    public static final Vacancy OCCUPIED = new Occupied();

    /**
     * Returns a {@code Vacancy} of either vacant or occupied.
     */
    public static Vacancy of(boolean isVacant) {
        requireAllNonNull(isVacant);
        return isVacant
                ? VACANT
                : OCCUPIED;
    }

    public abstract boolean isVacant();

    private static class Vacant extends Vacancy {

        @Override
        public boolean isVacant() {
            return true;
        }

        @Override
        public String toString() {
            return "Vacant";
        }

        @Override
        public boolean equals(Object other) {
            return other instanceof Vacant;
        }
    }

    private static class Occupied extends Vacancy {

        @Override
        public boolean isVacant() {
            return false;
        }

        @Override
        public String toString() {
            return "Occupied";
        }

        @Override
        public boolean equals(Object other) {
            return other instanceof Occupied;
        }
    }

}
