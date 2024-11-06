package com.example.wetravel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wetravel.adapter.RecentsAdapter;
import com.example.wetravel.adapter.TopPlacesAdapter;
import com.example.wetravel.fragments.ProfileFragment;
import com.example.wetravel.fragments.ReservationFragment;
import com.example.wetravel.fragments.StatisticsFragment;
import com.example.wetravel.model.RecentsData;
import com.example.wetravel.model.TopPlacesData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recentRecycler, topPlacesRecycler;
    RecentsAdapter recentsAdapter;
    TopPlacesAdapter topPlacesAdapter;

    ImageView logout_btn, mainMenu, profileMenu, reservationsMenu, calculationsMenu;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Reference to the main layout file

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser == null) {
            // If not logged in, redirect to LoginActivity
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            finish();
            return; // Prevent further execution of onCreate
        }

        logout_btn = findViewById(R.id.logout_bt);

        mainMenu = findViewById(R.id.main_menu);
        profileMenu = findViewById(R.id.profile_menu);
        reservationsMenu = findViewById(R.id.reservations_menu);
        calculationsMenu = findViewById(R.id.calculations_menu);

        // Initialize RecyclerViews
        initializeRecyclerViews();

        // Set up logout button
        setupLogoutButton();

        // Set default view to main content
        showMainContent();

        mainMenu.setImageResource(R.drawable.ic_action_home1);

        // Set up menu click listeners
        setupMenuClickListeners();
    }

    private void initializeRecyclerViews() {
        List<RecentsData> recentsDataList = new ArrayList<>();
        recentsDataList.add(new RecentsData("AM Lake", "India", "From 200€", R.drawable.recentimage1));
        recentsDataList.add(new RecentsData("Nilgiri Hills", "India", "From 300€", R.drawable.recentimage2));
        recentsDataList.add(new RecentsData("AM Lake", "India", "From 200€", R.drawable.recentimage1));
        recentsDataList.add(new RecentsData("Nilgiri Hills", "India", "From 300€", R.drawable.recentimage2));
        recentsDataList.add(new RecentsData("AM Lake", "India", "From 200€", R.drawable.recentimage1));
        recentsDataList.add(new RecentsData("Nilgiri Hills", "India", "From 300€", R.drawable.recentimage2));

        setRecentRecycler(recentsDataList);

        List<TopPlacesData> topPlacesDataList = new ArrayList<>();
        topPlacesDataList.add(new TopPlacesData("Kasimir Hill", "India", "€200 - €500", R.drawable.topplaces));
        topPlacesDataList.add(new TopPlacesData("Kasimir Hill", "India", "€200 - €500", R.drawable.topplaces));
        topPlacesDataList.add(new TopPlacesData("Kasimir Hill", "India", "€200 - €500", R.drawable.topplaces));
        topPlacesDataList.add(new TopPlacesData("Kasimir Hill", "India", "€200 - €500", R.drawable.topplaces));
        topPlacesDataList.add(new TopPlacesData("Kasimir Hill", "India", "€200 - €500", R.drawable.topplaces));

        setTopPlacesRecycler(topPlacesDataList);
    }

    private void setupLogoutButton() {
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                Intent logout = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(logout);
                finish();
            }
        });
    }

    private void setupMenuClickListeners() {
        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMainContent();
                highlightSelectedIcon(mainMenu);
            }
        });

        profileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new ProfileFragment());
                highlightSelectedIcon(profileMenu);
            }
        });

        reservationsMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new ReservationFragment());
                highlightSelectedIcon(reservationsMenu);
            }
        });

        calculationsMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new StatisticsFragment());
                highlightSelectedIcon(calculationsMenu);
            }
        });
    }

    private void setRecentRecycler(List<RecentsData> recentsDataList) {
        recentRecycler = findViewById(R.id.recent_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recentRecycler.setLayoutManager(layoutManager);
        recentsAdapter = new RecentsAdapter(this, recentsDataList);
        recentRecycler.setAdapter(recentsAdapter);
    }

    private void setTopPlacesRecycler(List<TopPlacesData> topPlacesDataList) {
        topPlacesRecycler = findViewById(R.id.top_places_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        topPlacesRecycler.setLayoutManager(layoutManager);
        topPlacesAdapter = new TopPlacesAdapter(this, topPlacesDataList);
        topPlacesRecycler.setAdapter(topPlacesAdapter);
    }

    private void highlightSelectedIcon(ImageView selectedIcon) {
// Reset all icons to non-highlighted state
        resetIcons();

// Change the selected icon to its highlighted version
        if (selectedIcon == mainMenu) {
            mainMenu.setImageResource(R.drawable.ic_action_home1);
        } else if (selectedIcon == profileMenu) {
            profileMenu.setImageResource(R.drawable.ic_action_person);
        } else if (selectedIcon == reservationsMenu) {
            reservationsMenu.setImageResource(R.drawable.ic_action_assignment);
        } else if (selectedIcon == calculationsMenu) {
            calculationsMenu.setImageResource(R.drawable.ic_action_assessment1);
        }
    }

    private void resetIcons() {
// Set each icon back to its default (non-highlighted) drawable
        mainMenu.setImageResource(R.drawable.ic_action_home);
        profileMenu.setImageResource(R.drawable.ic_action_person1);
        reservationsMenu.setImageResource(R.drawable.ic_action_assignment1);
        calculationsMenu.setImageResource(R.drawable.ic_action_assessment);
    }

    private void loadFragment(Fragment fragment) {
        // Hide main content layout
        findViewById(R.id.main_content_layout).setVisibility(View.GONE);
        // Show fragment container
        findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);

        // Replace the current fragment in the fragment_container
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    private void showMainContent() {
        // Show main content layout
        findViewById(R.id.main_content_layout).setVisibility(View.VISIBLE);
        // Hide fragment container
        findViewById(R.id.fragment_container).setVisibility(View.GONE);

        // Optional: Remove any existing fragments
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (currentFragment != null) {
            getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
        }
    }
}
