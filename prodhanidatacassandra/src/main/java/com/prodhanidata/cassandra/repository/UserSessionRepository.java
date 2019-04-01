package com.prodhanidata.cassandra.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.prodhanidata.cassandra.entity.SessionKey;
import com.prodhanidata.cassandra.entity.UserSession;

@Repository("userSessionRepository")
public interface UserSessionRepository extends CassandraRepository<UserSession, SessionKey> ,Serializable{
	
	@AllowFiltering
	List<UserSession> findUserSessionBySessionKeySessionId(String sessionId);
	
	@AllowFiltering
	List<UserSession> findUserSessionBySessionKeyUserId(String userId);
	
//	@AllowFiltering
//	List<UserSession> findUserSessionByClickurlUrl(String url);
}
