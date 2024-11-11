package com.jcmbeng.fundtransfer.services.impl;

import com.jcmbeng.fundtransfer.dtos.ClientDTO;
import com.jcmbeng.fundtransfer.dtos.PaginationAndSortDTO;
import com.jcmbeng.fundtransfer.dtos.ResponsePaginatedAndSortedDTO;
import com.jcmbeng.fundtransfer.entities.Client;
import com.jcmbeng.fundtransfer.enums.CriteriaFilter;
import com.jcmbeng.fundtransfer.exceptions.*;
import com.jcmbeng.fundtransfer.mappers.impl.ClientMapper;
import com.jcmbeng.fundtransfer.repositories.ClientRepository;
import com.jcmbeng.fundtransfer.repositories.specifications.ClientSpecification;
import com.jcmbeng.fundtransfer.services.ClientService;
import com.jcmbeng.fundtransfer.validators.DtoValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.jcmbeng.fundtransfer.enums.CustomMessage.*;
import static com.jcmbeng.fundtransfer.utils.StringUtil.resourceNotFoundWithId;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl (ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDTO create (final ClientDTO dto) {
        DtoValidator.validate (dto);

        if (clientRepository.existsByEmail (dto.getEmail ())) {
            throw new UniqueConstraintException (VALUE_ALREADY_USED);
        }
        try {
            final Client client = ClientMapper.builder ()
                    .build ()
                    .toEntity (dto);

            return ClientMapper.builder ()
                    .build ()
                    .toDto (
                            clientRepository.save (client)
                    );

        } catch (InvalidOperationException exception) {
            throw new InvalidOperationException (exception);
        }
    }

    @Override
    public ResponsePaginatedAndSortedDTO findAll (PaginationAndSortDTO paginationAndSortDTO) {
        try {

           Specification<Client> clientSpecification = Specification
                    .where (ClientSpecification.hasContent ("name",
                            paginationAndSortDTO.getValueToSearch (),
                            CriteriaFilter.EQUALS))
                    .or (ClientSpecification.hasContent ("email",
                            paginationAndSortDTO.getValueToSearch (),
                            CriteriaFilter.LIKE))
                    .or (ClientSpecification.hasContent ("phoneNumber",
                            paginationAndSortDTO.getValueToSearch (),
                            CriteriaFilter.LIKE));

            return ResponsePaginatedAndSortedDTO.pagedDto (
                    clientRepository.findAll (clientSpecification, paginationAndSortDTO.toPageable ())
            );


        } catch (InvalidOperationException exception) {
            throw new InvalidOperationException (exception);
        }
    }

    @Override
    public ClientDTO findById (final Long id) {
        if (id <= 0 || id == null) {
            throw new InvalidIdException (ID_NOT_VALID);
        }
        try {


            return clientRepository
                    .findById (id)
                    .map (client -> {
                        return ClientMapper.builder ().build ().toDto (client);
                    })
                    .orElseThrow (
                            () -> new ResourceNotFoundException (resourceNotFoundWithId ("Client", id))
                    );

        } catch (InvalidOperationException exception) {
            throw new InvalidOperationException (exception);
        }
    }


    @Override
    public ClientDTO update (final Long id, final ClientDTO dto) {
        DtoValidator.validate (dto);

        if (id <= 0 || id == null) {
            throw new InvalidIdException (ID_NOT_VALID);
        }
        try {
            Optional<Client> optionalClient = clientRepository.findById (id);
            if (optionalClient.isEmpty ()) {
                throw new ResourceNotFoundException (resourceNotFoundWithId ("Client", id));
            }
            Client client = optionalClient.get ();
            client.setName (dto.getName ());
            client.setEmail (dto.getEmail ());
            client.setPhoneNumber (dto.getPhoneNumber ());

            return ClientMapper.builder ().build ().toDto (
                    client
            );

        } catch (InvalidOperationException exception) {
            throw new InvalidOperationException (exception);
        }
    }


    @Override
    public String delete (final Long id) {
        if (id <= 0 || id == null) {
            throw new InvalidIdException (ID_NOT_VALID);
        }
        try {
            Optional<Client> optionalClient = clientRepository.findById (id);
            if (optionalClient.isEmpty ()) {
                throw new ResourceNotFoundException ("Unable to find a Client with id " + id);
            }
            Client client = optionalClient.get ();
            client.setIsDeleted (true);
            clientRepository.save (client);
            return RESOURCE_DELETED.getMessage ();
        } catch (InvalidOperationException exception) {
            throw new InvalidOperationException (exception);
        }
    }
}
