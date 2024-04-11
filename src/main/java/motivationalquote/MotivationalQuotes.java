package motivationalquote;
import java.util.Random;

public class MotivationalQuotes {

    public static final String[] MOTIVATIONALQUOTELIST = {
        "The only way to do great work is to love what you do. - Steve Jobs\n",
        "Success is not final, failure is not fatal: It is the courage to continue that counts. - Winston Churchill\n",
        "Believe you can and you're halfway there. - Theodore Roosevelt\n",
        "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt\n",
        "It does not matter how slowly you go as long as you do not stop. - Confucius\n",
        "You are never too old to set another goal or to dream a new dream. - C.S. Lewis\n",
        "The only limit to our realization of tomorrow will be our doubts of today. - Franklin D. Roosevelt\n",
        "Success is not the key to happiness. Happiness is the key to success. " +
                "If you love what you are doing, you will be successful. - Albert Schweitzer\n",
        "The way to get started is to quit talking and begin doing. - Walt Disney\n",
        "Do not watch the clock; do what it does. Keep going. - Sam Levenson\n",
        "After a while you learn to ignore the " +
                "names people call you and just trust who you are. - Shrek\n",
        "Do not push buggy code. - nyh3\n",
        "Your limitation—it is only your imagination.\n",
        "Push yourself, because no one else is going to do it for you.\n",
        "Sometimes later becomes never. Do it now.\n",
        "Great things never come from comfort zones.\n",
        "Dream it. Wish it. Do it.\n",
        "Success does not just find you. You have to go out and get it.\n",
        "The harder you work for something, the greater you will feel when you achieve it.\n",
        "Dream bigger. Do bigger.\n",
        "Do not stop when you are tired. Stop when you’re done.\n",
        "Wake up with determination. Go to bed with satisfaction.\n",
        "Do something today that your future self will thank you for.\n",
        "Little things make big days.\n",
        "It is going to be hard, but hard does not mean impossible.\n",
        "Do not wait for opportunity. Create it.\n",
        "Sometimes we’re tested not to show our weaknesses, but to discover our strengths.\n",
        "The key to success is to focus on goals, not obstacles.\n",
        "Dream it. Believe it. Build it.\n",
        "The only way to do great work is to love what you do.\n",
        "In the middle of difficulty lies opportunity.\n",
        "You are capable of more than you know.\n",
        "Believe in yourself and all that you are. " +
                "Know that there is something inside you that is greater than any obstacle.\n",
        "Don’t be pushed around by the fears in your mind. Be led by the dreams in your heart.\n"
    };

    /**
     *
     * @return a randomly generated index from the MOTIVATIONALQUOTELIST array
     */
    public static String getQuote() {
        Random rand = new Random();
        int index = rand.nextInt(MOTIVATIONALQUOTELIST.length);
        return MOTIVATIONALQUOTELIST[index];
    }

}
