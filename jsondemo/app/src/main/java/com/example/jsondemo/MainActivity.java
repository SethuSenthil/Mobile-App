package com.example.jsondemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JSONObject rootObject = new JSONObject();

        try {
            rootObject.put("name", "Often");
            rootObject.put("id", 123456);
            rootObject.put("birthday", "3-2-1982");
        }catch(JSONException e){
            e.printStackTrace();
        }
        Log.d("TAG_INFO_2", rootObject.toString());

        JSONArray jsonArray = new JSONArray();

        JSONObject blockOneCourse = new JSONObject();

        try {
            blockOneCourse.put("block", "1B");
            blockOneCourse.put("course name", "Computer Science");
            blockOneCourse.put("grade", 96);
            //add block 1 to root obj
            //rootObject.put("class 1", blockOneCourse);
        }catch(JSONException e){
            e.printStackTrace();
        }

        JSONObject blockTwoCourse = new JSONObject();
        try {
            blockTwoCourse.put("block", "2B");
            blockTwoCourse.put("course name", "PE");
            blockTwoCourse.put("grade", 99);
            //add block 1 to root obj
            //rootObject.put("class 2", blockTwoCourse);
        }catch(JSONException e){
            e.printStackTrace();
        }

        JSONObject blockThreeCourse = new JSONObject();
        try {
            blockTwoCourse.put("block", "3B");
            blockTwoCourse.put("course name", "HAP");
            blockTwoCourse.put("grade", 100);
            //add block 1 to root obj
            //rootObject.put("class 2", blockTwoCourse);
        }catch(JSONException e){
            e.printStackTrace();
        }

        jsonArray.put(blockOneCourse);
        jsonArray.put(blockTwoCourse);
        jsonArray.put(blockThreeCourse);

        try {
            rootObject.put("classes", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("TAG_INFO_2", rootObject.toString());

        try {
            int block2Grade = blockTwoCourse.getInt("grade");
            Log.d("TAG_INFO_3", "Grade from block 2: " + block2Grade);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}