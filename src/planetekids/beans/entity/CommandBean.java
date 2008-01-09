package planetekids.beans.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name = "getCommands", query = "select o FROM CommandBean o")
public class CommandBean implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @ManyToOne(optional=false)
    private AccountBean account;
    
    @OneToMany(mappedBy="command", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private Set<CommandLineBean> command_lines = new HashSet<CommandLineBean>();

    private Date date;
    private float shipping;
    
    public CommandBean() {
        
    }

    public CommandBean(AccountBean account, Date date, float shipping) {
        this();
        this.setAccount(account);
        this.setDate(date);
        this.setShipping(shipping);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AccountBean getAccount() {
        return account;
    }

    public void setAccount(AccountBean account) {
        this.account = account;
    }

    public Set<CommandLineBean> getCommand_lines() {
        return command_lines;
    }

    public void setCommand_lines(Set<CommandLineBean> command_lines) {
        this.command_lines = command_lines;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getShipping() {
        return shipping;
    }

    public void setShipping(float shipping) {
        this.shipping = shipping;
    }
    
    /*@PreRemove
    public void titi() {

        System.out.println("removing command id : " +this.getId());
    }
    
    @PostRemove
    public void toto(){
	System.out.println("Command id : " + this.getId() + " removed");
    }*/
}
