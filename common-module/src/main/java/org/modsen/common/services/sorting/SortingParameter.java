package org.modsen.common.services.sorting;

import lombok.Getter;
import lombok.Setter;
import org.modsen.common.services.sorting.enums.SortingMethod;

@Getter
@Setter
public class SortingParameter {
    private String[] sortFields;
    private String[] joinEntityNames;
    private SortingMethod sortingMethod;

    public SortingParameter(SortingMethod sortingMethod) {
        this.sortingMethod = sortingMethod;
    }
}
