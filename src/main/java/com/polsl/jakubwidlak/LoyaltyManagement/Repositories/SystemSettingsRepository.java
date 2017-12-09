package com.polsl.jakubwidlak.LoyaltyManagement.Repositories;

import com.polsl.jakubwidlak.LoyaltyManagement.Entities.SystemSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemSettingsRepository extends JpaRepository<SystemSetting, Integer> {
    SystemSetting findBySystemSettingId(Long id);
    List<SystemSetting> findAll();
    SystemSetting findBySystemSettingName(String name);
}
