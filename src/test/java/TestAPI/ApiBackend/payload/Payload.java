package TestAPI.ApiBackend.payload;

import TestAPI.ApiBackend.payload.builders.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Payload implements TestAPI.ApiBackend.payload.PayloadConst {


    private static final String CREATE_PRIV_PROJ = "createMyPrivateProject";
    private static final int CREATE_PRIV_PROJ_ID = 1271580569;

    public String createUser(String username, String password) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(TestAPI.ApiBackend.payload.builders.CreateUser.builder()
                .jsonrpc(JSONRPC)
                .method(CREATE_USER_METHOD)
                .id(CREATE_USER_ID)
                .params(TestAPI.ApiBackend.payload.builders.CreateUser.params.builder()
                        .username(username)
                        .password(password)
                        .build())
                .build());
    }
    public String removeUser(Integer userId) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(RemoveUser.builder()
                .jsonrpc(JSONRPC)
                .method(REMOVE_USER)
                .id(REMOVE_USER_ID)
                .params(RemoveUser.Params.builder().user_id(userId).build())
                .build());
    }

    public String createNewTask(String title, int projectId) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(TestAPI.ApiBackend.payload.builders.CreateTask.builder()
                .jsonrpc(JSONRPC)
                .method(CREATE_TASK)
                .id(CREATE_TASK_ID)
                .params(TestAPI.ApiBackend.payload.builders.CreateTask.Params.builder().title(title).project_id(projectId).build())
                .build());
    }

    public String deleteTask(int taskId) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(
                RemoveTask.builder()
                        .jsonrpc(JSONRPC)
                        .method(REMOVE_TASK)
                        .id(REMOVE_TASK_ID)
                        .params(RemoveTask.Params.builder().task_id(taskId).build())
                        .build());
    }

    public String getTask(Integer taskId) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(
                GetTaskById.builder()
                        .jsonrpc(JSONRPC)
                        .method(GET_TASK)
                        .id(GET_TASK_ID)
                        .params(GetTaskById.Params.builder().task_id(taskId).build())
                        .build());
    }

    public String getTaskByAssignee(Integer projectId) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(
                GetTaskByAssignee.builder()
                        .jsonrpc(JSONRPC)
                        .method(GET_TASK_ASSIGNEE)
                        .id(GET_TASK_ASSIGNEE_ID)
                        .params(GetTaskByAssignee.Params.builder().project_id(projectId).query("assignee:nobody").build())
                        .build());
    }

    public String getTaskByAssignee(Integer projectId, String query) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(
                GetTaskByAssignee.builder()
                        .jsonrpc(JSONRPC)
                        .method(GET_TASK_ASSIGNEE)
                        .id(GET_TASK_ASSIGNEE_ID)
                        .params(GetTaskByAssignee.Params.builder().project_id(projectId).query("assignee:"+query).build())
                        .build());
    }


    public String createProject(String projectName) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(CreateProject.builder()
                .jsonrpc(JSONRPC)
                .method(CREATE_PROJECT)
                .id(CREATE_PROJECT_ID)
                .params(CreateProject.Params.builder().name(projectName).build())
                .build());
    }
    public String removeProject(Integer projectId) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(RemoveProject.builder()
                .jsonrpc(JSONRPC)
                .method(REMOVE_PROJECT)
                .id(REMOVE_PROJECT_ID)
                .params(RemoveProject.Params.builder().project_id(projectId).build())
                .build());
    }

    public String getUserByName(String username) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(
                RequestUserByName.builder()
                        .jsonrpc(JSONRPC)
                        .method(GET_USER_BY_NAME)
                        .id(GET_USER_BY_NAME_ID)
                        .params(RequestUserByName.Params.builder().username(username).build())
                        .build());
    }

    public String getAllUsers() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(
                GetAllUsers.builder()
                        .jsonrpc(JSONRPC)
                        .method(GET_ALL_USERS)
                        .id(GET_ALL_USERS_ID)
                        .build());
    }

    public String getProjectByName(String  name) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(
                GetProjectByName.builder()
                .jsonrpc(JSONRPC)
                .method(GET_PROJECT_BY_NAME)
                .id(GET_PROJECT_BY_NAME_ID)
                .params(GetProjectByName.Params.builder().name(name).build())
                .build());
    }

    public String getProjectById(int id) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(
                GetProjectById.builder()
                        .jsonrpc(JSONRPC)
                        .method(GET_PROJECT_BY_ID)
                        .id(GET_PROJECT_BY_ID_ID)
                        .params(GetProjectById.Params.builder().id(id).build())
                        .build());
    }
    
    public String getAllProjects() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(
                GetAllUsers.builder()
                .jsonrpc(JSONRPC)
                .method(GET_ALL_PROJECTS)
                .id(GET_ALL_PROJECTS_ID)
                .build());
}

    public String createMyPrivateProject(String projName) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(
                CreatePrivateProject.builder()
                        .jsonrpc(JSONRPC)
                        .method(CREATE_PRIV_PROJ)
                        .id(CREATE_PRIV_PROJ_ID)
                        .params(new String[]{projName})
                        .build());
    }
}

