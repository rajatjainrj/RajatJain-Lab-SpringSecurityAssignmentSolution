package xyz.rajatjain.collegefestdebatesecured;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import xyz.rajatjain.collegefestdebatesecured.service.PostStartupScript;

@Slf4j
@SpringBootApplication
public class CollegeFestDebateSecuredApplication {

    private PostStartupScript postStartupScript;

    @Autowired
    public void setPostStartupScript(PostStartupScript postStartupScript) {
        this.postStartupScript = postStartupScript;
    }

    public static void main(String[] args) {
        SpringApplication.run(CollegeFestDebateSecuredApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        log.info("College Fest Debate Student Management System just started up!");
        postStartupScript.initiateRolesAndDefaultUsers();
    }
}
