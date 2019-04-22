package pl.angmar.githubrepo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class RepositoryInfo {
    @JsonProperty(value="full_name")
    private String fullName;
    private String description;
    @JsonProperty(value="clone_url")
    private String cloneUrl;
    @JsonProperty(value="stargazers_count")
    private Long stars;
    @JsonProperty(value="created_at")
    private Date createdAt;
}
