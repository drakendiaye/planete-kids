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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

@Entity
@NamedQuery(name = "getQuestionnaires", query = "select o FROM QuestionnaireBean o")
public class QuestionnaireBean implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @OneToMany(mappedBy="questionnaire", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @Sort(type=SortType.NATURAL)
    private SortedSet<QuestionBean> questions = new TreeSet<QuestionBean>();
    
    @OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL, optional=false)
    private LocaleBean title;
    
    @OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL, optional=false)
    private LocaleBean description;
  
    public QuestionnaireBean() {
    }
    
    public QuestionnaireBean(LocaleBean title, LocaleBean description) {
        setTitle(title);
        setDescription(description);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SortedSet<QuestionBean> getQuestions() {
        return questions;
    }

    public void setQuestions(SortedSet<QuestionBean> questions) {
        this.questions = questions;
    }
    
    public String getTitle(String locale) {
        if(locale.compareTo("fr") == 0) return(title.getFr());
        else return(title.getEn());
    }

    public LocaleBean getTitle() {
        return title;
    }

    public void setTitle(LocaleBean title) {
        this.title = title;
    }
    
    public String getDescription(String locale) {
        if(locale.compareTo("fr") == 0) return(description.getFr());
        else return(description.getEn());
    }

    public LocaleBean getDescription() {
        return description;
    }

    public void setDescription(LocaleBean description) {
        this.description = description;
    }

}
