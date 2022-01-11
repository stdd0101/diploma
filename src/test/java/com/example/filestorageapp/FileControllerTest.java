package com.example.filestorageapp;

import com.example.filestorageapp.api.FileController;
import com.example.filestorageapp.domain.FileDTO;
import com.example.filestorageapp.service.StorageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FileController.class)
public class FileControllerTest {

    @MockBean
    private StorageServiceImpl service;

    @Autowired
    private MockMvc mockMvc;

    private List<FileDTO> fileList;

    @BeforeEach
    void setUp() {
        this.fileList = new ArrayList<>();
        this.fileList.add(new FileDTO("1638995299458_5.jpg", 9112));
        this.fileList.add(new FileDTO("newName.jpg", 88333));
        this.fileList.add(new FileDTO("1641404967079_240_F_378248025_94Yty6t31BKKtn1fJ8HMImxpgjWCKJRJ.jpg", 26548));
    }

    @Test
    @WithMockUser(username = "stdd01@gmail.com", password = "stzv78@yandex.ru", roles = "USER")
    void uploadFileTest() throws Exception {
        MockMultipartFile file
                = new MockMultipartFile(
                "filename",
                "original-name.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Test uploaded file content".getBytes()
        );

        String url = "http://localhost:9900/filestorage-pub/file.txt";
        when(service.upload(file)).thenReturn(url);

        mockMvc.perform(multipart("/cloud/file")
                .file(file))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("Success upload.")));
    }

    @Test
    @WithMockUser(username = "stdd01@gmail.com", password = "stzv78@yandex.ru", roles = "USER")
    void deleteFileTest() throws Exception {

        doNothing().when(service).delete(anyString());

        String filename = "newName.jpg";

        mockMvc.perform(delete("/cloud/file/" + filename))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("Success deleted.")));
    }

    @Test
    @WithMockUser(username = "stdd01@gmail.com", password = "stzv78@yandex.ru", roles = "USER")
    void downloadFileTest() throws Exception {
        String fileContent = "File download test";

        when(service.download(anyString())).thenReturn(fileContent.getBytes());

        mockMvc.perform(get("/cloud/file/newName.jpg"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().bytes(fileContent.getBytes()))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_OCTET_STREAM));
    }

    @Test
    @WithMockUser(username = "stdd01@gmail.com", password = "stzv78@yandex.ru", roles = "USER")
    void editFilenameTest() throws Exception {

        String oldFileName = fileList.get(0).getName();
        String newName = "newName.jpg";

        doNothing().when(service).renameFile(oldFileName, newName);

        this.mockMvc.perform(put("/cloud/file/" + oldFileName)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .param("name", newName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("Success renamed.")));
    }

    @Test
    @WithMockUser(username = "stdd01@gmail.com", password = "stzv78@yandex.ru", roles = "USER")
    void getAllFilesTest() throws Exception {
        given(service.getAll()).willReturn(fileList);

        this.mockMvc.perform(get("/cloud/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(fileList.size())));
    }

}
