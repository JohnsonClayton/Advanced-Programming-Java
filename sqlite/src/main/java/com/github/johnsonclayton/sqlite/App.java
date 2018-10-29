package com.github.johnsonclayton.sqlite;
 
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
/**
 *
 * @author cpjohnson
 */
public class App {
    public static String DB = "clay.db";
    public static String URL = "jdbc:sqlite:" + DB;
    
    private Connection connect() {
        // SQLite connection string
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
 
    /**
     * Insert a new row into the household table
     *
     * @param name
     * @param count
     */
    public void insert(String name, double count) {
        String sql = "INSERT INTO household(name,count) VALUES(?,?)";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, count);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void delete(String name) {
        String sql = "DELETE FROM household WHERE (name LIKE ?)";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void showTable(String tableName) {
        String sql = "SELECT * FROM household";
        
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            //pstmt.setString(1, tableName);
            ResultSet result = pstmt.executeQuery();
            System.out.println("Table " + tableName + ":\n");
            System.out.println("Name\tCount");
            while(result.next()) {
                //Print the items in order
                System.out.println(result.getString("name") + "\t" + result.getInt("count"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
 
    
    public void createNewTable() {     
        
        String sql = "DROP TABLE IF EXISTS household";
        
        try (Connection conn = DriverManager.getConnection(URL);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        // SQL statement for creating a new table
        sql = "CREATE TABLE IF NOT EXISTS household (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	count integer\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(URL);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Connect to a sample database
     *
     * @param fileName the database file name
     */
    public void createNewDatabase() { 
        try (Connection conn = connect()) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new App().run();
    }
    
    void run() {
        createNewDatabase();
        createNewTable();
        insert("Fork", 3);
        insert("Plate", 1);
        insert("Chopstick" , 10);
        showTable("household");
        delete("Fork");
        showTable("household");
    }

    ResultSet getTableInSet() {
        String sql = "SELECT * FROM household";
        ResultSet result = null;
        try(Connection conn = this.connect();
                Statement stmt = conn.createStatement()) {
            result = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    int getCountInTable(String itemName) {
        String sql = "SELECT count FROM household WHERE (name LIKE ?)";
        ResultSet result = null;
        int count = 0;
        try (Connection conn = this.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, itemName);
            stmt.execute();
            result = stmt.getResultSet();
            count = result.getInt("count");
            result.close();        
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }
}