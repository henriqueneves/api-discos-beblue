package br.com.beblue.shared.exceptions;

public class SaleNotFoundException extends NotFoundException {

    public SaleNotFoundException() {
        super("Sale not found.");
    }
}
