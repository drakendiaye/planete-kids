package planetekids.beans.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ResultBean implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @ManyToOne(optional=false)
    private AnswerBean answer;
    
    private String value;
    private String comment;
    
    public ResultBean() {
    }
    
    public ResultBean(AnswerBean answer, String value, String comment) {
        setAnswer(answer);
        setValue(value);
        setComment(comment);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AnswerBean getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerBean answer) {
        this.answer = answer;
    }

    public

    String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    /*@PreRemove
    public void titi() {
	System.out.println("\n\n\nremoving result : " + this.getId() + "\n\n\n");
    }
    
    @PostRemove
    public void toto(){
	System.out.println("result id : " + this.getId() + " removed");
    }*/
}
