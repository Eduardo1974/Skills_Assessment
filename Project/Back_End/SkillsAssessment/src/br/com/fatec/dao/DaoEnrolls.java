package br.com.fatec.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.entity.Course;
import br.com.fatec.entity.Enrolls;

public class DaoEnrolls {
	
	//DECIDIR SOBRE CRS_CODE (FK DE COURSE) 
	@SuppressWarnings("finally")
	public static boolean insertPeriod(Enrolls period){
		ConnectionMySql connection = new ConnectionMySql();
		String sql = "INSER INTO PERIOD (prd_year, prd_period, crs_code) VALUES ("+ period.getYear() +","+
																				    period.getPeriod() +","+
																				    period.getCodeCourse() +");";
		boolean insert = false;
		try{
			connection.conect();
			if(connection.executeSql(sql)){
				insert = true;
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			connection.close();
			return insert;
		}
	}
	
	@SuppressWarnings("finally")
	public static boolean deletePeriod(Long code) {
		ConnectionMySql connection = new ConnectionMySql();
		String sql = "DELETE FROM PERIOD WHERE PRD_CODE = " + code + ";";
		boolean delete = false;
		try {
			connection.conect();
			if (connection.executeSql(sql)) {
				delete = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			connection.close();
			return delete;
		}
	}
	
	//DECIDIR SOBRE CRS_CODE (FK DE COURSE) --- VERIFICAR AQUI
	@SuppressWarnings("finally")
	public static boolean updatePeriod(Enrolls period){
		ConnectionMySql connection =  new ConnectionMySql();
		String sql = "UPDATE COURSES SET crs_code = "+ period.getCodePeriod() +", "
								      + "prd_year = "+ period.getYear()+", "
						            + "prd_period = "+ period.getPeriod()+", "
								      + "crs_code = "+ period.getCodeCourse()+", "
						 		+ "where prd_code = "+ period.getCodePeriod() +";";
		boolean update = false;
		try {
			connection.conect();
			if(connection.executeSql(sql)){
				update = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			connection.close();
			return update;
		}
	}	

	//DECIDIR SOBRE CRS_CODE (FK DE COURSE) 
	@SuppressWarnings("finally")
	public static List<Enrolls> searchAllPeriod() {
		List<Enrolls> listPeriod = new ArrayList<>();
		ConnectionMySql connection = new ConnectionMySql();
		String query = "select * from period;";
		try {
			connection.conect();
			if (connection.executeQuery(query)) {
				do {
					Enrolls period = new Enrolls();
					period.setCodePeriod(Long.parseLong(connection.returnField("PRD_CODE")));
					period.setYear(Integer.parseInt(connection.returnField("PRD_YEAR")));
					period.setPeriod(Integer.parseInt(connection.returnField("PRD_PERIOD")));
					listPeriod.add(period);
				} while (connection.nextRegister());
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			connection.close();
			return listPeriod;
		}
	}
	
	//DECIDIR SOBRE CRS_CODE (FK DE COURSE) 
	@SuppressWarnings("finally")
	public static Enrolls searchPeriodById(Long code){
		ConnectionMySql connection = new ConnectionMySql();
		String query = "select * from course where crs_code = "+ code +";";
		Enrolls period = new Enrolls();
		try {
			connection.conect();
			if(connection.executeQuery(query)){
				do{					
					period.setCodePeriod(Long.parseLong(connection.returnField("PRD_CODE")));
					period.setYear(Integer.parseInt(connection.returnField("PRD_YEAR")));
					period.setPeriod(Integer.parseInt(connection.returnField("PRD_PERIOD")));
				}while(connection.nextRegister());
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			connection.close();
			return period;
		}
	}
}