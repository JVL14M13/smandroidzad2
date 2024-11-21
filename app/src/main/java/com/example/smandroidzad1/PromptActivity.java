package com.example.smandroidzad1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PromptActivity extends AppCompatActivity {

    private boolean correctAnswer;
    private TextView questionPromptTextView; // TextView do pytania
    private TextView answerTextView; // TextView do odpowiedzi
    private Button showCorrectAnswerButton; // Przycisk do pokazania odpowiedzi
    public static final String KEY_EXTRA_ANSWER_SHOWN = "com.example.smandroidzad1.AnswerShown";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_prompt);

        // Odebranie odpowiedzi z MainActivity
        correctAnswer = getIntent().getBooleanExtra(MainActivity.KEY_EXTRA_ANSWER, true);

        // Inicjalizacja widoków
        questionPromptTextView = findViewById(R.id.question_prompt_text); // TextView dla pytania
        answerTextView = findViewById(R.id.answer_text_view); // TextView dla odpowiedzi
        showCorrectAnswerButton = findViewById(R.id.show_correct_answer_button); // Przycisk do pokazania odpowiedzi

        // Nasłuchiwanie na kliknięcie przycisku "Pokaż odpowiedź"
        showCorrectAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Wyświetlenie odpowiedzi w TextView
                int answer = correctAnswer ? R.string.button_true : R.string.button_false;
                answerTextView.setText(answer);  // Ustawia odpowiednią odpowiedź
                setAnswerShownResult(true);  // Zmieniamy stan, że odpowiedź została pokazana
            }
        });

        // Obsługa paddingów w zależności od systemu
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Metoda do przekazania informacji, czy odpowiedź została pokazana
    private void setAnswerShownResult(boolean answerWasShown) {
        Intent resultIntent = new Intent(PromptActivity.this, MainActivity.class);
        resultIntent.putExtra(KEY_EXTRA_ANSWER_SHOWN, answerWasShown);
        setResult(RESULT_OK, resultIntent);  // Ustawienie wyniku, który będzie przekazany z powrotem do MainActivity
    }
}
