package cz.zcu.kiv.eegmongo.crossstore.domain.scenarios;

/**
 * @author: Jan Koreň
 * @email: korenjan@students.zcu.cz
 * Date: 16.5.12
 */
public class ScenarioMeta {

    private String name;
    private String source;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
