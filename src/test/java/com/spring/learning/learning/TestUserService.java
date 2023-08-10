package com.spring.learning.learning;

import com.spring.learning.learning.entitys.EnUser;
import com.spring.learning.learning.exceptions.BaseException;
import com.spring.learning.learning.models.request.RequestUser;
import com.spring.learning.learning.services.SUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestUserService {

	@Autowired
	private SUser sUser;
	interface TestData {
		public String email = "somepong@2j.com";
		public String password = "1234";
		public String name = "sompong";
	}
//	@Test
//	void testCreate() throws BaseException {
//		RequestUser request = new RequestUser();
//		request.setEmail(TestData.email);
//		request.setPassword(TestData.password);
//		request.setName(TestData.name);
//		EnUser user = sUser.create((RequestUser) request);
//
//		Assertions.assertNotNull(user);
//		Assertions.assertEquals("sompong1",user.getName());
//	}


}
