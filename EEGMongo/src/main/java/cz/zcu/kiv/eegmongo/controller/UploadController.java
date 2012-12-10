package cz.zcu.kiv.eegmongo.controller;

import cz.zcu.kiv.eegmongo.crossstore.domain.SubjectDocument;
import cz.zcu.kiv.eegmongo.logic.parser.SubjectDocumentParser;
import cz.zcu.kiv.eegmongo.repository.SubjectDocumentRepository;
import cz.zcu.kiv.eegmongo.model.ScenarioItem;
import cz.zcu.kiv.eegmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * @author: Jan Koreň
 * @email: korenjan@students.zcu.cz
 * Date: 15.5.12
 */
@Controller
@RequestMapping(value = "/upload")
public class UploadController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private SubjectDocumentRepository subjectDocumentRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String getUploadForm(Model model) {
        model.addAttribute(new ScenarioItem());
        return "upload/uploadForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView create(ScenarioItem scenarioItem, BindingResult result, ModelAndView mav) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.err.println("Error: " + error.getCode() + " - " + error.getDefaultMessage());
            }
            return new ModelAndView("upload/uploadForm");
        }

        SubjectDocumentParser parser = new SubjectDocumentParser();
        SubjectDocument subjectDocument = null;
        try {
            subjectDocument = parser.parse(scenarioItem.getFileData().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        subjectDocumentRepository.save(subjectDocument);

        mav.setViewName("users");

        return mav;
    }

}
