package com.cornez.lab5;
import android.app.*;
import android.view.*;
import android.os.*;
/**
 * Created by kevinbarbian on 3/19/18.
 */

public class AppetizerFragment extends Fragment {

    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            return inflater.inflate(R.layout.fragment_appetizer,container,false);
        }
        public String toString(){
            return "Appetizer";
        }
    }
