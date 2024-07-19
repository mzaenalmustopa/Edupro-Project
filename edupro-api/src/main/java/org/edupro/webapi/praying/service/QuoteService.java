package org.edupro.webapi.praying.service;

import org.edupro.webapi.praying.model.QuoteReq;
import org.edupro.webapi.praying.model.QuoteRes;

import java.util.List;
import java.util.Optional;

public interface QuoteService {
    List<QuoteRes> get();
    Optional<QuoteRes> getById(String id);
    Optional<QuoteRes> save(QuoteReq request);
    Optional<QuoteRes> update(QuoteReq request, String id);
    Optional<QuoteRes> delete(String id);
}
