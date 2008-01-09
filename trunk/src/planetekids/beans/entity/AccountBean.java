/**
* eCommerce Application Sample for J2EE Training 
* Implementation of AccountBean
* @author Fabienne Boyer - Didier Donsez
* may 2006
*/

package planetekids.beans.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name = "getAccounts", query = "select o FROM AccountBean o")
public class AccountBean implements java.io.Serializable {

    @Id
    private String emailAddress;
    
    @OneToMany(mappedBy="account", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private Set<CommandBean> commands = new HashSet<CommandBean>();
    
    private String password;
    private String firstName;
    private String lastName;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private int zipCode;
    private String city;
    private String phoneNumber;
    private String faxNumber;
  
    public AccountBean() {
        
    }
    
    public AccountBean(String emailAddress) {
        this.setEmailAddress(emailAddress);
    }


    public String getEmailAddress() {
	return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
	this.emailAddress = emailAddress;
    }
    
    public Set<CommandBean> getCommands() {
        return commands;
    }

    public void setCommands(Set<CommandBean> commands) {
        this.commands = commands;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getAddressLine1() {
	return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
	this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
	return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
	this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
	return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
	this.addressLine3 = addressLine3;
    }

    public int getZipCode() {
	return zipCode;
    }

    public void setZipCode(int zipCode) {
	this.zipCode = zipCode;
    }

    public String getCity() {
	return city;
    }

    public void setCity(String city) {
	this.city = city;
    }

    public String getPhoneNumber() {
	return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
    }

    public String getFaxNumber() {
	return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
	this.faxNumber = faxNumber;
    }
    
    /*@PreRemove
    public void titi(){
	System.out.println("removing account id : " +this.getEmailAddress());
    }
    
    @PostRemove
    public void toto(){
	System.out.println("account id : " + this.getEmailAddress() + " removed");
    }*/

}
