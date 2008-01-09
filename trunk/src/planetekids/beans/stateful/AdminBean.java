/**
 * eCommerce Application Sample for J2EE Training
 * Implementation of EcomAdminBean
 * @author Fabienne Boyer - Didier Donsez
 * may 2006
 */

package planetekids.beans.stateful;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import planetekids.beans.entity.AccountBean;
import planetekids.beans.entity.AgeBean;
import planetekids.beans.entity.AnswerBean;
import planetekids.beans.entity.CategoryBean;
import planetekids.beans.entity.ColorBean;
import planetekids.beans.entity.CommandBean;
import planetekids.beans.entity.CommandLineBean;
import planetekids.beans.entity.LabelBean;
import planetekids.beans.entity.LocaleBean;
import planetekids.beans.entity.ProductBean;
import planetekids.beans.entity.QuestionBean;
import planetekids.beans.entity.QuestionnaireBean;

@Stateful
@Remote(AdminRemote.class)
public class AdminBean implements AdminRemote  {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    /*------------------------------------------------------------------*/
    /*					Creation des marques							*/
    /*------------------------------------------------------------------*/
    
    public List<LabelBean> getLabels() throws Exception {
        return entityManager.createNamedQuery("getLabels").getResultList();
    }
    
    public LabelBean getLabel(int id) throws Exception {
        return entityManager.find(LabelBean.class, id);
    }
    
    public void setLabelName(int id, String name) throws Exception {
        LabelBean label = getLabel(id);
        label.setName(name);
    }

    
    public void setLabelDescriptionFr(int id, String description) throws Exception {
        LabelBean label = getLabel(id);
        label.getDescription().setFr(description);
    }
    
    public void setLabelDescriptionEn(int id, String description) throws Exception {
        LabelBean label = getLabel(id);
        label.getDescription().setEn(description);
    }
    
    public void setLabelSite(int id, String site) throws Exception {
        LabelBean label = getLabel(id);
        label.setSite(site);
    }
    
    public void setLabelImageLarge(int id, String image) throws Exception {
        LabelBean label = getLabel(id);
        label.setImage_large(image);
    }
    
    public void setLabelImageMedium(int id, String image) throws Exception {
        LabelBean label = getLabel(id);
        label.setImage_medium(image);
    }
    
    public void setLabelImageSmall(int id, String image) throws Exception {
        LabelBean label = getLabel(id);
        label.setImage_small(image);
    }
    
    public int createLabel(String name, String description_fr, String description_en, String site, String image_large, String image_medium, String image_small) throws Exception {
        LabelBean label = new LabelBean(name, new LocaleBean(description_fr, description_en), site, image_large, image_medium, image_small);
        entityManager.persist(label);
        return label.getId();
    }
    
    public void deleteLabel(int id) throws Exception {
        LabelBean label = getLabel(id);
        entityManager.refresh(label);
        entityManager.remove(label);
    }
    
    public void deleteLabels() throws Exception {
	List<LabelBean> labels = getLabels();
	if (labels != null) {
	    Iterator iterator = labels.iterator();
	    while (iterator.hasNext()) {
		LabelBean label = (LabelBean) iterator.next();
		deleteLabel(label.getId());
	    }
	}
    }

    
    /*------------------------------------------------------------------*/
    /*					Creation des couleurs							*/
    /*------------------------------------------------------------------*/
    
    
    public List<ColorBean> getColors() throws Exception {
        return entityManager.createNamedQuery("getColors").getResultList();
    }
    
    public ColorBean getColor(int id) throws Exception {
        return entityManager.find(ColorBean.class, id);
    }
    
    public void setColorNameFr(int id, String name) throws Exception {
        ColorBean color = getColor(id);
        color.getName().setFr(name);
    }
    
    public void setColorNameEn(int id, String name) throws Exception {
        ColorBean color = getColor(id);
        color.getName().setEn(name);
    }
    
    public void setColorDescriptionFr(int id, String description) throws Exception {
        ColorBean color = getColor(id);
        color.getDescription().setFr(description);
    }
    
    public void setColorDescriptionEn(int id, String description) throws Exception {
        ColorBean color = getColor(id);
        color.getDescription().setEn(description);
    }
    
    public void setColorImageLarge(int id, String image) throws Exception {
        ColorBean color = getColor(id);
        color.setImage_large(image);
    }
    
    public void setColorImageMedium(int id, String image) throws Exception {
        ColorBean color = getColor(id);
        color.setImage_medium(image);
    }
    
