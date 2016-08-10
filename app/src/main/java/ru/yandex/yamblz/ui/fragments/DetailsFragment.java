package ru.yandex.yamblz.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import ru.yandex.yamblz.R;

/**
 * Created by Volha on 10.08.2016.
 */

public class DetailsFragment extends BaseFragment {

    private final static String TAG_COLOR = "tag_color";

    public static DetailsFragment newInstance(int color) {

        Bundle args = new Bundle();
        args.putInt(TAG_COLOR, color);
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @BindView(R.id.tvColor)
    TextView tvColor;

    @BindView(R.id.tvContent)
    TextView tvContent;

    @BindView(R.id.llBackground)
    View background;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int color = getArguments().getInt(TAG_COLOR);
        ViewCompat.setTransitionName(tvColor, "color" + color);

        tvColor.setText("#".concat(Integer.toHexString(color)));
        tvColor.setBackgroundColor(color);
        tvContent.setBackgroundColor(color);
        background.setBackgroundColor(color);
    }
}
