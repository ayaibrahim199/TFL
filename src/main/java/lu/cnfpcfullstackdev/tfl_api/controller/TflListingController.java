package lu.cnfpcfullstackdev.tfl_api.controller;
import lu.cnfpcfullstackdev.tfl_api.dto.response.ListingResponseDTO;
import lu.cnfpcfullstackdev.tfl_api.dto.request.CreateListingRequestDTO;
import lu.cnfpcfullstackdev.tfl_api.dto.request.UpdateListingRequestDTO;
import lu.cnfpcfullstackdev.tfl_api.entity.TflListing;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;
import lu.cnfpcfullstackdev.tfl_api.services.TflListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/listings")
public class TflListingController {


    @Autowired
    private TflListingService listingService;

    @GetMapping
    @PreAuthorize("permitAll()")
    public List<ListingResponseDTO> getAllListings() {
        return listingService.getAllListings();
    }
// GET - Get listing by ID
    @GetMapping("/{id}")
    public ListingResponseDTO getListingById(@PathVariable Long id) {
        return listingService.getListingById(id);
    }
    // POST - Create new listing
@PostMapping
    @PreAuthorize("hasRole('BUSINESS')")

public ResponseEntity<ListingResponseDTO> createListing(@RequestBody @Valid CreateListingRequestDTO dto) {
    ListingResponseDTO created = listingService.createListing(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
}

// Search listings by status
@GetMapping("/search")
@PreAuthorize("permitAll()")

public List<ListingResponseDTO> getListingsByStatus(@RequestParam String status) {
    return listingService.searchByStatus(status);
}
// PUT - Update listing
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('BUSINESS')")

    public ResponseEntity<ListingResponseDTO> updateListing(@PathVariable Long id, @RequestBody UpdateListingRequestDTO dto) {
        ListingResponseDTO updated = listingService.updateListing(id, dto);
        return ResponseEntity.ok(updated);
    }
    
    // DELETE - Remove listing
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('BUSINESS')")

    public ResponseEntity<Void> deleteListing(@PathVariable Long id) {
        listingService.deleteListing(id);
        return ResponseEntity.noContent().build();
    }
 
    

}



