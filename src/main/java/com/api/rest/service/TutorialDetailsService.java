package com.api.rest.service;

import com.api.rest.model.TutorialDetails;
import jdk.jfr.Percentage;

public interface TutorialDetailsService {

    TutorialDetails saveTutorialDetails(TutorialDetails details);

    TutorialDetails getDetailsById(long id);

    void deleteDetailsById(long id);

    void deleteByTutorialId(long tutorialId);

    TutorialDetails updateDetailsById(long id, TutorialDetails detailsRequest);
}
