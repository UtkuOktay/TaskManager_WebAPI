package com.example.taskmanagerwebapi.Task;

import com.example.taskmanagerwebapi.Database.DatabaseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {
    private final DatabaseHelper databaseHelper;

    @Autowired
    public TaskController(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    @GetMapping("/list")
    public List<TaskItem> getTasks() {
        return databaseHelper.getTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTask(@PathVariable String id) {
        TaskItem task = databaseHelper.getTask(id);

        if (task == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(task);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addTask(@RequestBody String name) {
        String result = databaseHelper.insertTask(name, false);

        if (result != null)
            return ResponseEntity.badRequest().body(result);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateTask(@RequestBody TaskItem task) {
        String result = databaseHelper.updateTask(task);

        if (result != null)
            return ResponseEntity.badRequest().body(result);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable String id) {
        String result = databaseHelper.deleteTask(id);

        if (result != null)
            return ResponseEntity.badRequest().body(result);

        return ResponseEntity.ok().build();
    }
}
