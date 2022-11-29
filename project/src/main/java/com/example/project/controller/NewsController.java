package com.example.project.controller;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.project.dto.NewsDto;


@Controller
public class NewsController {
    @Autowired
    private Environment env;
    @RequestMapping("")
    public ModelAndView viewNews2() throws IOException, ParseException{
        ModelAndView mv = new ModelAndView("/news/news");
        //API key is stored in application.properties
        //If want to run this ask steven for the api key
        String apiKey = env.getProperty("news.api.key");
        String country = "sg";
        //Available category: business entertainment general health science sports technology
        String category = "business";
        String urlToReadNews = "https://newsapi.org/v2/top-headlines?country="+country+"&category="+category+"&apiKey="+apiKey;
        URL url = new URL(urlToReadNews);
        String stringJson = IOUtils.toString(url, Charset.forName("UTF-8"));
        List<NewsDto> newsList = new ArrayList<>();
        JSONObject json = new JSONObject(stringJson);
        JSONArray articles = json.getJSONArray("articles");
        for(int i = 0; i < articles.length(); ++i){
            JSONObject article = articles.getJSONObject(i);
            if(!article.isNull("description")||!article.isNull("urlToImage")){
                String title = article.getString("title");
                String description = article.getString("description");
                String newsURL = article.get("urlToImage").toString();
                String published =  article.getString("publishedAt");
                newsList.add(new NewsDto(title,description,newsURL,published));
            }
        }
        mv.addObject("newsList", newsList);
        return mv;
    }
}