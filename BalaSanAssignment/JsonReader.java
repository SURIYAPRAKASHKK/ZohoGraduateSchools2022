package com.zoho.ApiTest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class JsonReader {
	

	public static void main(String[] args) {
		 //con = null;		
		HttpsURLConnection con = null;
		String URLStr = "https://reqres.in/api/unknown";
		try {
			URL url = new URL(URLStr);
			con = (HttpsURLConnection) url.openConnection();
			con.connect();
			Scanner scan = new Scanner(con.getInputStream());
			while(scan.hasNextLine())
				System.out.print(scan.nextLine());

		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		finally {
			if(con != null)
				con.disconnect();
		}

	}

}
