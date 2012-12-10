package cz.zcu.kiv.eegmongo.logic.parser;

import cz.zcu.kiv.eegmongo.crossstore.domain.cisla.CislaDocument;
import cz.zcu.kiv.eegmongo.crossstore.domain.cisla.Image;
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
public class CislaDocumentParser extends DefaultHandler implements DocumentParser {

    private String temp;
    private List<Image> imageList;
    private Image image;
    private int count;
    private int delay;
    private int pause;
    private boolean random;
    private String txtFile;
    private String report;

    @Override
    public CislaDocument parse(InputStream inputStream) throws IOException, SAXException,
                ParserConfigurationException {

        //Create a "parser factory" for creating SAX parsers
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        saxParser.parse(inputStream, this);

        CislaDocument cislaDocument = new CislaDocument();
        cislaDocument.setImageList(imageList);
        cislaDocument.setCount(count);
        cislaDocument.setRandom(random);
        cislaDocument.setTxtFile(txtFile);
        cislaDocument.setDelay(delay);
        cislaDocument.setReport(report);
        cislaDocument.setPause(pause);

        return cislaDocument;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        temp = "";

        if (qName.equalsIgnoreCase("imagelist")) {
            imageList = new ArrayList<Image>();
            int length = attributes.getLength();

            count = Integer.parseInt(attributes.getValue("count"));
            random = attributes.getValue("random").equals("yes") ? true : false;
            txtFile = attributes.getValue("txt");
            delay = Integer.parseInt(attributes.getValue("delay"));
            report = attributes.getValue("report");
            pause = Integer.parseInt(attributes.getValue("pause"));
        }
        else if (qName.equalsIgnoreCase("img")) {
            image = new Image();
            String imageSource = attributes.getValue("src");
            int count = Integer.parseInt(attributes.getValue("count"));
            String mark = attributes.getValue("mark");

            image.setSource(imageSource);
            image.setCount(count);
            image.setMark(mark);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equalsIgnoreCase("img")) {
            imageList.add(image);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        temp = new String(ch, start, length);
    }
}
