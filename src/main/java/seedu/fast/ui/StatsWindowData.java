package seedu.fast.ui;

import java.util.Collections;
import java.util.HashMap;

import seedu.fast.model.tag.InvestmentPlanTag;
import seedu.fast.model.tag.PriorityTag;

public abstract class StatsWindowData {

    private HashMap<String, Integer> counts;

    public abstract String getMaxName();

    public abstract int getMaxValue();

    public abstract int getTotalCount();

    /**
     * Encapsulates the data of the investment plan tags.
     */
    public static class InvestmentPlanData extends StatsWindowData {

        private HashMap<String, Integer> counts = new HashMap<>();

        /**
         * Constructs a new InvestmentPlanData.
         */
        public InvestmentPlanData(int lifeCount, int motorCount, int travelCount, int healthCount,
                                  int propertyCount, int investmentCount, int savingsCount) {

            counts.put(InvestmentPlanTag.LifeInsurance.NAME, lifeCount);
            counts.put(InvestmentPlanTag.MotorInsurance.NAME, motorCount);
            counts.put(InvestmentPlanTag.TravelInsurance.NAME, travelCount);
            counts.put(InvestmentPlanTag.HealthInsurance.NAME, healthCount);
            counts.put(InvestmentPlanTag.PropertyInsurance.NAME, propertyCount);
            counts.put(InvestmentPlanTag.Investment.NAME, investmentCount);
            counts.put(InvestmentPlanTag.Savings.NAME, savingsCount);

        }

        // For tie-breakers, the max name is not fixed
        public String getMaxName() {
            return Collections.max(counts.entrySet(), HashMap.Entry.comparingByValue()).getKey();
        }

        public int getMaxValue() {
            return Collections.max(counts.entrySet(), HashMap.Entry.comparingByValue()).getValue();
        }

        public int getTotalCount() {
            int sum = 0;
            for (int i : counts.values()) {
                sum += i;
            }
            return sum;
        }

        public int getLifeCount() {
            return counts.get(InvestmentPlanTag.LifeInsurance.NAME);
        }

        public int getMotorCount() {
            return counts.get(InvestmentPlanTag.MotorInsurance.NAME);
        }

        public int getTravelCount() {
            return counts.get(InvestmentPlanTag.TravelInsurance.NAME);
        }

        public int getHealthCount() {
            return counts.get(InvestmentPlanTag.HealthInsurance.NAME);
        }

        public int getPropertyCount() {
            return counts.get(InvestmentPlanTag.PropertyInsurance.NAME);
        }

        public int getInvestmentCount() {
            return counts.get(InvestmentPlanTag.Investment.NAME);
        }

        public int getSavingsCount() {
            return counts.get(InvestmentPlanTag.Savings.NAME);
        }
    }


    /**
     * Encapsulates the data of the priority tags.
     */
    public static class PriorityData extends StatsWindowData {

        private HashMap<String, Integer> counts = new HashMap<>();

        /**
         * Constructs a new PriorityData.
         */
        public PriorityData(int highCount, int mediumCount, int lowCount) {

            counts.put(PriorityTag.HighPriority.NAME, highCount);
            counts.put(PriorityTag.MediumPriority.NAME, mediumCount);
            counts.put(PriorityTag.LowPriority.NAME, lowCount);
        }

        // Priorities High > Medium > Low
        public String getMaxName() {
            int maxValue = this.getMaxValue();
            if (this.getHighCount() == maxValue) {
                return PriorityTag.HighPriority.NAME;
            }
            if (this.getMediumCount() == maxValue) {
                return PriorityTag.MediumPriority.NAME;
            }
            if (this.getLowCount() == maxValue) {
                return PriorityTag.LowPriority.NAME;
            } else {
                return "";
            }
        }

        public int getMaxValue() {
            return Collections.max(counts.entrySet(), HashMap.Entry.comparingByValue()).getValue();
        }

        public int getTotalCount() {
            int sum = 0;
            for (int i : counts.values()) {
                sum += i;
            }
            return sum;
        }

        public int getHighCount() {
            return counts.get(PriorityTag.HighPriority.NAME);
        }

        public int getMediumCount() {
            return counts.get(PriorityTag.MediumPriority.NAME);
        }

        public int getLowCount() {
            return counts.get(PriorityTag.LowPriority.NAME);
        }
    }

}
