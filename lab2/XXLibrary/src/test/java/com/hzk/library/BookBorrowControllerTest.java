/**
 * 
 */
package com.hzk.library;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author kecheng
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookBorrowControllerTest {

	@Autowired
	private WebApplicationContext wac;
	 
	private MockMvc mocmvc;
	
	@Before
	public void setUp() throws Exception {
		mocmvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void getBookNotReturnedTest() throws Exception {
		String result=mocmvc.perform(get("/bookreturn/1017")
		      .contentType(MediaType.APPLICATION_JSON_UTF8))
			  .andExpect(status().isOk())
			  .andExpect(jsonPath("$.length()").value(12))
			  .andReturn().getResponse().getContentAsString();  //12个字段
		System.out.println(result);
	}

}
