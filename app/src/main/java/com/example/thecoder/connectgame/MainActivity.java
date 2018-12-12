package com.example.thecoder.connectgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    int[] state = {0,0,0,0,0,0,0,0,0};
    boolean isRed = true;
    public void dropIn(View view)
    {
        ImageView counter = (ImageView) view;
        counter.setTranslationY(-1000);

        Button playAgain = (Button)findViewById(R.id.playAgain);
        if(isRed == true) {
            counter.setImageResource(R.drawable.red);
            //String tag = view.getTag().toString();
            int tagNo = Integer.parseInt(view.getTag().toString());
            state[tagNo-1] = 1;
            if(isWinning(state, 1) == true)
                Toast.makeText(this, "Red win!", Toast.LENGTH_SHORT).show();
            if(stillPlaying() == false)
                playAgain.setVisibility(View.VISIBLE);
            isRed = false;
        }
        else
        {
            counter.setImageResource(R.drawable.yellow);
            int tagNo = Integer.parseInt(view.getTag().toString());
            state[tagNo-1] = 2;
            if(isWinning(state, 2) == true)
                Toast.makeText(this, "Yellow win!", Toast.LENGTH_SHORT).show();
            if(stillPlaying() == false)
                playAgain.setVisibility(View.VISIBLE);
            isRed = true;
        }
        counter.animate().translationYBy(1000).setDuration(300);
    }
    public void playAgain(View view)
    {
        Button playAgain = (Button)findViewById(R.id.playAgain);
        playAgain.setVisibility(View.INVISIBLE);
        Log.i("Loginfo", "Test0");
        for(int i=0; i<8;i++)
            state[i] = 0;
        Log.i("Loginfo", "Test1");
        //GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        android.support.v7.widget.GridLayout gridLayout = (android.support.v7.widget.GridLayout)findViewById(R.id.gridLayout);

        Log.i("Loginfo", "Test2");
        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
    }
    public boolean isWinning(int[] statePosition, int team)
    {
        for(int i = 0; i<8;i++)
        {
            int count = 1;
            int posisiton1 = winningPositions[i][0];
            int posisiton2 = winningPositions[i][1];
            int posisiton3 = winningPositions[i][2];
            Log.i("Loginfo", "Team " + team);
            Log.i("Loginfo", posisiton1+" ,"+posisiton2+" ,"+posisiton3+" ");
            if((statePosition[posisiton1] == statePosition[posisiton2]) && (statePosition[posisiton2] == statePosition[posisiton3]) && (statePosition[posisiton2] != 0) && (statePosition[posisiton2] == team))
            {
                Button playAgain = (Button)findViewById(R.id.playAgain);
                playAgain.setVisibility(View.VISIBLE);
                return true;
            }
        }
        return false;
    }
    public boolean stillPlaying()
    {
        for(int i=0; i<8;i++)
        {
            if(state[i] == 0)
                return true;
        }
        Button playAgain = (Button)findViewById(R.id.playAgain);
        playAgain.setVisibility(View.VISIBLE);
        return false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
