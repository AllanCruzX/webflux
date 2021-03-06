package com.apirest.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.apirest.consumer.model.PlaylistComTempo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ConsumerPlayListService {
	
	@Autowired
	private WebClient webClientPlayList;
	
	@Autowired
	private WebClient webClientTempo;
	
  public Mono<PlaylistComTempo> obterPorIdParlelo(String id){
		
	  Mono<PlaylistComTempo> monoPlaylist = this.webClientPlayList
				.method(HttpMethod.GET)
				.uri("/playlist/{id}", id)
				.retrieve()
				.bodyToMono(PlaylistComTempo.class);
	  
	  Mono<PlaylistComTempo> monoTempo = this.webClientTempo
				.method(HttpMethod.GET)
				.uri("/tempos/{id}", id)
				.retrieve()
				.bodyToMono(PlaylistComTempo.class);
	  
	  Mono<PlaylistComTempo> playlistComTempo = Mono.zip(monoPlaylist, monoTempo).map(tuple -> {
			tuple.getT1().setTempo(tuple.getT2().getTempo());
			return tuple.getT1();
		});
	  
	  return playlistComTempo;
		
	}
  
	public PlaylistComTempo obterPorCodigoSincrono(String id) {
		
		Mono<PlaylistComTempo> monoPlaylist = this.webClientPlayList
				.method(HttpMethod.GET)
				.uri("/playlist/{id}", id)
				.retrieve()
				.bodyToMono(PlaylistComTempo.class);
		
		
		 Mono<PlaylistComTempo> monoTempo = this.webClientTempo
					.method(HttpMethod.GET)
					.uri("/tempos/{id}", id)
					.retrieve()
					.bodyToMono(PlaylistComTempo.class);
		
		PlaylistComTempo playlist = monoPlaylist.block();
		
		PlaylistComTempo tempo = monoTempo.block();
		
		playlist.setTempo(tempo.getTempo());
		
		return playlist;
		
	}
  
  
  public Flux<PlaylistComTempo> getAllPlaylist(){
  		
  		return webClientPlayList
				.method(HttpMethod.GET)
				.uri("/playlist/")
				.retrieve()
				.bodyToFlux(PlaylistComTempo.class);
	  
  	}
  
	public Mono<PlaylistComTempo> salvar(PlaylistComTempo playlist) {
		
		 return webClientPlayList
					.post()
					.uri("/playlist")
					.body(BodyInserters.fromValue(playlist))
					.retrieve()
					.bodyToMono(PlaylistComTempo.class);
		
	}
	
	

}
