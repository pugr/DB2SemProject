package cz.zcu.kiv.eegmongo.logic.parser;

import cz.zcu.kiv.eegmongo.crossstore.domain.SubjectDocument;
import cz.zcu.kiv.eegmongo.crossstore.domain.subject.Subject;
import cz.zcu.kiv.eegmongo.crossstore.domain.subject.Test;
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
 * Date: 14.5.12
 */
@Service
public class SubjectDocumentParser extends DefaultHandler implements DocumentParser {

    private String temp;
    private List<Subject> subjectList;
    private List<Test> testList;
    private Subject subject;
    private Test test;
    private boolean inTestsElement = false;
    private boolean inTestElement = false;

    @Override
    public SubjectDocument parse(InputStream inputStream) throws IOException, SAXException,
                ParserConfigurationException {

        //Create a "parser factory" for creating SAX parsers
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        saxParser.parse(inputStream, this);

        SubjectDocument subjectDocument = new SubjectDocument();
        subjectDocument.setSubjects(subjectList);
        return subjectDocument;
    }

    /*
    * When the parser encounters plain text (not XML elements),
    * it calls(this method, which accumulates them in a string buffer
    */
    @Override
    public void characters(char[] buffer, int start, int length) {
        temp = new String(buffer, start, length);
    }

    /*
    * Every time the parser encounters the beginning of a new element,
    * it calls this method, which resets the string buffer
    */
    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) throws SAXException {
        temp = "";
        if (qName.equalsIgnoreCase("subjects")) {
            subjectList = new ArrayList<Subject>();
        }
        else if (qName.equalsIgnoreCase("subject")) {
            subject = new Subject();
        }
        else if (qName.equalsIgnoreCase("tests")) {
            testList = new ArrayList<Test>();
            inTestsElement = true;
        }
        else if (qName.equalsIgnoreCase("test")) {
            if(inTestsElement && !inTestElement) {
                test = new Test();
                inTestElement = true;
            }
        }
    }

    /*
    * When the parser encounters the end of an element, it calls this method
    */
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        if (qName.equalsIgnoreCase("subject")) {
            // add it to the list
            subjectList.add(subject);
        }
        else if (qName.equalsIgnoreCase("firstname")) {
            subject.setFirstName(temp);
        }
        else if (qName.equalsIgnoreCase("lastname")) {
            subject.setLastName(temp);
        }
        else if (qName.equalsIgnoreCase("test")) {
            if(inTestsElement && inTestElement) {
                test.setTest(temp);
                inTestElement = false;
            }
            else if(inTestsElement && !inTestElement) {
                testList.add(test);
            }
        }
        else if (qName.equalsIgnoreCase("name")) {
            test.setName(temp);
        }
        else if (qName.equalsIgnoreCase("result")) {
            test.setResult(temp);
        }
        else if (qName.equalsIgnoreCase("date")) {
            test.setDate(temp);
        }
        else if (qName.equalsIgnoreCase("tests")) {
            subject.setTests(testList);
            inTestsElement = false;
        }
    }
}