    public void setColorImageSmall(int id, String image) throws Exception {
        ColorBean color = getColor(id);
        color.setImage_small(image);
    }
    
    public int createColor(String name_fr, String name_en, String description_fr, String description_en, String image_large, String image_medium, String image_small) throws Exception {
        ColorBean color = new ColorBean(new LocaleBean(name_fr, name_en), new LocaleBean(description_fr, description_en), image_large, image_medium, image_small);
        entityManager.persist(color);
        return color.getId();
    }
    
    public void deleteColor(int id) throws Exception {
        ColorBean color = getColor(id);
        entityManager.refresh(color);
        entityManager.remove(color);
    }
    
    public void deleteColors() throws Exception {
	List<ColorBean> colors = getColors();
	if (colors != null) {
	    Iterator iterator = colors.iterator();
	    while (iterator.hasNext()) {
		ColorBean color = (ColorBean) iterator.next();
		deleteColor(color.getId());
	    }
	}
    }
    
    
    /*------------------------------------------------------------------*/
    /*					Creation des categories							*/
    /*------------------------------------------------------------------*/

    public List<CategoryBean> getCategories() throws Exception {
        return entityManager.createNamedQuery("getCategories").getResultList();
    }
    
    public CategoryBean getCategory(int id) throws Exception {
        return entityManager.find(CategoryBean.class, id);
    }
    
    public void setCategoryNameFr(int id, String name) throws Exception {
        CategoryBean category = getCategory(id);
        category.getName().setFr(name);
    }
    
    public void setCategoryNameEn(int id, String name) throws Exception {
        CategoryBean category = getCategory(id);
        category.getName().setEn(name);
    }
    
    public void setCategoryDescriptionFr(int id, String description) throws Exception {
        CategoryBean category = getCategory(id);
        category.getDescription().setFr(description);
    }
    
    public void setCategoryDescriptionEn(int id, String description) throws Exception {
        CategoryBean category = getCategory(id);
        category.getDescription().setEn(description);
    }
    
    public void setCategoryImageLarge(int id, String image) throws Exception {
        CategoryBean category = getCategory(id);
        category.setImage_large(image);
    }
    
    public void setCategoryImageMedium(int id, String image) throws Exception {
        CategoryBean category = getCategory(id);
        category.setImage_medium(image);
    }
    
    public void setCategoryImageSmall(int id, String image) throws Exception {
        CategoryBean category = getCategory(id);
        category.setImage_small(image);
    }
    
    public int createCategory(String name_fr, String name_en, String description_fr, String description_en, String image_large, String image_medium, String image_small) throws Exception {
        CategoryBean category = new CategoryBean(new LocaleBean(name_fr, name_en), new LocaleBean(description_fr, description_en), image_large, image_medium, image_small);
	entityManager.persist(category);
        return category.getId();
    }
    
    public void deleteCategory(int id) throws Exception {
        CategoryBean category = getCategory(id);
        entityManager.refresh(category);
        entityManager.remove(category);
    }
    
    public void deleteCategories() throws Exception {
	List<CategoryBean> categories = getCategories();
	if (categories != null) {
	    Iterator iterator = categories.iterator();
	    while (iterator.hasNext()) {
		CategoryBean category = (CategoryBean) iterator.next();
		deleteCategory(category.getId());
	    }
	}
    }
    
    /*------------------------------------------------------------------*/
    /*					Creation des Ages							*/
    /*------------------------------------------------------------------*/
    
    public List<AgeBean> getAges() throws Exception {
        return entityManager.createNamedQuery("getAges").getResultList();
    }
    
    public AgeBean getAge(int id) throws Exception {
        return entityManager.find(AgeBean.class, id);
    }
    
    public void setAgeNameFr(int id, String name) throws Exception {
        AgeBean age = this.getAge(id);
        age.getName().setFr(name);
    }
    
    public void setAgeNameEn(int id, String name) throws Exception {
        AgeBean age = this.getAge(id);
        age.getName().setEn(name);
    }
    
    public void setAgeDescriptionFr(int id, String description) throws Exception {
        AgeBean age = this.getAge(id);
        age.getDescription().setFr(description);
    }
    
    public void setAgeDescriptionEn(int id, String description) throws Exception {
        AgeBean age = getAge(id);
        age.getDescription().setEn(description);
    }
    
    public int createAge(String name_fr, String name_en, String description_fr, String description_en) throws Exception {
        AgeBean age = new AgeBean(new LocaleBean(name_fr, name_en), new LocaleBean(description_fr, description_en));
        entityManager.persist(age);
        return age.getId();
    }
    
