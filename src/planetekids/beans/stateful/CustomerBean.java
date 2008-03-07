package planetekids.beans.stateful;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import planetekids.beans.entity.AccountBean;
import planetekids.beans.entity.AgeBean;
import planetekids.beans.entity.AnswerBean;
import planetekids.beans.entity.CategoryBean;
import planetekids.beans.entity.ColorBean;
import planetekids.beans.entity.CommandBean;
import planetekids.beans.entity.CommandBean.State;
import planetekids.beans.entity.CommandLineBean;
import planetekids.beans.entity.LabelBean;
import planetekids.beans.entity.LocaleBean;
import planetekids.beans.entity.ProductBean;
import planetekids.beans.entity.QuestionnaireBean;
import planetekids.beans.entity.ResultBean;

@Stateful
public class CustomerBean implements CustomerRemote {

	@PersistenceContext
	private EntityManager entityManager;
	private CartRemote cart;
	private String account_id;

	public void init() {

	}
        
        public boolean test() throws Exception {
            return true;
        }

	public boolean LogIn(String account_id, String password) throws Exception {
		AccountBean account = entityManager.find(AccountBean.class, account_id);
		if (account == null || !account.getPassword().equals(password)) {
			return false;
		} else {
			this.account_id = account_id;
			return (true);
		}
	}

	public void LogOut() throws Exception {
		this.account_id = null;
	}

	public void flushCart() throws Exception {
		cart.flushCart();
	}

    public void validateCart(float price) throws Exception {
        int command = createCommand(new Date(System.currentTimeMillis()), price);
        Hashtable<Integer, Integer> cartTable = cart.getHashtable();
        
        Enumeration<Integer> enumeration = cartTable.keys();
        while(enumeration.hasMoreElements()) {
            Integer key = enumeration.nextElement();
            ProductBean product = entityManager.find(ProductBean.class, key.intValue());
            createCommandLine(command, product.getName("fr"), product.getName("en"), product.getPrice(), cartTable.get(key));
            product.setStock(product.getStock()-cartTable.get(key));
        }
        
        cart.validateCart();

    }

	public int createCommand(Date date, float shipping) throws Exception {
		AccountBean account = getAccount();
		CommandBean command = new CommandBean(account, date, shipping);
		entityManager.persist(command);
		return command.getId();
	}

	public CommandBean getCommand(int id) throws Exception {
		return entityManager.find(CommandBean.class, id);
	}

	public int createCommandLine(int command_id, String name_fr, String name_en, float price, int number) throws Exception {
		CommandBean command = getCommand(command_id);
		CommandLineBean command_line = new CommandLineBean(command, new LocaleBean(name_fr, name_en), price, number);
		entityManager.persist(command_line);
		return command_line.getId();
	}

	public float getCartPrice() throws Exception {
		return cart.getCartPrice();
	}

	public List<ProductBean> getCartProducts() throws Exception {
		return cart.getCartProducts();
	}

	public void setCartProductNumber(int product_id, int product_number) throws Exception {
		cart.setCartProductNumber(product_id, product_number);
	}

	public int getCartProductNumber(int product_id) throws Exception {
		return cart.getCartProductNumber(product_id);
	}

	public List<QuestionnaireBean> getQuestionnaires() throws Exception {
		return entityManager.createNamedQuery("getQuestionnaires").getResultList();
	}

	public QuestionnaireBean getQuestionnaire(int questionnaire_id) throws Exception {
		return entityManager.find(QuestionnaireBean.class, questionnaire_id);
	}

	public void createResult(int id, String value, String comment) throws Exception {
		AnswerBean answer = entityManager.find(AnswerBean.class, id);
		if (answer == null) {
			throw new Exception("Cannot find AnswerBean : id=" + String.valueOf(id));
		}
		ResultBean result = new ResultBean(answer, value, comment);
		entityManager.persist(result);
	}

	public List<LabelBean> getLabels() throws Exception {
		return entityManager.createNamedQuery("getLabels").getResultList();
	}

	public LabelBean getLabel(int id) throws Exception {
		return entityManager.find(LabelBean.class, id);
	}

	public List<ColorBean> getColors() throws Exception {
		return entityManager.createNamedQuery("getColors").getResultList();
	}

	public ColorBean getColor(int id) throws Exception {
		return entityManager.find(ColorBean.class, id);
	}

	public List<CategoryBean> getCategories() throws Exception {
		return entityManager.createNamedQuery("getCategories").getResultList();
	}

	public CategoryBean getCategory(int id) throws Exception {
		return entityManager.find(CategoryBean.class, id);
	}

	public List<AgeBean> getAges() throws Exception {
		return entityManager.createNamedQuery("getAges").getResultList();
	}

