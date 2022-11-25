package com.example.project.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;



@Controller
public class NewsController {

    @RequestMapping("/news")
    public ModelAndView viewNews() throws UnirestException{
        String apiKey = "49ee89a591mshdfab1f0ab341bfcp1eb0bbjsnc51a1f6f5633";
        String host = "contextualwebsearch-websearch-v1.p.rapidapi.com";
        String pageSize = "10";
        String queryCategory = "finance";
        ModelAndView mv = new ModelAndView("/news/news");
        HttpResponse<JsonNode> response = Unirest.get("https://contextualwebsearch-websearch-v1.p.rapidapi.com/api/search/NewsSearchAPI?q="+queryCategory+"&pageNumber=1&pageSize="+pageSize+"&autoCorrect=true&fromPublishedDate=null&toPublishedDate=null")
            .header("X-RapidAPI-Key", apiKey)
            .header("X-RapidAPI-Host", host)
            .asJson();
        JSONObject obj = response.getBody().getObject();
        JSONArray objArray = obj.getJSONArray("value");
        Map<String,String> newsDetails = new HashMap<>();
        for(int i = 0; i < objArray.length(); ++i){
            JSONObject news = objArray.getJSONObject(i);
            newsDetails.put(news.getString("title"), news.getString("description"));
        }
        mv.addObject("newsDetails", newsDetails);
        return mv;
    }
}
