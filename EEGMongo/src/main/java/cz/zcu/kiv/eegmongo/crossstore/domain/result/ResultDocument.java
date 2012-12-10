package cz.zcu.kiv.eegmongo.crossstore.domain.result;

import cz.zcu.kiv.eegmongo.crossstore.domain.Scenario;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author: Jan Koreň
 * @email: korenjan@students.zcu.cz
 * Date: 16.5.12
 */
@Document
public class ResultDocument extends Scenario {

    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
