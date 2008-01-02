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
public class AnswerBean implements Serializable, Comparable<AnswerBean> {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @ManyToOne(optional=false)
    private QuestionBean question;
    
    @OneToMany(mappedBy="answer", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<ResultBean> results = new HashSet<ResultBean>();
    
    @OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL, optional=false)
    private LocaleBean text;
    
    private Boolean commentable;
    
    private int number;
    
    public AnswerBean() {
    }
    
    public AnswerBean(QuestionBean question, LocaleBean text, Boolean commentable, int order) {
        setQuestion(question);
        setText(text);
        setCommentable(commentable);
        setNumber(order);
    }
    
    public int getId() {
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int order) {
        this.number = order;
    }
    
    public int compareTo(AnswerBean o) {
        if(this.getNumber() < o.getNumber()) return(-1);
        if(this.getNumber() > o.getNumber()) return(1);
        return(0);
    }
    
}
