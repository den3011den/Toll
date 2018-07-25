package bds.controllers;

import bds.dao.RoleRecord;
import bds.dao.TrackPoint;
import bds.dao.UserRecord;
import bds.dao.repo.RoleRepository;
import bds.dao.repo.TrackRepository;
import bds.dao.repo.UserRepository;
import bds.dto.PointDTO;
import bds.dto.RequestAddingPoint;
import bds.dto.RequestAutoIDTrack;
import bds.dto.ResponseAutoIDTrack;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;

@RestController
@PropertySource("classpath:/servercore.properties")
@EnableJpaRepositories("bds.dao")
@EntityScan(basePackageClasses = {bds.dao.TrackPoint.class, bds.dao.RoleRecord.class, bds.dao.UserRecord.class})
public class ServerCoreController {


    private static final Logger log = LoggerFactory.getLogger(ServerCoreController.class);
    static private List<TrackPoint> allTracks;
    static private List<UserRecord> allUsers;
    static private List<RoleRecord> allRoles;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    TrackRepository trackRepository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    RoleRepository roleRepository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    UserRepository userRepository;


    public void deleteTrackPoint(TrackPoint trackPoint) {
        trackRepository.delete(trackPoint);
    }

    public void updateTrackPoint(TrackPoint trackPoint, PointDTO pointDTO) {
        trackPoint.setFromPointDTO(pointDTO);
        trackRepository.save(trackPoint);
    }

    public boolean readTrackPointAll() {
        allTracks = (List<TrackPoint>) trackRepository.findAll();

        if (allTracks.size() == 0) {
            log.info("NO RECORDS");
            return false;
        }

        allTracks.stream().forEach(trackPoint -> log.info(trackPoint.toString()));
        return true;
    }


    public TrackPoint createTrackPoint(PointDTO pointDTO) {
        TrackPoint trackPoint = new TrackPoint();
        trackPoint.setFromPointDTO(pointDTO);
        return trackRepository.save(trackPoint);
    }


    public void deleteUserRecord(UserRecord userRecord) {
        userRepository.delete(userRecord);
    }

    public void updateUserRecord(UserRecord userRecord) {
        userRepository.save(userRecord);
    }

    public void readUserRecordAll() {
        allUsers = (List<UserRecord>) userRepository.findAll();

        if (allUsers.size() == 0) {
            log.info("NO RECORDS");
        }

        allTracks.stream().forEach(userrecord -> log.info(userrecord.toString()));
    }

    public UserRecord createUserRecord(UserRecord userRecord) {
         return userRepository.save(userRecord);
    }



    public void deleteRoleRecord(RoleRecord roleRecord) {
        roleRepository.delete(roleRecord);
    }

    public void updateRoleRecord(RoleRecord roleRecord) {
        roleRepository.save(roleRecord);
    }

    public void readRoleRecordAll() {
        allRoles = (List<RoleRecord>) roleRepository.findAll();

        if (allRoles.size() == 0) {
            log.info("NO RECORDS");
        }

        allRoles.stream().forEach(roleRecord -> log.info(roleRecord.toString()));
    }

    public RoleRecord createRoleRecord(RoleRecord roleRecord) {
        return roleRepository.save(roleRecord);
    }

    // путь к файлу и имя храгится в свойствах в ресурсах
    @Value("${servercore.receivedDataFilePath.prop}")
    private String receivedDataFilePath;

    // Лoг
    private static final Logger LOG = LoggerFactory.getLogger(ServerCoreController.class);

    // Запись в файл строки
    // filePath - путь и имя файла
    // writeString - записываемая в файл строка
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

    // слушаем адрес http://localhost:8080/coords, забираем объёкт и отвечаем {success:"true"}
    @RequestMapping(value = "/coords",  method = RequestMethod.POST)
    public String coords(@RequestBody PointDTO pointDTO, boolean testFlag) {

        LOG.info("got object: " + pointDTO.toString());

        if (testFlag != true) {
            writeToFile(receivedDataFilePath, pointDTO.toString() + "\n");
            createTrackPoint(pointDTO);
        }

        LOG.info("wrote object to : " + receivedDataFilePath);

        String response = "{success:\"true\"}";
        return response;
    }



    // слушаем адрес http://localhost:8080/addingtrackpoint, забираем объёкт, записываем в базу
    @RequestMapping(value = "/addingtrackpoint",  method = RequestMethod.POST)
    public String coordsByAutoid(@RequestBody RequestAddingPoint requestAddingPoint) throws ParseException, JsonProcessingException {

        LOG.info("/addingtrackpoint");
        LOG.info("got request: " + requestAddingPoint.toString());

        PointDTO pointDTO = new PointDTO(requestAddingPoint.getLat(), requestAddingPoint.getLon(), requestAddingPoint.getAutoId(), requestAddingPoint.getTime());

        createTrackPoint(pointDTO);

        RequestAutoIDTrack requestAutoIDTrack = new RequestAutoIDTrack(requestAddingPoint.getAutoId(), 10);

        String response = requestAutoIDTrack.toJson();

        LOG.info("response : " + response);

        return response;
    }



    // слушаем адрес http://localhost:8080/coordsbyautoid, забираем объёкт, отвечаем
    @RequestMapping(value = "/coordsbyautoid",  method = RequestMethod.POST)
    public String coordsByAutoid(@RequestBody RequestAutoIDTrack requestAutoIDTrack) throws ParseException, JsonProcessingException {

        LOG.info("/coordsbyautoid");
        LOG.info("got request: " + requestAutoIDTrack.toString());

        boolean rezvar = readTrackPointAll();

        ResponseAutoIDTrack responseAutoIDTrack = new ResponseAutoIDTrack();

        if (!rezvar) {
            responseAutoIDTrack.setAutoId(requestAutoIDTrack.getAutoId());
            responseAutoIDTrack.setLastPointsQuantity(requestAutoIDTrack.getLastPointsQuantity());
            responseAutoIDTrack.setSuccess("false");
            responseAutoIDTrack.setInfo("NO DATA IN DATABASE TABLE");
        }
        else {
            Collections.sort(allTracks, (TrackPoint a1, TrackPoint a2) -> a2.getId()-a1.getId());
            int countVar = 0;
            for( TrackPoint trackPoint : allTracks) {
                if (trackPoint.getAutoId().equalsIgnoreCase(requestAutoIDTrack.getAutoId())) {
                    countVar++;
                    responseAutoIDTrack.addPoint(trackPoint.toPointDTO());
                    if (countVar==requestAutoIDTrack.getLastPointsQuantity()) {
                        break;
                    }
                }
            }
            if (countVar == 0) {
                responseAutoIDTrack.setAutoId(requestAutoIDTrack.getAutoId());
                responseAutoIDTrack.setLastPointsQuantity(requestAutoIDTrack.getLastPointsQuantity());
                responseAutoIDTrack.setSuccess("false");
                responseAutoIDTrack.setInfo("NO DATA FIT TO CONDITIONS");
            }
            else {
                responseAutoIDTrack.setAutoId(requestAutoIDTrack.getAutoId());
                responseAutoIDTrack.setLastPointsQuantity(requestAutoIDTrack.getLastPointsQuantity());
                responseAutoIDTrack.setSuccess("true");
                responseAutoIDTrack.setInfo("response consists of " + countVar + "records");

            }

        }

        String response = responseAutoIDTrack.toJson();

        LOG.info("response : " + response);

        return response;
    }



}
