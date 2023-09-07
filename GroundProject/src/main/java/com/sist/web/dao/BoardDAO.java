package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.BoardEntity;

@Repository
public interface BoardDAO extends JpaRepository<BoardEntity, Integer> {
	@Query(value = "SELECT * "
				 + "FROM board "
				 + "ORDER BY no DESC "
				 + "LIMIT :start, 10", nativeQuery = true)
	public List<BoardEntity> boardListData(@Param("start") Integer start);
	
	@Query(value = "SELECT CEIL(COUNT(*)/10.0) "
				 + "FROM board ", nativeQuery = true)
	public int boardTotalPage();
	
	public BoardEntity findByNo(int no);
}
