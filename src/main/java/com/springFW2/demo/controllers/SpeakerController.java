package com.springFW2.demo.controllers;

import com.springFW2.demo.models.Session;
import com.springFW2.demo.models.Speaker;
import com.springFW2.demo.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/speakers")
public class SpeakerController {
    @Autowired
    private SpeakerRepository speakerRepository;
    @GetMapping
    private List<Speaker> list(){
        return speakerRepository.findAll();
    }
    @GetMapping
    @RequestMapping("{id}")
    private Speaker get(@PathVariable Long id){
        return speakerRepository.getOne(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Speaker create(@RequestBody final Speaker speaker){
        return speakerRepository.saveAndFlush(speaker);
    }
    @RequestMapping(value="{id}", method = RequestMethod.DELETE)
    private void delete(@PathVariable Long id){
        speakerRepository.deleteById(id);
    }
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    private Speaker update(@PathVariable Long id, @RequestBody Session session){
        Speaker existingSession = speakerRepository.getOne(id);
        BeanUtils.copyProperties(session, existingSession, "speaker_id");
        return speakerRepository.saveAndFlush(existingSession);
    }


}
