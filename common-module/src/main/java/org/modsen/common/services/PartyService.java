package org.modsen.common.services;

import org.modsen.common.dao.pojo.Event;
import org.modsen.common.dao.pojo.Party;
import org.modsen.common.services.sorting.enums.SortingMethod;

import java.util.List;

public interface PartyService {
    List<Party> findAllParties(SortingMethod... sortingParameters);
    Party findPartyById(long id);
    void registerParty(Party party);
    void changeParty(Party party);
    void deleteParty(Party party);
}
