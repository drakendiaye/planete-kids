package planetekids.beans.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class MultiLangStringBean implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    private String frString;
    private String enString;
    
    public MultiLangStringBean() {
    }
    
    public MultiLangStringBean(String frString, String enString) {
	this.frString = frString;
	this.enString = enString;
    }
    
    public String getFrString() {
	    return frString;
    }

    public void setFrString(String frString) {
	    this.frString = frString;
    }

    public String getEnString() {
	    return enString;
    }

    public void setEnString(String enString) {
	    this.enString = enString;
    }
    

}
