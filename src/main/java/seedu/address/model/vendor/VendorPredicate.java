package seedu.address.model.vendor;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Vendor} matches the {@code VendorId}, {@code Phone}, {@code Name}, {@code Email},
 * {@code Address}, {@code ServiceName}, {@code Cost}, {@code OperatingHours}, and list of {@code Tag} given.
 */
public class VendorPredicate implements Predicate<Vendor> {

    private final Optional<String> vendorIdOptional;
    private final Optional<String> phoneOptional;
    private final Optional<String> nameOptional;
    private final Optional<String> emailOptional;
    private final Optional<Set<Tag>> tagsOptional;
    private final Optional<String> addressOptional;
    private final Optional<String> serviceNameOptional;
    private final Optional<String> costOptional;
    private final Optional<String> operatingHoursOptional;

    /**
     * Creates a vendor predicate.
     *
     * @param vendorIdOptional
     * @param phoneOptional
     * @param nameOptional
     * @param emailOptional
     * @param tagsOptional
     * @param addressOptional
     * @param serviceNameOptional
     * @param costOptional
     * @param operatingHoursOptional
     */
    public VendorPredicate(Optional<String> vendorIdOptional,
                           Optional<String> phoneOptional,
                           Optional<String> nameOptional,
                           Optional<String> emailOptional,
                           Optional<Set<Tag>> tagsOptional,
                           Optional<String> addressOptional,
                           Optional<String> serviceNameOptional,
                           Optional<String> costOptional,
                           Optional<String> operatingHoursOptional) {
        requireAllNonNull(vendorIdOptional, phoneOptional, nameOptional, emailOptional, tagsOptional, addressOptional,
                serviceNameOptional, costOptional, operatingHoursOptional);
        if (tagsOptional.isPresent()) {
            this.tagsOptional = Optional.of(new HashSet<>(tagsOptional.get()));
        } else {
            this.tagsOptional = Optional.empty();
        }
        this.vendorIdOptional = vendorIdOptional;
        this.phoneOptional = phoneOptional;
        this.nameOptional = nameOptional;
        this.emailOptional = emailOptional;
        this.addressOptional = addressOptional;
        this.serviceNameOptional = serviceNameOptional;
        this.costOptional = costOptional;
        this.operatingHoursOptional = operatingHoursOptional;
    }

    @Override
    public boolean test(Vendor vendor) {
        requireNonNull(vendor);
        return testForEmail(vendor) && testForName(vendor) && testForTags(vendor) && testForPhone(vendor)
                && testForVendorId(vendor) && testForAddress(vendor) && testForCost(vendor)
                && testForServiceName(vendor) && testForOperatingHours(vendor);
    }

    private boolean testForVendorId(Vendor vendor) {
        if (vendorIdOptional.isPresent()) {
            return vendorIdOptional.get().trim().toLowerCase().equals(vendor.getVendorId().value.toLowerCase());
        }
        return true;
    }

    private boolean testForName(Vendor vendor) {
        if (nameOptional.isPresent()) {
            String nameTested = nameOptional.get().toLowerCase().trim();
            String guestName = vendor.getName().toString().toLowerCase();
            return guestName.contains(nameTested);
        }
        return true;
    }

    private boolean testForEmail(Vendor vendor) {
        if (emailOptional.isPresent()) {
            String emailTested = emailOptional.get().toLowerCase().trim();
            String guestEmail = vendor.getEmail().toString().toLowerCase();
            return guestEmail.contains(emailTested);
        }
        return true;
    }

    private boolean testForPhone(Vendor vendor) {
        if (phoneOptional.isPresent()) {
            return vendor.getPhone().value.indexOf(phoneOptional.get().trim()) == 0;
        }
        return true;
    }

    private boolean testForAddress(Vendor vendor) {
        if (addressOptional.isPresent()) {
            String addressTested = addressOptional.get().toLowerCase().trim();
            String vendorAddress = vendor.getAddress().toString().toLowerCase();
            return vendorAddress.contains(addressTested);
        }
        return true;
    }

    private boolean testForServiceName(Vendor vendor) {
        if (serviceNameOptional.isPresent()) {
            return serviceNameOptional.get().trim().toLowerCase().equals(
                    vendor.getServiceName().serviceName.toLowerCase());
        }
        return true;
    }

    private boolean testForCost(Vendor vendor) {
        if (costOptional.isPresent()) {
            if (costOptional.get().contains("<")) {
                return (vendor.getCost().value < Double.parseDouble(costOptional.get().replace("<", "").trim()));
            }

            if (costOptional.get().contains(">")) {
                return (vendor.getCost().value > Double.parseDouble(costOptional.get().replace(">", "").trim()));
            }
            return Double.parseDouble(costOptional.get()) == vendor.getCost().value;
        }
        return true;
    }

