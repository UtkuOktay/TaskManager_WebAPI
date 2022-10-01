# Task Manager - Web API
This is the Web API part of the Task Manager project developed with Java and Spring Boot. It provides the basic CRUD operations to the front-end application. It uses PostgreSQL to store and manage the data.

## Endpoints
Default URL: http://localhost:8080/api/task

|HTTP Method|URL|Description|Parameter|
|---|---|---|---|
|GET|/list|Returns a list of all tasks.|-|
|GET|/{id}|Returns all attributes of the requested task.|In URL - ID of the task|
|POST|/add|Adds the provided task.|In body - Name of the task - as plain text|
|PUT|/update|Updates the provided task.|In body - ID, name and isCompleted attributes - as JSON|
|DELETE|/delete/{id}|Deletes the provided task.|In URL - ID of the task|


## Request and Response Examples
### **Example 1**
**Request:**

HTTP GET request to /list

**Response:**
```json
[
    {
        "id": "0",
        "name": "Task 1",
        "isCompleted": true
    },
    {
        "id": "1",
        "name": "Task 2",
        "isCompleted": false
    }
]
```

### **Example 2**
**Request:**

HTTP PUT request to /update

```json
{
  "id": "0",
  "name": "Task 1",
  "isCompleted": true
}
```

**Response:**

HTTP 200

## UI
The repository for front-end can be accessed from [here](https://github.com/UtkuOktay/Task-Manager_UI).