package io.agileintelligence.ppmtool.web;

import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity <?> createNewProject(@Valid @RequestBody Project project, BindingResult result){
        if(result.hasErrors ()){
            return new ResponseEntity <String> ("Invalid Object", HttpStatus.BAD_REQUEST);
        }
         projectService.saveOrUpdate (project)        ;
        return new ResponseEntity <> (project, HttpStatus.CREATED);
    }
}