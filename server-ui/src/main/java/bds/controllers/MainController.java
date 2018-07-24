package bds.controllers;

import bds.AskTrackForm;
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
    public String processSendMoney(Model model, AskTrackForm askTrackForm) {

        try {

            SendingRequestService sendingRequestService = new SendingRequestService();
            RequestAutoIDTrack requestAutoIDTrack = new RequestAutoIDTrack(askTrackForm.getAutoId(),askTrackForm.getLastPointsQuantity());
            String gotAnswer = sendingRequestService.sendRequestToServer(requestAutoIDTrack);
            ObjectMapper mapper = new ObjectMapper();
            ResponseAutoIDTrack responseAutoIDTrack = mapper.readValue(gotAnswer,  ResponseAutoIDTrack.class);
            model.addAttribute("responseAutoIDTrack",responseAutoIDTrack);

        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "/askTrackPage";
        }
        return "/showTrackPage";
    }

}
