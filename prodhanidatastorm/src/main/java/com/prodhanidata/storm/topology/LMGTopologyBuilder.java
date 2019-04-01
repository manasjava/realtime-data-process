package com.prodhanidata.storm.topology;

import java.util.Map;

import org.apache.storm.topology.TopologyBuilder;

public class LMGTopologyBuilder extends TopologyBuilder{

	private String topologyName;
	private Map stormConf;
	
	public LMGTopologyBuilder(String topologyName) {
		super();
		this.topologyName = topologyName;
	}
	public LMGTopologyBuilder(String topologyName,Map stormConf) {
		super();
		this.topologyName = topologyName;
		this.stormConf = stormConf;
	}
	public String getTopologyName() {
		return topologyName;
	}
	public Map getStormConf() {
		return stormConf;
	}
	public void setStormConf(Map stormConf) {
		this.stormConf = stormConf;
	}
	
}
