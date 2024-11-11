package com.jcmbeng.fundtransfer.mappers.impl;

import com.jcmbeng.fundtransfer.dtos.ClientDTO;
import com.jcmbeng.fundtransfer.entities.Client;
import com.jcmbeng.fundtransfer.mappers.EntityMapper;
import lombok.Builder;

@Builder
public class ClientMapper extends EntityMapper<Client, ClientDTO> {

    @Override
    public ClientDTO toDto (final Client entity) {
        if (entity == null) {
            return null; // or throw an exception
        }
        return ClientDTO.builder ()
                .id (entity.getId ())
                .name (entity.getName ())
                .email (entity.getEmail ())
                .phoneNumber (entity.getPhoneNumber ())
                .creationDate (entity.getCreatedAt ()!=null ?  entity.getCreatedAt ().toString () : "")
                .lastUpdateDate (entity.getUpdatedAt ()!=null ?  entity.getUpdatedAt ().toString () : "")
                .build ();
    }

    @Override
    public Client toEntity (ClientDTO dto) {
        if (dto == null) {
            return null; // or throw an exception
        }
        return Client.builder ()
                .name (dto.getName ())
                .email (dto.getEmail ())
                .phoneNumber (dto.getPhoneNumber ())
                .build ();
    }

}
