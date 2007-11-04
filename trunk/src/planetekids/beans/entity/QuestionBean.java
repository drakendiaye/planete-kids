package planetekids.beans.entity;

import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

@Entity
public class QuestionBean implements Serializable, Comparable<QuestionBean> {
    
    public enum Pattern {SINGLE_CHOICE, MULTI_CHOICE, SORT, VALUE};
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @ManyToOne(optional=false)
    private QuestionnaireBean questionnaire;
    
    @OneToMany(mappedBy="question", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @Sort(type=SortType.NATURAL)
    private SortedSet<AnswerBean> answers = new TreeSet<AnswerBean>();
    
    @OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL, optional=false)
    private LocaleBean text;
    
    private Pattern pattern;
    
    private int number;
    
    public QuestionBean() {
    }
    
    public QuestionBean(QuestionnaireBean questionnaire, LocaleBean text, Pattern pattern,int order) {
        setQuestionnaire(questionnaire);
        setText(text);
        setPattern(pattern);
        setNumber(order);
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

    public SortedSet<AnswerBean> getAnswers() {
        return answers;
    }

    public void setAnswers(SortedSet<AnswerBean> answers) {
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
    
    public int getNumber() {
        return number;
    }

    public void setNumber(int order) {
        this.number = order;
    }

    public int compareTo(QuestionBean o) {
        if(this.getNumber() < o.getNumber()) return(-1);
        if(this.getNumber() > o.getNumber()) return(1);
        return(0);
    }
    
}
