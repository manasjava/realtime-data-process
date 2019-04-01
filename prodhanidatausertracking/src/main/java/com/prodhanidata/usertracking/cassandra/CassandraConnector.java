package com.prodhanidata.usertracking.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.Session;

public class CassandraConnector {

	
	private Cluster cluster;
	 
    private Session session;
 
    public void connect(String node, Integer port) {
        Builder b = Cluster.builder().addContactPoint(node);
        if (port != null) {
            b.withPort(port);
        }
        cluster = b.build();
 
        session = cluster.connect();
    }
 
    public Session getSession() {
        return this.session;
    }
 
    public void close() {
        session.close();
        cluster.close();
    }
    
    public static void main(String[] args) {
    	CassandraConnector cassandraConnector = new  CassandraConnector();
		cassandraConnector.connect("172.11.17.109", 9042);
		Session session =  cassandraConnector.getSession();
		cassandraConnector.close();
		
	}
}
