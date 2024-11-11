package com.jcmbeng.fundtransfer.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto implements Serializable {
    private static final long serialVersionUID = 5873937605143751984L;
    private String value;
}
