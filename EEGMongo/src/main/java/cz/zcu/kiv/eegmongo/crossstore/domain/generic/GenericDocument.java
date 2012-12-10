package cz.zcu.kiv.eegmongo.crossstore.domain.generic;

import cz.zcu.kiv.eegmongo.crossstore.domain.Scenario;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author: Jan Koreň
 * @email: korenjan@students.zcu.cz
 * Date: 17.5.12
 */
@Document
public class GenericDocument extends Scenario {

    private String json;

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
