package com.skytreasure.bingflightscraper.Parser;

import android.os.AsyncTask;

import com.skytreasure.bingflightscraper.model.FlightModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HtmlParser extends AsyncTask<String, Void, List<FlightModel>> {

    private ParserResponseInterface parserResponseInterface;

    public HtmlParser(ParserResponseInterface parserResponseInterface) {
        this.parserResponseInterface = parserResponseInterface;
    }

    @Override
    protected List<FlightModel> doInBackground(String... params) {
        return parse(params[0]);
    }

    protected List<FlightModel> parse(String url) {

        List<FlightModel> flightModelList = new ArrayList<>();
        Document pageDocument;

        try {
            pageDocument = Jsoup.connect(url).get();


            /*Elements HeaderElements = pageDocument.select(".fsmd-header__flight-num");
            for (int i = 0; i < HeaderElements.size(); i++) {
                String[] headerInfo = HeaderElements.get(i).text().split(" · ");
                flightModelList.get(i).flightCompany = headerInfo[0];
                flightModelList.get(i).flightNumber = headerInfo[1];
            }*/

            /*Elements otherDetailsElements = pageDocument.select(".fsmd-status__details");
            for (int i = 0; i < otherDetailsElements.size(); i++) {
                flightModelList.get(i).statusDetails = otherDetailsElements.get(i).text();
            }*/

            Elements statusElements = pageDocument.select(".fsmd-status__badge");
            for (int i = 0; i < statusElements.size(); i++) {
                flightModelList.add(new FlightModel());
                flightModelList.get(i).status = statusElements.get(i).text();
            }

            Elements departureAndArrivalScheduledTimeElements = pageDocument.select(".fsmd-leg__scheduled-time");
            for (int i = 0; i < departureAndArrivalScheduledTimeElements.size(); i++) {
                flightModelList.get(i).departureTime = departureAndArrivalScheduledTimeElements.get(i).select(".fsmd-value-pair__left").get(0).text();
                flightModelList.get(i).arrivalTime = departureAndArrivalScheduledTimeElements.get(i).select(".fsmd-value-pair__right").get(0).text();
            }

            Elements departureAndArrivalUpdatedTimeElements = pageDocument.select(".fsmd-leg__updated-time");
            for (int i = 0; i < departureAndArrivalUpdatedTimeElements.size(); i++) {
                flightModelList.get(i).departureTimeUpdated = departureAndArrivalUpdatedTimeElements.get(i).select(".fsmd-value-pair__left").get(0).text();
                flightModelList.get(i).arrivalTimeUpdated = departureAndArrivalUpdatedTimeElements.get(i).select(".fsmd-value-pair__right").get(0).text();
            }

            Elements departureArivalDatesElements = pageDocument.select(".fsmd-leg__updated-date");
            for (int i = 0; i < departureArivalDatesElements.size(); i++) {
                flightModelList.get(i).departureDate = departureArivalDatesElements.get(i).select(".fsmd-value-pair__left").get(0).text();
                flightModelList.get(i).arrivalDate = departureArivalDatesElements.get(i).select(".fsmd-value-pair__right").get(0).text();
            }

            Elements departureArivalCitiesElements = pageDocument.select(".fsmd-leg__city-name");
            for (int i = 0; i < departureArivalCitiesElements.size(); i++) {
                flightModelList.get(i).dCityName = departureArivalCitiesElements.get(i).select(".fsmd-value-pair__left").get(0).text();
                flightModelList.get(i).aCityName = departureArivalCitiesElements.get(i).select(".fsmd-value-pair__right").get(0).text();
            }

            Elements airportInfoOriginElements = pageDocument.select(".fsmd-leg__airport-info__origin");
            for (int i = 0; i < airportInfoOriginElements.size(); i++) {
                Elements infos = airportInfoOriginElements.get(i).select(".fsmd-airport-info");
                for (int j = 0; j < infos.size(); j++) {
                    switch (infos.get(j).child(0).text()) {
                        case "Terminal":
                            flightModelList.get(i).dTerminal = infos.get(j).child(1).text();
                            break;
                        case "Gate":
                            flightModelList.get(i).dGate = infos.get(j).child(1).text();
                            break;
                        case "Baggage":
                            flightModelList.get(i).dBaggage = infos.get(j).child(1).text();
                            break;
                    }
                }
            }

            Elements airportInfoDestinationElements = pageDocument.select(".fsmd-leg__airport-info__destination");
            for (int i = 0; i < airportInfoDestinationElements.size(); i++) {
                Elements infos = airportInfoDestinationElements.get(i).select(".fsmd-airport-info");
                for (int j = 0; j < infos.size(); j++) {
                    switch (infos.get(j).child(0).text()) {
                        case "Terminal":
                            flightModelList.get(i).aTerminal = infos.get(j).child(1).text();
                            break;
                        case "Gate":
                            flightModelList.get(i).aGate = infos.get(j).child(1).text();
                            break;
                        case "Baggage":
                            flightModelList.get(i).aBaggage = infos.get(j).child(1).text();
                            break;
                    }
                }
            }

            Elements sourceDestinationCode = pageDocument.select(".fsmd-progress-bar");
            for (int i = 0; i < sourceDestinationCode.size(); i++) {
                //flightModelList.get(i).sourceDestinationCode = airportInfoElements.get(i).text();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return flightModelList;
    }

    @Override
    protected void onPostExecute(List<FlightModel> fmlist) {
        super.onPostExecute(fmlist);

        parserResponseInterface.onParsingDone(fmlist);
    }
}
