package helloTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import hello.Application;
import hello.MyList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class HelloControllerTest {

	final String BASE_URL = "/Hello";
	final String TEST_STRING = "Test";
	final String RETURN_STRING = "Super";

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void HelloTest() {

		ResponseEntity<String> response = restTemplate.getForEntity(BASE_URL,
				String.class);
		assertEquals("Hello from Spring Boot!", response.getBody());
		assertNotEquals("", response.getBody());
	}

	@SuppressWarnings("unchecked")
	@Test(expected = IllegalStateException.class)
	public void givenMethodIsConfiguredToThrowException_whenCallingMethod_thenExceptionIsThrown() {

		MyList listMock = Mockito.mock(MyList.class);

		// This Test is useless, it only demonstrates how the thenThrow Mocking
		// works
		when(listMock.add(anyString())).thenThrow(IllegalStateException.class);

		listMock.add(TEST_STRING);
	}

	@Test
	public void givenMethodIsConfiguredToReturnString_whenCallingMethod_thenStringIsReturned() {

		MyList listMock = Mockito.mock(MyList.class);

		when(listMock.add(anyString())).thenReturn(RETURN_STRING);

		assertEquals(listMock.add(TEST_STRING), RETURN_STRING);
	}
}
