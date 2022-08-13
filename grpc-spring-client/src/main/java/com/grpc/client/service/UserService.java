package com.grpc.client.service;

import com.grpc.client.model.BaseResponse;
import com.grpc.client.model.UserRequest;

public interface UserService {
	public BaseResponse searchUser(String name);

	public BaseResponse add(UserRequest request);

	public BaseResponse update(UserRequest request);
}
