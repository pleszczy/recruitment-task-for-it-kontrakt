package pl.dashboard.nbp;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.lang.String.format;
import static java.time.format.DateTimeFormatter.ofPattern;

class PublishDateArgsValidator {
    private final DateTimeFormatter formatter;
    private final String dateArgFormat;

    PublishDateArgsValidator(String dateArgFormat) {
        this.dateArgFormat = dateArgFormat;
        this.formatter = ofPattern(dateArgFormat);
    }

    void validateArguments(String... args) throws IllegalArgumentException {
        if (args == null || args.length < 1) {
            throw new IllegalArgumentException(format("Please provide the date argument in the %s format", dateArgFormat));
        }
        if (args.length > 1) {
            throw new IllegalArgumentException("Only one date argument allowed");
        }
        try {
            formatter.parse(args[0]);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(format("Could not parse the date argument provided : %s. Date should be in the %s format", args[0], dateArgFormat));
        }
    }
}
