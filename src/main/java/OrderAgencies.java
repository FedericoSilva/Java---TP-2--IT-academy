public enum OrderAgencies {

    ADDRESS_LINE("ADDRESS_LINE"),
    AGENCY_CODE("AGENCY_CODE"),
    DISTANCE("DISTANCE");

    private String order;

    OrderAgencies(String order) {
        this.order = order;
    }
}
