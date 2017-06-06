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
public class Identiteit {
    private int BSN;
    private String plaatsUitgifte;
    private String uitgiftedatum;
    private String idSoort;
    private int eigenaar;

    public int getBSN() {
        return BSN;
    }

    public void setBSN(int BSN) {
        this.BSN = BSN;
    }

    public String getPlaatsUitgifte() {
        return plaatsUitgifte;
    }

    public void setPlaatsUitgifte(String plaatsUitgifte) {
        this.plaatsUitgifte = plaatsUitgifte;
    }

    public String getUitgiftedatum() {
        return uitgiftedatum;
    }

    public void setUitgiftedatum(String uitgiftedatum) {
        this.uitgiftedatum = uitgiftedatum;
    }

    public String getIdSoort() {
        return idSoort;
    }

    public void setIdSoort(String idSoort) {
        this.idSoort = idSoort;
    }

    public int getEigenaar() {
        return eigenaar;
    }

    public void setEigenaar(int eigenaar) {
        this.eigenaar = eigenaar;
    }
    
}
