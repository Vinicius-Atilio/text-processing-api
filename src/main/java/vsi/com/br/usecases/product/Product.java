package vsi.com.br.usecases.product;


import java.util.Objects;

public class Product {
    private String name;
    private String code;
    private int price;

    public Product(String nome, String code, int price) {
        this.name = nome;
        this.code = code;
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (code.equals(((Product) obj).code)
                && name.equals(((Product) obj).name)) return true;

        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
