package planetekids.beans.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LocaleBean implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    private String fr;
    private String en;
    
    public LocaleBean() {
    }
    
    public LocaleBean(String fr, String en) {
        this.setFr(fr);
        this.setEn(en);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }
}
