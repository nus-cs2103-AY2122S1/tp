package seedu.fast.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.fast.commons.core.LogsCenter;
import seedu.fast.model.Fast;
import seedu.fast.model.ReadOnlyFast;
import seedu.fast.model.tag.InvestmentPlanTag;
import seedu.fast.model.tag.PriorityTag;

/**
 * Controller for a stats page
 */
public class StatsWindow extends UiPart<Stage> {

    public static final String MESSAGE_USAGE = "Opens a new window with built-in statistics to provide "
        + "you with an overview of your data.\n\n"
        + "To view the statistics, simply click the \"Stats\" menu item on the top bar or press `F2`.\n"
        + "Currently, FAST supports these statistics:\n"
        + "* Priority Tag Chart\n"
        + "* Insurance Plan Chart";
    private static final Logger logger = LogsCenter.getLogger(StatsWindow.class);
    private static final String FXML = "StatsWindow.fxml";
    private static final String PRIORITY_CHART_MESSAGE_INTRO = "You currently have a total of: ";
    private static final String HIGH_PRIORITY_MESSAGE = "Good job! You have a large proportion of high value clients!\n"
        + "However, this means that you need to be sure to put in extra effort to maintain it!";
    private static final String MEDIUM_PRIORITY_MESSAGE = "Nice! You have a sizeable portion of medium value clients!\n"
        + "This is a great base for your portfolio!";
    private static final String LOW_PRIORITY_MESSAGE = "Keep it up! Focus on developing your client base to boost \n"
        + "your client portfolio!";


    private Fast fast;

    @FXML
    private PieChart investmentPlanPieChart;

    @FXML
    private PieChart priorityPieChart;

    @FXML
    private Label investmentPlanPieChartDesc;

    @FXML
    private Label priorityPieChartDesc;

    /**
     * Creates a new StatsWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public StatsWindow(Stage root, ReadOnlyFast fast) {
        super(FXML, root);
        // Since StatsWindow receives a Fast instance, it is safe to typecast it
        this.fast = (Fast) fast;
        populatePriorityPieChart();
        addCountsToPieChart(priorityPieChart);
        populateInvestmentPlanPieChart();
        addCountsToPieChart(investmentPlanPieChart);
        priorityPieChart.setLegendVisible(false);
        investmentPlanPieChart.setLegendVisible(false);
    }



    /**
     * Creates a new HelpWindow.
     */
    public StatsWindow(ReadOnlyFast fast) {
        this(new Stage(), fast);
    }


