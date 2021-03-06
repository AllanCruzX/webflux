package com.apirest.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.consumer.model.PlaylistComTempo;
import com.apirest.consumer.service.ConsumerPlayListService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ConsumerPlayListController {

	@Autowired
	private ConsumerPlayListService consumerPlayListService;

	@GetMapping("/playlist/{id}")
	public Mono<PlaylistComTempo> obterPorIdParlelo(@PathVariable String id) {

		return consumerPlayListService.obterPorIdParlelo(id);

	}

	@GetMapping("/playlist/{id}/sincrono")
	public ResponseEntity<PlaylistComTempo> obterPorIdSincrono(@PathVariable String id) {

		PlaylistComTempo playlistComTempo = this.consumerPlayListService.obterPorCodigoSincrono(id);

		return ResponseEntity.ok(playlistComTempo);
	}

	@GetMapping("/playlist")
	public Flux<PlaylistComTempo> getPlaylist() {

		return consumerPlayListService.getAllPlaylist();
	}

	@PostMapping(value = "/playlist")
	public Mono<PlaylistComTempo> save(@RequestBody PlaylistComTempo playlist) {
		return consumerPlayListService.salvar(playlist);
	}

}
