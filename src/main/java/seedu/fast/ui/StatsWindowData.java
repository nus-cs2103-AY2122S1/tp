package seedu.fast.ui;

import java.util.ArrayList;
import java.util.Collections;

public abstract class StatsWindowData {

    private ArrayList<Integer> counts = new ArrayList<>();

    public int getMax() {
        return Collections.max(counts);
    }

    public int getTotalCount() {
        return counts.stream()
            .mapToInt(Integer::intValue)
            .sum();
    }

    /**
     * Encapsulates the data of the investment plan tags.
     */
    static class InvestmentPlanData extends StatsWindowData {

        private ArrayList<Integer> counts = new ArrayList<>();

        public InvestmentPlanData(int lifeCount, int motorCount, int travelCount, int healthCount,
                           int propertyCount, int investmentCount, int savingsCount) {

            counts.add(lifeCount);
            counts.add(motorCount);
            counts.add(travelCount);
            counts.add(healthCount);
            counts.add(propertyCount);
            counts.add(investmentCount);
            counts.add(savingsCount);
        }

        public int getLifeCount() {
            return counts.get(0);
        }

        public int getMotorCount() {
            return counts.get(1);
        }

        public int getTravelCount() {
            return counts.get(2);
        }

        public int getHealthCount() {
            return counts.get(3);
        }

        public int getPropertyCount() {
            return counts.get(4);
        }

        public int getInvestmentCount() {
            return counts.get(5);
        }

        public int getSavingsCount() {
            return counts.get(6);
        }
    }


    /**
     * Encapsulates the data of the priority tags.
     */
    static class PriorityData extends StatsWindowData {

        private ArrayList<Integer> counts = new ArrayList<>();

        public PriorityData(int highCount, int mediumCount, int lowCount) {

            counts.add(highCount);
            counts.add(mediumCount);
            counts.add(lowCount);

        }

        public int getHighCount() {
            return counts.get(0);
        }

        public int getMediumCount() {
            return counts.get(1);
        }

        public int getLowCount() {
            return counts.get(2);
        }
    }

}
