package pl.dashboard.nbp;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

class FxRateXmlPrettyPrinter {

    void parse(String fxRateXml) throws IOException, SAXException, ParserConfigurationException {
        var document = convertToXmlDocument(fxRateXml);
        var nodes = document.getElementsByTagName("Rate");
        printHeader(document);
        printRates(nodes);
    }

    private void printHeader(Document document) {
        System.out.println("Data: " + document.getElementsByTagName("EffectiveDate").item(0).getTextContent());
        System.out.println("Waluta = kupno; sprzedaż");
    }

    private void printRates(NodeList nodes) {
        for (var node = 0; node < nodes.getLength(); node++) {
            var nNode = nodes.item(node);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                var element = (Element) nNode;
                var currency = getElement(element, "Currency");
                var bid = getElement(element, "Bid");
                var ask = getElement(element, "Ask");
                System.out.println(String.format("%s = %s; %s", currency, bid, ask));
            }
        }
    }

    private String getElement(Element element, String elementName) {
        return element.getElementsByTagName(elementName).item(0).getTextContent();
    }

    private Document convertToXmlDocument(String fxRateXml) throws ParserConfigurationException, SAXException, IOException {
        var dbFactory = DocumentBuilderFactory.newInstance();
        var dBuilder = dbFactory.newDocumentBuilder();
        var doc = dBuilder.parse(new InputSource(new StringReader(fxRateXml)));
        doc.getDocumentElement().normalize();
        return doc;
    }
}
