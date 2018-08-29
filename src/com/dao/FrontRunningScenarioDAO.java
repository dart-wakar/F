package com.dao;
import com.connections.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.pojos.Scenario;

 

public class FrontRunningScenarioDAO {

	public int addScenario(Scenario scenario, Connection connection){
		
		int r=0;
		String INSERT_SCENARIO = "insert into scenario values (?)";
		try {
			PreparedStatement ps=connection.prepareStatement(INSERT_SCENARIO);
			ps.setString(1, scenario.getScenarioType());
			r=ps.executeUpdate();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return r;
	}
	
	public int findnextId(Connection connection){
		
		String FIND_NEXT_ID = "SELECT MAX(scenario_id) AS F FROM scenario";
		int id=0;
		Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(FIND_NEXT_ID);
			while (resultSet.next()) {
				id = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
