package org.modsen.common.dao.hibernate;

import org.modsen.common.dao.pojo.Event;
import org.modsen.common.dao.pojo.Party;
import org.modsen.common.services.sorting.SortingParameter;

import java.util.List;
import java.util.Optional;

public interface PartyDAO {
    Optional<Party> findPartyById(long id);
    List<Party> findAllParties(List<SortingParameter> sortingParameters);
    void saveParty(Party party);
    void removeParty(Party party);
}
