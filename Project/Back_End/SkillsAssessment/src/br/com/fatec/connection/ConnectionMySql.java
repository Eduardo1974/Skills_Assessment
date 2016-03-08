package br.com.fatec.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Statement;

public class ConnectionMySql {

	private static String user="root";
    private static String password="eduardo";
    private static String database="SkillsAssessment";
    private static String ip="192.168.98.128:3306/SkillsAssessment?useSSL=false";
    private static String driver="com.mysql.jdbc.Driver";
    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultset = null;
    
    public ConnectionMySql(){};
    
    //padrao singleton
    public static Connection getConnection() {
        System.out.println(">>Connecting to database");
        try {
            Class.forName(driver);
            if(connection==null || connection.isClosed()){
            	connection=DriverManager.getConnection("jdbc:mysql://"+ip+"",user, password);
                //connection=DriverManager.getConnection("jdbc:mysql://"+ip+"/"+database+"",user, password);
            }
            return connection; 
        }catch (ClassNotFoundException e) {    
            throw new RuntimeException(e);    
        }catch (SQLException e) {

            close();
            throw new RuntimeException(e);
        }

    }

    public boolean conect() throws SQLException{
    	boolean error = true;
    	try{
    		Class.forName(driver);
    	}catch(ClassNotFoundException ex){
    		Logger.getLogger(ConnectionMySql.class.getName()).log(Level.SEVERE,null, ex);
            error = false;
    	}
    	
    	// realizando conexão
        connection = (Connection) DriverManager.getConnection("jdbc:mysql://"+ip+"",user, password);
        // criando canal para execução de sql
        statement = (Statement) connection.createStatement();
        // retorna resultado da conexão
        return error;    	
    }
    
    public static void close(){
        try{
            if(connection!=null && !connection.isClosed()){
                connection.close();
                System.out.println(">>Connection successfully closed");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //select
    public boolean executeQuery(String query) throws SQLException{
    	resultset = statement.executeQuery(query); //executa sql
        if (resultset.next()) { //aponta para primeiro registro da consulta
            return true; // não há registros
        } else {
            return false;
        }
    }
    
    public ResultSet returnRegister() throws SQLException {
        return resultset;
    }
    
    public String returnField(String campo) throws SQLException {
        String data;
        data = resultset.getString(campo);
        return data;
    }
    
    public boolean nextRegister() throws SQLException {
        return resultset.next();
    }
    
    //INSERT, UPDATE E DELETE
    public boolean executeSql(String SQL) throws SQLException {
        int i = statement.executeUpdate(SQL);
        if (i == 0) {
            return false;
        } else {
            return true;
        }
    }

	public static java.sql.Connection restartConnection() {
		close();
		return ConnectionMySql.getConnection();
	}

}
