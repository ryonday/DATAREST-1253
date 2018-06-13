package com.ryonday.sdrputisbroke;

import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
public class SdrPutIsBrokeApplicationTests {

    @Autowired
    MockMvc mvc;

    @BeforeClass
    public static void setUpSQLitePath() throws Exception {
        log.info("Setting up SQLLite Library Path...");
        System.setProperty("sqlite4java.library.path", "build/sqlite4-libs");
        System.setProperty("java.library.path", "build/sqlite4-libs");
    }

    @Test
    public void put_is_broken() throws Exception {
        this.mvc.perform(
            post("/thingies")
                .contentType(APPLICATION_JSON)
                .content("{\n" +
                    "\t\"foo\":\"foo\",\n" +
                    "\t\"bar\":\"bar\",\n" +
                    "\t\"some_data\":\"data2\",\n" +
                    "\t\"more_data\":\"data also2!\"\n" +
                    "}"))
            .andExpect(status().isCreated());

        this.mvc.perform(put("/thingies/foo-bar")
            .contentType(APPLICATION_JSON)
            .content("{\n" +
                "\t\"some_data\":\"data3\",\n" +
                "\t\"more_data\":\"data also3!\"\n" +
                "}")).andExpect(status().isAccepted());
    }

}
