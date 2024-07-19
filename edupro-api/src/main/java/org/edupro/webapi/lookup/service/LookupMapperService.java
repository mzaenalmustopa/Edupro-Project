package org.edupro.webapi.lookup.service;

import org.edupro.webapi.lookup.model.LookupRes;

import java.util.List;

public interface LookupMapperService {
    LookupRes getById(String id);
    List<LookupRes> getByGroup(String group);
}
