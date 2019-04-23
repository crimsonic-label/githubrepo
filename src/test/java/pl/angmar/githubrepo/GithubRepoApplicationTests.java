package pl.angmar.githubrepo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
		classes = GithubRepoApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GithubRepoApplicationTests {

	@LocalServerPort
	private int port;

	private TestRestTemplate restTemplate = new TestRestTemplate();
	private HttpHeaders headers = new HttpHeaders();

    @Test
    public void shouldGetInfoForExistingRepository() throws Exception {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<RepositoryInfo> response = restTemplate.exchange(
                createURLWithPort("/repositories/crimsonic-label/eventlog"),
                HttpMethod.GET, entity, RepositoryInfo.class);

        assertNotNull("The response should has a body", response.getBody());
        assertEquals("Wrong repository full name", "crimsonic-label/eventlog", response.getBody().getFullName());
        assertEquals("Wrong repository description", "Event log loading to HSQLDB", response.getBody().getDescription());
        assertEquals("Wrong repository clone url", "https://github.com/crimsonic-label/eventlog.git", response.getBody().getCloneUrl());
        assertEquals("Wrong repository clone url", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-12-16 18:19:40"), response.getBody().getCreatedAt());
        assertTrue("Repository can has stars", response.getBody().getStars()>=0);
    }

    @Test
    public void shouldShowErrorForNonExistingRepository() {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<RepositoryInfo> response = restTemplate.exchange(
                createURLWithPort("/repositories/crimsonic-label/-non-existing-"),
                HttpMethod.GET, entity, RepositoryInfo.class);

        assertEquals("Should return not found 404 error status", HttpStatus.NOT_FOUND, response.getStatusCode());
    }


    private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
