package com.example.michael.worducation.matching;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.michael.worducation.R;

import java.util.Random;

public class WordsToPictures extends Matching {

    public static int[] layouts = {R.layout.activity_words_to_pictures1, R.layout.activity_words_to_pictures2,
            R.layout.activity_words_to_pictures3, R.layout.activity_words_to_pictures4};
    public static final int type = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();

        setValues(i.getIntExtra("Word", 0), i.getIntExtra("Wrong1", 0), i.getIntExtra("Wrong2", 0),
                i.getIntExtra("Wrong3", 0), i.getIntExtra("Layout", 0));

        setContentView(layouts[getLayout()]);

        TextView given = (TextView) findViewById(R.id.given);
        given.setText(words[getIndex()]);

        ImageButton correct = (ImageButton) findViewById(R.id.correct);
        correct.setImageResource(images[getIndex()]);

        ImageButton wrong1 = (ImageButton) findViewById(R.id.wrong1);
        wrong1.setImageResource(images[getWrong1()]);

        ImageButton wrong2 = (ImageButton) findViewById(R.id.wrong2);
        wrong2.setImageResource(images[getWrong2()]);

        ImageButton wrong3 = (ImageButton) findViewById(R.id.wrong3);
        wrong3.setImageResource(images[getWrong3()]);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_words_to_pictures, menu);
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

    public void incorrect(View view) {
        Intent intent = new Intent(WordsToPictures.this, ProcessResponse.class);

        intent.putExtra("Word", getIndex());
        intent.putExtra("Wrong1", getWrong1());
        intent.putExtra("Wrong2", getWrong2());
        intent.putExtra("Wrong3", getWrong3());
        intent.putExtra("Layout", getLayout());
        intent.putExtra("Type", type);
        intent.putExtra("Response", false);

        startActivity(intent);
    }

    public void correct(View view)
    {
        Intent intent = new Intent(WordsToPictures.this, ProcessResponse.class);

        intent.putExtra("Word", getIndex());
        intent.putExtra("Wrong1", getWrong1());
        intent.putExtra("Wrong2", getWrong2());
        intent.putExtra("Wrong3", getWrong3());
        intent.putExtra("Response", true);
        intent.putExtra("Type", type);


        startActivity(intent);
    }
}
