package planetekids.clients;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.transaction.UserTransaction;
import planetekids.beans.entity.AccountBean;
import planetekids.beans.entity.AgeBean;
import planetekids.beans.entity.CategoryBean;
import planetekids.beans.entity.ColorBean;
import planetekids.beans.entity.LabelBean;
import planetekids.beans.entity.ProductBean;
import planetekids.beans.stateful.AdminBean;
import planetekids.beans.stateful.AdminRemote;

public class AdminClient {

    public static void main(String[] args) {
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

	while (true) {
	    try {
		System.out.println("------------------------------------------------------");
		System.out.println("Main Menu");
		System.out.println("0 = quit the application");
		System.out.println("1 = manage labels");
		System.out.println("2 = manage colors");
		System.out.println("3 = manage categories");
		System.out.println("4 = manage ages");
		System.out.println("5 = manage products");
		System.out.println("6 = manage customers");
		System.out.println("------------------------------------------------------");
		System.out.print(">");
		int choice = Tx.readInt();

		if (choice == 0) {
		    System.exit(2);
		} else if (choice == 1) {
		    manageLabels(utx, admin);
		} else if (choice == 2) {
		    manageColors(utx, admin);
		} else if (choice == 3) {
		    manageCategories(utx, admin);
		} else if (choice == 4) {
		    manageAges(utx, admin);
		} else if (choice == 5) {
		    manageProducts(utx, admin);
		} else if (choice == 6) {
		    manageCustomers(utx, admin);
		} else {
		    System.out.println("Bad choice");
		}
	    } catch (Exception ex) {
		System.out.println("error : " + ex.getMessage());
	    }
	}
    }

