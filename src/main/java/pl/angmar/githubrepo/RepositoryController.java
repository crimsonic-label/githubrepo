package pl.angmar.githubrepo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RepositoryController {

    private RepoInfoService repoInfoService;
    private static final String PATH = "/error";

    public RepositoryController(RepoInfoService repoInfoService) {
        this.repoInfoService = repoInfoService;
    }

    @RequestMapping("/repositories/{owner}/{repositoryName}")
    public RepositoryInfo getRepositoryInfo(@PathVariable String owner, @PathVariable String repositoryName){
        return repoInfoService.getInfo(owner, repositoryName);
    }
}
