package com.jcmbeng.fundtransfer.ws;

import com.jcmbeng.fundtransfer.dtos.ClientDTO;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(
        value = "/api/v1/clients"
)
public interface ClientApi extends CustomWS<ClientDTO>{
}