    public void deleteAge(int id) throws Exception {
        AgeBean age = getAge(id);
        entityManager.refresh(age);
        entityManager.remove(age);
    }
    
    public void deleteAges() throws Exception {
	List<AgeBean> ages = this.getAges();
	if (ages != null) {
	    Iterator iterator = ages.iterator();
	    while (iterator.hasNext()) {
		AgeBean age = (AgeBean) iterator.next();
		deleteAge(age.getId());
	    }
	}
    }
    
    /*------------------------------------------------------------------*/
    /*					Creation des produits							*/
    /*------------------------------------------------------------------*/

    public List<ProductBean> getProducts() throws Exception {
        return entityManager.createNamedQuery("getProducts").getResultList();
    }
    
    public List<ProductBean> getProductsByCategory(int category_id) throws Exception {
        List<ProductBean> products = new ArrayList<ProductBean>();
        products.addAll(getCategory(category_id).getProducts());
        return products;
    }
    
    public List<ProductBean> getProductsByColor(int color_id) throws Exception {
        List<ProductBean> products = new ArrayList<ProductBean>();
        products.addAll(getColor(color_id).getProducts());
        return products;
    }
    
    public List<ProductBean> getProductsByLabel(int label_id) throws Exception {
        List<ProductBean> products = new ArrayList<ProductBean>();
        products.addAll(getLabel(label_id).getProducts());
        return products;
    }
    
    public List<ProductBean> getProductsByAge(int age_id) throws Exception {
        List<ProductBean> products = new ArrayList<ProductBean>();
        products.addAll(getAge(age_id).getProducts());
        return products;
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
        ProductBean product = getProduct(id);
        product.getName().setFr(name);
    }
    
    public void setProductNameEn(int id, String name) throws Exception {
        ProductBean product = getProduct(id);
        product.getName().setEn(name);
    }
    
    public void setProductDescriptionFr(int id, String description) throws Exception {
        ProductBean product = getProduct(id);
        product.getDescription().setFr(description);
    }
    
    public void setProductDescriptionEn(int id, String description) throws Exception {
        ProductBean product = getProduct(id);
        product.getDescription().setEn(description);
    }
    
    public void setProductCategory(int id, int category_id) throws Exception {
        ProductBean product = getProduct(id);
        CategoryBean category = getCategory(category_id);
        product.setCategory(category);
    }
    
    public void setProductColor(int id, int color_id) throws Exception {
        ProductBean product = getProduct(id);
        ColorBean color = getColor(color_id);
        product.setColor(color);
    }
    
    public void setProductLabel(int id, int label_id) throws Exception {
        ProductBean product = getProduct(id);
        LabelBean label = getLabel(label_id);
        product.setLabel(label);
    }
    
    public void setProductAge(int id, int age_id) throws Exception {
	ProductBean product = getProduct(id);
	AgeBean age = this.getAge(age_id);
	product.setAge(age);
    }

    public void setProductPrice(int id, float price) throws Exception {
        ProductBean product = getProduct(id);
        product.setPrice(price);
    }
    
    public void setProductStock(int id, int stock) throws Exception {
        ProductBean product = getProduct(id);
        product.setStock(stock);
    }
    
    public void setProductImageLarge(int id, String image) throws Exception {
        ProductBean product = getProduct(id);
        product.setImage_large(image);
    }
    
    public void setProductImageMedium(int id, String image) throws Exception {
        ProductBean product = getProduct(id);
        product.setImage_medium(image);
    }
    
    public void setProductImageSmall(int id, String image) throws Exception {
        ProductBean product = getProduct(id);
        product.setImage_small(image);
    }
    
    public int createProduct(String name_fr, String name_en, String description_fr, String description_en, int category_id, int color_id, int label_id, int age_id, float price, int stock, String image_large, String image_medium, String image_small) throws Exception {
        CategoryBean category = this.getCategory(category_id);
        ColorBean color = this.getColor(color_id);
        LabelBean label = this.getLabel(label_id);
        AgeBean age = this.getAge(age_id); 
        ProductBean product = new ProductBean(new LocaleBean(name_fr, name_en), new LocaleBean(description_fr, description_en), category, color, label, age, price, stock, image_large, image_medium, image_small);
        entityManager.persist(product);
        return product.getId();
    }
    
    public void deleteProduct (int id) throws Exception {
        ProductBean product = getProduct(id);
        entityManager.refresh(product);
        entityManager.remove(product);
    }
    
