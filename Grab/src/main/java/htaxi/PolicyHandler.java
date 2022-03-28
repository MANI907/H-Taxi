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
    @Autowired GrabRepository grabRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverStartService_ChangeGrabStatus(@Payload StartService startService){

        if(!startService.validate()) return;

        System.out.println("\n\n##### listener ChangeGrabStatus : " + startService.toJson() + "\n\n");


        

        // Sample Logic //
        // Grab grab = new Grab();
        // grabRepository.save(grab);

    }


}


