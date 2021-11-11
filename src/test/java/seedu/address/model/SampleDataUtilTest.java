package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.application.Application;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.testutil.ApplicationBuilder;

public class SampleDataUtilTest {

    private Application shopee = new ApplicationBuilder().withCompany("Shopee").withPosition("software engineer")
            .withDeadline("2021-10-23").withCompletion("Completed").withStatus("Accepted")
            .withInterviewDateAndTime("2021-10-31 1600").build();
    private Application google = new ApplicationBuilder().withCompany("Google").withPosition("frontend developer")
            .withDeadline("2021-12-14").withPriority("High").withRequirements("resume", "portfolio").build();
    private Application huawei = new ApplicationBuilder().withCompany("Huawei").withPosition("software engineer")
            .withDeadline("2021-12-30").build();
    private Application deutsche = new ApplicationBuilder().withCompany("Deutsche Bank")
            .withPosition("software engineer").withDeadline("2021-12-25").withCompletion("Completed")
            .withPriority("Low").withRequirements("CV")
            .withInterviewDateAndTime("2021-12-27 1300", "2022-01-05 1300").build();
    private Application[] applications = new Application[]{shopee, google, huawei, deutsche};

    @Test
    public void getSampleData_success() {
        Application[] sampleApplications = SampleDataUtil.getSampleApplications();
        for (int i = 0; i < sampleApplications.length; i++) {
            assertEquals(applications[i], sampleApplications[i]);
        }
    }

    @Test
    public void getSampleInternship_success() {
        List<Application> applicationList = new ArrayList<>();
        for (Application application: applications) {
            applicationList.add(application);
        }
        Internship sampleInternship = new Internship();
        sampleInternship.setApplications(applicationList);
        assertEquals(sampleInternship, SampleDataUtil.getSampleInternship());
    }
}
