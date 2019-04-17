import javax.print.DocFlavor;

public class Agency implements Comparable<Agency> {

    private Address address;
    private String correspondent_id;
    private String description;
    private boolean  disabled;
    private String distance;
    private String id;
    private String payment_method_id;
    private String phone;
    private String side_id;
    private boolean terminal;
    private String agency_code;

    public static OrderAgencies orderAgencies;

    public Agency(Address address, String correspondent_id, String description, boolean disabled,
                  String distance, String id, String payment_method_id, String phone, String side_id, boolean terminal, String agency_code) {
        this.address = address;
        this.correspondent_id = correspondent_id;
        this.description = description;
        this.disabled = disabled;
        this.distance = distance;
        this.id = id;
        this.payment_method_id = payment_method_id;
        this.phone = phone;
        this.side_id = side_id;
        this.terminal = terminal;
        this.agency_code = agency_code;
    }

    public String getAgency_code() {
        return agency_code;
    }

    public void setAgency_code(String agency_code) {
        this.agency_code = agency_code;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public int compareTo(Agency a) {
        switch (orderAgencies){
            case AGENCY_CODE:
                int x1 = Integer.parseInt(this.getAgency_code());
                int x2 = Integer.parseInt(a.getAgency_code());
                return x1 - x2;

            case ADDRESS_LINE:
                return this.getAddress().getAddress_line().compareTo(a.getAddress().getAddress_line());

            default:
                if(this.getDistance()!=null && a.getDistance()!=null){
                    return this.getDistance().compareTo(a.getDistance());
                }
                return 0;
        }
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getCorrespondent_id() {
        return correspondent_id;
    }

    public void setCorrespondent_id(String correspondent_id) {
        this.correspondent_id = correspondent_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPayment_method_id() {
        return payment_method_id;
    }

    public void setPayment_method_id(String payment_method_id) {
        this.payment_method_id = payment_method_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSide_id() {
        return side_id;
    }

    public void setSide_id(String side_id) {
        this.side_id = side_id;
    }

    public boolean isTerminal() {
        return terminal;
    }

    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
    }
}
