package com.jcmbeng.fundtransfer.mappers;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstract base class for mapping between entity and DTO (Data Transfer Object) representations.
 * <p>
 * This class provides default implementations for converting lists of entities to lists of DTOs
 * and vice versa, while the specific conversion logic for individual entities must be implemented
 * by subclasses.
 * </p>
 *
 * @param <E> the type representing the entity
 * @param <D> the type representing the DTO
 */
public abstract class EntityMapper<E, D> {

    /**
     * Converts an entity to its corresponding DTO representation.
     *
     * @param entity the entity to convert
     * @return the DTO representation of the entity
     */
    public abstract D toDto(E entity);

    /**
     * Converts a DTO to its corresponding entity representation.
     *
     * @param dto the DTO to convert
     * @return the entity representation of the DTO
     */
    public abstract E toEntity(D dto);

    /**
     * Converts a list of entities to a list of their corresponding DTO representations.
     *
     * @param entities the list of entities to convert
     * @return a list of DTOs representing the entities
     */
    public List<D> toDtoList(List<E> entities) {
        return entities.stream()
                .map(this::toDto)  // Maps each entity to its DTO
                .collect(Collectors.toList()); // Collects the results into a list
    }

    /**
     * Converts a list of DTOs to a list of their corresponding entity representations.
     *
     * @param dtos the list of DTOs to convert
     * @return a list of entities representing the DTOs
     */
    public List<E> toEntityList(List<D> dtos) {
        return dtos.stream()
                .map(this::toEntity) // Maps each DTO to its entity
                .collect(Collectors.toList()); // Collects the results into a list
    }
}