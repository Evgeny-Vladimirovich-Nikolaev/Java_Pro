package utils;

import bookBase.dto.Book;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CsvReader {

    public static List<?> readAsList(String resourceName, Class clazz, char separator, boolean ignoreLeadSpace) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(CsvReader.class.getResourceAsStream(resourceName)))) {
            CsvToBean<Book> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(clazz)
                    .withSeparator(separator)
                    .withIgnoreLeadingWhiteSpace(ignoreLeadSpace)
                    .build();
            return csvToBean.stream().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

}
