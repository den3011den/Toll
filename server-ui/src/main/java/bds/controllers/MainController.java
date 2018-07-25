package bds.controllers;

import bds.AddTrackForm;
import bds.AskTrackForm;
import bds.dto.RequestAddingPoint;
import bds.dto.RequestAutoIDTrack;
import bds.dto.ResponseAutoIDTrack;
import bds.services.SendingRequestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MainController {

    @RequestMapping(value = "/askTrack", method = RequestMethod.GET)
    public String viewAskTrackPage(Model model) {

        AskTrackForm form = new AskTrackForm("Ж777ЖД70", 5);

        model.addAttribute("askTrackForm", form);

        return "askTrackPage";
    }

    @RequestMapping(value = "/askTrack", method = RequestMethod.POST)
    public String processAskTrackPage(Model model, AskTrackForm askTrackForm) {

        try {

            SendingRequestService sendingRequestService = new SendingRequestService();
            RequestAutoIDTrack requestAutoIDTrack = new RequestAutoIDTrack(askTrackForm.getAutoId(),askTrackForm.getLastPointsQuantity());
            String gotAnswer = sendingRequestService.sendTrackRequestToServer(requestAutoIDTrack);
            ObjectMapper mapper = new ObjectMapper();
            ResponseAutoIDTrack responseAutoIDTrack = mapper.readValue(gotAnswer,  ResponseAutoIDTrack.class);
            model.addAttribute("responseAutoIDTrack",responseAutoIDTrack);

        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "/askTrackPage";
        }
        return "/showTrackPage";
    }


    @RequestMapping(value = "/addTrackPage", method = RequestMethod.GET)
    public String viewAddTrackPage(Model model) {

        AddTrackForm form = new AddTrackForm("Ж777ЖД70", 11, 22, 888888888);

        model.addAttribute("addTrackForm", form);

        return "addTrackPage";
    }

    @RequestMapping(value = "/addTrack", method = RequestMethod.POST)
    public String processAddPoint(Model model, AddTrackForm addTrackForm) {

        try {

            SendingRequestService sendingRequestService = new SendingRequestService();
            RequestAddingPoint requestAddingPoint = new RequestAddingPoint(addTrackForm.getAutoId(), addTrackForm.getLat(), addTrackForm.getLon(), addTrackForm.getTime());
            String gotAnswer = sendingRequestService.sendAddPointRequestToServer(requestAddingPoint);
            ObjectMapper mapper = new ObjectMapper();
            RequestAutoIDTrack requestAutoIDTrack = mapper.readValue(gotAnswer,  RequestAutoIDTrack.class);
            AskTrackForm askTrackForm = new AskTrackForm(requestAutoIDTrack.getAutoId(), requestAutoIDTrack.getLastPointsQuantity());
            processAskTrackPage(model, askTrackForm);

        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "/askTrackPage";
        }
        return "/showTrackPage";
    }

}
