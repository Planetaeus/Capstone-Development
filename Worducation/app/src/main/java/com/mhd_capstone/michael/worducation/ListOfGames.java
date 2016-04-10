package com.example.michael.worducation;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.michael.worducation.matching.Matching;
import com.example.michael.worducation.matching.PicturesToWords;
import com.example.michael.worducation.matching.WordsToPictures;

import java.util.Random;


public class ListOfGames extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_games);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_or_words, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startP2W( View view )
    {
        int i = Matching.randIndex();
        int rand1 = Matching.randWord(i);
        int rand2 = Matching.randWord(i, rand1);
        int rand3 = Matching.randWord(i, rand1, rand2);

        Intent intent = new Intent( ListOfGames.this, PicturesToWords.class );

        intent.putExtra( "Word", i );
        intent.putExtra( "Wrong1", rand1 );
        intent.putExtra( "Wrong2", rand2 );
        intent.putExtra( "Wrong3", rand3 );

        startActivity( intent );
    }

    public void startW2P( View view )
    {
        int i = Matching.randIndex();
        int rand1 = Matching.randWord(i);
        int rand2 = Matching.randWord(i, rand1);
        int rand3 = Matching.randWord(i, rand1, rand2);

        Random r = new Random();
        int randNum = r.nextInt(4);

        Intent intent = new Intent( ListOfGames.this, WordsToPictures.class );

        intent.putExtra( "Word", i );
        intent.putExtra( "Wrong1", rand1 );
        intent.putExtra( "Wrong2", rand2 );
        intent.putExtra( "Wrong3", rand3 );
        intent.putExtra( "Layout", randNum );

        startActivity(intent);
    }
}
