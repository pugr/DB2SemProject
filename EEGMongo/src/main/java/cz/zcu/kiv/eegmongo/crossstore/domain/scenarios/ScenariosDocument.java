package cz.zcu.kiv.eegmongo.crossstore.domain.scenarios;

import cz.zcu.kiv.eegmongo.crossstore.domain.Scenario;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author: Jan Koreň
 * @email: korenjan@students.zcu.cz
 * Date: 16.5.12
 */
@Document
public class ScenariosDocument extends Scenario {

    private List<ScenarioMeta> scenariosMetadataList;

    public List<ScenarioMeta> getScenariosMetadataList() {
        return scenariosMetadataList;
    }

    public void setScenariosMetadataList(List<ScenarioMeta> scenariosMetadataList) {
        this.scenariosMetadataList = scenariosMetadataList;
    }
}
