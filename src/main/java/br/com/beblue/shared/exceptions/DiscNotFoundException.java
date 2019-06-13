package br.com.beblue.shared.exceptions;

public class DiscNotFoundException extends NotFoundException {

    public DiscNotFoundException() {
        super("Disc not found.");
    }
}
