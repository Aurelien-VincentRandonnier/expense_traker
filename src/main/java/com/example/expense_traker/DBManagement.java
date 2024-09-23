package com.example.expense_traker;
import java.sql.*;
import java.util.Scanner;

import java.sql.*;
import java.util.Scanner;

public class DBManagement {
    private final Connection dbConnnection;

    public DBManagement() throws SQLException {
        this.dbConnnection = DriverManager.getConnection("jdbc:sqlite:expensetracker.db");
    }

    private static final String dbName = "maindb";

    public void initDatabase(){
        String DATA_BASE_URL = "jdbc:sqlite:expensetracker.db";
        try(Connection connection = DriverManager.getConnection(DATA_BASE_URL);
            Statement statement = connection.createStatement()
        ){
            String createTableQuery = "CREATE TABLE IF NOT EXISTS "+ dbName +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "amount FLOAT NOT NULL," +
                    "date DATE NOT NULL," +
                    "description TEXT)";
            statement.executeUpdate(createTableQuery);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void addData(){
        String newData = "INSERT INTO " + dbName + "(name, amount, date, description) VALUES (?, ?, ?, ?)";
        Scanner input = new Scanner(System.in);
        try(PreparedStatement ps = dbConnnection.prepareStatement(newData)){
            System.out.print("String : ");
            String product = input.next();
            ps.setString(1, product);

            System.out.print("float : ");
            float amount = input.nextFloat();
            ps.setFloat(2, amount);

            System.out.print("Date (yyyy-mm-dd) : ");
            String date = input.next();
            ps.setDate(3, java.sql.Date.valueOf(date));

            ps.setString(4, "A description of Product A");
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }


    public void printData(){
        String selectAllTable = "SELECT * FROM " + dbName;
        try(PreparedStatement psSelectTable = dbConnnection.prepareStatement(selectAllTable)){
            ResultSet rs = psSelectTable.executeQuery();

            //Access the metaData of the result set (getMetaData is a object type)
            int rowCount = rs.getMetaData().getColumnCount();

            while(rs.next()){
                for(int i = 1; i <= rowCount; i++) {
                    int columnType = rs.getMetaData().getColumnType(i);
                    checkType(columnType, rs, i);
                    System.out.print(" | ");
                }
                System.out.println();
            }
        } catch (SQLException e){
            System.out.println((e.getMessage()));
        }
    }

    private static void checkType(int columnType, ResultSet rs, int i) throws SQLException {
        switch (columnType) {
            case Types.INTEGER:
                System.out.print(rs.getInt(i));
                break;
            case Types.FLOAT:
            case Types.DOUBLE:
            case Types.REAL:
                System.out.print(rs.getFloat(i));
                break;
            case Types.DATE:
                System.out.print(rs.getDate(i));
                break;
            case Types.VARCHAR:
            case Types.CHAR:
                System.out.print(rs.getString(i));
                break;
            default:
                System.out.print(rs.getObject(i)); // Fallback for other types
                break;
        }
    }


    public void eraseAll(){
        String eraseAllRecord = "DELETE FROM " + dbName;
        try(PreparedStatement psEraseAllRecord = dbConnnection.prepareStatement(eraseAllRecord)){
            psEraseAllRecord.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    // MAKE UPDATE ALL THAT CONTAIN ALL UPDATE AND IS CALLED EVERYTIME AN ACTION IS DONE ON THE APP
}

