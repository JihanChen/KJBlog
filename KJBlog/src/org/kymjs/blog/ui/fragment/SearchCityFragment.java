package org.kymjs.blog.ui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.kymjs.blog.R;
import org.kymjs.kjframe.ui.BindView;

/**
 * Created by lody  on 2015/3/14.
 *
 * 搜索城市的对话框
 *
 */
public class SearchCityFragment extends DialogFragment {

    @BindView(id=R.id.search_city_edit)
    EditText searchEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = View.inflate(getActivity(), R.layout.frag_search_city, null);
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder b = new AlertDialog.Builder(getActivity()).setIcon(R.drawable.ic_search);
        return b.create();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除边框和标题
        setStyle(R.style.full_screen_dialog, 0);
        setCancelable(true);
    }


}
