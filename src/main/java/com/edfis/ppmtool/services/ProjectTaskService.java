package com.edfis.ppmtool.services;

import com.edfis.ppmtool.domain.Backlog;
import com.edfis.ppmtool.domain.Project;
import com.edfis.ppmtool.domain.ProjectTask;
import com.edfis.ppmtool.exceptions.project.ProjectNotFoundException;
import com.edfis.ppmtool.repositories.BacklogRepository;
import com.edfis.ppmtool.repositories.ProjectRepository;
import com.edfis.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {

    private final BacklogRepository backlogRepository;
    private final ProjectTaskRepository projectTaskRepository;
    private final ProjectRepository projectRepository;
    private final ProjectService projectService;

    public ProjectTaskService(BacklogRepository backlogRepository, ProjectTaskRepository projectTaskRepository, ProjectRepository projectRepository, ProjectService projectService) {
        this.backlogRepository = backlogRepository;
        this.projectTaskRepository = projectTaskRepository;
        this.projectRepository = projectRepository;
        this.projectService = projectService;
    }

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask, String username) {

            Backlog backlog = projectService.findProjectByIdentifier(projectIdentifier, username).getBacklog();
            projectTask.setBacklog(backlog);
            Integer backlogPTSequence = backlog.getPTSequence();
            backlogPTSequence++;
            backlog.setPTSequence(backlogPTSequence);
            projectTask.setProjectSequence(projectIdentifier + "-" + backlogPTSequence);
            projectTask.setProjectIdentifier(projectIdentifier);
            if (projectTask.getPriority() == null || projectTask.getPriority()==0) {
                projectTask.setPriority(3);
            }

            if (projectTask.getStatus() == null) {
                projectTask.setStatus("TO_DO");
            }
            return projectTaskRepository.save(projectTask);

    }

    public Iterable<ProjectTask> findBacklogById(String backlogId, String username) {
        Project project = projectService.findProjectByIdentifier(backlogId, username);
        if(project == null){
            throw new ProjectNotFoundException("Project with ID: '" + backlogId + "' does not exist");
        }
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(backlogId);
    }

    public ProjectTask findProjectTaskByProjectSequence(String backlogId, String projectTaskId, String username){
        projectService.findProjectByIdentifier(backlogId, username);
        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(projectTaskId);
        if (projectTask == null){
            throw new ProjectNotFoundException("Project with ID: '" + projectTaskId + "' does not exist");
        }

        if(!projectTask.getProjectIdentifier().equals(backlogId)){
            throw new ProjectNotFoundException("Project task '" +
                    projectTaskId +
                    "' does not exist in project: '" +
                    backlogId);
        }
        return projectTask;
    }

    public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlogId, String projectTaskId, String username){
        return projectTaskRepository.save(updatedTask);
    }

    public void deleteProjectTaskByProjectSequence(String backlogId, String projectTaskId, String username){
        ProjectTask projectTask = findProjectTaskByProjectSequence(backlogId,projectTaskId, username);
        projectTaskRepository.delete(projectTask);
    }
}
