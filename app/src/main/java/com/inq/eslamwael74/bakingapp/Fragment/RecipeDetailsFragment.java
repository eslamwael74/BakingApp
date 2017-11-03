package com.inq.eslamwael74.bakingapp.Fragment;


import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.inq.eslamwael74.bakingapp.Adapter.IngredientAdapter;
import com.inq.eslamwael74.bakingapp.Adapter.StepsAdapter;
import com.inq.eslamwael74.bakingapp.Model.Ingredient;
import com.inq.eslamwael74.bakingapp.Model.Recipe;
import com.inq.eslamwael74.bakingapp.Model.Step;
import com.inq.eslamwael74.bakingapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by eslamwael74 on 01/11/17.
 */

public class RecipeDetailsFragment extends Fragment implements ExoPlayer.EventListener {

    @BindView(R.id.back)
    ImageView imgBck;

    @OnClick(R.id.back)
    void backOnClick() {
        getActivity().onBackPressed();
    }

    @BindView(R.id.tv_step_intro)
    TextView tvStepName;

    @BindView(R.id.player_view)
    SimpleExoPlayerView simpleExoPlayer;

    @BindView(R.id.tv_step_desc)
    TextView tvStep;

    @BindView(R.id.lin_next)
    LinearLayout linNext;

    @OnClick(R.id.lin_next)
    void clickNext() {

        if (id == steps.size() - 1){
            return;
        }
        id++;
        for (int i = 0; i < steps.size(); i++) {

            if (id == steps.get(i).getId()) {

                player.setPlayWhenReady(false);

                step = new Step(id, steps.get(i).getShortDescription(), steps.get(i).getDescription(), steps.get(i).getVideoURL());

                getStep(step);


            }
        }


    }

    @BindView(R.id.lin_perv)
    LinearLayout linPerv;

    @OnClick(R.id.lin_perv)
    void clickPerv() {

        if (id == 0){
            return;
        }
        id--;
        for (int i = 0; i < steps.size(); i++) {

            if (id == steps.get(i).getId()) {

                player.setPlayWhenReady(false);

                step = new Step(id, steps.get(i).getShortDescription(), steps.get(i).getDescription(), steps.get(i).getVideoURL());

                getStep(step);

            }
        }
    }

    @BindView(R.id.rel_placeholder)
    RelativeLayout relPlaceHolder;


    ArrayList<Step> steps;
    SimpleExoPlayer player;
    Step step;
    int id;


    public static RecipeDetailsFragment newInstance(ArrayList<Step> steps, int id) {
        RecipeDetailsFragment mAppDetailsFragment = new RecipeDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("steps", steps);
        args.putInt("id", id);
        mAppDetailsFragment.setArguments(args);
        return mAppDetailsFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe_details, container, false);
        ButterKnife.bind(this, view);

        steps = getArguments().getParcelableArrayList("steps");
        id = getArguments().getInt("id");

        for (int i = 0; i < steps.size(); i++) {

            if (id == steps.get(i).getId()) {

                step = new Step(id, steps.get(i).getShortDescription(), steps.get(i).getDescription(), steps.get(i).getVideoURL());

                getStep(step);


            }
        }

        return view;
    }

    public void getStep(Step step) {

        tvStepName.setText(step.getShortDescription());
        tvStep.setText(step.getDescription());

        if (step.getVideoURL() != "") {
            relPlaceHolder.setVisibility(View.GONE);
            simpleExoPlayer.setVisibility(View.VISIBLE);
            setupExo(step.getVideoURL());
        } else {
            relPlaceHolder.setVisibility(View.VISIBLE);
            simpleExoPlayer.setVisibility(View.GONE);
        }
    }


    public void setupExo(String videoUrl) {


        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveVideoTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);


        LoadControl loadControl = new DefaultLoadControl();


        player = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector, loadControl);

        simpleExoPlayer.setPlayer(player);
        simpleExoPlayer.setKeepScreenOn(true);
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getActivity(), Util.getUserAgent(getActivity(), "ExoPlayer"));

        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        MediaSource videoSource = new ExtractorMediaSource(Uri.parse(videoUrl),
                dataSourceFactory, extractorsFactory, null, null);

        player.addListener(this);
        player.prepare(videoSource);
        simpleExoPlayer.requestFocus();
        player.setPlayWhenReady(true);      //play video when ready

    }

    @Override
    public void onPause() {
        super.onPause();
        if (player != null) {
            player.setPlayWhenReady(false); //pause a video
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.release();
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity() {

    }
}