    private boolean testForOperatingHours(Vendor vendor) {
        if (operatingHoursOptional.isPresent()) {
            LocalTime vendorStartTime = vendor.getOperatingHours().startTime;
            LocalTime vendorEndTime = vendor.getOperatingHours().endTime;
            List<DayOfWeek> vendorDays = vendor.getOperatingHours().recurringDays;

            if (operatingHoursOptional.get().trim().contains("-")) {
                OperatingHours testOperatingHours = parseOperatingHours(operatingHoursOptional.get());
                boolean dayTest = isSubset(testOperatingHours.recurringDays,
                        vendorDays);
                boolean timeTest =
                        isBeforeOrEquals(testOperatingHours.startTime, vendorStartTime)
                                && isAfterOrEquals(testOperatingHours.endTime, vendorStartTime);
                boolean timeTest2 =
                        isBeforeOrEquals(testOperatingHours.startTime, vendorEndTime)
                                && isAfterOrEquals(testOperatingHours.startTime,
                                vendorStartTime);
                return dayTest && (timeTest || timeTest2);
            }

            if (operatingHoursOptional.get().trim().contains(" ")) {
                String trimmedOperatingHours = operatingHoursOptional.get().trim();

                String[] args = trimmedOperatingHours.split("\\s+");
                List<DayOfWeek> days = parseDaysOfWeek(args[0]);
                LocalTime startTime = parseStartTime(args[1]);

                boolean dayTest = isSubset(days,
                        vendorDays);
                return dayTest && inBetween(startTime, vendor);
            }

            if (operatingHoursOptional.get().trim().equals("now")) {
                DayOfWeek day = LocalDate.now().getDayOfWeek();
                LocalTime time = LocalTime.now();
                return inBetween(time, vendor) && vendorDays.contains(day);
            }

            return isSubset(parseDaysOfWeek(operatingHoursOptional.get()),
                    vendorDays);
        }
        return true;
    }

    private static boolean inBetween(LocalTime time, Vendor vendor) {
        LocalTime vendorStartTime = vendor.getOperatingHours().startTime;
        LocalTime vendorEndTime = vendor.getOperatingHours().endTime;

        boolean timeTest = time.isBefore(vendorEndTime)
                && time.isAfter(vendorStartTime);
        boolean timeTest2 = time.equals(vendorStartTime)
                || time.equals(vendorEndTime);

        return timeTest || timeTest2;
    }

    private static boolean isBeforeOrEquals(LocalTime time1, LocalTime time2) {
        return time1.isBefore(time2) || time1.equals(time2);
    }

    private static boolean isAfterOrEquals(LocalTime time1, LocalTime time2) {
        return time1.isAfter(time2) || time1.equals(time2);
    }

    private boolean isSubset(List<DayOfWeek> list1, List<DayOfWeek> list2) {
        int i = 0;
        int j = 0;
        for (i = 0; i < list1.size(); i++) {
            for (j = 0; j < list2.size(); j++) {
                if (list1.get(i).equals(list2.get(j))) {
                    break;
                }
            }
            if (j == list2.size()) {
                return false;
            }
        }
        return true;
    }

    private static OperatingHours parseOperatingHours(String operatingHours) {
        String trimmedOperatingHours = operatingHours.trim();
        String[] args = trimmedOperatingHours.split("\\s+");
        String days = args[0];
        LocalTime startTime = parseStartTime(args[1].split("-")[0]);
        LocalTime endTime = parseEndTime(args[1].split("-")[1]);
        List<DayOfWeek> operatingDays = parseDaysOfWeek(days);

        return new OperatingHours(startTime, endTime, operatingDays, trimmedOperatingHours);
    }

    private static LocalTime parseStartTime(String startTimeString) {
        Integer startTimeHour = Integer.parseInt(startTimeString.substring(0, 2));
        Integer startTimeMinutes = Integer.parseInt(startTimeString.substring(2));

        return LocalTime.of(startTimeHour, startTimeMinutes);
    }

    private static LocalTime parseEndTime(String endTimeString) {
        Integer endTimeHour = Integer.parseInt(endTimeString.substring(0, 2));
        Integer endTimeMinutes = Integer.parseInt(endTimeString.substring(2));

        return LocalTime.of(endTimeHour, endTimeMinutes);
    }

    private static List<DayOfWeek> parseDaysOfWeek(String daysofWeek) {
        String trimmedDaysOfWeek = daysofWeek.trim();

        List<DayOfWeek> operatingDays = new ArrayList<>();

        for (int i = 0; i < trimmedDaysOfWeek.length(); i++) {
            DayOfWeek day = DayOfWeek.of(Character.getNumericValue(trimmedDaysOfWeek.charAt(i)));
            if (!operatingDays.contains(day)) {
                operatingDays.add(day);
            }
        }

        operatingDays.sort(new Comparator<DayOfWeek>() {
            public int compare(DayOfWeek d1, DayOfWeek d2) {
                return d1.compareTo(d2);
            }
        });

        return operatingDays;
    }

    private boolean testForTags(Vendor vendor) {
        if (tagsOptional.isPresent()) {
            List<String> vendorTagStrings =
                    vendor.getTags().stream().map(tag -> tag.tagName).collect(Collectors.toList());
            return tagsOptional.get().stream()
                    .anyMatch(tag -> vendorTagStrings.contains(StringUtil.capitalizeFirstLetter(tag.tagName.trim())));
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VendorPredicate // instanceof handles nulls
                && emailOptional.equals(((VendorPredicate) other).emailOptional)
                && nameOptional.equals(((VendorPredicate) other).nameOptional)
                && phoneOptional.equals(((VendorPredicate) other).phoneOptional)
                && vendorIdOptional.equals(((VendorPredicate) other).vendorIdOptional)
                && addressOptional.equals(((VendorPredicate) other).addressOptional)
                && serviceNameOptional.equals(((VendorPredicate) other).serviceNameOptional)
                && costOptional.equals(((VendorPredicate) other).costOptional)
                && operatingHoursOptional.equals(((VendorPredicate) other).operatingHoursOptional)
                && tagsOptional.equals(((VendorPredicate) other).tagsOptional)); // state check
    }

}
