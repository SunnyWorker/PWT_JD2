package org.modsen.web.controllers;

import org.modsen.common.dao.pojo.Event;
import org.modsen.common.dao.pojo.Party;
import org.modsen.common.services.PartyService;
import org.modsen.common.services.sorting.enums.SortingMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parties")
public class PartyController {

    private PartyService partyService;

    @Autowired
    public PartyController(PartyService partyService) {
        this.partyService = partyService;
    }

    @GetMapping("/getAll")
    public List<Party> findAllParties(@RequestParam(required = false) SortingMethod sortedByTheme,
                                      @RequestParam(required = false) SortingMethod sortedByOrganizer,
                                      @RequestParam(required = false) SortingMethod sortedByTime)
    {
        return partyService.findAllParties(
                sortedByTheme,
                sortedByOrganizer,
                sortedByTime
        );
    }

    @GetMapping("/get")
    public Party findPartyById(@RequestParam long id) {
        return partyService.findPartyById(id);
    }

    @PostMapping("/add")
    public void registerParty(@RequestBody Party party) {
        partyService.registerParty(party);
    }

    @PatchMapping("/change")
    public void changeParty(@RequestBody Party party) {
        partyService.changeParty(party);
    }

    @DeleteMapping("/delete")
    public void deleteParty(@RequestBody Party party) {
        partyService.deleteParty(party);
    }

}
