package com.nace.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nace.controller.NaceController;
import com.nace.model.NaceEntry;
import com.nace.model.NaceResponse;
import com.nace.service.NaceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(NaceController.class)
public class WebMockTest {

	@MockBean
	NaceService service;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private JacksonTester<NaceEntry> jsonTester;

	@Before
	public void setup() {
		JacksonTester.initFields(this, objectMapper);

	}

	@Test
	public void shouldReturnAllNaceEntries() throws Exception {
		//given
		NaceResponse naceResponse = new NaceResponse(asList(prepareNaceEntry()),null, null);
		when(service.getNaceAllEntries()).thenReturn(naceResponse);

		//when
		this.mockMvc.perform(get("/nace/entry"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Dummy")));
	}

	@Test
	public void shouldCreateNaceEntry() throws Exception {
		//given
		NaceResponse naceResponse = new NaceResponse(asList(prepareNaceEntry()),null, null);
		when(service.getNaceAllEntries()).thenReturn(naceResponse);

		//when
		this.mockMvc.perform(get("/nace/entry"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Dummy")));
	}

	@Test
	public void shouldPersistNaceEntry()  throws Exception{
		//given
		NaceResponse naceResponse = new NaceResponse(asList(prepareNaceEntry()),null, null);
		when(service.createNaceEntry(prepareNaceEntry())).thenReturn(naceResponse);

		mockMvc
				.perform(post("/nace/entry")
						.content(jsonTester.write(prepareNaceEntry()).getJson()).contentType(APPLICATION_JSON))
				.andExpect(status().isCreated());

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
}