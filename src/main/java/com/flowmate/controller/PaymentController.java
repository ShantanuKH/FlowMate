package com.flowmate.controller;

import com.flowmate.model.PlanType;
import com.flowmate.model.User;
import com.flowmate.response.PaymentLinkResponse;
import com.flowmate.service.UserService;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")

public class PaymentController {


//    To call the api key
    @Value("${razorpay.api.key}")
    private String apiKey;

    //    To call the api Secret
    @Value("${razorpay.api.secret}")
    private String apiSecret;


    @Autowired
    private UserService userService;



    @PostMapping("/{planType}")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink (
            @PathVariable PlanType planType,
            @RequestHeader ("Authorization") String jwt
            ) throws Exception{

        User user = userService.findUSerProfileByJwt(jwt);



//         To convert the currency to rupees we multiplied it by 100
        int amount = 799*100;

        if(planType.equals(PlanType.ANUALLY)){
            amount = amount*12;

//            AS we are giving 30% off we are writing it as 0.7, We can assume it like 30/100
            amount = (int)(amount*0.7);
        }


            RazorpayClient razorpay = new RazorpayClient(apiKey,apiSecret);

            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount", amount);
            paymentLinkRequest.put("currency", "INR");

            JSONObject customer = new JSONObject();
            customer.put("name", user.getFullName());
            customer.put("email", user.getEmail());
            paymentLinkRequest.put("customer", customer);


            JSONObject notify= new JSONObject();
            notify.put("email", true);
            paymentLinkRequest.put("notify", notify);


            /////////////////////////////////////////////////////////////////////////////////////////////////////////////// LocalHost

            paymentLinkRequest.put("callback_url", "http://localhost:5454/upgrade_plan/success?planType" + planType);



            PaymentLink payment = razorpay.paymentLink.create(paymentLinkRequest);
            String paymentLinkId= payment.get("id");
            String paymentLinkUrl = payment.get("short_url");

            PaymentLinkResponse  res = new PaymentLinkResponse();
            res.setPayment_link_url(paymentLinkUrl);
            res.setPayment_link_url(paymentLinkId);
                return new ResponseEntity<>(res, HttpStatus.CREATED);





    }
}
