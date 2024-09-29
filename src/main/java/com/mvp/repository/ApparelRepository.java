package com.mvp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mvp.model.Apparel;
import com.mvp.model.User;

public interface ApparelRepository extends JpaRepository<Apparel, Long> {
	List<Apparel> findByUserId(Long userId);
	List<Apparel> findByUser(User user);
	
	@Query("SELECT a FROM Apparel a ORDER BY a.id") 
	List<Apparel> findTopNApparels(int limit);
}
