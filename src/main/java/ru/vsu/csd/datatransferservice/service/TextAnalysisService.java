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
    private static final Logger logger = LoggerFactory.getLogger(TextAnalysisService.class);

    public TextAnalysisService() {
    }

    public Map<String, Double> startAnalysis(String text) {
        System.out.println("Input text: " + text);

        try {
            logger.info("Importing libraries");
            ProcessBuilder pbd = new ProcessBuilder("pip", "install", "nltk", "pandas", "sys");
            Process install = pbd.start();
            install.waitFor();

            logger.info("Python Script run");
            ProcessBuilder pb = new ProcessBuilder("python", "script/analysis-emotional-coloring.py", text);
            // Направляем вывод ошибок в поток вывода
            pb.redirectErrorStream(true);

            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            ArrayList<String> line = new ArrayList<String>();
            while (true) {
                line.add(reader.readLine());
                if (!(line.get(line.size() - 1) != null)) break;
                logger.info(line.get(line.size() - 1));
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
            System.out.print(ex.getStackTrace());
            throw new RuntimeException(ex);
        }
    }
}
