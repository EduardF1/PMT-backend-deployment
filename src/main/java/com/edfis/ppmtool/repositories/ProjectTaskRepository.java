package com.edfis.ppmtool.repositories;

import com.edfis.ppmtool.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;

public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {
    Iterable<ProjectTask> findByProjectIdentifierOrderByPriority(String backlogId);
    ProjectTask findByProjectSequence(String sequence);
}
