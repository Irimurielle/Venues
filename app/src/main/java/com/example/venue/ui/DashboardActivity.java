package com.example.venue.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import com.bumptech.glide.request.target.Target;
import com.example.venue.GlideApp;
import com.example.venue.R;
import com.example.venue.models.Categories;
import com.example.venue.models.CategoriesList;
import com.example.venue.models.SpaceDetailsResponse;
import com.example.venue.models.VenueList;
import com.example.venue.models.VenueResponse;
import com.example.venue.services.ApiClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;/*getAllSpaceDetails1();*/

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
    @BindView(R.id.gridLayout)
    GridView griddy;

    private List<VenueResponse> venueResponses = new ArrayList<>();
    private List<SpaceDetailsResponse> spaceDetailsResponses = new ArrayList<>();
    private List<Categories> categories = new ArrayList<>();
    Categories category;

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
            }
        });

        conference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this,MoreVenuesActivity.class);;
                startActivity(intent);
            }
        });

        wedding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this,MoreVenuesActivity.class);
                startActivity(intent);
            }
        });

        office.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this,MoreVenuesActivity.class);
                startActivity(intent);
            }
        });

        party.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this,MoreVenuesActivity.class);
                startActivity(intent);
            }
        });


        getAllVenues();

        /*getCategory();*/

        CustomAdapter customAdapter;
    }

    private void getCategory() {
        Call<CategoriesList> categoriesListCall = ApiClient.getCategoryService().getAllSearchedCategory(6);
        categoriesListCall.enqueue(new Callback<CategoriesList>() {
            @Override
            public void onResponse(Call<CategoriesList> call, Response<CategoriesList> response) {
                if (response.isSuccessful()){
                    String message ="Request successful..";
                    Toast.makeText(DashboardActivity.this,message,Toast.LENGTH_LONG).show();
                    CategoriesList categoriesList = response.body();
                    categories = new ArrayList<>(Arrays.asList(categoriesList.getVenues()));
                    spaceDetailsResponses = new ArrayList<>(Arrays.asList(categoriesList.getSpace_details()));
                    DashboardActivity.CustomAdapter1 customAdapter1 = new DashboardActivity.CustomAdapter1(categories,spaceDetailsResponses,DashboardActivity.this);
                    griddy.setAdapter(customAdapter1);
                }else{
                    String message ="an error occurred try again later..";
                    Toast.makeText(DashboardActivity.this,message,Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<CategoriesList> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(DashboardActivity.this,message,Toast.LENGTH_LONG).show();
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

    public class CustomAdapter1 extends BaseAdapter{

        private List<Categories> categories;
        private List<SpaceDetailsResponse> spaceDetailsResponse;
        private Context context;
        private LayoutInflater layoutInflater;

        public CustomAdapter1(List<Categories> categories, List<SpaceDetailsResponse> spaceDetailsResponse, Context context) {
            this.categories = categories;
            this.spaceDetailsResponse = spaceDetailsResponse;
            this.context = context;
            this.layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return categories.size();
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
            TextView name = view.findViewById(R.id.name);

            name.setText(categories.get(i).getSpace_category_name());

            GlideApp.with(context)
                    .load("https://venues.ewawe.com/propertyProfile/" + spaceDetailsResponse.get(i).getSpace_profile())
                    .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .into(imageView);


            return view;
        }
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
            TextView rating = view.findViewById(R.id.star);
            TextView name = view.findViewById(R.id.name);
            TextView name3 = view.findViewById(R.id.name3);
            TextView name4 = view.findViewById(R.id.name4);
            TextView name6 = view.findViewById(R.id.name6);

            name.setText(venueResponse.get(i).getProperty_name());
            name3.setText(venueResponse.get(i).getProperty_website());
            rating.setText(venueResponse.get(i).getVenue_rating());
            name4.setText(venueResponse.get(i).getAddress() + "\n" + venueResponse.get(i).getMain_address());

            GlideApp.with(context)
                    .load("https://venues.ewawe.com/propertyProfile/" + venueResponse.get(i).getProfile_image())
                    .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .into(imageView);

            name6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,VenueActivity.class);
                    intent.putExtra("venue",venueResponse.get(i));
                    context.startActivity(intent);
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