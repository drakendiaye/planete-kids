package planetekids.beans.stateful;

import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import planetekids.beans.entity.AccountBean;
import planetekids.beans.entity.AgeBean;
import planetekids.beans.entity.CategoryBean;
import planetekids.beans.entity.ColorBean;
import planetekids.beans.entity.CommandBean;
import planetekids.beans.entity.CommandLineBean;
import planetekids.beans.entity.LabelBean;
import planetekids.beans.entity.ProductBean;
import planetekids.beans.entity.QuestionnaireBean;

@Remote
public interface CustomerRemote {
    public void init();
    
    public boolean LogIn(String email, String password) throws Exception;
    public void LogOut() throws Exception;
    
    public void flushCart() throws Exception;
    public int validateCart() throws Exception;
    public float getCartPrice() throws Exception;
    public List<ProductBean> getCartProducts()  throws Exception;
    public void setCartProductNumber(int product_id, int product_number) throws Exception;
    public int getCartProductNumber(int product_id) throws Exception;
    
    public List<QuestionnaireBean> getQuestionnaires() throws Exception;
    public QuestionnaireBean getQuestionnaire(int questionnaire_id) throws Exception;
    public void createResult(int id, String value, String comment) throws Exception;
    
    public List<LabelBean> getLabels() throws Exception;
    public LabelBean getLabel(int id) throws Exception;
    
    public List<ColorBean> getColors() throws Exception;
    public ColorBean getColor(int id) throws Exception;
    
    public List<CategoryBean> getCategories() throws Exception;
    public CategoryBean getCategory(int id) throws Exception;
    
    public List<AgeBean> getAges() throws Exception;
    public AgeBean getAge(int id) throws Exception;
    
    public List<ProductBean> getProducts() throws Exception;
    public List<ProductBean> getProductsByCategory(int category_id) throws Exception;
    public List<ProductBean> getProductsByColor(int color_id) throws Exception;
    public List<ProductBean> getProductsByLabel(int label_id) throws Exception;
    public List<ProductBean> getProductsByFilter(List<Integer> category_ids, List<Integer> color_ids, List<Integer> label_ids, List<Integer> age_ids, boolean and) throws Exception;
    public ProductBean getProduct(int id) throws Exception;
    
    public String createAccount(String email, String password, String firstName, String lastName, String addressLine1, String addressLine2, String addressLine3, int zipCode, String city, String phoneNumber, String faxNumber) throws Exception;
    public AccountBean getAccount() throws Exception;
    public void deleteAccount() throws Exception;
    public void setAccountPassword(String password) throws Exception;
    public void setAccountFirstName(String firstName) throws Exception;
    public void setAccountLastName(String lastName) throws Exception;
    public void setAccountAddressLine1(String addressLine1) throws Exception;
    public void setAccountAddressLine2(String addressLine2) throws Exception;
    public void setAccountAddressLine3(String addressLine3) throws Exception;
    public void setAccountZipCode(int zipCode) throws Exception;
    public void setAccountCity(String city) throws Exception;
    public void setAccountPhoneNumber(String phoneNumber) throws Exception;
    public void setAccountFaxNumber(String faxNumber) throws Exception;
    
    public int createCommand(String email, Date date, float shipping) throws Exception;
    public CommandBean getCommand(int id) throws Exception;
    public List<CommandBean> getCommands() throws Exception;
    public List<CommandBean> getCommandsByAccount(String email) throws Exception;
    
    public int createCommandLine(int command_id, String name_fr, String name_en, float price, int number) throws Exception;
    public CommandLineBean getCommandLine(int id) throws Exception;
    public List<CommandLineBean> getCommandLines() throws Exception;
    public List<CommandLineBean> getCommandLinesByCommand(int command_id) throws Exception;
}
