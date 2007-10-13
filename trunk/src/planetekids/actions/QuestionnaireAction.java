package planetekids.actions;

import org.apache.struts2.config.Namespace;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;

@Namespace("questionnaire")
@Results({
    @Result(name="success", value="/questionnaire.jsp"),
    @Result(name="error", value="/error.jsp")
})
public class QuestionnaireAction {

    public String execute() throws Exception {
        if(true) {
            return "success";
        } else {
            return "error";
        }
    }
}