    /**
     * Shows the stats window.
     *
     * @throws IllegalStateException <ul>
     *                               <li>
     *                               if this method is called on a thread other than the JavaFX Application Thread.
     *                               </li>
     *                               <li>
     *                               if this method is called during animation or layout processing.
     *                               </li>
     *                               <li>
     *                               if this method is called on the primary stage.
     *                               </li>
     *                               <li>
     *                               if {@code dialogStage} is already showing.
     *                               </li>
     *                               </ul>
     */
    public void show() {
        logger.fine("Showing stats page of your clients.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the stats window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the stats window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Adds the data from Fast into the PieChart.
     */
    public void populatePriorityPieChart() {
        priorityPieChart.getData().clear();  //Ensure that PieChart is blank.

        // Gets the respective counts
        int highPriorityCount = this.fast.getHighPriorityCount();
        int mediumPriorityCount = this.fast.getMediumPriorityCount();
        int lowPriorityCount = this.fast.getLowPriorityCount();

        // Assert statements to verify that the input data is correct
        assert highPriorityCount >= 0 : "highPriorityCount must be positive";
        assert mediumPriorityCount >= 0 : "mediumPriorityCount must be positive";
        assert lowPriorityCount >= 0 : "lowPriorityCount must be positive";

        addPriorityPieChartAnalysis(highPriorityCount, mediumPriorityCount, lowPriorityCount);

        addPieChartData(PriorityTag.HighPriority.NAME, highPriorityCount, this.priorityPieChart);
        addPieChartData(PriorityTag.MediumPriority.NAME, mediumPriorityCount, this.priorityPieChart);
        addPieChartData(PriorityTag.LowPriority.NAME, lowPriorityCount, this.priorityPieChart);
    }

    /**
     * Adds the data from Fast into the PieChart.
     */
    public void populateInvestmentPlanPieChart() {
        investmentPlanPieChart.getData().clear(); //Ensure that PieChart is blank.
        InvestmentPlanData data = getInvestmentPlanData();
        addInvestmentPlanPieChartAnalysis(data);
        addInvestmentPlanPieChartData(data);

    }

    private void addInvestmentPlanPieChartData(InvestmentPlanData data) {
        addPieChartData(InvestmentPlanTag.LifeInsurance.NAME, data.getLifeCount(), this.investmentPlanPieChart);
        addPieChartData(InvestmentPlanTag.MotorInsurance.NAME, data.getMotorCount(), this.investmentPlanPieChart);
        addPieChartData(InvestmentPlanTag.TravelInsurance.NAME, data.getTravelCount(), this.investmentPlanPieChart);
        addPieChartData(InvestmentPlanTag.HealthInsurance.NAME, data.getHealthCount(), this.investmentPlanPieChart);
        addPieChartData(InvestmentPlanTag.PropertyInsurance.NAME, data.getPropertyCount(), this.investmentPlanPieChart);
        addPieChartData(InvestmentPlanTag.Investment.NAME, data.getInvestmentCount(), this.investmentPlanPieChart);
        addPieChartData(InvestmentPlanTag.Savings.NAME, data.getSavingsCount(), this.investmentPlanPieChart);
    }

    /**
     * Adds the counts to the labels of the PieChart.
     * Adapted from: https://stackoverflow.com/questions/35479375/display-additional-values-in-pie-chart
     * Credit: jewelsea
     */
    public void addCountsToPieChart(PieChart pieChart) {
        pieChart.getData().forEach(data ->
            data.nameProperty().bind(
                Bindings.concat(
                    data.getName(), ":\n", (int) data.getPieValue(), (data.getPieValue() == 1)
                        ? " client" : " clients" // check plural or singular
                )
            ));
    }

    /**
     * Adds the data elements {@code name} and {@code count} to {@code pc}
     */
    public void addPieChartData(String name, int count, PieChart pc) {
        if (count > 0) {
            PieChart.Data data = new PieChart.Data(name, count);
            pc.getData().add(data);
        }
    }

    /**
     * Adds a brief analysis of the client base's Priority Tags to the PieChart.
     */
    public void addPriorityPieChartAnalysis(int highCount, int medCount, int lowCount) {
        int totalCount = highCount + medCount + lowCount;
        String totalClientCount = totalCount + " Clients!";

        // Gets the max out of 3 values
        int maxCount = Math.max(highCount, Math.max(medCount, lowCount));

        // Prioritise higher Priorities first,
        // e.g. if the counts are equal for all, HIGH_PRIORITY_MESSAGE will be used
        if (highCount == maxCount) {
            setPriorityPieChartLabel(totalClientCount, HIGH_PRIORITY_MESSAGE);
            return; //return to prevent fallthrough
        }
        if (medCount == maxCount) {
            setPriorityPieChartLabel(totalClientCount, MEDIUM_PRIORITY_MESSAGE);
            return; //return to prevent fallthrough
        }
        if (lowCount == maxCount) {
            setPriorityPieChartLabel(totalClientCount, LOW_PRIORITY_MESSAGE);
            return;
        }
        // The code should NOT reach here
        assert false;
    }

    /**
     * Adds a brief analysis of the client base's Investment Plan Tags to the PieChart.
     */
    public void addInvestmentPlanPieChartAnalysis(InvestmentPlanData investmentPlanData) {
        int totalCount = investmentPlanData.getTotalCount();

        String totalClientCount = totalCount + " Clients!";

        // Gets the max out of the values
        int maxCount = investmentPlanData.getMax();


    }

    /**
     * Sets the text of the analysis.
     */
    private void setPriorityPieChartLabel(String totalClientCount, String text) {
        priorityPieChartDesc.setText(PRIORITY_CHART_MESSAGE_INTRO + totalClientCount + "\n\n" + text);
    }

    /**
     * Focuses on the stats window.
     */
    public void focus() {
        assert getRoot() != null : "StatsWindow is not initialised";
        populatePriorityPieChart();
        addCountsToPieChart(priorityPieChart);
        populateInvestmentPlanPieChart();
        addCountsToPieChart(investmentPlanPieChart);
        getRoot().requestFocus();
    }

    /**
     * Gets the investment plan tag data from FAST.
     */
    public InvestmentPlanData getInvestmentPlanData() {
        // Gets the respective counts
        int lifeInsuranceCount = this.fast.getLifeInsuranceCount();
        int motorInsuranceCount = this.fast.getMotorInsuranceCount();
        int travelInsuranceCount = this.fast.getTravelInsuranceCount();
        int healthInsuranceCount = this.fast.getHealthInsuranceCount();
        int propertyInsuranceCount = this.fast.getPropertyInsuranceCount();
        int investmentCount = this.fast.getInvestmentCount();
        int savingsCount = this.fast.getSavingsCount();

        // Assert statements to verify that the input data is correct
        assert lifeInsuranceCount >= 0 : "lifeInsuranceCount must be positive";
        assert motorInsuranceCount >= 0 : "motorInsuranceCount must be positive";
        assert travelInsuranceCount >= 0 : "travelInsuranceCount must be positive";
        assert healthInsuranceCount >= 0 : "healthInsuranceCount must be positive";
        assert propertyInsuranceCount >= 0 : "propertyInsuranceCount must be positive";
        assert investmentCount >= 0 : "investmentCount must be positive";
        assert savingsCount >= 0 : "savingsCount must be positive";

        return new InvestmentPlanData(lifeInsuranceCount, motorInsuranceCount,
            travelInsuranceCount, healthInsuranceCount, propertyInsuranceCount, investmentCount, savingsCount);
    }

    /**
     * Encapsulates the data of the investment plan tags.
     */
    private class InvestmentPlanData {

        private ArrayList<Integer> counts = new ArrayList<>();

        private InvestmentPlanData(int lifeCount, int motorCount, int travelCount, int healthCount,
                                   int propertyCount, int investmentCount, int savingsCount) {

            counts.add(lifeCount);
            counts.add(motorCount);
            counts.add(travelCount);
            counts.add(healthCount);
            counts.add(propertyCount);
            counts.add(investmentCount);
            counts.add(savingsCount);
        }

        private int getMax() {
            return Collections.max(counts);
        }

        private int getTotalCount() {
            return counts.stream()
                .mapToInt(Integer::intValue)
                .sum();
        }

        private int getLifeCount() {
            return counts.get(0);
        }

        private int getMotorCount() {
            return counts.get(1);
        }

        private int getTravelCount() {
            return counts.get(2);
        }

        private int getHealthCount() {
            return counts.get(3);
        }

        private int getPropertyCount() {
            return counts.get(4);
        }

        private int getInvestmentCount() {
            return counts.get(5);
        }

        private int getSavingsCount() {
            return counts.get(6);
        }


    }


}
