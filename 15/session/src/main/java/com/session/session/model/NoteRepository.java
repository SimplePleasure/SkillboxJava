package com.session.session.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends CrudRepository<VisitSaver, Integer> {

    @Query(value = "SELECT user_info as `browser`, COUNT(*) as `count` FROM sessions.notes Group BY user_info ", nativeQuery = true)
    List<Object[]> getStatistic();

}
