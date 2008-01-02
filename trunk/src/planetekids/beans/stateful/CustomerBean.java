package planetekids.beans.stateful;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import planetekids.beans.entity.AccountBean;
import planetekids.beans.entity.AnswerBean;
import planetekids.beans.entity.LocaleBean;
import planetekids.beans.entity.QuestionBean;
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
	
        QuestionnaireBean questionnaire = new QuestionnaireBean(new LocaleBean("Planete Kids ouvre bientôt ses portes sur le net.", "Planete Kids opens to the web soon."),
        new LocaleBean("Aidez-nous à créer un site à  votre image !", "Help us to create a website that fits you!"));
        
        QuestionBean question = new QuestionBean(questionnaire, new LocaleBean("Quel type de connexion internet utilisez-vous ?", "What kind of internet connection do you use?"), QuestionBean.Pattern.SINGLE_CHOICE, 1);
        AnswerBean answer = new AnswerBean(question, new LocaleBean("Connexion par modem", "Modem connection"), false, 1);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("Connexion haut débit", "High bandwidth connection"), false, 2);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("Connexion par câble", "Cable connection"), false, 3);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("Autre, veuillez préciser :", "Other, please specify :"), true, 4);
        question.getAnswers().add(answer);
        questionnaire.getQuestions().add(question);
        
        question = new QuestionBean(questionnaire, new LocaleBean("Quel navigateur internet utilisez-vous ?", "What browser do you use?"), QuestionBean.Pattern.SINGLE_CHOICE, 2);
        answer = new AnswerBean(question, new LocaleBean("Internet Explorer", "Internet Explorer"), false, 1);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("Firefox", "Firefox"), false, 2);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("Opera", "Opera"), false, 3);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("Safari", "Safari"), false, 4);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("Autre, veuillez préciser :", "Other, please specify :"), true, 5);
        question.getAnswers().add(answer);
        questionnaire.getQuestions().add(question);
        
        question = new QuestionBean(questionnaire, new LocaleBean("D'une manière générale, lorsque vous achetez sur internet, vous êtes sensible à :", "Usually, when you purchase on the internet, you are sensible to :"), QuestionBean.Pattern.SORT, 3);
        answer = new AnswerBean(question, new LocaleBean("L'attractivité des prix", "Prices attractivity"), false, 1);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("La diversité des produits proposés", "Products diversity"), false, 2);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("La qualité du site (esthétisme, facilité d'utilisation...)", "Website quality (esthetism, user-friendliness...)"), false, 3);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("Les services proposés (garanties, remboursement...)", "Services (warranty, refund...)"), false, 4);
        question.getAnswers().add(answer);
        questionnaire.getQuestions().add(question);
        
        question = new QuestionBean(questionnaire, new LocaleBean("Dans lesquelles des catégories suivantes avez-vous acheté un produit ou un service au cours des 12 derniers mois ?", "In which of these categories did you buy a product or a service during the past 12 months?"), QuestionBean.Pattern.MULTI_CHOICE, 4);
        answer = new AnswerBean(question, new LocaleBean("Produits culturels", "Cultural products"), false, 1);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("Informatique", "Computing"), false, 2);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("TV, Hi-Fi", "TV, Hi-Fi"), false, 3);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("Textile", "Textile"), false, 4);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("Alimentation", "Food"), false, 5);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("Tourisme", "Tourism"), false, 6);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("Fleur", "Flower"), false, 7);
        question.getAnswers().add(answer);
        questionnaire.getQuestions().add(question);
        
	question = new QuestionBean(questionnaire, new LocaleBean("Combien d'enfants de moins de 17 ans vivent dans votre foyer ?", "How many children less than 17 years old are living in your home?"), QuestionBean.Pattern.VALUE, 5);
        answer = new AnswerBean(question, new LocaleBean("enfant(s)", "child(ren)"), false, 1);
        question.getAnswers().add(answer);
        questionnaire.getQuestions().add(question);
        
        question = new QuestionBean(questionnaire, new LocaleBean("Seriez-vous interessé par l'achat de vêtements en ligne ?", "Would you be interested in buying clothes on the internet?"), QuestionBean.Pattern.SINGLE_CHOICE, 6);
        answer = new AnswerBean(question, new LocaleBean("Oui", "Yes"), false, 1);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("Non", "No"), false, 2);
        question.getAnswers().add(answer);
        questionnaire.getQuestions().add(question);
	
        question = new QuestionBean(questionnaire, new LocaleBean("Ecrivez trois mots-clef que vous utliseriez pour rechercher des vêtements pour votre enfant sur internet.", "Specify three words you would use to find clothes for your child on the internet."), QuestionBean.Pattern.VALUE, 7);
        answer = new AnswerBean(question, new LocaleBean("(a)", "(a)"), false, 1);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("(b)", "(b)"), false, 2);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("(c)", "(c)"), false, 3);
        question.getAnswers().add(answer);
        questionnaire.getQuestions().add(question);
        
        question = new QuestionBean(questionnaire, new LocaleBean("À quelle fréquence achetez-vous des vêtements pour votre enfant ?", "How often do you buy clothes for your child?"), QuestionBean.Pattern.SINGLE_CHOICE, 8);
        answer = new AnswerBean(question, new LocaleBean("Une fois par an", "Once a year"), false, 1);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("Deux à trois fois par an", "Twice or three times a year"), false, 2);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("Quatre fois par an ou plus, veuillez préciser :", "Four times a year or more, please specify :"), true, 3);
        question.getAnswers().add(answer);
        questionnaire.getQuestions().add(question);
        
        question = new QuestionBean(questionnaire, new LocaleBean("Qui choisit les vêtements pour votre enfant ?", "Who choose the clothes for your child?"), QuestionBean.Pattern.SINGLE_CHOICE, 9);
        answer = new AnswerBean(question, new LocaleBean("Vous", "You"), false, 1);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("Votre enfant", "Your child"), false, 2);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("Autre, veuillez préciser :", "Other, please specify :"), true, 3);
        question.getAnswers().add(answer);
        questionnaire.getQuestions().add(question);
        
        question = new QuestionBean(questionnaire, new LocaleBean("Quel est votre budget annuel consacré aux vêtements pour votre enfant ?", "How much do you spend a year to buy clothes for your child?"), QuestionBean.Pattern.VALUE, 10);
        answer = new AnswerBean(question, new LocaleBean(" € par an", " € per year"), false, 1);
        question.getAnswers().add(answer);
        questionnaire.getQuestions().add(question);
        
        question = new QuestionBean(questionnaire, new LocaleBean("Vous êtes :", "You are:"), QuestionBean.Pattern.SINGLE_CHOICE, 11);
        answer = new AnswerBean(question, new LocaleBean("Un homme", "A man"), false, 1);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("Une femme", "A female"), false, 2);
        question.getAnswers().add(answer);
        questionnaire.getQuestions().add(question);
        
        question = new QuestionBean(questionnaire, new LocaleBean("Vous avez :", "You are:"), QuestionBean.Pattern.SINGLE_CHOICE, 12);
        answer = new AnswerBean(question, new LocaleBean("Moins de 15 ans", "Less than 15 years old"), false, 1);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("Entre 15 et 25 ans", "Between 15 and 25 years old"), false, 2);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("Entre 25 et 35 ans", "Between 25 and 35 years old"), false, 3);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("Entre 35 et 60 ans", "Between 35 and 60 years old"), false, 4);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("Plus de 60 ans", "More than 60 years old"), false, 5);
        question.getAnswers().add(answer);
        questionnaire.getQuestions().add(question);
        
        question = new QuestionBean(questionnaire, new LocaleBean("Quelle est votre catégorie socio-professionnelle ?", "What is your socio-professional category?"), QuestionBean.Pattern.SINGLE_CHOICE, 13);
        answer = new AnswerBean(question, new LocaleBean("Etudiant", "Student"), false, 1);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("Agriculteur", "Farmer"), false, 2);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("Artisan / commerçant", "Artisan / storekeeper"), false, 3);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("Ouvrier", "Factory worker"), false, 4);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("Employé", "Employee"), false, 5);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("Cadre", "Executive"), false, 6);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("Retraité", "Retired"), false, 7);
        question.getAnswers().add(answer);
        answer = new AnswerBean(question, new LocaleBean("Autre", "Other"), false, 8);
	
        question.getAnswers().add(answer);
        questionnaire.getQuestions().add(question);
        
        entityManager.persist(questionnaire);
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
