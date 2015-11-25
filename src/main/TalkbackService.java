package main;

import java.io.IOException;

public class TalkbackService implements SerialServer.Service {
	ReadFile file = new ReadFile();
    public String processQuery(String query) {
		try {
			file.readFile(query);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "<p>You said: '" + query + "'</p>";
    }

    public static void main(String[] args) throws IOException {
	TalkbackService service = new TalkbackService();
        int port = 8888;
        SerialServer server = new SerialServer(port, service, "talkback");
        server.run();
    }
}
