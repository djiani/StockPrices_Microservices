package com.revature.stock.dbservice.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.stock.dbservice.model.Quote;
import com.revature.stock.dbservice.model.Quotes;
import com.revature.stock.dbservice.repository.QuotesRepository;

@RestController
@RequestMapping("/rest/db")
public class DBServiceResource {
	
	@Autowired
	private QuotesRepository quotesRespoistory;

	@GetMapping("/{username}")
	public List<String> getQuotes(@PathVariable final String username){
		
		return getQuotesByUserName(username);
	}

	

	@PostMapping("/add")
	public List<String> add(@RequestBody final Quotes quotes){
		
		quotes.getQuotes()
		.stream()
		.map(quote -> new Quote(quotes.getUserName(), quote) )
		.forEach(quote -> {
			quotesRespoistory.save(quote);
			});
		
		return getQuotesByUserName(quotes.getUserName());
	}
	
	@DeleteMapping("/delete/{username}")
	public List<String> delete(@PathVariable("username") final String username) {
		List<Quote> quotes = quotesRespoistory.findByUserName(username);
	
		quotesRespoistory.deleteAll(quotes);
		
		return getQuotesByUserName(username);
	}
	
	
	private List<String> getQuotesByUserName(final String username) {
		return quotesRespoistory.findByUserName(username)
			.stream()
			.map(Quote::getQuote)   //nice way to map collection
			.collect(Collectors.toList());
	}
}
