/**
 * eCommerce Application Sample for J2EE Training
 * Remote interface for the EcomAdmin bean
 * EJB3.0
 * @author Fabienne Boyer - Didier Donsez - may 2006
 */

package planetekids.beans.stateful;

import java.util.Date;
import java.util.List;
import planetekids.beans.entity.AccountBean;
import planetekids.beans.entity.AgeBean;
import planetekids.beans.entity.CategoryBean;
import planetekids.beans.entity.ColorBean;
import planetekids.beans.entity.CommandBean;
import planetekids.beans.entity.CommandBean.State;
import planetekids.beans.entity.CommandLineBean;
import planetekids.beans.entity.LabelBean;
import planetekids.beans.entity.ProductBean;
import planetekids.beans.entity.QuestionBean;
import planetekids.beans.entity.QuestionnaireBean;

public interface AdminRemote {
    public List<LabelBean> getLabels() throws Exception;
    public LabelBean getLabel(int id) throws Exception;
    public void setLabelName(int id, String name) throws Exception;
    public void setLabelDescriptionFr(int id, String description) throws Exception;
    public void setLabelDescriptionEn(int id, String description) throws Exception;
    public void setLabelSite(int id, String site) throws Exception;
    public void setLabelImageLarge(int id, String image) throws Exception;
    public void setLabelImageMedium(int id, String image) throws Exception;
    public void setLabelImageSmall(int id, String image) throws Exception;
    public int createLabel(String name, String description_fr, String description_en, String site, String image_large, String image_medium, String image_small) throws Exception;
    public void deleteLabel(int id) throws Exception;
    public void deleteLabels() throws Exception;
    
    public List<ColorBean> getColors() throws Exception;
    public ColorBean getColor(int id) throws Exception;
    public void setColorNameFr(int id, String name) throws Exception;
    public void setColorNameEn(int id, String name) throws Exception;
    public void setColorDescriptionFr(int id, String description) throws Exception;
    public void setColorDescriptionEn(int id, String description) throws Exception;
    public void setColorImageLarge(int id, String image) throws Exception;
    public void setColorImageMedium(int id, String image) throws Exception;
    public void setColorImageSmall(int id, String image) throws Exception;
    public int createColor(String name_fr, String name_en, String description_fr, String description_en, String image_large, String image_medium, String image_small) throws Exception;
    public void deleteColor(int id) throws Exception;
    public void deleteColors() throws Exception;
    
    public List<CategoryBean> getCategories() throws Exception;
    public CategoryBean getCategory(int id) throws Exception;
    public void setCategoryNameFr(int id, String name) throws Exception;
    public void setCategoryNameEn(int id, String name) throws Exception;
    public void setCategoryDescriptionFr(int id, String description) throws Exception;
    public void setCategoryDescriptionEn(int id, String description) throws Exception;
    public void setCategoryImageLarge(int id, String image) throws Exception;
    public void setCategoryImageMedium(int id, String image) throws Exception;
    public void setCategoryImageSmall(int id, String image) throws Exception;
    public int createCategory(String name_fr, String name_en, String description_fr, String description_en, String image_large, String image_medium, String image_small) throws Exception;
    public void deleteCategory(int id) throws Exception;
    public void deleteCategories() throws Exception;
    
    public List<AgeBean> getAges() throws Exception ;    
    public AgeBean getAge(int id) throws Exception ;    
    public void setAgeNameFr(int id, String name) throws Exception ;    
    public void setAgeNameEn(int id, String name) throws Exception ;    
    public void setAgeDescriptionFr(int id, String description) throws Exception ;    
    public void setAgeDescriptionEn(int id, String description) throws Exception ;    
    public int createAge(String name_fr, String name_en, String description_fr, String description_en) throws Exception ;    
    public void deleteAge(int id) throws Exception ;    
    public void deleteAges() throws Exception ;
    
