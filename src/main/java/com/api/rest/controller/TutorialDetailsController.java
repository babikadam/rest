package com.api.rest.controller;

import com.api.rest.model.Tutorial;
import com.api.rest.model.TutorialDetails;
import com.api.rest.service.TutorialDetailsService;
import com.api.rest.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class TutorialDetailsController {

    @Autowired
    private TutorialDetailsService tutorialDetailsService;

    @Autowired
    private TutorialService tutorialService;

    @PostMapping("/tutorials/{tutorialId}/details")
    public ResponseEntity<TutorialDetails> createDetails(@PathVariable(value = "tutorialId") long tutorialId,
                                                        @RequestBody TutorialDetails detailsRequest) {
        Tutorial tutorial = tutorialService.getTutorialById(tutorialId);

        if(tutorial == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        detailsRequest.setCreatedOn(new Date());
        detailsRequest.setTutorial(tutorial);

        //save tutorial details database
//        TutorialDetails details = tutorialDetailsService.saveTutorialDetails(detailsRequest);

        return new ResponseEntity<>(tutorialDetailsService.saveTutorialDetails(detailsRequest), HttpStatus.CREATED);
    }

    @GetMapping({"/details/{id}", "/tutorials/{id}/details"})
    public ResponseEntity<TutorialDetails> getDetailsById(@PathVariable(value = "id") long id) {
        TutorialDetails details = tutorialDetailsService.getDetailsById(id);

        if(details == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @PutMapping("/details/{id}")
    public ResponseEntity<TutorialDetails> updateDetails(@PathVariable("id") long id,
                                                         @RequestBody TutorialDetails detailsRequest) {
        TutorialDetails details = tutorialDetailsService.updateDetailsById(id, detailsRequest);

        if(details == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

       // TutorialDetails _details = tutorialDetailsService.saveTutorialDetails(detailsRequest);

        return new ResponseEntity<>(tutorialDetailsService.saveTutorialDetails(details), HttpStatus.OK);
    }

    @DeleteMapping("/details/{id}")
    public ResponseEntity<HttpStatus> deleteDetail(@PathVariable("id") long id) {
        try {
            //delete detail
            tutorialDetailsService.deleteDetailsById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/tutorials/{tutorialId}/details")
    public ResponseEntity<TutorialDetails> deleteDetailsTutorial(@PathVariable("tutorialId") long tutorialId) {
        if(!tutorialService.existsTutorialById(tutorialId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //delete details

        tutorialDetailsService.deleteByTutorialId(tutorialId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
