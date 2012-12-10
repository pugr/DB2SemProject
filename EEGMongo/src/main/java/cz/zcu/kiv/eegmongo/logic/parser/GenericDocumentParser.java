package cz.zcu.kiv.eegmongo.logic.parser;

import cz.zcu.kiv.eegmongo.crossstore.domain.Scenario;
import cz.zcu.kiv.eegmongo.crossstore.domain.generic.GenericDocument;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;

import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import de.odysseus.staxon.json.JsonXMLConfig;
import de.odysseus.staxon.json.JsonXMLConfigBuilder;
import de.odysseus.staxon.json.JsonXMLOutputFactory;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.*;
import javax.xml.transform.*;
import javax.xml.transform.stax.StAXResult;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Jan Koreň
 * @email: korenjan@students.zcu.cz
 * Date: 16.5.12
 */
@Service
public class GenericDocumentParser extends DefaultHandler implements DocumentParser {

    //JSONObject result;
    //List<JSONObject> stack;

    @Override
    public Scenario parse(InputStream inputStream) throws IOException, SAXException, ParserConfigurationException {


        OutputStream output = new ByteArrayOutputStream();
        JsonXMLConfig config = new JsonXMLConfigBuilder().autoArray(true).prettyPrint(false).build();

        try {

            /* Create source (XML). */
            XMLEventReader reader = XMLInputFactory.newInstance().createXMLEventReader(inputStream);
            //XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(inputStream);
            //Source source = new StAXSource(reader);


            /* Create result (JSON). */
            XMLEventWriter writer = new JsonXMLOutputFactory(config).createXMLEventWriter(output);
            //XMLStreamWriter writer = new JsonXMLOutputFactory(config).createXMLStreamWriter(output);
            //Result result = new StAXResult(writer);

            /*
			 * Copy events from reader to writer.
			 */
			writer.add(reader);

            /* Copy source to result via "identity transform". */
            //TransformerFactory.newInstance().newTransformer().transform(source, result);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } finally {

            /* As per StAX specification, XMLStreamReader/Writer.close()
            doesn't close
            the underlying stream. */
            output.close();
            inputStream.close();
        }

        /*
        try {
            json = xmlToJson(inputStream);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String jsonString = json.toString();

        System.out.println(jsonString);

        GenericDocument genericDocument = new GenericDocument();
        genericDocument.setJson(jsonString);
        */


        GenericDocument genericDocument = new GenericDocument();
        String json = output.toString();

        genericDocument.setJson(json);

        return genericDocument;
    }

    /*
    private JSONObject xmlToJson(InputStream inputStream) throws JSONException, IOException {

        String xml = IOUtils.toString(inputStream);
        JSONObject jsonObject = org.json.XML.toJSONObject(xml);

        return jsonObject;
    }
    */

}
