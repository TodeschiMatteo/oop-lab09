package it.unibo.mvc;

import java.util.List;

/**
 * Controller Interface.
 */
public interface Controller {
    
    /**
     * Set the next String to be printed.
     * @param data
     */
    void setNextString(String data);

    /**
     * 
     * @return the next String to be printed
     */
    String getNextString();

    /**
     * Print the next String.
     */
    void printString();

    /**
     * 
     * @return a list that rapresent the history of the printed String
     */
    List<String> getHistory();
}
