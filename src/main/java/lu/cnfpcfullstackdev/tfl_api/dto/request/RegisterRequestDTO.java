package lu.cnfpcfullstackdev.tfl_api.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.*;
import lu.cnfpcfullstackdev.tfl_api.entity.UserRole;
 
public class RegisterRequestDTO {
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$", 
             message = "Username can only contain letters, numbers, hyphens and underscores")
    private String username;
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
    @NotNull(message = "Role is required")
    private UserRole role;
    // Business-specific fields (optional, only if role = BUSINESS)
    private String businessName;
    private String businessAddress;
    private String businessPhone;
 
    // Constructors, getters, setters...

    public RegisterRequestDTO() {}
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public UserRole getRole() {
        return role;
    }
 
    public void setRole(UserRole role) {
        this.role = role;
    }
 
    public String getBusinessName() {
        return businessName;
    }
 
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
 
    public String getBusinessAddress() {
        return businessAddress;
    }
 
    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }
 
    public String getBusinessPhone() {
        return businessPhone;
    }
 
    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

}