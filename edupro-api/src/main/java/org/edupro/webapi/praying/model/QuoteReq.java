package org.edupro.webapi.praying.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuoteReq {
    private String name;
    private String value;
    private String types;
    private String source;
    private Integer position;
}
