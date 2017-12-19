package com.polsl.jakubwidlak.LoyaltyManagement.Services;

import com.polsl.jakubwidlak.LoyaltyManagement.Entities.Offer;
import com.polsl.jakubwidlak.LoyaltyManagement.Entities.SystemSetting;
import com.polsl.jakubwidlak.LoyaltyManagement.Repositories.OfferRepository;
import com.polsl.jakubwidlak.LoyaltyManagement.Repositories.SystemSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Component
public class OfferCleanupCron {

    private SystemSettingsRepository systemSettingsRepository;
    private OfferRepository offerRepository;

    @Autowired
    public OfferCleanupCron(SystemSettingsRepository systemSettingsRepository, OfferRepository offerRepository) {
        this.systemSettingsRepository = systemSettingsRepository;
        this.offerRepository = offerRepository;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void clearOffers(){
        SystemSetting systemSetting = systemSettingsRepository.findBySystemSettingName("OfferTimeStored");
        List<Offer> offerList = offerRepository.findAll();
        for (Offer offer:offerList) {
            LocalDate endDate = offer.getOfferEndDate().toLocalDate();
            LocalDate currentDate = LocalDate.now();
            if(endDate.isBefore(currentDate)&&ChronoUnit.DAYS.between(endDate, currentDate)>systemSetting.getSystemSettingValue()){
                offerRepository.deleteByOfferId(offer.getOfferId());
            }
        }
    }
}
