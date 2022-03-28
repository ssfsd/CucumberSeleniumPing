package com.ping.steps;

import com.alibaba.fastjson.JSONObject;
import com.ping.LiveDemoTest;
import cucumber.api.java.After;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.extern.java.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.hasItems;
@Log
public class AssuredPractice {

    private static AssuredPractice instance=new AssuredPractice();

    public AssuredPractice(){
    }
    public static AssuredPractice getInstance(){
        return instance;
    }
    private   Response response;
    private  RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;

    public ResponseSpecification getResponseSpecification() {
        return responseSpecification;
    }

    public void setResponseSpecification(ResponseSpecification responseSpecification) {
        this.responseSpecification = responseSpecification;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }

    public void setRequestSpecification(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    public  Response post(String apiPath, String json) {
       response = given().contentType("application/json;charset=UTF-8").headers("headers", "headers").cookies("cookies", "cookiesvalue").body(json).when().log().all().post(apiPath.trim());
        response.getBody().prettyPrint();
        return response;
    }
    public  Response post(String apiPath)
    {
        response=given().contentType("application/json;charset=UTF-8")
                .headers("headers","headers").cookies("cookies","cookies")
                .when().log().all().get(apiPath.trim());
        log.info(String.valueOf(response.statusCode()));
        log.info("response:");
        response.getBody().prettyPrint();
        return response;
    }
    public  String getJsonPathValue(String jsonPath)
    {
        String responseJson= JsonPath.from(response.asString()).getString(jsonPath);
        return  responseJson;
    }
    public  void  find(String json,String findItemS)
    {
       response.then().body(json,hasItems(findItemS));
    }

    public  Response findByIdAndNameStep(int id,String name){
        response=given().log().all().param("id",id).param("name",name).when().get("/byparam");
        return response;
    }

    public  Map<String,String> stringToMap(String s)
    {
        Map<String,String> map=new HashMap<String,String>();
        JSONObject jsonObject=JSONObject.parseObject(s);
        for(Object k:jsonObject.keySet()){
            Object v=jsonObject.get(k);
            map.put(k.toString(),v.toString());
        }
        return map;
    }
    public  Map<String,Object> stringToMap2(String s)
    {
        Map<String,Object> map=new HashMap<String,Object>();
        JSONObject jsonObject=JSONObject.parseObject(s);
        for(Object k:jsonObject.keySet()){
            Object v=jsonObject.get(k);
            map.put(k.toString(),v);
        }
        return map;
    }
    public  RequestSpecification setUrlAndHeaders(String url,String headerList){
        Map<String,String> header=stringToMap(headerList);
        baseURI=url;
        RequestSpecBuilder builder=new RequestSpecBuilder();
        builder.addHeaders(header);
        requestSpecification=builder.build();
        return requestSpecification;
    }

    public  ResponseSpecification checkResult(int statusCode,String resHeader){
        Map<String,Object> header=stringToMap2(resHeader);
        ResponseSpecBuilder responseSpecBuilder=new ResponseSpecBuilder();
        responseSpecBuilder.expectStatusCode(statusCode);
        responseSpecBuilder.expectHeaders(header);
        responseSpecification =responseSpecBuilder.build();
        return responseSpecification;
    }


//    public static void main(String[] args){
//        String s="{\"accept-encoding\": \"gzip,deflate\",\"accept-language\":\"zh-CN\"}";
//        Map<String,Object> map= new HashMap<String,Object>();
//        JSONObject jsonObject=JSONObject.parseObject(s);
//        for(Object k:jsonObject.keySet()){
//            Object v=jsonObject.get(k);
//            map.put(k.toString(),v);
//        }
//        Map<String,String> mp=new HashMap<>();
//        mp.put("id","1");
//        mp.put("name","test");
//        given().params(mp).baseUri("http://localhost:8080").headers(map).log().all().get("/byparam");
//    }
//@After("@RestAssuredCase")
//    public void executeAfter() throws IOException {
//        log.info(".........................test");
//        LiveDemoTest liveDemoTest=new LiveDemoTest();
//        liveDemoTest.generateDemoReport();
//    log.info(".........................test after");
//    }
}
