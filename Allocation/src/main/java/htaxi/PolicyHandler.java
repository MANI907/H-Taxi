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
    @Autowired AllocationManagementRepository allocationManagementRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentApproved_ConfirmAllocation(@Payload PaymentApproved paymentApproved){

        if(!paymentApproved.validate()) return;

        System.out.println("\n\n##### listener ConfirmAllocation : " + paymentApproved.toJson() + "\n\n");


        

        // Sample Logic //
        // AllocationManagement allocationManagement = new AllocationManagement();
        // allocationManagementRepository.save(allocationManagement);

    }


}


