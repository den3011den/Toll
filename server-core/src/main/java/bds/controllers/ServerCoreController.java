package bds.controllers;

import bds.dto.PointDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ServerCoreController {

    private final RestTemplate restTemplate;

    public ServerCoreController(@Autowired RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


//    @RequestMapping("/coords")
//    public PointDTO coords(){
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11");
//        PointDTO pointDTO = restTemplate.getForObject(
//                "http://localhost/coord", PointDTO.class);
//        return pointDTO;
//    }

    @RequestMapping(value = "/coords",  method = RequestMethod.POST)
    public String coords(@RequestBody PointDTO pointDTO) {
        //PointDTO pointDTO = restTemplate.getForObject("http://localhost/coords", PointDTO.class);
        //pointDTO.toString();
        System.out.println(pointDTO);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
        String response = "{success:\"true\"}";
        return response;
    }

//    @PostMapping(path = "/coords", consumes = "application/json", produces = "application/json")
//    public String addMember(@RequestBody PointDTO pointDTO) {
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
//        String response = "{success:\"true\"}";
//        pointDTO.toString();
//        return response;
//    }

//    @RequestMapping(value = "/coords", method = RequestMethod.GET)
//    @PostMapping
//    public Response setCoords(@RequestParam(value="location") String location){
//        System.out.println(location);
//        Response response;
//        if (location.split(",").length == 2) {
//            response = new Response("ok", true);
//        } else {
//            response = new Response("fail", false);
//        }
//
//        return response;
//    }
  }
