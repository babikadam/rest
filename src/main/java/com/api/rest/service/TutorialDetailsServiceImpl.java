package com.api.rest.service;

import com.api.rest.model.TutorialDetails;
import com.api.rest.repository.TutorialDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TutorialDetailsServiceImpl implements  TutorialDetailsService {

    @Autowired
    private TutorialDetailsRepository detailsRepository;

    @Override
    public TutorialDetails saveTutorialDetails(TutorialDetails details) {
        return detailsRepository.save(details);
    }

    @Override
    public TutorialDetails getDetailsById(long id) {
        Optional<TutorialDetails> optionalDetails = detailsRepository.findById(id);

        if(optionalDetails.isPresent()) {
            return optionalDetails.get();
        }

        return null;
    }

    @Override
    public void deleteDetailsById(long id) {
        detailsRepository.deleteById(id);
    }

    @Override
    public void deleteByTutorialId(long tutorialId) {
        detailsRepository.deleteByTutorialId(tutorialId);
    }

    @Override
    public TutorialDetails updateDetailsById(long id, TutorialDetails detailsRequest) {
        TutorialDetails details = getDetailsById(id);
        if(details == null) {
            return null;
        }

        details.setCreatedBy(detailsRequest.getCreatedBy());

        return details;
    }

}
