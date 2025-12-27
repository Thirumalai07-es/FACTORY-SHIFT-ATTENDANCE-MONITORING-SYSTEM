package com.examly.springapp;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

import org.springframework.http.MediaType;



@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpringappApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    // ===================== DAY 3 - Directory Checks =====================
    @Test @Order(1)
    void Day3_test_Controller_Directory_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/controller").isDirectory());
    }

    @Test @Order(2)
    void Day3_test_Model_Directory_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/model").isDirectory());
    }

    @Test @Order(3)
    void Day3_test_Service_Directory_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service").isDirectory());
    }

    @Test @Order(4)
    void Day3_test_Repository_Directory_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/repository").isDirectory());
    }

    // ===================== DAY 4 - Model File Checks =====================
    @Test @Order(5)
    void Day4_test_AdminUser_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/model/AdminUser.java").isFile());
    }

    @Test @Order(6)
    void Day4_test_AttendanceLog_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/model/AttendanceLog.java").isFile());
    }

    @Test @Order(7)
    void Day4_test_AttendanceLogStatus_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/model/AttendanceLogStatus.java").isFile());
    }

    @Test
    @Order(8)
    void Day4_test_Employee_Has_Entity_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.model.Employee");
            Class<?> annotation = Class.forName("jakarta.persistence.Entity");
            assertTrue(clazz.isAnnotationPresent((Class<? extends Annotation>) annotation),
                "❌ @Entity annotation is missing on Employee class");
        } catch (ClassNotFoundException e) {
            fail("❌ Employee class not found.");
        } catch (Exception e) {
            fail("❌ Unable to check @Entity annotation on Employee.");
        }
    }
    
    @Test
    @Order(9)
    void test_Employee_Has_Id_Annotation_On_Field() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.model.Employee");
            Class<?> idAnnotation = Class.forName("jakarta.persistence.Id");
            boolean found = false;
            for (var field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent((Class<? extends Annotation>) idAnnotation)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found, "❌ No field in Employee class is annotated with @Id");
        } catch (ClassNotFoundException e) {
            fail("❌ Employee class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @Id annotation in Employee class.");
        }
    }
    @Test
@Order(10)
void Day4_test_AttendanceRecord_Has_Entity_Annotation() {
    try {
        Class<?> clazz = Class.forName("com.examly.springapp.model.AttendanceRecord");
        Class<?> annotation = Class.forName("jakarta.persistence.Entity");
        assertTrue(clazz.isAnnotationPresent((Class<? extends Annotation>) annotation),
            "❌ @Entity annotation is missing on AttendanceRecord class");
    } catch (ClassNotFoundException e) {
        fail("❌ AttendanceRecord class not found.");
    } catch (Exception e) {
        fail("❌ Unable to check @Entity annotation on AttendanceRecord.");
    }
}

