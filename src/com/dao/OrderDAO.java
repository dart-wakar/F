package com.dao;
import com.connections.MyConnection;
import java.sql.PreparedStatement;
import com.pojos.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

 
import com.pojos.Security.SecurityType;

public class OrderDAO {
	
	public List<Order> fetchAllOrdersInLastMinute() {
		List<Order> orders = new ArrayList<>();
		String FETCH_ORDERS_QUERY = "select * from orders";
		int orderId;
		String isin, securityTypes, securityCompanyName;
		SecurityType securityType = SecurityType.Equity;
		Security security;
		Order order;
		char tradeType, traderType;
		Statement statement;
		try {
			statement = MyConnection.getMyConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(FETCH_ORDERS_QUERY);
			while (resultSet.next()) {
				orderId = resultSet.getInt("orderid");
				isin = resultSet.getString("isin");
				securityTypes = resultSet.getString("security_type");
				securityCompanyName = resultSet.getString("company_name");
				tradeType = resultSet.getString("trade_type").charAt(0);
				traderType = resultSet.getString("trader").charAt(0);
				if (securityTypes.equals("Equity")) {
					securityType = SecurityType.Equity;
				} else if (securityTypes.equals("Future")) {
					securityType = SecurityType.Future;
				} else if (securityTypes.equals("Call")) {
					securityType = SecurityType.Call;
				} else if (securityTypes.equals("Put")) {
					securityType = SecurityType.Put;
				}
				security = new Security(isin, securityCompanyName, securityType);
				order = new Order(orderId, security, tradeType, traderType);
				orders.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}

	public int updateOrder(Order order,int scenarioId){
		
		 
		int r=0;
		String UPDATE_ORDER = "update orders set scenario_Id = ? where orderid=?";
		
		try {
			PreparedStatement ps = MyConnection.getMyConnection().prepareStatement(UPDATE_ORDER);
			ps.setInt(1, scenarioId);
			ps.setInt(2, order.getOrderId());
			r = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
		
		
	}
	
	
	
	
	
	
}
