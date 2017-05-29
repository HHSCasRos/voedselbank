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
public class Cliënt {
    private int kaartnr;
    private String voornaam;
    private String tussenvoegsel;
    private String achternaam;
    private char geslacht;
    private String geboortedatum;
    private String telefoonnr;
    private String mobielnr;
    private String email;
    private String partnerVoornaam;
    private String partnerTussenvoegsel;
    private String partnerAchternaam;
    private String adres;
    private String postcode;
    private String plaats;
    private int toegewezenUitgiftepunt;

    public int getKaartnr() {
        return kaartnr;
    }
    public void setKaartnr(int kaartnr) {
        this.kaartnr = kaartnr;
    }

    public String[] getNaam() {
        String[] naam = new String[3];
        naam[0] = voornaam;
        naam[1] = tussenvoegsel;
        naam[2] = achternaam;
        return naam;
    }
    public void setNaam(String naam) {
        char c;
        int i;
        int endFirstname;
        int endLastname;
        
        //get firstname from naam
        i = 0;
        do{
            c = naam.charAt(i);
            voornaam += c;
            i++;
        }while(c != ' ');
        
        //remember end position and move of the space
        endFirstname = i + 1;
        
        //get lastname from name
        i = naam.length();
        do{
            c = naam.charAt(i);
            achternaam += c;
            i--;
        }while(c != ' ');
        
        //remember end position
        endLastname = i;
        
        //get insertion from name
        i = endFirstname;
        while(i < endLastname){
            tussenvoegsel += naam.charAt(i);
        }
    }

    public char getGeslacht() {
        return geslacht;
    }
    public boolean setGeslacht(char geslacht) {
        if(geslacht == 'm' || geslacht == 'v'){
            this.geslacht = geslacht;
            return true;
        } else {
            return false;
        }
    }

    public String getGeboortedatum() {
        return geboortedatum;
    }
    public void setGeboortedatum(String geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public String getTelefoonnr() {
        return telefoonnr;
    }
    public boolean setTelefoonnr(String telefoonnr) {
        if(telefoonnr.length() <= 12){
            this.telefoonnr = telefoonnr;
            return true;
        } else {
            return false;
        }
    }

    public String getMobielnr() {
        return mobielnr;
    }
    public boolean setMobielnr(String mobielnr) {
        if(telefoonnr.length() <= 12){
            this.mobielnr = mobielnr;
            return true;
        } else {
            return false;
        }
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getPartnerNaam() {
        String[] partnerNaam = new String[3];
        partnerNaam[0] = partnerVoornaam;
        partnerNaam[1] = partnerTussenvoegsel;
        partnerNaam[2] = partnerAchternaam;
        return partnerNaam;
    }
    public void setPartnerNaam(String partnerNaam) {
        char c;
        int i;
        int endFirstname;
        int endLastname;
        
        //get firstname from naam
        i = 0;
        do{
            c = partnerNaam.charAt(i);
            partnerVoornaam += c;
            i++;
        }while(c != ' ');
        
        //remember end position and move of the space
        endFirstname = i + 1;
        
        //get lastname from name
        i = partnerNaam.length();
        do{
            c = partnerNaam.charAt(i);
            partnerAchternaam += c;
            i--;
        }while(c != ' ');
        
        //remember end position
        endLastname = i;
        
        //get insertion from name
        i = endFirstname;
        while(i < endLastname){
            partnerTussenvoegsel += partnerNaam.charAt(i);
        }
    }

    public String getAdres() {
        return adres;
    }
    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPlaats() {
        return plaats;
    }
    public void setPlaats(String plaats) {
        this.plaats = plaats;
    }

    public int getToegewezenUitgiftepunt() {
        return toegewezenUitgiftepunt;
    }
    public void setToegewezenUitgiftepunt(int toegewezenUitgiftepunt) {
        this.toegewezenUitgiftepunt = toegewezenUitgiftepunt;
    }
}