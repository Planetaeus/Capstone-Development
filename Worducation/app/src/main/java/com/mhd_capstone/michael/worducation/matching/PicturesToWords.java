package com.example.michael.worducation.matching;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.michael.worducation.R;

import java.util.Random;

public class PicturesToWords extends Matching {
    public static final int[] layouts = {R.layout.activity_pictures_to_words1, R.layout.activity_pictures_to_words2,
            R.layout.activity_pictures_to_words3, R.layout.activity_pictures_to_words4};

    public static final int type = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();

        setValues(i.getIntExtra("Word", 0), i.getIntExtra("Wrong1", 0), i.getIntExtra("Wrong2", 0),
                i.getIntExtra("Wrong3", 0), i.getIntExtra("Layout", 0));

        setContentView(layouts[getLayout()]);

        ImageView given = (ImageView) findViewById(R.id.given);
        given.setImageResource(images[getIndex()]);

        Button correct = (Button) findViewById(R.id.correct);
        correct.setText(words[getIndex()]);

        Button wrong1 = (Button) findViewById(R.id.wrong1);
        wrong1.setText(words[getWrong1()]);

        Button wrong2 = (Button) findViewById(R.id.wrong2);
        wrong2.setText(words[getWrong2()]);

        Button wrong3 = (Button) findViewById(R.id.wrong3);
        wrong3.setText(words[getWrong3()]);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pencil, menu);
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

    public void incorrect( View view )
    {
        Intent intent = new Intent(PicturesToWords.this, ProcessResponse.class);

        intent.putExtra( "Word", getIndex() );
        intent.putExtra( "Wrong1", getWrong1() );
        intent.putExtra( "Wrong2", getWrong2() );
        intent.putExtra( "Wrong3", getWrong3() );
        intent.putExtra( "Layout", getLayout() );
        intent.putExtra( "Type", type );

        startActivity( intent );
    }

    public void correct(View view)
    {
        Intent intent = new Intent(PicturesToWords.this, ProcessResponse.class);

        intent.putExtra("Word", getIndex());
        intent.putExtra("Wrong1", getWrong1());
        intent.putExtra("Wrong2", getWrong2());
        intent.putExtra("Wrong3", getWrong3());
        intent.putExtra("Response", true);
        intent.putExtra("Type", type);


        startActivity(intent);
    }
}
