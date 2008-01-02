package planetekids.beans.stateful;

import java.util.List;
import javax.ejb.Remote;
import planetekids.beans.entity.QuestionnaireBean;

@Remote
public interface CustomerRemote {
    public void init();
    public List<QuestionnaireBean> getQuestionnaires();
    public QuestionnaireBean getQuestionnaire(int questionnaire_id);
    public void createResult(int id, String value, String comment) throws Exception;
}
