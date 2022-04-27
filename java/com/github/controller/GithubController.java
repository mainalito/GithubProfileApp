package com.github.controller;


import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import com.github.models.GithubUser;
import com.github.models.Repo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller

public class GithubController {

	String API_URL = "https://api.github.com/users/";
    static String HTML_URL = "https://github.com/";
	
	
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
		//remove whitespaces
		String newName = name.replace(" ", "");
		System.out.println(newName);
		Mono<GithubUser> githubMono = this.webclient.get().uri(UriBuilder -> 
		UriBuilder.path(newName).build())
				.retrieve().bodyToMono(GithubUser.class);
		GithubUser user = githubMono.block();
	
		Flux<Repo> githubFlux = this.webclient.get().uri(UriBuilder -> 
		UriBuilder.path(newName + "/repos").build())
		.retrieve().bodyToFlux(Repo.class);
		
		List<Repo> repos = githubFlux.collect(Collectors.toList()).share().block();
		
		//sort according to highest stars
		List<Repo> repository =  repos.stream().sorted((a,b)-> Integer.compare(b.getStargazers_count(), a.getStargazers_count())).map(x->{
			String url = HTML_URL+ user.getLogin()+"/"+ x.getName();
			x.setUrl(url);
			return x;
		}).limit(4).collect(Collectors.toList());
		//repository.forEach(System.out::println);

		model.addAttribute("total_repos",repos.stream().map(x->{
			String url = HTML_URL+ user.getLogin()+"/"+ x.getName();
			x.setUrl(url);
			return x;
		}).collect(Collectors.toList()));
		model.addAttribute("repos", repository);
		model.addAttribute("users", user);
		return "index";

	}




}
