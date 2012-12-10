package cz.zcu.kiv.eegmongo.crossstore.domain;

import cz.zcu.kiv.eegmongo.logic.AvailableScenarios;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author: Jan Koreň
 * @email: korenjan@students.zcu.cz
 * Date: 15.5.12
 */
@Document
public class Scenario {

    @Id
    private String id;

    private String name;
    private String description;
    private String userId;
    private AvailableScenarios scenarioType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public AvailableScenarios getScenarioType() {
        return scenarioType;
    }

    public void setScenarioType(AvailableScenarios scenarioType) {
        this.scenarioType = scenarioType;
    }
}
