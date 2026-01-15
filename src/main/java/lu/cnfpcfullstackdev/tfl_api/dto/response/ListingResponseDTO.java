package lu.cnfpcfullstackdev.tfl_api.dto.response;
 
import lu.cnfpcfullstackdev.tfl_api.entity.ListingStatus;
import java.time.LocalDateTime;
 
public class ListingResponseDTO {
    private Long id;
    private String title;
    private String description;
    private Integer quantity;
    private LocalDateTime expiryDate;
    private String pickupTime;
    private ListingStatus status;
    // Business information (nested DTO - not the full User!)
    private BusinessSummaryDTO business;
 
    // Empty constructor (required for Jackson)
    public ListingResponseDTO() {
    }
    // Full constructor
    public ListingResponseDTO(Long id, String title, String description, 
                              Integer quantity, LocalDateTime expiryDate, 
                              String pickupTime, ListingStatus status, 
                              BusinessSummaryDTO business) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.pickupTime = pickupTime;
        this.status = status;
        this.business = business;
    }
 
    // Getters and Setters...
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
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
    public ListingStatus getStatus() {
        return status;
    }
    public void setStatus(ListingStatus status) {
        this.status = status;
    }
    public BusinessSummaryDTO getBusiness() {
        return business;
    }
    public void setBusiness(BusinessSummaryDTO business) {
        this.business = business;
    }
}
