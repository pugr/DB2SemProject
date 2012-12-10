package cz.zcu.kiv.eegmongo.logic.parser;

import cz.zcu.kiv.eegmongo.crossstore.domain.Scenario;
import cz.zcu.kiv.eegmongo.crossstore.domain.p300.P300Document;
import cz.zcu.kiv.eegmongo.crossstore.domain.p300.Phase;
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
public class P300DocumentParser extends DefaultHandler implements DocumentParser  {

    private String temp;
    private List<Phase> phases;
    private Phase phase;

    @Override
    public Scenario parse(InputStream inputStream) throws IOException, SAXException, ParserConfigurationException {

        //Create a "parser factory" for creating SAX parsers
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        saxParser.parse(inputStream, this);

        P300Document p300Document = new P300Document();
        p300Document.setPhases(phases);

        return p300Document;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        temp = new String(ch, start, length);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        temp = "";

        if (qName.equalsIgnoreCase("scenario")) {
            phases = new ArrayList<Phase>();
        }
        else if (qName.equalsIgnoreCase("phase")) {
            phase = new Phase();

            String source = attributes.getValue("src");
            String type = attributes.getValue("type");
            String instruction = attributes.getValue("instruction");

            phase.setSource(source);
            phase.setType(type);
            phase.setInstruction(instruction);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equalsIgnoreCase("phase")) {
            phases.add(phase);
        }
    }
}
