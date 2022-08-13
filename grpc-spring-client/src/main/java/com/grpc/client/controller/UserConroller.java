package com.grpc.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grpc.client.model.UserRequest;
import com.grpc.client.service.UserService;

@RequestMapping("/user")
@RestController
public class UserConroller {
	@Autowired
	UserService userService;

	@GetMapping("/search/{name}")
	public ResponseEntity<?> searchUser(@PathVariable String name) {
		return ResponseEntity.ok(userService.searchUser(name));
	}

	@PostMapping("/add")
	public ResponseEntity<?> addNew(@RequestBody UserRequest request) {
		return new ResponseEntity<>(userService.add(request), HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody UserRequest request) {
		return new ResponseEntity<>(userService.update(request), HttpStatus.OK);
	}
}
