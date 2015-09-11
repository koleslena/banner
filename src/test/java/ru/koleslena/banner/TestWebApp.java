package ru.koleslena.banner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.koleslena.banner.config.AppTestConfig;
import ru.koleslena.banner.config.WebConfig;
import ru.koleslena.banner.service.RandomService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @since 10.09.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppTestConfig.class, WebConfig.class})
@WebAppConfiguration
public class TestWebApp {

    protected static final String JSON_UTF_8 = "application/json;charset=UTF-8";

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void validateGetBanner() throws Exception {

        mockMvc.perform(get("/banner/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(JSON_UTF_8))
                .andExpect(jsonPath("$.name").value("name 1"));

    }

    @Test
    public void validateGetNextBanner() throws Exception {

        mockMvc.perform(get("/banner/next/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(JSON_UTF_8))
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.id").exists());

    }

}