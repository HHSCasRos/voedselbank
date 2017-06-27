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
public class Naam {
    private static String voornaam;
    private static String tussenvoegsel;
    private static String achternaam;

    public static String[] getNaam() {
        String[] naam = new String[3];
        naam[0] = voornaam;
        naam[1] = tussenvoegsel;
        naam[2] = achternaam;
        return naam;
    }
    public static void setNaam(String naam) {
        char c;
        int i;
        int endFirstname;
        int endLastname;
        
        //get firstname from naam
        voornaam = "";
        
        i = 0;
        do{
            c = naam.charAt(i);
            voornaam += c;
            i++;
        }while(naam.charAt(i) != ' ');
        
        //remember end position and move of the space
        endFirstname = i;
        
        //get lastname from name
        achternaam = "";
        
        i = naam.length() - 1;
        do{
            c = naam.charAt(i);
            achternaam += c;
            i--;
        }while(naam.charAt(i) != ' ');
        achternaam = new StringBuilder(achternaam).reverse().toString();
        
        //remember end position
        endLastname = i;
        
        //get insertion from name
        tussenvoegsel = " ";
        
        i = endFirstname + 1;
        while(i < endLastname){
            tussenvoegsel += naam.charAt(i);
            i++;
        }
        tussenvoegsel += " ";
    }
}