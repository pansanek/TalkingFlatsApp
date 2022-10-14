package com.flats.talkingflatsapp;

import android.Manifest;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.core.content.res.ResourcesCompat;

import com.yandex.mapkit.MapKit;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.layers.ObjectEvent;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.CompositeIcon;
import com.yandex.mapkit.map.IconStyle;
import com.yandex.mapkit.map.MapWindow;
import com.yandex.mapkit.map.RotationType;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.mapkit.user_location.UserLocationLayer;
import com.yandex.mapkit.user_location.UserLocationObjectListener;
import com.yandex.mapkit.user_location.UserLocationView;
import com.yandex.runtime.image.ImageProvider;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Random;

public class UserLocationActivity extends Activity implements UserLocationObjectListener {

    private final String MAPKIT_API_KEY = "cb7bdd48-6aa7-4812-ba6e-72902c458a81";

    private MapView mapView;
    private UserLocationLayer userLocationLayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MapKitFactory.setApiKey(MAPKIT_API_KEY);
        MapKitFactory.initialize(this);
        setContentView(R.layout.user_location);
        super.onCreate(savedInstanceState);
        mapView = findViewById(R.id.mapview);
        mapView.getMap().setRotateGesturesEnabled(false);
        mapView.getMap().move(new CameraPosition(new Point(0, 0), 14, 0, 0));

        MapKit mapKit = MapKitFactory.getInstance();
        userLocationLayer = mapKit.createUserLocationLayer(mapView.getMapWindow());
        userLocationLayer.setVisible(true);
        userLocationLayer.setHeadingEnabled(true);

        userLocationLayer.setObjectListener(this);



        Random rnd = new Random();
        double raw_latitude = (55.721 + rnd.nextDouble() * 0.3);
        double raw_longitude = (37.603 + rnd.nextDouble() * 0.3);
        MathContext context = new MathContext(8, RoundingMode.HALF_UP);
        BigDecimal rounded_latitude = new BigDecimal(raw_latitude, context);
        BigDecimal rounded_longitude = new BigDecimal(raw_longitude, context);
        double latitude = Double.parseDouble(String.valueOf(rounded_latitude));
        double longitude = Double.parseDouble(String.valueOf(rounded_longitude));

        Point newpoint = new Point(latitude, longitude);
        drawLocationMark(newpoint, mapView);

    }

    @Override
    protected void onStop() {
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }

    @Override
    public void onObjectAdded(UserLocationView userLocationView) {
        userLocationLayer.setAnchor(
                new PointF((float)(mapView.getWidth() * 0.5), (float)(mapView.getHeight() * 0.5)),
                new PointF((float)(mapView.getWidth() * 0.5), (float)(mapView.getHeight() * 0.83)));

        userLocationView.getArrow().setIcon(ImageProvider.fromResource(
                this, R.drawable.user_arrow));

        CompositeIcon pinIcon = userLocationView.getPin().useCompositeIcon();

        pinIcon.setIcon(
                "icon",
                ImageProvider.fromResource(this, R.drawable.ic_baseline_map_24),
                new IconStyle().setAnchor(new PointF(0f, 0f))
                        .setRotationType(RotationType.ROTATE)
                        .setZIndex(0f)
                        .setScale(1f)
        );

        pinIcon.setIcon(
                "pin",
                ImageProvider.fromResource(this, R.drawable.search_result),
                new IconStyle().setAnchor(new PointF(0.5f, 0.5f))
                        .setRotationType(RotationType.ROTATE)
                        .setZIndex(1f)
                        .setScale(0.5f)
        );

        userLocationView.getAccuracyCircle().setFillColor(Color.BLUE & 0x99ffffff);
    }

    @Override
    public void onObjectRemoved(UserLocationView view) {
    }

    @Override
    public void onObjectUpdated(UserLocationView view, ObjectEvent event) {
    }

    // TODO: Map mark correct shift
    private void drawLocationMark(Point point, MapView mapview) {
        mapview.getMap().getMapObjects().addPlacemark(new Point(point.getLatitude(),
                point.getLongitude()), ImageProvider.fromBitmap(getBitmap()));
    }

    public Bitmap getBitmap() {
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_map_marker, null);
        return com.flats.talkingflatsapp.BitmapUtils.drawableToBitmap(drawable);
    }

    public void OnClickEndDay(View view) {
        Toast.makeText(getApplicationContext(), "Окончание рабочего дня", Toast.LENGTH_LONG).show();
    }
}