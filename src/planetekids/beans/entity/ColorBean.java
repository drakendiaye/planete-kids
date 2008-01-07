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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;
import javax.persistence.Transient;

@Entity
@NamedQuery(name = "getColors", query = "select o FROM ColorBean o")
public class ColorBean implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL, optional=false)
    private LocaleBean name;
    
    @OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL, optional=false)
    private LocaleBean description;
    
    @OneToMany(mappedBy="color", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<ProductBean> products = new HashSet<ProductBean>();
    
    private String image_large;
    private String image_medium;
    private String image_small;
    
    public ColorBean() {
        
    }
    
    public ColorBean(LocaleBean name, LocaleBean description, String image_large, String image_medium, String image_small) {
        this.setName(name);
        this.setDescription(description);
        this.setImage_large(image_large);
        this.setImage_medium(image_medium);
        this.setImage_small(image_small);
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName(String locale) {
        if (locale.equals("fr"))
            return name.getFr();
        else
            return name.getEn();
    }
    
    public LocaleBean getName() {
        return name;
    }
    
    public void setName(LocaleBean name) {
        this.name = name;
    }
    
    public String getDescription(String locale) {
        if (locale.equals("fr"))
            return description.getFr();
        else
            return description.getEn();
    }
    
    public LocaleBean getDescription() {
        return description;
    }
    
    public void setDescription(LocaleBean description) {
        this.description = description;
    }
    
    public Set<ProductBean> getProducts() {
        return products;
    }
    
    public void setProducts(Set<ProductBean> products) {
        this.products = products;
    }
    
    public String getImage_large() {
        return image_large;
    }
    
    public void setImage_large(String image_large) {
        this.image_large = image_large;
    }
    
    public String getImage_medium() {
        return image_medium;
    }
    
    public void setImage_medium(String image_medium) {
        this.image_medium = image_medium;
    }
    
    public String getImage_small() {
        return image_small;
    }
    
    public void setImage_small(String image_small) {
        this.image_small = image_small;
    }
    
    @Transient
    public boolean removing = false;

    @PreRemove
    public void cleanAssociations() {
        removing = true;
    }
}
