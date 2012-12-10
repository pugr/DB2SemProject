package cz.zcu.kiv.eegmongo.crossstore.domain;

import cz.zcu.kiv.eegmongo.crossstore.domain.Scenario;
import cz.zcu.kiv.eegmongo.crossstore.domain.subject.Subject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author: Jan Koreň
 * @email: korenjan@students.zcu.cz
 * Date: 15.5.12
 */
@Document
public class SubjectDocument extends Scenario {

    private List<Subject> subjects;

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
