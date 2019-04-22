package pl.angmar.githubrepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RepoInfoService {

    private static final Logger logger = LoggerFactory.getLogger(RepoInfoService.class);

    public RepositoryInfo getInfo(String owner, String repositoryName){
        RestTemplate restTemplate = new RestTemplate();
        try {
            RepositoryInfo repoinfo = restTemplate.getForObject(
                    "https://api.github.com/repos/" + owner + "/" + repositoryName, RepositoryInfo.class);
            logger.info(repoinfo.toString());
            return repoinfo;

        } catch(HttpClientErrorException e) {
            logger.error("Error reading {}, {} repository info", owner, repositoryName, e);
            throw new ResponseStatusException(HttpStatus.valueOf(e.getRawStatusCode()),
                        "Repository " + owner + "/" + repositoryName + " " + e.getMessage(), e);
        }
    }
}
