package com.jcmbeng.fundtransfer.dtos;

import com.jcmbeng.fundtransfer.consts.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static com.jcmbeng.fundtransfer.consts.Constants.ITEM_PER_PAGE;

/**
 * Data Transfer Object (DTO) for handling pagination and sorting details.
 *
 * <p>This class encapsulates pagination parameters (page and size) and sorting details
 * (field and direction) to create {@link Pageable} objects, allowing pagination
 * and sorting in database queries.</p>
 *
 * @see Pageable
 * @see Sort
 */
@Builder
@Data
@AllArgsConstructor
public class PaginationAndSortDTO {

    /**
     * The page number to retrieve (0-based).
     * Default is <code>0</code> (first page).
     */
    private int page = 0;

    /**
     * The number of records per page.
     * Default is <code>{@link  Constants#ITEM_PER_PAGE}</code>.
     */
    private int size = ITEM_PER_PAGE;

    /**
     * The field on which to sort. Default is <code>"id"</code>.
     */
    private String sortField = "id";

    /**
     * The direction of the sort. Default is {@link Sort.Direction#ASC} (ascending).
     */
    private Sort.Direction sortDirection = Sort.Direction.ASC;


    /**
     * The value to search ine the entity if the user decices to perfomr any search
     *
     */
    private String valueToSearch;

    // Constructors

    /**
     * Default constructor for {@link PaginationAndSortDTO}.
     */
    public PaginationAndSortDTO() { }

    /**
     * Parameterized constructor for setting pagination and sorting details.
     *
     * @param page the page number to retrieve
     * @param size the number of records per page
     * @param sortField the field to sort on
     * @param sortDirection the direction of the sort
     */
    public static PaginationAndSortDTO getPaginAndSort (int page,
                                                        int size,
                                                        String sortField,
                                                        String sortDirection) {
        return PaginationAndSortDTO.builder ()
                .page(page)
                .size(size)
                .sortField((sortField==null || sortField.isBlank ()) ? "id" :
                        sortField)
                .sortDirection((sortDirection==null || !sortDirection.equalsIgnoreCase ("DESC")) ? Sort.Direction.ASC :
                        Sort.Direction.DESC)
                .build ();
    }

    // Getters and Setters

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public Sort.Direction getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(Sort.Direction sortDirection) {
        this.sortDirection = sortDirection;
    }

    /**
     * Converts the pagination and sorting details to a {@link Pageable} instance.
     *
     * @return a {@link Pageable} object configured with the page, size, sort field, and sort direction.
     */
    public Pageable toPageable() {
        return PageRequest.of(page, size, Sort.by(sortDirection, sortField));
    }
}
