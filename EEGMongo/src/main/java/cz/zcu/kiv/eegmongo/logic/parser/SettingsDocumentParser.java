package cz.zcu.kiv.eegmongo.logic.parser;

import cz.zcu.kiv.eegmongo.crossstore.domain.Scenario;
import cz.zcu.kiv.eegmongo.crossstore.domain.settings.SettingsDocument;
import org.springframework.stereotype.Service;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: Jan Koreň
 * @email: korenjan@students.zcu.cz
 * Date: 16.5.12
 */
@Service
public class SettingsDocumentParser extends DefaultHandler implements DocumentParser  {

    private String temp;

    private String audioSource;
    private String audioCompression;
    private String videoSource;
    private String videoCompression;

    @Override
    public Scenario parse(InputStream inputStream) throws IOException, SAXException,
            ParserConfigurationException {

        //Create a "parser factory" for creating SAX parsers
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        saxParser.parse(inputStream, this);

        SettingsDocument settingsDocument = new SettingsDocument();
        settingsDocument.setAudioCompression(audioCompression);
        settingsDocument.setAudioSource(audioSource);
        settingsDocument.setVideoCompression(videoCompression);
        settingsDocument.setVideoSource(videoSource);

        return settingsDocument;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        temp = new String(ch, start, length);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        temp = "";
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equalsIgnoreCase("audiosource")) {
            audioSource = temp;
        }
        else if (qName.equalsIgnoreCase("audiocompress")) {
            audioCompression = temp;
        }
        else if (qName.equalsIgnoreCase("videosource")) {
            videoSource = temp;
        }
        else if (qName.equalsIgnoreCase("videocompress")) {
            videoCompression = temp;
        }
    }
}
