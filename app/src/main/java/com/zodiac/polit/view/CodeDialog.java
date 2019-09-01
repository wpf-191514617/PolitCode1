package com.zodiac.polit.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.zodiac.polit.R;

public class CodeDialog extends Dialog {

    private ImageView ivCode;

    public CodeDialog(@NonNull Context context) {
        super(context, R.style.customDialog);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_code , null);
        ivCode = ((View) view).findViewById(R.id.ivCode);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        setContentView(view);
        ivCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public void setImage(int resourceId){
        ivCode.setImageResource(resourceId);
    }




}
