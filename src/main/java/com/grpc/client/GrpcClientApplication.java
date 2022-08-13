
package com.grpc.client;

import com.grpc.client.proto.UserServiceGrpc;
import com.grpc.client.proto.UserDetails;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

//@SpringBootApplication 
public class GrpcClientApplication {

	public static void main(String[] args) { //
		// SpringApplication.run(GrpcClientApplication.class, args);

		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build();
		UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub = UserServiceGrpc.newBlockingStub(channel);

		UserDetails.UserResponse userResponse = userServiceBlockingStub
				.searchUser(UserDetails.UserRequest.newBuilder().setName("Hanmant").build());
		System.out.println(userResponse.getName());

		channel.shutdown();
	}

}
