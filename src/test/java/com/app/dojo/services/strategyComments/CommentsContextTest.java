package com.app.dojo.services.strategyComments;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(profiles = "test")
public class CommentsContextTest {
    @InjectMocks
    private CommentsContext commentsContext;

    @DisplayName("Test CommentsContext to get default strategy to find comments")
    @Test
    void loadDefaultStrategy(){
        CommentsStrategy defaultStrategy = this.commentsContext.loadStrategy("DEFAULT");
        assertNotNull(defaultStrategy);
    }

    @DisplayName("Test CommentsContext to get course strategy to find comments")
    @Test
    void loadCourseStrategy(){
        CommentsStrategy courseStrategy = this.commentsContext.loadStrategy("COURSE");
        assertNotNull(courseStrategy);
    }
}
