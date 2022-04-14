package codesquad.be.todoserver;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DisplayName("API /api/todos/* 인수 테스트")
class TodosApiAcceptanceTest {

    @LocalServerPort
    int port;

    @BeforeEach
    @Sql({"/testDB/schema.sql", "/testDB/data.sql"})
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void 특정_투두리스트_조회_성공() {
        given()
            .accept(MediaType.APPLICATION_JSON_VALUE)

        .when()
            .get("/api/todos/1")

        .then()
            .statusCode(HttpStatus.OK.value())
            .assertThat()
            .body("id", equalTo(1))
            .body("title", equalTo("Github 공부하기"))
            .body("contents", equalTo("add, commit, push"))
            .body("user", equalTo("sam"))
            .body("status", equalTo("doing"));
    }

    @Test
    void 특정_투두리스트_조회_실패() {
        given()
            .accept(MediaType.APPLICATION_JSON_VALUE)

        .when()
            .get("/api/todos/4444")

        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void 정상적인_todo_json_요청시_todo_생성() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("title", "Todo 생성하기 API 개발");
        requestBody.put("contents", "기능 구현, 테스트");
        requestBody.put("user", "sam");
        requestBody.put("status", "doing");


        given()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .header("Content-type", "application/json")
            .body(requestBody)

        .when()
            .post("/api/todos")

        .then()
            .statusCode(HttpStatus.CREATED.value())
            .assertThat()
            .body("id", equalTo(5))
            .body("title", equalTo("Todo 생성하기 API 개발"))
            .body("contents", equalTo("기능 구현, 테스트"))
            .body("user", equalTo("sam"))
            .body("status", equalTo("doing"));
    }

    @Test
    void Todo의_타이틀_수정_요청시_수정된_Todo를_반환한다() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("title", "블로그에 포스팅 꼭~~~~하기");

        given()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .header("Content-type", "application/json")
            .body(requestBody)

        .when()
            .patch("/api/todos/2")

        .then()
            .statusCode(HttpStatus.OK.value())
            .assertThat()
            .body("id", equalTo(2))
            .body("title", equalTo("블로그에 포스팅 꼭~~~~하기"))
            .body("contents", equalTo("*Github 공부내용 \\r\\n *모던 자바스크립트 1장 공부내용"))
            .body("user", equalTo("sam"))
            .body("status", equalTo("todo"));
    }

    @Test
    void Todo의_본문_수정_요청시_수정된_Todo를_반환한다() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("contents", "오늘은 Github 공부를 했다. 오늘에서야 쓴다!");

        given()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .header("Content-type", "application/json")
            .body(requestBody)

        .when()
            .patch("/api/todos/2")

        .then()
            .statusCode(HttpStatus.OK.value())
            .assertThat()
            .body("id", equalTo(2))
            .body("title", equalTo("블로그에 포스팅 꼭~~~~하기"))
            .body("contents", equalTo("오늘은 Github 공부를 했다. 오늘에서야 쓴다!"))
            .body("user", equalTo("sam"))
            .body("status", equalTo("todo"));
    }

    @Test
    void Todo의_타이틀과_본문_수정_요청시_수정된_Todo를_반환한다() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("title", "블로그에 포스팅 꼭~~~~하기");
        requestBody.put("contents", "오늘은 Github 공부를 했다. 오늘에서야 쓴다!");

        given()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .header("Content-type", "application/json")
            .body(requestBody)

        .when()
            .patch("/api/todos/2")

        .then()
            .statusCode(HttpStatus.OK.value())
            .assertThat()
            .body("id", equalTo(2))
            .body("title", equalTo("블로그에 포스팅 꼭~~~~하기"))
            .body("contents", equalTo("오늘은 Github 공부를 했다. 오늘에서야 쓴다!"))
            .body("user", equalTo("sam"))
            .body("status", equalTo("todo"));
    }

    @Test
    void Todo의_상태_수정_요청시_수정된_Todo를_반환한다() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("status", "doing");

        given()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .header("Content-type", "application/json")
            .body(requestBody)

        .when()
            .patch("/api/todos/2")

        .then()
            .statusCode(HttpStatus.OK.value())
            .assertThat()
            .body("id", equalTo(2))
            .body("title", equalTo("블로그에 포스팅 꼭~~~~하기"))
            .body("contents", equalTo("오늘은 Github 공부를 했다. 오늘에서야 쓴다!"))
            .body("user", equalTo("sam"))
            .body("status", equalTo("doing"));
    }
}
