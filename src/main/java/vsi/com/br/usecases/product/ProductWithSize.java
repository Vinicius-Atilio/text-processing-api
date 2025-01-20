package vsi.com.br.usecases.product;

public class ProductWithSize extends Product {
    private int size;

    public ProductWithSize(Product product, int size) {
        super(product.getName(), product.getCode(), product.getPrice());
        this.size = size;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (size != ((ProductWithSize) obj).size) return false;

        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public int getSize() {
        return size;
    }
}
