package cz.zcu.kiv.eegmongo.logic.parser;

import cz.zcu.kiv.eegmongo.crossstore.domain.Scenario;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: Jan Koreň
 * @email: korenjan@students.zcu.cz
 * Date: 16.5.12
 */
public interface DocumentParser {

    Scenario parse(InputStream inputStream) throws IOException, SAXException,
            ParserConfigurationException;
}
