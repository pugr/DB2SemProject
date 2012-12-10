package cz.zcu.kiv.eegmongo.logic.parser;

import cz.zcu.kiv.eegmongo.crossstore.domain.Scenario;
import cz.zcu.kiv.eegmongo.logic.AvailableScenarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: Jan Koreň
 * @email: korenjan@students.zcu.cz
 * Date: 16.5.12
 */
@Component
public class DocumentParserService {

    @Autowired
    private CislaDocumentParser cislaDocumentParser;
    @Autowired
    private GenericDocumentParser genericDocumentParser;
    @Autowired
    private P300DocumentParser p300DocumentParser;
    @Autowired
    private ResultDocumentParser resultDocumentParser;
    @Autowired
    private ScenariosDocumentParser scenariosDocumentParser;
    @Autowired
    private SettingsDocumentParser settingsDocumentParser;
    @Autowired
    private SubjectDocumentParser subjectDocumentParser;

    public Scenario parse(InputStream inputStream, AvailableScenarios availableScenarios)
            throws IOException, SAXException, ParserConfigurationException {

        switch (availableScenarios) {
            case SCENARIO_CISLA:
                return cislaDocumentParser.parse(inputStream);
            case SCENARIO_GENERIC:
                return genericDocumentParser.parse(inputStream);
            case SCENARIO_P300:
                return p300DocumentParser.parse(inputStream);
            case SCENARIO_RESULT:
                return resultDocumentParser.parse(inputStream);
            case SCENARIO_SCENARIOS:
                return scenariosDocumentParser.parse(inputStream);
            case SCENARIO_SETTINGS:
                return settingsDocumentParser.parse(inputStream);
            case SCENARIO_SUBJECTS:
                return subjectDocumentParser.parse(inputStream);
        }

        return null;
    }
}