@Test
@Order(11)
void Day4_test_AttendanceRecord_Has_Id_Annotation_On_Field() {
    try {
        Class<?> clazz = Class.forName("com.examly.springapp.model.AttendanceRecord");
        Class<?> idAnnotation = Class.forName("jakarta.persistence.Id");
        boolean found = false;

        for (var field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent((Class<? extends Annotation>) idAnnotation)) {
                found = true;
                break;
            }
        }

        assertTrue(found, "❌ No field in AttendanceRecord class is annotated with @Id");

    } catch (ClassNotFoundException e) {
        fail("❌ AttendanceRecord class not found.");
    } catch (Exception e) {
        fail("❌ Unable to verify @Id annotation in AttendanceRecord class.");
    }
}


    // ===================== DAY 5 - Repository Tests =====================
    // File Exists
  
    @Test @Order(12)
    void Day5_test_AttendanceLogRepository_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/repository/AttendanceLogRepository.java").isFile());
    }

    @Test @Order(13)
    void Day5_test_DailyAttendanceSummaryRepository_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/repository/DailyAttendanceSummaryRepository.java").isFile());
    }

    @Test @Order(14)
    void Day5_test_AttendanceRecordRepository_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/repository/AttendanceRecordRepository.java").isFile());
    }

    // @Repository Annotations
    @Test @Order(15)
    void Day5_test_EmployeeRepository_Has_Repository_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.repository.EmployeeRepository");
            Class<?> annotation = Class.forName("org.springframework.stereotype.Repository");
            assertTrue(clazz.isAnnotationPresent((Class<? extends Annotation>) annotation),
                "❌ @Repository annotation is missing on EmployeeRepository class");
        } catch (Exception e) { fail("❌ Unable to verify @Repository annotation on EmployeeRepository."); }
    }

    @Test @Order(16)
    void Day5_test_AttendanceLogRepository_Has_Repository_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.repository.AttendanceLogRepository");
            Class<?> annotation = Class.forName("org.springframework.stereotype.Repository");
            assertTrue(clazz.isAnnotationPresent((Class<? extends Annotation>) annotation),
                "❌ @Repository annotation is missing on AttendanceLogRepository class");
        } catch (Exception e) { fail("❌ Unable to verify @Repository annotation on AttendanceLogRepository."); }
    }

    @Test @Order(17)
    void Day5_test_AdminUserRepository_Has_Repository_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.repository.AdminUserRepository");
            Class<?> annotation = Class.forName("org.springframework.stereotype.Repository");
            assertTrue(clazz.isAnnotationPresent((Class<? extends Annotation>) annotation),
                "❌ @Repository annotation is missing on AdminUserRepository class");
        } catch (Exception e) { fail("❌ Unable to verify @Repository annotation on AdminUserRepository."); }
    }

    @Test @Order(18)
    void Day5_test_AttendanceRecordRepository_Has_Repository_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.repository.AttendanceRecordRepository");
            Class<?> annotation = Class.forName("org.springframework.stereotype.Repository");
            assertTrue(clazz.isAnnotationPresent((Class<? extends Annotation>) annotation),
                "❌ @Repository annotation is missing on AttendanceRecordRepository class");
        } catch (Exception e) { fail("❌ Unable to verify @Repository annotation on AttendanceRecordRepository."); }
    }

 
    // ===================== DAY 6 - Controller Tests =====================
    @Test @Order(19)
    void Day6_test_AttendanceRecord_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/controller/AttendanceRecordController.java").isFile());
    }

    @Test @Order(20)
    void Day6_test_EmployeeController_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/controller/EmployeeController.java").isFile());
    }

    @Test @Order(21)
    void Day6_test_AttendanceLogController_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/controller/AttendanceLogController.java").isFile());
    }

    // @RestController checks
    @Test @Order(22)
    void Day6_test_AttendanceRecordController_Has_RestController_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.AttendanceRecordController");
            Class<?> annotation = Class.forName("org.springframework.web.bind.annotation.RestController");
            assertTrue(clazz.isAnnotationPresent((Class<? extends Annotation>) annotation),
                "❌ @RestController annotation is missing on AttendanceRecordController class");
        } catch (Exception e) { fail("❌ Unable to verify @RestController annotation on AttendanceRecordController."); }
    }

    @Test @Order(23)
    void Day6_test_EmployeeController_Has_RestController_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.EmployeeController");
            Class<?> annotation = Class.forName("org.springframework.web.bind.annotation.RestController");
            assertTrue(clazz.isAnnotationPresent((Class<? extends Annotation>) annotation),
                "❌ @RestController annotation is missing on EmployeeController class");
        } catch (Exception e) { fail("❌ Unable to verify @RestController annotation on EmployeeController."); }
    }

    @Test @Order(24)

    void Day6_test_DailyAttendanceSummaryController_Has_PostMapping() {
    try {
        // Load the controller class
        Class<?> clazz = Class.forName("com.examly.springapp.controller.DailyAttendanceSummaryController");

        // Load @PostMapping annotation dynamically
        Class<?> postMapping = Class.forName("org.springframework.web.bind.annotation.PostMapping");

        boolean found = false;

        // Check if any method has @PostMapping
        for (java.lang.reflect.Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent((Class<? extends Annotation>) postMapping)) {
                found = true;
                break;
            }
        }

        assertTrue(found, "❌ No method with @PostMapping found in DailyAttendanceSummaryController");

    } catch (ClassNotFoundException e) {
        fail("❌ DailyAttendanceSummaryController class not found.");
    } catch (Exception e) {
        fail("❌ Unable to verify @PostMapping annotation in DailyAttendanceSummaryController.");
    }
}


    @Test @Order(25)

    void Day6_test_AdminUserController_Has_GetMapping() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.AdminUserController");
            Class<?> getMapping = Class.forName("org.springframework.web.bind.annotation.GetMapping");

            boolean found = false;
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent((Class<? extends Annotation>) getMapping)) {
                    found = true;
                    break;
                }
            }

            assertTrue(found, "❌ No @GetMapping method found in AdminUserController");

        } catch (ClassNotFoundException e) {
            fail("❌ AdminUserController class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @GetMapping in AdminUserController.");
        }
    }


    

    // ===================== DAY 7 - @RequestMapping Tests =====================

