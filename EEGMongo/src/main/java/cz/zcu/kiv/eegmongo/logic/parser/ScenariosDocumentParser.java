package cz.zcu.kiv.eegmongo.logic.parser;

import cz.zcu.kiv.eegmongo.crossstore.domain.Scenario;
import cz.zcu.kiv.eegmongo.crossstore.domain.scenarios.ScenarioMeta;
import cz.zcu.kiv.eegmongo.crossstore.domain.scenarios.ScenariosDocument;
import org.springframework.stereotype.Service;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Jan Koreň
 * @email: korenjan@students.zcu.cz
 * Date: 16.5.12
 */
@Service
public class ScenariosDocumentParser extends DefaultHandler implements DocumentParser  {

    private String temp;
    private String name;
    private String source;
    private ScenarioMeta scenarioMeta;
    private List<ScenarioMeta> scenarioMetaList;

    @Override
    public Scenario parse(InputStream inputStream) throws IOException, SAXException, ParserConfigurationException {

        //Create a "parser factory" for creating SAX parsers
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        saxParser.parse(inputStream, this);

        ScenariosDocument scenariosDocument = new ScenariosDocument();
        scenariosDocument.setScenariosMetadataList(scenarioMetaList);

        return scenariosDocument;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        temp = new String(ch, start, length);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        temp = "";

        if (qName.equalsIgnoreCase("scenarios")) {
            scenarioMetaList = new ArrayList<ScenarioMeta>();
        }
        else if (qName.equalsIgnoreCase("scenario")) {

            scenarioMeta = new ScenarioMeta();

            name = attributes.getValue("name");
            source = attributes.getValue("src");

            scenarioMeta.setName(name);
            scenarioMeta.setSource(source);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equalsIgnoreCase("scenario")) {
            scenarioMetaList.add(scenarioMeta);
        }
    }
}
