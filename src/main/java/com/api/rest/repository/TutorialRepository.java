package com.api.rest.repository;

import com.api.rest.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

    List<Tutorial> findByIsPublished(boolean isPublished); //return all tutorials with published value as input
    List<Tutorial> findByTitleContaining(String title); // returns all tutorials which title contains input title


}
