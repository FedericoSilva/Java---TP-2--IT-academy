import java.util.Collection;

public interface AgencyService {


    public Collection<Agency> setOder(int order);
    public Collection<Agency> getAgencies();
    public void setAgenciesMap(Agency[] agencies);
}
