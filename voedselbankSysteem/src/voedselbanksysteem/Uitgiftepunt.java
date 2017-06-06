/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voedselbanksysteem;

/**
 *
 * @author Cas_Ros
 */
public class Uitgiftepunt {
    private int id;
    private String contactpersoon;
    private int telefoonnr;
    private String email;
    private int maximumCapaciteit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContactpersoon() {
        return contactpersoon;
    }

    public void setContactpersoon(String contactpersoon) {
        this.contactpersoon = contactpersoon;
    }

    public int getTelefoonnr() {
        return telefoonnr;
    }

    public void setTelefoonnr(int telefoonnr) {
        this.telefoonnr = telefoonnr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMaximumCapaciteit() {
        return maximumCapaciteit;
    }

    public void setMaximumCapaciteit(int maximumCapaciteit) {
        this.maximumCapaciteit = maximumCapaciteit;
    }
    
}