    static void manageLabels(UserTransaction utx, AdminRemote admin) throws Exception {
	while (true) {
	    try {
		System.out.println("------------------------------------------------------");
		System.out.println("Manage Labels");
		System.out.println("0 = return");
		System.out.println("1 = view labels list");
		System.out.println("2 = create a label");
		System.out.println("3 = modify a label");
		System.out.println("4 = delete a label");
		System.out.println("------------------------------------------------------");
		System.out.print(">");

		int choice = Tx.readInt();

		if (choice == 0) {
		    return;
		} else if (choice == 1) {
		    List<LabelBean> labels = null;
		    try {
			utx.begin();
			labels = admin.getLabels();
			utx.commit();
		    } catch (Exception ex) {
			utx.rollback();
			throw ex;
		    }
		    if (labels != null) {
			Iterator iterator = labels.iterator();
			while (iterator.hasNext()) {
			    LabelBean label = (LabelBean) iterator.next();
			    System.out.println("id : " + label.getId());
			    System.out.println("name : " + label.getName());
			    System.out.println("description french : " + label.getDescription().getFr());
			    System.out.println("description english : " + label.getDescription().getEn());
			    System.out.println("site : " + label.getSite());
			    System.out.println("image large : " + label.getImage_large());
			    System.out.println("image medium : " + label.getImage_medium());
			    System.out.println("image small : " + label.getImage_small());
			    System.out.println("");
			}
		    }
		} else if (choice == 2) {
		    System.out.print("name: ");
		    String name = Tx.readString();
		    System.out.print("description french: ");
		    String description_fr = Tx.readString();
		    System.out.print("description english: ");
		    String description_en = Tx.readString();
		    System.out.print("site : ");
		    String site = Tx.readString();
		    System.out.print("image large : ");
		    String image_large = Tx.readString();
		    System.out.print("image medium : ");
		    String image_medium = Tx.readString();
		    System.out.print("image small : ");
		    String image_small = Tx.readString();
		    try {
			utx.begin();
			admin.createLabel(name, description_fr, description_en, site, image_large, image_medium, image_small);
			utx.commit();
		    } catch (Exception ex) {
			utx.rollback();
			throw ex;
		    }
		    System.out.println("label successfully created");
		} else if (choice == 3) {
		    System.out.print("id : ");
		    int id = Tx.readInt();
		    while (true) {
			try {
			    LabelBean label;
			    try {
				utx.begin();
				label = admin.getLabel(id);
				utx.commit();
			    } catch (Exception ex) {
				utx.rollback();
				throw ex;
			    }
			    if (label == null) {
				System.out.println("Label id " + id + " does not exist");
				break;
			    }
			    System.out.println("------------------------------------------------------");
			    System.out.println("Modify Label ");
			    System.out.println("id : " + label.getId());
			    System.out.println("name : " + label.getName());
			    System.out.println("description french : " + label.getDescription().getFr());
			    System.out.println("description english : " + label.getDescription().getEn());
			    System.out.println("site : " + label.getSite());
			    System.out.println("image large : " + label.getImage_large());
			    System.out.println("image medium : " + label.getImage_medium());
			    System.out.println("image small : " + label.getImage_small());
			    System.out.println("------------------------------------------------------");
			    System.out.println("0 = return");
			    System.out.println("1 = modify name french");
			    System.out.println("2 = modify description french");
			    System.out.println("3 = modify description english");
			    System.out.println("4 = modify site");
			    System.out.println("5 = modify image_large");
			    System.out.println("6 = modify image_medium");
			    System.out.println("7 = modify image_small");
			    System.out.println("------------------------------------------------------");
			    System.out.print(">");
			    choice = Tx.readInt();

			    if (choice == 0) {
				break;
			    } else if (choice == 1) {
				System.out.print("name : ");
				String name = Tx.readString();
				try {
				    utx.begin();
				    admin.setLabelName(id, name);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("label successfully updated");
			    } else if (choice == 2) {
				System.out.print("description french : ");
				String description = Tx.readString();
				try {
				    utx.begin();
				    admin.setLabelDescriptionFr(id, description);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("label successfully updated");
			    } else if (choice == 3) {
				System.out.print("description en : ");
				String description = Tx.readString();
				try {
				    utx.begin();
				    admin.setLabelDescriptionEn(id, description);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("label successfully updated");
			    } else if (choice == 4) {
				System.out.print("site : ");
				String site = Tx.readString();
				try {
				    utx.begin();
				    admin.setLabelSite(id, site);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("label successfully updated");
			    } else if (choice == 5) {
				System.out.print("image large : ");
				String image = Tx.readString();
				try {
				    utx.begin();
				    admin.setLabelImageLarge(id, image);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("label successfully updated");
			    } else if (choice == 6) {
				System.out.print("image medium : ");
				String image = Tx.readString();
				try {
				    utx.begin();
				    admin.setLabelImageMedium(id, image);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("label successfully updated");
			    } else if (choice == 7) {
				System.out.print("image small : ");
				String image = Tx.readString();
				try {
				    utx.begin();
				    admin.setLabelImageSmall(id, image);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("label successfully updated");
			    } else {
				System.out.println("Bad choice");
			    }
			} catch (Exception ex) {
			    System.out.println("error : " + ex.getMessage());
			}
		    }
		} else if (choice == 4) {
		    System.out.print("id : ");
		    int id = Tx.readInt();
		    try {
			LabelBean label;
			try {
			    utx.begin();
			    label = admin.getLabel(id);
			    utx.commit();
			} catch (Exception ex) {
			    utx.rollback();
			    throw ex;
			}
			if (label == null) {
			    System.out.println("Label id " + id + " does not exist");
			    break;
			}
			utx.begin();
			admin.deleteLabel(id);
			utx.commit();
		    } catch (Exception ex) {
			utx.rollback();
			System.out.println("error : " + ex.getMessage());
		    }
		} else {
		    System.out.println("Bad choice");
		}
	    } catch (Exception ex) {
		System.out.println("error : " + ex.getMessage());
	    }
	}
    }

    static void manageColors(UserTransaction utx, AdminRemote admin) throws Exception {
	while (true) {
	    try {
		System.out.println("------------------------------------------------------");
		System.out.println("Manage Colors");
		System.out.println("0 = return");
		System.out.println("1 = view colors list");
		System.out.println("2 = create a color");
		System.out.println("3 = modify a color");
		System.out.println("4 = delete a color");
		System.out.println("------------------------------------------------------");
		System.out.print(">");

		int choice = Tx.readInt();

		if (choice == 0) {
		    return;
		} else if (choice == 1) {
		    List<ColorBean> colors = null;
		    try {
			utx.begin();
			colors = admin.getColors();
			utx.commit();
		    } catch (Exception ex) {
			utx.rollback();
			throw ex;
		    }
		    if (colors != null) {
			Iterator iterator = colors.iterator();
			while (iterator.hasNext()) {
			    ColorBean color = (ColorBean) iterator.next();
			    System.out.println("id : " + color.getId());
			    System.out.println("name french : " + color.getName().getFr());
			    System.out.println("name english : " + color.getName().getEn());
			    System.out.println("description french : " + color.getDescription().getFr());
			    System.out.println("description english : " + color.getDescription().getEn());
			    System.out.println("image large : " + color.getImage_large());
			    System.out.println("image medium : " + color.getImage_medium());
			    System.out.println("image small : " + color.getImage_small());
			    System.out.println("");
			}
		    }
		} else if (choice == 2) {
		    System.out.print("name french: ");
		    String name_fr = Tx.readString();
		    System.out.print("name english: ");
		    String name_en = Tx.readString();
		    System.out.print("description french: ");
		    String description_fr = Tx.readString();
		    System.out.print("description english: ");
		    String description_en = Tx.readString();
		    System.out.print("image large : ");
		    String image_large = Tx.readString();
		    System.out.print("image medium : ");
		    String image_medium = Tx.readString();
		    System.out.print("image small : ");
		    String image_small = Tx.readString();
		    try {
			utx.begin();
			admin.createColor(name_fr, name_en, description_fr, description_en, image_large, image_medium, image_small);
			utx.commit();
		    } catch (Exception ex) {
			utx.rollback();
			throw ex;
		    }
		    System.out.println("color successfully created");
		} else if (choice == 3) {
		    System.out.print("id : ");
		    int id = Tx.readInt();
		    while (true) {
			try {
			    ColorBean color = null;
			    try {
				utx.begin();
				color = admin.getColor(id);
				utx.commit();
			    } catch (Exception ex) {
				utx.rollback();
				throw ex;
			    }
			    if (color == null) {
				System.out.println("Color id " + id + " does not exist");
				break;
			    }
			    System.out.println("------------------------------------------------------");
			    System.out.println("Modify Color ");
			    System.out.println("id : " + color.getId());
			    System.out.println("name french : " + color.getName().getFr());
			    System.out.println("name english : " + color.getName().getEn());
			    System.out.println("description french : " + color.getDescription().getFr());
			    System.out.println("description english : " + color.getDescription().getEn());
			    System.out.println("image large : " + color.getImage_large());
			    System.out.println("image medium : " + color.getImage_medium());
			    System.out.println("image small : " + color.getImage_small());
			    System.out.println("------------------------------------------------------");
			    System.out.println("0 = return");
			    System.out.println("1 = modify name french");
			    System.out.println("2 = modify name english");
			    System.out.println("3 = modify description french");
			    System.out.println("4 = modify description english");
			    System.out.println("5 = modify image_large");
			    System.out.println("6 = modify image_medium");
			    System.out.println("7 = modify image_small");
			    System.out.println("------------------------------------------------------");
			    System.out.print(">");
			    choice = Tx.readInt();

			    if (choice == 0) {
				break;
			    } else if (choice == 1) {
				System.out.print("name french : ");
				String name = Tx.readString();
				try {
				    utx.begin();
				    admin.setColorNameFr(id, name);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("color successfully updated");
			    } else if (choice == 2) {
				System.out.print("name en : ");
				String name = Tx.readString();
				try {
				    utx.begin();
				    admin.setColorNameEn(id, name);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("color successfully updated");
			    } else if (choice == 3) {
				System.out.print("description french : ");
				String description = Tx.readString();
				try {
				    utx.begin();
				    admin.setColorDescriptionFr(id, description);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("color successfully updated");
			    } else if (choice == 4) {
				System.out.print("description en : ");
				String description = Tx.readString();
				try {
				    utx.begin();
				    admin.setColorDescriptionEn(id, description);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("color successfully updated");
			    } else if (choice == 5) {
				System.out.print("image large : ");
				String image = Tx.readString();
				try {
				    utx.begin();
				    admin.setColorImageLarge(id, image);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("color successfully updated");
			    } else if (choice == 6) {
				System.out.print("image medium : ");
				String image = Tx.readString();
				try {
				    utx.begin();
				    admin.setColorImageMedium(id, image);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("color successfully updated");
			    } else if (choice == 7) {
				System.out.print("image small : ");
				String image = Tx.readString();
				try {
				    utx.begin();
				    admin.setColorImageSmall(id, image);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("color successfully updated");
			    } else {
				System.out.println("Bad choice");
			    }
			} catch (Exception ex) {
			    System.out.println("error : " + ex.getMessage());
			}
		    }
		} else if (choice == 4) {
		    System.out.print("id : ");
		    int id = Tx.readInt();
		    try {
			ColorBean color;
			try {
			    utx.begin();
			    color = admin.getColor(id);
			    utx.commit();
			} catch (Exception ex) {
			    utx.rollback();
			    throw ex;
			}
			if (color == null) {
			    System.out.println("Color id " + id + " does not exist");
			    break;
			}
			try {
			    utx.begin();
			    admin.deleteColor(id);
			    utx.commit();
			} catch (Exception ex) {
			    utx.rollback();
			    System.out.println("error : " + ex.getMessage());
			}
		    } catch (Exception ex) {
			System.out.println("error : " + ex.getMessage());
		    }
		} else {
		    System.out.println("Bad choice");
		}
	    } catch (Exception ex) {
		System.out.println("error : " + ex.getMessage());
	    }
	}
    }

    static void manageCategories(UserTransaction utx, AdminRemote admin) throws Exception {
	while (true) {
	    try {
		System.out.println("------------------------------------------------------");
		System.out.println("Manage Categories");
		System.out.println("0 = return");
		System.out.println("1 = view categories list");
		System.out.println("2 = create a category");
		System.out.println("3 = modify a category");
		System.out.println("4 = delete a category");
		System.out.println("------------------------------------------------------");
		System.out.print(">");

		int choice = Tx.readInt();

		if (choice == 0) {
		    return;
		} else if (choice == 1) {
		    List<CategoryBean> categories = null;
		    try {
			utx.begin();
			categories = admin.getCategories();
			utx.commit();
		    } catch (Exception ex) {
			utx.rollback();
			throw ex;
		    }
		    if (categories != null) {
			Iterator iterator = categories.iterator();
			while (iterator.hasNext()) {
			    CategoryBean category = (CategoryBean) iterator.next();
			    System.out.println("id : " + category.getId());
			    System.out.println("name french : " + category.getName().getFr());
			    System.out.println("name english : " + category.getName().getEn());
			    System.out.println("description french : " + category.getDescription().getFr());
			    System.out.println("description english : " + category.getDescription().getEn());
			    System.out.println("image large : " + category.getImage_large());
			    System.out.println("image medium : " + category.getImage_medium());
			    System.out.println("image small : " + category.getImage_small());
			    System.out.println("");
			}
		    }
		} else if (choice == 2) {
		    System.out.print("name french: ");
		    String name_fr = Tx.readString();
		    System.out.print("name english: ");
		    String name_en = Tx.readString();
		    System.out.print("description french: ");
		    String description_fr = Tx.readString();
		    System.out.print("description english: ");
		    String description_en = Tx.readString();
		    System.out.print("image large : ");
		    String image_large = Tx.readString();
		    System.out.print("image medium : ");
		    String image_medium = Tx.readString();
		    System.out.print("image small : ");
		    String image_small = Tx.readString();
		    try {
			utx.begin();
			admin.createCategory(name_fr, name_en, description_fr, description_en, image_large, image_medium, image_small);
			utx.commit();
		    } catch (Exception ex) {
			utx.rollback();
			throw ex;
		    }
		    System.out.println("category successfully created");
		} else if (choice == 3) {
		    System.out.print("id : ");
		    int id = Tx.readInt();
		    while (true) {
			try {
			    CategoryBean category = null;
			    try {
				utx.begin();
				category = admin.getCategory(id);
				utx.commit();
			    } catch (Exception ex) {
				utx.rollback();
				throw ex;
			    }
			    if (category == null) {
				System.out.println("Category id " + id + " does not exist");
				break;
			    }
			    System.out.println("------------------------------------------------------");
			    System.out.println("Modify Category ");
			    System.out.println("id : " + category.getId());
			    System.out.println("name french : " + category.getName().getFr());
			    System.out.println("name english : " + category.getName().getEn());
			    System.out.println("description french : " + category.getDescription().getFr());
			    System.out.println("description english : " + category.getDescription().getEn());
			    System.out.println("image large : " + category.getImage_large());
			    System.out.println("image medium : " + category.getImage_medium());
			    System.out.println("image small : " + category.getImage_small());
			    System.out.println("------------------------------------------------------");
			    System.out.println("0 = return");
			    System.out.println("1 = modify name french");
			    System.out.println("2 = modify name english");
			    System.out.println("3 = modify description french");
			    System.out.println("4 = modify description english");
			    System.out.println("5 = modify image_large");
			    System.out.println("6 = modify image_medium");
			    System.out.println("7 = modify image_small");
			    System.out.println("------------------------------------------------------");
			    System.out.print(">");
			    choice = Tx.readInt();

			    if (choice == 0) {
				break;
			    } else if (choice == 1) {
				System.out.print("name french : ");
				String name = Tx.readString();
				try {
				    utx.begin();
				    admin.setCategoryNameFr(id, name);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("category successfully updated");
			    } else if (choice == 2) {
				System.out.print("name en : ");
				String name = Tx.readString();
				try {
				    utx.begin();
				    admin.setCategoryNameEn(id, name);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("category successfully updated");
			    } else if (choice == 3) {
				System.out.print("description french : ");
				String description = Tx.readString();
				try {
				    utx.begin();
				    admin.setCategoryDescriptionFr(id, description);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("category successfully updated");
			    } else if (choice == 4) {
				System.out.print("description en : ");
				String description = Tx.readString();
				try {
				    utx.begin();
				    admin.setCategoryDescriptionEn(id, description);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("category successfully updated");
			    } else if (choice == 5) {
				System.out.print("image large : ");
				String image = Tx.readString();
				try {
				    utx.begin();
				    admin.setCategoryImageLarge(id, image);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("category successfully updated");
			    } else if (choice == 6) {
				System.out.print("image medium : ");
				String image = Tx.readString();
				try {
				    utx.begin();
				    admin.setCategoryImageMedium(id, image);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("category successfully updated");
			    } else if (choice == 7) {
				System.out.print("image small : ");
				String image = Tx.readString();
				try {
				    utx.begin();
				    admin.setCategoryImageSmall(id, image);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("category successfully updated");
			    } else {
				System.out.println("Bad choice");
			    }
			} catch (Exception ex) {
			    System.out.println("error : " + ex.getMessage());
			}
		    }
		} else if (choice == 4) {
		    System.out.print("id : ");
		    int id = Tx.readInt();
		    try {
			CategoryBean category;
			try {
			    utx.begin();
			    category = admin.getCategory(id);
			    utx.commit();
			} catch (Exception ex) {
			    utx.rollback();
			    throw ex;
			}
			if (category == null) {
			    System.out.println("Category id " + id + " does not exist");
			    break;
			}
			try {
			    utx.begin();
			    admin.deleteCategory(id);
			    utx.commit();
			} catch (Exception ex) {
			    System.out.println("error : " + ex.getMessage());
			}
		    } catch (Exception ex) {
			System.out.println("error : " + ex.getMessage());
		    }
		} else {
		    System.out.println("Bad choice");
		}
	    } catch (Exception ex) {
		System.out.println("error : " + ex.getMessage());
	    }
	}
    }

    static void manageAges(UserTransaction utx, AdminRemote admin) throws Exception {
	while (true) {
	    try {
		System.out.println("------------------------------------------------------");
		System.out.println("Manage Ages");
		System.out.println("0 = return");
		System.out.println("1 = view ages list");
		System.out.println("2 = create an age");
		System.out.println("3 = modify an age");
		System.out.println("4 = delete an age");
		System.out.println("------------------------------------------------------");
		System.out.print(">");

		int choice = Tx.readInt();

		if (choice == 0) {
		    return;
		} else if (choice == 1) {
		    List<AgeBean> ages = null;
		    try {
			utx.begin();
			ages = admin.getAges();
			utx.commit();
		    } catch (Exception ex) {
			utx.rollback();
			throw ex;
		    }
		    if (ages != null) {
			Iterator iterator = ages.iterator();
			while (iterator.hasNext()) {
			    AgeBean age = (AgeBean) iterator.next();
			    System.out.println("id : " + age.getId());
			    System.out.println("name french : " + age.getName().getFr());
			    System.out.println("name english : " + age.getName().getEn());
			    System.out.println("description french : " + age.getDescription().getFr());
			    System.out.println("description english : " + age.getDescription().getEn());
			    System.out.println("");
			}
		    }
		} else if (choice == 2) {
		    System.out.print("name french: ");
		    String name_fr = Tx.readString();
		    System.out.print("name english: ");
		    String name_en = Tx.readString();
		    System.out.print("description french: ");
		    String description_fr = Tx.readString();
		    System.out.print("description english: ");
		    String description_en = Tx.readString();
		    try {
			utx.begin();
			admin.createAge(name_fr, name_en, description_fr, description_en);
			utx.commit();
		    } catch (Exception ex) {
			utx.rollback();
			throw ex;
		    }
		    System.out.println("age successfully created");
		} else if (choice == 3) {
		    System.out.print("id : ");
		    int id = Tx.readInt();
		    while (true) {
			try {
			    AgeBean age = null;
			    try {
				utx.begin();
				age = admin.getAge(id);
				utx.commit();
			    } catch (Exception ex) {
				utx.rollback();
				throw ex;
			    }
			    if (age == null) {
				System.out.println("Age id " + id + " does not exist");
				break;
			    }
			    System.out.println("------------------------------------------------------");
			    System.out.println("Modify Age ");
			    System.out.println("id : " + age.getId());
			    System.out.println("name french : " + age.getName().getFr());
			    System.out.println("name english : " + age.getName().getEn());
			    System.out.println("description french : " + age.getDescription().getFr());
			    System.out.println("description english : " + age.getDescription().getEn());
			    System.out.println("------------------------------------------------------");
			    System.out.println("0 = return");
			    System.out.println("1 = modify name french");
			    System.out.println("2 = modify name english");
			    System.out.println("3 = modify description french");
			    System.out.println("4 = modify description english");
			    System.out.println("------------------------------------------------------");
			    System.out.print(">");
			    choice = Tx.readInt();

			    if (choice == 0) {
				break;
			    } else if (choice == 1) {
				System.out.print("name french : ");
				String name = Tx.readString();
				try {
				    utx.begin();
				    admin.setAgeNameFr(id, name);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("age successfully updated");
			    } else if (choice == 2) {
				System.out.print("name en : ");
				String name = Tx.readString();
				try {
				    utx.begin();
				    admin.setAgeNameEn(id, name);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("age successfully updated");
			    } else if (choice == 3) {
				System.out.print("description french : ");
				String description = Tx.readString();
				try {
				    utx.begin();
				    admin.setAgeDescriptionFr(id, description);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("age successfully updated");
			    } else if (choice == 4) {
				System.out.print("description en : ");
				String description = Tx.readString();
				try {
				    utx.begin();
				    admin.setAgeDescriptionEn(id, description);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("age successfully updated");
			    } else {
				System.out.println("Bad choice");
			    }
			} catch (Exception ex) {
			    System.out.println("error : " + ex.getMessage());
			}
		    }
		} else if (choice == 4) {
		    System.out.print("id : ");
		    int id = Tx.readInt();
		    try {
			AgeBean age;
			try {
			    utx.begin();
			    age = admin.getAge(id);
			    utx.commit();
			} catch (Exception ex) {
			    utx.rollback();
			    throw ex;
			}
			if (age == null) {
			    System.out.println("Age id " + id + " does not exist");
			    break;
			}
			try {
			    utx.begin();
			    admin.deleteAge(id);
			    utx.commit();
			} catch (Exception ex) {
			    utx.rollback();
			    System.out.println("error : " + ex.getMessage());
			}
		    } catch (Exception ex) {
			System.out.println("error : " + ex.getMessage());
		    }
		} else {
		    System.out.println("Bad choice");
		}
	    } catch (Exception ex) {
		System.out.println("error : " + ex.getMessage());
	    }
	}
    }
    
    static void manageProducts(UserTransaction utx, AdminRemote admin) throws Exception {
	while (true) {
	    try {
		System.out.println("------------------------------------------------------");
		System.out.println("Manage Products");
		System.out.println("0 = return");
		System.out.println("1 = view products list");
		System.out.println("2 = find products by category");
		System.out.println("3 = find products by color");
		System.out.println("4 = find products by label");
		System.out.println("5 = find products by age");
		System.out.println("6 = find products by filter");
		System.out.println("7 = create a product");
		System.out.println("8 = modify a product");
		System.out.println("9 = delete a product");
		System.out.println("------------------------------------------------------");
		System.out.print(">");

		int choice = Tx.readInt();

		if (choice == 0) {
		    return;
		} else if (choice == 1) {
		    List<ProductBean> products = null;
		    try {
			utx.begin();
			products = admin.getProducts();
			utx.commit();
		    } catch (Exception ex) {
			utx.rollback();
			throw ex;
		    }
		    if (products != null) {
			Iterator iterator = products.iterator();
			while (iterator.hasNext()) {
			    ProductBean product = (ProductBean) iterator.next();
			    System.out.println("id : " + product.getId());
			    System.out.println("name french : " + product.getName().getFr());
			    System.out.println("name english : " + product.getName().getEn());
			    System.out.println("description french : " + product.getDescription().getFr());
			    System.out.println("description english : " + product.getDescription().getEn());
			    System.out.println("category : " + product.getCategory().getId());
			    System.out.println("color : " + product.getColor().getId());
			    System.out.println("label : " + product.getLabel().getId());
			    System.out.println("age : " + product.getAge().getId());
			    System.out.println("price : " + product.getPrice());
			    System.out.println("stock : " + product.getStock());
			    System.out.println("image large : " + product.getImage_large());
			    System.out.println("image medium : " + product.getImage_medium());
			    System.out.println("image small : " + product.getImage_small());
			    System.out.println("");
			}
		    }
		} else if (choice == 2) {
		    System.out.print("category : ");
		    int category_id = Tx.readInt();
		    List<ProductBean> products = null;
		    try {
			utx.begin();
			products = admin.getProductsByCategory(category_id);
			utx.commit();
		    } catch (Exception ex) {
			utx.rollback();
			throw ex;
		    }
		    if (products != null) {
			Iterator iterator = products.iterator();
			while (iterator.hasNext()) {
			    ProductBean product = (ProductBean) iterator.next();
			    System.out.println("id : " + product.getId());
			    System.out.println("name french : " + product.getName().getFr());
			    System.out.println("name english : " + product.getName().getEn());
			    System.out.println("description french : " + product.getDescription().getFr());
			    System.out.println("description english : " + product.getDescription().getEn());
			    System.out.println("category : " + product.getCategory().getId());
			    System.out.println("color : " + product.getColor().getId());
			    System.out.println("label : " + product.getLabel().getId());
			    System.out.println("age : " + product.getAge().getId());
			    System.out.println("price : " + product.getPrice());
			    System.out.println("stock : " + product.getStock());
			    System.out.println("image large : " + product.getImage_large());
			    System.out.println("image medium : " + product.getImage_medium());
			    System.out.println("image small : " + product.getImage_small());
			    System.out.println("");
			}
		    }
		} else if (choice == 3) {
		    System.out.print("color : ");
		    int color_id = Tx.readInt();
		    List<ProductBean> products = null;
		    try {
			utx.begin();
			products = admin.getProductsByColor(color_id);
			utx.commit();
		    } catch (Exception ex) {
			utx.rollback();
			throw ex;
		    }
		    if (products != null) {
			Iterator iterator = products.iterator();
			while (iterator.hasNext()) {
			    ProductBean product = (ProductBean) iterator.next();
			    System.out.println("id : " + product.getId());
			    System.out.println("name french : " + product.getName().getFr());
			    System.out.println("name english : " + product.getName().getEn());
			    System.out.println("description french : " + product.getDescription().getFr());
			    System.out.println("description english : " + product.getDescription().getEn());
			    System.out.println("category : " + product.getCategory().getId());
			    System.out.println("color : " + product.getColor().getId());
			    System.out.println("label : " + product.getLabel().getId());
			    System.out.println("age : " + product.getAge().getId());
			    System.out.println("price : " + product.getPrice());
			    System.out.println("stock : " + product.getStock());
			    System.out.println("image large : " + product.getImage_large());
			    System.out.println("image medium : " + product.getImage_medium());
			    System.out.println("image small : " + product.getImage_small());
			    System.out.println("");
			}
		    }
		} else if (choice == 4) {
		    System.out.print("label : ");
		    int label_id = Tx.readInt();
		    List<ProductBean> products = null;
		    try {
			utx.begin();
			products = admin.getProductsByLabel(label_id);
			utx.commit();
		    } catch (Exception ex) {
			utx.rollback();
			throw ex;
		    }
		    if (products != null) {
			Iterator iterator = products.iterator();
			while (iterator.hasNext()) {
			    ProductBean product = (ProductBean) iterator.next();
			    System.out.println("id : " + product.getId());
			    System.out.println("name french : " + product.getName().getFr());
			    System.out.println("name english : " + product.getName().getEn());
			    System.out.println("description french : " + product.getDescription().getFr());
			    System.out.println("description english : " + product.getDescription().getEn());
			    System.out.println("category : " + product.getCategory().getId());
			    System.out.println("color : " + product.getColor().getId());
			    System.out.println("label : " + product.getLabel().getId());
			    System.out.println("age : " + product.getAge().getId());
			    System.out.println("price : " + product.getPrice());
			    System.out.println("stock : " + product.getStock());
			    System.out.println("image large : " + product.getImage_large());
			    System.out.println("image medium : " + product.getImage_medium());
			    System.out.println("image small : " + product.getImage_small());
			    System.out.println("");
			}
		    }
		} else if (choice == 5) {
		    System.out.print("age : ");
		    int age_id = Tx.readInt();
		    List<ProductBean> products = null;
		    try {
			utx.begin();
			products = admin.getProductsByAge(age_id);
			utx.commit();
		    } catch (Exception ex) {
			utx.rollback();
			throw ex;
		    }
		    if (products != null) {
			Iterator iterator = products.iterator();
			while (iterator.hasNext()) {
			    ProductBean product = (ProductBean) iterator.next();
			    System.out.println("id : " + product.getId());
			    System.out.println("name french : " + product.getName().getFr());
			    System.out.println("name english : " + product.getName().getEn());
			    System.out.println("description french : " + product.getDescription().getFr());
			    System.out.println("description english : " + product.getDescription().getEn());
			    System.out.println("category : " + product.getCategory().getId());
			    System.out.println("color : " + product.getColor().getId());
			    System.out.println("label : " + product.getLabel().getId());
			    System.out.println("age : " + product.getAge().getId());
			    System.out.println("price : " + product.getPrice());
			    System.out.println("stock : " + product.getStock());
			    System.out.println("image large : " + product.getImage_large());
			    System.out.println("image medium : " + product.getImage_medium());
			    System.out.println("image small : " + product.getImage_small());
			    System.out.println("");
			}
		    }
		} else if (choice == 6) {
		    String[] ids;
		    System.out.print("Categories (coma separated) : ");
		    String categories = Tx.readString();
		    List<Integer> category_ids = new ArrayList<Integer>();
		    if (!categories.equals("")) {
			ids = categories.split(",");
			for (int i = 0; i < ids.length; i++) {
			    category_ids.add(Integer.valueOf(ids[i]));
			}
		    }
		    System.out.print("Colors (coma separated) : ");
		    String colors = Tx.readString();
		    List<Integer> color_ids = new ArrayList<Integer>();
		    if (!colors.equals("")) {
			ids = colors.split(",");
			for (int i = 0; i < ids.length; i++) {
			    color_ids.add(Integer.valueOf(ids[i]));
			}
		    }
		    System.out.print("Labels (coma separated) : ");
		    String labels = Tx.readString();
		    List<Integer> label_ids = new ArrayList<Integer>();
		    if (!labels.equals("")) {
			ids = labels.split(",");
			for (int i = 0; i < ids.length; i++) {
			    label_ids.add(Integer.valueOf(ids[i]));
			}
		    }
		    System.out.print("Age (coma separated) : ");
		    String ages = Tx.readString();
		    List<Integer> age_ids = new ArrayList<Integer>();
		    if (!ages.equals("")) {
			ids = ages.split(",");
			for (int i = 0; i < ids.length; i++) {
			    age_ids.add(Integer.valueOf(ids[i]));
			}
		    }
		    System.out.print("operation (and/or) : ");
		    String operation = Tx.readString();
		    boolean and;
		    if (operation.equals("and")) {
			and = true;
		    } else if (operation.equals("or")) {
			and = false;
		    } else {
			throw new Exception("bad operation");
		    }
		    List<ProductBean> products = null;
		    try {
			utx.begin();
			products = admin.getProductsByFilter(category_ids, color_ids, label_ids, and);
			utx.commit();
		    } catch (Exception ex) {
			utx.rollback();
			throw ex;
		    }
		    if (products != null) {
			Iterator iterator = products.iterator();
			while (iterator.hasNext()) {
			    ProductBean product = (ProductBean) iterator.next();
			    System.out.println("id : " + product.getId());
			    System.out.println("name french : " + product.getName().getFr());
			    System.out.println("name english : " + product.getName().getEn());
			    System.out.println("description french : " + product.getDescription().getFr());
			    System.out.println("description english : " + product.getDescription().getEn());
			    System.out.println("category : " + product.getCategory().getId());
			    System.out.println("color : " + product.getColor().getId());
			    System.out.println("label : " + product.getLabel().getId());
			    System.out.println("age : " + product.getAge().getId());
			    System.out.println("price : " + product.getPrice());
			    System.out.println("stock : " + product.getStock());
			    System.out.println("image large : " + product.getImage_large());
			    System.out.println("image medium : " + product.getImage_medium());
			    System.out.println("image small : " + product.getImage_small());
			    System.out.println("");
			}
		    }
		} else if (choice == 7) {
		    System.out.print("name french: ");
		    String name_fr = Tx.readString();
		    System.out.print("name english: ");
		    String name_en = Tx.readString();
		    System.out.print("description french: ");
		    String description_fr = Tx.readString();
		    System.out.print("description english: ");
		    String description_en = Tx.readString();
		    System.out.print("category : ");
		    int category_id = Tx.readInt();
		    System.out.print("color : ");
		    int color_id = Tx.readInt();
		    System.out.print("label : ");
		    int label_id = Tx.readInt();
		    System.out.print("age : ");
		    int age_id = Tx.readInt();
		    System.out.print("price : ");
		    float price = Tx.readFloat();
		    System.out.print("stock : ");
		    int stock = Tx.readInt();
		    System.out.print("image large : ");
		    String image_large = Tx.readString();
		    System.out.print("image medium : ");
		    String image_medium = Tx.readString();
		    System.out.print("image small : ");
		    String image_small = Tx.readString();
		    try {
			utx.begin();
			admin.createProduct(name_fr, name_en, description_fr, description_en, category_id, color_id, label_id, age_id,
				price, stock, image_large, image_medium, image_small);
			utx.commit();
		    } catch (Exception ex) {
			utx.rollback();
			throw ex;
		    }
		    System.out.println("product successfully created");
		} else if (choice == 8) {
		    System.out.print("id : ");
		    int id = Tx.readInt();
		    while (true) {
			try {
			    ProductBean product = null;
			    try {
				utx.begin();
				product = admin.getProduct(id);
				utx.commit();
			    } catch (Exception ex) {
				utx.rollback();
				throw ex;
			    }
			    if (product == null) {
				System.out.println("Product id " + id + " does not exist");
				break;
			    }
			    System.out.println("------------------------------------------------------");
			    System.out.println("Modify Product ");
			    System.out.println("id : " + product.getId());
			    System.out.println("name french : " + product.getName().getFr());
			    System.out.println("name english : " + product.getName().getEn());
			    System.out.println("description french : " + product.getDescription().getFr());
			    System.out.println("description english : " + product.getDescription().getEn());
			    System.out.println("category : " + product.getCategory().getId());
			    System.out.println("color : " + product.getColor().getId());
			    System.out.println("label : " + product.getLabel().getId());
			    System.out.println("age : " + product.getAge().getId());
			    System.out.println("price : " + product.getPrice());
			    System.out.println("stock : " + product.getStock());
			    System.out.println("image large : " + product.getImage_large());
			    System.out.println("image medium : " + product.getImage_medium());
			    System.out.println("image small : " + product.getImage_small());
			    System.out.println("------------------------------------------------------");
			    System.out.println("0 = return");
			    System.out.println("1 = modify name french");
			    System.out.println("2 = modify name english");
			    System.out.println("3 = modify description french");
			    System.out.println("4 = modify description english");
			    System.out.println("5 = modify category");
			    System.out.println("6 = modify color");
			    System.out.println("7 = modify label");
			    System.out.println("8 = modify age");
			    System.out.println("9 = modify price");
			    System.out.println("10 = modify stock");
			    System.out.println("11 = modify image_large");
			    System.out.println("12 = modify image_medium");
			    System.out.println("13 = modify image_small");
			    System.out.println("------------------------------------------------------");
			    System.out.print(">");
			    choice = Tx.readInt();

			    if (choice == 0) {
				break;
			    } else if (choice == 1) {
				System.out.print("name french : ");
				String name = Tx.readString();
				try {
				    utx.begin();
				    admin.setProductNameFr(id, name);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("product successfully updated");
			    } else if (choice == 2) {
				System.out.print("name en : ");
				String name = Tx.readString();
				try {
				    utx.begin();
				    admin.setProductNameEn(id, name);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("product successfully updated");
			    } else if (choice == 3) {
				System.out.print("description french : ");
				String description = Tx.readString();
				try {
				    utx.begin();
				    admin.setProductDescriptionFr(id, description);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("product successfully updated");
			    } else if (choice == 4) {
				System.out.print("description en : ");
				String description = Tx.readString();
				try {
				    utx.begin();
				    admin.setProductDescriptionEn(id, description);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("product successfully updated");
			    } else if (choice == 5) {
				System.out.print("category : ");
				int category_id = Tx.readInt();
				try {
				    utx.begin();
				    admin.setProductCategory(id, category_id);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("product successfully updated");
			    } else if (choice == 6) {
				System.out.print("color : ");
				int color_id = Tx.readInt();
				try {
				    utx.begin();
				    admin.setProductColor(id, color_id);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("product successfully updated");
			    } else if (choice == 7) {
				System.out.print("label : ");
				int label_id = Tx.readInt();
				try {
				    utx.begin();
				    admin.setProductLabel(id, label_id);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("product successfully updated");
			    } else if (choice == 8) {
				System.out.print("age : ");
				int age_id = Tx.readInt();
				try {
				    utx.begin();
				    admin.setProductAge(id, age_id);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("product successfully updated");
			    } else if (choice == 9) {
				System.out.print("price : ");
				float price = Tx.readFloat();
				try {
				    utx.begin();
				    admin.setProductPrice(id, price);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("product successfully updated");
			    } else if (choice == 10) {
				System.out.print("stock : ");
				int stock = Tx.readInt();
				try {
				    utx.begin();
				    admin.setProductStock(id, stock);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("product successfully updated");
			    } else if (choice == 11) {
				System.out.print("image large : ");
				String image = Tx.readString();
				try {
				    utx.begin();
				    admin.setProductImageLarge(id, image);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("product successfully updated");
			    } else if (choice == 12) {
				System.out.print("image medium : ");
				String image = Tx.readString();
				try {
				    utx.begin();
				    admin.setProductImageMedium(id, image);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("product successfully updated");
			    } else if (choice == 13) {
				System.out.print("image small : ");
				String image = Tx.readString();
				try {
				    utx.begin();
				    admin.setProductImageSmall(id, image);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("product successfully updated");
			    } else {
				System.out.println("Bad choice");
			    }
			} catch (Exception ex) {
			    System.out.println("error : " + ex.getMessage());
			}
		    }
		} else if (choice == 9) {
		    System.out.print("id : ");
		    int id = Tx.readInt();
		    try {
			ProductBean product;
			try {
			    utx.begin();
			    product = admin.getProduct(id);
			    utx.commit();
			} catch (Exception ex) {
			    utx.rollback();
			    throw ex;
			}
			if (product == null) {
			    System.out.println("Product id " + id + " does not exist");
			    break;
			}
			try {
			    utx.begin();
			    admin.deleteProduct(id);
			    utx.commit();
			} catch (Exception ex) {
			    utx.rollback();
			    System.out.println("error : " + ex.getMessage());
			}
		    } catch (Exception ex) {
			System.out.println("error : " + ex.getMessage());
		    }
		} else {
		    System.out.println("Bad choice");
		}
	    } catch (Exception ex) {
		System.out.println("error : " + ex.getMessage());
	    }
	}
    }

    static void manageCustomers(UserTransaction utx, AdminRemote admin) throws Exception {
	while (true) {
	    try {
		System.out.println("------------------------------------------------------");
		System.out.println("Manage Customers");
		System.out.println("0 = return");
		System.out.println("1 = view customer list");
		System.out.println("2 = create a customer");
		System.out.println("3 = modify a customer");
		System.out.println("4 = delete a customer");
		System.out.println("------------------------------------------------------");
		System.out.print(">");

		int choice = Tx.readInt();

		if (choice == 0) {
		    return;
		} else if (choice == 1) {
		    List<AccountBean> accounts = null;
		    try {
			utx.begin();
			accounts = admin.getAccounts();
			utx.commit();
		    } catch (Exception ex) {
			utx.rollback();
			throw ex;
		    }
		    if (accounts != null) {
			Iterator iterator = accounts.iterator();
			while (iterator.hasNext()) {
			    AccountBean account = (AccountBean) iterator.next();
			    System.out.println("------------------------------------------------------");
			    System.out.println("E-mail address: " + account.getEmailAddress());
			    System.out.println("Password: " + account.getPassword());
			    System.out.println("First name: " + account.getFirstName());
			    System.out.println("Last name: " + account.getLastName());
			    System.out.println("Address Line 1: " + account.getAddressLine1());
			    System.out.println("Address Line 2: " + account.getAddressLine2());
			    System.out.println("Address Line 3: " + account.getAddressLine3());
			    System.out.println("Zip Code: " + account.getZipCode());
			    System.out.println("City: " + account.getCity());
			    System.out.println("Phone number: " + account.getPhoneNumber());
			    System.out.println("Fax number: " + account.getFaxNumber());
			    System.out.println("");
			}
		    } else {
			System.out.println("There is no customer account");
		    }
		} else if (choice == 2) {
		    System.out.print("E-mail address: ");
		    String email = Tx.readString();
		    System.out.print("Password: ");
		    String password = Tx.readString();
		    System.out.print("First name: ");
		    String firstName = Tx.readString();
		    System.out.print("Last name: ");
		    String lastName = Tx.readString();
		    System.out.print("Address Line 1: ");
		    String addressLine1 = Tx.readString();
		    System.out.print("Address Line 2: ");
		    String addressLine2 = Tx.readString();
		    System.out.print("Address Line 3: ");
		    String addressLine3 = Tx.readString();
		    System.out.print("Zip Code: ");
		    int zipCode = Tx.readInt();
		    System.out.print("City: ");
		    String city = Tx.readString();
		    System.out.print("Phone number: ");
		    String phoneNumber = Tx.readString();
		    System.out.print("Fax number: ");
		    String faxNumber = Tx.readString();

		    try {
			utx.begin();
			admin.createAccount(email, password, firstName, lastName, addressLine1, addressLine2, addressLine3, zipCode, city,
				phoneNumber, faxNumber);
			utx.commit();
		    } catch (Exception ex) {
			utx.rollback();
			throw ex;
		    }
		    System.out.println("Account successfully created");
		} else if (choice == 3) {
		    System.out.print("E-mail address: ");
		    String email = Tx.readString();
		    while (true) {
			try {
			    AccountBean account;
			    try {
				utx.begin();
				account = admin.getAccount(email);
				utx.commit();
			    } catch (Exception ex) {
				utx.rollback();
				throw ex;
			    }
			    if (account == null) {
				System.out.println("Customer with e-mail address \"" + email + "\" does not exist");
				break;
			    }

			    System.out.println("Modify account with e-mail address \"" + account.getEmailAddress() + "\"");
			    System.out.println("1 - Password: " + account.getPassword());
			    System.out.println("2 - First name: " + account.getFirstName());
			    System.out.println("3 - Last name: " + account.getLastName());
			    System.out.println("4 - Address Line 1: " + account.getAddressLine1());
			    System.out.println("5 - Address Line 2: " + account.getAddressLine2());
			    System.out.println("6 - Address Line 3: " + account.getAddressLine3());
			    System.out.println("7 - Zip Code: " + account.getZipCode());
			    System.out.println("8 - City: " + account.getCity());
			    System.out.println("9 - Phone number: " + account.getPhoneNumber());
			    System.out.println("10 - Fax number: " + account.getFaxNumber());
			    System.out.println("\nPlease enter the number of the field to be modified, or type 0 to exit");
			    System.out.print(">");
			    choice = Tx.readInt();

			    if (choice == 0) {
				break;
			    } else if (choice == 1) {
				System.out.print("Password: ");
				String newValue = Tx.readString();
				try {
				    utx.begin();
				    admin.setAccountPassword(email, newValue);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("Account successfully updated");
			    } else if (choice == 2) {
				System.out.print("First name: ");
				String newValue = Tx.readString();
				try {
				    utx.begin();
				    admin.setAccountFirstName(email, newValue);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("Account successfully updated");
			    } else if (choice == 3) {
				System.out.print("Last name: ");
				String newValue = Tx.readString();
				try {
				    utx.begin();
				    admin.setAccountLastName(email, newValue);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("Account successfully updated");
			    } else if (choice == 4) {
				System.out.print("Address Line 1: ");
				String newValue = Tx.readString();
				try {
				    utx.begin();
				    admin.setAccountAddressLine1(email, newValue);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("Account successfully updated");
			    } else if (choice == 5) {
				System.out.print("Address Line 2: ");
				String newValue = Tx.readString();
				try {
				    utx.begin();
				    admin.setAccountAddressLine2(email, newValue);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("Account successfully updated");
			    } else if (choice == 6) {
				System.out.print("Address Line 3: ");
				String newValue = Tx.readString();
				try {
				    utx.begin();
				    admin.setAccountAddressLine3(email, newValue);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("Account successfully updated");
			    } else if (choice == 7) {
				System.out.print("Zip Code: ");
				int newValue = Tx.readInt();
				try {
				    utx.begin();
				    admin.setAccountZipCode(email, newValue);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("Account successfully updated");
			    } else if (choice == 8) {
				System.out.print("City: ");
				String newValue = Tx.readString();
				try {
				    utx.begin();
				    admin.setAccountCity(email, newValue);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("Account successfully updated");
			    } else if (choice == 9) {
				System.out.print("Phone number: ");
				String newValue = Tx.readString();
				try {
				    utx.begin();
				    admin.setAccountPhoneNumber(email, newValue);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("Account successfully updated");
			    } else if (choice == 10) {
				System.out.print("Fax number: ");
				String newValue = Tx.readString();
				try {
				    utx.begin();
				    admin.setAccountFaxNumber(email, newValue);
				    utx.commit();
				} catch (Exception ex) {
				    utx.rollback();
				    throw ex;
				}
				System.out.println("Account successfully updated");
			    } else {
				System.out.println("Bad choice");
			    }
			} catch (Exception ex) {
			    System.out.println("Error: " + ex.getMessage());
			}
		    }
		} else if (choice == 4) {
		    System.out.print("E-mail address: ");
		    String email = Tx.readString();
		    AccountBean account;
		    try {
			utx.begin();
			account = admin.getAccount(email);
			utx.commit();
		    } catch (Exception ex) {
			utx.rollback();
			throw ex;
		    }
		    if (account == null) {
			System.out.println("Customer with e-mail address \"" + email + "\" does not exist");
			break;
		    }
		    utx.begin();
		    admin.deleteAccount(email);
		    utx.commit();
		} else {
		    System.out.println("Bad choice");
		}
	    } catch (Exception ex) {
		System.out.println("error : " + ex.getMessage());
	    }
	}
    }
}
