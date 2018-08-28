package com.pojos;

public class Scenario {

	private int scenarioId;
	private String scenarioType;
	
	public Scenario() {
		// TODO Auto-generated constructor stub
		scenarioId=0;
		scenarioType="Scenario 1";
	}
	public Scenario(String scenarioType) {
		 
		this.scenarioType = scenarioType;
	}
	public int getScenarioId() {
		return scenarioId;
	}
	public void setScenarioId(int scenarioId) {
		this.scenarioId = scenarioId;
	}
	public String getScenarioType() {
		return scenarioType;
	}
	public void setScenarioType(String scenarioType) {
		this.scenarioType = scenarioType;
	}
	



}
