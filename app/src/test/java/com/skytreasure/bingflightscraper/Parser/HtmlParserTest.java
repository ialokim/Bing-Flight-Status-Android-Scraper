package com.skytreasure.bingflightscraper.Parser;

import com.skytreasure.bingflightscraper.Parser.HtmlParser;
import com.skytreasure.bingflightscraper.Parser.ParserResponseInterface;
import com.skytreasure.bingflightscraper.model.FlightModel;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class HtmlParserTest {

    private class ParserResponse implements ParserResponseInterface {

        @Override
        public void onParsingDone(List<FlightModel> flightModel) {
            System.out.println(flightModel);

        }
    }

    @Test
    public void test() throws Exception {
        String url = "https://www.bing.com/search?q=flight+";
        String query = "kl 0759";
        List<FlightModel> flights = new HtmlParser(new ParserResponse()).parse(url + query);


    }
}