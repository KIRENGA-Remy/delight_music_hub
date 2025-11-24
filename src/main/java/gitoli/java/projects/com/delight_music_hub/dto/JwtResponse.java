package gitoli.java.projects.com.delight_music_hub.dto;

import lombok.Data;

import java.util.Set;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String id;
    private String username;
    private String email;
    private Set<String> roles;
}
