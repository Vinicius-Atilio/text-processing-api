package vsi.com.br.usecases.teste;

import vsi.com.br.usecases.product.Product;
import vsi.com.br.usecases.product.ProductWithSize;

public class ProductScenarioCreator {

    public static Product macBook() {
        return new Product("MacBook", "47", 13999);
    }

    public static Product noteBook() {
        return new Product("NoteBook", "47", 6350);
    }

    public static Product iPhone() {
        return new Product("iPhone", "09", 7689);
    }

    public static Product cellPhone() {
        return new Product("CellPhone", "09", 3499);
    }

    public static ProductWithSize macBookWithSize(int size) {
        return new ProductWithSize(macBook(), size);
    }

    public static ProductWithSize noteBookWithSize(int size) {
        return new ProductWithSize(noteBook(), size);
    }

    public static ProductWithSize iPhoneWithSize(int size) {
        return new ProductWithSize(iPhone(), size);
    }

    public static ProductWithSize cellPhoneWithSize(int size) {
        return new ProductWithSize(cellPhone(), size);
    }
}
