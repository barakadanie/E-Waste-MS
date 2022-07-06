package com.example.e_wastems;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.SearchView;

import androidx.appcompat.widget.Toolbar;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressActivity extends AppCompatActivity {
    ExpandableListView expandableListView;
    List<String>names;
    Map<String, List<String>> childs;
    MyAdap adp;
    SearchView search;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        search=findViewById(R.id.search);
        getSupportActionBar().setTitle("Collection Zones Address");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        expandableListView=findViewById(R.id.expanded_list);
        getData();
        adp=new MyAdap(this,names,childs);
        expandableListView.setAdapter(adp);
    }

    private void getData() {
        names=new ArrayList<>();
        names.add("Baragoi");
        childs=new HashMap<>();
        List<String>Baragoi=new ArrayList<>();
        Baragoi.add("Collection Offices located Near Kowop building along North Horr (A4) Road ,Afia house, 2floor room NO.25\nFor More information Contact us through:+254704855357/+254752251531");
        names.add("Bungoma");
        childs=new HashMap<>();
        List<String>Bungoma=new ArrayList<>();
        Bungoma.add("Collection Offices Near Kibabii university,At Delvic Plaza ground floor room No.15\nFor More information Contact us through:+254704855357/+254752251531");
        names.add("Busia");
        childs=new HashMap<>();
        List<String>Busia=new ArrayList<>();
        Busia.add("Collection Offices located Near Zuri Appartments along Busia-isumu Highway,Afia house, 2floor room NO.25\nFor More information Contact us through:+254704855357/+254752251531");
        names.add("Eldoret");
        childs=new HashMap<>();
        List<String>Eldoret=new ArrayList<>();
        Eldoret.add("Collection Offices located inside KVDA building, ground floor room NO.2\nFor More information Contact us through:+254704855357/+254752251529");
        names.add("Nairobi");
        childs=new HashMap<>();
        List<String>Nairobi=new ArrayList<>();
        Nairobi.add("Collection Offices located inside Nairobi CBD, 2floor room NO.25\nFor More information Contact us through:+254704855357/+254752251530 ");
        names.add("Nakuru");
        childs=new HashMap<>();
        List<String>Nakuru=new ArrayList<>();
        Nakuru.add("Collection Offices located opposite Nakuru Nakumatt Stores house.\nFor More information Contact us through:+254704855357/+254752251531");
        names.add("Isiolo");
        childs=new HashMap<>();
        List<String>Isiolo=new ArrayList<>();
        Isiolo.add("Collection Offices located Located in Isiolo,Next to JAMHURI HOTEL\nFor More information Contact us through:+254704855357/+254752251531");
        names.add("Kisumu");
        childs=new HashMap<>();
        List<String>Kisumu=new ArrayList<>();
        Kisumu.add("Collection Offices located inside Kisumu Aplha house, 2floor room NO.25\nFor More information Contact us through:+254704855357/+254752251531");
        names.add("Lodwar");
        childs=new HashMap<>();
        List<String>Lodwar=new ArrayList<>();
        Lodwar.add("Collection Offices located Opposite Stegra Hotel,\nFor More information Contact us through:+254704855357/+254752251531");
        names.add("Malindi");
        childs=new HashMap<>();
        List<String>Malindi=new ArrayList<>();
        Malindi.add("Collection Offices located at TSS Towers ground Floor room No. 5,\nFor More information Contact us through:+254704855357/+254752251531");
        names.add("Marsabit");
        childs=new HashMap<>();
        List<String>Marsabit=new ArrayList<>();
        Marsabit.add("Collection Offices located at Lengima house next to Oasaki shop,\nFor More information Contact us through:+254704855357/+254752251531");
        names.add("Mombasa");
        childs=new HashMap<>();
        List<String>Mombasa=new ArrayList<>();
        Mombasa.add("Collection Offices located at TSS Towers ground Floor room No. 5,\nFor More information Contact us through:+254704855357/+254752251531");
        childs.put(names.get(0),Baragoi);
        childs.put(names.get(1),Bungoma);
        childs.put(names.get(2),Busia);
        childs.put(names.get(3),Eldoret);
        childs.put(names.get(4),Nairobi);
        childs.put(names.get(5),Nakuru);
        childs.put(names.get(6),Isiolo);
        childs.put(names.get(7),Kisumu);
        childs.put(names.get(8),Lodwar);
        childs.put(names.get(9),Malindi);
        childs.put(names.get(10),Marsabit);
        childs.put(names.get(11),Mombasa);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}