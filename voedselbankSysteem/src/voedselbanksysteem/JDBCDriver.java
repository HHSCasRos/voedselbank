package voedselbanksysteem;

import java.sql.Connection;
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
}