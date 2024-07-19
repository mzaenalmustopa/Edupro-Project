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
public class CommonRes {
    private String id;
    private String kode;
    private String nama;
    private Integer noUrut;
    private DataStatus status;

    public CommonRes(String kode, String nama, DataStatus status) {
        this.kode = kode;
        this.nama = nama;
        this.status = status;
    }
}
