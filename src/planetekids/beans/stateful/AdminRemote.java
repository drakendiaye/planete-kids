/**
 * eCommerce Application Sample for J2EE Training
 * Remote interface for the EcomAdmin bean
 * EJB3.0
 * @author Fabienne Boyer - Didier Donsez - may 2006
 */

package planetekids.beans.stateful;

import java.util.List;
import planetekids.beans.entity.AccountBean;
import planetekids.beans.entity.CategoryBean;
import planetekids.beans.entity.ColorBean;
import planetekids.beans.entity.LabelBean;
import planetekids.beans.entity.ProductBean;
import planetekids.beans.entity.QuestionBean;

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
    public List<ProductBean> getProductsByCategory(int category_id) throws Exception;
    public List<ProductBean> getProductsByColor(int color_id) throws Exception;
    public List<ProductBean> getProductsByLabel(int label_id) throws Exception;
    public List<ProductBean> getProductsByFilter(List<Integer> category_ids, List<Integer> color_ids, List<Integer> label_ids, boolean and) throws Exception;
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
    
    public int createQuestionnaire (String nameFr, String nameEn, String descFr, String descEn) throws Exception;
    public int createQuestion (int questionnaireId, String questionNameFr, String questionNameEn, QuestionBean.Pattern pattern, int order) throws Exception;
    public int createAnswer (int questionId, String answerNameFr, String answerNameEn, Boolean commentable, int order) throws Exception;

    public String createAccount (String email, String password, String firstName, String lastName, String addressLine1, String addressLine2, String addressLine3, int zipCode, String city, String phoneNumber, String faxNumber) throws Exception;
    public AccountBean getAccount (String email) throws Exception;
    public void setAccountPassword (String email, String password) throws Exception;
    public void setAccountFirstName (String email, String firstName) throws Exception;
    public void setAccountLastName (String email, String lastName) throws Exception;
    public void setAddressLine1 (String email, String addressLine1) throws Exception;
    public void setAddressLine2 (String email, String addressLine2) throws Exception;
    public void setAddressLine3 (String email, String addressLine3) throws Exception;
    public void setZipCode (String email, int zipCode) throws Exception;
    public void setCity (String email, String city) throws Exception;
    public void setPhoneNumber (String email, String phoneNumber) throws Exception;
    public void setFaxNumber (String email, String faxNumber) throws Exception;
}
