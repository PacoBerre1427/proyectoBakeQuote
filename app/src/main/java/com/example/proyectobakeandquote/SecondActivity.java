package com.example.proyectobakeandquote;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import androidx.activity.EdgeToEdge;

public class SecondActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    // opcional: mantener el contenedor como campo para reutilizarlo
    private FrameLayout overlayContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Activar edge to edge (si esa clase existe en tu setup)
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_second);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.toolbar);
        overlayContainer = findViewById(R.id.contenedorOverlay); // asegúrate que existe en el XML

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                // NOTA: usamos SecondActivity.this.getLayoutInflater() y SecondActivity.this.findViewById()
                // pero como overlayContainer es campo, lo usamos directamente
                if (id == R.id.nav_cotizar) {
                    overlayContainer.removeAllViews();
                    View overlayView = SecondActivity.this.getLayoutInflater()
                            .inflate(R.layout.activity_vista_cotizar, overlayContainer, false);
                    overlayContainer.addView(overlayView);
                    overlayContainer.setVisibility(View.VISIBLE);

                    Button btnCerrar = overlayView.findViewById(R.id.btnCerrarOverlay);
                    if (btnCerrar != null) {
                        btnCerrar.setOnClickListener(v -> overlayContainer.setVisibility(View.GONE));
                    }

                } else if (id == R.id.nav_inventario) {
                    overlayContainer.removeAllViews();
                    View overlayView = SecondActivity.this.getLayoutInflater()
                            .inflate(R.layout.activity_vista_inventario, overlayContainer, false);
                    overlayContainer.addView(overlayView);
                    overlayContainer.setVisibility(View.VISIBLE);

                } else if (id == R.id.nav_agendar) {
                    overlayContainer.removeAllViews();
                    View overlayView = SecondActivity.this.getLayoutInflater()
                            .inflate(R.layout.activity_vista_agendar, overlayContainer, false);
                    overlayContainer.addView(overlayView);
                    overlayContainer.setVisibility(View.VISIBLE);

                } else if (id == R.id.nav_cotizaciones) {
                    overlayContainer.removeAllViews();
                    View overlayView = SecondActivity.this.getLayoutInflater()
                            .inflate(R.layout.activity_vista_cotizaciones, overlayContainer, false);
                    overlayContainer.addView(overlayView);
                    overlayContainer.setVisibility(View.VISIBLE);
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        // Ajustar padding para edge-to-edge a la vista principal
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
