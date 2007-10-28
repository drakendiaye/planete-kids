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
import javax.persistence.OneToOne;

@Entity
public class QuestionBean implements Serializable {
    
    public enum Pattern {SINGLE_CHOICE, MULTI_CHOICE, SORT, VALUE};
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @ManyToOne(optional=false)
    private QuestionnaireBean questionnaire;
    
    @OneToMany(mappedBy="question", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<AnswerBean> answers = new HashSet<AnswerBean>();
    
<<<<<<< .mine
    @OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL, optional=false)
    private LocaleBean text;
    
=======
    @OneToOne
    private MultiLangStringBean multiLangString;
    
    private String text;
>>>>>>> .r60
    private Pattern pattern;
    
    public QuestionBean() {
    }
    
    public QuestionBean(QuestionnaireBean questionnaire, LocaleBean text, Pattern pattern) {
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
    
    public String getText(String locale) {
        if(locale.compareTo("fr") == 0) return(text.getFr());
        else return(text.getEn());
    }

    public LocaleBean getText() {
        return text;
    }

    public void setText(LocaleBean text) {
        this.text = text;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public MultiLangStringBean getMultiLangString() {
	return multiLangString;
    }

    public void setMultiLangString(MultiLangStringBean multiLangString) {
	this.multiLangString = multiLangString;
    }
    
}
