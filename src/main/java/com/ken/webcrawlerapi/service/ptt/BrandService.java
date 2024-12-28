package com.ken.webcrawlerapi.service.ptt;

import com.ken.webcrawlerapi.service.ptt.pojo.Brand;
import com.ken.webcrawlerapi.service.ptt.pojo.BrandLog;
import com.ken.webcrawlerapi.service.ptt.repository.BrandLogRepository;
import com.ken.webcrawlerapi.service.ptt.repository.BrandRepository;
import com.ken.webcrawlerapi.service.ptt.repository.PTTCrawlerBrandRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ken.chen
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    private final BrandLogRepository brandLogRepository;

    private final PTTCrawlerBrandRepository pttCrawlerBrandRepository;

    public void updateBrand(){
        List<Brand> brands = pttCrawlerBrandRepository.getBrands();

        List<BrandLog> brandLogList = new ArrayList<>();

        for(Brand brand:brands){
            Brand originBrand = isBrandExist(brand);
            if(originBrand==null){
                brandRepository.save(brand);
            }
            else{
                brandRepository.update(
                        brand.getName(),
                        brand.getTitle(),
                        brand.getUrl(),
                        brand.getUserCount(),
                        brand.getCategory(),
                        originBrand.getId());
                BrandLog brandLog = new BrandLog();
                BeanUtils.copyProperties(originBrand,brandLog);
                brandLog.setId(null);
                brandLogList.add(brandLog);
            }
        }

        if(!CollectionUtils.isEmpty(brandLogList)){
            brandLogRepository.saveAll(brandLogList);
        }
    }

    private Brand isBrandExist(Brand source){
        String brandName = source.getName();
        return brandRepository.findByName(brandName).orElse(null);
    }

}