@Test
@Order(26)
void Day7_test_AttendanceLogController_Has_RequestMapping() {
    try {
        Class<?> clazz = Class.forName("com.examly.springapp.controller.AttendanceLogController");
        Class<?> requestMapping = Class.forName("org.springframework.web.bind.annotation.RequestMapping");

        boolean found = clazz.isAnnotationPresent((Class<? extends Annotation>) requestMapping);
        for (Method m : clazz.getDeclaredMethods()) {
            if (m.isAnnotationPresent((Class<? extends Annotation>) requestMapping)) {
                found = true;
                break;
            }
        }

        assertTrue(found, "❌ No @RequestMapping found on AttendanceLogController (class or methods)");

    } catch (ClassNotFoundException e) {
        fail("❌ AttendanceLogController class not found.");
    } catch (Exception e) {
        fail("❌ Unable to verify @RequestMapping in AttendanceLogController.");
    }
}

@Test
@Order(27)
void Day7_test_EmployeeController_Has_RequestMapping() {
    try {
        Class<?> clazz = Class.forName("com.examly.springapp.controller.EmployeeController");
        Class<?> requestMapping = Class.forName("org.springframework.web.bind.annotation.RequestMapping");

        boolean found = clazz.isAnnotationPresent((Class<? extends Annotation>) requestMapping);
        for (Method m : clazz.getDeclaredMethods()) {
            if (m.isAnnotationPresent((Class<? extends Annotation>) requestMapping)) {
                found = true;
                break;
            }
        }

        assertTrue(found, "❌ No @RequestMapping found on EmployeeController (class or methods)");

    } catch (ClassNotFoundException e) {
        fail("❌ EmployeeController class not found.");
    } catch (Exception e) {
        fail("❌ Unable to verify @RequestMapping in EmployeeController.");
    }
}

@Test
@Order(28)
void Day7_test_AdminUserController_Has_RequestMapping() {
    try {
        Class<?> clazz = Class.forName("com.examly.springapp.controller.AdminUserController");
        Class<?> requestMapping = Class.forName("org.springframework.web.bind.annotation.RequestMapping");

        boolean found = clazz.isAnnotationPresent((Class<? extends Annotation>) requestMapping);
        for (Method m : clazz.getDeclaredMethods()) {
            if (m.isAnnotationPresent((Class<? extends Annotation>) requestMapping)) {
                found = true;
                break;
            }
        }

        assertTrue(found, "❌ No @RequestMapping found on AdminUserController (class or methods)");

    } catch (ClassNotFoundException e) {
        fail("❌ AdminUserController class not found.");
    } catch (Exception e) {
        fail("❌ Unable to verify @RequestMapping in AdminUserController.");
    }
}

