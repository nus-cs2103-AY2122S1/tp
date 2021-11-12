package seedu.tracker.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.calendar.AcademicYear;
import seedu.tracker.model.calendar.Semester;
import seedu.tracker.model.module.Code;
import seedu.tracker.model.module.Description;
import seedu.tracker.model.module.Mc;
import seedu.tracker.model.module.Module;
import seedu.tracker.model.module.Title;
import seedu.tracker.model.tag.Tag;
import seedu.tracker.model.util.SampleDataUtil;

/**
 * A utility class to help with building Module objects.
 */
public class ModuleBuilder {

    public static final String DEFAULT_CODE = "CS2103T";
    public static final String DEFAULT_TITLE = "Software Engineering";
    public static final String DEFAULT_DESCRIPTION = "Covers the main areas of software development";
    public static final int DEFAULT_MC = 4;

    private Code code;
    private Title title;
    private Description description;
    private Mc mc;
    private Set<Tag> tags;
    private boolean hasAcademicCalendar = false;
    private AcademicCalendar academicCalendar;

    /**
     * Creates a {@code ModuleBuilder} with the default details.
     */
    public ModuleBuilder() {
        code = new Code(DEFAULT_CODE);
        title = new Title(DEFAULT_TITLE);
        description = new Description(DEFAULT_DESCRIPTION);
        mc = new Mc(DEFAULT_MC);
        tags = new HashSet<>();
    }

    /**
     * Initializes the ModuleBuilder with the data of {@code moduleToCopy}.
     */
    public ModuleBuilder(Module moduleToCopy) {
        code = moduleToCopy.getCode();
        title = moduleToCopy.getTitle();
        description = moduleToCopy.getDescription();
        mc = moduleToCopy.getMc();
        tags = new HashSet<>(moduleToCopy.getTags());
        if (moduleToCopy.hasAcademicCalendar()) {
            academicCalendar = moduleToCopy.getAcademicCalendar();
            hasAcademicCalendar = true;
        }
    }

    /**
     * Sets the {@code Code} of the {@code Module} that we are building.
     */
    public ModuleBuilder withCode(String code) {
        this.code = new Code(code);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Module} that we are building.
     */
    public ModuleBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Title} of the {@code Module} that we are building.
     */
    public ModuleBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Module} that we are building.
     */
    public ModuleBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Mc} of the {@code Module} that we are building.
     */
    public ModuleBuilder withMc(int mc) {
        this.mc = new Mc(mc);
        return this;
    }

    /**
     * Sets the academic calendar of the Module that we are building.
     */
    public ModuleBuilder withAcademicCalendar(int year, int semester) {
        this.academicCalendar = new AcademicCalendar(new AcademicYear(year), new Semester(semester));
        this.hasAcademicCalendar = true;
        return this;
    }

    /**
     * Builds a module with the attributes in this ModuleBuilder.
     * @return Module newly built.
     */
    public Module build() {
        if (hasAcademicCalendar) {
            return new Module(code, title, description, mc, tags, academicCalendar);
        }
        return new Module(code, title, description, mc, tags);
    }

}
