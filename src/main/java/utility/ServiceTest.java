package utility;

import com.aventstack.extentreports.Status;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import okhttp3.Headers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertEquals;

public class ServiceTest extends main.java.Base.TestBaseEndPoint {

    public static int RESPONSE_STATUS_CODE_200 = 200;
    public static int RESPONSE_STATUS_CODE_500 = 500;
    public static int RESPONSE_STATUS_CODE_400 = 400;
    public static int RESPONSE_STATUS_CODE_401 = 401;
    public static int RESPONSE_STATUS_CODE_201 = 201;


    public static Response ApiGet(String url) {
        Response resp = null;
        try {
            RequestSpecification rs = given().contentType("").body("");
            rs = rs.given().headers("", "");

            resp = rs.when().get(url).then().extract().response();

        } catch (Exception e) {
            org.testng.Assert.fail("*******************************ApiGet: " + e.getMessage());
        }
        return resp;
    }

    public static Response ApiGetQueryParam(String url) {
        Response resp = null;
        try {
            RequestSpecification rs = given().contentType("").body("");
            rs = rs.given().headers("", "");

            resp = rs.when().get(url).then().extract().response();

        } catch (Exception e) {
            org.testng.Assert.fail("*******************************ApiGet: " + e.getMessage());
        }
        return resp;
    }

    public Response ApiPost(String url) {
        Response resp = null;
        try {
            RequestSpecification rs = given().contentType("").body("");
            rs = rs.given().headers("", "");
            resp = rs.when().post(url).then().extract().response();

        } catch (Exception e) {
            org.testng.Assert.fail("*******************************ApiPost: " + e.getMessage());
        }

        return resp;
    }

    public Response ApiPut(String url) {
        Response resp = null;
        try {
            RequestSpecification rs = given().contentType("").body("");
            rs = rs.given().headers("", "");
            resp = rs.when().put(url).then().extract().response();

        } catch (Exception e) {
            org.testng.Assert.fail("*******************************ApiPut: " + e.getMessage());
        }
        return resp;
    }

    public Response apiDelete(String url) {
        Response resp = null;
        try {
            RequestSpecification rs = given().contentType("").body("");
            rs = rs.given().headers("","");
            resp = rs.when().delete(url).then().extract().response();

        } catch (Exception e) {
            org.testng.Assert.fail("*******************************ApiDelete: " + e.getMessage());
        }
        return resp;
    }


    public static void AssertHeaders(Headers Header, String Expected) {
        if (Header.toString().contains((Expected))) {
            test.log(Status.PASS, "Header:: " + Expected + " Test Passed");
        } else {
            test.log(Status.FAIL, "Header:: " + Expected + " Test Failed");
        }
    }

    public static void AssertCookies(Map<String, String> cookies, String Expected){
        if (cookies.toString().contains((Expected))) {
            test.log(Status.PASS, "Cookies:: " + Expected + " Test Passed");
        } else {
            test.log(Status.FAIL, "Cookies:: " + Expected + " Test Failed");
        }
    }

    public static void verifyLink(String urlLink) throws IOException {
        URL link = new URL(urlLink);
        HttpURLConnection httpConn = (HttpURLConnection) link.openConnection();
        httpConn.setConnectTimeout(2000);
        httpConn.connect();
        if (httpConn.getResponseCode() == RESPONSE_STATUS_CODE_200) {
            test.log(Status.PASS, urlLink + ":::: is a Valid link ::::" + httpConn.getResponseMessage());
        } else {
            test.log(Status.FAIL, urlLink + ":::: is not a valid link ::::" + httpConn.getResponseMessage());
        }
    }

    public static void AssertStatusCode(int ActualStatusCode, int ExpectedStatusCode) throws IOException, InterruptedException {
        try {
            assertEquals(ActualStatusCode, ExpectedStatusCode);
            test.log(Status.PASS, "Status Code Test Passed");
        } catch (Throwable e) {
            System.out.println(ActualStatusCode);
            test.log(Status.FAIL, "Status Code Test Failed");
        }
    }

    public static void AssertStatusLine(String ActualStatusLine){
        try {
            assertEquals(ActualStatusLine, "HTTP/1.1 200 OK");
            test.log(Status.PASS, "Status Line Check Passed");
        } catch (Throwable e) {
            System.out.println(ActualStatusLine);
            test.log(Status.FAIL, "Status Line Check Failed");
        }
    }

    public static void AssertResponseBody(String jsonString, String Path, String Expected){
        try {
            assertThat(JsonPath.from(String.valueOf(jsonString)).get(Path), equalTo(Expected));
            test.log(Status.PASS, "Response Body Check Passed");
        } catch (Throwable e) {
            test.log(Status.FAIL, "Response Body Check Failed");
        }
    }
}
