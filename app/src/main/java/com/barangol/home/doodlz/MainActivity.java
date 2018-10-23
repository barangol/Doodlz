package com.barangol.home.doodlz;

import android.graphics.Paint;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    DoubleView doubleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        doubleView = findViewById(R.id.doubleView);

        //Person person = new Person("катя");
        TreeSet< Person1> personMap = new TreeSet< Person1>();
      //  Iterator<Person1> listIterator = personMap.iterator();
        personMap.add(new Person1("Kate"));
        personMap.add(new Person1("54"));
        personMap.add(new Person1("Dera"));
        personMap.add(new Person1("hera"));
        Iterator<Person1> listIterator = personMap.iterator();
         for(Person1 person1:personMap){
            Log.d("MyTag", person1.getName());

        }
        while (listIterator.hasNext()){

            Log.d("MyTag", listIterator.next().getName());

        }
        ArrayList<Person1> arrayList = new ArrayList<Person1>();
        arrayList.add(new Person1("dasha"));
        arrayList.add(new Person1("111dasha"));
        arrayList.add(new Person1("345"));
      //  Object[] p = arrayList.toArray();
       // Log.d("MyTag", p[1]);
        ListIterator<Person1> listIterator1 = arrayList.listIterator();

        while (listIterator1.hasNext()){
            Log.d("MyTag", listIterator1.next().getName());

        }

        for(Person1 person1 : arrayList){
            Log.d("MyTag", person1.getName());

        }
        String str1 = "Java";
        String str2 = "Hello";
        str2 = str2.concat(str1); // HelloJava
      //  String s3 = s1.join("-", s1,s2);
        Log.d("MyTag", str2);
        //Pattern pp = new Pattern();

        String input = "min(min(-5,min(7,3)),max(min(max(5,15),min(-100,1)),max(100,300)))";
        Pattern pattern =  Pattern.compile("(min|max)\\(-?\\d+,-?\\d+\\)");
         Matcher matcher = pattern.matcher(input);
        while (matcher.find()){
            String s= matcher.group();
            String s1 = "";
  //          Log.d("MyTag",s1);
            Pattern op = Pattern.compile("-?\\d+");
            Matcher mop = op.matcher(s);
            Integer res;
            if(mop.find()) {

 //               Log.d("MyTag", s);// String op1 =  mop.group();
                  Integer op1 = Integer.valueOf(mop.group());
                mop.find();
                  Integer op2 = Integer.valueOf(mop.group());
                if(s.contains("min")){ s1 = s1+"min\\(";
                    if(op1<op2) res = op1;  else res = op2;}
                else{ s1 = s1+"max\\(";
                    if (op1<op2) res = op2; else res = op1;}
                    s1 = s1+ op1 + "," + op2 +"\\)";
                input = input.replaceAll(s1,res.toString());
  //              Log.d("MyTag", res.toString());
 //               Log.d("MyTag", input);
                matcher = pattern.matcher(input);
            }
       }
        Log.d("MyTag", input);

        MyCalc myCalc ;
               myCalc = (x,y,z) -> {
            if(x<y) return y*z; else return z*z;

        };
        Log.d("MyTag", ((Integer) myCalc.Calculate(10,15,20)).toString());

    }
    interface MyCalc{
        int Calculate(int x,int y, int z);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getTitle().toString()){
          case "Color": doubleView.clear(); break;
          case "Add": Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
                    doubleView.addRect();
          break;

      }

      //  doubleView.clear();
        return super.onOptionsItemSelected(item);
    }
}
