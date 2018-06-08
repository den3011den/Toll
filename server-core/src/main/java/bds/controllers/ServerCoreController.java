package bds.controllers;

import java.util.concurrent.atomic.AtomicLong;
import bds.dto.PointDTO;
import org.codehaus.groovy.antlr.treewalker.SourcePrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class ServerCoreController {

    @RequestMapping(value = "/coords", method = RequestMethod.GET)
    public void getCoords(){
        //Response response;
        //response = new Response("ok", true);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
        //return response;
    }

  }
