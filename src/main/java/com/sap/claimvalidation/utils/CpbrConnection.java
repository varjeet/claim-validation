package com.sap.claimvalidation.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.sap.claimvalidation.entities.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;


public class CpbrConnection {

    private static final Logger log = LoggerFactory.getLogger(CpbrConnection.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        SpringApplication.run(CpbrConnection.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    public List<ResultDetail> setValidationRule(Validation validation) throws JsonProcessingException {

        List<ResultDetail> results= triggerRules(validation);
           return results;
    }






    public List<ResultDetail> trigerRules(Validation validation) throws JsonProcessingException {
        Claim claim= validation.getVocabulary();
        Item item= claim.getItem();
        Result result=new Result();
        Version version=claim.getVersion();
        Header header=claim.getHeader();
        RestTemplate  restTemplate=new RestTemplate();
        List<ResultDetail> claimRes=new ArrayList<>();


        try {
            String accessToken=getAccessToken(restTemplate);
            String url = "https://bpmruleruntime.cfapps.eu10.hana.ondemand.com/rules-service/rest/v2/workingset-rule-services";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(accessToken);
            HttpEntity<String> request =
                new HttpEntity<String>(validation.toString(), headers);
              String claimRes1 =
                restTemplate.postForObject(url, request, String.class);
              JsonNode root = objectMapper.readTree(claimRes1);

            for(int i=0;i<root.size();i++)
            {
                ResultDetail resultDetail=new ResultDetail();
                resultDetail.setSeverity(root.get(0).findValuesAsText("severity").get(0));
                resultDetail.setMessageClassId(root.get(0).findValuesAsText("messageClassId").get(0));
                resultDetail.setMessage(root.get(0).findValuesAsText("message").get(0));
                claimRes.add(resultDetail);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return claimRes;

    }

    private String getAccessToken(RestTemplate restTemplate) throws UnsupportedEncodingException, JsonProcessingException {
        String clientId = "sb-clone-4e065cbb-418b-43d3-896e-65c4d800bb96!b73718|bpmrulebroker!b2466";
        String clientSecret = "b73887cd-5ceb-4af1-b212-be6affdefb66$7jnt_xFQN0vcNjvkktg4Lvdm7tgHZ1IWfuHt1vaKVVc=";
        String url = "https://0aaea7ddtrial.authentication.eu10.hana.ondemand.com/oauth/token?grant_type=client_credentials";
        String combinedCredentials = clientId + ":" + clientSecret;
        String encodedCredentials = Base64.getEncoder().encodeToString(combinedCredentials.getBytes("utf-8"));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(encodedCredentials);

        HttpEntity<String> request =
                new HttpEntity<String>(null, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        JsonNode root = objectMapper.readTree(responseEntity.getBody());
        log.info(responseEntity.getBody());
        String accessToken = root.path("access_token").asText();
        log.info(accessToken);
        return accessToken;
    }








































































    public List<ResultDetail> triggerRules(Validation validation) throws JsonProcessingException {
        Claim claim= validation.getVocabulary();
        Item item= claim.getItem();
        Result result=new Result();
        Version version=claim.getVersion();
        Header header=claim.getHeader();

        String uri="http://localhost:8281/bpmruleruntime.cfapps.eu10.hana.ondemand.com/rules-service/rest/v2/workingset-rule-services";
        RestTemplate  restTemplate=new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject claimObject = new JSONObject();
        claimObject.put("claimId",claim.getId());
        claimObject.put("versionId",claim.getVersion_id());
        claimObject.put("itemId",claim.getItem_id());
        claimObject.put("headerId",claim.getHeader_id());
        claimObject.put("itemNumber",item.getItem_number());
        claimObject.put("material",item.getMaterial());
        claimObject.put("partCausingDamage",item.getPartCausingDamage());
        claimObject.put("type",item.getType());
        claimObject.put("amount",item.getAmount());
        claimObject.put("headerId",item.getVersion());
        claimObject.put("purchasingOrganization",version.getPurchasingOrganization());
        claimObject.put("salesOrganization",version.getSalesOrganization());
        claimObject.put("distributionChannel",version.getDistributionChannel());
        claimObject.put("versionCategory",version.getVersionCategory());
        claimObject.put("personResponsible",version.getPersonResponsible());
        claimObject.put("registartionDate",header.getRegistartion_date());
        claimObject.put("damageDate",header.getDamage_date());
        claimObject.put("repairStartDate",header.getRepair_start_Date());
        claimObject.put("repairEndDate",header.getRepair_end_date());
        claimObject.put("customer",header.getCustomer());
        claimObject.put("objectType",header.getObject_type());
        claimObject.put("objectNumber",header.getObject_number());
        claimObject.put("source",header.getSource());
        claimObject.put("sourceObjectNumber",header.getObject_number());
        HttpEntity<String> request =
                new HttpEntity<String>(claimObject.toString(), headers);

        String claimRes1 =
                restTemplate.postForObject(uri, request, String.class);
        JsonNode root = objectMapper.readTree(claimRes1);
        List<ResultDetail> claimRes =new ArrayList<>();
        for(int i=0;i<root.size();i++)
        {

         ResultDetail resultDetail=new ResultDetail();
         resultDetail.setSeverity(root.get(i).findValuesAsText("severity").get(0));
         resultDetail.setMessageClassId(root.get(i).findValuesAsText("messageClassId").get(0));
         resultDetail.setMessage(root.get(i).findValuesAsText("message").get(0));
         claimRes.add(resultDetail);
        }

        return claimRes;
    }





}
