package voedselbanksysteem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static voedselbanksysteem.DatabaseSource.*;

/**
 *
 * @author Cas_Ros
 */
public class JDBCDriver {
    public static void Toevoegen(ArrayList<String> velden) {
        int aantal;
        
        try{
            Connection connection;
            connection = getConnection();
            Statement sql = connection.createStatement();
            String query = "INSERT INTO temp (";
            for(int i = 0; i < velden.size(); i++){
                query += velden.get(i);
            }
            aantal = sql.executeUpdate(query);
        }
        catch(SQLException e){
            e.getStackTrace();
        }
        finally{
            closeConnection();
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
            String query = "UPDATE Uitgiftepunt SET maximumCapaciteit = " + nieuweWaarde + " WHERE naam = " + naam;
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
    public static ArrayList<String> getUitgiftepunten(){
        ArrayList<String> uitgiftepunten = new ArrayList();
        try{
            Connection connection;
            connection = getConnection();
            Statement sql = connection.createStatement();
            String query = "SELECT naam FROM uitgiftepunt";
            ResultSet executeQuery = sql.executeQuery(query);
            
            uitgiftepunten.add(executeQuery.getString(1));
            if(!executeQuery.isLast()){
                executeQuery.next();
                uitgiftepunten.add(executeQuery.getString(1));
            }
            return uitgiftepunten;
        }
        catch(SQLException e){
            e.getStackTrace();
        }
        finally{
            closeConnection();
            return uitgiftepunten;
        }
    }
    public static ArrayList<ArrayList<String>> getDistributieLijst (int weeknr){
        ArrayList<ArrayList<String>> DistributieLijst = new ArrayList ();
        try{
            Connection connection ();
            connection = getConnection();
            Statement sql = connection.createStatement ();
            String query = "SELECT naam, sum(), sum() - sum (),sum () FROM ";
        }
    }
}