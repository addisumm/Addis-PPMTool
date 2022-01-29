package io.agileintelligence.ppmtool.services;

import io.agileintelligence.ppmtool.domain.Backlog;
import io.agileintelligence.ppmtool.domain.ProjectTask;
import io.agileintelligence.ppmtool.repositories.BacklogRepository;
import io.agileintelligence.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {
    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTask addProjectTask (String projectIdentifier, ProjectTask projectTask) {

        // Exceptions: project not found
        //PTs to be added to a specific project, project!=null, BL exists
        Backlog backlog = backlogRepository.findByProjectIdentifier (projectIdentifier);

        //set the backlog (bl) to the projectTask (pt)
        projectTask.setBacklog (backlog);

        //We want our projectSequence to be like this: IDPRO-1, IDPRO-2,
        Integer backlogPTSequence = backlog.getPTSeqquence ();

        //update the Backlog (BL) SEQUENCE
        backlogPTSequence++;
        //Add sequence to the projectTask
        projectTask.setProjectSequence (projectIdentifier + "-" + backlogPTSequence);
        projectTask.setProjectIdentifer (projectIdentifier);

        //INITIAL priority when priority null, group the tasks based on their PRIORITY
        if ( projectTask.getPriority () == null) { 
            projectTask.setPriority (3);
        }
        //INITIAL status when status is null
        if (projectTask.getStatus () == "" || projectTask.getStatus () == null) {
            projectTask.setStatus ("To_DO");
        }
        return projectTaskRepository.save (projectTask);
    }
}