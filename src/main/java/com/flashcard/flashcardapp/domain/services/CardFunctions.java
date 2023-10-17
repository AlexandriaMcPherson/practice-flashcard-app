package com.flashcard.flashcardapp.domain.services;

public class CardFunctions {

    public static double calculateNewInterval(double prevInterval, float ease, int score) {
        switch (score) {
            case 1:
            return 0;
            case 2:
            return Math.max(prevInterval + 1.0, (prevInterval + score / 4) * 1.2);
            case 3:
            return Math.max(prevInterval + 1.0, (prevInterval + score / 2) * ease);
            case 4:
            return Math.max(prevInterval + 1.0, (prevInterval + score) * ease);
            default:
            return prevInterval + 1.0;
        }
    }

    public static float calculateNewEase(float prevEase, int score) {
        switch (score) {
            case 1:
            return Math.max(1.3F, prevEase - 0.2F);
            case 2:
            return Math.max(1.3F, prevEase - 0.15F);
            case 4:
            return Math.min(2.5F, prevEase + 0.15F);
            default:
            return prevEase;
        }
    }

    public static long calculateNewTimeMillis(double reviewInterval) {
        // Current time + (interval * millis in a day)
        return (long) (System.currentTimeMillis() + (reviewInterval * 86400000L));
    }


}
