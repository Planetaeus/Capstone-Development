package com.example.michael.worducation.matching;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.example.michael.worducation.R;

import java.util.Random;

/**
 * Makes it possible to have a random selection of classes, using an array of the classes and a randomizer
 * based on the current size of the ArrayList.
 *
 * @author Michael Taft Morris
 * @version 15.04.05
 */

public abstract class Matching extends ActionBarActivity {

    public static final String[] words = {"Backpack", "Book", "Chair", "Computer", "Crayons", "Desk", "Door",
            "Eraser", "Folder", "Globe", "Glue", "Lunch Box", "Markers", "Notebook",
            "Paper", "Pen", "Pencil", "Pencil Case", "Report Card", "Ruler", "School",
            "School Bus", "Scissors", "Sharpener", "Student", "Tablet", "Teacher", "Tape",
            "Whiteboard"};
    public static final int[] images = {R.drawable.backpack, R.drawable.book, R.drawable.chair, R.drawable.computer,
            R.drawable.crayons, R.drawable.desk, R.drawable.door, R.drawable.eraser,
            R.drawable.folder, R.drawable.globe, R.drawable.glue, R.drawable.lunch_box,
            R.drawable.markers, R.drawable.notebook, R.drawable.paper, R.drawable.pen,
            R.drawable.pencil, R.drawable.pencil_case, R.drawable.report_card,
            R.drawable.ruler, R.drawable.school, R.drawable.school_bus, R.drawable.scissors,
            R.drawable.sharpener, R.drawable.student, R.drawable.tablet, R.drawable.teacher,
            R.drawable.tape, R.drawable.whiteboard};

    private int index, wrong1, wrong2, wrong3, layout;

    public static int randIndex() {
        return (int) (Math.random() * words.length);
    }

    public static int randWord(int i, int r1, int r2) {
        int word = randWord(i, r1);
        if (word == r2)
            return randWord(i, r1, r2);
        return word;
    }

    public static int randWord(int i, int r) {
        int word = randWord(i);
        if (word == r)
            return randWord(i, r);
        return word;
    }

    public static int randWord(int i) {
        int word = randIndex();
        if (word == i)
            return randWord(i);
        return word;
    }

    public void nextWord(View view) {
        int i = randWord(index);
        int rand1 = randWord(i);
        int rand2 = randWord(i, rand1);
        int rand3 = randWord(i, rand1, rand2);

        Random r = new Random();
        int randNum = r.nextInt(4);

        Intent intent = new Intent(Matching.this, Matching.class);

        intent.putExtra("Word", i);
        intent.putExtra("Wrong1", rand1);
        intent.putExtra("Wrong2", rand2);
        intent.putExtra("Wrong3", rand3);
        intent.putExtra("Layout", randNum);

        startActivity(intent);
    }

    public void setValues(int i, int w1, int w2, int w3, int layout) {
        index = i;
        wrong1 = w1;
        wrong2 = w2;
        wrong3 = w3;
        this.layout = layout;
    }

    public int getIndex()
    {
        return index;
    }

    public int getWrong1()
    {
        return wrong1;
    }

    public int getWrong2()
    {
        return wrong2;
    }

    public int getWrong3()
    {
        return wrong3;
    }

    public int getLayout()
    {
        return layout;
    }

    public abstract void incorrect( View view );
}