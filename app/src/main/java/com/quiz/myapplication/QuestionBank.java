package com.quiz.myapplication;

import java.util.ArrayList;
import java.util.Collections;

public class QuestionBank {

    public static ArrayList<Question> getQuestions() {
        ArrayList<Question> banglaQuestions = new ArrayList<>();
        ArrayList<Question> englishQuestions = new ArrayList<>();
        ArrayList<Question> finalQuestions = new ArrayList<>();

        // বাংলা প্রশ্ন
        banglaQuestions.add(new Question("বাংলা", "পদ্মা সেতুর দৈর্ঘ্য কত?", new String[]{"৬.১৫ কিমি", "৭.১০ কিমি", "৮.২৫ কিমি", "৫.৪০ কিমি"}, 0));
        banglaQuestions.add(new Question("বাংলা", "বাংলাদেশের জাতীয় সংগীত কে রচনা করেছেন?", new String[]{"কাজী নজরুল ইসলাম", "রবীন্দ্রনাথ ঠাকুর", "সুকান্ত ভট্টাচার্য", "মাইকেল মধুসূদন দত্ত"}, 1));
        banglaQuestions.add(new Question("বাংলা", "বাংলাদেশের মুদ্রার নাম কী?", new String[]{"টাকা", "রূপি", "ডলার", "ইউরো"}, 0));
        banglaQuestions.add(new Question("বাংলা", "বাংলাদেশের প্রধান নদীর নাম কী?", new String[]{"পদ্মা", "যমুনা", "মেঘনা", "সবগুলো"}, 3));
        banglaQuestions.add(new Question("বাংলা", "বাংলাদেশ স্বাধীনতা লাভ করে কোন সালে?", new String[]{"১৯৭১", "১৯৫২", "১৯৪৭", "১৯৬৫"}, 0));

        // ইংরেজি প্রশ্ন
        englishQuestions.add(new Question("ইংরেজি", "Who is known as the father of Computer Science?", new String[]{"Alan Turing", "Charles Babbage", "John Von Neumann", "Ada Lovelace"}, 1));
        englishQuestions.add(new Question("ইংরেজি", "What is the largest planet in our solar system?", new String[]{"Earth", "Mars", "Jupiter", "Saturn"}, 2));

        // Randomize and Select
        Collections.shuffle(banglaQuestions);
        Collections.shuffle(englishQuestions);

        finalQuestions.addAll(banglaQuestions.subList(0, 3)); // 3 Bangla questions
        finalQuestions.addAll(englishQuestions.subList(0, 2)); // 2 English questions

        return finalQuestions;
    }
}
