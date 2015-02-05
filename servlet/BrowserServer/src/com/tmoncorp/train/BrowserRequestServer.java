package com.tmoncorp.train;

/**
 * Created by great on 1/5/15.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tmoncorp.train.page.WebPage;

public class BrowserRequestServer {
	private int port;
	private String line; // single line from request
	private Socket socket; // socket opened to client
	private ServerSocket serverSocket; // gets client connection
	private BufferedReader in;
	private PrintWriter out;
	private static Map<String, String> pathMap = new HashMap<>();
	private StringBuffer request;
	private StringBuffer postData;
	private String reqPath;
	private Map<String, String> getParamMap;

	private static final int DEFAULT_PORT = 8888;
	private static final String NOT_FOUND_MARKUP = "<h1 style='color:#F90000'>404 Not Found Error</h1>";

	static {
		pathMap.put("/", "com.tmoncorp.train.page.impl.DefaultPage");
		pathMap.put("/test", "com.tmoncorp.train.page.impl.TestPage");
		pathMap.put("/param", "com.tmoncorp.train.page.impl.ParamPage");
		pathMap.put("/calc", "com.tmoncorp.train.page.impl.CalculatorPage");
	}

	public void startServer(String[] args) {
		init(args);
		run();
	}

	private void run() {
		while (true) {
			try {
				waitRequest();

				boolean firstLine = false;
				while (true) {
					readReqLine();

					if (!firstLine) {
						extractRequestPath();
						firstLine = true;
					}

					if (line.equals("")) {
						getPostData();
						break; // request is complete, exit while loop
					}
				}

				response();

				close();
			} catch (IOException e) {
				System.err.println("Error: " + e);
				break;
			}
		}
	}

	private void init(String[] args) {
		request = new StringBuffer();
		postData = new StringBuffer();
		port = DEFAULT_PORT;
		if (args.length > 0) {
			try {
				port = Integer.parseInt(args[0]);
			} catch (Exception e) {
				port = DEFAULT_PORT;
			}
		}

		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("Error: " + e);
			System.exit(0);
		}
	}

	private void waitRequest() throws IOException {
		System.out.println("\nListening on port " + port + "...");
		socket = serverSocket.accept();
		System.out.println("Connection established...\n");
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	private void readReqLine() throws IOException {
		line = in.readLine();
		request.append(line);
	}

	private void extractRequestPath() {
		String[] tempArr;
		tempArr = line.split(" ");
		parsePath(tempArr[1]);
	}

	private void parsePath(String fullPath) {
		Pattern p1 = Pattern.compile("(/[/\\w]*)");
		Matcher m1 = p1.matcher(fullPath);
		while (m1.find()) {
			reqPath = m1.group(1);
		}
		
		getParamMap = new HashMap<String, String>();
		Pattern p2 = Pattern.compile("(\\w*)(=)(\\w*)");
		Matcher m2 = p2.matcher(fullPath);
		while (m2.find()) {
			getParamMap.put(m2.group(1), m2.group(3));
		}
	}

	private void getPostData() throws IOException {
		while (in.ready()) {
			char c = (char) in.read();
			postData.append(c);
		}
	}

	private PrintWriter response() throws IOException {
		out = new PrintWriter(socket.getOutputStream());

		if (pathMap.containsKey(reqPath)) {
			WebPage page = null;
			try {
				page = instantiate(pathMap.get(reqPath), WebPage.class);
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				e.printStackTrace();
			}
			// send HTTP headers for browser to read
			out.println("HTTP/1.0 200 OK");
			out.println("Content-Type: text/html");
			out.println(); // blank line between headers and content
			out.print(page.getResponse(getParamMap));
		} else {
			// send HTTP headers for browser to read
			out.println("HTTP/1.0 404 Not Found");
			out.println("Content-Type: text/html");
			out.println();
			out.println(NOT_FOUND_MARKUP);
		}
		return out;
	}

	private void close() throws IOException {
		out.flush();
		out.close();
		socket.close();
	}

	// 질문 <T> T 의미???
	private static <T> T instantiate(String className, Class<T> type)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		return type.cast(Class.forName(className).newInstance());
	}

	public static void main(String[] args) {
		BrowserRequestServer br = new BrowserRequestServer();
		br.startServer(args);
	}

}
