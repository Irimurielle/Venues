package com.example.venue.ui;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
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
import com.example.venue.models.SpaceDetailsResponse;
import com.example.venue.models.SpaceFacilityResponse;
import com.example.venue.models.SpaceGalleryResponse;
import com.example.venue.models.SpaceSeatArrangeResponse;
import com.example.venue.models.VenueResponse;
import com.example.venue.services.ApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VenueActivity extends FragmentActivity/* implements OnMapReadyCallback*/{

    /*@BindView(R.id.grid)
    GridView gridview;*/
    /*@BindView(R.id.image)
    ImageView imageView;
    @BindView(R.id.close)
    ImageView close;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.textView18)
    TextView textView18;
    @BindView(R.id.name4)
    TextView name4;
    @BindView(R.id.name6)
    TextView name6;*/
    @BindView(R.id.gridview)
    GridView grid;
    /*GoogleMap map;*/
    /*Context context;*/
    private List<SpaceDetailsResponse> spaceDetailsResponses =new ArrayList<>();
    private List<SpaceGalleryResponse> spaceGalleryResponses =new ArrayList<>();
    private List<SpaceFacilityResponse> spaceFacilityResponses =new ArrayList<>();
    private List<SpaceSeatArrangeResponse> spaceSeatArrangeResponses =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue);
        ButterKnife.bind(this);

        /*SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);*/

        /*close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VenueActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VenueActivity.this, BookingActivity.class);
                startActivity(intent);
                finish();
            }
        });*/

        getAllSpaceDetails();

    }

    private void getAllSpaceDetails(){
        Call<SpaceDetailsList> spaceDetails = ApiClient.getSpaceDetailsService().getAllSpaceDetails(1);
        spaceDetails.enqueue(new Callback<SpaceDetailsList>() {
            @Override
            public void onResponse(Call<SpaceDetailsList> call, Response<SpaceDetailsList> response) {
                if (response.isSuccessful()){
                    String message ="Request successful..";
                    Toast.makeText(VenueActivity.this,message,Toast.LENGTH_LONG).show();
                    SpaceDetailsList spaceDetailsList = response.body();
                    spaceDetailsResponses = new ArrayList<>(Arrays.asList(spaceDetailsList.getSpace_details()));
                    spaceGalleryResponses = new ArrayList<>(Arrays.asList(spaceDetailsList.getSpace_gallery()));
                    spaceFacilityResponses = new ArrayList<>(Arrays.asList(spaceDetailsList.getSpace_facility()));
                    spaceSeatArrangeResponses = new ArrayList<>(Arrays.asList(spaceDetailsList.getSpace_seat_arrange()));
                    VenueActivity.CustomAdapter customAdapter = new VenueActivity.CustomAdapter(spaceDetailsResponses,spaceFacilityResponses,spaceSeatArrangeResponses,VenueActivity.this);
                    grid.setAdapter(customAdapter);
                }else{
                    String message ="an error occurred try again later..";
                    Toast.makeText(VenueActivity.this,message,Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<SpaceDetailsList> call, Throwable t) {
                Log.e("Failed", t.getLocalizedMessage());
            }
        });
    }

    /*@Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng Rwanda= new LatLng(-1.97622, 29.90697);
        MarkerOptions options = new MarkerOptions().position(Rwanda).title("Rwanda");

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Rwanda,10));
        googleMap.addMarker(options);

    }*/

    public class CustomAdapter extends BaseAdapter implements OnMapReadyCallback {

        private List<SpaceDetailsResponse> spaceDetailsResponse;
        private List<SpaceFacilityResponse> spaceFacilityResponse;
        private List<SpaceSeatArrangeResponse> spaceSeatArrangeResponse;
        private Context context;
        private LayoutInflater layoutInflater;

        public CustomAdapter(List<SpaceDetailsResponse> spaceDetailsResponse,List<SpaceFacilityResponse> spaceFacilityResponse,List<SpaceSeatArrangeResponse> spaceSeatArrangeResponse,Context context) {
            this.spaceDetailsResponse = spaceDetailsResponses;
            this.spaceFacilityResponse = spaceFacilityResponse;
            this.spaceSeatArrangeResponse = spaceSeatArrangeResponse;
            this.context = context;
            this.layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return spaceDetailsResponse.size();
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
                view = layoutInflater.inflate(R.layout.venue_row,viewGroup,false);
            }

            ImageView imageView = view.findViewById(R.id.image);
            ImageView close = view.findViewById(R.id.close);
            Button button = view.findViewById(R.id.button);
            TextView name = view.findViewById(R.id.name);
            TextView textView18 = view.findViewById(R.id.textView18);
            TextView name4 = view.findViewById(R.id.name4);
            TextView name6 = view.findViewById(R.id.name6);
            GridView gridview = view.findViewById(R.id.grid);
            GoogleMap map;
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

            name.setText(spaceDetailsResponse.get(i).getSpace_activity());
            textView18.setText(spaceDetailsResponse.get(i).getSpace_description() + "\n" + spaceDetailsResponse.get(i).getSpace_location());
            name6.setText(spaceDetailsResponse.get(i).getCancellation_policy());
            name6.setText(spaceDetailsResponse.get(i).getWorking_hours());

            GlideApp.with(context)
                    .load("https://venues.ewawe.com/propertyProfile/" + spaceDetailsResponse.get(i).getSpace_profile())
                    .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .into(imageView);

            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(VenueActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(VenueActivity.this, BookingActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

            VenueActivity.CustomAdapter1 customAdapter1 = new VenueActivity.CustomAdapter1(spaceGalleryResponses,VenueActivity.this);
            gridview.setAdapter(customAdapter1);

            return view;
        }

        @Override
        public void onMapReady(GoogleMap googleMap) {

            LatLng Rwanda= new LatLng(-1.97622, 29.90697);
            MarkerOptions options = new MarkerOptions().position(Rwanda).title("Rwanda");

            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Rwanda,10));
            googleMap.addMarker(options);

        }
    }

    public class CustomAdapter1 extends BaseAdapter {

        private List<SpaceGalleryResponse> spaceGalleryResponse;
        private Context context;
        private LayoutInflater layoutInflater;

        public CustomAdapter1(List<SpaceGalleryResponse> spaceGalleryResponse,Context context) {
            this.spaceGalleryResponse = spaceGalleryResponses;
            this.context = context;
            this.layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return spaceGalleryResponse.size();
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

            if (view == null) {
                view = layoutInflater.inflate(R.layout.gallery_row, viewGroup, false);
            }

            ImageView image1 = view.findViewById(R.id.image1);

            GlideApp.with(context)
                    .load("https://venues.ewawe.com/propertyProfile/" + spaceGalleryResponse.get(i).getPhotoImage())
                    .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .into(image1);

            return view;
        }
    }
}