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
@NamedQueries( { @NamedQuery(name = "getProducts", query = "select o FROM ProductBean o ORDER BY id")
/*@NamedQuery(name="getProductsByAges", query = "select o FROM ProductBean o WHERE age_id= :age_id ORDER BY id"),
 @NamedQuery(name="getProductsByColors", query = "select o FROM ProductBean o WHERE color_id= :color_id ORDER BY id"),
 @NamedQuery(name="getProductsByCategories", query = "select o FROM ProductBean o WHERE category_id= :category_id ORDER BY id"),
 @NamedQuery(name="getProductsByLabels", query = "select o FROM ProductBean o WHERE label_id= :label_id ORDER BY id")
 */
})
public class ProductBean implements java.io.Serializable, java.lang.Comparable {

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

    @ManyToOne(optional = false)
    private AgeBean age;

    private float price;
    private int stock;
    private String image_large;
    private String image_medium;
    private String image_small;

    public ProductBean() {

    }

    public ProductBean(LocaleBean name, LocaleBean description, CategoryBean category, ColorBean color, LabelBean label, AgeBean age,
	    float price, int stock, String image_large, String image_medium, String image_small) {
	this();
	this.setName(name);
	this.setDescription(description);
	this.setCategory(category);
	this.setColor(color);
	this.setLabel(label);
	this.setAge(age);
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

    public AgeBean getAge() {
	return age;
    }

    public void setAge(AgeBean age) {
	this.age = age;
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
	if (!category.removing)
	    category.getProducts().remove(this);
	if (!color.removing)
	    color.getProducts().remove(this);
	if (!label.removing)
	    label.getProducts().remove(this);
	if (!age.removing)
	    age.getProducts().remove(this);
	// System.out.println("removing product id : " +this.getId());
    }

    public int compareTo(Object o) {
	int nombre1 = ((ProductBean) o).getId();
	int nombre2 = this.getId();
	if (nombre1 > nombre2)
	    return -1;
	else if (nombre1 == nombre2)
	    return 0;
	else
	    return 1;
    }

    /*
     * @PostRemove public void toto(){ System.out.println("Product id : " +
     * this.getId() + " removed"); }
     */
}
