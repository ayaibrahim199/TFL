package lu.cnfpcfullstackdev.tfl_api.dto.request;

import java.time.LocalDateTime;

public class UpdateListingRequestDTO {
    private String title;
    private String description;
    private Integer quantity;
    private LocalDateTime expiryDate;
    private String pickupTime;

    public UpdateListingRequestDTO() {}

    // Getters and Setters
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }
    public String getPickupTime() {
        return pickupTime;
    }
    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }
}