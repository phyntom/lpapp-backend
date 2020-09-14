package co.radiantmic.lpapp.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.StAXEventBuilder;
import org.jdom2.input.StAXStreamBuilder;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Optional;

public class XmlParserUtil {

    private static Logger log = LogManager.getLogger(XmlParserUtil.class);

    /**
     * @param reader
     * @return
     * @throws JDOMException
     * @throws IOException
     */
    @SuppressWarnings("Duplicates")
    public static Document useSAXParser(StringReader reader) throws JDOMException,
            IOException {
        SAXBuilder saxBuilder = new SAXBuilder();
        return saxBuilder.build(reader);
    }

    /**
     * @param reader
     * @return
     */
    @SuppressWarnings("Duplicates")
    public static Optional<Document> staxParser(StringReader reader) {

        XMLInputFactory factory = XMLInputFactory.newFactory();
        Document xmlDoc;
        Optional<Document> optionalDoc;
        try {
            XMLEventReader eventReader = factory.createXMLEventReader(reader);
            StAXEventBuilder builder = new StAXEventBuilder();
            xmlDoc = builder.build(eventReader);
            optionalDoc = Optional.ofNullable(xmlDoc);

        } catch (JDOMException | XMLStreamException ex) {
            log.error("Error | Cannot read XML document using stax event parser - {}",ex.getMessage());
            optionalDoc = Optional.empty();
        }
        return optionalDoc;
    }

    /**
     * @param reader
     * @return
     */
    @SuppressWarnings("Duplicates")
    public static Optional<Document> streamParser(StringReader reader) {

        XMLInputFactory factory = XMLInputFactory.newFactory();
        Document xmlDoc;
        try {
            XMLStreamReader eventReader = factory.createXMLStreamReader(reader);
            StAXStreamBuilder builder = new StAXStreamBuilder();
            xmlDoc = builder.build(eventReader);
            return Optional.ofNullable(xmlDoc);
        } catch (JDOMException | XMLStreamException ex) {
            log.error("Error | Cannot read XML document using stream event parser - {}",ex.getMessage());
            return Optional.empty();
        }
    }
}
