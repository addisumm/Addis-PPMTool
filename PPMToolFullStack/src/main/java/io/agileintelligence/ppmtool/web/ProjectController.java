package io.agileintelligence.ppmtool.web;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.services.MapValidationErrorService;
import io.agileintelligence.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping
    public ResponseEntity <?> createNewProject (@Valid @RequestBody Project project, BindingResult result) {
        ResponseEntity <?> errorMap = mapValidationErrorService.mapVlidationService (result);
        if (errorMap != null) return errorMap;
        projectService.saveOrUpdate (project);
        return new ResponseEntity <> (project, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId){
        Project project= projectService.findProjectByIdentifier (projectId);
        return  new ResponseEntity <Project> (project,HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects(){
        return projectService.findAllProjects ();
    }

}