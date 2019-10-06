package com.example.musadiq.log;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class about extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ImageView mTitleWindow=(ImageView)findViewById(R.id.titleWindow);
        TextView mMessageWindow=(TextView)findViewById(R.id.messageWindow);

        StringBuilder stringBuilder=new StringBuilder();
        String somemessage="\n*Glaucoma is a group of eye diseases which result in damage to the optic nerve and vision loss.\n\n *The most common type is open-angle glaucoma with less common types including closed-angle glaucoma and normal-tension glaucoma.\n\n*Open-angle glaucoma develops slowly over time and there is no pain.\n\n*Peripheral vision may begin to decrease followed by central vision resulting in blindness if not treated.\n\n*Closed-angle glaucoma can present gradually or suddenly." +

                "\n\n*Risk factors for glaucoma include increased pressure in the eye, a family history of the condition, and high blood pressure."+

                "\n\n*If treated early it is possible to slow or stop the progression of disease with medication, laser treatment, or surgery.\n\n* The goal of these treatments is to decrease eye pressure. \n\n*A number of different classes of glaucoma medication are available. Laser treatments may be effective in both open-angle and closed-angle glaucoma."+

                "\n\n*About 6 to 67 million people have glaucoma globally.The disease affects about 2 million people in the United States.\n\n*It occurs more commonly among older people.Closed-angle glaucoma is more common in women.Glaucoma has been called the \"silent thief of sight\" because the loss of vision usually occurs slowly over a long period of time.\n\n*Worldwide, glaucoma is the second-leading cause of blindness after cataracts.\n\n*The word \"glaucoma\" is from Ancient Greek glaukos which means blue, green, or gray.";
            stringBuilder.append(somemessage);
        mMessageWindow.setText(stringBuilder.toString());
    }
}
