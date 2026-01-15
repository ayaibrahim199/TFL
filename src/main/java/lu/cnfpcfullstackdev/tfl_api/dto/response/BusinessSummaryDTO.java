

    package lu.cnfpcfullstackdev.tfl_api.dto.response;
 
public class BusinessSummaryDTO {
    private Long id;
    private String businessName;
    private String businessAddress;
    public BusinessSummaryDTO() {}
    public BusinessSummaryDTO(Long id, String businessName, String businessAddress) {
        this.id = id;
        this.businessName = businessName;
        this.businessAddress = businessAddress;
    }
 
    // Getters and Setters...
}

