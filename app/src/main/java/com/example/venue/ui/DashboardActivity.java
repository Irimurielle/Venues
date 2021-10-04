package com.example.venue.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.venue.GlideApp;
import com.example.venue.R;
import com.example.venue.models.SpaceDetails;
import com.example.venue.models.SpaceDetailsList;
import com.example.venue.models.VenueList;
import com.example.venue.models.VenueResponse;
import com.example.venue.services.ApiClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {
    private static final String TAG = DashboardActivity.class.getSimpleName();
    @BindView(R.id.textView)
    TextView TextView;
    @BindView(R.id.edit)
    SearchView search;
    @BindView(R.id.conference)
    CardView conference;
    @BindView(R.id.wedding)
    CardView wedding;
    @BindView(R.id.office)
    CardView office;
    @BindView(R.id.party)
    CardView party;
    @BindView(R.id.grid)
    GridView gridView;
    private List<VenueResponse> venueResponses = new ArrayList<>();
    private List<SpaceDetails> spaceDetailsResponses =new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        ButterKnife.bind(this);

        TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, MoreVenuesActivity.class);
                startActivity(intent);
                finish();
            }
        });

        conference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, MoreVenuesActivity.class);
                startActivity(intent);
                finish();
            }
        });

        wedding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, MoreVenuesActivity.class);
                startActivity(intent);
                finish();
            }
        });

        office.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, MoreVenuesActivity.class);
                startActivity(intent);
                finish();
            }
        });

        party.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, MoreVenuesActivity.class);
                startActivity(intent);
                finish();
            }
        });


        getAllVenues();

        CustomAdapter customAdapter;
    }

    private void getAllSpaceDetails(){
        Call<SpaceDetailsList> spaceDetails = ApiClient.getSpaceDetailsService().getAllSpaceDetails(4);
        spaceDetails.enqueue(new Callback<SpaceDetailsList>() {
            @Override
            public void onResponse(Call<SpaceDetailsList> call, Response<SpaceDetailsList> response) {
                if (response.isSuccessful()){
                    String message ="Request successful..";
                    Toast.makeText(DashboardActivity.this,message,Toast.LENGTH_LONG).show();
                    SpaceDetailsList spaceDetailsList = response.body();
                    Intent intent = new Intent(DashboardActivity.this, VenueActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    String message ="an error occurred try again later..";
                    Toast.makeText(DashboardActivity.this,message,Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<SpaceDetailsList> call, Throwable t) {
                Log.e("Failed", t.getLocalizedMessage());
            }
        });
    }

    private void getAllVenues() {

        Call<VenueList> venues = ApiClient.getInterface().getVenueResponses();
        venues.enqueue(new Callback<VenueList>() {
            @Override
            public void onResponse(Call<VenueList> call, Response<VenueList> response) {
                if (response.isSuccessful()){

                    String message ="Request successful..";
                    Toast.makeText(DashboardActivity.this,message,Toast.LENGTH_LONG).show();
                    VenueList venueList = response.body();
                    venueResponses = new ArrayList<>(Arrays.asList(venueList.getVenues()));
                    DashboardActivity.CustomAdapter customAdapter = new DashboardActivity.CustomAdapter(venueResponses,DashboardActivity.this);
                    gridView.setAdapter(customAdapter);
                    search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                        @Override
                        public boolean onQueryTextSubmit(String s) {
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String s) {
                            customAdapter.getFilter().filter(s);
                            return true;
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<VenueList> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(DashboardActivity.this,message,Toast.LENGTH_LONG).show();
            }
        });
    }

    public class CustomAdapter extends BaseAdapter implements Filterable {

        private List<VenueResponse> venueResponse;
        private Context context;
        private LayoutInflater layoutInflater;

        public CustomAdapter(List<VenueResponse> venueResponse, Context context) {
            this.venueResponse = venueResponse;
            this.context = context;
            this.layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return venueResponse.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            if (view == null){
                view = layoutInflater.inflate(R.layout.row_item,viewGroup,false);
            }

            ImageView imageView = view.findViewById(R.id.imageView);
            ImageView rating = view.findViewById(R.id.star);
            TextView name = view.findViewById(R.id.name);
            TextView name3 = view.findViewById(R.id.name3);
            TextView name4 = view.findViewById(R.id.name4);
            TextView name6 = view.findViewById(R.id.name6);

            name.setText(venueResponse.get(i).getProperty_name());
            name3.setText(venueResponse.get(i).getContact_phone() + "\n" + venueResponse.get(i).getProperty_website());
            name4.setText(venueResponse.get(i).getAddress() + "\n" + venueResponse.get(i).getMain_address());

            GlideApp.with(context)
                    .load("https://venues.ewawe.com/propertyProfile/" + venueResponse.get(i).getProfile_image())
                    .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .into(imageView);

            GlideApp.with(context)
                    .load(venueResponse.get(i).getVenue_rating())
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    }).into(rating);

            name6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getAllSpaceDetails();
                }
            });


            return view;
        }

        @Override
        public Filter getFilter() {

            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {

                    FilterResults filterResults = new FilterResults();

                    if (charSequence == null || charSequence.length() == 0) {
                        filterResults.count = venueResponse.size();
                        filterResults.values = venueResponse;

                    }else {

                        String search = charSequence.toString().toLowerCase();
                        List<VenueResponse> venues = new ArrayList<>();

                        for (VenueResponse venueResponse1:venueResponse){
                            if (venueResponse1.getProperty_name().contains(search) || venueResponse1.getProperty_description().contains(search) || venueResponse1.getMain_address().contains(search)){
                                venues.add(venueResponse1);
                            }

                            filterResults.count = venues.size();
                            filterResults.values = venues;
                        }
                    }

                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                    venueResponse = (List<VenueResponse>) filterResults.values;
                    notifyDataSetChanged();
                }
            };
            return filter;
        }
    }
}