package com.skytreasure.bingflightscraper;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.skytreasure.bingflightscraper.model.FlightModel;

/**
 * Created by akash on 21/12/17.
 */
@Layout(R.layout.item_flight_card)
public class FlightItem {

    @View(R.id.label)
    TextView tvLabel;
    @View(R.id.tv_source)
    TextView tvSource;
    @View(R.id.tv_destination)
    TextView tvDestination;
    /*@View(R.id.tv_date)
    TextView tvDate;
    @View(R.id.tv_day)
    TextView tvDay;*/
    @View(R.id.tv_flightname)
    TextView tvFlightName;
    @View(R.id.tv_flightnumber)
    TextView tvFlightNumber;
    @View(R.id.tv_pnr)
    TextView tvPnr;
    @View(R.id.tv_journey_stime)
    TextView tvJourneyStarttime;
    @View(R.id.tv_journey_etime)
    TextView tvJourneyEndtime;
    @View(R.id.tv_status)
    TextView tvStatus;
    @View(R.id.tv_origin_terminal)
    TextView tvOriginTerminal;
    @View(R.id.tv_origin_gate)
    TextView tvOriginGate;
    @View(R.id.tv_origin_baggage)
    TextView tvOriginBaggage;
    @View(R.id.tv_destination_terminal)
    TextView tvDestinationTerminal;
    @View(R.id.tv_destination_gate)
    TextView tvDestinationGate;
    @View(R.id.tv_destination_baggage)
    TextView tvDestinationBaggage;

    private Context mContext;
    private PlaceHolderView mPlaceHolderView;
    private FlightModel flightModel;

    public FlightItem(Context mContext, PlaceHolderView mPlaceHolderView, FlightModel fm) {
        this.flightModel = fm;
        this.mContext = mContext;
        this.mPlaceHolderView = mPlaceHolderView;
    }

    @Resolve
    private void onResolved() {
        tvLabel.setText(flightModel.departureDate); //TODO
        tvSource.setText(flightModel.dCityName);
        tvDestination.setText(flightModel.aCityName);
        //   tvDate.setText(flightModel.departureArrivalDates);
        tvJourneyStarttime.setText(flightModel.departureTimeUpdated);
        tvJourneyEndtime.setText(flightModel.arrivalTimeUpdated);
        tvPnr.setText(flightModel.sourceDestinationCode);
        tvStatus.setText(flightModel.status);
        tvOriginTerminal.setText("Terminal " + flightModel.dTerminal);
        tvOriginGate.setText("Gate " + flightModel.dGate);
        tvOriginBaggage.setText(flightModel.dBaggage);
        tvDestinationTerminal.setText("Terminal " + flightModel.aTerminal);
        tvDestinationGate.setText("Gate " + flightModel.aGate);
        tvDestinationBaggage.setText(flightModel.aBaggage);
    }
}
