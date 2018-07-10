package bds.services;

import bds.dto.PointDTO;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;


/*

Тест класса:
/tracker-core/src/main/java/bds/services/SavingMessagesService

*/

public class SavingMessagesServiceTest {

    @Test
    public void takePointDTOFromQueue() throws IOException, InterruptedException {
        putPointDTOIntoQueue();
    }

    @Test
    public void putPointDTOIntoQueue() throws IOException, InterruptedException {
        SavingMessagesService savingMessagesService = new SavingMessagesService();
        PointDTO pointDTO = new PointDTO(20.1, 30.1, "tt333t70", 333L);
        savingMessagesService.putPointDTOIntoQueue(pointDTO);
        String takeString = savingMessagesService.takePointDTOFromQueue();
        assertEquals(pointDTO.toString(), takeString);
    }
}