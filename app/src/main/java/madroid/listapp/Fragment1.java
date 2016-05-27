package madroid.listapp;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.TextView;

public class Fragment1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View view=inflater.inflate(R.layout.fragment_fragment1, container, false);

        return view;
    }

    public void changeText( String s){
        //this textview should be bound in the fragment onCreate as a member variable
        TextView frv=(TextView) getView().findViewById(R.id.fragment1_text);
        frv.setText(s);
    }

    public String getTextFragment(){
        EditText txt = (EditText)getView().findViewById(R.id.fragment1_edit);
        return txt.getText().toString();
    }

}
