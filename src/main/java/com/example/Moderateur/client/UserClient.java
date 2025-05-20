//package com.example.Moderateur.client;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//
//
//
//@FeignClient(name = "user-service", url = "http://user-service:8080")
//public interface UserClient {
//    @PostMapping("/api/users/{id}/suspend/{motifId}")
//    void suspendUser(@PathVariable("id") Long id, @PathVariable("motifId") Long motifId);
//
//}
