package com.cornez.lab5;
import android.app.*;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.*;
import android.util.*;

public class MainActivity extends Activity {
    private static final String TAB_KEY_INDEX = "tab_key";
    private Fragment breakfastFragment;
    private Fragment dinnerFragment;
    private Fragment lunchFragment;
    private Fragment snackFragment;
    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;
    private int count;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count = 0;
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        preferences = getApplicationContext().getSharedPreferences("MyPreferences",MODE_PRIVATE);
        editor = preferences.edit();
        breakfastFragment = new AppetizerFragment();
        lunchFragment = new DessertFragment();
        dinnerFragment = new EntreeFragment();
        snackFragment = new SnackFragment();


        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(false);
        ActionBar.Tab breakfastTab = actionBar.newTab().setText(getString(R.string.ui_tabname_appetizer));
        ActionBar.Tab lunchTab = actionBar.newTab().setText(getString(R.string.ui_tabname_dessert));
        ActionBar.Tab snackTab = actionBar.newTab().setText(getString(R.string.ui_tabname_snack));
        ActionBar.Tab dinnerTab = actionBar.newTab().setText(getString(R.string.ui_tabname_entree));



        breakfastTab.setTabListener(new MyTabsListener(breakfastFragment,getApplicationContext()));
        snackTab.setTabListener(new MyTabsListener(snackFragment,getApplicationContext()));
        dinnerTab.setTabListener(new MyTabsListener(dinnerFragment,getApplicationContext()));
        lunchTab.setTabListener(new MyTabsListener(lunchFragment,getApplicationContext()));

        actionBar.addTab(breakfastTab);
        actionBar.addTab(lunchTab);
        //actionBar.addTab(snackTab);
        actionBar.addTab(dinnerTab);
        if (preferences.getString("MEAL", "TEST").equals("Appetizer")) {
            actionBar.setSelectedNavigationItem(0);
        }
        else if (preferences.getString("MEAL", "TEST").equals("Dessert")) {
            actionBar.setSelectedNavigationItem(1);
        }
        else
            actionBar.setSelectedNavigationItem(2);

//        else {
//            actionBar.setSelectedNavigationItem(savedInstanceState.getInt(TAB_KEY_INDEX,0));
//        }


    }
    class MyTabsListener implements ActionBar.TabListener {
        public Fragment fragment;

        public MyTabsListener(Fragment f, Context context) {
            fragment = f;
        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }

        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            ft.replace(R.id.fragment_container, fragment);
            if (count != 0) {
                Log.d("SELECTED", fragment.toString());
                editor.putString("MEAL", fragment.toString());
                editor.commit();
            }
            count++;

        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            ft.remove(fragment);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);


    }

}
