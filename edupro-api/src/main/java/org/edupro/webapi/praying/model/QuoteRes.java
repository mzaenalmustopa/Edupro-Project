package org.edupro.webapi.praying.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.edupro.webapi.constant.DataStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuoteRes {
    private String id;
    private String name;
    private String value;
    private String types;
    private String source;
    private Integer position;
    private DataStatus status;
}
