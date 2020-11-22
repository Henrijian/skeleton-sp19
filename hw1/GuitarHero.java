/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
import es.datastructur.synthesizer.GuitarString;

import java.util.HashMap;

public class GuitarHero {
    private static final double CONCERT_A = 440.0;
    private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);

    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        HashMap<Character, GuitarString> keyStringMap = new HashMap<>();
        for (int i = 0; i < keyboard.length(); i++) {
            Character key = (Character) keyboard.charAt(i);
            Double frequency = 440 * Math.pow(2, (i - 24) / 12);
            GuitarString guitarString = new GuitarString(frequency);
            keyStringMap.put(key, guitarString);
        }

        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (keyboard.indexOf(key) >= 0) {
                    GuitarString guitarString = keyStringMap.get(key);
                    guitarString.pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = 0;
            for (Character key: keyStringMap.keySet()) {
                GuitarString guitarString = keyStringMap.get(key);
                sample += guitarString.sample();
                guitarString.tic();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);
        }
    }
}