    public List<ProductBean> getProducts() throws Exception;
    public List<ProductBean> getProductsByCategory(int category_id) throws Exception;
    public List<ProductBean> getProductsByColor(int color_id) throws Exception;
    public List<ProductBean> getProductsByLabel(int label_id) throws Exception;
    public List<ProductBean> getProductsByAge(int age_id) throws Exception;
    public List<ProductBean> getProductsByFilter(List<Integer> category_ids, List<Integer> color_ids, List<Integer> label_ids, List<Integer> age_ids, boolean and) throws Exception;
    public ProductBean getProduct(int id) throws Exception;
    public void setProductNameFr(int id, String name) throws Exception;
    public void setProductNameEn(int id, String name) throws Exception;
    public void setProductDescriptionFr(int id, String description) throws Exception;
    public void setProductDescriptionEn(int id, String description) throws Exception;
    public void setProductCategory(int id, int category_id) throws Exception;
    public void setProductColor(int id, int color_id) throws Exception;
    public void setProductLabel(int id, int label_id) throws Exception;
    public void setProductAge(int id, int age_id) throws Exception;
    public void setProductPrice(int id, float price) throws Exception;
    public void setProductStock(int id, int stock) throws Exception;
    public void setProductImageLarge(int id, String image) throws Exception;
    public void setProductImageMedium(int id, String image) throws Exception;
    public void setProductImageSmall(int id, String image) throws Exception;
    public int createProduct(String name_fr, String name_en, String description_fr, String description_en, int category_id, int color_id, int label_id, int age_id, float price, int stock, String image_large, String image_medium, String image_small) throws Exception;
    public void deleteProduct (int id) throws Exception;
    public void deleteProducts() throws Exception;
    
    public QuestionnaireBean getQuestionnaire(int questionnaire_id) throws Exception;
    public int createQuestionnaire(String nameFr, String nameEn, String descFr, String descEn) throws Exception;
    public int createQuestion(int questionnaireId, String questionNameFr, String questionNameEn, QuestionBean.Pattern pattern, int order) throws Exception;
    public int createAnswer(int questionId, String answerNameFr, String answerNameEn, Boolean commentable, int order) throws Exception;
    public void deleteQuestionnaire(int questionnaire_id) throws Exception;
    public void deleteQuestionnaires() throws Exception;
    
    public String createAccount(String email, String password, String firstName, String lastName, String addressLine1, String addressLine2, String addressLine3, int zipCode, String city, String phoneNumber, String faxNumber) throws Exception;
    public AccountBean getAccount(String email) throws Exception;
    public List<AccountBean> getAccounts() throws Exception;
    public void deleteAccount(String email) throws Exception;
    public void deleteAccounts() throws Exception;
    public void setAccountPassword(String email, String password) throws Exception;
    public void setAccountFirstName(String email, String firstName) throws Exception;
    public void setAccountLastName(String email, String lastName) throws Exception;
    public void setAccountAddressLine1(String email, String addressLine1) throws Exception;
    public void setAccountAddressLine2(String email, String addressLine2) throws Exception;
    public void setAccountAddressLine3(String email, String addressLine3) throws Exception;
    public void setAccountZipCode(String email, int zipCode) throws Exception;
    public void setAccountCity(String email, String city) throws Exception;
    public void setAccountPhoneNumber(String email, String phoneNumber) throws Exception;
    public void setAccountFaxNumber(String email, String faxNumber) throws Exception;
    
    public int createCommand(String email, Date date, float shipping) throws Exception;
    public CommandBean getCommand(int id) throws Exception;
    public List<CommandBean> getCommands() throws Exception;
    public List<CommandBean> getCommandsByAccount(String email) throws Exception;
    public void deleteCommand(int id) throws Exception;
    public void deleteCommands() throws Exception;
    public void setCommandDate(int id, Date date) throws Exception;
    public void setCommandShipping(int id, float shipping) throws Exception;
    
    public int createCommandLine(int command_id, String name_fr, String name_en, float price, int number) throws Exception;
    public CommandLineBean getCommandLine(int id) throws Exception;
    public List<CommandLineBean> getCommandLines() throws Exception;
    public List<CommandLineBean> getCommandLinesByCommand(int command_id) throws Exception;
    public void deleteCommandLine(int id) throws Exception;
    public void deleteCommandLines() throws Exception;
    public void setCommandLineNameFr(int id, String name_fr) throws Exception;
    public void setCommandLineNameEn(int id, String name_en) throws Exception;
    public void setCommandLinePrice(int id, float price) throws Exception;
    public void setCommandLineNumber(int id, int number) throws Exception;
    
    public float getCommandTotal(int id) throws Exception;
    public void setCommandState(int id, State state) throws Exception;
    public State getCommandState(int id) throws Exception;
}
