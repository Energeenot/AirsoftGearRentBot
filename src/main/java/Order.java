import java.time.LocalDate;

public class Order {
    String clientName;
    String id;
    String productName;
    String wayToImage;
    String category;
    String price;
    String quantity;
    String methodReceipt;
    String startDay;
    String endDay;

    public Order(String clientName, String id, String productName, String wayToImage, String category, String price, String quantity, String methodReceipt, String startDay, String endDay) {
        this.clientName = clientName;
        this.id = id;
        this.productName = productName;
        this.wayToImage = wayToImage;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.methodReceipt = methodReceipt;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public void setMethodReceipt(String methodReceipt) {
        this.methodReceipt = methodReceipt;
    }

    public void setStartDay(String  startDay) {
        this.startDay = startDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public String getMethodReceipt() {
        return methodReceipt;
    }

    public String  getStartDay() {
        return startDay;
    }

    public String  getEndDay() {
        return endDay;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getClientName() {
        return clientName;
    }

    public String getId() {
        return id;
    }

    public String getProductName() {
        return productName;
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

    public String getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "clientName='" + clientName + '\'' +
                ", id='" + id + '\'' +
                ", productName='" + productName + '\'' +
                ", wayToImage='" + wayToImage + '\'' +
                ", category='" + category + '\'' +
                ", price='" + price + '\'' +
                ", quantity='" + quantity + '\'' +
                ", methodReceipt='" + methodReceipt + '\'' +
                ", startDay='" + startDay + '\'' +
                ", endDay='" + endDay + '\'' +
                '}';
    }
}
