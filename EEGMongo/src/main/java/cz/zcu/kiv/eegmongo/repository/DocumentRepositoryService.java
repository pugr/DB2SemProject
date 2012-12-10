package cz.zcu.kiv.eegmongo.repository;

import cz.zcu.kiv.eegmongo.crossstore.domain.Scenario;
import cz.zcu.kiv.eegmongo.crossstore.domain.User;
import cz.zcu.kiv.eegmongo.crossstore.domain.cisla.CislaDocument;
import cz.zcu.kiv.eegmongo.crossstore.domain.generic.GenericDocument;
import cz.zcu.kiv.eegmongo.crossstore.domain.p300.P300Document;
import cz.zcu.kiv.eegmongo.crossstore.domain.result.ResultDocument;
import cz.zcu.kiv.eegmongo.crossstore.domain.scenarios.ScenariosDocument;
import cz.zcu.kiv.eegmongo.crossstore.domain.settings.SettingsDocument;
import cz.zcu.kiv.eegmongo.crossstore.domain.SubjectDocument;
import cz.zcu.kiv.eegmongo.logic.AvailableScenarios;
import cz.zcu.kiv.eegmongo.model.ScenarioItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Jan Koreň
 * @email: korenjan@students.zcu.cz
 * Date: 16.5.12
 */
@Service
public class DocumentRepositoryService {

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

    /*
    @Autowired
    private UserInfoRepository userInfoRepository;*/

    public void saveToRepositoryByDocumentType(AvailableScenarios availableScenarios,
                                                String userId,
                                                ScenarioItem scenarioItem,
                                                Scenario scenario) {
        scenario.setUserId(userId);
        scenario.setName(scenarioItem.getName());
        scenario.setDescription(scenarioItem.getDescription());
        scenario.setScenarioType(availableScenarios);

        switch (availableScenarios) {
            case SCENARIO_CISLA:
                cislaDocumentRepository.save((CislaDocument) scenario);
                break;
            case SCENARIO_GENERIC:
                genericDocumentRepository.save((GenericDocument) scenario);
                break;
            case SCENARIO_P300:
                p300DocumentRepository.save((P300Document) scenario);
                break;
            case SCENARIO_RESULT:
                resultDocumentRepository.save((ResultDocument) scenario);
                break;
            case SCENARIO_SCENARIOS:
                scenariosDocumentRepository.save((ScenariosDocument) scenario);
                break;
            case SCENARIO_SETTINGS:
                settingsDocumentRepository.save((SettingsDocument) scenario);
                break;
            case SCENARIO_SUBJECTS:
                subjectDocumentRepository.save((SubjectDocument)scenario);
                break;
        }
    }

    public List<Scenario> getAllDocumentsByUser(User user) {

        List<Scenario> scenarioDocuments = new ArrayList<Scenario>();

        Scenario s = new Scenario();

        for (BasicRepository repository : getAllAvailableRepositories()) {
            List<Scenario> findResult = repository.findByTheUserId(user.getId().toString());

            scenarioDocuments.addAll(findResult);
        }

        return scenarioDocuments;
    }

    /*
    public Scenario getScenarioById(String id) {

        Scenario scenario = null;

        for (BasicRepository repository : getAllAvailableRepositories()) {
            scenario = (Scenario) repository.findOne(id);
        }

        return scenario;
    }
    */

    public List<String> getAllDocumentNamesByUser(User user) {
        List<String> documentNames = new ArrayList<String>();

        String id = user.getId().toString();
        List<CislaDocument> cislaDocuments = cislaDocumentRepository.findByTheUserId(id);
        List<GenericDocument> genericDocuments = genericDocumentRepository.findByTheUserId(id);
        List<P300Document> p300Documents = p300DocumentRepository.findByTheUserId(id);
        List<ResultDocument> resultDocuments = resultDocumentRepository.findByTheUserId(id);
        List<ScenariosDocument> scenariosDocuments = scenariosDocumentRepository.findByTheUserId(id);
        List<SettingsDocument> settingsDocuments = settingsDocumentRepository.findByTheUserId(id);
        List<SubjectDocument> subjectDocuments = subjectDocumentRepository.findByTheUserId(id);

        for(CislaDocument document : cislaDocuments) {
            documentNames.add(document.getName());
        }

        for(GenericDocument document : genericDocuments) {
            documentNames.add(document.getName());
        }

        for(P300Document document : p300Documents) {
            documentNames.add(document.getName());
        }

        for(ResultDocument document : resultDocuments) {
            documentNames.add(document.getName());
        }

        for(ScenariosDocument document : scenariosDocuments) {
            documentNames.add(document.getName());
        }

        for(SettingsDocument document : settingsDocuments) {
            documentNames.add(document.getName());
        }

        for(SubjectDocument document : subjectDocuments) {
            documentNames.add(document.getName());
        }

        return documentNames;
    }

    public void deleteAllDocumentsByUser(User user) {

        List<BasicRepository> repositories = getAllAvailableRepositories();

        for(BasicRepository repository : repositories) {
            List<Scenario> scenarios = repository.findByTheUserId(user.getId().toString());
            if(scenarios.size() > 0) {
                repository.delete(scenarios);
            }
        }
    }

    private List<BasicRepository> getAllAvailableRepositories() {

        List<BasicRepository> repositories = new ArrayList<BasicRepository>();

        repositories.add(cislaDocumentRepository);
        repositories.add(genericDocumentRepository);
        repositories.add(p300DocumentRepository);
        repositories.add(resultDocumentRepository);
        repositories.add(scenariosDocumentRepository);
        repositories.add(settingsDocumentRepository);
        repositories.add(subjectDocumentRepository);

        return repositories;
    }
}
