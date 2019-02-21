package model;

import common.CustomerCampaignDTO;
import common.SortByValePerImpression;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Optimizes available Campaigns to maximize profit
 */
public class CampaignOptimizer {

    private int maxImpressions;

    /**
     * Takes the file content as a List of Strings, converts them to CustomerCampaignDTO objects that
     * we store in a new list listOfCustomerDetails. This list is then sorted by value per impression and then we
     * calculate how many of the most valuable campaigns that will fit in the maximum number of campaigns. When no more
     * fits we take the next most valuable and so on.
     *
     * @param fileContent is the content that we read from a file as a List of Strings
     * @return a sorted list of CustomerCampaignDTOs, based on most profitable
     */
    public List<CustomerCampaignDTO> optimize(List<String> fileContent) {

        // listOfCompanyDetails will contain all companies in the form of list of strings
        List<List<String>> listOfCompanyDetails = new ArrayList<>();
        List<CustomerCampaignDTO> listOfCustomerDetails = new ArrayList<>();

        /* We want to be able to access each part of the details, so we do a comma separated split for each company
        and the build a List of List of Strings */
        for (String company : fileContent) {
//            System.out.println(company);
            String[] companyArray = company.split("\\s*,\\s*");
            List<String> companyDetails = Arrays.stream(companyArray).collect(Collectors.toList());
            listOfCompanyDetails.add(companyDetails);
        }

//        System.out.println(listOfCompanyDetails);

        /* now we can set maxImpressions, because that is now the first element in the first element in the lists */
        maxImpressions = Integer.valueOf(listOfCompanyDetails.get(0).get(0));

        /* To make comparison between the different companies campaigns easier we use a DTO to store all data
        * This DTO is also used to send the results to the view later on */
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
            int totalImpressionsForCustomer = numberOfCampaigns * customerDetails.getImpressionsPerCampaign();
            customerDetails.setNumberOfCampaigns(numberOfCampaigns);
            customerDetails.setTotalImpressionForCustomer(totalImpressionsForCustomer);
            customerDetails.setTotalRevinueForCustomer(numberOfCampaigns * customerDetails.getPricePerCampaign());

            //remove used impressions from total
            maxImpressions = maxImpressions - totalImpressionsForCustomer;
        }
    }

}
