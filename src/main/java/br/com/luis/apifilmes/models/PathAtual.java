package br.com.luis.apifilmes.models;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

public class PathAtual {
	public String get() {
		UriComponentsBuilder uri = ServletUriComponentsBuilder.fromCurrentRequest();
		return uri.buildAndExpand().getPath();
	}
}
