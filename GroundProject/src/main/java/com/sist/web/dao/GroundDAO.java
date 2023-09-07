package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.web.entity.*;

@Repository
public interface GroundDAO extends JpaRepository<GroundEntity, Integer>{
	@Query(value = "SELECT * "
				 + "FROM ground "
				 + "ORDER BY hit DESC "
				 + "limit 3", nativeQuery = true)
	public List<GroundEntity> groundHitData();
	
	@Query(value = "SELECT * "
				 + "FROM ground "
				 + "ORDER BY rand() "
				 + "LIMIT 3", nativeQuery = true)
	public List<GroundEntity> groundRandData();
	
	@Query(value = "SELECT * "
				 + "FROM ground "
				 + "WHERE gaddr LIKE CONCAT('%',:fd,'%') "
				 + "ORDER BY gno ASC "
				 + "limit :start, 9", nativeQuery = true)
	public List<GroundEntity> groundListData(String fd, int start);
	
	@Query(value = "SELECT CEIL(COUNT(*)/9.0) "
				 + "FROM ground "
				 + "WHERE gaddr LIKE CONCAT('%',:fd,'%')", nativeQuery = true)
	public int groundTotalPage(String fd);
	
	public GroundEntity findByGno(int gno);
	
}
