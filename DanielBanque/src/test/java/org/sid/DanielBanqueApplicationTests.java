package org.sid;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DanielBanqueApplicationTests {

	/*@Test
	public void contextLoads() {
	}*/

	//Ce test unitaire de JUnit nous permet de voir si la base de données a été créée ou non
		@Test
		public void testVerificationCreationBD() {
			try{
				ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"} );
				assertTrue(true);
			}catch (Exception e) {
				assertTrue(e.getMessage(), false);
			}
		}
	
}
