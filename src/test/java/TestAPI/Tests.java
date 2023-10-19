package TestAPI;

import TestAPI.ApiBackend.ApiUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Tests {
    public static final String  ENDPOINT = "http://127.0.0.1/jsonrpc.php";
    public static final String ADD_REM_USER_DATA = "TestUser_for_createDeleteUser";
    public static final String PASSWOED_1 = "passwoed#1";
    public static final String FANCY_TEST_PROJECT = "Fancy Project";
    public static final String PROJECT_FOR_A_TASK_TESTING = "Project for a task testing";
    public static final String FUNNY_TASK = "FUNNY (NO) TASK";

    ApiUtility apiUtility = new ApiUtility();

    @BeforeClass
    private void setup() throws JsonProcessingException {
        RestAssured.baseURI = ENDPOINT;
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader("Content-Type", "application/json")
                .build();
    }

    @AfterTest
    @BeforeTest
    public void cleanUp() throws JsonProcessingException {
        apiUtility.removeAllUsers();
        apiUtility.removeAllProjects();
    }

    @Test
    public void createDeleteUser() throws JsonProcessingException {
        apiUtility.createUser(ADD_REM_USER_DATA, PASSWOED_1)
                .assertUserAdd(ADD_REM_USER_DATA)
                .removeUserById(apiUtility.getUserId(ADD_REM_USER_DATA))
                .assertRemovalByUsername(ADD_REM_USER_DATA);
    }

    @Test
    public void createDeleteProject() throws JsonProcessingException {
        ApiUtility apiProjects = new ApiUtility();
        apiProjects.createProject(FANCY_TEST_PROJECT);
        apiProjects.assertProjectAdd(FANCY_TEST_PROJECT);
        int projectId = apiProjects.getProjectId(FANCY_TEST_PROJECT);
        apiProjects.removeProject(projectId)
                   .assertRemoveProject(projectId);
    }

    @Test
    public void createDeleteTask() throws JsonProcessingException {
        apiUtility.createProject(PROJECT_FOR_A_TASK_TESTING)
                .createTask(FUNNY_TASK, apiUtility
                        .getProjectId(PROJECT_FOR_A_TASK_TESTING))
                .assertTaskCreate(FUNNY_TASK, apiUtility.getProjectId(PROJECT_FOR_A_TASK_TESTING))
                .deleteTask(apiUtility.getTaskId(apiUtility.getProjectId(PROJECT_FOR_A_TASK_TESTING)))
                .assertTaskDelete();
    }






}
