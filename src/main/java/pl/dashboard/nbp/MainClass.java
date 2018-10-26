package pl.dashboard.nbp;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class MainClass {
    private static final String NBP_PUBLICATION_DATE_FORMAT = "YYYY-MM-DD";

    public static void main(String... args) throws IOException, InterruptedException, ParserConfigurationException, SAXException {
        var publishDateValidator = new PublishDateArgsValidator(NBP_PUBLICATION_DATE_FORMAT);
        var fxRateProvider = new FxRateProvider();
        var fxRateXmlPrettyPrinter = new FxRateXmlPrettyPrinter();

        publishDateValidator.validateArguments(args);
        var fxRateXml = fxRateProvider.forDate(args[0]);
        fxRateXmlPrettyPrinter.parse(fxRateXml);
    }
}
