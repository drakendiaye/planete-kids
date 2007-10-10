/**
* eCommerce Application Sample for J2EE Training 
* Implementation of EcomCustomerBean
* @author Fabienne Boyer - Didier Donsez
* may 2006
*/

package beans.stateful;

import beans.entity.AnswerBean;
import beans.entity.QuestionBean;
import beans.entity.QuestionnaireBean;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.SessionSynchronization;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful
public class CustomerBean implements CustomerRemote{

    @PersistenceContext
    private EntityManager entityManager;
    
    public void init() {
        QuestionnaireBean questionnaire = new QuestionnaireBean("Planete-Kids ouvre bientôt ses porte sur le web!!!", "Aidez-nous à créer un site à votre image");
        
        QuestionBean question = new QuestionBean(questionnaire, "Combien d'enfants de moins de 17 ans vivent dans votre foyer?", QuestionBean.Pattern.VALUE);
        questionnaire.getQuestions().add(question);
        
        AnswerBean answer = new AnswerBean(question, "enfant(s)", false);
        question.getAnswers().add(answer);
        
        entityManager.persist(questionnaire);
        entityManager.joinTransaction();
    }
    
    public List<QuestionnaireBean> getQuestionnaires() {
        Query query = entityManager.createNamedQuery("getQuestionnaires");
        return query.getResultList();
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
