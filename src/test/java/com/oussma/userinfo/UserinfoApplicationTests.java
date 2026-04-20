package com.oussma.userinfo;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserinfoApplicationTests {

//	@Test
//	void contextLoads() {
//	}
	 @Test
	    void mainMethodRuns() {
	        // Verifies that the main method runs without throwing exceptions
	        UserinfoApplication.main(new String[]{});
	    }
}
