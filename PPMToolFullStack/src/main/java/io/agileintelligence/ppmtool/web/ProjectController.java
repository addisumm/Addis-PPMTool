package io.agileintelligence.ppmtool.web;

import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.domain.ProjectTask;
import io.agileintelligence.ppmtool.services.MapValidationErrorService;
import io.agileintelligence.ppmtool.services.ProjectService;
import io.agileintelligence.ppmtool.services.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private ProjectTaskService projectTaskService;

    @PostMapping
    public ResponseEntity <?> createNewProject (@Valid @RequestBody Project project, BindingResult result) {
        ResponseEntity <?> errorMap = mapValidationErrorService.mapVlidationService (result);
        if (errorMap != null) return errorMap;
        projectService.saveOrUpdate (project);
        return new ResponseEntity <> (project, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity <?> getProjectById (@PathVariable String projectId) {
        Project project = projectService.findProjectByIdentifier (projectId);
        return new ResponseEntity <Project> (project, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable <Project> getAllProjects () {
        return projectService.findAllProjects ();
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity <?> deleteProject (@PathVariable String projectId) {
        projectService.deleteProjectByIdentifier (projectId);
        return new ResponseEntity <String> ("Project with Id ' " + projectId + " 'was deleted ", HttpStatus.OK);
    }
    @GetMapping("{backlog_id}/{pt_id}")
    public ResponseEntity<?> getProjectTask(@PathVariable String backlog_id, @PathVariable String pt_id){
        ProjectTask projectTask=projectTaskService.findPTByProjectSequence (backlog_id,pt_id);
        return  new ResponseEntity <ProjectTask> (projectTask, HttpStatus.OK);
    }
}