    public void deleteProducts() throws Exception {
	List<ProductBean> products = getProducts();
	if (products != null) {
	    Iterator iterator = products.iterator();
	    while (iterator.hasNext()) {
		ProductBean product = (ProductBean) iterator.next();
		deleteProduct(product.getId());
	    }
	}
    }
    
    /*------------------------------------------------------------------*/
    /*					Creation des questionnaires						*/
    /*------------------------------------------------------------------*/
    
    
    public int createQuestionnaire(String nameFr, String nameEn, String descFr, String descEn) throws Exception {
        QuestionnaireBean questionnaire = new QuestionnaireBean(new LocaleBean(nameFr, nameEn), new LocaleBean(descFr, descEn));

        entityManager.persist(questionnaire);
        
        return questionnaire.getId();
    }
    
    public int createQuestion(int questionnaireId, String questionNameFr, String questionNameEn, QuestionBean.Pattern pattern, int order)
    throws Exception {
        QuestionnaireBean questionnaire = getQuestionnaire(questionnaireId);
        
        QuestionBean question = new QuestionBean(questionnaire, new LocaleBean(questionNameFr, questionNameEn), pattern, order);
        
        entityManager.persist(question);
        
        return question.getId();
    }
    
    public int createAnswer(int questionId, String answerNameFr, String answerNameEn, Boolean commentable, int order) throws Exception {
        QuestionBean question = entityManager.find(QuestionBean.class, questionId);
        
        AnswerBean answer = new AnswerBean(question, new LocaleBean(answerNameFr, answerNameEn), commentable, order);
        
        entityManager.persist(answer);
        
        return answer.getId();
    }
    
    public List<QuestionnaireBean> getQuestionnaires() throws Exception {
        return entityManager.createNamedQuery("getQuestionnaires").getResultList();
    }
    
    public QuestionnaireBean getQuestionnaire(int questionnaire_id) throws Exception {
        return entityManager.find(QuestionnaireBean.class, questionnaire_id);
    }
    
    public void deleteQuestionnaire(int questionnaire_id) throws Exception {
        QuestionnaireBean questionnaire = getQuestionnaire(questionnaire_id);
        entityManager.refresh(questionnaire);
	entityManager.remove(questionnaire);
    }
    
    public void deleteQuestionnaires() throws Exception {
	List<QuestionnaireBean> questionnaires = getQuestionnaires();
	if (questionnaires != null) {
	    Iterator iterator = questionnaires.iterator();
	    while (iterator.hasNext()) {
		QuestionnaireBean questionnaire = (QuestionnaireBean) iterator.next();
		deleteQuestionnaire(questionnaire.getId());
	    }
	}
    }
    
    /* Création des account */
    
    public String createAccount(String email, String password, String firstName, String lastName, String addressLine1, String addressLine2,
            String addressLine3, int zipCode, String city, String phoneNumber, String faxNumber) throws Exception {
        AccountBean account = new AccountBean(email);
        account.setPassword(password);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setAddressLine1(addressLine1);
        account.setAddressLine2(addressLine2);
        account.setAddressLine3(addressLine3);
        account.setZipCode(zipCode);
        account.setCity(city);
        account.setPhoneNumber(phoneNumber);
        account.setFaxNumber(faxNumber);
        
        entityManager.persist(account);
        
        return account.getEmailAddress();
    }
    
    public AccountBean getAccount(String email) throws Exception {
        return entityManager.find(AccountBean.class, email);
    }
    
    public List<AccountBean> getAccounts() throws Exception {
        return entityManager.createNamedQuery("getAccounts").getResultList();
    }
    
    public void deleteAccount(String email) throws Exception {
        AccountBean account = getAccount(email);
        entityManager.refresh(account);
        entityManager.remove(account);
    }
    
    public void deleteAccounts() throws Exception {
	List<AccountBean> accounts = getAccounts();
	if (accounts != null) {
	    Iterator iterator = accounts.iterator();
	    while (iterator.hasNext()) {
		AccountBean account = (AccountBean) iterator.next();
		deleteAccount(account.getEmailAddress());
	    }
	}
    }
    
    public void setAccountPassword(String email, String password) throws Exception {
        AccountBean account = getAccount(email);
        account.setPassword(password);
    }
    
    public void setAccountFirstName(String email, String firstName) throws Exception {
        AccountBean account = getAccount(email);
        account.setFirstName(firstName);
    }
    
    public void setAccountLastName(String email, String lastName) throws Exception {
        AccountBean account = getAccount(email);
        account.setLastName(lastName);
    }
    
