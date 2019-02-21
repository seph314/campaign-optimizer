package controller;

import common.CustomerCampaignDTO;
import model.CampaignOptimizer;
import model.FileHandler;

import java.io.File;
import java.util.List;

public class Controller {

    private FileHandler fileHandler = new FileHandler();

    public File[] getFiles() {
        return fileHandler.getFiles();
    }

    public List<String> readFile(String fileName) {
        return fileHandler.readFile(fileName);
    }

    public List<CustomerCampaignDTO> calculateResult(List<String> fileContent) {
        return new CampaignOptimizer().optimize(fileContent);
    }
}
