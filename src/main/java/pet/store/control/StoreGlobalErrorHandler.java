package pet.store.control;

import java.rmi.NoSuchObjectException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class StoreGlobalErrorHandler {
	private enum LogStatus {
		STACK_TRACE, MESSAGE_ONLY
	}
	
	@Data
	private class ExceptionMessage {
		private String message;
		private String statusReason;
		private int statusCode;
		private String timeStamp;
		private String uri;
		
	}
	
	
	@ExceptionHandler(NoSuchObjectException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ExceptionMessage handlerNoSuchObjectException(
			NoSuchObjectException ex, WebRequest webRequest) {
		return buildExceptionMessage(
				ex, HttpStatus.NOT_FOUND, webRequest, LogStatus.MESSAGE_ONLY);
	}

	

	private ExceptionMessage buildExceptionMessage(NoSuchObjectException ex, HttpStatus status, WebRequest webRequest,
			LogStatus logStatus) {
		String message = ex.toString();
		String statusReason = status.getReasonPhrase();
		int statusCode = status.value();
		String timeStamp = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
		String uri = null;
		
		if (webRequest instanceof ServletWebRequest swr) {
			uri = swr.getRequest().getRequestURI();
		}
		
		if (logStatus == LogStatus.MESSAGE_ONLY) {
			log.error("Exception: {}", ex.toString());
		} else {
			log.error("Exception: ", ex);
		}
		
		ExceptionMessage exMessage = new ExceptionMessage();

		exMessage.setMessage(message);
		exMessage.setStatusCode(statusCode);
		exMessage.setStatusReason(statusReason);
		exMessage.setUri(uri);
		exMessage.setTimeStamp(timeStamp);
		
		return exMessage;
	}
}