	public AgeBean getAge(int id) throws Exception {
		return entityManager.find(AgeBean.class, id);
	}

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

	public List<ProductBean> getProductsByFilter(List<Integer> category_ids, List<Integer> color_ids, List<Integer> label_ids, List<Integer> age_ids, boolean and) throws Exception {
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

		iterator = age_ids.iterator();
		while(iterator.hasNext()) {
			int age_id = iterator.next().intValue();
			products.addAll(this.getProductsByAge(age_id));
		}

		List<ProductBean> result = new ArrayList<ProductBean>();
		if(!and) {
			result.addAll(products);
		} else {
			Iterator<ProductBean> iterator2 = products.iterator();
			while(iterator2.hasNext()) {
				ProductBean product = iterator2.next();
				if((category_ids.size() == 0 || category_ids.contains(new Integer(product.getCategory().getId()))) &&
						(color_ids.size() == 0 || color_ids.contains(new Integer(product.getColor().getId()))) &&
						(age_ids.size() == 0 || age_ids.contains(new Integer(product.getAge().getId()))) &&
						(label_ids.size() == 0 || label_ids.contains(new Integer(product.getLabel().getId())))) {
					result.add(product);
				}
			}
		}

		Collections.sort(result);

		return result;
	}

	public ProductBean getProduct(int id) throws Exception {
		return entityManager.find(ProductBean.class, id);
	}

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

		LogOut();
		this.account_id = account.getEmailAddress();

		return this.account_id;
	}

	public AccountBean getAccount() throws Exception {
		if (this.account_id == null) {
			return null;
		} else {
			return entityManager.find(AccountBean.class, this.account_id);
		}
	}

	public void deleteAccount() throws Exception {
		AccountBean account = getAccount();
		entityManager.refresh(account);
		entityManager.remove(account);
	}

	public void setAccountPassword(String password) throws Exception {
		AccountBean account = getAccount();
		account.setPassword(password);
	}

	public void setAccountFirstName(String firstName) throws Exception {
		AccountBean account = getAccount();
		account.setFirstName(firstName);
	}

	public void setAccountLastName(String lastName) throws Exception {
		AccountBean account = getAccount();
		account.setLastName(lastName);
	}

	public void setAccountAddressLine1(String addressLine1) throws Exception {
		AccountBean account = getAccount();
		account.setAddressLine1(addressLine1);
	}

	public void setAccountAddressLine2(String addressLine2) throws Exception {
		AccountBean account = getAccount();
		account.setAddressLine2(addressLine2);
	}

	public void setAccountAddressLine3(String addressLine3) throws Exception {
		AccountBean account = getAccount();
		account.setAddressLine3(addressLine3);
	}

	public void setAccountZipCode(int zipCode) throws Exception {
		AccountBean account = getAccount();
		account.setZipCode(zipCode);
	}

	public void setAccountCity(String city) throws Exception {
		AccountBean account = getAccount();
		account.setCity(city);
	}

	public void setAccountPhoneNumber(String phoneNumber) throws Exception {
		AccountBean account = getAccount();
		account.setPhoneNumber(phoneNumber);
	}

	public void setAccountFaxNumber(String faxNumber) throws Exception {
		AccountBean account = getAccount();
		account.setFaxNumber(faxNumber);
	}

	public List<CommandBean> getCommands() throws Exception {
		return entityManager.createNamedQuery("getCommands").getResultList();
	}

	public List<CommandBean> getCommandsByAccount(String email) throws Exception {
		List<CommandBean> commands = new ArrayList<CommandBean>();
		commands.addAll(getAccount().getCommands());
		return commands;
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

	@PostConstruct
	private void postConstruct() {
		try {
			Context context = new InitialContext();
			cart = (CartRemote) context.lookup(CartBean.class.getName() + "_" + CartRemote.class.getName() + "@Remote");
		} catch (Exception ex) {

		}
	}

	public float getCommandTotal(int id) throws Exception {
		CommandBean command = getCommand(id);

		float total = command.getShipping();

		Set<CommandLineBean> command_lines = command.getCommand_lines();

		Iterator it = command_lines.iterator();

		CommandLineBean curcmdline;
		while (it.hasNext()) {
			curcmdline = (CommandLineBean) it.next();
			total += curcmdline.getNumber() * curcmdline.getPrice();
		}

		return total;
	}

	public State getCommandState(int id) throws Exception {
		return getCommand(id).getState();
	}

	@PreDestroy
	private void preDestroy() {
	}

	@PostActivate
	private void postActivate() {
	}

	@PrePassivate
	private void prePassivate() {
	}

	@Remove
	private void remove() {
	}
}
