package model;

import common.CustomerCampaignDTO;
import common.SortByValePerImpression;

import java.util.*;
import java.util.stream.Collectors;


public class CampaignOptimizer {

    private int maxImpressions;

    public List<CustomerCampaignDTO> optimize(List<String> fileContent) {

        // listOfCompanyDetails will contain all companies in the form of list of strings
        List<List<String>> listOfCompanyDetails = new ArrayList<>();
        List<CustomerCampaignDTO> listOfCustomerDetails = new ArrayList<>();

        for (String company : fileContent) {
//            System.out.println(company);
            String[] companyArray = company.split("\\s*,\\s*");
            List<String> companyDetails = Arrays.stream(companyArray).collect(Collectors.toList());
            listOfCompanyDetails.add(companyDetails);
        }

//        System.out.println(listOfCompanyDetails);

        //set maxImpressions
        maxImpressions = Integer.valueOf(listOfCompanyDetails.get(0).get(0));
//        System.out.println("max impressions " + maxImpressions);

        // convert to a list of CustomerDetail objects
        for (List<String> companyDetails : listOfCompanyDetails) {
            if (companyDetails.size() == 3) {
                CustomerCampaignDTO customerDetails = new CustomerCampaignDTO();
                customerDetails.setCustomerName(companyDetails.get(0));
                customerDetails.setImpressionsPerCampaign(Integer.valueOf(companyDetails.get(1)));
                customerDetails.setPricePerCampaign(Integer.valueOf(companyDetails.get(2)));
                customerDetails.setValuePerImpression();
                listOfCustomerDetails.add(customerDetails);
            }
        }


//        for (CustomerCampaignDTO customerDetails : listOfCustomerDetails){
//            System.out.println(customerDetails.getCustomerName() + " " + customerDetails.getValuePerImpression());
//        }

        // sort the list by most valuable customer per impression
        listOfCustomerDetails.sort(new SortByValePerImpression());

//        System.out.println("\n");
//        for (CustomerCampaignDTO customerDetails : listOfCustomerDetails){
//            System.out.println(customerDetails.getCustomerName() + " " + customerDetails.getValuePerImpression());
//        }

        maximizeProfit(listOfCustomerDetails);

        return listOfCustomerDetails;
    }

    private void maximizeProfit(List<CustomerCampaignDTO> listOfCustomerDetails) {

        for (CustomerCampaignDTO customerDetails : listOfCustomerDetails){
            int numberOfCampaigns = maxImpressions / customerDetails.getImpressionsPerCampaign();
//            System.out.println(numberOfCampaigns);
            int totalImpressionsForCustomer = numberOfCampaigns * customerDetails.getImpressionsPerCampaign();
            customerDetails.setNumberOfCampaigns(numberOfCampaigns);
            customerDetails.setTotalImpressionForCustomer(totalImpressionsForCustomer);
            customerDetails.setTotalRevinueForCustomer(numberOfCampaigns * customerDetails.getPricePerCampaign());

            //remove used impressions from total
            maxImpressions = maxImpressions - totalImpressionsForCustomer;
        }
    }

}
