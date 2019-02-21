package view;

import common.CustomerCampaignDTO;
import controller.Controller;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private Controller controller = new Controller();
    private List<CustomerCampaignDTO> listOfCustomerDetails;

    /**
     * Takes user input and calls appropriate methods depending on commands
     */
    public void commandLineParser(){

        System.out.println("Revenue Maximizer\n");
        System.out.println("Your files:");
        showFiles(controller.getFiles());
        System.out.println("\nEnter a file name to calculate optimal campaign");

        String command = "";
        while (!command.equalsIgnoreCase("quit")){
            Scanner in = new Scanner(System.in);
            command = in.nextLine();
            List<String> fileContent = controller.readFile(command);
            if (fileContent != null)
                listOfCustomerDetails = controller.calculateResult(fileContent);
            printResult();
        }
    }

    private void printResult() {
        int totalNumberOfImpressions = 0;
        int totalRevenue = 0;
        for (CustomerCampaignDTO customerDetails : listOfCustomerDetails){
            System.out.println(customerDetails.getCustomerName() +
                    "," + customerDetails.getNumberOfCampaigns() +
                    "," + customerDetails.getTotalImpressionForCustomer() +
                    "," + customerDetails.getTotalRevinueForCustomer()
            );
            totalNumberOfImpressions += customerDetails.getTotalImpressionForCustomer();
            totalRevenue += customerDetails.getTotalRevinueForCustomer();
        }
        System.out.println(totalNumberOfImpressions + "," + totalRevenue);
    }

    private void showFiles(File[] listOfFiles) {
        if (listOfFiles != null) {
            for (File file : listOfFiles){
                if(file.isFile()){
                    System.out.println(file.getName());
                }
            }
        } else {
            System.out.println("Could not find any files");
        }
    }


}
