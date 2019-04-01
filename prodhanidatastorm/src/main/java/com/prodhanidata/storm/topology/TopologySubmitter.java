package com.prodhanidata.storm.topology;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.storm.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

public abstract class TopologySubmitter {

	protected Config stromConf = null;
	public TopologySubmitter(Config stromConf) {
		this.stromConf = stromConf;
	}
	@Autowired
	protected List<AbstractLMGTopology> topologyList;

	public void loadTopology() {

		if (!CollectionUtils.isEmpty(topologyList)) {

			topologyList.forEach(topology -> {

				LMGTopologyBuilder builder = (LMGTopologyBuilder) topology.buildTopologyBuilder();

				if (builder.getStormConf() == null) {
					builder.setStormConf(getDefaultStormConfig());
				}

				this.submitTopology(builder);
			});
		}
	}

	protected Properties getDefaultStormConfig() {
		Properties configs = new Properties();
		try {
			configs.load(TopologySubmitter.class.getClassLoader().getResourceAsStream("default_configs.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return configs;
	}

	protected abstract boolean submitTopology(LMGTopologyBuilder builder);

	public List<AbstractLMGTopology> getTopologyList() {
		return topologyList;
	}

	public void setTopologyList(List<AbstractLMGTopology> topologyList) {
		this.topologyList = topologyList;
	}
	
	
}
