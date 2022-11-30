package org.modsen.eventworker.enums;

public enum SortingParameter {
    asc,desc;
    private String[] sortFields;
    private String[] joinEntityNames;

    public String[] getSortFields() {
        return sortFields;
    }

    public void setSortFields(String... sortFields) {
        this.sortFields = sortFields;
    }

    public String[] getJoinEntityNames() {
        return joinEntityNames;
    }

    public void setJoinEntityNames(String... joinEntitiesName) {
        this.joinEntityNames = joinEntitiesName;
    }
}
