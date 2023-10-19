package TestAPI.ApiBackend;

import TestAPI.ApiBackend.payload.Payload;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import static TestAPI.Tests.PROJECT_FOR_A_TASK_TESTING;
import static java.lang.Integer.parseInt;

public class ApiUtility implements TestApi.ApiBackend.ApiUtilityConstants {

    private final Payload payload;

    public ApiUtility() {
        this.payload = new Payload();
    }

    private Response sendRequest(String body) {
        return RestAssured.given()
                .baseUri(ENDPOINT)
                .auth().basic(JSONRPC, AUTH_TOKEN)
                .header("Content-Type", "application/json")
                .body(body)
                .post();
    }

    private Response sendUserRequest(String body, String user, String token) {
        return RestAssured.given()
                .baseUri(ENDPOINT)
                .auth().basic(user, token)
                .header("Content-Type", "application/json")
                .body(body)
                .post();
    }

    private int extractUserId(Response response) {
        return Integer.valueOf(response.getBody().jsonPath().getString("result.id"));
    }

    public ApiUtility createUser(String username, String password) throws JsonProcessingException {
        sendRequest(payload.createUser(username, password))
                .then()
                .statusCode(HTTP_SUCCESS_CODE);
        return this;
    }

    public ApiUtility removeUserById(int userId) throws JsonProcessingException {
        sendRequest(payload.removeUser(userId))
                .then()
                .statusCode(HTTP_SUCCESS_CODE);
        return this;
    }

    public int getUserId(String username) throws JsonProcessingException {
        Response response = sendRequest(payload.getUserByName(username));
        return extractUserId(response);
    }

    public ApiUtility assertUserAdd(String username) throws JsonProcessingException {
        sendRequest(payload.getUserByName(username))
                .then()
                .assertThat().statusCode(HTTP_SUCCESS_CODE)
                    .body("result.username", Matchers.equalTo(username));
        return this;
    }

    public ApiUtility assertRemovalByUsername(String username) throws JsonProcessingException {
        Response response = sendRequest(payload.getUserByName(username));
        response.then().assertThat().statusCode(HTTP_SUCCESS_CODE)
                .body("result", Matchers.equalTo(null));
        return this;
    }

    public void removeAllUsers() throws JsonProcessingException {
        Response allUsers = sendRequest(payload.getAllUsers());
        String resultString = allUsers.getBody().jsonPath().getString("result.id");
        String cleanString = resultString.replaceAll("[\\[\\] ]", "");
        String[] digitStrings = cleanString.split(",");

        for (String digitString : digitStrings) {
            int id = parseInt(digitString);
            if (id != 1) {
                removeUserById(id);
            }
        }
    }

    public ApiUtility createTask(String title, int projectId) throws JsonProcessingException {
          sendRequest(payload.createNewTask(title, projectId))
                .then()
                .statusCode(HTTP_SUCCESS_CODE);
        return this;
    }

    public ApiUtility deleteTask(int taskId) throws JsonProcessingException {
        sendRequest(payload.deleteTask(taskId))
                .then()
                .statusCode(HTTP_SUCCESS_CODE);
        return this;
    }

    public int getTaskId(int projectId) throws JsonProcessingException {
        Response task = sendRequest(payload.getTaskByAssignee(projectId));
        task.then().statusCode(HTTP_SUCCESS_CODE);
        return  task.getBody().jsonPath().getInt("result[0].id");
    }

    public int getTaskId(int projectId, String user) throws JsonProcessingException {
        Response task = sendRequest(payload.getTaskByAssignee(projectId,user));
        task.then().statusCode(HTTP_SUCCESS_CODE);
        return  task.getBody().jsonPath().getInt("result[0].id");
    }

    public ApiUtility assertTaskCreate(String title,  int projectId) throws JsonProcessingException {
        sendRequest(payload.getTaskByAssignee(projectId)).then().assertThat().statusCode(HTTP_SUCCESS_CODE).body("result[0].title", Matchers.equalTo(title));
        return this;
    }

    public ApiUtility assertTaskDelete() throws JsonProcessingException {
        sendRequest(payload.getTaskByAssignee(getProjectId(PROJECT_FOR_A_TASK_TESTING))).then().assertThat().body("result[0]",Matchers.equalTo(null));
        return this;
    }

    public ApiUtility createProject(String projName) throws JsonProcessingException {
        sendRequest(payload.createProject(projName)).then().statusCode(HTTP_SUCCESS_CODE);
        return this;
    }

    public ApiUtility createPrivateProject(String projName,String user,String tok) throws JsonProcessingException {
//        sendUserRequest(payload.createMyPrivateProject(projName),user,tok).then().statusCode(HTTP_SUCCESS_CODE);
        sendUserRequest(payload.createMyPrivateProject(projName),user,tok).prettyPrint();
        return this;
    }

    public ApiUtility removeProject(int projId) throws JsonProcessingException {
          sendRequest(payload.removeProject(projId))
                .then()
                .statusCode(HTTP_SUCCESS_CODE);
        return this;
    }

    public int getProjectId(String projectName) throws JsonProcessingException {
        Response project = sendRequest(payload.getProjectByName(projectName));
        return project.getBody().jsonPath().getInt("result.id");
    }
    public void removeAllProjects() throws JsonProcessingException {
        Response allProjects = sendRequest(payload.getAllProjects());
        String resultString = allProjects.getBody().jsonPath().getString("result.id");
        String cleanString = resultString.replaceAll("[\\[\\] ]", "");
        String[] digitStrings = cleanString.split(",");

        for (String digitString : digitStrings) {
            if (!digitString.isEmpty()) {
                int id = parseInt(digitString);
                removeProject(id);
            }
        }
    }

    public ApiUtility assertProjectAdd(String projectName) throws JsonProcessingException {
        sendRequest(payload.getProjectByName(projectName))
                .then().assertThat().statusCode(HTTP_SUCCESS_CODE)
                .body("result.name", Matchers.equalTo(projectName));
        return this;
    }

    public ApiUtility assertRemoveProject(int id) throws JsonProcessingException {
        sendRequest(payload.getProjectById(id))
                .then()
                .assertThat().statusCode(HTTP_SUCCESS_CODE)
                .body("error.data", Matchers.equalTo("Missing argument: project_id"));
        return this;
    }
}
