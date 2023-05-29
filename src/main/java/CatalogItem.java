public class CatalogItem {
    int idItem;
    String description;
    String wayToImage;
    String category;
    String price;
    int quantity;


    public CatalogItem(int idItem, String description, String wayToImage, String category, String price, int quantity) {
        this.idItem = idItem;
        this.description = description;
        this.wayToImage = wayToImage;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWayToImage(String wayToImage) {
        this.wayToImage = wayToImage;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getIdItem() {
        return idItem;
    }

    public String getDescription() {
        return description;
    }

    public String getWayToImage() {
        return wayToImage;
    }

    public String getCategory() {
        return category;
    }

    public String getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "CatalogItem{" +
                "idItem=" + idItem +
                ", description='" + description + '\'' +
                ", wayToImage='" + wayToImage + '\'' +
                ", category='" + category + '\'' +
                ", price='" + price + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}

