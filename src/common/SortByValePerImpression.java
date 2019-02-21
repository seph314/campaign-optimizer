package common;

import java.util.Comparator;

public class SortByValePerImpression implements Comparator<CustomerCampaignDTO>{

    @Override
    public int compare(CustomerCampaignDTO customerDetail1, CustomerCampaignDTO customerDetail2) {
        return customerDetail1.compareTo(customerDetail2);
    }
}
