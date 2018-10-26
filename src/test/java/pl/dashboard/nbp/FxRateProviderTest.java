package pl.dashboard.nbp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class FxRateProviderTest {
    private FxRateProvider SUT = new FxRateProvider();

    @Test
    void should_download_nbp_fx_rates() throws IOException, InterruptedException {
        Assertions.assertNotNull(SUT.forDate("2018-09-27"));
    }
}
