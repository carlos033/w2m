package com.w2m.aplicacion.api;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.context.request.NativeWebRequest;

import com.w2m.presentacion.api.ApiUtil;

import jakarta.servlet.http.HttpServletResponse;

@ExtendWith(MockitoExtension.class)
class ApiUtilTest1 {

	@InjectMocks
	private ApiUtil apiUtil;

	@Mock
	private NativeWebRequest nativeWebRequest;

	@Mock
	private HttpServletResponse httpServletResponse;

	@Test
	void testSetExampleResponse() throws Exception {
		// Arrange
		String contentType = "application/json";
		String example = "{\"key\": \"value\"}";

		Mockito.when(nativeWebRequest.getNativeResponse(HttpServletResponse.class)).thenReturn(httpServletResponse);
		Mockito.when(httpServletResponse.getWriter()).thenReturn(Mockito.mock(java.io.PrintWriter.class));

		// Act
		ApiUtil.setExampleResponse(nativeWebRequest, contentType, example);

		// Assert
		Mockito.verify(httpServletResponse).setCharacterEncoding("UTF-8");
		Mockito.verify(httpServletResponse).addHeader("Content-Type", contentType);
		Mockito.verify(httpServletResponse.getWriter()).print(example);
	}

	@Test
	void testSetExampleResponse2() throws Exception {
		// Arrange
		String contentType = "application/json";
		String example = "{\"key\": \"value\"}";

		Mockito.when(nativeWebRequest.getNativeResponse(HttpServletResponse.class)).thenReturn(httpServletResponse);
		Mockito.when(httpServletResponse.getWriter()).thenThrow(new IOException("Simulated IOException"));

		// Act and Assert
		assertThrows(RuntimeException.class, () -> ApiUtil.setExampleResponse(nativeWebRequest, contentType, example));

		// Verify
		Mockito.verify(httpServletResponse).setCharacterEncoding("UTF-8");
		Mockito.verify(httpServletResponse).addHeader("Content-Type", contentType);
		Mockito.verify(httpServletResponse).getWriter(); // Verifica que se llamó a getWriter
	}
}