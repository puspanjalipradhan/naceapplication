package com.nace.service;

import com.nace.data.NaceEntity;
import com.nace.data.NaceRepository;
import com.nace.model.NaceEntry;
import com.nace.model.NaceResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class NaceServiceImplTest {

    @Mock
    private NaceRepository naceRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    NaceServiceImpl naceServiceImpl;

    @Test
    public void shouldCreateNaceEntry() {
        //given
        NaceEntry naceEntry = prepareNaceEntry();

        //when
        NaceResponse naceResponse = naceServiceImpl.createNaceEntry(naceEntry);

        //then
        Assert.assertEquals("Successfully Created", naceResponse.getSuccessMsg());
    }

    @Test
    public void shouldGetNaceEntry() {
        //given
        int orderId = 1;
        when(naceRepository.findByOrderId(orderId)).thenReturn(convertNaceEntrytoEntity(prepareNaceEntry()));
        when(modelMapper.map(any(),any())).thenReturn(prepareNaceEntry());

        //when
        NaceResponse naceResponse = naceServiceImpl.getNaceEntry(orderId);

        //then
        Assert.assertEquals(1, naceResponse.getNaceEntries().size());
    }

    @Test
    public void shouldNotGetNaceEntry() {
        //given
        int invalidOrderId = 5;

        //when
        NaceResponse naceResponse = naceServiceImpl.getNaceEntry(invalidOrderId);

        //then
        Assert.assertEquals("Failed to find Object", naceResponse.getErrorMsg());
    }

    @Test
    public void shouldGetNaceEntries() {
        //given
        when(naceRepository.findAll()).thenReturn(asList(convertNaceEntrytoEntity(prepareNaceEntry())));
        when(modelMapper.map(any(),any())).thenReturn(prepareNaceEntry());

        //when
        NaceResponse naceResponse = naceServiceImpl.getNaceAllEntries();

        //then
        Assert.assertEquals(1, naceResponse.getNaceEntries().size());
    }


    private NaceEntry prepareNaceEntry() {
        NaceEntry naceEntry = new NaceEntry();
        naceEntry.setCode("0");
        naceEntry.setDescription("Dummy");
        naceEntry.setOrderId(1);
        naceEntry.setLevel(1);
        naceEntry.setRefIscRef4("1234");
        return naceEntry;
    }

    private NaceEntity convertNaceEntrytoEntity(NaceEntry entry) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(entry, NaceEntity.class);

    }
}
