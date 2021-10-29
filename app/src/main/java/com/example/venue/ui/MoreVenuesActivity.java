package com.example.venue.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.request.target.Target;
import com.example.venue.GlideApp;
import com.example.venue.R;
import com.example.venue.models.Categories;
import com.example.venue.models.MoreVenuesList;
import com.example.venue.models.MoreVenuesResponse;
import com.example.venue.services.ApiClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoreVenuesActivity extends AppCompatActivity {

    @BindView(R.id.filterbutton)
    Button filterButton;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.datetime)
    Button date;
    @BindView(R.id.gridview)
    GridView gridView;
    private int mYear, mMonth, mDay;
    Dialog dialog;
    private List<MoreVenuesResponse> moreVenuesResponses =new ArrayList<>();
    private List<Categories> categories =new ArrayList<>();
    Categories category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_venues);
        ButterKnife.bind(this);

        dialog = new Dialog(this);
        category = (Categories) getIntent().getSerializableExtra("category");

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MoreVenuesActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MoreVenuesActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                String sDate=dayOfMonth+ "-"+(monthOfYear + 1)+"-"+year;
                                date.setText(sDate);

                            }
                        }, mDay, mMonth, mYear);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                datePickerDialog.show();
            }
        });
        getAllMoreVenues();
        /*getCategories();*/
    }

    /*private void getCategories() {
        Call<CategoriesList> categoriesListCall = ApiClient.getCategoryService().getAllSearchedCategory(category.getId());
        categoriesListCall.enqueue(new Callback<CategoriesList>() {
            @Override
            public void onResponse(Call<CategoriesList> call, Response<CategoriesList> response) {
                if (response.isSuccessful()){
                    String message ="Request successful..";
                    Toast.makeText(MoreVenuesActivity.this,message,Toast.LENGTH_LONG).show();
                    CategoriesList categoriesList = response.body();
                    categories = new ArrayList<>(Arrays.asList(categoriesList.getVenues()));
                    MoreVenuesActivity.CustomAdapter customAdapter = new MoreVenuesActivity.CustomAdapter(categories,MoreVenuesActivity.this);
                    gridView.setAdapter(customAdapter);

                }else{
                    String message ="an error occurred try again later..";
                    Toast.makeText(MoreVenuesActivity.this,message,Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<CategoriesList> call, Throwable t) {
                Log.e("Failed", t.getLocalizedMessage());
            }
        });
    }*/

    private void getAllMoreVenues() {
        Call<MoreVenuesList> moreVenues = ApiClient.getMoreVenueService().getAllMoreVenues();
        moreVenues.enqueue(new Callback<MoreVenuesList>() {
            @Override
            public void onResponse(Call<MoreVenuesList> call, Response<MoreVenuesList> response) {
                if (response.isSuccessful()){
                    String message ="Request successful..";
                    Toast.makeText(MoreVenuesActivity.this,message,Toast.LENGTH_LONG).show();
                    MoreVenuesList moreVenuesList = response.body();
                    moreVenuesResponses = new ArrayList<>(Arrays.asList(moreVenuesList.getMoreVenues()));
                    MoreVenuesActivity.CustomAdapter customAdapter = new MoreVenuesActivity.CustomAdapter(moreVenuesResponses,MoreVenuesActivity.this);
                    gridView.setAdapter(customAdapter);

                }else{
                    String message ="an error occurred try again later..";
                    Toast.makeText(MoreVenuesActivity.this,message,Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<MoreVenuesList> call, Throwable t) {
                Log.e("Failed", t.getLocalizedMessage());
            }
        });
    }

    /*public class CustomAdapter extends BaseAdapter {

        private List<Categories> categories;
        private Context context;
        private LayoutInflater layoutInflater;

        public CustomAdapter(List<Categories> categories, Context context) {
            this.categories = categories;
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
                view = layoutInflater.inflate(R.layout.row_more_item,viewGroup,false);
            }

            ImageView imageView = view.findViewById(R.id.image2);
            ImageView rating = view.findViewById(R.id.star1);
            ImageView details = view.findViewById(R.id.img5);
            TextView name = view.findViewById(R.id.text3);
            TextView category_name = view.findViewById(R.id.text4);
            TextView tests = view.findViewById(R.id.text5);

            details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getAllSpaceDetails();
                }
            });

            name.setText(categories.get(i).getSpace_activity() );
            category_name.setText(categories.get(i).getSpace_category_name());

            GlideApp.with(context)
                    .load("https://venues.ewawe.com/propertyProfile/" + categories.get(i).getSpace_profile())
                    .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .into(imageView);

            return view;
        }
    }*/

    public class CustomAdapter extends BaseAdapter {

        private List<MoreVenuesResponse> moreVenuesResponse;
        private Context context;
        private LayoutInflater layoutInflater;

        public CustomAdapter(List<MoreVenuesResponse> moreVenuesResponse, Context context) {
            this.moreVenuesResponse = moreVenuesResponse;
            this.context = context;
            this.layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return moreVenuesResponse.size();
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
                view = layoutInflater.inflate(R.layout.row_more_item,viewGroup,false);
            }

            ImageView imageView = view.findViewById(R.id.image2);
            ImageView rating = view.findViewById(R.id.star1);
            ImageView details = view.findViewById(R.id.img5);
            TextView name = view.findViewById(R.id.text3);
            TextView category_name = view.findViewById(R.id.text4);
            TextView tests = view.findViewById(R.id.text5);

            details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,DetailsActivity.class);
                    intent.putExtra("detail",moreVenuesResponse.get(i));
                    context.startActivity(intent);
                }
            });

            name.setText(moreVenuesResponse.get(i).getVenue_name() );
            category_name.setText(moreVenuesResponse.get(i).getSpace_category_name());

            GlideApp.with(context)
                    .load("https://venues.ewawe.com/propertyProfile/" + moreVenuesResponse.get(i).getProfile_image())
                    .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .into(imageView);

            GlideApp.with(context)
                    .load("https://venues.ewawe.com/propertyProfile/" + moreVenuesResponse.get(i).getVenue_rating())
                    .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .into(rating);

            return view;
        }
    }

    public void ShowPopup(View view) {

        ListView listView1 = (ListView) findViewById(R.id.listview1);
        ArrayList<String> stringArrayList1 = new ArrayList<String>();
        stringArrayList1.add("1 to 10");
        stringArrayList1.add("10 to 50");
        stringArrayList1.add("50 to 100");
        stringArrayList1.add("100 to 200");
        stringArrayList1.add("200 to 500");

        ListAdapter listAdapter1 = new ArrayAdapter<String>(this,R.layout.list_row,stringArrayList1);
        listView1.setAdapter(listAdapter1);

        ListView listView2 = (ListView) findViewById(R.id.listview2);
        ArrayList<String> stringArrayList2 = new ArrayList<String>();
        stringArrayList1.add("Elevator");
        stringArrayList1.add("Fireplace");
        stringArrayList1.add("wifi");
        stringArrayList1.add("Parking");

        ListAdapter listAdapter2 = new ArrayAdapter<String>(this,R.layout.list_row,stringArrayList2);
        listView1.setAdapter(listAdapter2);

        ListView listView3 = (ListView) findViewById(R.id.listview3);
        ArrayList<String> stringArrayList3 = new ArrayList<String>();
        stringArrayList1.add("Co-Working Space");
        stringArrayList1.add("Conference");
        stringArrayList1.add("Meeting");
        stringArrayList1.add("Party");
        stringArrayList1.add("Church");

        ListAdapter listAdapter3 = new ArrayAdapter<String>(this,R.layout.list_row,stringArrayList3);
        listView1.setAdapter(listAdapter3);

        ListView listView4 = (ListView) findViewById(R.id.listview4);
        ArrayList<String> stringArrayList4 = new ArrayList<String>();
        stringArrayList1.add("Bathroom");
        stringArrayList1.add("Bedroom");
        stringArrayList1.add("Dining Area");

        ListAdapter listAdapter4 = new ArrayAdapter<String>(this,R.layout.list_row,stringArrayList4);
        listView1.setAdapter(listAdapter4);

        dialog.setContentView(R.layout.filter);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.show();
    }

}