package ru.vsu.csd.datatransferservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("analysis_emotional_coloring")
public class AnalysisEmotionalColoringController {
    @GetMapping("{text}")
    public double[] doAnalysis(@PathVariable String text) {
//        try {
//            Process process = Runtime.getRuntime().exec("pip install --user -r requirements.txt");
//
//            BufferedReader inputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            String inputLine;
//            while ((inputLine = inputReader.readLine()) != null) {
//                System.out.println(inputLine);
//            }
//
//            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
//            String errorLine;
//            while ((errorLine = errorReader.readLine()) != null) {
//                System.err.println(errorLine);
//            }
//
//            int exitValue = process.waitFor();
//
//            if (exitValue == 0) {
//                // Dependencies installed successfully, now execute the script
//                PythonInterpreter.initialize(System.getProperties(), System.getProperties(), new String[0]);
//                PythonInterpreter interpreter = new PythonInterpreter();
//                interpreter.execfile("src/main/resources/scripts/analysis-emotional-coloring.py");
//                return "Python script executed successfully";
//            } else {
//                // Error installing dependencies
//                return "Error installing Python dependencies";
//            }
//        } catch (IOException e) {
//                e.printStackTrace();
//                return "Error occurred during script execution";
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        Random random = new Random();
        double[] randomArray = new double[5];

        for (int i = 0; i < 5; i++) {
            double randomNumber = random.nextDouble() * 2 - 1;
            randomArray[i] = randomNumber;
        }

        return randomArray;
    }
}
