package planetekids.beans.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
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
public class AnswerBean implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @ManyToOne(optional=false)
    private QuestionBean question;
    
    @OneToMany(mappedBy="answer", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<ResultBean> results = new HashSet<ResultBean>();
    
    private String text;
    private Boolean commentable;
    
    public AnswerBean() {
    }
    
    public AnswerBean(QuestionBean question, String text, Boolean commentable) {
        setQuestion(question);
        setText(text);
        setCommentable(commentable);
    }
    
    public int GetId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public QuestionBean getQuestion() {
        return question;
    }
    
    public void setQuestion(QuestionBean question) {
        this.question = question;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public Boolean getCommentable() {
        return commentable;
    }
    
    public void setCommentable(Boolean commentable) {
        this.commentable = commentable;
    }
    
    public Set<ResultBean> getResults() {
        return results;
    }
    
    public void setResults(Set<ResultBean> results) {
        this.results = results;
    }
    
}
