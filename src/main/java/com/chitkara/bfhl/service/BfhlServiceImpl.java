package com.chitkara.bfhl.service;

import com.chitkara.bfhl.config.BfhlUserProperties;
import com.chitkara.bfhl.dto.BfhlRequest;
import com.chitkara.bfhl.dto.BfhlResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BfhlServiceImpl implements BfhlService {

	private final BfhlUserProperties userProperties;

	public BfhlServiceImpl(BfhlUserProperties userProperties) {
		this.userProperties = userProperties;
	}

	@Override
	public BfhlResponse process(BfhlRequest request) {
		List<String> data = request.getData() != null ? request.getData() : List.of();

		List<String> oddNumbers = new ArrayList<>();
		List<String> evenNumbers = new ArrayList<>();
		List<String> alphabets = new ArrayList<>();
		List<String> specialCharacters = new ArrayList<>();
		long sum = 0;

		for (String item : data) {
			if (isNumeric(item)) {
				long value = Long.parseLong(item);
				sum += value;
				if (value % 2 == 0) {
					evenNumbers.add(item);
				} else {
					oddNumbers.add(item);
				}
			} else if (isAlphabetic(item)) {
				alphabets.add(item.toUpperCase());
			} else {
				specialCharacters.add(item);
			}
		}

		return new BfhlResponse(
				true,
				userProperties.getUserId(),
				userProperties.getEmail(),
				userProperties.getRollNumber(),
				oddNumbers,
				evenNumbers,
				alphabets,
				specialCharacters,
				Long.toString(sum),
				buildConcatString(alphabets));
	}

	private boolean isNumeric(String value) {
		return value != null && !value.isEmpty() && value.chars().allMatch(Character::isDigit);
	}

	private boolean isAlphabetic(String value) {
		return value != null && !value.isEmpty() && value.chars().allMatch(Character::isLetter);
	}

	private String buildConcatString(List<String> alphabets) {
		List<String> reversedOrder = new ArrayList<>(alphabets);
		Collections.reverse(reversedOrder);

		StringBuilder combined = new StringBuilder();
		for (String alphabet : reversedOrder) {
			combined.append(new StringBuilder(alphabet).reverse());
		}

		String raw = combined.toString();
		if (raw.isEmpty()) {
			return "";
		}

		StringBuilder result = new StringBuilder(raw.length());
		for (int i = 0; i < raw.length(); i++) {
			char character = raw.charAt(i);
			result.append(i % 2 == 0 ? Character.toUpperCase(character) : Character.toLowerCase(character));
		}
		return result.toString();
	}
}
