package voedselbanksysteem;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static voedselbanksysteem.DatabaseSource.*;

/**
 *
 * @author Cas_Ros
 */
public class JDBCDriver {
    public static boolean Toevoegen(ArrayList<String> velden) {
        int aantal;
        
        try{
            Connection connection;
            connection = getConnection();
            
            Statement sql = connection.createStatement();
            String query = "INSERT IGNORE INTO temp (kaartnummer, naam, naamPartner, telefoonnummer, email, mobiel, aantalPersonen, aantalOnderNorm, gebruiktInMaanden, identiteitsbewijsSoort, datumUitgifteIndentiteitsbewijs, identiteitsnummer, plaatsUitgifteIdentiteitsbewijs, adres, postcode, plaats, status, intaker, intakedatum, startdatumUitgifte, datumHerintake, datumStopzetting, redenStopzetting, verwijzersDoor, verwijzersDoorContactpersoon, verwijzersDoorTelefoonnummer, verwijzersDoorEmail, verwijzersNaar, verwijzersNaarContactpersoon, verwijzersNaarTelefoonnummer, verwijzersNaarEmail, Uitgiftepunt, pakket) VALUES (";
            for(int i = 0; i < velden.size(); i++){
                query += velden.get(i);
            }
            aantal = sql.executeUpdate(query);
        }
        catch(SQLException e){
            e.getStackTrace();
            return false;
        }
        finally{
            closeConnection();
            return true;
        }
    }
    
    public static int updateUitgiftepunt(String naam, int nieuweWaarde){
        int aantal = 0;
        
        //get rid of negative values
        if(nieuweWaarde < 0){
            return 0;
        }
        try{
            Connection connection;
            connection = getConnection();
            Statement sql = connection.createStatement();
            String query = "UPDATE Uitgiftepunt SET maximumCapaciteit = " + nieuweWaarde + " WHERE Naam = " + naam;
            aantal = sql.executeUpdate(query);
        }
        catch(SQLException e){
            e.getStackTrace();
        }
        finally{
            closeConnection();
            return aantal;
        }
    }
    public static void getUitgiftepunten(JComboBox jc){
        
        try{
            Connection connection;
            connection = getConnection();
            Statement sql = connection.createStatement();
            String query = "SELECT Naam FROM Uitgiftepunt";
            ResultSet rs = sql.executeQuery(query);
            
            while(rs.next()){
                jc.addItem(rs.getString(1));
            }
            
            
            
            
            
           
        }
        catch(SQLException e){
            e.getStackTrace();
        }
        finally{
            closeConnection();
            
        }
    }
    
    public static ArrayList<ArrayList<String>> getDistributieLijst (int datum){
        ArrayList<ArrayList<String>> DistributieLijst = new ArrayList ();
        int datumVorigeWeek = datum - 7;
        try{
            Connection connection;
            connection = getConnection();
            Statement sql = connection.createStatement ();
            String query = "SELECT naam, COUNT("
                    + "SELECT Id FROM Cliënt WHERE status = 'actief'"
                    + " AND startUitgifte < " + datumVorigeWeek
                    + " AND datumStopzetting > " + datumVorigeWeek
                + "), COUNT("
                    + " SELECT Id FROM Cliënt WHERE status = 'actief'"
                    + " AND startUitgifte < " + datum
                    + " AND datumStopzetting > " + datum
                + ") - sum(uitgiftepunt), sum(uitgiftepunt)"
                + " FROM Uitgiftepunt JOIN Cliënt ON toegewezenUitgiftepunt = naam"
                + " JOIN Intake ON cliënt = kaartnr"
                + " WHERE status = 'actief'"
                + " AND startUitgifte < " + datum
                + " AND datumStopzetting > " + datum
                + " GROUP BY naam";
            ResultSet executeQuery = sql.executeQuery(query);
        }
        catch(SQLException e){
            e.getStackTrace();
        }
        finally{
            closeConnection();
            return DistributieLijst;
        }
    }
    
