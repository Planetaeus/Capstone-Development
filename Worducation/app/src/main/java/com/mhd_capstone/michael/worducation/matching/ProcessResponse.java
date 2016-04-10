package com.example.michael.worducation.matching;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.michael.worducation.ListOfGames;
import com.example.michael.worducation.R;

import java.util.Random;

public class ProcessResponse extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_response);

        TextView message = (TextView) findViewById( R.id.message );
        Button carryOn = (Button) findViewById( R.id.carry_on );

        if( getIntent().getBooleanExtra( "Response", false ) )
        {
            message.setText("Correct");
            message.setBackgroundColor(0xff00ff00);

            carryOn.setText( "Next Word" );
            carryOn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    carryOn();
                }
            });
        }
        else
        {
            message.setText("Incorrect");
            message.setBackgroundColor(0xffff0000);

            carryOn.setText( "Try Again" );
            carryOn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tryAgain();
                }
            });
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_incorrect, menu);
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

    public void carryOn()
    {
        Intent old = this.getIntent();
        Random r = new Random();
        int randNum = r.nextInt(4);
        int i = Matching.randWord(old.getIntExtra("Word", 0));
        int rand1 = Matching.randWord(i);
        int rand2 = Matching.randWord(i, rand1);
        int rand3 = Matching.randWord(i, rand1, rand2);

        Intent intent;
        if( old.getIntExtra("Type", 0) == 0 )
        {
            intent = new Intent( ProcessResponse.this, PicturesToWords.class );
        }
        else
        {
            intent = new Intent( ProcessResponse.this, WordsToPictures.class );
        }

        intent.putExtra("Word", i);
        intent.putExtra("Wrong1", rand1);
        intent.putExtra("Wrong2", rand2);
        intent.putExtra("Wrong3", rand3);
        intent.putExtra("Layout", randNum);
        startActivity( intent );
    }

    public void tryAgain()
    {
        Intent intent = getIntent();
        Intent i;
        if( intent.getIntExtra("Type", 0) == 0 )
        {
            i = new Intent( ProcessResponse.this, PicturesToWords.class );
        }
        else
        {
            i = new Intent( ProcessResponse.this, WordsToPictures.class );
        }

        i.putExtra( "Word", intent.getIntExtra( "Word", 0) );
        i.putExtra( "Wrong1", intent.getIntExtra( "Wrong1", 0 ) );
        i.putExtra( "Wrong2", intent.getIntExtra( "Wrong2", 0 ) );
        i.putExtra( "Wrong3", intent.getIntExtra( "Wrong3", 0 ) );
        i.putExtra( "Layout", intent.getIntExtra( "Layout", 0 ) );

        startActivity( i );
    }

    public void goToGames( View view)
    {
        Intent intent = new Intent(this, ListOfGames.class);
        startActivity(intent);
    }

}
