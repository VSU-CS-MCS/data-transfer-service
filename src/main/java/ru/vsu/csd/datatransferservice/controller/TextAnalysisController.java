package ru.vsu.csd.datatransferservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.csd.datatransferservice.service.TextAnalysisService;

import java.util.Map;

@RestController
@RequestMapping("text-for-analysis")
public class TextAnalysisController {
    @Autowired
    private TextAnalysisService textAnalysisService ;

    @PostMapping("analyze")
    public Map<String, Double> analyzeText(@RequestBody String text) {
        return textAnalysisService.startAnalysis(text);
    }
}
