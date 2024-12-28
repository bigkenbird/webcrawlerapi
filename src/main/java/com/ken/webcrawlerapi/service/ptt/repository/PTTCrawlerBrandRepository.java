package com.ken.webcrawlerapi.service.ptt.repository;

import com.ken.webcrawlerapi.exception.pojo.WebCrawlerException;
import com.ken.webcrawlerapi.service.ptt.pojo.Brand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;


/**
 * @author ken.chen
 */
@Repository
@Slf4j
@RequiredArgsConstructor
public class PTTCrawlerBrandRepository {

    private final WebDriver chromeDriver;

    @Value("${ptt.crawler.index.url}")
    private String pttCrawlerIndexUrl;

    public List<Brand> getBrands(){
        chromeDriver.get(pttCrawlerIndexUrl);
        String indexPageSource = chromeDriver.getPageSource();
        if(!StringUtils.hasText(indexPageSource)){
            throw new WebCrawlerException(String.format("crawl url:%s, but no html can find",pttCrawlerIndexUrl));
        }
        Document document = Jsoup.parse(indexPageSource);
        Elements elements = document.select(".b-ent");
        return elements.stream().map(this::mapToBrand).toList();
    }

    private String mapToBrandUrl(String uri){
        return String.format("https://www.ptt.cc/%s",uri);
    }

    private Brand mapToBrand(Element element){
        Element linkElement = element.selectFirst("a.board");
        String uri = (linkElement == null) ? null : linkElement.attr("href");
        String url = StringUtils.hasText(uri) ? mapToBrandUrl(uri):null;

        Element nameElement = element.selectFirst("div.board-name");
        String name = (nameElement == null) ? null : nameElement.text();

        Element userCountElement = Objects.requireNonNull(element.selectFirst("div.board-nuser")).tagName("span");
        Integer userCount =  Integer.valueOf(userCountElement.text());

        Element categoryElement = element.selectFirst("div.board-class");
        String category = (categoryElement == null) ? null : categoryElement.text();

        Element titleElement = element.selectFirst("div.board-title");
        String title = (titleElement == null) ? null : titleElement.text();

        Brand brand = new Brand();
        brand.setName(name);
        brand.setTitle(title);
        brand.setUserCount(userCount);
        brand.setCategory(category);
        brand.setUrl(url);

        return brand;
    }


}
