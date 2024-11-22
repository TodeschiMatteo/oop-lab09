package it.unibo.mvc;

import java.util.List;
import java.util.LinkedList;

/**
 *  Controller implementation.
 */
public final class SimpleController implements Controller {

    private String next = "";
    private final List<String> history = new LinkedList<>();

    @Override
    public void setNextString(final String data) {
        this.next = data;
    }

    @Override
    public String getNextString() {
        return this.next;
    }

    @Override
    public void printString() {
        if (this.next.isBlank()) {
            throw new IllegalStateException("No string avaible");
        }
        history.add(this.next);
        System.out.println(this.next); // NOPMD allowed
    }

    @Override
    public List<String> getHistory() {
        return new LinkedList<>(history);
    }

}
