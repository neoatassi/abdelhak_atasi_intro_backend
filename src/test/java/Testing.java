import com.gradle.code.*;
import com.gradle.code.exceptions.FloorDoesNotExist;
import com.gradle.code.exceptions.ProjectDoesNotExist;
import com.gradle.code.exceptions.RoomDoesNotExist;
import com.gradle.code.repos.HashProjectsRepo;
import com.gradle.code.services.ProjectServiceImp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Testing {
    ProjectServiceImp manager;

    @BeforeEach
    void setUp(){
        manager = new ProjectServiceImp(new HashProjectsRepo());
    }

    @AfterEach
    void clearIDs(){
        manager.resetID();
    }

    @Test
    void testCreateProject(){
        Project testProject = manager.createProject("1st project");
        assertNotNull(manager);
        assertEquals(0, testProject.getProjectId());
        assertNotNull(testProject.getFloors());
        assertNotNull(testProject.getFloor(0).getRooms());
    }

    @Test
    void testAddFloorToProject(){
        Project testProject = manager.createProject("Test project");
        manager.addFloorToProject(testProject.getProjectId());
        manager.addFloorToProject(testProject.getProjectId());
        assertEquals(3, testProject.getFloors().size());
        assertThrows(ProjectDoesNotExist.class, ()-> manager.addFloorToProject(6));
    }

    @Test
    void testRemoveProject() throws ProjectDoesNotExist{
        Project testProject = manager.createProject("1st project");
        manager.removeProject(testProject.getProjectId());
        assertEquals(0, manager.getProjects().size());
        assertThrows(ProjectDoesNotExist.class, () ->
               manager.removeProject(3));
    }

    @Test
    void testPrintOutProjects(){
        Project testProject1 = manager.createProject("1st project");
        Project testProject2 = manager.createProject("2nd project");
        manager.printOutProjects();
    }

    @Test
    void testRemoveLastFloor(){
        Project testProject = manager.createProject("Test project");
        manager.addFloorToProject(testProject);
        manager.addFloorToProject(testProject);
        manager.addFloorToProject(testProject);
        manager.removeFloor(testProject, 3);
        assertEquals(3, testProject.getFloors().size());
        assertThrows(FloorDoesNotExist.class, ()-> manager.removeFloor(testProject, 6));
    }

    @Test
    void testFloorLevels(){
        Project testProject = manager.createProject("Test project");
        manager.addFloorToProject(testProject);
        manager.addFloorToProject(testProject);
        manager.addFloorToProject(testProject);
        assertEquals(2, testProject.getFloors().get(2).getFloorLevel());
    }

    @Test
    void testAddRoomToFloor(){
        Project testProject = manager.createProject("Test project");
        manager.FloorService.addRoomToFloor(testProject, 0);
        manager.FloorService.addRoomToFloor(testProject, 0);
        assertEquals(3, testProject.getFloor(0).getRooms().size());
    }

    @Test
    void testRemoveRoomFromFloor(){
        Project testProject = manager.createProject("Test project");
        manager.FloorService.addRoomToFloor(testProject, 0);
        manager.FloorService.addRoomToFloor(testProject, 0);
        assertEquals(3, testProject.getFloor(0).getRooms().size());
        manager.FloorService.removeRoomFromFloor(testProject, 0, 2);
        assertEquals(2, testProject.getFloor(0).getRooms().size());
        assertThrows(RoomDoesNotExist.class, ()->
                manager.FloorService.removeRoomFromFloor(testProject, 0, 6));
    }

    @Test
    void testAddMediaToRoom(){
        Project testProject = manager.createProject("Test project");
        String text = "Howdy!";
        manager.FloorService.RoomService.addMediaToRoom(testProject, 0, 0, text);
        manager.FloorService.RoomService.addMediaToRoom(testProject, 0, 0, "there");
        manager.FloorService.RoomService.addMediaToRoom(testProject, 0, 0, "partner :)");
        System.out.println(testProject.getFloor(0).getRoom(0).getMedia());
    }



}
