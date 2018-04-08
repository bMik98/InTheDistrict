package com.akurus.telegram.controller;

import org.springframework.web.bind.annotation.*;


@RestController
public class AccountController {


	@RequestMapping(path = "/{name}", method = RequestMethod.GET)
	public void getAccountByName(@PathVariable String name) {
	}


}
