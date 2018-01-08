
package app;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "logging.level.org.springframework.web=DEBUG")
@AutoConfigureMockMvc
public class ApplicationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void rendersForm() throws Exception {
		mockMvc.perform(get("/greeting")).andExpect(content().string(containsString("Form")));
	}

	@Test
	public void submitsForm() throws Exception {
		mockMvc.perform(post("/greeting").param("id", "12345").param("content", "Hello"))
				.andExpect(content().string(containsString("Result")))
				.andExpect(content().string(containsString("id: 12345")));
	}

}
