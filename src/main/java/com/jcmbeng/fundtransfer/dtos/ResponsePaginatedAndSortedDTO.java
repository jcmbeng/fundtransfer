package com.jcmbeng.fundtransfer.dtos;

import com.jcmbeng.fundtransfer.mappers.EntityMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * Data Transfer Object (DTO) for encapsulating the response of paginated and sorted data.
 *
 * <p>This class is used to structure the response for paginated and sorted data
 * returned from service methods, including the total number of items, total pages,
 * current page number, and the actual data content.</p>
 *
 * @param <T> the type of the data contained in the response
 */
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class ResponsePaginatedAndSortedDTO<T> implements Serializable {

    private long totalItems;     // Total number of items in the dataset
    private long totalPages;     // Total number of pages available
    private long currentPage;    // Current page number (0-based)
    private List<T> data;        // List of items in the current page

    /**
     * Creates a {@link ResponsePaginatedAndSortedDTO} instance from a Spring Data {@link Page}.
     *
     * @param page the Spring Data Page containing the data and pagination information
     * @return a {@link ResponsePaginatedAndSortedDTO} object populated with data from the provided page
     */
    public static ResponsePaginatedAndSortedDTO<?> pagedDto(Page<?> page) {
        return ResponsePaginatedAndSortedDTO.builder()
                .currentPage(page.getNumber())
                .totalItems(page.getNumberOfElements())
                .data((List<Object>) page.getContent())
                .totalPages(page.getTotalPages())
                .build();
    }

    /**
     * Creates a {@link ResponsePaginatedAndSortedDTO} instance with the specified pagination details.
     *
     * @param currentPage the current page number
     * @param totalItems the total number of items in the dataset
     * @param totalPages the total number of pages available
     * @param data the list of data items for the current page
     * @return a {@link ResponsePaginatedAndSortedDTO} object populated with the provided details
     */
    public static ResponsePaginatedAndSortedDTO<?> pagedDto(final long currentPage,
                                                            final long totalItems,
                                                            final long totalPages,
                                                            List<?> data) {
        return ResponsePaginatedAndSortedDTO.builder()
                .currentPage(currentPage)
                .totalItems(totalItems)
                .totalPages(totalPages)
                .data((List<Object>) data)
                .build();
    }

    /**
     * Creates a {@link ResponsePaginatedAndSortedDTO} instance from a Spring Data {@link Page}.
     *
     * @param page the Spring Data Page containing the data and pagination information
     * @return a {@link ResponsePaginatedAndSortedDTO} object populated with data from the provided page
     */
    public static ResponsePaginatedAndSortedDTO<?> pagedDto(Page<?> page, EntityMapper mapper) {
        return ResponsePaginatedAndSortedDTO.builder()
                .currentPage(page.getNumber())
                .totalItems(page.getNumberOfElements())
                .data(mapper.toDtoList (page.getContent()))
                .totalPages(page.getTotalPages())
                .build();
    }
}
