/**
 * eCommerce Application Sample for J2EE Training
 * Implementation of EcomAdminBean
 * @author Fabienne Boyer - Didier Donsez
 * may 2006
 */

package planetekids.beans.stateful;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import planetekids.beans.entity.CategoryBean;
import planetekids.beans.entity.ColorBean;
import planetekids.beans.entity.LabelBean;
import planetekids.beans.entity.LocaleBean;
import planetekids.beans.entity.ProductBean;

@Stateful
@Remote(AdminRemote.class)
public class AdminBean implements AdminRemote{
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public List<LabelBean> getLabels() throws Exception {
        return entityManager.createNamedQuery("getLabels").getResultList();
    }
    
    public LabelBean getLabel(int id) throws Exception {
        return entityManager.find(LabelBean.class, id);
    }
    
    public void setLabelNameFr(int id, String name) throws Exception {
        LabelBean label = entityManager.find(LabelBean.class, id);
        label.getName().setFr(name);
    }
    
    public void setLabelNameEn(int id, String name) throws Exception {
        LabelBean label = entityManager.find(LabelBean.class, id);
        label.getName().setEn(name);
    }
    
    public void setLabelSite(int id, String site) throws Exception {
        LabelBean label = entityManager.find(LabelBean.class, id);
        label.setSite(site);
    }
    
    public void setLabelImageLarge(int id, String image) throws Exception {
        LabelBean label = entityManager.find(LabelBean.class, id);
        label.setImage_large(image);
    }
    
    public void setLabelImageMedium(int id, String image) throws Exception {
        LabelBean label = entityManager.find(LabelBean.class, id);
        label.setImage_medium(image);
    }
    
    public void setLabelImageSmall(int id, String image) throws Exception {
        LabelBean label = entityManager.find(LabelBean.class, id);
        label.setImage_small(image);
    }
    
    public void createLabel(String name_fr, String name_en, String site, String image_large, String image_medium, String image_small) throws Exception {
        LabelBean label = new LabelBean(new LocaleBean(name_fr, name_en), site, image_large, image_medium, image_small);
        entityManager.persist(label);
    }
    
    public List<ColorBean> getColors() throws Exception {
        return entityManager.createNamedQuery("getColors").getResultList();
    }
    
    public ColorBean getColor(int id) throws Exception {
        return entityManager.find(ColorBean.class, id);
    }
    
    public void setColorNameFr(int id, String name) throws Exception {
        ColorBean color = entityManager.find(ColorBean.class, id);
        color.getName().setFr(name);
    }
    
    public void setColorNameEn(int id, String name) throws Exception {
        ColorBean color = entityManager.find(ColorBean.class, id);
        color.getName().setEn(name);
    }
    
    public void setColorImageLarge(int id, String image) throws Exception {
        ColorBean color = entityManager.find(ColorBean.class, id);
        color.setImage_large(image);
    }
    
    public void setColorImageMedium(int id, String image) throws Exception {
        ColorBean color = entityManager.find(ColorBean.class, id);
        color.setImage_medium(image);
    }
    
    public void setColorImageSmall(int id, String image) throws Exception {
        ColorBean color = entityManager.find(ColorBean.class, id);
        color.setImage_small(image);
    }
    
    public void createColor(String name_fr, String name_en, String image_large, String image_medium, String image_small) throws Exception {
        ColorBean color = new ColorBean(new LocaleBean(name_fr, name_en), image_large, image_medium, image_small);
        entityManager.persist(color);
    }
    
    public List<CategoryBean> getCategories() throws Exception {
        return entityManager.createNamedQuery("getCategories").getResultList();
    }
    
    public CategoryBean getCategory(int id) throws Exception {
        return entityManager.find(CategoryBean.class, id);
    }
    
    public void setCategoryNameFr(int id, String name) throws Exception {
        CategoryBean category = entityManager.find(CategoryBean.class, id);
        category.getName().setFr(name);
    }
    
    public void setCategoryNameEn(int id, String name) throws Exception {
        CategoryBean category = entityManager.find(CategoryBean.class, id);
        category.getName().setEn(name);
    }
    
    public void setCategoryImageLarge(int id, String image) throws Exception {
        CategoryBean category = entityManager.find(CategoryBean.class, id);
        category.setImage_large(image);
    }
    
    public void setCategoryImageMedium(int id, String image) throws Exception {
        CategoryBean category = entityManager.find(CategoryBean.class, id);
        category.setImage_medium(image);
    }
    
    public void setCategoryImageSmall(int id, String image) throws Exception {
        CategoryBean category = entityManager.find(CategoryBean.class, id);
        category.setImage_small(image);
    }
    
    public void createCategory(String name_fr, String name_en, String image_large, String image_medium, String image_small) throws Exception {
        CategoryBean category = new CategoryBean(new LocaleBean(name_fr, name_en), image_large, image_medium, image_small);
        entityManager.persist(category);
    }
    
    public List<ProductBean> getProducts() throws Exception {
        return entityManager.createNamedQuery("getProducts").getResultList();
    }
    
