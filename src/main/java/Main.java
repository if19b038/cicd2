import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String [] args)
    {
        try {
            BufferedReader reader;
            String line;
            StringBuffer respnseContent = new StringBuffer();
            URL url = new URL("http://localhost:1234/data");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while((line = reader.readLine()) != null) {
                    respnseContent.append(line);
                }
                reader.close();
            }
            else {
                reader = new BufferedReader(new InputStreamReader((connection.getInputStream())));
                while((line = reader.readLine()) != null) {
                    respnseContent.append(line);
                }
                reader.close();
            }
            System.out.println(respnseContent.toString());
        }
        catch (IOException ex)
        {
            System.out.println("Fail");
        }
        hello();
    }

    public static void hello() {
        System.out.println("Hello");
    }
}
