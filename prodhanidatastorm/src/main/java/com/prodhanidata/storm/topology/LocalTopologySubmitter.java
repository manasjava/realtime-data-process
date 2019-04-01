package com.prodhanidata.storm.topology;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"local"})
@Component
public final class LocalTopologySubmitter extends TopologySubmitter {

	@Autowired
	public LocalTopologySubmitter(Config stromConf) {
		super(stromConf);
	}

	LocalCluster cluster = new LocalCluster();

	@Override
	protected boolean submitTopology(LMGTopologyBuilder builder) {
		cluster.submitTopology(builder.getTopologyName(), this.stromConf, builder.createTopology());
		return true;
	}
	
}
