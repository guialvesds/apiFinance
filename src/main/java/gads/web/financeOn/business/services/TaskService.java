package gads.web.financeOn.business.services;

import gads.web.financeOn.infrastructure.entity.TaskEntity;
import gads.web.financeOn.infrastructure.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskEntity save(TaskEntity taskEntity) {
        return this.taskRepository.save(taskEntity);
    }

    public List<TaskEntity> getAll() {
        return this.taskRepository.findAll();
    }

    public TaskEntity finById(String taskId){
        return this.taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Taks not found"));
    }

    public void completed(String taskId) {
        var taskCompleted = this.finById(taskId);
        taskCompleted.setCompleted(true);
        this.save(taskCompleted);

    }

    public void delete(String taskId){
        this.taskRepository.deleteById(taskId);
    }

}
