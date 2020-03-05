package com.revature.stock.dbservice.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.stock.dbservice.model.Quote;
import com.revature.stock.dbservice.repository.QuotesRepository;

@RestController
@RequestMapping("/rest/db")
public class DBServiceResource {
	private QuotesRepository quotesRespoistory;

	@GetMapping("/{username}")
	public List<String> getQuotes(@PathVariable final String username){
		
		return quotesRespoistory.findByUserName(username)
			.stream()
			.map(Quote::getQuote)   //nice way to map collection
			.collect(Collectors.toList());
	}

	@PostMapping("/add")
	public List<String> add(@RequestBody final Quote quotes){
		
		return null;
	}
}
