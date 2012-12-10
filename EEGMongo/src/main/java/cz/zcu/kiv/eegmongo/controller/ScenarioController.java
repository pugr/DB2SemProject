package cz.zcu.kiv.eegmongo.controller;

import cz.zcu.kiv.eegmongo.crossstore.domain.Scenario;
import cz.zcu.kiv.eegmongo.crossstore.domain.SubjectDocument;
import cz.zcu.kiv.eegmongo.crossstore.domain.User;
import cz.zcu.kiv.eegmongo.crossstore.domain.cisla.CislaDocument;
import cz.zcu.kiv.eegmongo.crossstore.domain.generic.GenericDocument;
import cz.zcu.kiv.eegmongo.crossstore.domain.p300.P300Document;
import cz.zcu.kiv.eegmongo.crossstore.domain.result.ResultDocument;
import cz.zcu.kiv.eegmongo.crossstore.domain.scenarios.ScenariosDocument;
import cz.zcu.kiv.eegmongo.crossstore.domain.settings.SettingsDocument;
import cz.zcu.kiv.eegmongo.logic.AvailableScenarios;
import cz.zcu.kiv.eegmongo.logic.parser.DocumentParserService;
import cz.zcu.kiv.eegmongo.model.ScenarioItem;
import cz.zcu.kiv.eegmongo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Jan Koreň
 * @email: korenjan@students.zcu.cz
 * Date: 16.5.12
 */
@Controller
public class ScenarioController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CislaDocumentRepository cislaDocumentRepository;
    @Autowired
    private GenericDocumentRepository genericDocumentRepository;
    @Autowired
    private P300DocumentRepository p300DocumentRepository;
    @Autowired
    private ResultDocumentRepository resultDocumentRepository;
    @Autowired
    private ScenariosDocumentRepository scenariosDocumentRepository;
    @Autowired
    private SettingsDocumentRepository settingsDocumentRepository;
    @Autowired
    private SubjectDocumentRepository subjectDocumentRepository;

    @Autowired
    private DocumentParserService documentParserFactory;

    @Autowired
    private DocumentRepositoryService documentRepositoryService;

    @RequestMapping(value = "/scenarios/{userId}/newScenario",
                    method = RequestMethod.GET)
    public ModelAndView addScenario(@PathVariable String userId, ModelAndView mav) {

        User user = userRepository.findOne(Long.parseLong(userId));

        mav.addObject(new ScenarioItem());
        String[] documentTypesList = getAvailableScenarios();
        mav.addObject("documentTypesList", documentTypesList);
        mav.addObject(user);
        mav.setViewName("upload/uploadForm");

        return mav;

    }

    @RequestMapping(value = "/scenarios/{userId}/newScenario",
                    method = RequestMethod.POST)
    public ModelAndView create(@PathVariable String userId,
                         @RequestParam("documentType") String documentTypeString,
                         ScenarioItem scenarioItem, BindingResult result) {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.err.println("Error: " + error.getCode() + " - " + error.getDefaultMessage());
            }
            return new ModelAndView("upload/uploadForm");
        }

        AvailableScenarios documentType = AvailableScenarios.valueOf(documentTypeString);

        Scenario scenario = null;
        try {
            scenario = documentParserFactory.parse(scenarioItem.getFileData().getInputStream(),
                    documentType);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        documentRepositoryService.saveToRepositoryByDocumentType(documentType,
                userId, scenarioItem, scenario);

        List<SubjectDocument> subjectDocuments = subjectDocumentRepository.findAll();
        subjectDocumentRepository.findAll();

        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userRepository.findAll());
        mav.addObject("status", "ok");
        mav.setViewName("users/list");

        return mav;

    }

    @RequestMapping(value = "/scenarios/SCENARIO_CISLA/{scenarioId}",
                    method = RequestMethod.GET)
    public ModelAndView getScenarioCislaDetail(@PathVariable String scenarioId) {

        CislaDocument document = cislaDocumentRepository.findOne(scenarioId);

        ModelAndView mav = new ModelAndView();
        mav.addObject("document", document);
        mav.setViewName("scenarios/cisla");

        return mav;
    }

    @RequestMapping(value = "/scenarios/SCENARIO_GENERIC/{scenarioId}",
                    method = RequestMethod.GET)
    public ModelAndView getScenarioGenericDetail(@PathVariable String scenarioId) {

        GenericDocument document = genericDocumentRepository.findOne(scenarioId);

        ModelAndView mav = new ModelAndView();
        mav.addObject("document", document);
        mav.setViewName("scenarios/generic");

        return mav;
    }

    @RequestMapping(value = "/scenarios/SCENARIO_P300/{scenarioId}",
                    method = RequestMethod.GET)
    public ModelAndView getScenarioP300Detail(@PathVariable String scenarioId) {

        P300Document document = p300DocumentRepository.findOne(scenarioId);

        ModelAndView mav = new ModelAndView();
        mav.addObject("document", document);
        mav.setViewName("scenarios/p300");

        return mav;
    }

    @RequestMapping(value = "/scenarios/SCENARIO_RESULT/{scenarioId}",
                    method = RequestMethod.GET)
    public ModelAndView getScenarioResultDetail(@PathVariable String scenarioId) {

        ResultDocument document = resultDocumentRepository.findOne(scenarioId);

        ModelAndView mav = new ModelAndView();
        mav.addObject("document", document);
        mav.setViewName("scenarios/result");

        return mav;
    }

    @RequestMapping(value = "/scenarios/SCENARIO_SCENARIOS/{scenarioId}",
                    method = RequestMethod.GET)
    public ModelAndView getScenarioScenariosDetail(@PathVariable String scenarioId) {

        ScenariosDocument document = scenariosDocumentRepository.findOne(scenarioId);

        ModelAndView mav = new ModelAndView();
        mav.addObject("document", document);
        mav.setViewName("scenarios/scenarios");

        return mav;
    }

    @RequestMapping(value = "/scenarios/SCENARIO_SETTINGS/{scenarioId}",
                    method = RequestMethod.GET)
    public ModelAndView getScenarioSettingsDetail(@PathVariable String scenarioId) {

        SettingsDocument document = settingsDocumentRepository.findOne(scenarioId);

        ModelAndView mav = new ModelAndView();
        mav.addObject("document", document);
        mav.setViewName("scenarios/settings");

        return mav;
    }

    @RequestMapping(value = "/scenarios/SCENARIO_SUBJECTS/{scenarioId}",
                    method = RequestMethod.GET)
    public ModelAndView getScenarioSubjectsDetail(@PathVariable String scenarioId) {

        SubjectDocument document = subjectDocumentRepository.findOne(scenarioId);

        ModelAndView mav = new ModelAndView();
        mav.addObject("document", document);
        mav.setViewName("scenarios/subjects");

        return mav;
    }

    private String[] getAvailableScenarios() {
        List<String> values = new ArrayList<String>();
        for(AvailableScenarios value : AvailableScenarios.values()) {
            values.add(value.name());
        }
        return values.toArray(new String[values.size()]);
    }
}
