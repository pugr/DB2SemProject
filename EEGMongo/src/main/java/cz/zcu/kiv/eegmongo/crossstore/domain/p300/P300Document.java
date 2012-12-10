package cz.zcu.kiv.eegmongo.crossstore.domain.p300;

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
public class P300Document extends Scenario {

    private List<Phase> phases;

    public List<Phase> getPhases() {
        return phases;
    }

    public void setPhases(List<Phase> phases) {
        this.phases = phases;
    }
}
