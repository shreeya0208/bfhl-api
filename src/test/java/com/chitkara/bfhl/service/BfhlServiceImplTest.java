package com.chitkara.bfhl.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.chitkara.bfhl.config.BfhlUserProperties;
import com.chitkara.bfhl.dto.BfhlRequest;
import com.chitkara.bfhl.dto.BfhlResponse;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BfhlServiceImplTest {

	private BfhlService bfhlService;

	@BeforeEach
	void setUp() {
		BfhlUserProperties userProperties = new BfhlUserProperties();
		userProperties.setUserId("shreeya_02082005");
		userProperties.setEmail("shreeya1132.be23@chitkara.edu.in");
		userProperties.setRollNumber("2310991132");
		bfhlService = new BfhlServiceImpl(userProperties);
	}

	@Test
	void exampleA() {
		BfhlResponse response = bfhlService.process(new BfhlRequest(List.of("a", "1", "334", "4", "R", "$")));

		assertTrue(response.isSuccess());
		assertEquals("shreeya_02082005", response.getUserId());
		assertEquals("shreeya1132.be23@chitkara.edu.in", response.getEmail());
		assertEquals("2310991132", response.getRollNumber());
		assertEquals(List.of("1"), response.getOddNumbers());
		assertEquals(List.of("334", "4"), response.getEvenNumbers());
		assertEquals(List.of("A", "R"), response.getAlphabets());
		assertEquals(List.of("$"), response.getSpecialCharacters());
		assertEquals("339", response.getSum());
		assertEquals("Ra", response.getConcatString());
	}

	@Test
	void exampleB() {
		BfhlResponse response = bfhlService.process(
				new BfhlRequest(List.of("2", "a", "y", "4", "&", "-", "*", "5", "92", "b")));

		assertTrue(response.isSuccess());
		assertEquals(List.of("5"), response.getOddNumbers());
		assertEquals(List.of("2", "4", "92"), response.getEvenNumbers());
		assertEquals(List.of("A", "Y", "B"), response.getAlphabets());
		assertEquals(List.of("&", "-", "*"), response.getSpecialCharacters());
		assertEquals("103", response.getSum());
		assertEquals("ByA", response.getConcatString());
	}

	@Test
	void exampleC() {
		BfhlResponse response = bfhlService.process(new BfhlRequest(List.of("A", "ABCD", "DOE")));

		assertTrue(response.isSuccess());
		assertEquals(List.of(), response.getOddNumbers());
		assertEquals(List.of(), response.getEvenNumbers());
		assertEquals(List.of("A", "ABCD", "DOE"), response.getAlphabets());
		assertEquals(List.of(), response.getSpecialCharacters());
		assertEquals("0", response.getSum());
		assertEquals("EoDdCbAa", response.getConcatString());
	}

	@Test
	void emptyDataReturnsZeroSumAndEmptyArrays() {
		BfhlResponse response = bfhlService.process(new BfhlRequest(List.of()));

		assertTrue(response.isSuccess());
		assertEquals("0", response.getSum());
		assertEquals("", response.getConcatString());
	}
}
