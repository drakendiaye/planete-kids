/*-----------------------------------------------------------------------------*/
/* InitDataBase.java                                                           */
/* Program that initializes the ecom databaseby creating beans			 */
/* Fabienne Boyer - Didier Donsez may 2006                                     */
/*-----------------------------------------------------------------------------*/
package planetekids.clients;

import java.util.Random;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.transaction.UserTransaction;

import planetekids.beans.entity.QuestionBean;
import planetekids.beans.stateful.AdminBean;
import planetekids.beans.stateful.AdminRemote;

public class InitDataBaseClient {

    public static void main(String[] args) throws Exception {
	Context initialContext = null;
	UserTransaction utx = null;
	AdminRemote admin = null;
	try {
	    System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.ow2.easybeans.component.smartclient.spi.SmartContextFactory");
	    initialContext = new InitialContext();

	    utx = (UserTransaction) initialContext.lookup("javax.transaction.UserTransaction");

	    admin = (AdminRemote) initialContext.lookup(AdminBean.class.getName() + "_" + AdminRemote.class.getName() + "@Remote");
	} catch (Exception e) {
	    System.err.println("Error : " + e.getMessage());
	    System.exit(2);
	}

	try {
	    utx.begin();

	    /*------------------------------*/
	    /* Création des comptes clients */
	    /*------------------------------*/

	    admin.createAccount("clemplantier@gmail.com", "azerty", "Clément", "Plantier", "2 rue de la Poste", "", "", 38400,
		    "ST Martin d'Hères", "06 00 00 00 00", "");
	    admin.createAccount("guillaumerenault@yahoo.fr", "azerty", "Guillaume", "Renault", "11 rue Pasteur", "", "", 38400,
		    "ST Martin d'Hères", "06 00 00 00 01", "");
	    admin.createAccount("marcosoft74@yahoo.fr", "azerty", "Marc", "Schaller", "18 avenue G. Peri", "", "", 38400,
		    "ST Martin d'Hères", "06 00 00 00 02", "");
	    admin.createAccount("missog@club-internet.fr", "azerty", "Guillaume", "Missonnier", "51 avenue du Vercors", "", "", 38170,
		    "Seyssinet", "06 00 00 00 03", "");

	    /*----------------------*/
	    /* Création des marques */
	    /*----------------------*/


	    // id = 1
	    admin.createLabel("Nike", "Nike", "Marque americaine", "American label", "www.nike.com", "images/nike_large.png",
		    "images/nike_medium.png", "images/nike_small.png");
	    // id = 2
	    admin.createLabel("Adidas", "Adidas", "Marque", "Label", "www.adidas.com", "images/adidas_large.png",
		    "images/adidas_medium.png", "images/adidas_small.png");
	    // id = 3
	    admin.createLabel("Rebook", "Rebook", "Marque encore", "Label again", "www.rebook.com", "images/rebook_large.png",
		    "images/rebook_medium.png", "images/rebook_small.png");
	    // id = 4
	    admin.createLabel("QuikSilver", "QuikSilver", "Description en francais", "Description in english", "www.quiksilver.com",
		    "images/quiksilver_large.png", "images/quiksilver_medium.png", "images/quiksilver_small.png");
	    // id = 5
	    admin.createLabel("Chevignon", "Chevignon", "Description en francais", "Description in english", "www.chevignon.com",
		    "images/chevignon_large.png", "images/chevignon_medium.png", "images/chevignon_small.png");
	    // id = 6
	    admin.createLabel("Naf Naf", "Naf Naf", "Description en francais", "Description in english", "www.nafnaf.com",
		    "images/nafnaf_large.png", "images/nafnaf_medium.png", "images/nafnaf_small.png");

	    /*-----------------------*/
	    /* Création des couleurs */
	    /*-----------------------*/

	    // id = 1
	    admin.createColor("Blanc", "White", "Couleur blanche : 0xFFFFFF", "White Color : 0xFFFFFF", "images/white_large.png",
		    "images/white_large.png", "images/white_large.png");
	    // id = 2
	    admin.createColor("Jaune", "Yellow", "Couleur jaune : 0xFFFF00", "Yellow Color : 0xFFFF00", "images/yellow_large.png",
		    "images/yellow_medium.png", "images/yellow_small.png");
	    // id = 3
	    admin.createColor("Orange", "Orange", "Couleur orange : 0xFF9900", "Orange Color : 0xFF9900", "images/orange_large.png",
		    "images/orange_medium.png", "images/orange_small.png");
	    // id = 4
	    admin.createColor("Rose", "Pink", "Couleur rose : 0xFF90b7", "Pink Color : 0xFF90b7", "images/pink_large.png",
		    "images/pink_large.png", "images/pink_large.png");
	    // id = 5
	    admin.createColor("Rouge", "Red", "Couleur rouge : 0xFF0000", "Red Color : 0xFF0000", "images/red_large.png",
		    "images/red_medium.png", "images/red_small.png");
	    // id = 6
	    admin.createColor("Violet", "Purple", "Couleur Violete : 0x9900FF", "Purple Color : 0x9900FF", "images/purple_large.png",
		    "images/purple_large.png", "images/purple_large.png");
	    // id = 7
	    admin.createColor("Noir", "Black", "Couleur Noire : 0x000000", "Black Color : 0x000000", "images/black_large.png",
		    "images/black_large.png", "images/black_large.png");
	    // id = 8
	    admin.createColor("Bleu foncé", "Dark Blue", "Couleur bleue : 0x0033FF", "Blue Color : 0x0033FF", "images/darkblue_large.png",
		    "images/darkblue_medium.png", "images/darkblue_small.png");
	    // id = 9
	    admin.createColor("Bleu clair", "Bright Blue", "Couleur Bleue claire : 0x00CCFF", "Bright Blue Color : 0x00CCFF",
		    "images/brightblue_large.png", "images/brightblue_large.png", "images/brightblue_large.png");
	    // id = 10
	    admin.createColor("Vert foncé", "Dark Green", "Couleur verte : 0x76FF76", "Green Color : 0x76FF76",
		    "images/darkgreen_large.png", "images/darkgreen_medium.png", "images/darkgreen_small.png");
	    // id = 11
	    admin.createColor("Vert clair", "Bright Green", "Couleur Verte claire : 0x00FF55", "Bright Green Color : 0x00FF55",
		    "images/brightgreen_large.png", "images/brightgreen_large.png", "images/brightgreen_large.png");
	    // id = 12
	    admin.createColor("Gris", "Grey", "Couleur Grise : 0xADADAD", "Grey Color : 0xADADAD", "images/grey_large.png",
		    "images/grey_large.png", "images/grey_large.png");

	    /*-------------------------*/
	    /* Création des catégories */
	    /*-------------------------*/

	    // id = 1
	    admin.createCategory("Veste", "Jacket", "Pratique par temps de pluie ou grand froid",
		    "Useful when raining or when it's very cold", "images/jacket_large.png", "images/jacket_medium.png",
		    "images/jacket_small.png");
	    // id = 2
	    admin.createCategory("T-Shirt", "T-Shirt", "Desciption en francais", "Description in english", "images/t-shirt_large.png",
		    "images/t-shirt_medium.png", "images/t-shirt_small.png");
	    // id = 3
	    admin.createCategory("Chemises", "Shirt", "Desciption en francais", "Description in english", "images/shirt_large.png",
		    "images/shirt_medium.png", "images/shirt_small.png");
	    // id = 4
	    admin.createCategory("Chemisiers", "Blouses", "Desciption en francais", "Description in english", "images/blouses_large.png",
		    "images/blouses_medium.png", "images/blouses_small.png");
	    // id = 5
	    admin.createCategory("Pull", "Sweaters", "Desciption en francais", "Description in english", "images/sweat_large.png",
		    "images/sweat_medium.png", "images/sweat_small.png");
	    // id = 6
	    admin.createCategory("Jupes", "Skirts", "Desciption en francais", "Description in english", "images/skirt_large.png",
		    "images/skirt_medium.png", "images/skirt_small.png");
	    // id = 7
	    admin.createCategory("Short", "Shorts", "Desciption en francais", "Description in english", "images/short_large.png",
		    "images/short_medium.png", "images/short_small.png");
	    // id = 8
	    admin.createCategory("Pantalon", "Trousers", "On le met de bas en haut", "From Paris to Berlin", "images/trousers_large.png",
		    "images/trousers_medium.png", "images/trousers_small.png");
	    // id = 9
	    admin.createCategory("Robe", "Dress", "Description en francais", "Description in english", "images/dress_large.png",
		    "images/dress_medium.png", "images/dress_small.png");

	    /*-----------------------*/
	    /* Création des produits */
	    /*-----------------------*/

	    // id = 1
	    admin.createProduct("Pantalon Naf-Naf", "Naf-Naf trousers", "Etanche pour bébé", "For baby, waterproof", 1, 1, 1, 23, 90, "",
		    "", "");
	    // id = 2
	    admin.createProduct("Veste", "Jacket", "Veste en coton, quand il ne fait pas froid",
		    "A coton Jacket, to wear when it's war olny", 1, 4, 2, 123, 45, "", "", "");

	    // creation de tous les produits :

	    Random rand = new Random();

	    // //////////
	    // Vestes //
	    // //////////
	    // Pour toutes les couleurs
	    for (int i = 1; i <= 12; i++) {
		// Pour toutes les marques
		for (int j = 1; j <= 6; j++) {
		    admin.createProduct("Veste numero " + ((i - 1) * 6 + j), "Jacket number " + ((i - 1) * 6 + j),
			    "Description en francais", "Description in english", 1, i, j, rand.nextFloat() * 34, rand.nextInt(20), "", "",
			    "");
		}
	    }

	    // ///////////
	    // T-shirt //
	    // ///////////
	    // Pour toutes les couleurs
	    for (int i = 1; i <= 12; i++) {
		// Pour toutes les marques
		for (int j = 1; j <= 6; j++) {
		    admin.createProduct("T-shirt numero " + ((i - 1) * 6 + j), "T-shirt number " + ((i - 1) * 6 + j),
			    "Description en francais", "Description in english", 2, i, j, rand.nextFloat() * 34, rand.nextInt(20), "", "",
			    "");
		}
	    }

	    // ////////////
	    // Chemises //
	    // ////////////
	    // Pour toutes les couleurs
	    for (int i = 1; i <= 12; i++) {
		// Pour toutes les marques
		for (int j = 1; j <= 6; j++) {
		    admin.createProduct("Chemise numero " + ((i - 1) * 6 + j), "Shirt number " + ((i - 1) * 6 + j),
			    "Description en francais", "Description in english", 3, i, j, rand.nextFloat() * 34, rand.nextInt(20), "", "",
			    "");
		}
	    }

	    // //////////////
	    // chemisiers //
	    // //////////////
	    // Pour toutes les couleurs
	    for (int i = 1; i <= 12; i++) {
		// Pour toutes les marques
		for (int j = 1; j <= 6; j++) {
		    admin.createProduct("Chemisier numero " + ((i - 1) * 6 + j), "Blouse number " + ((i - 1) * 6 + j),
			    "Description en francais", "Description in english", 4, i, j, rand.nextFloat() * 34, rand.nextInt(20), "", "",
			    "");
		}
	    }

	    // ////////
	    // Pull //
	    // ////////
	    // Pour toutes les couleurs
	    for (int i = 1; i <= 12; i++) {
		// Pour toutes les marques
		for (int j = 1; j <= 6; j++) {
		    admin.createProduct("Pull numero " + ((i - 1) * 6 + j), "Sweater number " + ((i - 1) * 6 + j),
			    "Description en francais", "Description in english", 5, i, j, rand.nextFloat() * 34, rand.nextInt(20), "", "",
			    "");
		}
	    }

	    // /////////
	    // Jupes //
	    // /////////
	    // Pour toutes les couleurs
	    for (int i = 1; i <= 12; i++) {
		// Pour toutes les marques
		for (int j = 1; j <= 6; j++) {
		    admin.createProduct("Jupe numero " + ((i - 1) * 6 + j), "Skirt number " + ((i - 1) * 6 + j), "Description en francais",
			    "Description in english", 6, i, j, rand.nextFloat() * 34, rand.nextInt(20), "", "", "");
		}
	    }

	    // /////////
	    // Short //
	    // /////////
	    // Pour toutes les couleurs
	    for (int i = 1; i <= 12; i++) {
		// Pour toutes les marques
		for (int j = 1; j <= 6; j++) {
		    admin.createProduct("Short numero " + ((i - 1) * 6 + j), "Short number " + ((i - 1) * 6 + j),
			    "Description en francais", "Description in english", 7, i, j, rand.nextFloat() * 34, rand.nextInt(20), "", "",
			    "");
		}
	    }

	    // /////////////
	    // Pantalons //
	    // /////////////
	    // Pour toutes les couleurs
	    for (int i = 1; i <= 12; i++) {
		// Pour toutes les marques
		for (int j = 1; j <= 6; j++) {
		    admin.createProduct("Pantalon numero " + ((i - 1) * 6 + j), "Pants number " + ((i - 1) * 6 + j),
			    "Description en francais", "Description in english", 8, i, j, rand.nextFloat() * 34, rand.nextInt(20), "", "",
			    "");
		}
	    }

	    // /////////
	    // Robes //
	    // /////////
	    // Pour toutes les couleurs
	    for (int i = 1; i <= 12; i++) {
		// Pour toutes les marques
		for (int j = 1; j <= 6; j++) {
		    admin.createProduct("Robe numero " + ((i - 1) * 6 + j), "Dress number " + ((i - 1) * 6 + j), "Description en francais",
			    "Description in english", 9, i, j, rand.nextFloat() * 34, rand.nextInt(20), "", "", "");
		}
	    }

	    /*---------------------------*/
	    /* Création du questionnaire */
	    /*---------------------------*/

	    int questionnaireId = admin.createQuestionnaire("Planete Kids ouvre bientôt ses portes sur le net.",
		    "Planete Kids opens to the web soon.", "Aidez-nous à créer un site à  votre image !",
		    "Help us to create a website that fits you!");

	    int questionId = admin.createQuestion(questionnaireId, "Quel type de connexion internet utilisez-vous ?",
		    "What kind of internet connection do you use?", QuestionBean.Pattern.SINGLE_CHOICE, 1);
	    admin.createAnswer(questionId, "Connexion par modem", "Modem connection", false, 1);
	    admin.createAnswer(questionId, "Connexion haut débit", "High bandwidth connection", false, 2);
	    admin.createAnswer(questionId, "Connexion par câble", "Cable connection", false, 3);
	    admin.createAnswer(questionId, "Autre, veuillez préciser :", "Other, please specify :", true, 4);

	    questionId = admin.createQuestion(questionnaireId, "Quel navigateur internet utilisez-vous ?", "What browser do you use?",
		    QuestionBean.Pattern.SINGLE_CHOICE, 2);
	    admin.createAnswer(questionId, "Internet Explorer", "Internet Explorer", false, 1);
	    admin.createAnswer(questionId, "Firefox", "Firefox", false, 2);
	    admin.createAnswer(questionId, "Opera", "Opera", false, 3);
	    admin.createAnswer(questionId, "Safari", "Safari", false, 3);
	    admin.createAnswer(questionId, "Autre, veuillez préciser :", "Other, please specify :", true, 5);

	    questionId = admin.createQuestion(questionnaireId,
		    "D'une manière générale, lorsque vous achetez sur internet, vous êtes sensible à :",
		    "Usually, when you purchase on the internet, you are sensible to :", QuestionBean.Pattern.SORT, 3);
	    admin.createAnswer(questionId, "L'attractivité des prix", "Price attractivity", false, 1);
	    admin.createAnswer(questionId, "La diversité des produits proposés", "Product diversity", false, 2);
	    admin.createAnswer(questionId, "La qualité du site (esthétisme, facilité d'utilisation...)",
		    "Website quality (esthetism, user-friendliness...)", false, 3);
	    admin.createAnswer(questionId, "Les services proposés (garanties, remboursement...)", "Services (warranty, refund...)", false,
		    4);

	    questionId = admin.createQuestion(questionnaireId,
		    "Dans lesquelles des catégories suivantes avez-vous acheté un produit ou un service au cours des 12 derniers mois ?",
		    "In which of these categories did you buy a product or a service during the past 12 months?",
		    QuestionBean.Pattern.MULTI_CHOICE, 4);
	    admin.createAnswer(questionId, "Produits culturels", "Cultural products", false, 1);
	    admin.createAnswer(questionId, "Informatique", "Computing", false, 2);
	    admin.createAnswer(questionId, "TV, Hi-Fi", "TV, Hi-Fi", false, 3);
	    admin.createAnswer(questionId, "Textile", "Textile", false, 4);
	    admin.createAnswer(questionId, "Alimentation", "Food", false, 5);
	    admin.createAnswer(questionId, "Tourisme", "Tourism", false, 6);
	    admin.createAnswer(questionId, "Fleur", "Flower", false, 7);

	    questionId = admin.createQuestion(questionnaireId, "Combien d'enfants de moins de 17 ans vivent dans votre foyer ?",
		    "How many children less than 17 years old are living in your home?", QuestionBean.Pattern.VALUE, 5);
	    admin.createAnswer(questionId, "enfant(s)", "child(ren)", false, 1);

	    questionId = admin.createQuestion(questionnaireId, "Seriez-vous interessé par l'achat de vêtements en ligne ?",
		    "Would you be interested in buying clothes on the internet?", QuestionBean.Pattern.SINGLE_CHOICE, 6);
	    admin.createAnswer(questionId, "Oui", "Yes", false, 1);
	    admin.createAnswer(questionId, "Non", "No", false, 2);

	    questionId = admin.createQuestion(questionnaireId,
		    "Ecrivez trois mots-clef que vous utliseriez pour rechercher des vêtements pour votre enfant sur internet.",
		    "Specify three words you would use to find clothes for your child on the internet.", QuestionBean.Pattern.VALUE, 7);
	    admin.createAnswer(questionId, "(a)", "(a)", false, 1);
	    admin.createAnswer(questionId, "(b)", "(b)", false, 2);
	    admin.createAnswer(questionId, "(c)", "(c)", false, 3);

	    questionId = admin.createQuestion(questionnaireId, "À quelle fréquence achetez-vous des vêtements pour votre enfant ?",
		    "How often do you buy clothes for your child?", QuestionBean.Pattern.SINGLE_CHOICE, 8);
	    admin.createAnswer(questionId, "Une fois par an", "Once a year", false, 1);
	    admin.createAnswer(questionId, "Deux à trois fois par an", "Twice or three times a year", false, 2);
	    admin.createAnswer(questionId, "Quatre fois par an ou plus, veuillez préciser :",
		    "Four times a year or more, please specify :", true, 3);

	    questionId = admin.createQuestion(questionnaireId, "Qui choisit les vêtements pour votre enfant ?",
		    "Who choose the clothes for your child?", QuestionBean.Pattern.SINGLE_CHOICE, 9);
	    admin.createAnswer(questionId, "Vous", "You", false, 1);
	    admin.createAnswer(questionId, "Votre enfant", "Your child", false, 2);
	    admin.createAnswer(questionId, "Autre, veuillez préciser :", "Other, please specify :", true, 3);

	    questionId = admin.createQuestion(questionnaireId, "Quel est votre budget annuel consacré aux vêtements pour votre enfant ?",
		    "How much do you spend a year to buy clothes for your child?", QuestionBean.Pattern.VALUE, 10);
	    admin.createAnswer(questionId, " € par an", " € per year", false, 1);

	    questionId = admin.createQuestion(questionnaireId, "Vous êtes :", "You are:", QuestionBean.Pattern.SINGLE_CHOICE, 11);
	    admin.createAnswer(questionId, "Un homme", "A man", false, 1);
	    admin.createAnswer(questionId, "Une femme", "A female", false, 2);

	    questionId = admin.createQuestion(questionnaireId, "Vous avez :", "You are:", QuestionBean.Pattern.SINGLE_CHOICE, 12);
	    admin.createAnswer(questionId, "Moins de 15 ans", "Less than 15 years old", false, 1);
	    admin.createAnswer(questionId, "Entre 15 et 25 ans", "Between 15 and 25 years old", false, 2);
	    admin.createAnswer(questionId, "Entre 25 et 35 ans", "Between 25 and 35 years old", false, 3);
	    admin.createAnswer(questionId, "Entre 35 et 60 ans", "Between 35 and 60 years old", false, 4);
	    admin.createAnswer(questionId, "Plus de 60 ans", "More than 60 years old", false, 5);

	    questionId = admin.createQuestion(questionnaireId, "Quelle est votre catégorie socio-professionnelle ?",
		    "What is your socio-professional category?", QuestionBean.Pattern.SINGLE_CHOICE, 13);
	    admin.createAnswer(questionId, "Etudiant", "Student", false, 1);
	    admin.createAnswer(questionId, "Agriculteur", "Farmer", false, 2);
	    admin.createAnswer(questionId, "Artisan / commerçant", "Artisan / storekeeper", false, 3);
	    admin.createAnswer(questionId, "Ouvrier", "Factory worker", false, 4);
	    admin.createAnswer(questionId, "Employé", "Employee", false, 5);
	    admin.createAnswer(questionId, "Cadre", "Executive", false, 6);
	    admin.createAnswer(questionId, "Retraité", "Retired", false, 7);
	    admin.createAnswer(questionId, "Autre", "Other", false, 8);

	    utx.commit();

	} catch (Exception e) {
	    System.err.println("InitDataBase program get an exception " + e);
	    utx.rollback();
	    System.exit(2);
	}
    }
}
