package com.github.controller;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.models.GithubUser;
import com.github.models.Repo;

import reactor.core.publisher.Mono;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class GithubController {

	String API_URL = "https://api.github.com/users/";
	
	private final WebClient webclient;

	public GithubController(WebClient.Builder webClientBuilder) {
		this.webclient = webClientBuilder.baseUrl(API_URL).build();

	}
	@GetMapping("/")
	public String Home() {
		
		return "index";

	}
	@GetMapping("/search")
	public String fetchUser(@RequestParam String name, Model model) {
		Mono<GithubUser> githubMono = this.webclient.get().uri(UriBuilder -> 
		UriBuilder.path(name).build())
				.retrieve().bodyToMono(GithubUser.class);
		GithubUser user = githubMono.block();
		
		model.addAttribute("users", user);
		return "index";

	}
	@GetMapping("/repo")
	public String fetchRepo(@RequestParam String name, Model model) {
		Mono<Repo> githubMono = this.webclient.get().uri(UriBuilder -> 
		UriBuilder.path(name + "repos").build())
				.retrieve().bodyToMono(Repo.class);
		Repo repo= githubMono.block();
		System.out.println("repos"+ repo.toString());
		model.addAttribute("repos", repo);
		return "index";
		

	}



}
