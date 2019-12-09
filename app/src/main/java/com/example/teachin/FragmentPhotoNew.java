package com.example.teachin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import com.example.teachin.FragmentPhoto;
import com.example.teachin.ImageAdapter;
import com.example.teachin.R;

public class FragmentPhotoNew extends FragmentPhoto {

    private View v;

    public FragmentPhotoNew() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.photo_fragment, container, false);
        ViewPager viewPager = v.findViewById(R.id.viewPager_photo);
        ImageAdapterNew adapter = new ImageAdapterNew(this.getActivity());
        viewPager.setAdapter(adapter);
        return v;
    }
}
