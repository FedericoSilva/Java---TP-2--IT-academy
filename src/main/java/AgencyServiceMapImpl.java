import java.util.Collection;
import java.util.HashMap;

public class AgencyServiceMapImpl implements AgencyService {

    private HashMap<String, Agency> agenciesMap;

    public AgencyServiceMapImpl() {
        agenciesMap = new HashMap<>();
    }

    public Collection<Agency> getAgencies(){
        return agenciesMap!=null ? agenciesMap.values() : null;
    }

    @Override
    public void setAgenciesMap(Agency[] agencies) {

        for (Agency agency: agencies
        ) {
            agenciesMap.put(agency.getId(), agency);
        }
    }


    public Collection<Agency> setOder(int order){
        return null;
    }


}
