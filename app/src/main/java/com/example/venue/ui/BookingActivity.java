package com.example.venue.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.venue.R;
import com.example.venue.models.BookingRequest;
import com.example.venue.models.BookingResponse;
import com.example.venue.services.ApiClient;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingActivity extends AppCompatActivity {

    @BindView(R.id.date)
    EditText arrival_date;
    @BindView(R.id.exitDate)
    EditText depart_date;
    @BindView(R.id.time)
    EditText arrival_time;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.guests)
    EditText guests;
    @BindView(R.id.names)
    EditText names;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.tests)
    EditText tests;
    @BindView(R.id.request)
    EditText request;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.submit)
    Button Button;
    private int mHour, mMinute,mSecond,mYear, mMonth, mDay;
    String name;

    String format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        ButterKnife.bind(this);

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookingActivity.this, VenueActivity.class);
                startActivity(intent);
                finish();
            }
        });

        arrival_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(BookingActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                String sDate=year+ "-"+(monthOfYear + 1)+"-"+dayOfMonth;
                                arrival_date.setText(sDate);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                datePickerDialog.show();
            }
        });

        depart_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(BookingActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                String sDate=year+ "-"+(monthOfYear + 1)+"-"+dayOfMonth;
                                depart_date.setText(sDate);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                datePickerDialog.show();
            }
        });

        arrival_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);
                mSecond=c.get(Calendar.SECOND);
                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(BookingActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                if (hourOfDay == 0) {

                                    hourOfDay += 12;

                                    format = "AM";
                                }
                                else if (hourOfDay == 12) {

                                    format = "PM";

                                }
                                else if (hourOfDay > 12) {

                                    hourOfDay -= 12;

                                    format = "PM";

                                }
                                else {

                                    format = "AM";
                                }
                                arrival_time.setText(hourOfDay + ":" + minute + ":" + mSecond);

                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });

        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBooking(addReservation());
            }
        });
    }

    public BookingRequest addReservation(){
        BookingRequest bookingRequest = new BookingRequest();
        bookingRequest.setVenue_id(1);
        bookingRequest.setHow_many(guests.getText().toString());
        bookingRequest.setGuest_name(names.getText().toString());
        bookingRequest.setGuest_email(email.getText().toString());
        bookingRequest.setGuest_phone(phone.getText().toString());
        bookingRequest.setSpecial_request(request.getText().toString());
        bookingRequest.setArrival_date(arrival_date.getText().toString());
        bookingRequest.setArrival_time(arrival_time.getText().toString());
        bookingRequest.setDepart_date(depart_date.getText().toString());

        boolean validName = isValidName(names);
        boolean validPhone = isValidPhone(phone);
        boolean validGuests = isValidGuests(guests);

        if (!validName || !validPhone || !validGuests);

        return bookingRequest;
    }

    private boolean isValidName(EditText names) {

        if (names.equals("")) {
            names.setError("Please enter your name");
            return false;
        }
        return true;
    }

    private boolean isValidPhone(EditText phone) {

        if (phone.equals("")) {
            phone.setError("Please enter your phone");
            return false;
        }
        return true;
    }

    private boolean isValidGuests(EditText guests) {

        if (guests.equals("")) {
            guests.setError("Please enter the number of your guests");
            return false;
        }
        return true;
    }

    public void saveBooking(BookingRequest bookingRequest){
        Call<BookingResponse> bookingResponseCall = ApiClient.getBookingService().saveBooking(bookingRequest);
        bookingResponseCall.enqueue(new Callback<BookingResponse>() {
            @Override
            public void onResponse(Call<BookingResponse> call, Response<BookingResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(BookingActivity.this, "Booking successful", Toast.LENGTH_LONG).show();
                    /*new RaveUiManager(BookingActivity.this).setAmount(100)
                            .setCurrency("RWF")
                            .setEmail("booking@venues.rw")
                            .setfName("Ewawe")
                            .setlName("Venues")
                            .setNarration("narration")
                            .setPublicKey("FLWPUBK-2233de66d4c708f2a01420a9b04f7060-X")
                            .setEncryptionKey("e0c19c92e929d884f21c2b96")
                            .setTxRef("txRef")
                            .setPhoneNumber("+250786520477", true)
                            .acceptAccountPayments(false)
                            .acceptCardPayments(true)
                            .acceptMpesaPayments(false)
                            .acceptAchPayments(false)
                            .acceptGHMobileMoneyPayments(false)
                            .acceptUgMobileMoneyPayments(false)
                            .acceptZmMobileMoneyPayments(false)
                            .acceptRwfMobileMoneyPayments(true)
                            .acceptSaBankPayments(false)
                            .acceptUkPayments(false)
                            .acceptBankTransferPayments(false)
                            .acceptUssdPayments(false)
                            .acceptBarterPayments(false)
                            .acceptFrancMobileMoneyPayments(false)
                            .allowSaveCardFeature(false)
                            .onStagingEnv(false)
                            .withTheme(R.style.MyCustomTheme)
                            .initialize();*/

                }else{
                    Toast.makeText(BookingActivity.this, "Booking failed", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<BookingResponse> call, Throwable t) {
                Toast.makeText(BookingActivity.this, "Booking unsuccessful"+ t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RaveConstants.RAVE_REQUEST_CODE && data != null) {
            String message = data.getStringExtra("response");
            if (resultCode == RavePayActivity.RESULT_SUCCESS) {
                Toast.makeText(this, "SUCCESS " + message, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(BookingActivity.this,DashboardActivity.class);
                startActivity(intent);
            }
            else if (resultCode == RavePayActivity.RESULT_ERROR) {
                Toast.makeText(this, "ERROR " + message, Toast.LENGTH_SHORT).show();
            }
            else if (resultCode == RavePayActivity.RESULT_CANCELLED) {
                Toast.makeText(this, "CANCELLED " + message, Toast.LENGTH_SHORT).show();
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }*/
}