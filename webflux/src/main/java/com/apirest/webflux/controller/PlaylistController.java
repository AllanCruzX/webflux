package com.apirest.webflux.controller;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.apirest.webflux.document.Playlist;
import com.apirest.webflux.services.PlaylistService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import java.util.concurrent.TimeUnit;

@CrossOrigin(origins = "*")
@RestController
public class PlaylistController {
	
	@Autowired
	PlaylistService service;
	
	@GetMapping(value="/playlist")
	public Flux<Playlist> getPlaylist(){
		return service.findAll();
	}
	
	
	@GetMapping(value="/playlist/{id}")
	public Mono<Playlist> getPlaylistId(@PathVariable String id){
		return service.findById(id);
	}
	
	@PostMapping(value="/playlist")
	public Mono<Playlist> save(@RequestBody Playlist playlist){
		return service.save(playlist);
	}
	
	//--------------------------------------------
	//Events Stream
	
	@GetMapping(value="/playlist/webflux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Tuple2<Long, Playlist>> getPlaylistByWebflux(){

		System.out.println("---Start get Playlists by WEBFLUX--- " + LocalDateTime.now());
		
		//intervalo de cada reposta(Stream) que vai ser enviado para o cliente
		Flux<Long> interval = Flux.interval(Duration.ofSeconds(10));
        Flux<Playlist> playlistFlux = service.findAll();

     // Envia para o clinte cada Playlist com intervalo de tempo 
        return Flux.zip(interval, playlistFlux);
        
	}

	@GetMapping(value="/playlist/mvc")
	public List<String> getPlaylistByMvc() throws InterruptedException {

		System.out.println("---Start get Playlists by MVC--- " + LocalDateTime.now());


		List<String> playlistList = new ArrayList<>();
		playlistList.add("Java 8");
		playlistList.add("Spring Security");
		playlistList.add("Github");
		playlistList.add("Deploy de uma aplica????o java no IBM Cloud");
		playlistList.add("Bean no Spring Framework");
		TimeUnit.SECONDS.sleep(15);

		return playlistList;

	}
	
	@GetMapping(value="/playlist/{id}/sincrono")
	public ResponseEntity<Playlist>getPlaylistIdSincrono(@PathVariable String id) throws InterruptedException{
		
		Playlist p = new Playlist("231d61cb-8701-48d0-a0e5-647abcfb7d02", "AWS");
				
		return ResponseEntity.ok(p);
	}

}