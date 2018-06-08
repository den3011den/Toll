package bds.controllers;

import java.util.concurrent.atomic.AtomicLong;
import bds.dto.PointDTO;
import org.codehaus.groovy.antlr.treewalker.SourcePrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class ServerCoreController {

    @RequestMapping(value = "/server-core/send-coords", method = RequestMethod.POST)
    @ResponseBody
    public PointDTO getCoords(@RequestBody PointDTO pointDTO) {
        System.out.println("Получили: " + pointDTO.toString());
        return pointDTO;
    }
 }
