package com.nace.service;

import com.nace.model.NaceEntry;
import com.nace.model.NaceResponse;


public interface NaceService {
    NaceResponse createNaceEntry(NaceEntry naceEntry);

    NaceResponse getNaceEntry(int orderId);

    NaceResponse getNaceAllEntries();
}
