package bds.controllers;

import bds.dto.PointDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@RestController
@PropertySource("classpath:/servercore.properties")
public class ServerCoreController {

    @Value("${servercore.receivedDataFilePath.prop}")
    private String receivedDataFilePath;

    // Лoг
    private static final Logger LOG = LoggerFactory.getLogger(ServerCoreController.class);

    // Запись в файл строки
    private boolean writeToFile(String filePath, String writeString) {

        try {
            FileWriter writer = new FileWriter(filePath, true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(writeString);
            bufferWriter.close();
            return true;
        }
            catch (IOException e) {
                LOG.info(e.toString());
                return false;
    }
}

    @RequestMapping(value = "/coords",  method = RequestMethod.POST)
    public String coords(@RequestBody PointDTO pointDTO) {

        LOG.info("got object: " + pointDTO.toString());

        writeToFile(receivedDataFilePath, pointDTO.toString() + "\n");

        String response = "{success:\"true\"}";
        return response;
    }

  }
