package ru.yandex.yamblz.ui.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import ru.yandex.yamblz.R;
import ru.yandex.yamblz.ui.adapters.ColorRecyclerViewAdapter;
import ru.yandex.yamblz.ui.other.OnColorItemClickListener;

public class ContentFragment extends BaseFragment implements OnColorItemClickListener {

    @BindView(R.id.colorsList)
    RecyclerView colorsList;

    ColorRecyclerViewAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ColorRecyclerViewAdapter(this);
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        colorsList.setAdapter(adapter);
        colorsList.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onColorClick(int color, View view) {
        DetailsFragment details = DetailsFragment.newInstance(color);
        setAllowEnterTransitionOverlap(false);
        setAllowReturnTransitionOverlap(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setExitTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.explode));
            details.setEnterTransition(TransitionInflater.from(getContext()).inflateTransition(R.transition.content_enter_transition)); // not worked =(
            details.setExitTransition(TransitionInflater.from(getContext()).inflateTransition(R.transition.content_exit_transition));
            details.setSharedElementEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.shared_element_transition));
            details.setSharedElementReturnTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.shared_element_transition));
        }

        getFragmentManager()
                .beginTransaction()
                .addSharedElement(view, "color" + color)
                .addToBackStack(null)
                .replace(R.id.main_frame_layout, details)
                .commit();
    }
}
