package Task3;

import java.util.ArrayList;
import java.util.List;

import static Task3.Constants.CELL_CAPACITY;

public class StorageCell {

    private final double maxLength;
    private final double maxWidth;
    private final double maxHeight;
    private final List<Product> products = new ArrayList<>();

    public StorageCell(final double maxLength, final double maxWidth, final double maxHeight) {
        this.maxLength = maxLength;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
    }

    public void addProduct(final Product product) {
        if (isFull()) {
            throw new IllegalStateException("Cell is full!");
        }

        if (!canAddProductToCell(product)) {
            throw new IllegalArgumentException("Product dimensions exceed cell dimensions");
        }

        products.add(product);
    }

    private boolean canAddProductToCell(final Product product) {
        return product.length() <= maxLength &&
                product.width() <= maxWidth &&
                product.height() <= maxHeight;
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public int getCurrentCount() {
        return products.size();
    }

    public int getCapacity() {
        return CELL_CAPACITY;
    }

    public double getMaxLength() {
        return maxLength;
    }

    public double getMaxWidth() {
        return maxWidth;
    }

    public double getMaxHeight() {
        return maxHeight;
    }

    private boolean isFull() {
        return products.size() >= CELL_CAPACITY;
    }
}