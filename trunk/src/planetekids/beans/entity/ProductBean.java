package planetekids.beans.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;

@Entity
@NamedQueries({@NamedQuery(name = "getProducts", query = "select o FROM ProductBean o"),
@NamedQuery(name = "getProductsByCategory", query = "select o FROM ProductBean o WHERE o.category = :category"),
@NamedQuery(name = "getProductsByColor", query = "select o FROM ProductBean o WHERE o.color = :color"),
@NamedQuery(name = "getProductsByLabel", query = "select o FROM ProductBean o WHERE o.label = :label")
})
public class ProductBean implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    private LocaleBean name;
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    private LocaleBean description;
    
    @ManyToOne(optional = false)
    private CategoryBean category;
    
    @ManyToOne(optional = false)
    private ColorBean color;
    
    @ManyToOne(optional = false)
    private LabelBean label;
    
    private float price;
    private int stock;
    private String image_large;
    private String image_medium;
    private String image_small;

    public ProductBean() {

    }

    public ProductBean(LocaleBean name, LocaleBean description, CategoryBean category, ColorBean color, LabelBean label, float price, int stock, String image_large, String image_medium, String image_small) {
        this();
        this.setName(name);
        this.setDescription(description);
        this.setCategory(category);
        this.setColor(color);
        this.setLabel(label);
        this.setPrice(price);
        this.setStock(stock);
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
        if (locale.equals("fr")) {
            return name.getFr();
        } else {
            return name.getEn();
        }
    }

    public LocaleBean getName() {
        return name;
    }

    public void setName(LocaleBean name) {
        this.name = name;
    }

    public String getDescription(String locale) {
        if (locale.equals("fr")) {
            return description.getFr();
        } else {
            return description.getEn();
        }
    }

    public LocaleBean getDescription() {
        return description;
    }

    public void setDescription(LocaleBean description) {
        this.description = description;
    }

    public CategoryBean getCategory() {
        return category;
    }

    public void setCategory(CategoryBean category) {
        this.category = category;
    }

    public ColorBean getColor() {
        return color;
    }

    public void setColor(ColorBean color) {
        this.color = color;
    }

    public LabelBean getLabel() {
        return label;
    }

    public void setLabel(LabelBean label) {
        this.label = label;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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

    @PreRemove
    public void cleanAssociations() {
        if (!category.removing) category.getProducts().remove(this);
        if (!color.removing) color.getProducts().remove(this);
        if (!label.removing) label.getProducts().remove(this);
    }
}
