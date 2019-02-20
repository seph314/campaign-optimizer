package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class CampaignOptimizer {

    private List<String> result;

    public List<String> optimize(List<String> fileContent){

        // listOfCompanyDetails will contain all companies in the form of list of strings
        List<List<String>> listOfCompanyDetails = new ArrayList<>();
        List<CustomerDetails> listOfCustomerDetails = new ArrayList<>();

        for (String company : fileContent){
            System.out.println(company);
            String[] companyArray = company.split("\\s*,\\s*");
            List<String> companyDetails = Arrays.stream(companyArray).collect(Collectors.toList());
            listOfCompanyDetails.add(companyDetails);
        }

        System.out.println(listOfCompanyDetails);


//        Iterator<List<String>> iterator = listOfCompanyDetails.iterator();
//        while(iterator.hasNext()){
//            List<String> details = iterator.next();
//            if (details.size() == 3){
//                CustomerDetails customerDetails = new CustomerDetails();
//                customerDetails.setCustomerName(details.get(0));
//                customerDetails.setImpressionsPerCampaign(Integer.valueOf(details.get(1)));
//                customerDetails.setPricePerCampaign(Integer.valueOf(details.get(2)));
//                customerDetails.calculateValuePerImpression();
//                listOfCustomerDetails.add(customerDetails);
//            }
//        }

        // convert to a list of CustomerDetail objects
        for (List<String> companyDetails : listOfCompanyDetails){
            if (companyDetails.size() == 3){
                CustomerDetails customerDetails = new CustomerDetails();
                customerDetails.setCustomerName(companyDetails.get(0));
                customerDetails.setImpressionsPerCampaign(Integer.valueOf(companyDetails.get(1)));
                customerDetails.setPricePerCampaign(Integer.valueOf(companyDetails.get(2)));
                customerDetails.calculateValuePerImpression();
                listOfCustomerDetails.add(customerDetails);
            }
        }

        System.out.println(listOfCustomerDetails.size() + String.valueOf(listOfCustomerDetails.get(0).getCustomerName()));

        // add value per impression for each company
        for (List<String> companyDetails : listOfCompanyDetails){
            // skip the first one containing total number of impressions
            if (companyDetails.size() == 3){
                double valuePerImpression = Double.parseDouble(companyDetails.get(2))/Double.parseDouble(companyDetails.get(1));
                companyDetails.add(String.valueOf(valuePerImpression));
            }
        }

        System.out.println(listOfCompanyDetails);



        return result;
    }

}
