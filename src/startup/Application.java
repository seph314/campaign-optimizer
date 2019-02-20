package startup;

import view.UserInterface;

/**
 * This application reads a file containing a monthly inventory over available campaigns
 * The result is a presentation that shows how many campaigns to sell to what customers
 * in order to maximize the revenue.
 */
public class Application {

    public static void main(String[] args) {
        new UserInterface().commandLineParser();
    }

}
