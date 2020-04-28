package com.session.session.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends CrudRepository<VisitSaver, Integer> {


    @Query(value = "SELECT id, user, user_info FROM sessions.notes n  WHERE n.user = :name", nativeQuery = true)
    List<VisitSaver> getUserSessions(@Param("name") String userName);

    @Query(value = "SELECT user_info AS `browser`, COUNT(*) AS `count` FROM sessions.notes GROUP BY user_info ", nativeQuery = true)
    List<StatRow> getUses();

    interface StatRow {
        String getBrowser();
        int getCount();
    }
}
