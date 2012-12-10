package cz.zcu.kiv.eegmongo.model;

import cz.zcu.kiv.eegmongo.logic.AvailableScenarios;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author: Jan Koreň
 * @email: korenjan@students.zcu.cz
 * Date: 15.5.12
 */
public class ScenarioItem {

    private String name;
    private String description;
    private String documentType;
    private CommonsMultipartFile fileData;

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

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public CommonsMultipartFile getFileData() {
        return fileData;
    }

    public void setFileData(CommonsMultipartFile fileData) {
        this.fileData = fileData;
    }
}