@Test
@Order(29)
void Day7_test_AttendanceRecordController_Has_RequestMapping() {
    try {
        Class<?> clazz = Class.forName("com.examly.springapp.controller.AttendanceRecordController");
        Class<?> requestMapping = Class.forName("org.springframework.web.bind.annotation.RequestMapping");

        boolean found = clazz.isAnnotationPresent((Class<? extends Annotation>) requestMapping);
        for (Method m : clazz.getDeclaredMethods()) {
            if (m.isAnnotationPresent((Class<? extends Annotation>) requestMapping)) {
                found = true;
                break;
            }
        }

        assertTrue(found, "❌ No @RequestMapping found on AttendanceRecordController (class or methods)");

    } catch (ClassNotFoundException e) {
        fail("❌ AttendanceRecordController class not found.");
    } catch (Exception e) {
        fail("❌ Unable to verify @RequestMapping in AttendanceRecordController.");
    }
}

@Test
@Order(30)
void Day7_test_DailyAttendanceSummaryController_Has_RequestMapping() {
    try {
        Class<?> clazz = Class.forName("com.examly.springapp.controller.DailyAttendanceSummaryController");
        Class<?> requestMapping = Class.forName("org.springframework.web.bind.annotation.RequestMapping");

        boolean found = clazz.isAnnotationPresent((Class<? extends Annotation>) requestMapping);
        for (Method m : clazz.getDeclaredMethods()) {
            if (m.isAnnotationPresent((Class<? extends Annotation>) requestMapping)) {
                found = true;
                break;
            }
        }

        assertTrue(found, "❌ No @RequestMapping found on DailyAttendanceSummaryController (class or methods)");

    } catch (ClassNotFoundException e) {
        fail("❌ DailyAttendanceSummaryController class not found.");
    } catch (Exception e) {
        fail("❌ Unable to verify @RequestMapping in DailyAttendanceSummaryController.");
    }
}
@Test
@Order(31)
void Day7_test_AdminUserController_Has_PathVariable() {
    try {
        Class<?> clazz = Class.forName("com.examly.springapp.controller.AdminUserController");
        Class<?> pathVariable = Class.forName("org.springframework.web.bind.annotation.PathVariable");

        boolean found = false;

        for (Method method : clazz.getDeclaredMethods()) {
            for (Parameter param : method.getParameters()) {
                if (param.isAnnotationPresent((Class<? extends Annotation>) pathVariable)) {
                    found = true;
                    break;
                }
            }
            if (found) break;
        }

        assertTrue(found, "❌ No @PathVariable annotation found in any method parameter of AdminUserController");

    } catch (ClassNotFoundException e) {
        fail("❌ AdminUserController class not found.");
    } catch (Exception e) {
        fail("❌ Unable to verify @PathVariable in AdminUserController.");
    }
}

@Test
	@Order(32)
    void Day7_test_EmployeeController_Has_PathVariable() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.EmployeeController");
            Class<?> pathVariable = Class.forName("org.springframework.web.bind.annotation.PathVariable");

            boolean found = false;

            for (Method method : clazz.getDeclaredMethods()) {
                for (Parameter param : method.getParameters()) {
                    if (param.isAnnotationPresent((Class<? extends Annotation>) pathVariable)) {
                        found = true;
                        break;
                    }
                }
                if (found) break;
            }

            assertTrue(found, "❌ No @PathVariable found in any method parameter of EmployeeController");

        } catch (ClassNotFoundException e) {
            fail("❌ EmployeeController class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @PathVariable in EmployeeController.");
        }
    }

    // ===================== DAY 8 - Service / ServiceImpl File Checks =====================
    @Test @Order(33)
    void Day8_test_AttendanceLogService_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/AttendanceLogService.java").isFile());
    }

    @Test @Order(34)
    void Day8_test_AdminUserService_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/AdminUserService.java").isFile());
    }

    @Test @Order(35)
    void Day8_test_DailyAttendanceSummaryService_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/DailyAttendanceSummaryService.java").isFile());
    }

    @Test @Order(36)
    void Day8_test_AttendanceRecordService_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/AttendanceRecordService.java").isFile());
    }

    @Test @Order(37)
    void Day8_test_EmployeeService_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/EmployeeService.java").isFile());
    }

    @Test @Order(38)
    void Day8_test_AttendanceLogServiceImpl_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/AttendanceLogServiceImpl.java").isFile());
    }

    @Test @Order(39)
    void Day8_test_EmployeeServiceImpl_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/EmployeeServiceImpl.java").isFile());
    }

