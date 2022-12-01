package org.modsen.common.services.implementation;

import org.modsen.common.dao.pojo.Event;
import org.modsen.common.dao.hibernate.PartyDAO;
import org.modsen.common.dao.pojo.Party;
import org.modsen.common.exceptions.DuplicateEntityException;
import org.modsen.common.exceptions.NoSuchEntityFoundException;
import org.modsen.common.services.sorting.SortingParameter;
import org.modsen.common.services.sorting.enums.SortingMethod;
import org.modsen.common.services.PartyService;

import java.util.Arrays;
import java.util.List;

public class PartyServiceImplementation implements PartyService {

    private PartyDAO partyDAO;

    public PartyServiceImplementation(PartyDAO partyDAO) {
        this.partyDAO = partyDAO;
    }


    @Override
    public List<Party> findAllParties(SortingMethod[] sortingMethods)
    {
        List<SortingParameter> sortingParameterList = Arrays.stream(sortingMethods)
                .map(sortingMethod -> new SortingParameter(sortingMethod))
                .toList();

        addCustomSort(sortingParameterList);

        List<SortingParameter> actualSortingParameters = sortingParameterList.stream()
                .filter(sortingParameter -> sortingParameter.getSortingMethod()!=null)
                .toList();

        return partyDAO.findAllParties(actualSortingParameters);
    }

    protected void addCustomSort(List<SortingParameter> sortingParameterList) {
        addJoinEntityNames(sortingParameterList.get(0),"event");
        addSortFields(sortingParameterList.get(0),"theme");
        addJoinEntityNames(sortingParameterList.get(1),"organizer");
        addSortFields(sortingParameterList.get(1),"lastName","firstName");
        addSortFields(sortingParameterList.get(2),"time");
    }

    protected void addSortFields(SortingParameter sortingParameter, String...sortFields) {
        if(sortingParameter==null) return;
        sortingParameter.setSortFields(sortFields);
    }

    protected void addJoinEntityNames(SortingParameter sortingParameter, String...joinEntityNames) {
        if(sortingParameter==null) return;
        sortingParameter.setJoinEntityNames(joinEntityNames);
    }

    @Override
    public Party findPartyById(long id) {
        return partyDAO.findPartyById(id).orElseThrow(()->new NoSuchEntityFoundException("Party with id "+id+" not found!"));
    }

    @Override
    public void registerParty(Party party) {
        if (party.getId()!=null && partyDAO.findPartyById(party.getId()).isPresent())
            throw new DuplicateEntityException("Party with id "+party.getId()+"already exists!");
        else partyDAO.saveParty(party);
    }

    @Override
    public void changeParty(Party party) {
        if(party.getId()!=null && findPartyById(party.getId())!=null)
            partyDAO.saveParty(party);
    }

    @Override
    public void deleteParty(Party party) {
        partyDAO.removeParty(party);
    }
}