    public void setAccountAddressLine1(String email, String addressLine1) throws Exception {
        AccountBean account = getAccount(email);
        account.setAddressLine1(addressLine1);
    }
    
    public void setAccountAddressLine2(String email, String addressLine2) throws Exception {
        AccountBean account = getAccount(email);
        account.setAddressLine2(addressLine2);
    }
    
    public void setAccountAddressLine3(String email, String addressLine3) throws Exception {
        AccountBean account = getAccount(email);
        account.setAddressLine3(addressLine3);
    }
    
    public void setAccountZipCode(String email, int zipCode) throws Exception {
        AccountBean account = getAccount(email);
        account.setZipCode(zipCode);
    }
    
    public void setAccountCity(String email, String city) throws Exception {
        AccountBean account = getAccount(email);
        account.setCity(city);
    }
    
    public void setAccountPhoneNumber(String email, String phoneNumber) throws Exception {
        AccountBean account = getAccount(email);
        account.setPhoneNumber(phoneNumber);
    }
    
    public void setAccountFaxNumber(String email, String faxNumber) throws Exception {
        AccountBean account = getAccount(email);
        account.setFaxNumber(faxNumber);
    }

    public int createCommand(String email, Date date, float shipping) throws Exception {
        AccountBean account = getAccount(email);
        CommandBean command = new CommandBean(account, date, shipping);
        entityManager.persist(command);
        return command.getId();
    }

    public CommandBean getCommand(int id) throws Exception {
        return entityManager.find(CommandBean.class, id);
    }

    public List<CommandBean> getCommands() throws Exception {
        return entityManager.createNamedQuery("getCommands").getResultList();
    }
    
    public List<CommandBean> getCommandsByAccount(String email) throws Exception {
        List<CommandBean> commands = new ArrayList<CommandBean>();
        commands.addAll(getAccount(email).getCommands());
        return commands;
    }

    public void deleteCommand(int id) throws Exception {
        CommandBean command = getCommand(id);
        entityManager.refresh(command);
        entityManager.remove(command);
    }

    public void deleteCommands() throws Exception {
        List<CommandBean> commands = getCommands();
	if (commands != null) {
	    Iterator iterator = commands.iterator();
	    while (iterator.hasNext()) {
		CommandBean command = (CommandBean) iterator.next();
		deleteCommand(command.getId());
	    }
	}
    }

    public void setCommandDate(int id, Date date) throws Exception {
        getCommand(id).setDate(date);
    }

    public void setCommandShipping(int id, float shipping) throws Exception {
        getCommand(id).setShipping(shipping);
    }

    public int createCommandLine(int command_id, String name_fr, String name_en, float price, int number) throws Exception {
        CommandBean command = getCommand(command_id);
        CommandLineBean command_line = new CommandLineBean(command, new LocaleBean(name_fr, name_en), price, number);
        entityManager.persist(command_line);
        return command_line.getId();
    }

    public CommandLineBean getCommandLine(int id) throws Exception {
        return entityManager.find(CommandLineBean.class, id);
    }

    public List<CommandLineBean> getCommandLines() throws Exception {
        return entityManager.createNamedQuery("getCommandLines").getResultList();
    }
    
    public List<CommandLineBean> getCommandLinesByCommand(int command_id) throws Exception {
        List<CommandLineBean> command_lines = new ArrayList<CommandLineBean>();
        command_lines.addAll(getCommand(command_id).getCommand_lines());
        return command_lines;
    }

    public void deleteCommandLine(int id) throws Exception {
        CommandLineBean command_line = getCommandLine(id);
        entityManager.refresh(command_line);
        entityManager.remove(command_line);
    }

    public void deleteCommandLines() throws Exception {
        List<CommandLineBean> command_lines = getCommandLines();
	if (command_lines != null) {
	    Iterator iterator = command_lines.iterator();
	    while (iterator.hasNext()) {
		CommandLineBean command_line = (CommandLineBean) iterator.next();
		deleteCommand(command_line.getId());
	    }
	}
    }

    public void setCommandLineNameFr(int id, String name_fr) throws Exception {
        getCommandLine(id).getName().setFr(name_fr);
    }

    public void setCommandLineNameEn(int id, String name_en) throws Exception {
        getCommandLine(id).getName().setEn(name_en);
    }

    public void setCommandLinePrice(int id, float price) throws Exception {
        getCommandLine(id).setPrice(price);
    }

    public void setCommandLineNumber(int id, int number) throws Exception {
        getCommandLine(id).setNumber(number);
    }
    
}
