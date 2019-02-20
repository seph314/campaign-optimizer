package view;

import controller.Controller;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private Controller controller = new Controller();
    private List<String> fileContent;

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
            fileContent = controller.readFile(command);
            controller.calculateResult(fileContent);
        }
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
