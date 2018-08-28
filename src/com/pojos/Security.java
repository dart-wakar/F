package com.pojos;

public class Security {
	
	private String isin;
	private String companyName;
	public enum SecurityType { Equity, Call, Put, Future };
	private SecurityType securityType;
	
	public Security() {
		// TODO Auto-generated constructor stub
		isin = "jdiwjw";
		companyName = "Apple";
		securityType = SecurityType.Equity;
	}

	public Security(String isin, String companyName, SecurityType securityType) {
		super();
		this.isin = isin;
		this.companyName = companyName;
		this.securityType = securityType;
	}

	public String getIsin() {
		return isin;
	}

	public void setIsin(String isin) {
		this.isin = isin;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public SecurityType getSecurityType() {
		return securityType;
	}

	public void setSecurityType(SecurityType securityType) {
		this.securityType = securityType;
	}

	@Override
	public String toString() {
		return "Security [isin=" + isin + ", companyName=" + companyName + ", securityType=" + securityType + "]";
	}

}
