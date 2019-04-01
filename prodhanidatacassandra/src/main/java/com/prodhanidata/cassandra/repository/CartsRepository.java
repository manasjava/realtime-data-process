package com.prodhanidata.cassandra.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.prodhanidata.cassandra.entity.CartEntity;
import com.prodhanidata.cassandra.entity.CartKey;

@Repository("cartRepository")
public interface CartsRepository extends CassandraRepository<CartEntity, CartKey> ,Serializable{
	
	@AllowFiltering
	List<CartEntity> findCartEntityByCartKeySessionId(String sessionId);
	
	@AllowFiltering
	List<CartEntity> findCartEntityByCartKeyCartId(String orderId);
	
	@AllowFiltering
	List<CartEntity> findCartEntityByCartKeySessionIdAndCartKeyCartId(String SessionId,String cartId);
}

