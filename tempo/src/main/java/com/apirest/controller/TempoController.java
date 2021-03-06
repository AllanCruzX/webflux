package com.apirest.controller;

import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.model.Tempo;

@RestController
@RequestMapping("/tempos")
public class TempoController {
	
	@GetMapping("/{id}")
	public ResponseEntity<Tempo> obterTempo(@PathVariable String id) throws Exception {
		
		Random ran = new Random();
		int x = ran.nextInt(6) + 5;
		
		Tempo tempo = new Tempo("231d61cb-8701-48d0-a0e5-647abcfb7d02", x);
		
		//Thread.sleep(3000);

		return ResponseEntity.ok(tempo);
	}

}
