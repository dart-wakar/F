package com.logic;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


import com.connections.*;
import com.dao.*;
import com.pojos.*;
import com.pojos.Security.SecurityType;


public class Detector {
	
	  
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			FindScenario findscenario=new FindScenario();
			
			Scanner scanner = new Scanner(System.in);
			OrderDAO orderDAO=new OrderDAO();
			FrontRunningScenarioDAO frontRunningScenarioDAO = new FrontRunningScenarioDAO();
			List<Order> orders = new ArrayList<>();
			Map<String, List<Order>> securityToOrdersMap = new HashMap<>();
			String securityId, securityCompanyName, securityTypes;
			SecurityType securityType = SecurityType.Equity;
			Security security;
			int orderId;
			char tradeType, traderType;
			Order order;
//			for (int i = 0; i < 5; i++) {
//				System.out.println("Enter security id");
//				securityId = scanner.next();
//				 System.out.println("Enter Security company Name");
//				 securityCompanyName = scanner.next();
//				 System.out.println("Enter Security type");
//				 securityTypes = scanner.next();
//				 if (securityTypes.equals("Equity")) {
//					securityType = SecurityType.Equity;
//				} else if (securityTypes.equals("Future")) {
//					securityType = SecurityType.Future;
//				} else if (securityTypes.equals("Call")) {
//					securityType = SecurityType.Call;
//				} else if (securityTypes.equals("Put")) {
//					securityType = SecurityType.Put;
//				}
//				System.out.println("Enter trader type");
//				traderType = scanner.next().charAt(0);
//				System.out.println("Enter trade type");
//				tradeType = scanner.next().charAt(0);
//				security = new Security(securityId, securityCompanyName, securityType);
//				order = new Order(security, tradeType, traderType);
//				orders.add(order);
//			}
						
			orders=orderDAO.fetchAllOrdersInLastMinute();
		
			securityToOrdersMap = findscenario.getSecurityToOrdersMap(orders);
			System.out.println(securityToOrdersMap);
	
			Map<String,String> strings=new HashMap<>();
			strings=findscenario.preprocess(securityToOrdersMap);
			
			List<List<Order>> flagged=new ArrayList<>();
			
			flagged=findscenario.detectScenario(securityToOrdersMap, strings);
			
			Scenario scenario = new Scenario("Scenario 2");
			int scenarioId = 0 ;
			
			
			if(flagged.size()==0){
				System.out.println("No scenarios found");
			}	
		 	else{
			for(List<Order> flaggedorder : flagged){
			
				frontRunningScenarioDAO.addScenario(scenario);
				scenarioId = frontRunningScenarioDAO.findnextId();
			for (Order order2 : flaggedorder) {
				
				System.out.println(order2.getSecurity().getIsin()+order2.getTraderType()+order2.getTradeType());
				int changed = orderDAO.updateOrder(order2, scenarioId);
				System.out.println("Changed: " + changed);
			}
		}
			
	}
}
		
		

	 

}
