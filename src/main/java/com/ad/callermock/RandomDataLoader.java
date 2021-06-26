package com.ad.callermock;

import com.ad.model.TradeDetails;
import com.ad.service.Receiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

/**
 * RandomDataLoader randomly loads data on fixed interval
 */
@Component
public class RandomDataLoader {

    private static final Logger logger = LoggerFactory.getLogger(RandomDataLoader.class);

    @Autowired
    private Receiver<TradeDetails> tradeReceiver;

    public void scheduleDataLoader(){
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                uploadData();
            }
        };
        //running timer task as daemon thread
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 20*1000);
    }


    private void uploadData(){

        logger.info("***********Loading new set of data***********");

        TradeDetails td =  createTradeDetails(randomId(), randomId(), randomDays());
        tradeReceiver.accept(td);

        td =  createTradeDetails(randomId(), randomId(), randomDays());
        tradeReceiver.accept(td);

        td =  createTradeDetails(randomId(), randomId(), randomDays());
        tradeReceiver.accept(td);


    }

    private TradeDetails createTradeDetails(Long tradeId, Long version, Integer days){
        TradeDetails tradeDetails = new TradeDetails();
        tradeDetails.setTradeId(tradeId);
        tradeDetails.setVersion(version);
        tradeDetails.setBookId("Book-id"+tradeId);
        tradeDetails.setCounterpartyId("Counterparty"+version);

        tradeDetails.setMaturityDate(LocalDate.now().plusDays(days));
        return tradeDetails;
    }

    private Long randomId(){
        return (long)ThreadLocalRandom.current().nextInt(1,4);
    }

    private Integer randomDays(){
        return ThreadLocalRandom.current().nextInt(-4,4);
    }
}
