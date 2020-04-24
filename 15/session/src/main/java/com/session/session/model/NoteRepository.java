package com.session.session.model;

import com.session.session.Beans.SessionBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends CrudRepository<VisitSaver, Integer> {
}
