package dishtv.dummy.advancefeature;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import dishtv.dummy.advancefeature.autofill.ChipConfiguration;
import dishtv.dummy.advancefeature.autofill.NachoTextView;
import dishtv.dummy.advancefeature.autofill.chip.Chip;
import dishtv.dummy.advancefeature.autofill.chip.ChipSpan;
import dishtv.dummy.advancefeature.autofill.chip.ChipSpanChipCreator;
import dishtv.dummy.advancefeature.autofill.terminator.ChipTerminatorHandler;
import dishtv.dummy.advancefeature.autofill.tokenizer.SpanChipTokenizer;
import dishtv.dummy.advancefeature.autofill.validator.ChipifyingNachoValidator;
import dishtv.dummy.advancefeature.autofill.validator.IllegalCharacterIdentifier;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    NachoTextView mNachoTextView;
    NachoTextView mNachoTextViewWithIcons;
    private static String TAG = "Auto fill Android";
    private static String[] SUGGESTIONS = new String[]{"Nachos", "Chip", "Tortilla Chips", "Melted Cheese", "Salsa", "Guacamole", "Cheddar", "Mozzarella", "Mexico", "Jalapeno"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.testui);


        TextView mInfoBodyView=findViewById(R.id.info_body);
        mNachoTextView=findViewById(R.id.nacho_text_view);
        mNachoTextViewWithIcons=findViewById(R.id.nacho_text_view_with_icons);


        Spanned infoText;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            infoText = Html.fromHtml(getString(R.string.info_text_body), Html.FROM_HTML_MODE_LEGACY);
        } else {
            infoText = Html.fromHtml(getString(R.string.info_text_body));
        }
        mInfoBodyView.setText(infoText);

        setupChipTextView(mNachoTextView);
        setupChipTextView(mNachoTextViewWithIcons);

        List<String> testList = new ArrayList<>();
        testList.add("testing");
        testList.add("setText");
        mNachoTextView.setText(testList);

        mNachoTextViewWithIcons.setChipTokenizer(new SpanChipTokenizer<>(this, new ChipSpanChipCreator() {
            @Override
            public ChipSpan createChip(@NonNull Context context, @NonNull CharSequence text, Object data) {
                return new ChipSpan(context, text, ContextCompat.getDrawable(MainActivity.this, R.mipmap.ic_launcher), data);
            }

            @Override
            public void configureChip(@NonNull ChipSpan chip, @NonNull ChipConfiguration chipConfiguration) {
                super.configureChip(chip, chipConfiguration);
            }
        }, ChipSpan.class));



        findViewById(R.id.list_chip_values).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> chipValues = mNachoTextView.getChipValues();
                alertStringList("Chip Values", chipValues);
            }
        });


        findViewById(R.id.list_chip_and_token_values).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> chipAndTokenValues = mNachoTextView.getChipAndTokenValues();
                alertStringList("Chip and Token Values", chipAndTokenValues);
            }
        });
        findViewById(R.id.to_string).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> strings = new ArrayList<>();
                strings.add(mNachoTextView.toString());
                alertStringList("toString()", strings);
            }
        });

    }

    private void setupChipTextView(NachoTextView nachoTextView) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, SUGGESTIONS);
        nachoTextView.setAdapter(adapter);
        nachoTextView.setIllegalCharacterIdentifier(new IllegalCharacterIdentifier() {
            @Override
            public boolean isCharacterIllegal(Character c) {
                return !c.toString().matches("[a-z0-9 ]");
            }
        });
        nachoTextView.addChipTerminator('\n', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_ALL);
        nachoTextView.addChipTerminator(' ', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_TO_TERMINATOR);
        nachoTextView.addChipTerminator(';', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_CURRENT_TOKEN);
        nachoTextView.setNachoValidator(new ChipifyingNachoValidator());
        nachoTextView.enableEditChipOnTouch(true, true);
        nachoTextView.setOnChipClickListener(new NachoTextView.OnChipClickListener() {
            @Override
            public void onChipClick(Chip chip, MotionEvent motionEvent) {
                Log.d(TAG, "onChipClick: " + chip.getText());
            }
        });
        nachoTextView.setOnChipRemoveListener(new NachoTextView.OnChipRemoveListener() {
            @Override
            public void onChipRemove(Chip chip) {
                Log.d(TAG, "onChipRemoved: " + chip.getText());
                mNachoTextView.setSelection(mNachoTextView.getText().length());
            }
        });
    }


    private void alertStringList(String title, List<String> list) {
        String alertBody;
        if (!list.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (String chipValue : list) {
                builder.append(chipValue);
                builder.append("\n");
            }
            builder.deleteCharAt(builder.length() - 1);
            alertBody = builder.toString();
        } else {
            alertBody = "No strings";
        }

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(alertBody)
                .setCancelable(true)
                .setNegativeButton("Close", null)
                .create();

        dialog.show();
    }
}
