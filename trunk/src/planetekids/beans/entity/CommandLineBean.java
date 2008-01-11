package planetekids.beans.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQuery(name = "getCommandLines", query = "select o FROM CommandLineBean o")
public class CommandLineBean implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @ManyToOne(optional=false)
    private CommandBean command;
    
    @OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL, optional=false)
    private LocaleBean name;
     
    private float price;
    private int number;

    public CommandLineBean() {
        
    }

    public CommandLineBean(CommandBean command, LocaleBean name, float price, int number) {
        this();
        this.setCommand(command);
        this.setName(name);
        this.setPrice(price);
        this.setNumber(number);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CommandBean getCommand() {
        return command;
    }

    public void setCommand(CommandBean command) {
        this.command = command;
    }

    public LocaleBean getName() {
        return name;
    }

    public void setName(LocaleBean name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
   
    public String getName(String locale) {
        if (locale.equals("fr"))
            return name.getFr();
        else
            return name.getEn();
    }
}
