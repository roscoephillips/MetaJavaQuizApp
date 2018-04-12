package com.example.android.metaquizapp;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    //Global variables including EditText,

    EditText testTakerName, question1Response;
    TextView question1Text, question7Text, question8Text;
    CheckBox question2ACheckBox, question2BCheckBox, question2CCheckBox;
    CheckBox question7A, question7B, question7C, question7D, question7E, question7F, question7G, question7H, question7I, question7J;
    RadioButton question3RadioButton, question4RadioButton, question5RadioButton, question6RadioButton;
    Switch question8Switch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testTakerName = (EditText) findViewById(R.id.name);
        question1Response = (EditText) findViewById(R.id.question_1_response);

        question1Text = (TextView) findViewById(R.id.question1Text);
        question7Text = (TextView) findViewById(R.id.question7Text);
        question8Text = (TextView) findViewById(R.id.question8Text);

        question2ACheckBox = (CheckBox) findViewById(R.id.question_2_response_a);
        question2BCheckBox = (CheckBox) findViewById(R.id.question_2_response_b);
        question2CCheckBox = (CheckBox) findViewById(R.id.question_2_response_c);

        question7A = (CheckBox) findViewById(R.id.question_7_a);
        question7B = (CheckBox) findViewById(R.id.question_7_b);
        question7C = (CheckBox) findViewById(R.id.question_7_c);
        question7D = (CheckBox) findViewById(R.id.question_7_d);
        question7E = (CheckBox) findViewById(R.id.question_7_e);
        question7F = (CheckBox) findViewById(R.id.question_7_f);
        question7G = (CheckBox) findViewById(R.id.question_7_g);
        question7H = (CheckBox) findViewById(R.id.question_7_h);
        question7I = (CheckBox) findViewById(R.id.question_7_i);
        question7J = (CheckBox) findViewById(R.id.question_7_j);

        question3RadioButton = (RadioButton) findViewById((R.id.question_3_yes));
        question4RadioButton = (RadioButton) findViewById((R.id.question_4_sp));
        question5RadioButton = (RadioButton) findViewById((R.id.question_5_dp));
        question6RadioButton = (RadioButton) findViewById((R.id.question_6_a));

        question8Switch = (Switch) findViewById(R.id.question_8);
    }


    /**
     * Default value for user's score.
     */
    int userScore = 0;

    int question7Score = 0;

    /**
     * Calculates the user's score.
     */

    private int calculateScore(int userScore, int question7Score) {
        if (question7Score > 4) {
            return (userScore + 4) * 100 / 13;
        }
        return (userScore + question7Score) * 100 / 13;

    }

    /**
     * Highlights the TextView if question is incorrect.
     *
     * @param editedTextView     is the TextView to be edited.
     * @param textViewCorrection changes the text in the TextView to an error correction message.
     */
    private void highlightErrorQuestion(TextView editedTextView, String textViewCorrection) {
        TextView highlightErrorQuestion = (TextView) editedTextView;
        highlightErrorQuestion.setTextColor(Color.WHITE);
        highlightErrorQuestion.setBackgroundColor((Color.parseColor("#2196F3")));
        highlightErrorQuestion.setText(textViewCorrection);
    }


    /**
     * Highlights the CheckBox if question is incorrect.
     *
     * @param editedCheckBox is the CheckBox to be highlighted.
     */
    private void highlightCheckBox(CheckBox editedCheckBox) {
        CheckBox highlightCheckBox = (CheckBox) editedCheckBox;
        highlightCheckBox.setTextColor(Color.WHITE);
        highlightCheckBox.setBackgroundColor((Color.parseColor("#2196F3")));
    }


    /**
     * Highlights the RadioButton if question is incorrect.
     *
     * @param editedRadioButton is the RadioButton to be highlighted.
     */
    private void highlightRadioButton(RadioButton editedRadioButton) {
        RadioButton highlightRadioButton = (RadioButton) editedRadioButton;
        highlightRadioButton.setTextColor(Color.WHITE);
        highlightRadioButton.setBackgroundColor((Color.parseColor("#2196F3")));
    }

    void submitTestResults(View v) {

        String name = testTakerName.getText().toString();


        /**
         *Gives user credit for question 1.
         */
        try {
            int questionsQuantity = Integer.parseInt(question1Response.getText().toString());
            if (questionsQuantity >= 4 && questionsQuantity <= 10) {
                userScore++;
            } else {
                highlightErrorQuestion(question1Text, getString(R.string.question_1_correction));
            }

        } catch (NumberFormatException blankQuestion) {
            Toast.makeText(this, getString(R.string.empty_question_1), Toast.LENGTH_SHORT).show();
            return;
        }


        /**
         *Gives user credit for question 2.
         */
        if (question2ACheckBox.isChecked()) {
            userScore++;
        } else {
            highlightCheckBox(question2ACheckBox);
        }
        if (question2BCheckBox.isChecked()) {
            userScore++;
        } else {
            highlightCheckBox(question2BCheckBox);
        }
        if (question2CCheckBox.isChecked()) {
            userScore++;
        } else {
            highlightCheckBox(question2CCheckBox);

        }

        /**
         * Gives user credit for question 3.
         */
        boolean isQuestion3YesChecked = question3RadioButton.isChecked();
        if (isQuestion3YesChecked) {
            userScore++;
        } else {
            highlightRadioButton(question3RadioButton);
        }


        /**
         * Gives user credit for question 4.
         */
        boolean isQuestion4SpChecked = question4RadioButton.isChecked();
        if (isQuestion4SpChecked) {
            userScore++;
        } else {
            highlightRadioButton(question4RadioButton);
            question4RadioButton.setText(getString(R.string.question_4_correction));
        }


        /**
         * Gives user credit for question 5.
         */
        boolean isQuestion5DpChecked = question5RadioButton.isChecked();
        if (isQuestion5DpChecked) {
            userScore++;
        } else {
            highlightRadioButton(question5RadioButton);
            question5RadioButton.setText(getString(R.string.question_5_correction));
        }

        /**
         * Gives user credit for question 6.
         */
        boolean isQuestion6YesChecked = question6RadioButton.isChecked();
        if (isQuestion6YesChecked) {
            userScore++;
        } else {
            highlightRadioButton(question6RadioButton);
            question6RadioButton.setText(getString(R.string.question_6_correction));
        }

        /**
         * Gives user credit for question 7. A-J are the 10 choices available.  If at least four of these are checked, you receive four points toward your total score.
         */
        if (question7A.isChecked()) {
            question7Score++;
        }

        if (question7B.isChecked()) {
            question7Score++;
        }

        if (question7C.isChecked()) {
            question7Score++;
        }

        if (question7D.isChecked()) {
            question7Score++;
        }

        if (question7E.isChecked()) {
            question7Score++;
        }

        if (question7F.isChecked()) {
            question7Score++;
        }

        if (question7G.isChecked()) {
            question7Score++;
        }

        if (question7H.isChecked()) {
            question7Score++;
        }

        if (question7I.isChecked()) {
            question7Score++;
        }

        if (question7J.isChecked()) {
            question7Score++;
        }


        if (question7Score < 4) {
            highlightErrorQuestion(question7Text, getString(R.string.question_7_correction));

        }

        boolean question8SwitchState = question8Switch.isChecked();
        if (question8SwitchState) {
            userScore++;
        } else {
            highlightErrorQuestion(question8Text, getString(R.string.question_8_correction));


        }


        int score = calculateScore(userScore, question7Score);
        if (score == 100) {
            String scoreMessage = getString(R.string.test_results, name, score);
            Toast.makeText(this, scoreMessage, Toast.LENGTH_LONG).show();
        } else

        {
            String scoreMessage = getString(R.string.try_again, name, score);
            Toast.makeText(this, scoreMessage, Toast.LENGTH_LONG).show();
        }

        userScore = 0;
        question7Score = 0;

    }

    void resetViews(View v) {
        recreate();

    }


}
