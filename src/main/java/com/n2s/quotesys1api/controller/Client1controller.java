package com.n2s.quotesys1api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Client1controller {
// to make the calls we need the loadbalnacer
    @Autowired
    private LoadBalancerClient loadBalancerClient;


    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/callclientone")
public ResponseEntity<String> callfirstclient() throws Exception{

    String clientresponsestring = " Response from client one ";

    return new ResponseEntity<String>(clientresponsestring, HttpStatus.OK);
}
    // inorder to get the base url of the particular client we need to write a function
@GetMapping("/callclienttwothroughclientone")
    public ResponseEntity<String> callsecondclient() throws Exception{

        try{

            return new ResponseEntity<>( restTemplate.getForObject(getBaseUrl()+"/callclienttwo", String.class), HttpStatus.OK);

        }
        catch(Exception exception){

            return new ResponseEntity<>( restTemplate.getForObject(getBaseUrl()+"/callclienttwo", String.class), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    private String getBaseUrl(){
        //service id is used to get the instance from the eureka server
        //It would return localhost:8080
        ServiceInstance instance = loadBalancerClient.choose("client2");
        return instance.getUri().toString();
    }

}
