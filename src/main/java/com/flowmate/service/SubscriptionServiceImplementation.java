package com.flowmate.service;

import com.flowmate.model.PlanType;
import com.flowmate.model.Subscription;
import com.flowmate.model.User;
import com.flowmate.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SubscriptionServiceImplementation implements SubscriptionService {

    @Autowired
    private UserService userService;


    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription createSubscription(User user) {
        Subscription subscription = new Subscription();


        //Here we are creating free subscription plan for user
        subscription.setUser(user);
        subscription.setSubscriptionStartDate(LocalDate.now());
        subscription.setGetSubscriptionEndDate(LocalDate.now().plusMonths(12));
        subscription.setValid(true);
        subscription.setPlanType(PlanType.FREE);


        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription getUSerSubscription(Long userId) throws Exception {
        Subscription subscription =  subscriptionRepository.findByUserId(userId);

        if(!isValid(subscription)){
            subscription.setPlanType(PlanType.FREE);
            subscription.setGetSubscriptionEndDate(LocalDate.now().plusMonths(12));
            subscription.setSubscriptionStartDate(LocalDate.now());
        }

        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription upgradeSubscription(Long userId, PlanType planType) {
        Subscription subscription=subscriptionRepository.findByUserId(userId);
        subscription.setPlanType(planType);
        subscription.setSubscriptionStartDate(LocalDate.now());
        if(planType.equals(PlanType.ANUALLY)){
            subscription.setGetSubscriptionEndDate(LocalDate.now().plusMonths(12));
        } else {
            subscription.setGetSubscriptionEndDate(LocalDate.now().plusMonths(1));
        }
        return subscriptionRepository.save(subscription);
    }

    @Override
    public boolean isValid(Subscription subscription) {
        if(subscription.getPlanType().equals(PlanType.FREE)){
            return true;
        }
        LocalDate endDate = subscription.getGetSubscriptionEndDate();
        LocalDate currentDate = LocalDate.now();
        return endDate.isAfter(currentDate) || endDate.isEqual(currentDate);
    }
}
