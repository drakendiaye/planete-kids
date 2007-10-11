package planetekids.beans.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class QuestionBean implements Serializable {
    
    public enum Pattern {SINGLE_CHOICE, MULTI_CHOICE, SORT, VALUE};
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @ManyToOne(cascade=CascadeType.ALL, optional=false)
    private QuestionnaireBean questionnaire;
    
    @OneToMany(mappedBy="question", cascade=CascadeType.ALL)
    private List<AnswerBean> answers = new ArrayList<AnswerBean>();
    
    private String text;
    private Pattern pattern;
    
    public QuestionBean() {
    }
    
    public QuestionBean(QuestionnaireBean questionnaire, String text, Pattern pattern) {
        setQuestionnaire(questionnaire);
        setText(text);
        setPattern(pattern);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public QuestionnaireBean getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(QuestionnaireBean questionnaire) {
        this.questionnaire = questionnaire;
    }

    public List<AnswerBean> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerBean> answers) {
        this.answers = answers;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }
    
}
