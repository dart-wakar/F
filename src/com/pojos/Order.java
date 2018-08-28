package com.pojos;

public class Order {
    int orderId;
    String clientId;
    Security security;
    String tradeDate;
    int ScenarioId;
 
    
    char  tradeType;
    double limitPrice;
    char traderType;
    
    public Order() {
		// TODO Auto-generated constructor stub
	}

	public Order(int orderId, Security security, char tradeType, char traderType) {
		super();
		this.orderId = orderId;
		this.security = security;
		
		this.tradeType = tradeType;
		
		this.traderType = traderType;
	}






	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", clientId=" + clientId + ", security=" + security + ", tradeDate="
				+ tradeDate + ", tradeType=" + tradeType + ", limitPrice=" + limitPrice + ", traderType=" + traderType
				+ "]";
	}



	public int getOrderId() {
		return orderId;
	}



	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}



	public String getClientId() {
		return clientId;
	}



	public void setClientId(String clientId) {
		this.clientId = clientId;
	}



	public Security getSecurity() {
		return security;
	}



	public void setSecurity(Security security) {
		this.security = security;
	}



	public String getTradeDate() {
		return tradeDate;
	}



	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}



	public char getTradeType() {
		return tradeType;
	}



	public void setTradeType(char tradeType) {
		this.tradeType = tradeType;
	}



	public double getLimitPrice() {
		return limitPrice;
	}



	public void setLimitPrice(double limitPrice) {
		this.limitPrice = limitPrice;
	}



	public char getTraderType() {
		return traderType;
	}



	public void setTraderType(char traderType) {
		this.traderType = traderType;
	}
    
}