// ===================== DAY 9 - DailyAttendanceSummaryController Pagination Tests =====================

// Check pageNumber
@Test
@Order(40)
public void Day9_testPagination_PageNumberApplied() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/daily-summary/employee/1")
            .param("page", "0")
            .param("size", "5")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.pageable.pageNumber").value(0));
}


// Check pageSize
@Test
@Order(41)
public void Day9_testPagination_PageSizeApplied() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/daily-summary/employee/code/EMP001")
            .param("startDate", "2025-01-01")
            .param("endDate", "2025-12-31")
            .param("page", "0")
            .param("size", "10")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.pageable.pageSize").value(10));
}


// Check sorting object exists
@Test
@Order(42)
public void Day9_testPagination_SortingPresent() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/daily-summary/employee/code/EMP001")
            .param("startDate", "2025-01-01")
            .param("endDate", "2025-12-31")
            .param("page", "0")
            .param("size", "5")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.pageable.sort").exists())
            .andExpect(jsonPath("$.pageable.sort.sorted").isBoolean());
}


// Check content array exists
@Test
@Order(43)
public void Day9_testPagination_ContentArrayExists() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/daily-summary/employee/code/EMP001")
            .param("startDate", "2025-01-01")
            .param("endDate", "2025-12-31")
            .param("page", "0")
            .param("size", "5")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content").isArray());
}


// Check totalElements exists
@Test
@Order(44)
public void Day9_testPagination_TotalElementsExists() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/daily-summary/employee/code/EMP001")
            .param("startDate", "2025-01-01")
            .param("endDate", "2025-12-31")
            .param("page", "0")
            .param("size", "5")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.totalElements").exists());
}


// Check totalPages exists
@Test
@Order(45)
public void Day9_testPagination_TotalPagesExists() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/daily-summary/employee/code/EMP001")
            .param("startDate", "2025-01-01")
            .param("endDate", "2025-12-31")
            .param("page", "0")
            .param("size", "5")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.totalPages").exists());
}

 
// ===================== DAY 10 - EmployeeController & AttendanceLogController CRUD =====================

