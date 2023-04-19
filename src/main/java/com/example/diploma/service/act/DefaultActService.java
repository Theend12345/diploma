package com.example.diploma.service.act;

import com.example.diploma.dto.ActDto;
import com.example.diploma.repository.ActRepository;

import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DefaultActService implements ActService {

    private final ActRepository actRepository;
    private final ActConverter actConverter;

    @Override
    public List<ActDto> findByDateBetween(String v1, String v2) {
        return actRepository.findByDateBetween(v1, v2).stream()
                .map(actConverter::fromActToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActDto> findByObject(String obj) {
        return actRepository.findByObject(obj).stream()
                .map(actConverter::fromActToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActDto> findByDefect(String def) {
        return actRepository.findByDefect(def).stream()
                .map(actConverter::fromActToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActDto> findByLimit(String lim) {
        return actRepository.findByLimit(lim).stream()
                .map(actConverter::fromActToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActDto> findByKmo(Integer id_kmo) {
        return actRepository.findByKmo(id_kmo).stream()
                .map(actConverter::fromActToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getDataStatus(List<String> data) {
        double positiveCounter = 0;
        double negativeCounter = 0;
        double neutralCounter = 0;
        double onePercent;

        List<String> results = new ArrayList<>();

        for (String status : data) {
            switch (status) {
                case "1":
                    positiveCounter++;
                    break;
                case "-1":
                    negativeCounter++;
                    break;
                case "0":
                    neutralCounter++;
                    break;
            }
        }

        onePercent = data.size() / 100f;

        results.add(String.format("%.1f", positiveCounter / onePercent));
        results.add(String.format("%.1f", negativeCounter / onePercent));
        results.add(String.format("%.1f", neutralCounter / onePercent));

        return results;
    }

    @Override
    public Map<String, Long> getDataDefects(List<String> data) {

        Map<String, Long> result = new HashMap<>();


        Map<String, Long> frequency =
                data.stream().collect(Collectors.groupingBy(
                        Function.identity(), Collectors.counting()));
        //System.out.println(k + ": " + v)
        frequency.forEach(result::put);

        return result;
    }

    @Override
    public List<ActDto> findAll() {
        return actRepository.findAll().stream()
                .map(actConverter::fromActToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActDto> findByObjectAndDateBetween(String obj, String v1, String v2) {
        return actRepository.findByObjectAndDateBetween(obj, v1, v2).stream()
                .map(actConverter::fromActToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActDto> findByObjectAndDefectAndDateBetween(String obj, String v3, String v1, String v2) {
        return actRepository.findByObjectAndDefectAndDateBetween(obj, v3.trim(), v1, v2).stream()
                .map(actConverter::fromActToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActDto> findByDate(String v1) {
        return actRepository.findByDate(v1).stream()
                .map(actConverter::fromActToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActDto> findByStation(String st) {
        return actRepository.findByStation(st).stream()
                .map(actConverter::fromActToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getDefects(String v1) {
        HashMap<String, String> defects = new HashMap<>();
        List<String> list = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream("D:/Java/diploma/defects.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workbook.getSheet(v1);

            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                defects.put(sheet.getRow(i).getCell(1).getStringCellValue(), sheet.getRow(i).getCell(1).getStringCellValue());
            }

            list = new ArrayList(defects.values());

            fileInputStream.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(list);
        return list;
    }

    @Override
    public String getFrequentFailure(String[] data) {

        String result = new String();

        Map<String, Integer> stringsCount = new HashMap<>();
        List<String> list = new ArrayList<>();

        for (int i = 0; i < data.length; i++) {
            list.add(data[i]);
        }

        for (String s : list) {
            Integer c = stringsCount.get(s);
            if (c == null) c = 0;
            c++;
            stringsCount.put(s, c);
        }

        Map.Entry<String, Integer> mostRepeated = null;
        for (Map.Entry<String, Integer> e : stringsCount.entrySet()) {
            if (mostRepeated == null || mostRepeated.getValue() < e.getValue())
                mostRepeated = e;
        }

        if (mostRepeated != null) {
            System.out.println(mostRepeated.getKey());
            result = new String(mostRepeated.getKey());
        }
        return result;
    }
}
