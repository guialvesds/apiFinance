package gads.web.financeOn.business.controller;

import gads.web.financeOn.business.services.TaskService;
import gads.web.financeOn.infrastructure.entity.TaskEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskEntity>> findAll() {
        var users = this.taskService.getAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> findById(@PathVariable String id) {
        var user = this.taskService.finById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<TaskEntity> save(@RequestBody TaskEntity task) {
        task = this.taskService.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        this.taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/completed")
    public ResponseEntity<Void> completed(@PathVariable String id) {
        this.taskService.completed(id);
        return ResponseEntity.noContent().build();
    }

}