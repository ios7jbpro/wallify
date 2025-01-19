package com.ios7.wallify;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.WallpaperManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.palette.graphics.Palette;

import com.ios7.wallify.databinding.ActivityCropWallpaperBinding;

import java.io.IOException;


public class CropWallpaper extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityCropWallpaperBinding binding;
    private com.canhub.cropper.CropImageView cropImageView;
    private LinearLayout cropScreen;
    private LinearLayout confirmScreen;
    private TextView textViewTopbar;
    private TextView nextButton;
    private TextView goBackButton;
    private TextView setWallpaperButton;
    private ImageView cropPreview;
    private ImageView imageview1;
    private ImageView imageview3;
    private TextView textview2;
    private TextView time2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCropWallpaperBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        cropImageView = findViewById(R.id.cropImageView);
        cropScreen = findViewById(R.id.cropScreen);
        confirmScreen = findViewById(R.id.confirmScreen);
        textViewTopbar = findViewById(R.id.textViewTopbar);
        nextButton = findViewById(R.id.nextButton);
        goBackButton = findViewById(R.id.goBackButton);
        setWallpaperButton = findViewById(R.id.setWallpaperButton);
        cropPreview = findViewById(R.id.cropPreview);
        imageview1 = findViewById(R.id.imageview1);
        imageview3 = findViewById(R.id.imageview3);
        textview2 = findViewById(R.id.textview2);
        time2 = findViewById(R.id.time2);
        confirmScreen.setVisibility(View.GONE);
        goBackButton.setVisibility(View.GONE);
        setWallpaperButton.setVisibility(View.GONE);
        // Create the bottom sheet to warn the user
        BottomSheetDialog bottomSheet = new BottomSheetDialog(this);
        // Use the custom warning_dialog.xml as the bottomsheet
        View bottomSheetView = getLayoutInflater().inflate(R.layout.warning_dialog, null);
        // Disable default background to get rid of the square corners
        bottomSheet.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        // Set the view to the bottomsheet
        bottomSheet.setContentView(bottomSheetView);
        // Removed since it's stable now
        //bottomSheet.show();
        // Setup the dismiss button by finding the view ID
        MaterialButton dismissButton = bottomSheetView.findViewById(R.id.dissmissButton);
        // Dismiss button click listener
        dismissButton.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View _view) {
                                                 bottomSheet.dismiss();
                                             }
                                         });

        // Next button click listener
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                cropScreen.setVisibility(View.GONE);
                confirmScreen.setVisibility(View.VISIBLE);
                goBackButton.setVisibility(View.VISIBLE);
                setWallpaperButton.setVisibility(View.VISIBLE);
                nextButton.setVisibility(View.GONE);
                textViewTopbar.setText("Preview");
                // Show the cropped preview
                cropPreview.setImageBitmap(cropImageView.getCroppedImage());
                cropPreview.setVisibility(View.GONE);
                imageview1.setImageBitmap(cropImageView.getCroppedImage());
                imageview3.setImageBitmap(cropImageView.getCroppedImage());
                // Extract the color to use it in textview2
                Bitmap bitmap = ((BitmapDrawable) imageview1.getDrawable()).getBitmap();
                Palette.from(bitmap).generate(palette -> {
                    int vibrant = palette.getDominantColor(0x000000); // <=== color you want
                    int vibrantLight = palette.getLightVibrantColor(0x000000);
                    int vibrantDark = palette.getDarkVibrantColor(0x000000);
                    int muted = palette.getMutedColor(0x000000);
                    int mutedLight = palette.getLightMutedColor(0x000000);
                    int mutedDark = palette.getDarkMutedColor(0x000000);
                    // Set the vibrantLight color to textview2
                    textview2.setTextColor(vibrantLight);
                    // Set the vibrantLight color to time2
                    time2.setTextColor(vibrantLight);
                });
            }
        });

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                cropScreen.setVisibility(View.VISIBLE);
                confirmScreen.setVisibility(View.GONE);
                goBackButton.setVisibility(View.GONE);
                setWallpaperButton.setVisibility(View.GONE);
                nextButton.setVisibility(View.VISIBLE);
            }
            });

        setWallpaperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                WallpaperManager wallManager = WallpaperManager.getInstance(getApplicationContext());
                try {
                    Toast.makeText(getApplicationContext(), "Loading in cropped image and setting wallpaper...", Toast.LENGTH_SHORT).show();
                    wallManager.clear(); // Clear the existing wallpaper
                    wallManager.setBitmap(cropImageView.getCroppedImage()); // Set the loaded Bitmap as wallpaper
                } catch (IOException ex) {
                    ex.printStackTrace(); // Handle exceptions
                    Toast.makeText(getApplicationContext(), "Failed to set wallpaper", Toast.LENGTH_SHORT).show();
                }
            }
            });

        // Get the image to load onto the cropper using the passed key from the old activity
        Intent intent = getIntent();
        Uri link = intent.getParcelableExtra("link");
        // Load the link using glide into the view
       // Glide.with(getApplicationContext())
       //         .asBitmap()
       //         .load(link)
       //         .into(binding.cropImageView);
       // cropImageView.setImageBitmap(
       //         Glide.with(getApplicationContext())
       //                 .asBitmap()
       //                 .load(link)
       // );

        try {


            Glide.with(getApplicationContext())
                    .asBitmap()
                    .load(link)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            try {
                            cropImageView.setImageBitmap(resource);
                            } catch (Exception e) {
                                // Show the error in a toast
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                // Copy to clipboard
                                String errorCode = e.getMessage();
                                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                ClipData clip = ClipData.newPlainText("Error:", errorCode);
                                clipboard.setPrimaryClip(clip);
                            }
                        }
                    });
        } catch (Exception e) {
            // Show the error in a toast
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}