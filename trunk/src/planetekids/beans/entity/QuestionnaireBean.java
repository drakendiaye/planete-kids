package planetekids.beans.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name = "getQuestionnaires", query = "select o FROM QuestionnaireBean o")
public class QuestionnaireBean implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @OneToMany(mappedBy="questionnaire", cascade=CascadeType.ALL)
    private List<QuestionBean> questions = new ArrayList<QuestionBean>();
    
    private String title;
    private String description;
  
    public QuestionnaireBean() {
    }
    
    public QuestionnaireBean(String title, String description) {
        setTitle(title);
        setDescription(description);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<QuestionBean> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionBean> questions) {
        this.questions = questions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