    public List<ProductBean> getProductsByCategory(int category_id) throws Exception {
        Query query = entityManager.createNamedQuery("getProductsByCategory");
        query.setParameter("category", entityManager.find(CategoryBean.class, category_id));
        return query.getResultList();
    }
    
    public List<ProductBean> getProductsByColor(int color_id) throws Exception {
        Query query = entityManager.createNamedQuery("getProductsByColor");
        query.setParameter("color", entityManager.find(ColorBean.class, color_id));
        return query.getResultList();
    }
    
    public List<ProductBean> getProductsByLabel(int label_id) throws Exception {
        Query query = entityManager.createNamedQuery("getProductsByLabel");
        query.setParameter("label", entityManager.find(LabelBean.class, label_id));
        return query.getResultList();
    }
    
    public List<ProductBean> getProductsByFilter(List<Integer> category_ids, List<Integer> color_ids, List<Integer> label_ids, boolean and) throws Exception {
        Iterator<Integer> iterator;
        Set<ProductBean> products = new HashSet<ProductBean>();
        
        iterator = category_ids.iterator();
        while(iterator.hasNext()) {
            int category_id = iterator.next().intValue();
            products.addAll(this.getProductsByCategory(category_id));
        }
        
        iterator = color_ids.iterator();
        while(iterator.hasNext()) {
            int color_id = iterator.next().intValue();
            products.addAll(this.getProductsByColor(color_id));
        }
        
        iterator = label_ids.iterator();
        while(iterator.hasNext()) {
            int label_id = iterator.next().intValue();
            products.addAll(this.getProductsByLabel(label_id));
        }
        
        ArrayList<ProductBean> result = new ArrayList<ProductBean>();
        if(!and) {
            result.addAll(products);
        } else {
            Iterator<ProductBean> iterator2 = products.iterator();
            while(iterator2.hasNext()) {
                ProductBean product = iterator2.next();
                if((category_ids.size() == 0 || category_ids.contains(new Integer(product.getCategory().getId()))) &&
                   (color_ids.size() == 0 || color_ids.contains(new Integer(product.getColor().getId()))) &&
                   (label_ids.size() == 0 || label_ids.contains(new Integer(product.getLabel().getId())))) {
                    result.add(product);
                }
            }
        }
        
        return result;
    }
    
    public ProductBean getProduct(int id) throws Exception {
        return entityManager.find(ProductBean.class, id);
    }
    
    public void setProductNameFr(int id, String name) throws Exception {
        ProductBean product = entityManager.find(ProductBean.class, id);
        product.getName().setFr(name);
    }
    
    public void setProductNameEn(int id, String name) throws Exception {
        ProductBean product = entityManager.find(ProductBean.class, id);
        product.getName().setEn(name);
    }
    
    public void setProductDescriptionFr(int id, String description) throws Exception {
        ProductBean product = entityManager.find(ProductBean.class, id);
        product.getDescription().setFr(description);
    }
    
    public void setProductDescriptionEn(int id, String description) throws Exception {
        ProductBean product = entityManager.find(ProductBean.class, id);
        product.getDescription().setEn(description);
    }
    
    public void setProductCategory(int id, int category_id) throws Exception {
        ProductBean product = entityManager.find(ProductBean.class, id);
        CategoryBean category = entityManager.find(CategoryBean.class, category_id);
        product.setCategory(category);
    }
    
    public void setProductColor(int id, int color_id) throws Exception {
        ProductBean product = entityManager.find(ProductBean.class, id);
        ColorBean color = entityManager.find(ColorBean.class, color_id);
        product.setColor(color);
    }
    
    public void setProductLabel(int id, int label_id) throws Exception {
        ProductBean product = entityManager.find(ProductBean.class, id);
        LabelBean label = entityManager.find(LabelBean.class, label_id);
        product.setLabel(label);
    }
    
    public void setProductPrice(int id, float price) throws Exception {
        ProductBean product = entityManager.find(ProductBean.class, id);
        product.setPrice(price);
    }
    
    public void setProductStock(int id, int stock) throws Exception {
        ProductBean product = entityManager.find(ProductBean.class, id);
        product.setStock(stock);
    }
    
    public void setProductImageLarge(int id, String image) throws Exception {
        ProductBean product = entityManager.find(ProductBean.class, id);
        product.setImage_large(image);
    }
    
    public void setProductImageMedium(int id, String image) throws Exception {
        ProductBean product = entityManager.find(ProductBean.class, id);
        product.setImage_medium(image);
    }
    
    public void setProductImageSmall(int id, String image) throws Exception {
        ProductBean product = entityManager.find(ProductBean.class, id);
        product.setImage_small(image);
    }
    
    public void createProduct(String name_fr, String name_en, String description_fr, String description_en, int category_id, int color_id, int label_id, float price, int stock, String image_large, String image_medium, String image_small) throws Exception {
        CategoryBean category = entityManager.find(CategoryBean.class, category_id);
        ColorBean color = entityManager.find(ColorBean.class, color_id);
        LabelBean label = entityManager.find(LabelBean.class, label_id);
        ProductBean product = new ProductBean(new LocaleBean(name_fr, name_en), new LocaleBean(description_fr, description_en), category, color, label, price, stock, image_large, image_medium, image_small);
        entityManager.persist(product);
    }
}
