package com.flowmate.service;

import com.flowmate.model.PlanType;
import com.flowmate.model.Subscription;
import com.flowmate.model.User;

public interface SubscriptionService {

    Subscription createSubscription(User user);


    Subscription getUSerSubscription(Long userId) throws Exception;


    Subscription upgradeSubscription(Long userId, PlanType planType);


    boolean isValid(Subscription subscription);


}
