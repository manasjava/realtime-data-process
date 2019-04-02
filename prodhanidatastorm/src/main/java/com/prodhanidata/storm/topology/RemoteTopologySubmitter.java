package com.prodhanidata.storm.topology;

import org.apache.storm.Config;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Component
@Profile({"live"})
public class RemoteTopologySubmitter extends TopologySubmitter {

	
	@Autowired
	public RemoteTopologySubmitter(Config stromConf) {
		super(stromConf);
	}

	@Override
	protected boolean submitTopology(PDSTopologyBuilder builder) {
		try {
			StormSubmitter.submitTopology(builder.getTopologyName(), this.stromConf,
					builder.createTopology());
			return true;
		} catch (AlreadyAliveException | InvalidTopologyException | AuthorizationException e) {
			
			e.printStackTrace();
		}
		return false;
	}

}
