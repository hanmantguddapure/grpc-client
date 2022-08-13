
package com.grpc.client.service;

import org.springframework.stereotype.Service;

import com.grpc.client.model.BaseResponse;
import com.grpc.client.model.UserRequest;
import com.grpc.client.proto.UserDetails;
import com.grpc.client.proto.UserServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public BaseResponse searchUser(String name) {

		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build();

		UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub = UserServiceGrpc.newBlockingStub(channel);

		UserDetails.UserResponse userResponse = userServiceBlockingStub
				.searchUser(UserDetails.UserRequest.newBuilder().setName(name).build());
		channel.shutdown();
		return BaseResponse.builder().message(userResponse.getName()).id(userResponse.getUserId()).build();
	}

	@Override
	public BaseResponse add(UserRequest request) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build();

		UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub = UserServiceGrpc.newBlockingStub(channel);

		UserDetails.Response response = userServiceBlockingStub
				.insertRecord(UserDetails.UserRequest.newBuilder().setName(request.getUserName()).build());
		channel.shutdown();
		return BaseResponse.builder().message(response.getMessage()).build();
	}

	@Override
	public BaseResponse update(UserRequest request) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build();

		UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub = UserServiceGrpc.newBlockingStub(channel);

		UserDetails.Response response = userServiceBlockingStub.updateRecord(UserDetails.UserRequest.newBuilder()
				.setName(request.getUserName()).setUserId(request.getUserId()).build());
		channel.shutdown();
		return BaseResponse.builder().message(response.getMessage()).build();
	}

}
