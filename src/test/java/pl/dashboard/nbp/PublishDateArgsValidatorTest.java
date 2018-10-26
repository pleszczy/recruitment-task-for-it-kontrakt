package pl.dashboard.nbp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PublishDateArgsValidatorTest {
    private final static String TEST_DATE_FORMAT = "YYYY-MM-DD";
    private PublishDateArgsValidator SUT = new PublishDateArgsValidator(TEST_DATE_FORMAT);

    @Test()
    void should_throw_an_exception_if_no_args_are_provided() {
        var emptyArgs = new String[]{};

        assertThrows(IllegalArgumentException.class, () -> SUT.validateArguments(emptyArgs), "Expected to throw an exception if no args were provided");
    }

    @Test()
    void should_throw_an_exception_if_more_than_one_arg_is_provided() {
        var tooManyArgs = new String[]{"one","two"};

        assertThrows(IllegalArgumentException.class, () -> SUT.validateArguments(tooManyArgs), "Expected to throw an exception if there are more than one arg");
    }

    @Test()
    void should_not_throw_an_exception_if_the_publication_date_arg_is_correctly_formatted() {
        var correctlyFormattedDateArg = new String[]{"2018-09-27"};

        SUT.validateArguments(correctlyFormattedDateArg);
    }

    @Test()
    void should_throw_an_exception_if_the_publication_date_arg_is_wrongly_formatted() {
        var wronglyFormattedDateArg = new String[]{"12/12/12"};

        SUT.validateArguments("2018-09-27");

        assertThrows(IllegalArgumentException.class, () -> SUT.validateArguments(wronglyFormattedDateArg), "Expected to throw an exception if the publication date arg is not formatted according to YYYY-MM-DD ISO 8601 format e.g. 2018-09-27");
    }
}