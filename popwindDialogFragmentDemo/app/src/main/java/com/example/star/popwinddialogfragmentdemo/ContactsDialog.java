package com.example.star.popwinddialogfragmentdemo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by xiongxingxing on 15/8/31.
 */
public class ContactsDialog extends DialogFragment {

    String contactNumber = null;
    String skypeContact = null;
    String userID = null;
    int flatsCount = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        TextView contactTV, skypeTV;

        View layout = inflater.inflate(R.layout.contacts_dialog, container);

        contactTV = (TextView) layout.findViewById(R.id.contactNumber);
        skypeTV = (TextView) layout.findViewById(R.id.skypeName);
        RelativeLayout skypeContactsLayout = (RelativeLayout) layout.findViewById(R.id.skypeContactsLayout);
        RelativeLayout contactsLayout = (RelativeLayout) layout.findViewById(R.id.contactsLayout);
        RelativeLayout allUserFlats = (RelativeLayout) layout.findViewById(R.id.allUserFlats);

//        contactNumber = getArguments().getString("contactNumber", null);
        contactNumber="18207279343";
//        skypeContact = getArguments().getString("skypeName", null);
        skypeContact="10086";
//        userID = getArguments().getString("userID", null);
        userID="xingxing";
//        flatsCount = getArguments().getInt("flatsCount");
        flatsCount=20;
        if (TextUtils.isEmpty(skypeContact)) {
            skypeContactsLayout.setVisibility(View.GONE);
        }
        if (TextUtils.isEmpty(userID)) {
            allUserFlats.setVisibility(View.GONE);
        }
        if (flatsCount == 1) {
            allUserFlats.setVisibility(View.GONE);
        }

        String convertedNumber = convertNumber(contactNumber);

        contactTV.setText(convertedNumber);
        skypeTV.setText(skypeContact);

        WindowManager.LayoutParams params = getDialog().getWindow()
                .getAttributes();
        params.gravity = Gravity.BOTTOM | Gravity.CENTER;
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setAttributes(params);

        contactsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + contactNumber));
                startActivity(intent);
            }
        });

        allUserFlats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("userID", userID);
                getActivity().finish();
                startActivity(intent);
            }
        });
        skypeContactsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isSkypeClientInstalled(getActivity())) {
                    Intent sky = new Intent("android.intent.action.VIEW");
                    sky.setData(Uri.parse("skype:" + skypeContact));
                    startActivity(sky);
                } else {
                    Toast.makeText(getActivity(), "Skype не установлен", Toast.LENGTH_LONG).show();
                }


            }
        });

        return layout;
    }

    public boolean isSkypeClientInstalled(Context myContext) {
        PackageManager myPackageMgr = myContext.getPackageManager();
        try {
            myPackageMgr.getPackageInfo("com.skype.raider", PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            return (false);
        }
        return (true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.CustomDialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        getDialog().setCanceledOnTouchOutside(true);

    }

    private String convertNumber(String number) {
        StringBuffer sb = new StringBuffer(number);
        sb.insert(2, " (");
        sb.insert(7, ") ");
        sb.insert(12, "-");
        sb.insert(15, "-");
        return String.valueOf(sb);
    }
}