    public static int setCliëntFromTemp(){
        int aantal = 0;
        ArrayList<String> cliënt = new ArrayList();
        
        try{
            Connection connection;
            connection = getConnection();
            Statement sql = connection.createStatement();
            Statement insertsql = connection.createStatement();//statement 
            String query = "SELECT kaartnummer, naam, telefoonnummer, mobiel, email, naamPartner, adres, postcode, plaats FROM temp";
            ResultSet executedQuery = sql.executeQuery(query);
            
            while(executedQuery.next()){
                //change naam to voornaam, tussenvoegsel and achternaam
                Naam.setNaam(executedQuery.getString("naam"));
                String[] naam = new String[3];
                naam[0] = Naam.getNaam()[0];
                naam[1] = Naam.getNaam()[1];
                naam[2] = Naam.getNaam()[2];
                
                Naam.setNaam(executedQuery.getString("naamPartner"));
                String[] partner = new String[3];
                partner[0] = Naam.getNaam()[0];
                partner[1] = Naam.getNaam()[1];
                partner[2] = Naam.getNaam()[2];
                
                cliënt.add(executedQuery.getString(1) + ", " + 
                    "'" + naam[0] + "', " + 
                    "'" + naam[1] + "', " + 
                    "'" + naam[2] + "', " + 
                    "'" + executedQuery.getString(3) + "', " + 
                    "'" + executedQuery.getString(4) + "', " + 
                    "'" + executedQuery.getString(5) + "', " + 
                    "'" + partner[0] + "', " + 
                    "'" + partner[1] + "', " + 
                    "'" + partner[2] + "', " + 
                    "'" + executedQuery.getString(7) + "', " + 
                    "'" + executedQuery.getString(8) + "', " + 
                    "'" + executedQuery.getString(9) + "')");
                
                String insert = "INSERT IGNORE INTO Cliënt (kaartnr, voornaam, tussenvoegsel, achternaam, telefoonnr, mobielnr, email, partnetVoornaam, partnerTussenvoegsel, partnerAchternaam, adres, postcode, plaats) VALUES (";
                insert += cliënt.get(cliënt.size() - 1);
                aantal += insertsql.executeUpdate(insert);
            }
        }
        catch(SQLException e){
            e.getStackTrace();
            System.out.println("Cliënt");
        }
        finally{
            closeConnection();
            System.out.println(cliënt.get(0));
            return aantal;
        }
    }
    public static int setIntakerFromTemp(){
        int aantal = 0;
        ArrayList<String> intaker = new ArrayList();
        
        try{
            Connection connection;
            connection = getConnection();
            Statement sql = connection.createStatement();
            Statement insertsql = connection.createStatement();//statement 
            String query = "SELECT intaker FROM temp";
            ResultSet executedQuery = sql.executeQuery(query);
            
            while(executedQuery.next()){
                //change naam to voornaam, tussenvoegsel and achternaam
                Naam.setNaam(executedQuery.getString("intaker"));
                String[] naam = new String[3];
                naam[0] = Naam.getNaam()[0];
                naam[1] = Naam.getNaam()[1];
                naam[2] = Naam.getNaam()[2];
                
                intaker.add("'" + naam[0] + "', " + 
                    "'" + naam[1] + "', " + 
                    "'" + naam[2] + "')");
                
                String insert = "INSERT IGNORE INTO Intaker (voornaam, tussenvoegsel, achternaam) VALUES (";
                insert += intaker.get(intaker.size() - 1);
                aantal += insertsql.executeUpdate(insert);
            }
        }
        catch(SQLException e){
            e.getStackTrace();
            System.out.println("Intaker");
        }
        finally{
            closeConnection();
            System.out.println(intaker.get(0));
            return aantal;
        }
    }
    public static int setHulpverlenendeInsantieFromTemp(){
        int aantal = 0;
        ArrayList<String> hulpverlenendeInsantie = new ArrayList();
        
        try{
            Connection connection;
            connection = getConnection();
            Statement sql = connection.createStatement();
            Statement insertsql = connection.createStatement();//statement 
            String query = "SELECT verwijzersDoor, verwijzersDoorContactpersoon, verwijzersDoorEmail, verwijzersDoorTelefoonnummer FROM temp";
            ResultSet executedQuery = sql.executeQuery(query);
            
            while(executedQuery.next()){
                //change naam to voornaam, tussenvoegsel and achternaam
                Naam.setNaam(executedQuery.getString("verwijzersDoorContactpersoon"));
                String[] naam = new String[3];
                naam[0] = Naam.getNaam()[0];
                naam[1] = Naam.getNaam()[1];
                naam[2] = Naam.getNaam()[2];
                
                hulpverlenendeInsantie.add("'" + executedQuery.getString(1) + "', " + 
                    "'" + naam[0] + "', " + 
                    "'" + naam[1] + "', " + 
                    "'" + naam[2] + "', " + 
                    "'" + executedQuery.getString(3) + "', " +  
                    "'" + executedQuery.getString(4) + "')");
                
                String insert = "INSERT IGNORE INTO HulpverlenendeInsantie (naam, contactpersoonVoornaam, contactpersoonTussenvoegsel, contactpersoonAchternaam, email, telefoonnr) VALUES (";
                insert += hulpverlenendeInsantie.get(hulpverlenendeInsantie.size() - 1);
                aantal += insertsql.executeUpdate(insert);
            }
        }
        catch(SQLException e){
            e.getStackTrace();
            System.out.println("HulpverlenendeInsantie");
        }
        finally{
            closeConnection();
            System.out.println(hulpverlenendeInsantie.get(0));
            return aantal;
        }
    }
    public static int setIntakeFromTemp(){
        int aantal = 0;
        ArrayList<String> intake = new ArrayList();
        
        try{
            Connection connection;
            connection = getConnection();
            Statement sql = connection.createStatement();
            Statement intakersql = connection.createStatement();
            Statement insertsql = connection.createStatement();//statement 
            String query = "SELECT intaker, verwijzersDoor, kaartnummer, Intakedatum, startdatumUitgifte, datumStopzetting, redenStopzetting, status, aantalPersonen, aantalOnderNorm, gebruiktInMaanden, pakket FROM temp";
            ResultSet executedQuery = sql.executeQuery(query);
            
            while(executedQuery.next()){
                //change temp.intaker to Intaker.idIntaker
                Naam.setNaam(executedQuery.getString(1));
                String[] naam = new String[3];
                naam[0] = Naam.getNaam()[0];
                naam[1] = Naam.getNaam()[1];
                naam[2] = Naam.getNaam()[2];
                ResultSet intaker = intakersql.executeQuery("SELECT idIntaker FROM Intaker WHERE voornaam = '" + naam[0] + "' AND tussenvoegsel = '" + naam[1] + "' AND achternaam = '" + naam[2] + "'");
                intaker.next();
                
                intake.add(intaker.getString(1) + ", " +
                    "'" + executedQuery.getString(2) + "', " + 
                    executedQuery.getString(3) + ", " + 
                    "'" + executedQuery.getString(4) + "', " + 
                    "'" + executedQuery.getString(5) + "', " + 
                    "'" + executedQuery.getString(6) + "', " + 
                    "'" + executedQuery.getString(7) + "', " + 
                    "'" + executedQuery.getString(8) + "', " + 
                    "'" + executedQuery.getString(9) + "', " + 
                    executedQuery.getString(10) + ", " +
                    executedQuery.getString(11) + ", " +
                    "'" + executedQuery.getString(12) + "')");
                
                String insert = "INSERT IGNORE INTO Intake (intaker, hulpverlenendeInstantie, cliënt, intakedatum, startUitgifte, datumStopzetting, redenStopzetting, StatusIntake, aantalPersonen, aantalPersonenNorm, gebruikInMaanden, voedselpakketSoort) VALUES (";
                insert += intake.get(intake.size() - 1);
                aantal += insertsql.executeUpdate(insert);
            }
        }
        catch(SQLException e){
            e.getStackTrace();
            System.out.println("Intake");
        }
        finally{
            closeConnection();
            System.out.println(intake.get(0));
            return aantal;
        }
    }
    public static int setUitgifteFromTemp(){
        int aantal = 0;
        ArrayList<String> uitgifte = new ArrayList();
        
        try{
            Connection connection;
            connection = getConnection();
            Statement sql = connection.createStatement();
            Statement insertsql = connection.createStatement();//statement 
            String query = "SELECT Uitgiftepunt, verwijzersNaarContactpersoon, verwijzersNaarTelefoonnummer, verwijzersNaarEmail FROM temp";
            ResultSet executedQuery = sql.executeQuery(query);
            
            while(executedQuery.next()){
                
                uitgifte.add("'" + executedQuery.getString(1) + "', " + 
                    "'" + executedQuery.getString(2) + "', " + 
                    "'" + executedQuery.getString(3) + "', " + 
                    "'" + executedQuery.getString(4) + "')");
                
                String insert = "INSERT IGNORE INTO Uitgiftepunt (Naam, contactpersoon, telefoonnr, email) VALUES (";
                insert += uitgifte.get(uitgifte.size() - 1);
                aantal += insertsql.executeUpdate(insert);
            }
        }
        catch(SQLException e){
            e.getStackTrace();
            System.out.println("Uitgifte");
        }
        finally{
            closeConnection();
            System.out.println(uitgifte.get(0));
            return aantal;
        }
    }
    public static int setVoedselpakketFromTemp(){
        int aantal = 0;
        ArrayList<String> voedselpakket = new ArrayList();
        
        try{
            Connection connection;
            connection = getConnection();
            Statement sql = connection.createStatement();
            Statement insertsql = connection.createStatement();//statement 
            String query = "SELECT pakket FROM temp";
            ResultSet executedQuery = sql.executeQuery(query);
            
            while(executedQuery.next()){
                //change naam to voornaam, tussenvoegsel and achternaam
               
                
                voedselpakket.add(
                    "'" + executedQuery.getString(1) + "')");
                
                String insert = "INSERT IGNORE INTO Voedselpakket (soort) VALUES (";
                insert += voedselpakket.get(voedselpakket.size() - 1);
                aantal += insertsql.executeUpdate(insert);
            }
        }
        catch(SQLException e){
            e.getStackTrace();
            System.out.println("Voedselpakket");
        }
        finally{
            closeConnection();
            System.out.println(voedselpakket.get(0));
            return aantal;
        }
    }
    public static int setIdentiteitFromTemp(){
        int aantal = 0;
        ArrayList<String> identiteit = new ArrayList();
        
        try{
            Connection connection;
            connection = getConnection();
            Statement sql = connection.createStatement();
            Statement insertsql = connection.createStatement();//statement 
            String query = "SELECT identiteitsnummer, plaatsUitgifteIdentiteitsbewijs, datumUitgifteIndentiteitsbewijs, identiteitsbewijsSoort FROM temp";
            ResultSet executedQuery = sql.executeQuery(query);
            
            while(executedQuery.next()){
        
                
                identiteit.add(executedQuery.getString(1) + ", " + 
                    "'" + executedQuery.getString(2) + "', " + 
                    "'" + executedQuery.getString(3) + "', " + 
                    "'" + executedQuery.getString(4) + "')");
                
                String insert = "INSERT IGNORE INTO Identiteit (BSN, plaatsUitgifte, uitgiftedatum, idSoort) VALUES (";
                insert += identiteit.get(identiteit.size() - 1);
                aantal += insertsql.executeUpdate(insert);
            }
        }
        catch(SQLException e){
            e.getStackTrace();
            System.out.println("Identiteit");
        }
        finally{
            closeConnection();
            System.out.println(identiteit.get(0));
            return aantal;
        }
    }
    public static void fillTable (ResultSet rs, JTable myTable) throws SQLException {
        
        ResultSetMetaData rsmetadata = rs.getMetaData();
            
            int columns = rsmetadata.getColumnCount() + 1;
            
            DefaultTableModel dtm = new DefaultTableModel();
            Vector columns_name = new Vector();
            Vector data_rows = new Vector();
            
           
            for(int i= 1; i<columns; i++){
            columns_name.addElement(rsmetadata.getColumnClassName(i));
            }
             dtm.setColumnIdentifiers(columns_name);
             
             while(rs.next()){
                 
                 data_rows = new Vector();
                 for(int j = 1; j< columns; j++){
                     data_rows.addElement(rs.getString(j));
                 }
                 dtm.addRow(data_rows);
                     
             }
             myTable.setModel(dtm);
    }
}