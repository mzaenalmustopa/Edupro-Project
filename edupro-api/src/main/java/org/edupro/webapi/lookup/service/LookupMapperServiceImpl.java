package org.edupro.webapi.lookup.service;

import lombok.RequiredArgsConstructor;
import org.edupro.webapi.lookup.model.LookupRes;
import org.edupro.webapi.mapper.LookupMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LookupMapperServiceImpl implements LookupMapperService {
    private final LookupMapper lookupMapper;

    @Override
    public LookupRes getById(String id) {
        return lookupMapper.getById(id);
    }

    @Override
    public List<LookupRes> getByGroup(String group) {
        return lookupMapper.getByGroup(group);
    }
}
