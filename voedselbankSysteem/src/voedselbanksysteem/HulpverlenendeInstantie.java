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
public class HulpverlenendeInstantie {
    private String naam;
    private String contactpersoonVoornaam;
    private String contactpersoonTussenvoegsel;
    private String contactpersoonAchternaam;
    private String email;
    private int telefoonnr;

    public String getNaam() {
        return naam;
    }
    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String[] getContactpersoonNaam(){
        String[] naam = new String[3];
        naam[0] = contactpersoonVoornaam;
        naam[1] = contactpersoonTussenvoegsel;
        naam[2] = contactpersoonAchternaam;
        return naam;
    }
    public void setContactpersoonNaam(String name){
        char c;
        int i;
        int endFirstname;
        int endLastname;
        
        //get firstname from naam
        i = 0;
        do{
            c = name.charAt(i);
            contactpersoonVoornaam += c;
            i++;
        }while(c != ' ');
        
        //remember end position and move of the space
        endFirstname = i + 1;
        
        //get lastname from name
        i = name.length();
        do{
            c = name.charAt(i);
            contactpersoonAchternaam += c;
            i--;
        }while(c != ' ');
        
        //remember end position
        endLastname = i;
        
        //get insertion from name
        i = endFirstname;
        while(i < endLastname){
            contactpersoonTussenvoegsel += name.charAt(i);
        }
    }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String emailAdres) {
        this.email = emailAdres;
    }

    public int getTelefoonnr() {
        return telefoonnr;
    }
    public void setTelefoonnr(int telefoonnr) {
        this.telefoonnr = telefoonnr;
    }

}