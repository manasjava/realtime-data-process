package com.prodhanidata.cassandra.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.prodhanidata.cassandra.entity.OrderKey;
import com.prodhanidata.cassandra.entity.OrdersEntity;

@Repository("orderRepository")
public interface OrdersRepository extends CassandraRepository<OrdersEntity, OrderKey> ,Serializable{
	
	@AllowFiltering
	List<OrdersEntity> findOrdersEntityByOrderKeySessionId(String sessionId);
	
	@AllowFiltering
	List<OrdersEntity> findOrdersEntityByOrderKeyOrderId(String orderId);
	
	@AllowFiltering
	List<OrdersEntity> findOrdersEntityByOrderKeySessionIdAndOrderKeyOrderId(String SessionId,String orderId);
}
