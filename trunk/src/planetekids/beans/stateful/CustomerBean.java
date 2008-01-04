package planetekids.beans.stateful;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import planetekids.beans.entity.AnswerBean;
import planetekids.beans.entity.CategoryBean;
import planetekids.beans.entity.ColorBean;
import planetekids.beans.entity.LabelBean;
import planetekids.beans.entity.ProductBean;
import planetekids.beans.entity.QuestionnaireBean;
import planetekids.beans.entity.ResultBean;

@Stateful
public class CustomerBean implements CustomerRemote{
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public void init() {
	/*AccountBean account = new AccountBean("clemplantier@gmail.com");
	account.setFirstName("Clément");
	account.setLastName("Plantier");
	account.setZipCode(38400);
	account.setCity("Saint Martin d'Hères");
	account.setAddressLine1("2 rue de la Poste");
	account.setPhoneNumber("O6 81 42 38 49");
	account.setPassword("azerty");
	
	
	entityManager.persist(account);*/
    }
    
    public List<QuestionnaireBean> getQuestionnaires() {
        return entityManager.createNamedQuery("getQuestionnaires").getResultList();
    }
    
    public QuestionnaireBean getQuestionnaire(int questionnaire_id) {
        return entityManager.find(QuestionnaireBean.class, questionnaire_id);
    }
    
    public void createResult(int id, String value, String comment) throws Exception {
        AnswerBean answer = entityManager.find(AnswerBean.class, id);
        if(answer == null) throw new Exception("Cannot find AnswerBean : id="+String.valueOf(id));
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
    
    @PostConstruct
    private void postConstruct() {
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
