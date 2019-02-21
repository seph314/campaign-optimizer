package common;

public class CustomerCampaignDTO {
    private String customerName;
    private int impressionsPerCampaign;
    private int pricePerCampaign;
    private int valuePerImpression;

    public CustomerCampaignDTO(String customerName, int impressionsPerCampaign, int pricePerCampaign) {
        this.customerName = customerName;
        this.impressionsPerCampaign = impressionsPerCampaign;
        this.pricePerCampaign = pricePerCampaign;
        this.valuePerImpression = pricePerCampaign / impressionsPerCampaign;
    }

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

    public int getValuePerImpression() {
        return valuePerImpression;
    }

    public void calculateValuePerImpression() {
        this.valuePerImpression = pricePerCampaign / impressionsPerCampaign;
    }
}