// POST /employees (Add Employee)
@Test
@Order(46)
public void Day10_testAddEmployee() throws Exception {
    String requestBody = "{ " +
            "\"empCode\": \"EMP001\", " +
            "\"name\": \"John Employee\", " +
            "\"email\": \"john.employee@example.com\", " +
            "\"department\": \"HR\"" +
            "}";

    mockMvc.perform(MockMvcRequestBuilders.post("/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.empCode").value("EMP001"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Employee"));
}


// GET /employees/{id}
@Test @Order(47)
public void Day10_testGetEmployeeById() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/employees/1")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.empCode").value("EMP001"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Employee"));
}


// PUT /employees/{id} (Update Employee)
@Test
@Order(48)
public void Day10_testUpdateEmployee() throws Exception {
    String requestBody = "{ " +
            "\"empCode\": \"EMP001\", " +
            "\"name\": \"John Updated\", " +
            "\"email\": \"updated.employee@example.com\", " +
            "\"department\": \"Finance\"" +
            "}";

    mockMvc.perform(MockMvcRequestBuilders.put("/employees/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Updated"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.department").value("Finance"));
}

// POST /attendance (Add Attendance Log)
@Test
@Order(49)
public void Day10_testAddAttendanceLog() throws Exception {
    String requestBody = "{ " +
            "\"employeeCode\": \"EMP001\", " +
            "\"date\": \"2025-01-01\", " +
            "\"checkInTime\": \"09:00:00\" " +
            "}";

    mockMvc.perform(MockMvcRequestBuilders.post("/attendancelogs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isCreated());
}


//PUT /attendance/{id} (Update Attendance Log)
@Test
@Order(50)
public void Day10_testUpdateAttendanceLog() throws Exception {
    String requestBody = "{ " +
            "\"employeeCode\": \"EMP001\", " +
            "\"date\": \"2025-01-01\", " +
            "\"checkOutTime\": \"18:00:00\" " +
            "}";

    mockMvc.perform(MockMvcRequestBuilders.put("/attendancelogs/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.checkOutTime").value("18:00:00"));
}

//GET /employees (Get All Employees)
@Test @Order(51)
public void Day10_testGetAllEmployees() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/employees")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
            .andExpect(MockMvcResultMatchers.jsonPath("$.length()").isNotEmpty());
}
//GET /attendance (Get All Attendance Logs)
@Test
@Order(52)
public void Day10_testGetAllAttendanceLogs() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/attendancelogs")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
            .andExpect(MockMvcResultMatchers.jsonPath("$.length()").isNotEmpty());
}



// ===================== DAY 11 - DailyAttendanceSummaryController Exception =====================

@Test
@Order(53)
public void testGetSummaryByEmployeeCodeAndDateRange_Positive() throws Exception {

    // 1. Create employee
    String empJson = "{ \"empCode\": \"EMP9001\", \"name\": \"Test User\" }";

    MvcResult empResult = mockMvc.perform(MockMvcRequestBuilders.post("/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(empJson)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();

    Long empId = new ObjectMapper()
            .readTree(empResult.getResponse().getContentAsString())
            .get("id").asLong();

    // 2. Create Daily Summary
    String summaryJson =
            "{ \"employeeCode\": \"EMP9001\", \"date\": \"2025-01-10\" }";

    mockMvc.perform(MockMvcRequestBuilders.post("/daily-summary/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(summaryJson)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());

    // 3. Test JPQL endpoint
    mockMvc.perform(MockMvcRequestBuilders.get("/daily-summary/employee/code/EMP9001")
            .param("startDate", "2025-01-01")
            .param("endDate", "2025-01-31")
            .param("page", "0")
            .param("size", "10")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.content.length()").value(1));
}

@Test
@Order(54)
public void testGetSummaryByEmployeeCodeAndDateRange_NoRecords() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.get("/daily-summary/employee/code/INVALID100")
            .param("startDate", "2025-01-01")
            .param("endDate", "2025-01-31")
            .param("page", "0")
            .param("size", "10")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.content.length()").value(0));
}


// ===================== DAY 12 - AdminUserController & AttendanceRecord CRUD =====================
 
// GET /appointments/{id}
@Test @Order(55)
public void testAddAdmin() throws Exception {
    String requestBody = "{ \"username\": \"admin1\", \"password\": \"pass123\" }";

    mockMvc.perform(MockMvcRequestBuilders.post("/admin/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("admin1"));
}

// PUT /appointments/{id}
@Test @Order(56)
public void testGetAdminById() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/admin/1")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.username").exists());
}
@Test @Order(57)
public void testUpdateAdmin() throws Exception {
    String requestBody = "{ \"username\": \"admin1-updated\", \"password\": \"pass456\" }";

    mockMvc.perform(MockMvcRequestBuilders.put("/admin/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("admin1-updated"));
}

@Test @Order(58)
public void testGetAllAdmins() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/admin")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
}


// Day - 13------------------- GLOBAL EXCEPTION HANDLER FILE -------------------

@Test @Order(59)
void Day13_testExceptionDirectoryExists() {
    assertTrue(new File("src/main/java/com/examly/springapp/exception").isDirectory(),
               "Exception directory should exist");
}
@Test @Order(60)
void Day13_testGlobalExceptionFileExists() {
    assertTrue(new File("src/main/java/com/examly/springapp/exception/GlobalExceptionHandler.java").isFile(),
               "GlobalExceptionHandler.java file should exist");
}


// Day - 14------------------- Configuration HANDLER FILE -------------------
@Test @Order(61)
    void Day14test_configuartion_Directory_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/configuration").isDirectory());
    }


// Day - 15------------------- AOP HANDLER FILE -------------------
@Test @Order(62)
public void Day15_testAOPLogFileExists() {
    assertTrue(new File("src/main/java/com/examly/springapp/aop").isDirectory()); 
}
}
