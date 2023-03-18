package com.app.dojo.services.strategyGroups;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(profiles = "test")
class GroupsContextTest {
  @InjectMocks
  private GroupsContext groupsContext;

  @Test
  @DisplayName("Test GroupsContext, Test to load default strategy")
  void loadDefaultStrategy(){
    GroupsStrategy defaultStrategy=this.groupsContext.loadStrategy("DEFAULT");
    assertNotNull(defaultStrategy);
  }

  @Test
  @DisplayName("Test GroupsContext, Test to load room strategy")
  void loadRoomStrategy(){
    GroupsStrategy defaultStrategy=this.groupsContext.loadStrategy("ROOM");
    assertNotNull(defaultStrategy);
  }

  @Test
  @DisplayName("Test GroupsContext, Test to load schedule strategy")
  void loadScheduleStrategy(){
    GroupsStrategy defaultStrategy=this.groupsContext.loadStrategy("SCHEDULE");
    assertNotNull(defaultStrategy);
  }

  @Test
  @DisplayName("Test GroupsContext, Test to load course strategy")
  void loadCourseStrategy(){
    GroupsStrategy defaultStrategy=this.groupsContext.loadStrategy("COURSE");
    assertNotNull(defaultStrategy);
  }
}