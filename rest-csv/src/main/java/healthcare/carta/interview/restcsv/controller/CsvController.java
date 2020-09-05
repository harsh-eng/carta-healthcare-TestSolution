package healthcare.carta.interview.restcsv.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.minidev.json.JSONObject;

@RestController
public class CsvController {

	@PostMapping(value = "/" , consumes = "multipart/form-data")
	public ResponseEntity<Object> getAverage(@RequestParam ("data") MultipartFile  data, @RequestParam ("column") String column) {
		String line="";
		double sum=0;
		double rows=0;
		double outPut=0;
		JSONObject obj = new JSONObject();
		try {
			InputStreamReader streamReader = new InputStreamReader(data.getInputStream(), StandardCharsets.UTF_8);
			BufferedReader br = new BufferedReader(streamReader);
			int index = -1;
			if((line = br.readLine()) != null) {
				String [] csvData =line.split(",");
				for (int i=0;i<csvData.length;i++) {
					if(column.equalsIgnoreCase(csvData[i])) {
						index = i;
						break;
					}
				}
			}
			if(index==-1) {
				obj.put("Error", "Given column name not found");
				return new ResponseEntity<> (obj,HttpStatus.BAD_REQUEST);
			}

			while ((line = br.readLine()) != null) {
				String [] csvData =line.split(",");
				double value=0;
				if(index<=csvData.length-1) {
					value=Double.valueOf(csvData[index]);
				}
				sum = sum+value;
				rows++;
			}
			if(rows!=0) {
				outPut=(sum/rows);
			}
		}catch(IOException e) {
			obj.put("Error", e.getMessage());
			return new ResponseEntity<> (obj,HttpStatus.BAD_REQUEST);
		}
		catch(NumberFormatException e) {
			obj.put("Error","NumberFormat Exception:"+ e.getMessage());
			return new ResponseEntity<> (obj,HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
			e.printStackTrace();
			obj.put("Error", e.getMessage());
			return new ResponseEntity<> (obj,HttpStatus.BAD_REQUEST);
		}

		obj.put("data", outPut);
		return new ResponseEntity<> (obj,HttpStatus.OK);

	}

}
