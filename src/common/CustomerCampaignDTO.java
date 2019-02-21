package common;

/**
 * A Data Transfer Object used to encapsulate primitive data when send between different layers
 * Implements Comparable so that we can compare different customer campaigns to each other so
 * that we can maximize profit by selling to the highest bidder
 */
public class CustomerCampaignDTO implements Comparable<Object> {

    private String customerName;
    private int impressionsPerCampaign;
    private int pricePerCampaign;
    private double valuePerImpression;
    private int numberOfCampaigns;
    private int totalImpressionForCustomer;
    private int totalRevinueForCustomer;

    public CustomerCampaignDTO() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getImpressionsPerCampaign() {
        return impressionsPerCampaign;
    }

    public void setImpressionsPerCampaign(int impressionsPerCampaign) {
        this.impressionsPerCampaign = impressionsPerCampaign;
    }

    public int getPricePerCampaign() {
        return pricePerCampaign;
    }

    public void setPricePerCampaign(int pricePerCampaign) {
        this.pricePerCampaign = pricePerCampaign;
    }

    public double getValuePerImpression() {
        return valuePerImpression;
    }

    public void setValuePerImpression() {
        this.valuePerImpression = (double) pricePerCampaign / (double) impressionsPerCampaign;
    }

    public int getNumberOfCampaigns() {
        return numberOfCampaigns;
    }

    public void setNumberOfCampaigns(int numberOfCampaigns) {
        this.numberOfCampaigns = numberOfCampaigns;
    }

    public int getTotalImpressionForCustomer() {
        return totalImpressionForCustomer;
    }

    public void setTotalImpressionForCustomer(int totalImpressionForCustomer) {
        this.totalImpressionForCustomer = totalImpressionForCustomer;
    }

    public int getTotalRevinueForCustomer() {
        return totalRevinueForCustomer;
    }

    public void setTotalRevinueForCustomer(int totalRevinueForCustomer) {
        this.totalRevinueForCustomer = totalRevinueForCustomer;
    }

    @Override
    public int compareTo(Object o) {
        CustomerCampaignDTO customerDetails = (CustomerCampaignDTO) o;
        if (this.valuePerImpression < customerDetails.valuePerImpression)
            return 1;
        if (this.valuePerImpression > customerDetails.valuePerImpression)
            return -1;
        else
            return 0;
    }
}
