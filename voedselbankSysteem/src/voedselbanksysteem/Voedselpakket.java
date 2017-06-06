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
public class Voedselpakket {
    private int id;
    private String soort;
    private int uitgiftepunt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSoort() {
        return soort;
    }

    public void setSoort(String soort) {
        this.soort = soort;
    }

    public int getUitgiftepunt() {
        return uitgiftepunt;
    }

    public void setUitgiftepunt(int uitgiftepunt) {
        this.uitgiftepunt = uitgiftepunt;
    }
    
}
