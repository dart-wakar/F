package com.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.connections.*;
import com.dao.*;
import com.pojos.*;
import com.pojos.Security.SecurityType;


public class FindScenario {
		
	List<Integer> l=new ArrayList<Integer>();
	
	public  Map<String, List<Order>> getSecurityToOrdersMap(List<Order> orders) {
		Map<String, List<Order>> securityToOrdersMap = new HashMap<>();
		
		List<Order> orderList;
		
		for (Order order : orders) {
			String securityCompanyName = order.getSecurity().getCompanyName();
			if (securityToOrdersMap.containsKey(securityCompanyName)) {
				orderList = securityToOrdersMap.get(securityCompanyName);
			} else {
				orderList = new ArrayList<>();
			}
			orderList.add(order);
			securityToOrdersMap.put(securityCompanyName, orderList);
		}
		
		
		
		
		return securityToOrdersMap;
	}
	
	
	public String get2(char a,char b,char c){//c for security type
		String s;
		
		if(a=='f'){
			if(b=='b'){
				s="1";
			}
			else
				s= "3";
		}
		else{
			if(b=='b'){
				s= "2";
			}
			else
				s="4";
			 
		}
		
		 
		return s;
		
	}
	public String get(char traderType,char tradeType,char securityType){
		String s;
		
		if(traderType=='f'){
			if(tradeType=='b'){
				if (securityType == 'p') {
					s = "d";
				} else {
					s = "c";
				}
			} else {
				if (securityType == 'p') {
					s = "c";
				}else {
					s = "d";
				}
			}
		} else {
			if(tradeType=='b'){
				if (securityType == 'p') {
					s = "b";
				} else {
					s = "a";
				}
			} else {
				if (securityType == 'p') {
					s = "a";
				}else {
					s = "b";
				}
			}
		}
		
		 
		return s;
		
	}
	
	public Map<String,String> preprocess(Map<String, List<Order>> m){
		  
		Map<String,String> securityTobaseStringMap =new HashMap<>();
		for (Map.Entry<String, List<Order>> entry : m.entrySet())
		{
			List<Order> orders=new ArrayList<Order>();
			orders = entry.getValue();
			String baseString="";
			for (Order order : orders) {
					char type=order.getTraderType();
					char otype=order.getTradeType();
					char stype ='e';//by default its equity
					stype = order.getSecurity().getSecurityType().toString().toLowerCase().charAt(0);
					String rtype=get(type,otype,stype);
					baseString+=rtype;
					
					 
			}
			securityTobaseStringMap.put(entry.getKey(), baseString);
			  
		}	
		System.out.println("Inside the func" + securityTobaseStringMap);
		 return securityTobaseStringMap;
	}
	
	public List<List<Order>> detectScenario(Map<String,List<Order>> secToOrderMap,Map<String,String> secToBaseStringMap){
		
		List<List<Integer>> indexesReturned = new ArrayList<>();
		List<List<Order>> flaggedOrders = new ArrayList<>(); 
		
		for (Map.Entry<String, String> entry : secToBaseStringMap.entrySet()){
			
			String baseString=entry.getValue();
//			String searchString="ca";
			String searchString="db";
			indexesReturned = getIndex(baseString, searchString);
			if (indexesReturned.size() == 0) {
//				Order order=new Order();
//				List<Order> dummy = new ArrayList<>();
//				dummy.add(order);
//				flaggedOrders.add(dummy);
				return flaggedOrders;
				
			} else {
				List<Order> orders = secToOrderMap.get(entry.getKey());
				List<Order> oneFlagOrder; 
				
				for(List<Integer> l:indexesReturned){
					oneFlagOrder = new ArrayList<>();
				for (int flaggedIndex : l) {
					oneFlagOrder.add(orders.get(flaggedIndex));
				}
				flaggedOrders.add(oneFlagOrder);
			}			 
		}	
	} 
		 
		return flaggedOrders;
		
		 
	}
	 public List<List<Integer>> getIndex(String baseString,String searchString){
		 int m=searchString.length();
		 int n=baseString.length();

		 List<List<Integer>> allIndexes = new ArrayList<>();
		 List<Integer> l = new ArrayList<>();
		 int j = 0;
		 for (int i = 0; i < n && j < m;i++){
			 if (j<m && baseString.charAt(i) == searchString.charAt(j)){
				 l.add(i);
				 j++;
			 }
			 if(j==m){
				 allIndexes.add(l);
				 j = 0;
				 l = new ArrayList<>();
				 
			 }
		 
		 }
		 
		if(allIndexes.size()>0)
			 return allIndexes ;
		 {
			 List<Integer> dummy = new ArrayList<>();
			 dummy.add(-1);
			 allIndexes.add(dummy);
			 return allIndexes;
		 }
		 
		 
   
	 }
	
	
	
	 
}
