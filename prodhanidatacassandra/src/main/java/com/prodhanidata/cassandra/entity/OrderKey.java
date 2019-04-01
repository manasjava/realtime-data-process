package com.prodhanidata.cassandra.entity;

import static org.springframework.data.cassandra.core.cql.Ordering.DESCENDING;
import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class OrderKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKeyColumn(name = "id", type = PARTITIONED)
	private UUID id;

	@PrimaryKeyColumn(name = "sessionId", ordinal = 0)
	private String sessionId;

	@PrimaryKeyColumn(name = "orderId", ordinal = 1, ordering = DESCENDING)
	private String orderId;

	public OrderKey() {
		super();
	}

	public OrderKey(UUID id,String sessionId, String orderId) {
		super();
		this.id=id;
		this.sessionId = sessionId;
		this.orderId = orderId;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "OrderKey [id=" + id + ", sessionId=" + sessionId + ", orderId=" + orderId + "]";
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		OrderKey other = (OrderKey) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		
		return true;
	}
	
	
}
