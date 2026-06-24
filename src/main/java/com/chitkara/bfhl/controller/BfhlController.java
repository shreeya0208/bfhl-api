package com.chitkara.bfhl.controller;

import com.chitkara.bfhl.dto.BfhlRequest;
import com.chitkara.bfhl.dto.BfhlResponse;
import com.chitkara.bfhl.service.BfhlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BfhlController {

	private final BfhlService bfhlService;

	public BfhlController(BfhlService bfhlService) {
		this.bfhlService = bfhlService;
	}

	@PostMapping("/bfhl")
	public ResponseEntity<BfhlResponse> processBfhl(@RequestBody BfhlRequest request) {
		return ResponseEntity.ok(bfhlService.process(request));
	}
}
