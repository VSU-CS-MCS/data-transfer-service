package ru.vsu.csd.datatransferservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TextAnalysisService {
    private static final String SCRIPT_PATH = "script/analysis-emotional-coloring.py";
    private static final Logger logger = LoggerFactory.getLogger(TextAnalysisService.class);

    public TextAnalysisService() {
    }

    public Map<String, Double> startAnalysis(String text) {
        logger.info("Input text: " + text);
        Process process = null;
        try {
            ProcessBuilder pbd = new ProcessBuilder("pip", "install", "nltk", "pandas", "sys");
            Process install = pbd.start();
            install.waitFor();
            ProcessBuilder pb = new ProcessBuilder("python", SCRIPT_PATH, text);
            pb.redirectErrorStream(true);
            process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            ArrayList<String> line = new ArrayList<String>();
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                line.add(currentLine);
                logger.info(currentLine);
            }

            process.waitFor();

            logger.info("Python Script finished");
            String[] parts = line.get(0).split(" ");
            Map<String, Double> res = new HashMap<>();
            res.put("emotionalColoringJoy", Double.parseDouble(parts[0]));
            res.put("emotionalColoringSadness", Double.parseDouble(parts[1]));
            res.put("emotionalColoringAnger", Double.parseDouble(parts[2]));
            res.put("emotionalColoringDisgust", Double.parseDouble(parts[3]));
            res.put("emotionalColoringFear", Double.parseDouble(parts[4]));
            return res;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
    }
}
