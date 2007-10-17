package planetekids.beans.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class QuestionBean implements Serializable {
    
    public enum Pattern {SINGLE_CHOICE, MULTI_CHOICE, SORT, VALUE};
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @ManyToOne(optional=false)
    //@JoinColumn(name = "questionnaire_id")
    private QuestionnaireBean questionnaire;
    
    @OneToMany(mappedBy="question", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<AnswerBean> answers = new HashSet<AnswerBean>();
    
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

    public Set<AnswerBean> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<AnswerBean> answers) {
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
