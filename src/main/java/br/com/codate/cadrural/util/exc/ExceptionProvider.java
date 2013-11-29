package br.com.codate.cadrural.util.exc;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Faz o tratamento e tradução das exceções.
 * 
 * @author charles.viegas
 */
@Provider
public class ExceptionProvider implements ExceptionMapper<Throwable> {

    private static final Properties properties = new Properties();

    private static final String filename = "mensagem.properties";

    static {

	try {
	    InputStream inputStream = ExceptionProvider.class.getClassLoader().getResourceAsStream(filename);
	    if (inputStream != null) {
		Properties prop = new Properties();
		prop.load(inputStream);
		properties.putAll(prop);
	    }
	} catch (IOException e) {
	    System.out.println(e.getMessage());
	}
    }

    @Override
    public Response toResponse(Throwable e) {

	e.printStackTrace();

	String mensagem = "Não foi possível identificar o erro";
	ArrayList<Throwable> stack = listCauseExceptions(e);
	for (Throwable exc : stack) {
	    String key = exc.getClass().getSimpleName();
	    String msg = properties.getProperty(key);
	    if (msg != null) {
		mensagem = msg;
		break;
	    }
	}
	return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(mensagem).type(MediaType.TEXT_PLAIN).build();
    }

    // Lista todas as excecoes causa da raiz para a mais externa
    private ArrayList<Throwable> listCauseExceptions(Throwable e) {
	ArrayList<Throwable> stack = new ArrayList<Throwable>();
	do {
	    stack.add(e);
	    e = e.getCause();
	} while (e != null);

	Collections.reverse(stack);
	return stack;
    }
}