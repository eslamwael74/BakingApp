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
import android.widget.Toast;

import com.google.android.exoplayer2.C;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by eslamwael74 on 01/11/17.
 */

public class RecipeDetailsFragment extends Fragment implements ExoPlayer.EventListener {
    int type = 0;

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

    @BindView(R.id.img)
    ImageView imageView;

    @OnClick(R.id.lin_next)
    void clickNext() {

        position = C.TIME_UNSET;
//        stopExo();


        if (id == steps.size() - 1) {
            return;
        }
        id++;
        for (int i = 0; i < steps.size(); i++) {

            if (id == steps.get(i).getId()) {

                step = new Step(id, steps.get(i).getShortDescription(), steps.get(i).getDescription(), steps.get(i).getVideoURL(),steps.get(i).getThumbnailURL());

                if (!step.getVideoURL().equals(""))
                    type = 0;
                else
                    type = 1;

                getStep(step, type);


            }
        }


    }

    @BindView(R.id.lin_perv)
    LinearLayout linPerv;

    @OnClick(R.id.lin_perv)
    void clickPerv() {

        position = C.TIME_UNSET;
//        stopExo();


        if (id == 0) {
            return;
        }
        id--;
        for (int i = 0; i < steps.size(); i++) {

            if (id == steps.get(i).getId()) {

                step = new Step(id, steps.get(i).getShortDescription(), steps.get(i).getDescription(), steps.get(i).getVideoURL(),steps.get(i).getThumbnailURL());

                if (!step.getVideoURL().equals(""))
                    type = 0;
                else
                    type = 1;

                getStep(step, type);

            }
        }
    }

    @BindView(R.id.rel_placeholder)
    RelativeLayout relPlaceHolder;


    ArrayList<Step> steps;
    SimpleExoPlayer player;
    Step step;
    int id;
    long position = C.TIME_UNSET;
    Uri videoUri;
    String PLAYER_POSITION_KEY = "playerPosition";

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

        if (savedInstanceState != null) {
            position = savedInstanceState.getLong(PLAYER_POSITION_KEY, C.TIME_UNSET);
            id = savedInstanceState.getInt("idr", 1);

            getStep(id);
        } else {

            id = getArguments().getInt("id");

            getStep(id);

        }

        return view;
    }

    void getStep(int id) {

        for (int i = 0; i < steps.size(); i++) {

            if (id == steps.get(i).getId()) {

                step = new Step(id, steps.get(i).getShortDescription(), steps.get(i).getDescription(), steps.get(i).getVideoURL(),steps.get(i).getThumbnailURL());

//                Toast.makeText(getActivity(), "VideoURL" + step.getVideoURL(), Toast.LENGTH_SHORT).show();

                if (!step.getVideoURL().equals(""))
                    type = 0;
                else
                    type = 1;

                getStep(step, type);


            }
        }

    }


    public void getStep(Step step, int type) {

        if (step.getThumbnailURL() == null){
            Picasso.with(getActivity())
                    .load(step.getThumbnailURL())
                    .placeholder(R.drawable.android_placeholder)
                    .error(R.drawable.android_placeholder)
                    .into(imageView);
        }

        tvStepName.setText(step.getShortDescription());
        tvStep.setText(step.getDescription());

        if (type == 0) {
            videoUri = Uri.parse(step.getVideoURL());
            relPlaceHolder.setVisibility(View.GONE);
            simpleExoPlayer.setVisibility(View.VISIBLE);
            setupExo(videoUri);
        } else {
            relPlaceHolder.setVisibility(View.VISIBLE);
            simpleExoPlayer.setVisibility(View.GONE);
        }
        type = 0;
        this.type = 0;
        this.step = null;
    }


    public void setupExo(Uri videoUri) {

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

        MediaSource videoSource = new ExtractorMediaSource(videoUri,
                dataSourceFactory, extractorsFactory, null, null);

        if (position != C.TIME_UNSET) {
            player.seekTo(position);
        }

        player.addListener(this);
        player.prepare(videoSource);
        simpleExoPlayer.requestFocus();
        player.setPlayWhenReady(true);      //play video when ready


    }

    @Override
    public void onPause() {
        super.onPause();

        if (player != null) {
            position = player.getCurrentPosition();
            stopExo();
        }

    }

    void stopExo() {
        if (player != null) {

            player.stop();
            player.release();
            player.setPlayWhenReady(false); //pause a video
            player = null;

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(PLAYER_POSITION_KEY, position);
        outState.putInt("idr", id);
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
