package com.example.diploma.mapping;

import com.example.diploma.dto.Ads;
import com.example.diploma.dto.CreateAds;
import com.example.diploma.dto.FullAds;
import com.example.diploma.entity.AdEntity;
import com.example.diploma.entity.UserEntity;
import org.springframework.stereotype.Component;

/**
 * @author anna
 */
@Component
public class AdMapper {
    /**
     * Маппит {@link AdEntity} в dto
     * @param entity объект класса {@link AdEntity}
     * @return {@link Ads}
     */
    public Ads entityToAdsDto(AdEntity entity) {
        return new Ads(entity.getAuthor().getId(), entity.getImagePath(),
                entity.getPk(), entity.getPrice(), entity.getTitle());
    }

    /**
     * Маппит {@link AdEntity} в dto
     * @param entity объект класса {@link AdEntity}
     * @return {@link FullAds}
     */
    public FullAds entityToFullAdsDto(AdEntity entity) {
        return new FullAds(entity.getPk(), entity.getAuthor().getFirstName(), entity.getAuthor().getLastName(),
                entity.getDescription(), entity.getAuthor().getEmail(), entity.getImagePath(),
                entity.getAuthor().getPhone(), entity.getPrice(), entity.getTitle());
    }

    /**
     * Возвращает объект {@link AdEntity}, созданный из dto {@link CreateAds} и {@link UserEntity}
     * @param ads содержит данные объявления
     * @param author содержит данные пользователя
     * @return {@link AdEntity}
     */
    public AdEntity createAdsToEntity(CreateAds ads, UserEntity author) {
        return new AdEntity(author, ads.getTitle(), ads.getPrice(), ads.getDescription());
    }
}
