package voedselbanksysteem;
public class Intaker {
    private int id;
    private String voornaam;
    private String tussenvoegsel;
    private String achternaam;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public String[] getNaam(){
        String[] naam = new String[3];
        naam[0] = voornaam;
        naam[1] = tussenvoegsel;
        naam[2] = achternaam;
        return naam;
    }
    public void setNaam(String name){
        char c;
        int i;
        int endFirstname;
        int endLastname;
        
        //get firstname from naam
        i = 0;
        do{
            c = name.charAt(i);
            voornaam += c;
            i++;
        }while(c != ' ');
        
        //remember end position and move of the space
        endFirstname = i + 1;
        
        //get lastname from name
        i = name.length();
        do{
            c = name.charAt(i);
            achternaam += c;
            i--;
        }while(c != ' ');
        
        //remember end position
        endLastname = i;
        
        //get insertion from name
        i = endFirstname;
        while(i < endLastname){
            tussenvoegsel += name.charAt(i);
        }
    }
}