package org.edupro.webapi.base.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.edupro.webapi.constant.DataStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommonLembagaRes {
    private String id;
    private String idLembaga;
    private String namaLembaga;
    private String kode;
    private String nama;
    private Integer noUrut;
    private DataStatus status;
}
