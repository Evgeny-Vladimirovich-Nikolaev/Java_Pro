package main.java.utils;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.experimental.UtilityClass;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class CsvReader {

    public static <T> Set<T> readAsSet(String resourceName, Class<T> clazz, char separator, boolean ignoreLeadSpace) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(CsvReader.class.getResourceAsStream(resourceName)))) {

            CsvToBean<T> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(clazz)
                    .withSeparator(separator)
                    .withIgnoreLeadingWhiteSpace(ignoreLeadSpace)
                    .build();
            return csvToBean.stream().collect(Collectors.toSet());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptySet();
    }

}
