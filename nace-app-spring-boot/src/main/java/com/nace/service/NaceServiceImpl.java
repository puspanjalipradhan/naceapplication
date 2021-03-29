package com.nace.service;

import com.nace.data.NaceEntity;
import com.nace.data.NaceRepository;
import com.nace.model.NaceEntry;
import com.nace.model.NaceResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.Arrays.asList;

@Service
public class NaceServiceImpl implements NaceService {

    @Autowired
    private NaceRepository naceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public NaceResponse createNaceEntry(NaceEntry naceEntry) {
        NaceEntity naceEntity = modelMapper.map(naceEntry, NaceEntity.class);
        naceRepository.save(naceEntity);
        return new NaceResponse(null, "Successfully Created", null);
    }

    @Override
    public NaceResponse getNaceEntry(int orderId) {

        return Optional.ofNullable(naceRepository.findByOrderId(orderId))
                .map(naceEntity -> new NaceResponse(asList(modelMapper.map(naceEntity, NaceEntry.class)), null, null)
                )
                .orElse(new NaceResponse(null, null, "Failed to find Object"));

    }

    @Override
    public NaceResponse getNaceAllEntries() {
        return Optional.ofNullable(naceRepository.findAll())
                .map(naceEntities -> new NaceResponse(convertNaceEntry(naceEntities), "Sucessfully fetched Data", null))
                .orElse(new NaceResponse(null, null, "Failed to find Object"));
    }

    private List<NaceEntry> convertNaceEntry(Iterable<NaceEntity> naceEntities) {

        return StreamSupport.stream(naceEntities.spliterator(), false)
                .map(entity -> modelMapper.map(entity, NaceEntry.class))
                .collect(Collectors.toList());
    }
}
