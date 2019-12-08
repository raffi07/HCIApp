package com.example.teachin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class FragmentPhoto extends Fragment {
    private View v;

    public FragmentPhoto() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.photo_fragment, container, false);
        ViewPager viewPager = v.findViewById(R.id.viewPager_photo);
        ImageAdapter adapter = new ImageAdapter(this.getActivity());
        viewPager.setAdapter(adapter);
        return v;
    }
}
