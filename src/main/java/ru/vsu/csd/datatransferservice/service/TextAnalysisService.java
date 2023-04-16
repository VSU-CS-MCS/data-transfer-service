package ru.vsu.csd.datatransferservice.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TextAnalysisService {
    public TextAnalysisService() {
    }

    public ArrayList<Double> startAnalysis(String text) {
        System.out.println("Input text: " + text);

        try {
            // Установка библиотек
            System.out.println("Importing libraries");
            ProcessBuilder pbd = new ProcessBuilder("pip", "install", "nltk", "pandas", "sys");
            Process install = pbd.start();
            install.waitFor();

            // Создаем процесс-строитель
            System.out.println("Python Script run");
            ProcessBuilder pb = new ProcessBuilder("python", "script/analysis-emotional-coloring.py", text);
            // Направляем вывод ошибок в поток вывода
            pb.redirectErrorStream(true);

            // Запускаем процесс
            Process process = pb.start();

            // Читаем результат выполнения скрипта
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            ArrayList<String> line = new ArrayList<String>();
            while (true) {
                line.add(reader.readLine());
                if (!(line.get(line.size() - 1) != null)) break;
                System.out.println(line.get(line.size() - 1));
            }

            // Дожидаемся завершения процесса
            process.waitFor();
            System.out.println("Python Script finished");

            String[] parts = line.get(0).split(" ");
            ArrayList<Double> res = new ArrayList<Double>();

            for (String part : parts) {
                double num = Double.parseDouble(part);
                res.add(num);
            }

            return res;
        } catch (Exception ex) {
            System.out.print(ex.getStackTrace());
            throw new RuntimeException(ex);
        }
    }
}
