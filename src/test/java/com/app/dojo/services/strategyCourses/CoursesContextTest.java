package com.app.dojo.services.strategyCourses;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@ActiveProfiles(profiles = "test")
class CoursesContextTest {
  @InjectMocks
  private CoursesContext coursesContext;

  @Test
  @DisplayName("Test CoursesContext, Test to get default strategy to find courses")
  void loadDefaultStrategy(){
    CoursesStrategy levelStrategy=coursesContext.loadStrategy("DEFAULT");
    assertNotNull(levelStrategy);
  }
}