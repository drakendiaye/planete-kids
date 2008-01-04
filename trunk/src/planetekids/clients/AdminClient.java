package planetekids.clients;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.transaction.UserTransaction;
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
            initialContext = initialContext = new InitialContext();
            
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
                System.out.println("4 = manage products");
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
                    manageProducts(utx, admin);
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
                    if(labels != null) {
                        Iterator iterator = labels.iterator();
                        while(iterator.hasNext()) {
                            LabelBean label = (LabelBean)iterator.next();
                            System.out.println("id : " + label.getId());
                            System.out.println("name french : " + label.getName().getFr());
                            System.out.println("name english : " + label.getName().getEn());
                            System.out.println("site : " + label.getSite());
                            System.out.println("image large : " + label.getImage_large());
                            System.out.println("image medium : " + label.getImage_medium());
                            System.out.println("image small : " + label.getImage_small());
                            System.out.println("");
                        }
                    }
                } else if (choice == 2) {
                    System.out.print("name french: ");
                    String name_fr = Tx.readString();
                    System.out.print("name english: ");
                    String name_en = Tx.readString();
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
                        admin.createLabel(name_fr, name_en, site, image_large, image_medium, image_small);
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
                            if(label == null) {
                                System.out.println("Label id " + id + " does not exist");
                                break;
                            }
                            System.out.println("------------------------------------------------------");
                            System.out.println("Modify Label ");
                            System.out.println("id : " + label.getId());
                            System.out.println("name french : " + label.getName().getFr());
                            System.out.println("name english : " + label.getName().getEn());
                            System.out.println("site : " + label.getSite());
                            System.out.println("image large : " + label.getImage_large());
                            System.out.println("image medium : " + label.getImage_medium());
                            System.out.println("image small : " + label.getImage_small());
                            System.out.println("------------------------------------------------------");
                            System.out.println("0 = return");
                            System.out.println("1 = modify name french");
                            System.out.println("2 = modify name english");
                            System.out.println("3 = modify site");
                            System.out.println("4 = modify image_large");
                            System.out.println("5 = modify image_medium");
                            System.out.println("6 = modify image_small");
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
                                    admin.setLabelNameFr(id, name);
                                    utx.commit();
                                } catch (Exception ex) {
                                    utx.rollback();
                                    throw ex;
                                }
                                System.out.println("label successfully updated");
                            } else if (choice == 2) {
                                System.out.print("name en : ");
                                String name = Tx.readString();
                                try {
                                    utx.begin();
                                    admin.setLabelNameEn(id, name);
                                    utx.commit();
                                } catch (Exception ex) {
                                    utx.rollback();
                                    throw ex;
                                }
                                System.out.println("label successfully updated");
                            } else if (choice == 3) {
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
                            } else if (choice == 4) {
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
                            } else if (choice == 5) {
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
                            } else if (choice == 6) {
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
                } else {
                    System.out.println("Bad choice");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                //System.out.println("error : " + ex.getMessage());
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
                    if(colors != null) {
                        Iterator iterator = colors.iterator();
                        while(iterator.hasNext()) {
                            ColorBean color = (ColorBean)iterator.next();
                            System.out.println("id : " + color.getId());
                            System.out.println("name french : " + color.getName().getFr());
                            System.out.println("name english : " + color.getName().getEn());
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
                    System.out.print("image large : ");
                    String image_large = Tx.readString();
                    System.out.print("image medium : ");
                    String image_medium = Tx.readString();
                    System.out.print("image small : ");
                    String image_small = Tx.readString();
                    try {
                        utx.begin();
                        admin.createColor(name_fr, name_en, image_large, image_medium, image_small);
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
                            if(color == null) {
                                System.out.println("Color id " + id + " does not exist");
                                break;
                            }
                            System.out.println("------------------------------------------------------");
                            System.out.println("Modify Color ");
                            System.out.println("id : " + color.getId());
                            System.out.println("name french : " + color.getName().getFr());
                            System.out.println("name english : " + color.getName().getEn());
                            System.out.println("image large : " + color.getImage_large());
                            System.out.println("image medium : " + color.getImage_medium());
                            System.out.println("image small : " + color.getImage_small());
                            System.out.println("------------------------------------------------------");
                            System.out.println("0 = return");
                            System.out.println("1 = modify name french");
                            System.out.println("2 = modify name english");
                            System.out.println("3 = modify image_large");
                            System.out.println("4 = modify image_medium");
                            System.out.println("5 = modify image_small");
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
                            } else if (choice == 4) {
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
                            } else if (choice == 5) {
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
                    if(categories != null) {
                        Iterator iterator = categories.iterator();
                        while(iterator.hasNext()) {
                            CategoryBean category = (CategoryBean)iterator.next();
                            System.out.println("id : " + category.getId());
                            System.out.println("name french : " + category.getName().getFr());
                            System.out.println("name english : " + category.getName().getEn());
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
                    System.out.print("image large : ");
                    String image_large = Tx.readString();
                    System.out.print("image medium : ");
                    String image_medium = Tx.readString();
                    System.out.print("image small : ");
                    String image_small = Tx.readString();
                    try {
                        utx.begin();
                        admin.createCategory(name_fr, name_en, image_large, image_medium, image_small);
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
                            if(category == null) {
                                System.out.println("Category id " + id + " does not exist");
                                break;
                            }
                            System.out.println("------------------------------------------------------");
                            System.out.println("Modify Category ");
                            System.out.println("id : " + category.getId());
                            System.out.println("name french : " + category.getName().getFr());
                            System.out.println("name english : " + category.getName().getEn());
                            System.out.println("image large : " + category.getImage_large());
                            System.out.println("image medium : " + category.getImage_medium());
                            System.out.println("image small : " + category.getImage_small());
                            System.out.println("------------------------------------------------------");
                            System.out.println("0 = return");
                            System.out.println("1 = modify name french");
                            System.out.println("2 = modify name english");
                            System.out.println("3 = modify image_large");
                            System.out.println("4 = modify image_medium");
                            System.out.println("5 = modify image_small");
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
                            } else if (choice == 4) {
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
                            } else if (choice == 5) {
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
                System.out.println("5 = find products by filter");
                System.out.println("6 = create a product");
                System.out.println("7 = modify a product");
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
                    if(products != null) {
                        Iterator iterator = products.iterator();
                        while(iterator.hasNext()) {
                            ProductBean product = (ProductBean)iterator.next();
                            System.out.println("id : " + product.getId());
                            System.out.println("name french : " + product.getName().getFr());
                            System.out.println("name english : " + product.getName().getEn());
                            System.out.println("description french : " + product.getDescription().getFr());
                            System.out.println("description english : " + product.getDescription().getEn());
                            System.out.println("category : " + product.getCategory().getId());
                            System.out.println("color : " + product.getColor().getId());
                            System.out.println("label : " + product.getLabel().getId());
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
                    if(products != null) {
                        Iterator iterator = products.iterator();
                        while(iterator.hasNext()) {
                            ProductBean product = (ProductBean)iterator.next();
                            System.out.println("id : " + product.getId());
                            System.out.println("name french : " + product.getName().getFr());
                            System.out.println("name english : " + product.getName().getEn());
                            System.out.println("description french : " + product.getDescription().getFr());
                            System.out.println("description english : " + product.getDescription().getEn());
                            System.out.println("category : " + product.getCategory().getId());
                            System.out.println("color : " + product.getColor().getId());
                            System.out.println("label : " + product.getLabel().getId());
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
                    if(products != null) {
                        Iterator iterator = products.iterator();
                        while(iterator.hasNext()) {
                            ProductBean product = (ProductBean)iterator.next();
                            System.out.println("id : " + product.getId());
                            System.out.println("name french : " + product.getName().getFr());
                            System.out.println("name english : " + product.getName().getEn());
                            System.out.println("description french : " + product.getDescription().getFr());
                            System.out.println("description english : " + product.getDescription().getEn());
                            System.out.println("category : " + product.getCategory().getId());
                            System.out.println("color : " + product.getColor().getId());
                            System.out.println("label : " + product.getLabel().getId());
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
                    if(products != null) {
                        Iterator iterator = products.iterator();
                        while(iterator.hasNext()) {
                            ProductBean product = (ProductBean)iterator.next();
                            System.out.println("id : " + product.getId());
                            System.out.println("name french : " + product.getName().getFr());
                            System.out.println("name english : " + product.getName().getEn());
                            System.out.println("description french : " + product.getDescription().getFr());
                            System.out.println("description english : " + product.getDescription().getEn());
                            System.out.println("category : " + product.getCategory().getId());
                            System.out.println("color : " + product.getColor().getId());
                            System.out.println("label : " + product.getLabel().getId());
                            System.out.println("price : " + product.getPrice());
                            System.out.println("stock : " + product.getStock());
                            System.out.println("image large : " + product.getImage_large());
                            System.out.println("image medium : " + product.getImage_medium());
                            System.out.println("image small : " + product.getImage_small());
                            System.out.println("");
                        }
                    }
                } else if (choice == 5) {
                    String[] ids;
                    System.out.print("Categories (coma separated) : ");
                    String categories = Tx.readString();
                    List<Integer> category_ids = new ArrayList<Integer>();
                    if(!categories.equals("")) {
                        ids = categories.split(",");
                        for(int i=0;i<ids.length;i++) {
                            category_ids.add(Integer.valueOf(ids[i]));
                        }
                    }
                    System.out.print("Colors (coma separated) : ");
                    String colors = Tx.readString();
                    List<Integer> color_ids = new ArrayList<Integer>();
                    if(!colors.equals("")) {
                        ids = colors.split(",");
                        for(int i=0;i<ids.length;i++) {
                            color_ids.add(Integer.valueOf(ids[i]));
                        }
                    }
                    System.out.print("Labels (coma separated) : ");
                    String labels = Tx.readString();
                    List<Integer> label_ids = new ArrayList<Integer>();
                    if(!labels.equals("")) {
                        ids = labels.split(",");
                        for(int i=0;i<ids.length;i++) {
                            label_ids.add(Integer.valueOf(ids[i]));
                        }
                    }
                    System.out.print("operation (and/or) : ");
                    String operation = Tx.readString();
                    boolean and;
                    if(operation.equals("and")) {
                        and = true;
                    } else if(operation.equals("or")) {
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
                    if(products != null) {
                        Iterator iterator = products.iterator();
                        while(iterator.hasNext()) {
                            ProductBean product = (ProductBean)iterator.next();
                            System.out.println("id : " + product.getId());
                            System.out.println("name french : " + product.getName().getFr());
                            System.out.println("name english : " + product.getName().getEn());
                            System.out.println("description french : " + product.getDescription().getFr());
                            System.out.println("description english : " + product.getDescription().getEn());
                            System.out.println("category : " + product.getCategory().getId());
                            System.out.println("color : " + product.getColor().getId());
                            System.out.println("label : " + product.getLabel().getId());
                            System.out.println("price : " + product.getPrice());
                            System.out.println("stock : " + product.getStock());
                            System.out.println("image large : " + product.getImage_large());
                            System.out.println("image medium : " + product.getImage_medium());
                            System.out.println("image small : " + product.getImage_small());
                            System.out.println("");
                        }
                    }
                } else if (choice == 6) {
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
                        admin.createProduct(name_fr, name_en, description_fr, description_en, category_id, color_id, label_id, price, stock, image_large, image_medium, image_small);
                        utx.commit();
                    } catch (Exception ex) {
                        utx.rollback();
                        throw ex;
                    }
                    System.out.println("product successfully created");
                } else if (choice == 7) {
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
                            if(product == null) {
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
                            System.out.println("8 = modify price");
                            System.out.println("9 = modify stock");
                            System.out.println("10 = modify image_large");
                            System.out.println("11 = modify image_medium");
                            System.out.println("12 = modify image_small");
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
                            } else if (choice == 9) {
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
                            } else if (choice == 10) {
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
                            } else if (choice == 11) {
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
                            } else if (choice == 12) {
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
                } else {
                    System.out.println("Bad choice");
                }
            } catch (Exception ex) {
                System.out.println("error : " + ex.getMessage());
            }
        }
    }
}
