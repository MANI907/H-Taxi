package htaxi;

import htaxi.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired PaymentRepository paymentRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverGrabCancelled_PaymentCancelled(@Payload GrabCancelled grabCancelled){

        if(!grabCancelled.validate()) return;

        System.out.println("\n\n##### listener PaymentCancelled : " + grabCancelled.toJson() + "\n\n");


        

        // Sample Logic //
        // Payment payment = new Payment();
        // paymentRepository.save(payment);

    }


}


