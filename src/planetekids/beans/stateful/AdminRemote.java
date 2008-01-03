/**
 * eCommerce Application Sample for J2EE Training
 * Remote interface for the EcomAdmin bean
 * EJB3.0
 * @author Fabienne Boyer - Didier Donsez - may 2006
 */

package planetekids.beans.stateful;

import java.util.List;
import planetekids.beans.entity.CategoryBean;
import planetekids.beans.entity.ColorBean;
import planetekids.beans.entity.LabelBean;
import planetekids.beans.entity.ProductBean;

public interface AdminRemote {
    public List<LabelBean> getLabels() throws Exception;
    public LabelBean getLabel(int id) throws Exception;
    public void setLabelNameFr(int id, String name) throws Exception;
    public void setLabelNameEn(int id, String name) throws Exception;
    public void setLabelSite(int id, String site) throws Exception;
    public void setLabelImageLarge(int id, String image) throws Exception;
    public void setLabelImageMedium(int id, String image) throws Exception;
    public void setLabelImageSmall(int id, String image) throws Exception;
    public void createLabel(String name_fr, String name_en, String site, String image_large, String image_medium, String image_small) throws Exception;
    
    public List<ColorBean> getColors() throws Exception;
    public ColorBean getColor(int id) throws Exception;
    public void setColorNameFr(int id, String name) throws Exception;
    public void setColorNameEn(int id, String name) throws Exception;
    public void setColorImageLarge(int id, String image) throws Exception;
    public void setColorImageMedium(int id, String image) throws Exception;
    public void setColorImageSmall(int id, String image) throws Exception;
    public void createColor(String name_fr, String name_en, String image_large, String image_medium, String image_small) throws Exception;
    
    public List<CategoryBean> getCategories() throws Exception;
    public CategoryBean getCategory(int id) throws Exception;
    public void setCategoryNameFr(int id, String name) throws Exception;
    public void setCategoryNameEn(int id, String name) throws Exception;
    public void setCategoryImageLarge(int id, String image) throws Exception;
    public void setCategoryImageMedium(int id, String image) throws Exception;
    public void setCategoryImageSmall(int id, String image) throws Exception;
    public void createCategory(String name_fr, String name_en, String image_large, String image_medium, String image_small) throws Exception;
    
    public List<ProductBean> getProducts() throws Exception;
    public ProductBean getProduct(int id) throws Exception;
    public void setProductNameFr(int id, String name) throws Exception;
    public void setProductNameEn(int id, String name) throws Exception;
    public void setProductDescriptionFr(int id, String description) throws Exception;
    public void setProductDescriptionEn(int id, String description) throws Exception;
    public void setProductCategory(int id, int category_id) throws Exception;
    public void setProductColor(int id, int color_id) throws Exception;
    public void setProductLabel(int id, int label_id) throws Exception;
    public void setProductPrice(int id, float price) throws Exception;
    public void setProductStock(int id, int stock) throws Exception;
    public void setProductImageLarge(int id, String image) throws Exception;
    public void setProductImageMedium(int id, String image) throws Exception;
    public void setProductImageSmall(int id, String image) throws Exception;
    public void createProduct(String name_fr, String name_en, String description_fr, String description_en, int category_id, int color_id, int label_id, float price, int stock, String image_large, String image_medium, String image_small) throws Exception;
}
