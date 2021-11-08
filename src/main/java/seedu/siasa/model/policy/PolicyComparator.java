package seedu.siasa.model.policy;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Comparator;

public class PolicyComparator {
    public static final Comparator<Policy> POLICY_SORT_BY_ALPHA_ASC = (
            a, b) -> String.CASE_INSENSITIVE_ORDER.compare(a.getTitle().toString(), b.getTitle().toString());

    public static final Comparator<Policy> POLICY_SORT_BY_ALPHA_DSC = (
            a, b) -> -String.CASE_INSENSITIVE_ORDER.compare(a.getTitle().toString(), b.getTitle().toString());

    public static final Comparator<Policy> POLICY_SORT_BY_PAYMENT_ASC = (
            a, b) -> a.getPaymentStructure().compareTotalPayment(b.getPaymentStructure());

    public static final Comparator<Policy> POLICY_SORT_BY_PAYMENT_DSC = (
            a, b) -> -a.getPaymentStructure().compareTotalPayment(b.getPaymentStructure());

    public static final Comparator<Policy> POLICY_SORT_BY_COMMISSION_ASC = (
            a, b) -> a.compareTotalCommission(b);

    public static final Comparator<Policy> POLICY_SORT_BY_COMMISSION_DSC = (
            a, b) -> -a.compareTotalCommission(b);

    public static final Comparator<Policy> POLICY_SORT_BY_EXPIRY_ASC = (
            a, b) -> ChronoLocalDate.timeLineOrder().compare(
                    a.getCoverageExpiryDate().orElse(new CoverageExpiryDate(LocalDate.MAX)).value,
                    b.getCoverageExpiryDate().orElse(new CoverageExpiryDate(LocalDate.MAX)).value);

    public static final Comparator<Policy> POLICY_SORT_BY_EXPIRY_DSC = (
            a, b) -> -ChronoLocalDate.timeLineOrder().compare(
                    a.getCoverageExpiryDate().orElse(new CoverageExpiryDate(LocalDate.MAX)).value,
                    b.getCoverageExpiryDate().orElse(new CoverageExpiryDate(LocalDate.MAX)).value);
}
