package controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class UtilsTests {

	protected final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf-8"));

	protected final MediaType contentTypeForm = new MediaType(MediaType.APPLICATION_FORM_URLENCODED.getType(),
			MediaType.APPLICATION_FORM_URLENCODED.getSubtype(), Charset.forName("utf-8"));

	protected MockHttpSession login(MockMvc mockMvc, String username, String password) throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/login").with(csrf())
				.session(new MockHttpSession())
				.contentType(contentTypeForm)
				.param("username", username)
				.param("password", password)
			)
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/"))
			.andExpect(authenticated().withUsername(username))
			.andReturn();

		return (MockHttpSession) result.getRequest().getSession();
	}

	protected void logout(MockMvc mockMvc) throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/logout"));
		
	}

	 protected static byte[] convertObjectToJsonBytes(Object object) throws IOException {
	        ObjectMapper mapper = new ObjectMapper();
	        //mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	        return mapper.writeValueAsBytes(object);
	    }
	 
	    public static String createStringWithLength(int length) {
	        StringBuilder builder = new StringBuilder();
	 
	        for (int index = 0; index < length; index++) {
	            builder.append("a");
	        }
	 
	        return builder.toString();
	    }

}
