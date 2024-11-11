package com.jcmbeng.fundtransfer.handlers;


import com.jcmbeng.fundtransfer.dtos.ResponsePaginatedAndSortedDTO;
import com.jcmbeng.fundtransfer.enums.CustomMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ResponseHandler {

    public static ResponseEntity generateResponse(Object responseObj, CustomMessage customMessage) {

        Map<String, Object> map = new HashMap<>();
        map.put("message", customMessage.getMessage());
        map.put("status", customMessage.getHttpStatus());
        map.put("statusCode", customMessage.getHttpStatus().value());
        map.put("response", responseObj);
        if (responseObj instanceof List<?>) {
            map.put("totalCount", ((List<?>) responseObj).size());
        }

        return new ResponseEntity<>(map, customMessage.getHttpStatus());

    }
    public static ResponseEntity response(ResponsePaginatedAndSortedDTO responseObj, CustomMessage customMessage) {

        Map<String, Object> map = new HashMap<>();
        map.put("message", customMessage.getMessage());
        map.put("status", customMessage.getHttpStatus());
        map.put("statusCode", customMessage.getHttpStatus().value());
        map.put("data", responseObj.getData());
        map.put("totalItems", responseObj.getTotalItems());
        map.put("totalPages", responseObj.getTotalPages());
        map.put("currentPage", responseObj.getCurrentPage());
        return new ResponseEntity<>(map, customMessage.getHttpStatus());
    }


}
