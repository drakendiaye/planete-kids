package planetekids.beans.stateful;

import java.util.List;
import javax.ejb.Remote;
import planetekids.beans.entity.CategoryBean;
import planetekids.beans.entity.ColorBean;
import planetekids.beans.entity.LabelBean;
import planetekids.beans.entity.ProductBean;
import planetekids.beans.entity.QuestionnaireBean;

@Remote
public interface CustomerRemote {
    public void init();
    public List<QuestionnaireBean> getQuestionnaires();
    public QuestionnaireBean getQuestionnaire(int questionnaire_id);
    public void createResult(int id, String value, String comment) throws Exception;
    
    public List<LabelBean> getLabels() throws Exception;
    public LabelBean getLabel(int id) throws Exception;
    
    public List<ColorBean> getColors() throws Exception;
    public ColorBean getColor(int id) throws Exception;
    
    public List<CategoryBean> getCategories() throws Exception;
    public CategoryBean getCategory(int id) throws Exception;
    
    public List<ProductBean> getProducts() throws Exception;
    public List<ProductBean> getProductsByCategory(int category_id) throws Exception;
    public List<ProductBean> getProductsByColor(int color_id) throws Exception;
    public List<ProductBean> getProductsByLabel(int label_id) throws Exception;
    public List<ProductBean> getProductsByFilter(List<Integer> category_ids, List<Integer> color_ids, List<Integer> label_ids, boolean and) throws Exception;
    public ProductBean getProduct(int id) throws Exception;
}
