package seedu.intern.logic.parser;

import static seedu.intern.logic.commands.FilterCommand.MESSAGE_NOT_FILTERED;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_COURSE;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_GRADUATIONYEARMONTH;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_INSTITUTION;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_JOB;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.intern.logic.commands.FilterCommand;
import seedu.intern.logic.commands.FilterCommand.FilterApplicantDescriptor;
import seedu.intern.logic.parser.exceptions.ParseException;
import seedu.intern.model.applicant.ApplicationStatus;
import seedu.intern.model.applicant.Institution;
import seedu.intern.model.skills.Skill;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_SKILL,
                        PREFIX_GRADE,
                        PREFIX_INSTITUTION,
                        PREFIX_COURSE,
                        PREFIX_JOB,
                        PREFIX_GRADUATIONYEARMONTH,
                        PREFIX_STATUS);

        FilterApplicantDescriptor filterApplicantDescriptor = new FilterApplicantDescriptor();

        if (argMultimap.getValue(PREFIX_GRADE).isPresent()) {
            filterApplicantDescriptor.setGrade(ParserUtil.parseGrade(argMultimap.getValue(PREFIX_GRADE).get()));
        }
        parseInstitutionsForEdit(argMultimap.getAllValues(PREFIX_INSTITUTION))
                .ifPresent(filterApplicantDescriptor::setInstitutions);
        if (argMultimap.getValue(PREFIX_GRADUATIONYEARMONTH).isPresent()) {
            filterApplicantDescriptor.setGraduationYearMonth(ParserUtil.parseGraduationYearMonth(
                    argMultimap.getValue(PREFIX_GRADUATIONYEARMONTH).get()));
        }
        parseCoursesForEdit(argMultimap.getAllValues(PREFIX_COURSE)).ifPresent(filterApplicantDescriptor::setCourses);
        parseApplicationStatusesForEdit(argMultimap.getAllValues(PREFIX_STATUS))
                .ifPresent(filterApplicantDescriptor::setApplicationStatuses);
        parseSkillsForEdit(argMultimap.getAllValues(PREFIX_SKILL)).ifPresent(filterApplicantDescriptor::setSkills);
        parseJobsForEdit(argMultimap.getAllValues(PREFIX_JOB)).ifPresent(filterApplicantDescriptor::setJobs);
        if (!filterApplicantDescriptor.isAnyFieldFiltered()) {
            throw new ParseException(String.format(MESSAGE_NOT_FILTERED, FilterCommand.MESSAGE_USAGE));
        }
        return new FilterCommand(filterApplicantDescriptor);
    }


    private Optional<Set<Institution>> parseInstitutionsForEdit(Collection<String> institutions) throws ParseException {
        assert institutions != null;

        if (institutions.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> institutionSet = institutions.size() == 1 && institutions.contains("")
                ? Collections.emptySet() : institutions;
        return Optional.of(ParserUtil.parseInstitutions(institutionSet));
    }

    private Optional<Set<List<String>>> parseJobsForEdit(Collection<String> jobs) throws ParseException {
        assert jobs != null;

        if (jobs.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> jobSet = jobs.size() == 1 && jobs.contains("")
                ? Collections.emptySet() : jobs;
        return Optional.of(ParserUtil.parseJobFilters(jobSet));
    }

    private Optional<Set<List<String>>> parseCoursesForEdit(Collection<String> courses) throws ParseException {
        assert courses != null;

        if (courses.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> courseSet = courses.size() == 1 && courses.contains("")
                ? Collections.emptySet() : courses;
        return Optional.of(ParserUtil.parseCourseFilters(courseSet));
    }

    private Optional<Set<ApplicationStatus>> parseApplicationStatusesForEdit(Collection<String> statuses)
            throws ParseException {
        assert statuses != null;

        if (statuses.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> statusSet = statuses.size() == 1 && statuses.contains("")
                ? Collections.emptySet() : statuses;
        return Optional.of(ParserUtil.parseApplicationStatuses(statusSet));
    }

    /**
     * Parses {@code Collection<String> skills} into a {@code Set<skill>} if {@code skills} is non-empty.
     * If {@code skills} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<skill>} containing zero skills.
     */
    private Optional<Set<Skill>> parseSkillsForEdit(Collection<String> skills) throws ParseException {
        assert skills != null;

        if (skills.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> skillSet = skills.size() == 1 && skills.contains("") ? Collections.emptySet() : skills;
        return Optional.of(ParserUtil.parseSkills(skillSet));
    }

}
