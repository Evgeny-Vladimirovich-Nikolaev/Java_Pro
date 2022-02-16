package exchangeRate;

import exchangeRate.aggregator.RateAggregator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import receiver.ValueReceiver;

import javax.sound.midi.Receiver;
import java.io.IOException;


@Configuration
public class ExchangeRateClient {

    private static final AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext("exchangeRate");
    private static final RateAggregator aggregator = appContext.getBean(RateAggregator.class);

    public static void main(String[] args) {
        do{
            selectCode();
        } while (resume());
        ValueReceiver.closeReader();
    }

    private static void selectCode() {
        String code;
        do {
            code = ValueReceiver.receiveString(RateMessage.ENTER_CODE.getMsg());
        } while (code.length() != 3);
        try {
            System.out.println(aggregator.getCurrency(code));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean resume() {
        String response = "";
        while (!"Y".equalsIgnoreCase(response) && !"N".equalsIgnoreCase(response)) {
            response = ValueReceiver.receiveString(RateMessage.EXIT_APP.getMsg());
        }
        return "N".equalsIgnoreCase(response);
    }
}
