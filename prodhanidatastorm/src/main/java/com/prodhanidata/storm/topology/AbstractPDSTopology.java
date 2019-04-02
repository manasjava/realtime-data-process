package com.prodhanidata.storm.topology;

import org.apache.storm.topology.TopologyBuilder;

public abstract class AbstractPDSTopology {
	
	protected abstract TopologyBuilder buildTopologyBuilder();

}
