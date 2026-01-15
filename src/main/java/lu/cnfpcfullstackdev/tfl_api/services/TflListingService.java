package lu.cnfpcfullstackdev.tfl_api.services;

import lu.cnfpcfullstackdev.tfl_api.dto.request.CreateListingRequestDTO;
import lu.cnfpcfullstackdev.tfl_api.dto.request.UpdateListingRequestDTO;
import lu.cnfpcfullstackdev.tfl_api.dto.response.ListingResponseDTO;
import lu.cnfpcfullstackdev.tfl_api.entity.ListingStatus;
import lu.cnfpcfullstackdev.tfl_api.entity.TflListing;
import lu.cnfpcfullstackdev.tfl_api.entity.TflUser;
import lu.cnfpcfullstackdev.tfl_api.entity.UserRole;
import lu.cnfpcfullstackdev.tfl_api.exception.DuplicateResourceException;
import lu.cnfpcfullstackdev.tfl_api.exception.ResourceNotFoundException;
import lu.cnfpcfullstackdev.tfl_api.mapper.ListingMapper;
import lu.cnfpcfullstackdev.tfl_api.repository.TflListingRepository;
import lu.cnfpcfullstackdev.tfl_api.repository.TflUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.List;
import java.util.stream.Collectors;
 
@Service
public class TflListingService {
    @Autowired
    private TflListingRepository repository;


    @Autowired
    private TflUserRepository userRepository;
    
    
    // Get all listings
    public List<ListingResponseDTO> getAllListings() {
        return repository.findAll().stream().map(ListingMapper::toResponseDTO).collect(Collectors.toList());
    }

    // Get listing by ID
    public ListingResponseDTO getListingById(Long id) {
        TflListing listing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("listing", id));
        return ListingMapper.toResponseDTO(listing);
               
    }

    // Create new listing
    public ListingResponseDTO createListing(CreateListingRequestDTO dto) {

        Long businessId = dto.getBusinessId();
        TflUser business = userRepository.findById(businessId)
                .orElseThrow(() -> new ResourceNotFoundException("TflUser", businessId));

        // verify that business is actually a business
        if (business.getRole() != UserRole.BUSINESS) {
            throw new RuntimeException("User with id " + businessId + " is not a business.");
        }

        TflListing listing = ListingMapper.toEntity(dto);
        listing.setBusiness(business);

        // Prevent duplicate titles
        if (repository.existsByTitle(listing.getTitle())) {
            throw new DuplicateResourceException("Listing with title '" + listing.getTitle() + "' already exists.");
        }
        
        listing.setStatus(ListingStatus.AVAILABLE);
        TflListing savedListing = repository.save(listing);
        return ListingMapper.toResponseDTO(savedListing);
    }

    // Search by status
    public List<ListingResponseDTO> searchByStatus(String status) {
        if (status != null) {
            ListingStatus listingStatus = ListingStatus.valueOf(status.toUpperCase());
            return repository.findByStatus(listingStatus).stream().map(ListingMapper :: toResponseDTO).collect(Collectors.toList());
        }
        return repository.findAll().stream().map(ListingMapper :: toResponseDTO).collect(Collectors.toList());
    }

    // Update listing
    public ListingResponseDTO updateListing(Long id, UpdateListingRequestDTO dto) {
        TflListing listing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("listing", id));

        if (dto.getTitle() != null) listing.setTitle(dto.getTitle());
        if (dto.getDescription() != null) listing.setDescription(dto.getDescription());
        // other fields (location/price) depend on entity fields — set if present

        TflListing updated = repository.save(listing);
        return ListingMapper.toResponseDTO(updated);
    }

    // Delete listing
    public void deleteListing(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("listing", id);
        }
        repository.deleteById(id);
    }

}